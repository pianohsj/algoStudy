package 백준;

import java.util.Scanner;

public class B13458_시험감독_SSR {
	static int N;
	static int A[];
	static int B,C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //시험장 수
		
		A = new int[N]; // 각 시험자에 있는 응시자 수

		for(int i=0;i<N;i++)
			A[i]=sc.nextInt();
		
		B=sc.nextInt(); //총 감독관이 감시할 수 있는 응시자수
		C=sc.nextInt(); // 부감독관이 감시할 수 있는 응시자 수
		solve();
	}
	
	public static void solve() {
		int num=0;
		double sum=N;
		for(int i=0;i<N;i++) {
			num=A[i]-B;
			if(num>0) {
				sum+=Math.ceil(num/(double)C);		
			}
		}
		System.out.println((long)sum);
	}
}
