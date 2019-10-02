package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B17471_게리맨더링_HSJ {
	static int N;
	static int[] people;
	static LinkedList<Integer>[] connect;
	static boolean[] teamA;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		people = new int[N];
		connect = new LinkedList[N];
		//링크드리스트 배열 초기화
		for(int i = 0; i < N; i++) {
			connect[i] = new LinkedList<Integer>();
		}
		//인구수 받기
		for(int i = 0; i < N; i++) {
			people[i] = sc.nextInt();
		}
		//연결된점 받기
		for(int i = 0; i < N; i++) {
			int num = sc.nextInt();
			for(int j = 0; j < num; j++) {
				connect[i].add(sc.nextInt());
			}
		}
		
		//teamA, teamB나누기
		teamA = new boolean[N];
		for(int i = 1; i < N; i++) {
			go(i, 0);
		}
		System.out.println(min);
		
	}
	
	public static void go(int num, int cnt) {
		if(cnt >= num) {
			//유효성검사하기
			if(isOk()) {
				//인구수차이구하기
				min = Math.min(min, getDiff());
			}
			return;
		}else {
			for(int i = 0; i < N; i++) {
				teamA[i] = true;
				go(num,cnt+1);
				teamA[i] = false;
			}
		}
	}
	
	public static int getDiff() {
		int aCnt = 0;
		int bCnt = 0;
		for(int i = 0; i < N; i++) {
			if(teamA[i])aCnt+=people[i];
			else bCnt += people[i];
		}
		return Math.abs(aCnt-bCnt);
	}
	
	public static boolean isOk() {
		//teamA,teamB연결되어있는지확인
		LinkedList<Integer> list = new LinkedList<>();//teamA
		LinkedList<Integer> list2 = new LinkedList<>();//teamB
		for(int i = 0; i < N; i++) {
			if(teamA[i]) {
				for(int j = 0; j < connect[i].size(); j++) {
					list.add(connect[i].get(j));
				}
			}else {
				for(int j = 0; j < connect[i].size(); j++) {
					list2.add(connect[i].get(j));
				}
			} 
		}
		for(int i = 0; i < N; i++) {
			if(teamA[i]) {
				if(list.contains(i)) continue;
				else return false;
			}else {
				if(list2.contains(i)) continue;
				else return false;
			}
		}
		
		return true;
	}

}
