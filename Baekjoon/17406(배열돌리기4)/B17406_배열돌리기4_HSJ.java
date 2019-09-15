package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B17406_배열돌리기4_HSJ {
	static int N, M, K;
	static int[][] A, copy;
	static int[] dx = {0,1,0,-1,0};//동남서북동
	static int[] dy = {1,0,-1,0,1};
	static int r, c, s;
	static LinkedList<Integer> list = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	static LinkedList<Pair> permList = new LinkedList<>();
	static boolean[] check;
	static Pair[] go;
	static class Pair{
		int r, c, s;
		Pair(int r, int c, int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		A = new int[N][M];
		copy = new int[N][M];
		check = new boolean[K];
		go = new Pair[K];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				A[i][j] = sc.nextInt();
				copy[i][j] = A[i][j];
			}
		}
		for(int i = 0; i < K; i++) {
			r = sc.nextInt();
			c = sc.nextInt();
			s = sc.nextInt();
			go[i] = new Pair(r,c,s);
			/*
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					System.out.print(A[j][k]+"");
				}
				System.out.println("");
			}
			System.out.println("");
			*/
		}
		perm(permList);
		//최소값 구하기
		//getMin();
		System.out.println(min);
	}
	
	public static void perm(LinkedList<Pair> l) {
		if(l.size() == K) {
			for(int i = 0; i < l.size(); i++) {
				Pair p = l.get(i);
				insert(p.r-1,p.c-1,p.s);
			}
			getMin();
			//map초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					A[i][j] = copy[i][j];
				}
			}
			return;
		}
		for(int i = 0; i < K; i++) {
			if(!check[i]) {
				l.add(go[i]);
				check[i] = true;
				perm(l);
				check[i] = false;
				l.removeLast();
			}
		}
	}
	public static void insert(int r, int c, int s) {
		for(int i = 1; i <= s; i++) {
			int cnt = 0;
			int nx = r-i;
			int ny = c;
			int size = 0;
			for(int j = 0; j < 5; j++) { //큐에 집어넣기
				if(j == 0) size = i+1;//방향꺾기
				else size = i*2;
				
				if(j!=0) {
					nx += dx[j];
					ny += dy[j];
					if(nx==r-i && ny ==c) break;
				}
				while(cnt < size) {
					list.add(A[nx][ny]);
					cnt++;
					if(cnt != size) {
						nx += dx[j];
						ny += dy[j];
						if(nx==r-i && ny ==c) break;
					}
				}
				cnt = 0;
			}
			//돌리기
			turn(i,r,c);
		}
	}
	
	public static void turn(int len, int x, int y) {
		//시계방향 
		list.addFirst(list.removeLast());
		int nx = x-len;
		int ny = y;
		int cnt = 0;
		int size = 0;
		for(int i = 0; i < 5; i++) {
			if(i == 0) size = len+1;//방향꺾기
			else size = len*2;
			if(i!=0) {
				nx += dx[i];
				ny += dy[i];
				if(nx==x-len && ny ==y) break;
			}
			while(cnt <size) {
				A[nx][ny] = list.removeFirst();
				cnt++;
				if(cnt != size) {
					nx += dx[i];
					ny += dy[i];
					if(nx==x-len && ny ==y) break;
				}
			}
			cnt = 0;
		}
	}
	
	public static void getMin() {
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum+=A[i][j];
			}
			min = Math.min(min, sum);
			sum = 0;
		}
	}

}
