import java.util.*;
public class Main {
	static int M,N;
	static int[][] map;
	static Queue<Pair> que;
	
	public static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		que=new LinkedList<Pair>();
		
		M=sc.nextInt();
		N=sc.nextInt();
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==1) {
					que.add(new Pair(i,j));
				}
			}
		}
		int day=0;
		while(!que.isEmpty()) {
			day+=1;
			que=bfs(que,day);
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println(day);
		}
		if(day>=1) {
			day-=1;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					day=-1;
				}
			}
		}
		System.out.println(day);
		
		
	}
	public static Queue<Pair> bfs(Queue<Pair> que, int day) {
		Queue<Pair> newQ=new LinkedList<Pair>();
		while(!que.isEmpty()) {
			Pair p=que.remove();
			//System.out.println(p.x+" "+p.y+" "+N+" "+M);
			if(p.x+1<N && map[p.x+1][p.y]==0) {
				map[p.x+1][p.y]=day;
				newQ.add(new Pair(p.x+1,p.y));
			}
			if(p.x-1>=0 && map[p.x-1][p.y]==0) {
				map[p.x-1][p.y]=day;
				newQ.add(new Pair(p.x-1,p.y));
			}
			if(p.y+1<M && map[p.x][p.y+1]==0) {
				map[p.x][p.y+1]=day;
				newQ.add(new Pair(p.x,p.y+1));
			}
			if(p.y-1>=0 && map[p.x][p.y-1]==0) {
				map[p.x][p.y-1]=day;
				newQ.add(new Pair(p.x,p.y-1));
			}
		}
		return newQ;	
	}
}
