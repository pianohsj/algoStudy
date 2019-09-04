package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B17135_캐슬디펜스_HSJ {
	static int N, M, D;
	static int[][] map;
	static int[][] copymap;
	static boolean[][] visited;
	static boolean[][] isEnemy;
	static int[] dx = {0,-1,0};
	static int[] dy = {-1,0,1};
	static LinkedList<Pair> enemy = new LinkedList<>();
	static LinkedList<Pair> kill = new LinkedList<>();
	static int max = 0;
	static int killCnt = 0;
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		map = new int[N+1][M];
		copymap = new int[N+1][M];
		visited = new boolean[N][M];
		isEnemy = new boolean[N][M];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				copymap[i][j] = map[i][j];
			}
		}
		pick(0,0);
		System.out.println(max);
	}
	public static void pick(int idx, int cnt) {
		if(cnt==3) {
			go();
			max = Math.max(max, killCnt);
			killCnt = 0;
			//map되돌려놓기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = copymap[i][j];
				}
			}
			return;
		}
		if(idx>=M) return;
		
		map[N][idx] = 2;
		pick(idx+1, cnt+1);
		map[N][idx] = 0;
		pick(idx+1, cnt);
	}
	
	public static void go() {
		int count = 0;
		while(count !=N) {
			visited = new boolean[N][M];
			isEnemy = new boolean[N][M];
			enemy = new LinkedList<Pair>();
			for(int i = 0; i < M; i++) {
				kill.clear();
				if(map[N][i] == 2) {
					kill.add(new Pair(N,i));
					find();
				}
				visited = new boolean[N][M];
			}
			//적 없애기
			killCnt += enemy.size();
			for(int i = 0; i < enemy.size(); i++) {
				Pair p = enemy.get(i);
				map[p.x][p.y] = 0;
			}
			//적이 더이상 없는지 확인
			boolean finish = true;
			a: for(int i = 0; i < N; i++) {
				for(int j = 0; j< M; j++) {
					if(map[i][j]==1) {
						finish = false;
						break a;
					}
				}
			}
			if(finish) return;
			//적이 아직 남아있으면map갱신
			count++;
			if(count != N) {
				newMap();
			}

		}
	}
	
	public static void find() {
		int nx= 0;
		int ny= 0;
		int cnt = 0;
		while(!kill.isEmpty() && cnt<D) {
			Pair p = kill.remove();
			for(int i = 0; i < 3; i++) {
				nx = p.x+dx[i];
				ny = p.y+dy[i];
				if(nx < 0 || ny < 0 || nx>=N|| ny >= M) continue;
				if(isEnemy[nx][ny]) return;
				if(!visited[nx][ny]) {
					if(map[nx][ny] == 1) {
						enemy.add(new Pair(nx,ny));
						isEnemy[nx][ny] = true;
						return;
					}else {
						visited[nx][ny] = true;
						kill.add(new Pair(nx,ny));
					}
				}
			}
			cnt++;
		}
	}
	
	public static void newMap() {
		int[][] copy = new int[N+1][M];
		copy = map.clone();
		for(int i = N-1; i >= 1 ; i--) {
			for(int j = 0; j < M; j++) {
				map[i][j] = copy[i-1][j];
			}
		}
		for(int i = 0; i < M; i++) {
			map[0][i] = 0;
		}
	}

}
