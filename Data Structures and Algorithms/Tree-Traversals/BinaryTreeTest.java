package assignment6;

import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTreeTest {

	public static ArrayList<String> words = new ArrayList<String>();

	public static void main(String[] args) {

		//Get Desired Height
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Desired Height of a Full Binary Tree: ");
		int height = in.nextInt();
		
		//Get Number of Nodes that will be in the tree and create that many strings
		int numNodes = (int) Math.pow(2, height) - 1;
		for(int i = 0; i < numNodes; i++){
			words.add("word" + i);
		}
		//Create The Tree
		BinaryTree<String> testTree = createFullBinaryTree(height);
		/** Perform Traversals **/
		
		//Perform Pre-Order Traversal
		System.out.println("\nPre-Order traversal of the test tree, printing each node when visiting it ...");
		testTree.preOrderTraverse();
		
		//Perform Post-Order Traversal
		System.out.println("\nPost-Order traversal of the test tree, printing each node when visiting it ...");
		testTree.postOrderTraverse();
		
		//Perform Breadth-First Traversal	
		System.out.println("\nBreadth-First traversal of the test tree, printing each node when visiting it ...");
		testTree.breadthFirstTraverse();
	}

	public static BinaryTree<String> createFullBinaryTree(int height) {
		BinaryTree<String> tree =  null;
		//If height is 1 create node with no sub trees 
		if(height == 1){
			tree = new BinaryTree<String>(words.remove(0), null, null);
			return tree;
		}
		//Otherwise Recursively Build Sub Trees
		else{
			//Build Recursive Left Child
			BinaryTree<String> leftChild = createFullBinaryTree(height-1);
			//Build Recursive Right Child
			BinaryTree<String> rightChild = createFullBinaryTree(height-1);
			//Build new tree with these as its children
			tree = new BinaryTree<String>(words.remove(0), leftChild, rightChild);
			return tree;
		}
	}

}
