import java.util.*;
public class Main {
	static int R,C,T;
	//static int[][] map;
	static Pair[] air= {null,null};
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		R=sc.nextInt();
		C=sc.nextInt();
		T=sc.nextInt();
		int[][] map=new int[R][C];
		for(int i=0; i<R;i++) {
			for(int j=0; j<C; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==-1) {
					if(air[0]==null) {
						air[0]=new Pair(i,j);
					}else {
						air[1]=new Pair(i,j);
					}
				}
			}
		}
		for(int t=0; t<T; t++) {
			map=bfs(map);
			upWind(map);
			upDown(map);
		}
		int ans=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				ans+=map[i][j];
			}
		}
		System.out.println(ans+2);
	}
	public static void upWind(int[][] m) {
		Pair p=air[0];
		int i=p.x;
		int j=p.y+1;
		int befo=0;
		int d=1;
		while(i!=p.x || j!=p.y) {
			int t=m[i][j];
			m[i][j]=befo;
			befo=t;
			if(i==p.x && j==C-1) {
				d=0;
			}else if(i==0 && j==C-1) {
				d=3;
			}else if(i==0 && j==0) {
				d=2;
			}
			i=i+dx[d];
			j=j+dy[d];
		}
	}
	public static void upDown(int[][] m) {
		Pair p=air[1];
		int i=p.x;
		int j=p.y+1;
		int befo=0;
		int d=1;
		while(i!=p.x || j!=p.y) {
			int t=m[i][j];
			m[i][j]=befo;
			befo=t;
			if(i==p.x && j==C-1) {
				d=2;
			}else if(i==R-1 && j==C-1) {
				d=3;
			}else if(i==R-1 && j==0) {
				d=0;
			}
			i=i+dx[d];
			j=j+dy[d];
		}
	}
	public static int[][] bfs(int[][] m) {
		int[][] newM=new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(m[i][j]>0) {
					int amount=m[i][j]/5;
					int mid=m[i][j];
					for(int d=0; d<4; d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						if(nx>=0 && ny>=0 && nx<R && ny<C && m[nx][ny]!=-1) {
							newM[nx][ny]+=amount;
							mid-=amount;
						}
					}
					newM[i][j]+=mid;
				}
			}
		}
		newM[air[0].x][air[0].y]=-1;
		newM[air[1].x][air[1].y]=-1;
		return newM;
	}

}
