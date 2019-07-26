import java.util.*;
public class Main {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static int results(int arrN[], int cal[], int n) {
		int result = arrN[0];
		for(int i=0; i<n-1; i++) {
			if(cal[i]==-1) result+=arrN[i+1];
			else if(cal[i]==-2) result-=arrN[i+1];
			else if(cal[i]==-3) result*=arrN[i+1];
			else result/=arrN[i+1];
		}
		return result;
	}
	public static void dfs(int arrN[], int arrC[], int result[], boolean check[], int depth, int n) {
		if(depth == n-1) {
			int tmp = results(arrN, result, n);
			if(max<tmp) max = tmp;
			if(min>tmp) min = tmp;
			return;
		}
		for(int i=0; i<n-1; i++) {
			if(!check[i]) {
				check[i] = true;
				result[depth] = arrC[i];
				dfs(arrN, arrC, result, check, depth+1, n);
				check[i] = false;
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int arrN[] = new int[n];
		int arrC[] = new int[4];
		int cal[] = new int[n-1];
		int result[] = new int[n-1];
		boolean check[] = new boolean[n-1];
		for(int i=0; i<n; i++) {
			arrN[i] = scn.nextInt();
		}
		int t = 0;
		int r = -1;
		int tmp = 0;
		for(int i=0; i<4; i++) {
			arrC[i] = scn.nextInt();
				if(arrC[i]>0) {
				for(int j=0; j<arrC[i]; j++) {
					cal[t] = r;
					t++;
				}
				
			}
			r--;
		}
		dfs(arrN, cal, result, check, 0, n);
		System.out.println(max + " " + min);
	}	
}