package baekjoon;

import java.util.Scanner;

public class B13458_���谨��_HSJ {
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
		
		int cnt = 0; // ���� ��ġ �Ϸ�� Ŭ���� üũ
		long dNum = 0; //������ �� 
		isFirst = true;
		/*		
		while(cnt < N) {
			cnt = 0;
			for(int i = 0; i < N; i++) {
				if(CLASS[i] <= 0) {
					cnt++;
					continue; //�̹� ��ġ �Ϸ�� Ŭ������ �Ѿ��
				} 
				if(isFirst) {//�Ѱ���
					CLASS[i] -= GENERAL;
					isFirst = false;
				}else {//�ΰ���
					CLASS[i] -= ASSISTANT;
				}
				dNum++;
				if(CLASS[i] <= 0) cnt++;
			}
		}
		*/
		
		for(int i = 0; i < N; i++) {
			if(CLASS[i] <= 0) {//ó������ 0���� Ŭ����
				dNum++;
				continue; 
			} 
			CLASS[i] -= GENERAL;
			dNum++;
			if(CLASS[i] <= 0) { //�Ѱ������� ����� Ŭ����
				continue;
			}else {//�ΰ����� �ʿ��� Ŭ����
				dNum += (long)Math.ceil((double)CLASS[i]/ASSISTANT);
			}
		}
		
		System.out.println(dNum);
		
	}

}
