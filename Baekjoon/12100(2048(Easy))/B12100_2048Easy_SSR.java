package 백준;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B12100_2048Easy_SSR {
	static int N;
	static int map[][], copy_map[][];
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		copy_map = new int[N][N];
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				map[i][j]=sc.nextInt();
		
		dfs(0);
		System.out.println(max);
	}

	static void dfs(int depth) {
		int copy_map[][] =new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copy_map[i][j]=map[i][j];
			}
		}
		if(depth==5) {
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					copy_map[i][j]=map[i][j];
					if(map[i][j]>max)max=map[i][j];
				}
			}
			return;
		}
		
		for(int i=0;i<4;i++) {
			map=merge(i);
			dfs(depth+1);
			
			for(int x=0;x<N;x++) {
				for(int j=0;j<N;j++) {
					map[x][j]=copy_map[x][j];
				}
			}
		}
		
	}
	
	static int[][] merge(int dir) {
		Queue<Integer> q = new LinkedList<Integer>();
		int temp_map[][] = new int[N][N]; 
		
		switch(dir) {
		//오
		case 0:
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=0)
						q.add(map[i][j]);
				}
				int y=0;
				while(!q.isEmpty()) {
					if(temp_map[i][y]==0) {
						temp_map[i][y]=q.remove();
						
					}else if(temp_map[i][y]==q.peek()) {
						temp_map[i][y]+=q.remove();
						y++;
						
					}else {
						y++;
						temp_map[i][y]=q.remove();
						
						
					}
				}
			}
			break;
		//왼
		case 1:
			for(int i=0;i<N;i++) {
				for(int j=N-1;j>=0;j--) {
					if(map[i][j]!=0)
						q.add(map[i][j]);
				}
				int y=N-1;
				while(!q.isEmpty()) {
					if(temp_map[i][y]==0) {
						temp_map[i][y]=q.remove();
						
					}else if(temp_map[i][y]==q.peek()) {
						temp_map[i][y]+=q.remove();
						y--;
						
					}else {
						y--;
						temp_map[i][y]=q.remove();
						
						
					}
				}
			}
			break;
		//상
		case 2:
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[j][i]!=0)
						q.add(map[j][i]);
				}
				int x=0;
				while(!q.isEmpty()) {
					if(temp_map[x][i]==0) {
						temp_map[x][i]=q.remove();
						
					}else if(temp_map[x][i]==q.peek()) {
						temp_map[x][i]+=q.remove();
						x++;
						
					}else {
						x++;
						temp_map[x][i]=q.remove();
						
						
					}
				}
			}
			break;
		//하
		case 3: 
			for(int i=0;i<N;i++) {
				for(int j=N-1;j>=0;j--) {
					if(map[j][i]!=0)
						q.add(map[j][i]);
				}
				int x=N-1;
				while(!q.isEmpty()) {
					if(temp_map[x][i]==0) {
						temp_map[x][i]=q.remove();
						
					}else if(temp_map[x][i]==q.peek()) {
						temp_map[x][i]+=q.remove();
						x--;
						
					}else {
						x--;
						temp_map[x][i]=q.remove();
						
						
					}
				}
			}
			break;
		}
		return temp_map;
	}
}

