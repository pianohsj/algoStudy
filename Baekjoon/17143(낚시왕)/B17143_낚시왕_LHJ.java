import java.util.*;
public class Main {
	static int R,C,M;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,1,-1};
	public static class Shark{
		int r,c,s,d,z;
		Shark(int r, int c, int s, int d, int z){
			this.r=r;this.c=c;
			this.s=s;this.d=d;
			this.z=z;
		}
		void changeDir(int D){
			if(d==0) {
				this.d=1;
			}else if(d==1) {
				this.d=0;
			}else if(d==2) {
				this.d=3;
			}else if(d==3) {
				this.d=2;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		R=sc.nextInt();
		C=sc.nextInt();
		M=sc.nextInt();
		Shark[] shark=new Shark[M];
		int[][] map=new int[R][C];
		for(int i=0; i<M; i++) {
			shark[i]=new Shark(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt(),sc.nextInt()-1,sc.nextInt());
			map[shark[i].r][shark[i].c]=i+1;
		}
		int ans=0;
		for(int i=0; i<C; i++) {
			for(int j=0; j<R; j++) {
				if(map[j][i]>0 && shark[map[j][i]-1]!=null) {
					ans+=shark[map[j][i]-1].z;
					shark[map[j][i]-1]=null;
					break;
				}
			}
			int[][] newMap=new int[R][C];
			for(int k=0; k<M; k++) {
				if(shark[k]!=null) {
					int nr=shark[k].r+shark[k].s*dx[shark[k].d];
					int nc=shark[k].c+shark[k].s*dy[shark[k].d];
					
					while(nr<0 || nr>R-1 || nc<0 || nc>C-1) {
						shark[k].changeDir(shark[k].d);
						int over=0;
						if(nr<0) {
							over=Math.abs(nr);
							nr=0;
							nr+=over;
						}
						else if(nr>R-1) {
							over=Math.abs(nr-R+1);
							nr=R-1;
							nr-=over;
						}
						else if(nc<0) {
							over=Math.abs(nc);
							nc=0;
							nc+=over;
						}
						else if(nc>C-1) {
							over=Math.abs(nc-C+1);
							nc=C-1;
							nc-=over;
						}
					}
					
					
					shark[k].r=nr;
					shark[k].c=nc;
					
//					for(int j=0; j<shark[k].s; j++) {
//						int nr=shark[k].r+dx[shark[k].d];
//						int nc=shark[k].c+dy[shark[k].d];
//						if(nr<0 || nr>R-1 || nc<0 || nc>C-1) {
//							shark[k].changeDir(shark[k].d);
//							nr=shark[k].r+dx[shark[k].d];
//							nc=shark[k].c+dy[shark[k].d];
//						}
//						shark[k].r=nr;
//						shark[k].c=nc;
//					}
				
					if(newMap[shark[k].r][shark[k].c]==0) {
						newMap[shark[k].r][shark[k].c]=k+1;
					}else {
						if(shark[newMap[shark[k].r][shark[k].c]-1].z<shark[k].z) {
							shark[newMap[shark[k].r][shark[k].c]-1]=null;
							newMap[shark[k].r][shark[k].c]=k+1;
						}else {
							shark[k]=null;
						}
					}
				}
			}
			for(int x=0; x<R; x++) {
				for(int y=0; y<C; y++) {
					map[x][y]=newMap[x][y];
				}
			}
			
			
		}
		System.out.println(ans);

	}

}
