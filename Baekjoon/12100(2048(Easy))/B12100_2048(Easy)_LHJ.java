import java.util.*;
public class Main {
	static int N;
	static int ans=Integer.MIN_VALUE;
	//static int[][] map;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		int[][] map=new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		solve(0,map);
		System.out.println(ans);
	}
	public static void solve(int n, int[][] m) {
		if(n==5) {
			return;
		}
		
		for(int d=0; d<4; d++) {
			int[][] map=new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N;j++) {
					map[i][j]=m[i][j];
				}
			}
			move(map,d);
			solve(n+1,map);
		}
	}
	public static void move(int[][] m,int d) {
		LinkedList<Integer> list=new LinkedList<Integer>();
		
		if(d==0) {//위
			for(int i=0; i<N; i++) {
				for(int j=0; j<N;j++) {
					if(m[j][i]!=0) {
						list.add(m[j][i]);
					}
					m[j][i]=0;
				}
				int k=0;
				while(!list.isEmpty()) {
					m[k][i]=list.pollFirst();
					if(!list.isEmpty() && m[k][i]==list.getFirst()) {
						list.pollFirst();
						m[k][i]=m[k][i]*2;
					}
					ans=Math.max(ans, m[k][i]);
					k+=1;
				}
			}
		}else if(d==1) {//오
			for(int i=N-1; i>=0; i--) {
				for(int j=N-1; j>=0;j--) {
					if(m[i][j]!=0) {
						list.add(m[i][j]);
					}
					m[i][j]=0;
				}
				int k=N-1;
				while(!list.isEmpty()) {
					m[i][k]=list.pollFirst();
					if(!list.isEmpty() && m[i][k]==list.getFirst()) {
						list.pollFirst();
						m[i][k]=m[i][k]*2;
					}
					ans=Math.max(ans, m[i][k]);
					k-=1;
				}
			}
		}else if(d==2) {//아래
			for(int i=N-1; i>=0; i--) {
				for(int j=N-1; j>=0;j--) {
					if(m[j][i]!=0) {
						list.add(m[j][i]);
					}
					m[j][i]=0;
				}
				int k=N-1;
				while(!list.isEmpty()) {
					m[k][i]=list.pollFirst();
					if(!list.isEmpty() && m[k][i]==list.getFirst()) {
						list.pollFirst();
						m[k][i]=m[k][i]*2;
					}
					ans=Math.max(ans, m[k][i]);
					k-=1;
				}
			}
		}else if(d==3) {//왼
			for(int i=0; i<N; i++) {
				for(int j=0; j<N;j++) {
					if(m[i][j]!=0) {
						list.add(m[i][j]);
					}
					m[i][j]=0;
				}
				int k=0;
				while(!list.isEmpty()) {
					m[i][k]=list.pollFirst();
					if(!list.isEmpty() && m[i][k]==list.getFirst()) {
						list.pollFirst();
						m[i][k]=m[i][k]*2;
					}
					ans=Math.max(ans, m[i][k]);
					k+=1;
				}
			}
		}
	}

}
