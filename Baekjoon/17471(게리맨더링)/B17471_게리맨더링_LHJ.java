import java.util.*;
public class Main {
	static int N;
	static int[] people;
	static int[][] map;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		people=new int[N+1];
		map=new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			people[i]=sc.nextInt();
		}
		for(int i=1; i<N+1; i++) {
			int n=sc.nextInt();
			for(int j=0; j<n; j++) {
				map[i][sc.nextInt()]=1;
			}
		}
		int[] pick=new int[N+1];
		pick[1]=1;
		dfs(1, pick,1);
		pick[1]=1;
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
//		for(int i=1; i<N+1; i++) {
//			for(int j=1; j<N+1; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println("");
//		}
//		System.out.println("");
	}
	public static void dfs(int s,int[] pick,int n) {
		if(n==N) {
			return;
		}
		//System.out.println(Arrays.toString(pick)+" "+n+" "+s);
		if(n>=1 && n!=N) {
			int[][] subMap=new int[N+1][N+1];
			boolean isPossible=true;
			for(int i=1; i<N+1; i++) {
				if(pick[i]==0) {
					int count=0;
					for(int j=1; j<N+1; j++) {
						if(pick[j]==0 && map[i][j]==1) {
							subMap[i][j]=1;
							count+=1;
						}
					}
					if(count==0) {
						isPossible=false;
						//break;
					}
				}
			}
//			for(int i=1; i<N+1; i++) {
//				for(int j=1; j<N+1; j++) {
//					System.out.print(subMap[i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println(Arrays.toString(pick));
//			System.out.println(isPossible+"\n");
			if(isPossible) {
				int a=0;
				int b=0;
				for(int i=1; i<N+1; i++) {
					if(pick[i]==0) {
						a+=people[i];
					}else {
						b+=people[i];
					}
				}
				ans=Math.min(ans, Math.abs(a-b));
				//return;
			}
		}
		for(int i=1; i<N+1; i++) {
			if(map[s][i]==1 && pick[i]==0) {
				pick[i]=1;
				dfs(i,pick,n+1);
				pick[i]=0;
			}
		}
	}

}
