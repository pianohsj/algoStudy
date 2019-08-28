package 백준;

import java.util.LinkedList;
import java.util.Scanner;

public class B16234_인구이동_ssr {
	static int N,L,R;
	static int map[][];
	static boolean visited[][];
	static int cnt=0;
	
	final static int dx[] = {1,-1,0,0};
	final static int dy[] = {0,0,-1,1};
	
	public static class Pair{
		int x,y;
		
		public Pair(int x, int y) {
			
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][N];

		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				map[i][j]=sc.nextInt();
		
		
		while(true) {
			visited = new boolean[N][N];
			boolean isMove=false;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]&&move(i,j)>1)isMove=true;
				}
			}
			
			if(isMove)cnt++;
			else break;
			
		}
		
		
		System.out.println(cnt);

	}
	
	static int move(int x, int y) {
		
		LinkedList<Pair> tempL = new LinkedList<Pair>();
		LinkedList<Pair> q = new LinkedList<>();
		int sum=map[x][y];
		
		tempL.add(new Pair(x,y));
		q.add(new Pair(x,y));
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			
			Pair c = q.remove();
			//4방향
			for(int i=0;i<4;i++) {
				int nx = c.x+dx[i];
				int ny = c.y+dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N&&!visited[nx][ny]) {
					int diff = Math.abs(map[c.x][c.y]-map[nx][ny]);
					
					if(diff>=L&&diff<=R) {
						sum+=map[nx][ny];
						tempL.add(new Pair(nx, ny));
						q.add(new Pair(nx,ny));
						visited[nx][ny]=true;
						//tempL.add(new Pair(nx,ny));
					}
				}
			}
		}
		
		if(tempL.size()>1) {
			int newPop = sum/tempL.size();
			
			for(Pair p:tempL) {
				map[p.x][p.y]=newPop;
				
			}
		}
		return tempL.size();
	}

}
