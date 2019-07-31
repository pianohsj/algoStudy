package 백준;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2667_단지번호붙이기_SSR {
	static int N;
	static int map[][];
	static boolean visited[][];
	static LinkedList<Integer> dangi;
	final static int dx[]= {1,-1,0,0};
	final static int dy[]= {0,0,1,-1};

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		
		visited=new boolean[N][N];
		map=new int[N][N];
		dangi=new LinkedList<Integer>();
		
		for(int i=0;i<N;i++) {
			String line=sc.next();
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(line.substring(j,j+1));
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1&&!visited[i][j])bfs(i,j);
			
			}
		}
		System.out.println(dangi.size());
		
		Collections.sort(dangi);
		for(int d:dangi)
			System.out.println(d);
	
	}
	
	public static void bfs(int x, int y){
		int points[]= new int[]{x,y};
		int cnt=1;
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(points);
		
		visited[x][y]=true;
		while(!q.isEmpty()) {
			int temp[]=q.remove();
			
			for(int i=0;i<4;i++) {
				int nx=temp[0]+dx[i];
				int ny=temp[1]+dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N) {
					if(!visited[nx][ny]&&map[nx][ny]==1) {
						visited[nx][ny]=true;
						q.add(new int[] {nx,ny});
						cnt++;
					}
				}
			}	
		}
		dangi.add(cnt);
	}

}
