import java.util.*;
public class Main {
	static int  N;
	static int[] A;
	static int B,C;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		A=new int[N];
		
		for(int i=0; i<N; i++) {
			A[i]=sc.nextInt();
		}
		B=sc.nextInt();
		C=sc.nextInt();
		
		for(int i=0; i<N; i++) {
			if(A[i]-B<=0) {
				A[i]=1;
			}else if((A[i]-B)%C==0) {
				A[i]=(A[i]-B)/C+1;
			}else {
				A[i]=(A[i]-B)/C+2;
			}
			ans+=A[i];
		}
		System.out.println(ans);
	}
}
