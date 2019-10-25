public class Node {

	Node parent;
	
	int matrix[][];
	int x,y;			//Current coordinate of blank tile
	
	
	//The cost i.e heuristic function h(x)
	int cost;
	
	//Level i.e g(n)
	int level;
	
	//Constructor to initialize the state
	
	public Node(int matrix[][],int x,int y, int newX,int newY,int level, Node parent) {
		
		this.matrix = new int[3][3];
		//copying the passed matrix into our matrix
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
					this.matrix[i][j] = matrix[i][j];
		}
		
		//Now we want to swap the element with newX and newY cause that is our next state
		
		int temp = this.matrix[newX][newY];
		this.matrix[newX][newY] = this.matrix[x][y];
		this.matrix[x][y] = temp;
		
		//We will intialize cost of each state in the puzzle class. So for timing initialize with max value
		
		this.cost = Integer.MAX_VALUE;
		this.level = level;
		
		this.parent = parent;
		
		//Change the position of the blank tile
		this.x= newX;
		this.y = newY; 
		}
	
	
	
}
