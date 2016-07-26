package assignment6;

import java.util.ArrayList;

public class OldBTDemo {
	public static BinaryTree<String> createFullStringTree(int height){
		BinaryTree<String> tree = null;

		if (height > 0){
			//Number of Nodes = 2^height - 1
			int numNodes = (int) Math.pow(2, height) - 1;
			//Number of Leaves = Number of Total Nodes + 1  divided by 2
			int numLeaves = (numNodes+1)/2;
			//Number of Internal Nodes = Number of Total Nodes -1 divided by 2
			int numInternalNodes = ((numNodes-1)/2)-1;
			//Make Random Strings to Fill Nodes
			String[] data = randStrings(numNodes);
			int counter = 0;


			//if 1
			if(height == 1){
				tree = new BinaryTree<String>(data[0]);
			}
			//if 2
			else if(height == 2){
				tree = new BinaryTree<String>(data[0], new BinaryTree<String>(data[1]), new BinaryTree<String>(data[2]));
			}
			else{

				//Make Leaf Trees
				ArrayList<BinaryTree<String>> leaveList = new ArrayList<BinaryTree<String>>(numLeaves);
				for(int i = 0; i < numLeaves; i++){
					leaveList.add(new BinaryTree<String>(data[counter]));
					counter++;
				}

				//Make Internal Node Trees
				ArrayList<BinaryTree<String>> internalList = new ArrayList<BinaryTree<String>>(numInternalNodes);
				for(int i = 0; i < numInternalNodes; i++){
					internalList.add(new BinaryTree<String>(data[counter]));
					counter++;
				}

				//Make Internal Node - Leaf Trees
				ArrayList<BinaryTree<String>> internalLeafList = new ArrayList<BinaryTree<String>>(numLeaves/2);
				for(int i = 0; i < numLeaves/2; i++){
					internalLeafList.add(new BinaryTree<String>(internalList.get(0).getRootData(), leaveList.remove(0), leaveList.remove(0)));
					internalList.remove(0);
				}

				//Connect Internal Nodes to Internal Node/Leaf Trees
				int numNodesLeft = numInternalNodes - numLeaves/2;
				ArrayList<BinaryTree<String>> finalList = new ArrayList<BinaryTree<String>>(numNodesLeft);
				while(!internalLeafList.isEmpty()){
					finalList.add(new BinaryTree<String>(internalList.get(0).getRootData(), internalLeafList.remove(0), internalLeafList.remove(0)));
					internalList.remove(0);

				}

				//Connect Internal Trees
				while(finalList.size() >= 3){
					finalList.add(new BinaryTree<String>(internalList.get(0).getRootData(), finalList.remove(0), finalList.remove(0)));
					internalList.remove(0);
				}

				tree = new BinaryTree<String>(data[counter], finalList.remove(0), finalList.remove(0));
			}
		}
		else{
			System.out.println("Height must be greater than 0");
			tree = null;
		}
		return tree;
	}

	public static String[] randStrings(int num){
		String[] strings = new String[num];
		for(int i = 0; i < num; i++){
			strings[i] = "word"+i;
		}
		return strings;
	}
}
