import java.util.*;

public class Main {
	static int N,L,R;
	static int[][] map;
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int ans=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		L=sc.nextInt();
		R=sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				map[i][j]=sc.nextInt();
			}
		}
		solve();
		System.out.println(ans);
	}
	public static class Pair{
		public int x,y;
		Pair(int x, int y){
			this.x=x;this.y=y;
		}
	}
	public static void solve() {
		int[][] visited=new int[N][N];
		int number=0;
		int[] r=new int[2501];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]==0) {
					number+=1;
					int people=0;
					int contry=0;
					Queue<Pair> que=new LinkedList<Pair>();
					
					visited[i][j]=number;
					que.add(new Pair(i,j));
					people+=map[i][j];
					contry+=1;
					
					while(!que.isEmpty()) {
						Pair p=que.poll();
						for(int d=0; d<4; d++) {
							if(p.x+dx[d]>=0 && p.x+dx[d]<N && p.y+dy[d]>=0 && p.y+dy[d]<N
									&& Math.abs(map[p.x][p.y]-map[p.x+dx[d]][p.y+dy[d]])>=L
									&& Math.abs(map[p.x][p.y]-map[p.x+dx[d]][p.y+dy[d]])<=R
									&& visited[p.x+dx[d]][p.y+dy[d]]==0) {
								
								visited[p.x+dx[d]][p.y+dy[d]]=number;
								que.add(new Pair(p.x+dx[d],p.y+dy[d]));
								people+=map[p.x+dx[d]][p.y+dy[d]];
								contry+=1;
							}
						}
					}
					r[number]=people/contry;
				}
			}
				
		}
		if(number<N*N) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=r[visited[i][j]];
				}
			}
			ans+=1;
			solve();
		}else {
			return;
		}
	}
}
