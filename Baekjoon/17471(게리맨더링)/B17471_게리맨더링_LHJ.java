import java.util.*;
public class Main {
	static int N;
	static int[] people;
	static int[][] map;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		people=new int[N+1];
		map=new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			people[i]=sc.nextInt();
		}
		for(int i=1; i<N+1; i++) {
			int temp=sc.nextInt();
			for(int j=0; j<temp; j++) {
				map[i][sc.nextInt()]=1;
			}
		}
		int[] pick=new int[N+1];
		pick[1]=1;
		solve(pick,2,1);
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}	
	public static void solve(int[] pick,int n, int count) {
		if(n==N+1) {
			if(count<N) {
				int[] check=new int[N+1];
				for(int k=0; k<2; k++) {
					int i;
					for(i=1; i<N+1; i++) {
						if(pick[i]==k) {
							break;
						}
					}
					Queue<Integer> que=new LinkedList<Integer>();
					que.add(i);
					check[i]=1;
					while(!que.isEmpty()) {
						int q=que.poll();
						for(int j=1; j<N+1; j++) {
							if(pick[j]==k && map[q][j]==1 && check[j]==0) {
								check[j]=1;
								que.add(j);
							}
						}
					}
				}
				int num=0;
				int a=0;
				int b=0;
				for(int i=1; i<N+1; i++) {
					num+=check[i];
					if(pick[i]==1) {
						a+=people[i];
					}else {
						b+=people[i];
					}
				}
				if(num==N) {
					ans=Math.min(ans, Math.abs(a-b));
				}
			}
			return;
		}
		pick[n]=1;
		solve(pick,n+1,count+1);
		pick[n]=0;
		solve(pick,n+1,count);
	}
}
