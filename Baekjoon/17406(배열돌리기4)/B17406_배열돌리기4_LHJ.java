import java.util.*;
public class Main {
	static int[][] map;
	static int N,M,K;
	static int[][] F;
	static int[] check;
	static int[] pick;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		F=new int[K][3];
		check=new int[K];
		pick=new int[K];
		for(int i=0; i<K; i++) {
			for(int j=0; j<3; j++) {
				F[i][j]=sc.nextInt();
			}
		}
		solve(0);
		System.out.println(ans);
	}
	public static void solve(int n) {
		if(n==K) {
			int[][] rmap=rotate(F[pick[0]][0]-1,F[pick[0]][1]-1,F[pick[0]][2],map);
			
			for(int i=1; i<K; i++) {
				rmap=rotate(F[pick[i]][0]-1,F[pick[i]][1]-1,F[pick[i]][2],rmap);
			}
	
			for(int i=0; i<N; i++) {
				int sum=0;
				for(int j=0; j<M; j++) {
					sum+=rmap[i][j];
				}
				ans=Math.min(ans, sum);
			}
			
			return;
		}
		for(int i=0; i<K; i++) {
			if(check[i]==0) {
				check[i]=1;
				pick[n]=i;
				solve(n+1);
				check[i]=0;
			}
		}
	}
	public static int[][] rotate(int r,int c,int s,int[][] m){
		int[][] nmap=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i>=r-s && j>=c-s && i<=r+s && j<=c+s) {
					
				}else {
					nmap[i][j]=m[i][j];
				}
			}
		}
		for(int k=0; k<s; k++) {
			for(int i=r-s+k; i<=r+s-k; i++) {
				for(int j=c-s+k; j<=c+s-k; j++) {
					if(i==r-s+k || i==r+s-k || j==c-s+k || j==c+s-k) {
						if(i==r-s+k) {
							if(j==c-s+k) {
								nmap[i][j]=m[i+1][j];
							}else {
								nmap[i][j]=m[i][j-1];
							}
						}else if(i==r+s-k) {
							if(j==c+s-k) {
								nmap[i][j]=m[i-1][j];
							}else {
								nmap[i][j]=m[i][j+1];
							}
						}else if(j==c-s+k) {
							nmap[i][j]=m[i+1][j];
						}else if(j==c+s-k) {
							nmap[i][j]=m[i-1][j];
						}
					}
				}
			}
		}
		nmap[r][c]=m[r][c];
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(nmap[i][j]+" ");
//			}
//			System.out.println("");
//		}
		
		return nmap;
	}

}
