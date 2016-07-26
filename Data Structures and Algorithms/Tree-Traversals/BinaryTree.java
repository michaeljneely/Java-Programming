package assignment6;import java.util.ArrayList;/** * A class that implements the ADT binary tree.
 *  * @author Frank M. Carrano * @version 2.0 */

public class BinaryTree<T> implements BinaryTreeInterface<T>, java.io.Serializable{
	private static final long serialVersionUID = -1932834476575953631L;	private BinaryNodeInterface<T> root;

	public BinaryTree(){		root = null;
	} 
	public BinaryTree(T rootData){
		root = new BinaryNode<T>(rootData);	} 
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		privateSetTree(rootData, leftTree, rightTree);	} 	public void setTree(T rootData){
		root = new BinaryNode<T>(rootData);
	} 
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree){
		privateSetTree(rootData, (BinaryTree<T>)leftTree,(BinaryTree<T>)rightTree);	} 
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){		root = new BinaryNode<T>(rootData);		if ((leftTree != null) && !leftTree.isEmpty()) root.setLeftChild(leftTree.root);		if ((rightTree != null) && !rightTree.isEmpty()){
			if (rightTree != leftTree)				root.setRightChild(rightTree.root);			else
				root.setRightChild(rightTree.root.copy());		} 		if ((leftTree != null) && (leftTree != this)) leftTree.clear();		if ((rightTree != null) && (rightTree != this)) rightTree.clear();	}		/*	private BinaryNode<T> copyNodes(){		return (BinaryNode<T>)root.copy();	} */
	public T getRootData(){		T rootData = null;		if (root != null)rootData = root.getData();		return rootData;	} 	public boolean isEmpty(){		return root == null;	} 
	public void clear(){		root = null;	} 	protected void setRootData(T rootData){		root.setData(rootData);
	} 
	protected void setRootNode(BinaryNodeInterface<T> rootNode){
		root = rootNode;	} 	protected BinaryNodeInterface<T> getRootNode(){
		return root;
	} 		public int getHeight(){
		return root.getHeight();	} 
	public int getNumberOfNodes(){		return root.getNumberOfNodes();	} 	public void inorderTraverse(){		inorderTraverse(root);	}
	private void inorderTraverse(BinaryNodeInterface<T> node){
		if (node != null){
			inorderTraverse(node.getLeftChild());			System.out.println(node.getData());			inorderTraverse(node.getRightChild());		} 	} 		/** NEW - Other Traversals **/			/** Pre-Order Traversal **	 * Algorithm (starting at root node):	 * 1)Display data of node	 * 2)Traverse the left subtree by recursively calling preOrderTraversal()	 * 3)Traverse the right subtree by recursively calling preOrderTraversal()	 */		public void preOrderTraverse(){		//Start Pre-Order Traversal at Root Node		preOrderTraverse(root);	}		private void preOrderTraverse(BinaryNodeInterface<T> node){		if (node != null){			//Print Data			System.out.println(node.getData());			//Recursively Traverse Left Tree			preOrderTraverse(node.getLeftChild());			//Recursively Traverse Right Tree			preOrderTraverse(node.getRightChild());		}	}		/** Post-Order Traversal **	 * Algorithm (starting at the root node):	 * 1) Traverse the left subtree by recursively calling postOrderTraversal()	 * 2) Traverse the right right subtree by recursively calling postOrderTraversal()	 * 3) Display data of node	 */		public void postOrderTraverse(){		//Start Post-Order Traversal at Root Node		postOrderTraverse(root);	}		private void postOrderTraverse(BinaryNodeInterface<T> node){		if (node != null){			//Recursively Traverse Left Tree			postOrderTraverse(node.getLeftChild());			//Recursively Traverse Right Tree			postOrderTraverse(node.getRightChild());			//Print Data			System.out.println(node.getData());		}	}		/** Breadth-First (Level-Order) Traversal **	 * Requires an ArrayList	 * Algorithm (starting at the root node):	 * 1)Add the current node to the array List	 * 2)While the ArrayList isn't empty:	 *     3)Remove first node in the list	 *     4)Display its data	 *     5)If this node has a left child, add its left child to the list	 *     6) If this node has a right child add its right child to the list	 *7)End While	 */		public void breadthFirstTraverse(){		//Start Breadth-First Traversal at Root Node		breadthFirstTraverse(root);	}	private void breadthFirstTraverse(BinaryNodeInterface<T> node){		//Array List to hold Nodes		ArrayList<BinaryNodeInterface<T>> nodeList = new ArrayList<BinaryNodeInterface<T>>();		//Add the root node		nodeList.add(node);		while(!nodeList.isEmpty()){			//Remove first node in list			BinaryNodeInterface<T> temp = nodeList.remove(0);			//Display its data			System.out.println(temp.getData());			//If it has a left child add it to the ArrayList			if(temp.hasLeftChild()) nodeList.add(temp.getLeftChild());			//If it has a right child add it to the ArrayList			if(temp.hasRightChild()) nodeList.add(temp.getRightChild());			//Repeat...		}	}

} 