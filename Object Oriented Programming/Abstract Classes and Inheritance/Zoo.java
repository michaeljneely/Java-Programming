package week6;

public class Zoo {

	public static void main(String[] args) {
		
		//Create animals
		Canary jeremy = new Canary("Jeremy", 1, 1);
		Canary reginald = new Canary("Reginald", 3, 4);
		
		Ostrich squawkers = new Ostrich("Squawkers", 2, 5);
		Ostrich lionel = new Ostrich("Lionel", 2, 5);
		
		NorwegianBlue bert = new NorwegianBlue("Bert", 0 , 1);
		NorwegianBlue spencer = new NorwegianBlue("Spencer", 0 , 1);
		
		Shark bruce = new Shark("Bruce", 0, 5);
		Shark jaws = new Shark("Jaws", 99, 99);
		
		Salmon finn = new Salmon("Finn", 30, 5);
		Salmon jake = new Salmon("Jake", 30, 5);
		
		
		//Show contents of zoo and demonstrate each animal moving
		System.out.println("Here's what's in the zoo: ");
		System.out.println("============================");
		
		System.out.println(jeremy.toString());
		jeremy.move(50);
		System.out.println("============================");
		
		System.out.println(reginald.toString());
		reginald.move(20);
		System.out.println("============================");
		
		System.out.println(squawkers.toString());
		squawkers.move(40);
		System.out.println("============================");
		
		System.out.println(lionel.toString());
		lionel.move(40);
		System.out.println("============================");
		
		System.out.println(bert.toString());
		bert.move(0);
		System.out.println("============================");
		
		System.out.println(spencer.toString());
		spencer.move(0);
		System.out.println("============================");
		
		System.out.println(bruce.toString());
		bruce.move(1000);
		System.out.println("============================");
		
		System.out.println(jaws.toString());
		jaws.move(10000);
		System.out.println("============================");
		
		System.out.println(finn.toString());
		finn.move(70);
		System.out.println("============================");
		
		System.out.println(jake.toString());
		jake.move(70);
		System.out.println("============================");
		
		
		//Check equality
		System.out.println("Equality between same species");
		System.out.println("============================");
		
		
		System.out.println("Canaries: Jeremy equals Reginald: " + jeremy.equals(reginald)); //Will be false
		System.out.println("Ostriches: Squawkers equals Lionel: " + squawkers.equals(lionel)); //Will be true
		System.out.println("Norwegian Blues: Bert equals Spencer: " + bert.equals(spencer)); //Will be true
		System.out.println("Sharks: Bruce equals Jaws: " + bruce.equals(jaws)); //Will be false
		System.out.println("Salmon: Finn equals Jake: " + finn.equals(jake)); //Will be true
		System.out.println("============================");
		
		System.out.println("Equality between different species"); //Will all be false
		System.out.println("============================");
		System.out.println("Canaries and Salmon? Jeremy equals Jake: " + jeremy.equals(jake));
		System.out.println("What about Sharks and Ostriches? Jaws equals Squawkers: " + jaws.equals(squawkers));
		System.out.println("How about Salmon and Sharks? Finn equals Bruce: " + finn.equals(bruce));
		System.out.println("Canaries and Norwegian Blues? Reginald equals Bert: " + reginald.equals(bert));
		
		
		//End
		System.out.println("============================");
		System.out.println("Thanks for visiting the Zoo!");
	}

}
