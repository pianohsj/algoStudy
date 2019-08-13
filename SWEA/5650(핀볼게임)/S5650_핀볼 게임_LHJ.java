import java.util.*;
public class Solution {
	static int N;
	static int[][] map;
	static int[] dx= {0,1,0,-1};
	static int[] dy= {1,0,-1,0};
	static int ans=0;
	static class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static int changeDir(int block, int dir) {
		if(block==1) {
			if(dir==0 || dir==3) {
				return (dir+2)%4;
			}else if(dir==1) {
				return 0;
			}else if(dir==2) {
				return 3;
			}else {
				return -1;
			}
		}else if(block==2) {
			if(dir==0 || dir==1) {
				return (dir+2)%4;
			}else if(dir==2) {
				return 1;
			}else if(dir==3) {
				return 0;
			}else {
				return -1;
			}
		}else if(block==3) {
			if(dir==1 || dir==2) {
				return (dir+2)%4;
			}else if(dir==0) {
				return 1;
			}else if(dir==3) {
				return 2;
			}else {
				return -1;
			}
		}else if(block==4) {
			if(dir==2 || dir==3) {
				return (dir+2)%4;
			}else if(dir==1) {
				return 2;
			}else if(dir==0) {
				return 3;
			}else {
				return -1;
			}
		}else if(block==5) {
			return (dir+2)%4;
		}else {
			return dir;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T= sc.nextInt();
		for(int t=1; t<=T; t++) {
			ans=0;
			N=sc.nextInt();
			map=new int[N][N];
			Pair[][] hole =new Pair[2][11];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=sc.nextInt();
					if(map[i][j]>=6) {
						if(hole[0][map[i][j]]==null) {
							hole[0][map[i][j]]=new Pair(i,j);
						}else {
							hole[1][map[i][j]]=new Pair(i,j);
						}
					}
				}
			}
			Pair[][] wormHole =new Pair[N][N];
			for(int i=6; i<11; i++) {
				if(hole[0][i]!=null && hole[1][i]!=null) {
					wormHole[hole[0][i].x][hole[0][i].y]=new Pair(hole[1][i].x,hole[1][i].y);
					wormHole[hole[1][i].x][hole[1][i].y]=new Pair(hole[0][i].x,hole[0][i].y);
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==0) {
						for(int dd=0; dd<4; dd++) {
							int x=i,y=j;
							int cnt=0;
							int d=dd;
							while(true) {
								x=x+dx[d];
								y=y+dy[d];
								if(x<0||x>N-1||y<0||y>N-1) {
									d=changeDir(5,d);
									cnt++;
								}else {
									if(map[x][y]==-1 || (x==i&&y==j)) {
										ans=Math.max(ans, cnt);
										break;
									}else if(map[x][y]<=5) {
										d=changeDir(map[x][y],d);
										if(map[x][y]!=0) {
											cnt++;
										}
									}else {
										int newX=wormHole[x][y].x;
										int newY=wormHole[x][y].y;
										x=newX;y=newY;
									}
								}
							}
						}
					}
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}

}
