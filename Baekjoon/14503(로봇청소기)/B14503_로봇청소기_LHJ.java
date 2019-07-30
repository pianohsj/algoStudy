import java.util.*;
public class Main {
	static int N,M;
	static int r,c,d;
	static int[][] map;
	static int[][] D = {{-1,0},{0,1},{1,0},{0,-1}};
	static int ans=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		N=sc.nextInt();
		M=sc.nextInt();
		
		r=sc.nextInt()+1;
		c=sc.nextInt()+1;
		d=sc.nextInt();//0 1 2 3 �ϵ�����
		
		map=new int[N+2][M+2];
		for(int i=0; i<N+2; i++) {
			for (int j=0; j<M+2; j++) {
				if(i==0 || i==N+1 || j==0 || j==M+1) {
					map[i][j]=1;
				}else {
					map[i][j]=sc.nextInt();
				}
			}
		}
		solve(r,c,d);
		System.out.println(ans);
		
	}
	public static int rotate(int d) {
		if(d==0) {
			d=3;
		}else {
			d-=1;
		}
		return d;
	}
	public static void solve(int x, int y,int d) {
		if(map[x][y]==0) {
			map[x][y]=2;
			ans+=1;
		}
		
		int nextD=rotate(d);
		
		if(map[x+1][y]!=0 && map[x-1][y]!=0 && map[x][y-1]!=0 && map[x][y+1]!=0) {
			int backD=rotate(nextD);
			if(map[x+D[backD][0]][y+D[backD][1]]==2) {
				solve(x+D[backD][0],y+D[backD][1],d);
			}
		}
		else if(map[x+D[nextD][0]][y+D[nextD][1]]==0) {
			solve(x+D[nextD][0],y+D[nextD][1],nextD);
		}else {
			solve(x,y,nextD);
		}
	}
}
