import java.util.*;
public class Solution {
	static int T;
	static int N,M;
	static int[][] map;
	
	static int maxK;
	static int[][] dp;
	static int ans;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		T=sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N=sc.nextInt();
			M=sc.nextInt();		
			map=new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=sc.nextInt();
				}
			}
			
			if(N%2==0) {
				maxK=N+1;
			}else {
				maxK=N;
			}
			
			dp=new int[N][N];
			ans=0;
			for(int k=1; k<=maxK; k++) {
				if(M*N*N<k*k+(k-1)*(k-1)) {
					break;
				}
				for(int x=0; x<N; x++) {
					for(int y=0; y<N; y++) {
						if(k==1) {
							dp[x][y]=map[x][y];
						}else {
							for(int i=1; i<k-1; i++) {
								int j=(k-1)-i;
								int[][] n= {{1,1},{1,-1},{-1,1},{-1,-1}};
								for(int u=0; u<4; u++) {
									if(x+i*n[u][0]>=0 && y+j*n[u][1]>=0 &&
											x+i*n[u][0]<N && y+j*n[u][1]<N) {
										dp[x][y]+=map[x+i*n[u][0]][y+j*n[u][1]];
									}
								}
							}
							if(x+(k-1)>=0 && x+(k-1)<N) {
								dp[x][y]+=map[x+k-1][y];
							}
							if(x-(k-1)>=0 && x-(k-1)<N) {
								dp[x][y]+=map[x-(k-1)][y];
							}
							if(y+(k-1)>=0 && y+(k-1)<N) {
								dp[x][y]+=map[x][y+(k-1)];
							}
							if(y-(k-1)>=0 && y-(k-1)<N) {
								dp[x][y]+=map[x][y-(k-1)];
							}
						}
						if(dp[x][y]*M>=k*k+(k-1)*(k-1)) {
							if(ans<dp[x][y]) {
								ans=dp[x][y];
							}
						}
					}
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}
}
