import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static int[][] length;
	static int nH,nC;
	static int ans=Integer.MAX_VALUE;
	public static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][N];
		
		Pair[] house=new Pair[N];
		Pair[] chicken = new Pair[N];
		
		nH=0;
		nC=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==1) {
					house[nH]=new Pair(i,j);
					nH+=1;
				}else if(map[i][j]==2) {
					chicken[nC]=new Pair(i,j);
					nC+=1;
				}
			}
		}
		length=new int[nH][nC];
		for(int i=0; i<nH; i++) {
			for(int j=0; j<nC; j++) {
				length[i][j]=Math.abs(house[i].x-chicken[j].x)+Math.abs(house[i].y-chicken[j].y);
			}
		}
		int[] visited = new int[nC];
//		for(int i=0; i<nH; i++) {
//			for(int j=0; j<nC; j++) {
//				System.out.print(length[i][j]);
//			}
//			System.out.println("");
//		}
		//System.out.println(nH+" "+nC);
		dfs(0,visited);
		System.out.println(ans);
	}
	public static void dfs(int len, int[] visited) {
		if(len==nC) {
			int cL=0;
			for(int i=0; i<nH; i++) {
				cL+=chickenLength(visited,i);
			}
			ans=Math.min(ans, cL);
		}
		for(int i=0; i<nC; i++) {
			if(visited[i]==0) {
				visited[i]=1;
				dfs(len+1,visited);
				visited[i]=0;
			}
		}
	}
	public static int chickenLength(int[] visited,int houseIdx) {
		int min=Integer.MAX_VALUE;
		for(int i=0; i<nC; i++) {
			if(visited[i]==0) {
				if(min>length[houseIdx][i]) {
					min=length[houseIdx][i];
				}
			}
		}
		return min;
	}
}
