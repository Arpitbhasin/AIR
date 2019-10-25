import java.io.*;
import java.util.*;

public class Puzzle {

	static Scanner sc = new Scanner(System.in);
	static int row[] = {1,0,-1,0};
	static int col[] = {0,-1,0,1};
	
	
	public static int[][] acceptMatrix() {
		
		int matrix[][] = new int[3][3];
		
		for(int i=0;i<3;i++) {
			
			for(int j=0;j<3;j++)
				matrix[i][j] = sc.nextInt();
		}	
		return matrix;
	}
	
	public static int[] findBlank(int matrix[][]){
		
		int coordinate[] = new int[2];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				
				if(matrix[i][j] == 0) {
					coordinate[0] = i;
					coordinate[1] = j;
					break;
				}
			}
		}
		return coordinate;
	}

	public static boolean isSafe(int x,int y) {
		
		return (x >= 0 && x < 3 && y >= 0 && y< 3);
	}
	
	public static int calculateCost(int initial[][],int goal[][]) {
		
		int count = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				
				if(initial[i][j] != 0 && initial[i][j] != goal[i][j])
					count++;
			}
		}
		
		return count;
	}
	
	public static void printMatrix(int matrix[][]) {
		
		for(int i=0;i<3;i++) {
			
			for(int j=0;j<3;j++)
				System.out.print(matrix[i][j]+"\t");
			
			System.out.println();
		}
		
	}
	
	public static void printPath(Node child) {
		
		if(child == null)
				return;
		else {
			
			printPath(child.parent);
			printMatrix(child.matrix);
			System.out.println();
			
		}
		
	}
	
	public static void solve(int initial[][],int goal[][],int x,int y) {
		
		//Creating the root node
		Node root = new Node(initial,x,y,x,y,0,null);
		root.cost = calculateCost(root.matrix,goal);
		//System.out.println("X:"+x+"\tY:"+y);
		
		//Creating priority queue in order to store the nodes in ascending order of their cost
		//so that every time we remove the front we will get the node with minimum cost
		//for sorting according to our will we need to pass a comparator while creating priority queue
		PriorityQueue<Node> pq = new PriorityQueue<>(1000,(a,b)->(a.cost+a.level)-(b.cost+b.level));
		
		//Adding the root node in queue
		
		pq.add(root);
		System.out.println(root.cost);
		//Traversing queue while it is not empty or we reach the goal state
		
		while(!pq.isEmpty()) {
			
			//Node with minimum cost will be at the front always
			Node minimum = pq.poll();
			if(minimum.cost == 0) {
				
				//If reach the goal state then print all the path of how we reached the goal state
				printPath(minimum);
				return;
			}
			
			//Now generating all next possible moves from current state
			//Maximum there can be four moves
			for(int i=0;i<4;i++) {
				
				if(isSafe(minimum.x+row[i],minimum.y+col[i])) {
					//Creating new child node
					Node child = new Node(minimum.matrix,minimum.x,minimum.y,minimum.x+row[i],minimum.y+col[i],minimum.level+1,minimum);
					child.cost =calculateCost(child.matrix,goal);
					pq.add(child);
				}
				
			}
			
		}
}
	
	
	public static void main(String[] args) {
		
		
		System.out.println("Enter the current state of the board");
		int initial[][] = acceptMatrix();
		
		System.out.println("Enter the goal state of the board");
		int goal[][] = acceptMatrix();
		
		//Finding the blank tile
		int coordinate[] = findBlank(initial);
		int x = coordinate[0]; int y = coordinate[1];
		
		solve(initial,goal,x,y);
	}

}
