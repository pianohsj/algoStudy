import java.util.*;
public class Solution {
	static int T,N;
	static int count=0;
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			N=sc.nextInt();
			count=0;
			
			int[] queen=new int[N];
			for(int i=0; i<N; i++) {
				queen[0]=i;
				bfs(0,queen);
			}
			System.out.println("#"+t+" "+count);
		}
	}
	public static void bfs(int x, int[] queen) {
		if(x==N-1) {
			count+=1;
			return;
		}
		for(int i=0; i<N; i++) {
			if(check(queen,x,i)) {
				queen[x+1]=i;
				bfs(x+1,queen);
			}
		}
	}
	public static boolean check(int[] queen, int x, int y) {
		for(int i=0; i<=x; i++) {
			if(queen[i]==y) {
				return false;
			}else if(Math.abs(queen[i]-y)==Math.abs(i-(x+1))) {
				return false;
			}
		}
		return true;
		
	}

}
