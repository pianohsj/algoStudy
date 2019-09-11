import java.util.*;

public class Main {
	public static int[][] map;
	public static int[] paper= {0,0,0,0,0};
	public static int ans=Integer.MAX_VALUE;
	public static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		map=new int[10][10];
		int[][] m=new int[10][10];
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				map[i][j]=sc.nextInt();
				m[i][j]=map[i][j];
			}
		}
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	
	public static void solve(Pair p,int[] paper,int n){
		if(p.x==10) {

			ans=Math.min(ans, n);
			return;
		}
		
		Pair nextp;
		if(p.y+1==10) {
			nextp=new Pair(p.x+1,0);
		}else {
			nextp=new Pair(p.x,p.y+1);
		}
		if(map[p.x][p.y]==1) {
			for(int k=5; k>=1; k--) {
				if(paper[k-1]<5) {
					
					boolean ispossible=true;
					for(int i=0; i<k; i++) {
						for(int j=0; j<k; j++) {
							if(p.x+i>=10 || p.y+j>=10 || map[p.x+i][p.y+j]==0) {
								ispossible=false;
							}
						}
					}
					if(ispossible) {					
						for(int i=0; i<k; i++) {
							for(int j=0; j<k; j++) {
								map[p.x+i][p.y+j]=0;
								
							}
						}
						paper[k-1]+=1;
						solve(nextp,paper,n+1);
						paper[k-1]-=1;
						for(int i=0; i<k; i++) {
							for(int j=0; j<k; j++) {
								map[p.x+i][p.y+j]=1;
								
							}
						}
					}
				}
			}
		}else {
			solve(nextp,paper,n);
		}
	}
}
