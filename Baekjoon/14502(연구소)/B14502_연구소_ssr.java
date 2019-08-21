package 백준;

import java.util.Scanner;

public class B14502_연구소_ssr {
	static int N,M;//행*열
	static int map[][], check[][];
	static final int[] dx= {1,-1,0,0};
	static final int[] dy= {0,0,-1,1};
	static int answer=Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		
		map = new int[N][M];
		check = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=sc.nextInt();
		
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					map[i][j]=1; 
					
					wallDfs(i*M+j,1);
					
					map[i][j]=0;
				}
			}
		}
		
		System.out.println(answer);

	}
	
	static void wallDfs(int index, int depth) {
		
		if(depth==3) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					check[i][j]=map[i][j];
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(check[i][j]==2)virus(i, j);
				}
			}
			
			maxSafe();

		}else {
	
			for(int i=index+1;i<N*M;i++) {
				int x = (int)i/M;
				int y = i%M;
				if(map[x][y]==0) {
					map[x][y]=1;
					wallDfs(i, depth+1);
					map[x][y]=0;
				}
				
			}
		}
	}
	
	static void virus(int x, int y) {
		
		//동서남북
		for(int i=0;i<4;i++) {
			int nx = dx[i]+x;
			int ny = dy[i]+y;
			
			if(ny>=0&&ny<M&&nx>=0&&nx<N) {
				if(check[nx][ny]==0) {
					check[nx][ny]=3; //감염
					
					virus(nx,ny);
				}
			}
		}
	}
	
	static void maxSafe() {
		int safe=0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == 0) {
                    safe++;
                }
            }
        }

		if(safe>answer) {
			answer = safe;
		}
	}

}
