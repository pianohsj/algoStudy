import java.util.*;

public class Solution {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int N,M,K;
	static public class Micro {
		public int x,y;
		public int num;
		public int d;
		Micro(int x,int y, int num,int d){
			this.x=x;this.y=y;
			this.num=num;this.d=d;
		}
		public void move() {
			this.x=this.x+dx[this.d];
			this.y=this.y+dy[this.d];
		}
		public void changeDir() {
			if(this.d==0 || this.d==2) {
				this.d+=1;
			}else {
				this.d-=1;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++){
			N=sc.nextInt();
			M=sc.nextInt();
			K=sc.nextInt();
			Micro[] micro=new Micro[K];
			for(int i=0; i<K; i++) {
				micro[i]=new Micro(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()-1);
			}
			for(int m=0; m<M; m++) {
				int[][][] count=new int[N][N][2];
				for(int i=0; i<K; i++) {
					if(micro[i].num>0) {
						micro[i].move();
						if(micro[i].x==0 || micro[i].x==N-1 || micro[i].y==0 || micro[i].y==N-1) {
							micro[i].num=micro[i].num/2;
							micro[i].changeDir();
						}else if(micro[i].x>0 && micro[i].x<N-1 && micro[i].y>0 && micro[i].y<N-1) {
							if(count[micro[i].x][micro[i].y][0]==0) {
								count[micro[i].x][micro[i].y][0]=micro[i].num;
								count[micro[i].x][micro[i].y][1]=i;
							}else {
								if(count[micro[i].x][micro[i].y][0]>micro[i].num) {
									micro[count[micro[i].x][micro[i].y][1]].num+=micro[i].num;
									micro[i].num=0;
								}else {
									count[micro[i].x][micro[i].y][0]=micro[i].num;
									micro[i].num+=micro[count[micro[i].x][micro[i].y][1]].num;
									micro[count[micro[i].x][micro[i].y][1]].num=0;
									count[micro[i].x][micro[i].y][1]=i;
								}
							}
							
						}
						
					}
				}
				
			}
			int ans=0;
			for(int i=0; i<K; i++) {
				if(micro[i].num!=0) {
					ans+=micro[i].num;
				}
			}
			System.out.println("#"+t+" "+ans);
		}		
	}
}
