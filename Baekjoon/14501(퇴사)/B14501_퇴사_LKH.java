import java.util.*;
public class Main {
	static int max = Integer.MIN_VALUE;
	public static int max(int a, int b) {
		int result = 0;
		if(a>b) result =a;
		else result =b;
		return result;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int arr[][] = new int[n][2];
		for(int i=0; i<n; i++) {
			arr[i][0] = scn.nextInt();
			arr[i][1] = scn.nextInt();
		}
	
		int dp[] = new int[n];
		int day = arr[0][0];
		boolean check = false;
		for(int i=0; i<n; i++) {
			if(i+arr[i][0]>n) continue;
			for(int j=i; j>=0; j--) {
				if(arr[j][0]+j<=i) {
					dp[i] = max(dp[i], dp[j]+arr[i][1]);
					check = true;
				}
			}
			if(!check) dp[i] = arr[i][1];
		}
		for(int i=0; i<n; i++) {
			if(max<dp[i])max =dp[i];
		}
		System.out.println(max);
	}
}