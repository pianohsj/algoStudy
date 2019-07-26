package baekjoon;

import java.util.Scanner;

public class B14888_�����ڳ����ֱ�_HSJ {
	static int N;
	static int[] A, op;
	static int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
	static boolean isFirst = true;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		op = new int[4];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		for(int i = 0; i < 4; i++) {
			op[i] = sc.nextInt();
		}
		MAX = A[0]; MIN = A[0];
		dfs(0, A[0]);
	    System.out.println(MAX);
	    System.out.println(MIN);
	}
	
	public static void dfs(int cnt, int sum) {
		//����
		if(cnt == N-1) {
			if(isFirst) { //�����Ұ� �� ���ۿ� ���� ��
				MAX = sum;
				MIN = sum;
				isFirst = false;
			}else {
				if(sum > MAX) MAX = sum;
				if(sum < MIN) MIN = sum;
			}
		}else {
			for(int i = 0; i < 4; i++) {
				if(op[i] > 0) {
					op[i]--;
					switch(i) {
					case 0:
						dfs(cnt+1, sum+A[cnt+1]);
						break;
					case 1:
						dfs(cnt+1, sum-A[cnt+1]);
						break;
					case 2:
						dfs(cnt+1, sum*A[cnt+1]);
						break;
					case 3:
						dfs(cnt+1, sum/A[cnt+1]);
						break;
					}
					op[i]++;
				}
			}
		}
	}

}
