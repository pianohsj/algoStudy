import java.util.*;
public class Main {
	static int PL,MI,MU,DI;
	static int[] A;
	static int N;
	static int MIN = 1000000000;
	static int MAX = -1000000000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		A = new int[N];
		for(int i=0; i<N; i++) {
			A[i]=sc.nextInt();
		}
		PL = sc.nextInt();
		MI = sc.nextInt();
		MU = sc.nextInt();
		DI = sc.nextInt();
		
		solve(1,0,0,0,0,A[0]);
		System.out.println(MAX);
		System.out.println(MIN);
	}
	public static void solve(int idx, int pl, int mi, int mu, int di, int ans) {
		if(idx<N) {
			if(mi<MI) {
				solve(idx+1, pl, mi+1, mu, di, ans-A[idx]);
			}if(mu<MU) {
				solve(idx+1, pl, mi, mu+1, di, ans*A[idx]);
			}if(di<DI) {
				solve(idx+1, pl, mi, mu, di+1, ans/A[idx]);
			}if(pl<PL) {
				solve(idx+1, pl+1, mi, mu, di, ans+A[idx]);
			}
		}else {
			//System.out.println(""+pl+" "+mi+" "+mu+" "+di+" "+ans);
			if(MAX<ans) {
				MAX=ans;
			}
			if(MIN>ans) {
				MIN=ans;
			}
		}
	}                       
}
