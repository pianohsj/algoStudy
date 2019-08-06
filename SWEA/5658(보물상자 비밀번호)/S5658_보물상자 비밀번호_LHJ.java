import java.util.*;
public class Solution {
	static int N,K;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			N=sc.nextInt();
			K=sc.nextInt();
			String code=sc.next();
			
			arr=new int[N];
			code=code.substring(N-(N/4-1))+code;
			for(int i=0; i<N; i++) {
				arr[i]=Integer.parseInt(code.substring(i, i+N/4),16);
			}
			Arrays.sort(arr);
			
			int j=0;
			for(int i=N-1; i>=0; i--) {
				if(i==0||arr[i]!=arr[i-1]) {
					j+=1;
				}
				if(j==K) {
					System.out.println("#"+t+" "+arr[i]);
					break;
				}
			}
		}
	}
}
