// ===== Abstract Products =====
abstract class Burger {
    public abstract void createBurger();
}

abstract class Bread {
    public abstract void prepareBread();
}

// ===== Concrete Burger Products =====
// Normal Burgers
class BasicBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Basic Burger: Bun and Patty");
    }
}

class StandardBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Standard Burger: Bun, Patty, and Lettuce");
    }
}

class PremiumBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Premium Burger: Bun, Lettuce, Patty, Cheese, and White Sauce");
    }
}

// Wheat Burgers
class BasicWheatBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Basic Wheat Burger: Wheat Bun and Patty");
    }
}

class StandardWheatBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Standard Wheat Burger: Wheat Bun, Patty, and Lettuce");
    }
}

class PremiumWheatBurger extends Burger {
    @Override
    public void createBurger() {
        System.out.println("Premium Wheat Burger: Wheat Bun, Lettuce, Patty, Cheese, and White Sauce");
    }
}

// ===== Concrete Bread Products =====
class NormalBread extends Bread {
    @Override
    public void prepareBread() {
        System.out.println("Preparing Normal Bread");
    }
}

class WheatBread extends Bread {
    @Override
    public void prepareBread() {
        System.out.println("Preparing Wheat Bread");
    }
}

// ===== Abstract Factory =====
abstract class BurgerFactory {
    public abstract Burger createBurger(String type);
    public abstract Bread createBread();
}

// ===== Concrete Factories =====
class MukitBurgerFactory extends BurgerFactory {
    @Override
    public Burger createBurger(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicBurger();
            case "standard":
                return new StandardBurger();
            case "premium":
                return new PremiumBurger();
            default:
                return null;
        }
    }

    @Override
    public Bread createBread() {
        return new NormalBread();
    }
}

class MuhsinBurgerFactory extends BurgerFactory {
    @Override
    public Burger createBurger(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicWheatBurger();
            case "standard":
                return new StandardWheatBurger();
            case "premium":
                return new PremiumWheatBurger();
            default:
                return null;
        }
    }

    @Override
    public Bread createBread() {
        return new WheatBread();
    }
}

// ===== Client Code =====
public class FactoryDesignPattern {
    public static void main(String[] args) {
        String type = "premium";

        BurgerFactory mukitFactory = new MukitBurgerFactory();
        BurgerFactory muhsinFactory = new MuhsinBurgerFactory();

        System.out.println("Mukit's Combo:");
        Burger mukitBurger = mukitFactory.createBurger(type);
        Bread mukitBread = mukitFactory.createBread();
        mukitBread.prepareBread();
        mukitBurger.createBurger();

        System.out.println();

        System.out.println("Muhsin's Combo:");
        Burger muhsinBurger = muhsinFactory.createBurger(type);
        Bread muhsinBread = muhsinFactory.createBread();
        muhsinBread.prepareBread();
        muhsinBurger.createBurger();
    }
}
