package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B3190_뱀_HSJ {
	static int N, K,L;
	static int[][] map;
	static char[] dir;
	static int[] dirX = {1,0,-1,0};
	static int[] dirY = {0,1,0,-1};
	static LinkedList<Pair> list = new LinkedList<>();
	static LinkedList<Pair> snake = new LinkedList<>();
	static int cnt;
	
	static class Pair{
		int x, y;
		char c;
		Pair(int x, char c){
			this.x = x;
			this.c = c;
		}
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][N];
		for(int i = 0; i < K; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		
		L = sc.nextInt();
		
		for(int i = 0; i < L; i++) {
			list.add(new Pair(sc.nextInt(), sc.next().charAt(0)));
		}
		
		snake.add(new Pair(0,0));
		
		cnt = 0;
		simulation(snake, 0);
		
		System.out.println(cnt);
	}
	
	public static void simulation(LinkedList<Pair> s, int d) {
		++cnt;
		//방향전환 체크
		if(list.peek().x == cnt) {
			if(list.peek().c == 'D') {
				d = (d+1)%4;
			}else {
				d = (d+3)%4;
			}
			list.removeFirst();
		}
		int curR = s.getFirst().x+dirX[d];
		int curC = s.getFirst().y+dirY[d];
		
		//벽, 자기자신
		if(curR < 0 || curR >= N || curC < 0 || curC >= N || map[curR][curC] == 2) {
			return;
		}
		//사과 없을 때
		if(map[curR][curC] == 0) {
			map[curR][curC] = 2;
			s.addFirst(new Pair(curR,curC));
			map[s.peekLast().x][s.peekLast().y] = 0; //돌려놓기
			s.removeLast();
		}else if(map[curR][curC] == 1) {
			s.addFirst(new Pair(curR,curC));
		}
		
		simulation(s,d);
	}

}
