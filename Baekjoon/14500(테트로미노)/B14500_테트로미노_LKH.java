import java.util.*;
public class Main {
	static int block[][][] = {
							{{1,1,1,1},	
							 {0,0,0,0},
			                 {0,0,0,0},
			                 {0,0,0,0}},
							
							{{1,0,0,0},	
							 {1,0,0,0},
				             {1,0,0,0},
				             {1,0,0,0}},
							/////
							{{1,1,0,0},	
						     {1,1,0,0},
				             {0,0,0,0},
				             {0,0,0,0}},
							/////
							{{1,0,0,0},	
							 {1,0,0,0},
				             {1,1,0,0},
				             {0,0,0,0}},
							
							{{0,0,1,0},	
						     {1,1,1,0},
				             {0,0,0,0},
				             {0,0,0,0}},
							
							{{1,1,0,0},	
							 {0,1,0,0},
				             {0,1,0,0},
				             {0,0,0,0}},
							
							{{1,1,1,0},	
						     {1,0,0,0},
				             {0,0,0,0},
				             {0,0,0,0}},
							////
							{{0,1,0,0},	
							 {0,1,0,0},
				             {1,1,0,0},
				             {0,0,0,0}},
							
							{{1,1,1,0},	
							 {0,0,1,0},
					         {0,0,0,0},
					         {0,0,0,0}},
							
							{{1,1,0,0},	
							 {1,0,0,0},
						     {1,0,0,0},
						     {0,0,0,0}},
							
							{{1,0,0,0},	
						     {1,1,1,0},
							 {0,0,0,0},
							 {0,0,0,0}},
							///
							{{0,1,0,0},	
							 {1,1,0,0},
					         {1,0,0,0},
					         {0,0,0,0}},
								
							{{0,1,1,0},	
							 {1,1,0,0},
					         {0,0,0,0},
					         {0,0,0,0}},
							//	
							{{1,0,0,0},	
							 {1,1,0,0},
						     {0,1,0,0},
						     {0,0,0,0}},
								
							{{1,1,0,0},	
						     {0,1,1,0},
							 {0,0,0,0},
							 {0,0,0,0}},
							///
								
							{{0,1,0,0},	
							 {1,1,1,0},
				             {0,0,0,0},
				             {0,0,0,0}},
									
							{{1,1,1,0},	
							 {0,1,0,0},
							 {0,0,0,0},
					         {0,0,0,0}},
									
							{{1,0,0,0},	
							 {1,1,0,0},
							 {1,0,0,0},
							 {0,0,0,0}},
									
							{{0,1,0,0},	
						     {1,1,0,0},
							 {0,1,0,0},
							 {0,0,0,0}}
							 ///
							
};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		int sum = 0;
		for(int b=0; b<block.length; b++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					for(int y=0; y<4; y++) {
						for(int x=0; x<4; x++) {
							if(block[b][y][x] == 1) {
								if(j+x<m && y+i<n) {
									sum +=map[i+y][j+x];
								}
							}
						}
					}
					if(sum>max) {
						max = sum;
					}
					sum = 0;
				}
			}
		}
		System.out.println(max);
		
	}
}