import java.util.*;
public class Main {
	public static class Pair{
		int x, y, time;
		Pair(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int min = Integer.MAX_VALUE;
	public static void spread(int map[][], Queue<Pair> q, int n) {
		int visited[][] = new int[n][n];
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int time = p.time;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || nx>n-1 || ny<0 || ny>n-1 || map[ny][nx] == 1) continue;
				else if(visited[ny][nx]>0) {
					if(visited[ny][nx]>time+1) {
						visited[ny][nx] = time+1;
						q.add(new Pair(nx, ny, time+1));
					} // 해당 영역의 최저시간을 구해야함
					else continue;
				}
				else {
					visited[ny][nx] = time+1; 
					q.add(new Pair(nx, ny, time+1));	
				}
			}
		}
		// 시간체크
		int tmp_max = 0;
		boolean isFail = false;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 2) continue; // 바이러스는 세줄 필요는 없음
				else if(map[i][j] == 0 && visited[i][j] == 0) isFail = true; // 바이러스가 비어있는 공간을 못 채웠을 경우
				else if(visited[i][j]>tmp_max){ // 해당 케이스의 시간 구하기
					tmp_max = visited[i][j];
				}
			}
		}
		if(!isFail)
			if(min>tmp_max) min = tmp_max;
	}
	public static void pick(int map[][], LinkedList<Pair>list, int output[], int depth, int tmp, int cnt, int n, int m) {
		if(depth == m) {
			int copy[][] = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					copy[i][j] = map[i][j];
				}
			}// 배열 복제
			Queue<Pair> q = new LinkedList<>();
			int ableVirus[][] = new int[n][n];
			for(int i=0; i<m; i++) {
				q.add(list.get(output[i]));
			}
			spread(copy, q, n);
			// 바이러스 확산
			// 시간체크
			// 최소값 비교
			return;
		}
		for(int i=tmp; i<cnt; i++) {
			output[depth] = i;
			tmp++;
			pick(map, list, output, depth+1, tmp, cnt, n, m);
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][n];
		LinkedList<Pair> list = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j] == 2) {
					list.add(new Pair(j, i, 0));
				}// 바이러스 위치 저장
			}
		}
		// V개의 바이러스 중 m개 뽑기
		int cnt = list.size();
		int output[] = new int[m];
		pick(map, list, output, 0, 0, cnt, n, m);
		if(min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
	}
}