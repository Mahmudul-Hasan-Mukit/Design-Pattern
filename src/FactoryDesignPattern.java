class Burger{
	public void createBurger() {
		
	}
}
class BasicBurger extends Burger{
	@Override
	public void createBurger() {
		System.out.println("Made with only bun and patty");
	}
}
class StandardBurger extends Burger{
	public void createBurger() {
		System.out.println("Made with bun,patty and latus ");
	}
}
class premiumBurger extends Burger{
	public void createBurger() {
		System.out.println("Bun,latus,patty,cheese,white sause");
	}
}
class Burgerfactory{
	public Burger prefer(String str) {
		if(str.equals("basic")) {
			return new BasicBurger();
		}else if(str.equals("standard"))return new StandardBurger();
		else if(str.equals("premium"))return new premiumBurger();
		else return null;
	}
}
public class FactoryDesignPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String type="premium";
		Burgerfactory bf=new Burgerfactory();
		Burger burger=bf.prefer(type);
		burger.createBurger();

	}

}
