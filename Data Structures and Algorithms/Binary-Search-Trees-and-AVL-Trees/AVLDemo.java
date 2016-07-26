package assignment7;

/** Author: Michael Neely 13100590 **/
public class AVLDemo {

	private static AVLTree<String> avl = new AVLTree<String>();
	private static String[] someNFLTeams = {"Panthers", "Packers",  "Seahawks", "Patriots", "Chargers", 
			"Eagles", "Cowboys", "Texans", "Rams", "Steelers" , "Cardinals", "Broncos"};

	public static void main(String[] args){

		//Task 1 : Add 12 Strings (NFL Teams) to Initially Empty AVL Tree
		for(String team : someNFLTeams){
			avl.add(team);
		}

		//Task 2: Perform an in-order traversal to see the contents of the resulting tree
		avl.inorderTraverse();

		System.out.println("---");

		//Task 3: Add a String that was previously added
		avl.add("Seahawks");
		//Perform Traverse Again
		avl.inorderTraverse();

		System.out.println("---");
		
		//Task 5: Perform searches for two strings, one that is in the tree and one that is not, and display the results. 
		System.out.println(avl.getEntry("Cowboys"));
		System.out.println(avl.getEntry("Buccaneers"));

	}
}
