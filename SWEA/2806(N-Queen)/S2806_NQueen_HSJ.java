package samsung;

import java.util.Scanner;

public class S2806_NQueen_HSJ {
	static int T, N, cnt, aNum;
	static int[] nQeen;
	static boolean isSafe = true;
	static boolean isFirst = true;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		aNum = 1;
		while(T!=0) {
			cnt = 0;
			N = sc.nextInt();
			nQeen = new int[N];
			for(int i = 0; i < N; i++) {
				nQeen[0] = i;
	            // 1행 i열에 퀸을 놓았을 경우 가능한 횟수를 dfs로 구한다.
	            dfs(0);
	        }
			System.out.println("#"+aNum+" "+cnt);
			aNum+=1;
			T--;
		}
	}
	
	public static void dfs(int row) {
		if(row >= N-1) {
			cnt++;
		}else {
			a: for(int i = 0; i < N; i++) {
				nQeen[row+1] = i;
				for(int j = 0; j < row+1; j++) {
					//같은 열이거나 대각선
					if(nQeen[j] == i || Math.abs(i-nQeen[j])==Math.abs((row+1)-j)) {
						isSafe = false;
						continue a;
					}else {
						isSafe = true;
					}
				}
				if(isSafe) {
					dfs(row+1);
				}
			}
		}
		
	}
	
}
