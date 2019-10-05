import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int ans=Integer.MAX_VALUE;
	public static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static class Edge{
		int x,y,v;
		Edge(int x,int y,int v){
			this.x=x;
			this.y=y;
			this.v=v;
		}
	}
	public static boolean isCycle(int[][] map, int a, int b,int[] p){
        int s=a;
        int e=b;
        while(p[s]!=0) {
        	s=p[s];
        }
        while(p[e]!=0) {
        	e=p[e];
        }
        if(s!=e) {
        	p[e]=s;
        	return false;
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		map=new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==1) {
					map[i][j]=-1;
				}
			}
		}
		int num=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==-1) {
					num+=1;
					Queue<Pair> que=new LinkedList<Pair>();
					que.add(new Pair(i,j));
					map[i][j]=num;
					while(!que.isEmpty()) {
						Pair p=que.poll();
						for(int d=0; d<4; d++) {
							int nx=p.x+dx[d];
							int ny=p.y+dy[d];
							
							if(nx>=0 && ny>=0 && nx<N && ny<M && map[nx][ny]==-1) {
								que.add(new Pair(nx,ny));
								map[nx][ny]=num;
							}
						}
					}
				}
			}
		}
		int[][] graph=new int[num+1][num+1];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]>0) {
					for(int d=0; d<4; d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						int count=0;
						while(nx>=0 && ny>=0 && nx<N && ny<M && map[nx][ny]==0) {
							count+=1;
							nx=nx+dx[d];
							ny=ny+dy[d];
						}
						if(nx>=0 && ny>=0 && nx<N && ny<M && map[nx][ny]!=map[i][j]) {
							if(count>=2) {
								if(graph[map[i][j]][map[nx][ny]]==0) {
									graph[map[i][j]][map[nx][ny]]=count;
								}else {
									graph[map[i][j]][map[nx][ny]]=Math.min(count, graph[map[i][j]][map[nx][ny]]);
								}
							}
						}
					}
				}
			}
		}
		Queue<Edge> que=new LinkedList<Edge>();
		for(int i=1; i<num; i++) {
			for(int j=i+1; j<num+1; j++) {
				if(graph[i][j]>0) {
					que.add(new Edge(i,j,graph[i][j]));
				}
			}
		}
		int bnum=que.size();
		Edge[] bridge=que.toArray(new Edge[bnum]);

		//solve(num,graph);
		solve2(new int[num+1],0,bnum,bridge,num);
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	public static void solve(int num,int[][] graph) {
		int[][] m=new int[num+1][num+1];
		PriorityQueue<Edge> pq=new PriorityQueue<Edge>((Edge a, Edge b)->a.v-b.v);
		
		for(int i=0; i<num; i++) {
			for(int j=i+1; j<num+1; j++) {
				if(graph[i][j]>=2) {
					pq.add(new Edge(i,j,graph[i][j]));
				}
			}
		}
		int count=0;
		int nn=0;
		int[] p=new int[m.length];
		while(!pq.isEmpty()) {
			Edge e=pq.poll();
			if(!isCycle(m,e.x,e.y,p)) {
				nn+=1;
				m[e.x][e.y]=e.v;
                m[e.y][e.x]=e.v;
                count+=e.v;
			}
			
		}
		if(nn<num-1) {
			
		}else {
			ans=count;
		}
	}
	public static void solve2(int[] pick, int n, int bnum, Edge[] bridge,int inum) {
		if(n==bnum) {
			int sum=0;
			int[] check=new int[inum+1];
			int[][] m=new int[inum+1][inum+1];
			for(int i=0; i<bnum; i++) {
				if(pick[i]==1) {
					m[bridge[i].x][bridge[i].y]=bridge[i].v;
					m[bridge[i].y][bridge[i].x]=bridge[i].v;
					sum+=bridge[i].v;
				}
			}
			Queue<Integer> que=new LinkedList<Integer>();
			que.add(1);
			check[1]=1;
			int count=1;
			while(!que.isEmpty()) {
				int q=que.poll();
				for(int j=1; j<inum+1; j++) {
					if(m[q][j]>0 && check[j]==0) {
						que.add(j);
						check[j]=1;
						count+=1;
					}
				}
			}
			if(count==inum) {
				ans=Integer.min(ans,sum);
			}
			return;
		}
		pick[n]=1;
		solve2(pick,n+1,bnum,bridge,inum);
		pick[n]=0;
		solve2(pick,n+1,bnum,bridge,inum);
	}
}
