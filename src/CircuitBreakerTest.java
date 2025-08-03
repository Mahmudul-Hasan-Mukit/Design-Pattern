import java.util.Random;

public class CircuitBreakerTest {

    enum State { CLOSED, OPEN, HALF_OPEN }

    static class CircuitBreaker {
        private State state = State.CLOSED;
        private int failureCount = 0;
        private final int failureThreshold = 3;
        private final long retryTimePeriod = 5000; // milliseconds
        private long lastFailureTime = 0;

        public String call() {
            switch (state) {
                case OPEN:
                    if (System.currentTimeMillis() - lastFailureTime > retryTimePeriod) {
                        System.out.println("[CircuitBreaker] Moving to HALF_OPEN state.");
                        state = State.HALF_OPEN;
                    } else {
                        return "[CircuitBreaker] Request blocked (state: OPEN)";
                    }
                    // no break, fall through to attempt
                case HALF_OPEN:
                case CLOSED:
                    try {
                        String response = unreliableService();
                        onSuccess();
                        return "[Service] Success: " + response;
                    } catch (Exception e) {
                        onFailure();
                        return "[Service] Failed: " + e.getMessage();
                    }
                default:
                    return "[CircuitBreaker] Unknown state";
            }
        }

        private void onSuccess() {
            if (state == State.HALF_OPEN) {
                System.out.println("[CircuitBreaker] Success in HALF_OPEN → Switching to CLOSED");
            }
            failureCount = 0;
            state = State.CLOSED;
        }

        private void onFailure() {
            failureCount++;
            lastFailureTime = System.currentTimeMillis();
            System.out.println("[CircuitBreaker] Failure #" + failureCount);
            if (failureCount >= failureThreshold) {
                state = State.OPEN;
                System.out.println("[CircuitBreaker] Switching to OPEN state.");
            } else if (state == State.HALF_OPEN) {
                state = State.OPEN;
                System.out.println("[CircuitBreaker] HALF_OPEN failure → Switching back to OPEN");
            }
        }

        // Simulates a service that fails 50% of the time
        private String unreliableService() throws Exception {
            if (new Random().nextBoolean()) {
                throw new Exception("Service is down!");
            }
            return "Service response OK";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker cb = new CircuitBreaker();

        for (int i = 1; i <= 20; i++) {
            System.out.println("Request #" + i + ": " + cb.call());
            Thread.sleep(1000); // 1-second delay between requests
        }
    }
}
