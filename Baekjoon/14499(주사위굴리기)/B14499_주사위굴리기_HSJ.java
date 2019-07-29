package baekjoon;

import java.util.Scanner;

public class B14499_�ֻ���������_HSJ {
	static int N, M; //���� ����, ����
	static int r, c; //������ġ
	static int K; //��ɰ���
	static int[][] map;
	static int[] dice; //����
	static int[] dir; //����
	static int[] dx = { 1, -1, 0, 0 };//����
    static int[] dy = { 0, 0, -1, 1 };//�ϳ�
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][M];
		dir = new int[K];
		dice = new int[7];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < K; i++) {
			dir[i] = sc.nextInt();
		}
		
		for(int i = 0; i < dir.length; i++) {
			simulation(dir[i]);
		}
	}
	
	public static void simulation(int d) {
		//�����̵�
		r = r + dy[d-1];
		c = c + dx[d-1];
		if(r < 0 || r >= N || c < 0 || c >= M) {//���϶� 
			r = r - dy[d-1];
			c = c - dx[d-1];
			return;
		}else {
			changeDice(d);//�ֻ��� map �̵�
			//�ֻ��� �ظ� ���� 0���� �ƴ��� Ȯ���ϱ�
			if(map[r][c] == 0) {
				map[r][c] = dice[6];
			}else {
				dice[6] = map[r][c];
				map[r][c] = 0;
			}
			System.out.println(dice[1]);
		}
	}
	
	public static void changeDice(int d) {
		int[] clone = dice.clone();//dice ����
		
		if(d == 1) {//��
			dice[1] = clone[4];
			dice[3] = clone[1];
			dice[4] = clone[6];
			dice[6] = clone[3];
		}else if(d == 2) {//��
			dice[1] = clone[3];
			dice[3] = clone[6];
			dice[4] = clone[1];
			dice[6] = clone[4];
		}else if(d == 3) {//��
			dice[1] = clone[5];
			dice[2] = clone[1];
			dice[5] = clone[6];
			dice[6] = clone[2];
		}else if(d == 4) {//��
			dice[1] = clone[2];
			dice[2] = clone[6];
			dice[5] = clone[1];
			dice[6] = clone[5];
		}
	}

}
