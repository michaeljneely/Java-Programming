package assignment7;

/** Author: Michael Neely 13100590 **/
public class BSTDemo {
	
	private static BinarySearchTreeR<String> bst = new BinarySearchTreeR<String>();
	private static String[] someNFLTeams = {"Panthers", "Packers",  "Seahawks", "Patriots", "Chargers", 
	"Eagles", "Cowboys", "Texans", "Rams", "Steelers" , "Cardinals", "Broncos"};
	
	public static void main(String[] args){
		
		//Task 1 : Add 12 Strings (NFL Teams) to Initially Empty BST
		for(String team : someNFLTeams){
			bst.add(team);
		}
		
		//Task 2: Perform an in-order traversal to see the contents of the resulting tree
		bst.inorderTraverse();
		
		System.out.println("---");
		
		//Task 3: Add a String that was previously added
		bst.add("Seahawks");
		//Perform Traverse Again
		bst.inorderTraverse();
		
		System.out.println("---");
		
		//Task 4: Remove 2 Strings and perform In-Order Traverse Again
		bst.remove("Eagles");
		bst.remove("Seahawks");
		bst.inorderTraverse();
		
		System.out.println("---");
		
		//Task 5: Perform searches for two strings, one that is in the tree and one that is not, and display the results. 
		System.out.println(bst.getEntry("Cowboys"));
		System.out.println(bst.getEntry("Buccaneers"));
		
	}
}
