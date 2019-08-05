package baekjoon;

import java.util.Scanner;

public class B13458_시험감독_HSJ {
	static int N;
	static int[] CLASS;
	static int GENERAL;
	static int ASSISTANT;
	static boolean isFirst;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		CLASS = new int[N];
		for(int i = 0 ; i < N; i++) {
			CLASS[i] = sc.nextInt();
		}
		GENERAL = sc.nextInt();
		ASSISTANT = sc.nextInt();
		
		int cnt = 0; // 감독 배치 완료된 클래스 체크
		long dNum = 0; //감독관 수 
		isFirst = true;
		/*		
		while(cnt < N) {
			cnt = 0;
			for(int i = 0; i < N; i++) {
				if(CLASS[i] <= 0) {
					cnt++;
					continue; //이미 배치 완료된 클래스는 넘어가기
				} 
				if(isFirst) {//총감독
					CLASS[i] -= GENERAL;
					isFirst = false;
				}else {//부감독
					CLASS[i] -= ASSISTANT;
				}
				dNum++;
				if(CLASS[i] <= 0) cnt++;
			}
		}
		*/
		
		for(int i = 0; i < N; i++) {
			if(CLASS[i] <= 0) {//처음부터 0명인 클래스
				dNum++;
				continue; 
			} 
			CLASS[i] -= GENERAL;
			dNum++;
			if(CLASS[i] <= 0) { //총감독으로 충분한 클래스
				continue;
			}else {//부감독이 필요한 클래스
				dNum += (long)Math.ceil((double)CLASS[i]/ASSISTANT);
			}
		}
		
		System.out.println(dNum);
		
	}

}
