package assignment7;

/** Author:  Michael Neely 13100590 **/
public class AVLvsBST {

	private static BinarySearchTreeR<String> bst = new BinarySearchTreeR<String>();
	private static AVLTree<String> avl = new AVLTree<String>();
	private static String[] someNFLTeams = {"Panthers", "Packers",  "Seahawks", "Patriots", "Chargers", 
			"Eagles", "Cowboys", "Texans", "Rams", "Steelers" , "Cardinals", "Broncos"};

	public static void main(String[] args){

		//Set Up BST and AVL
		for(String team : someNFLTeams){
			avl.add(team);
			bst.add(team);
		}
		
		//Search Test
		String search = "Cowboys";
		avl.getEntry(search);
		int avlOps = avl.getOps();
		bst.getEntry(search);
		int bstOps = bst.getOps();
		System.out.println("In a Worst Case Search Situation to find \"" + search + "\", the AVL Tree took " + avlOps + " operations, and the Regular BST took " + bstOps + " operations" ); 
		
		//InsertionTest
		String bears = "Bears";
		bst.add(bears);
		avl.add(bears);
		int avlInsertOps = avl.getInsertOps();
		int bstInsertOps = bst.getInsertOps();
		System.out.println("In a Worst Case Insertion Situation to insert \"" + bears + "\", the AVL Tree took " + avlInsertOps + " operations, "
		+ "and the Regular BST took " + bstInsertOps + " operations" ); 
		
	}
}
