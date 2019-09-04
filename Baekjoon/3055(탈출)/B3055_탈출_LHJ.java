import java.util.*;
public class Main {
	public static int R,C;
	public static String[][] map;
	public static int[][] m;
	public static int[][] visited;
	public static int[] dx= {-1,0,1,0};
	public static int[] dy= {0,1,0,-1};
	public static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		R=sc.nextInt();
		C=sc.nextInt();
		map=new String[R][C];
		
		m=new int[R][C];
		visited=new int[R][C];
		String temp;
		Pair s=null;
		Queue<Pair> que=new LinkedList<Pair>();
		for(int i=0; i<R; i++) {
			temp=sc.next();
			for(int j=0; j<C; j++) {
				map[i][j]=""+temp.charAt(j);
				if(map[i][j].equals("S")) {
					s=new Pair(i,j);
					visited[i][j]=1;
					m[i][j]=-1;
				}else if(map[i][j].equals("D")) {
					m[i][j]=-2;
				}else if(map[i][j].equals("*")) {
					m[i][j]=1;
					que.add(new Pair(i,j));
				}else if(map[i][j].equals("X")) {
					m[i][j]=-3;
				}else {
					m[i][j]=0;
				}
			}
		}
		while(!que.isEmpty()) {
			Pair p=que.poll();
			for(int d=0; d<4; d++) {
				if(p.x+dx[d]>=0 && p.y+dy[d]>=0 && p.x+dx[d]<R && p.y+dy[d]<C && m[p.x+dx[d]][p.y+dy[d]]==0) {
					m[p.x+dx[d]][p.y+dy[d]]=m[p.x][p.y]+1;
					que.add(new Pair(p.x+dx[d],p.y+dy[d]));
				}
			}
		}
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(m[i][j]+" ");
//			}
//			System.out.println("");
//		}
		dfs(s,1);
		if(ans==Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(ans);
		}
		
		

	}
	public static void dfs(Pair p,int n){//처음 n은 1
		//System.out.println(p.x+" "+p.y);
		for(int d=0; d<4; d++) {
			if(p.x+dx[d]>=0 && p.y+dy[d]>=0 && p.x+dx[d]<R && p.y+dy[d]<C 
					&& (m[p.x+dx[d]][p.y+dy[d]]>n+1 || m[p.x+dx[d]][p.y+dy[d]]==-2 || m[p.x+dx[d]][p.y+dy[d]]==0) && visited[p.x+dx[d]][p.y+dy[d]]!=1) {
				if(m[p.x+dx[d]][p.y+dy[d]]==-2) {
					//System.out.println(n);
					ans=Math.min(n, ans);
					return;
				}else {
					visited[p.x+dx[d]][p.y+dy[d]]=1;
					dfs(new Pair(p.x+dx[d],p.y+dy[d]),n+1);
					visited[p.x+dx[d]][p.y+dy[d]]=0;
				}
			}
		}
	}
	public static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

}
