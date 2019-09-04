import java.util.*;
public class Main {
	static int ans = 0;
	static class pair implements Comparable<pair>{
		int x, y, dis;
		pair(int x, int y, int dis){
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(pair p) {
			return this.dis>= p.dis ? 1: -1;
		}
	}
	public static void nextTurn(int map[][], int n, int m) {
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<m; i++) {
			if(map[n-1][i]==1) {
				map[n-1][i] = 0;
			}
		}
		for(int i=0; i<m; i++) {
			for(int j=n-2; j>=0; j--) {
				q.add(map[j][i]);
				map[j][i] = 0;
			}
			int tmp = n-1;
			while(!q.isEmpty()) {
				map[tmp][i] = q.remove();
				tmp--;
			}
		}
		
	} // 공격이 끝난 후 적을 한칸 앞으로 이동 시킨다.
    
	public static int[] findEnemy(int map[][], int d, int n, int m, int ax, int ay) {
		int tmp_dis = Integer.MAX_VALUE;
		int result[] = new int[2];
		int tmp_x = -1;
		int tmp_y = -1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1) {
					int dis = Math.abs(ax-j)+Math.abs(ay-i); // 궁수와 적의 거리 측정
					if(dis<=d) { // D 이하일 경우
						if(tmp_dis>dis) { // 이전 적의 거리보다 더 가까울 경우
							tmp_x = j;
							tmp_y = i;
							tmp_dis = dis;
						}
						else if(tmp_dis == dis) { // 이전 적의 거리랑 같을 경우
							if(tmp_x>j) { // 가장 왼쪽에 있는 적을 찾기
								tmp_x = j;
								tmp_y = i;
							}
						}
					}
				}
			}
		}
		result[0] = tmp_x;
		result[1] = tmp_y;
		return result;
		// 적의 좌표를 배열로 Return
	}
	public static void depends(int map[][], int d, int n, int m, int enemy){
		int count = 0;
		while(true) {
			int kill[][] = new int[3][2];
			int cnt = 0;
			for(int i=0; i<m; i++) {
				if(map[n][i]==2) {
					kill[cnt] = findEnemy(map, d, n, m, i, n);
					cnt++;
				}// 적의 위치 찾기
			}
			for(int i=0; i<3; i++) {
				int x = kill[i][0];
				int y = kill[i][1];
				if(x>-1 && y>-1) {
					if(map[y][x] == 1) {
						ans++;
						map[y][x] =0;
					}
				}
			} // 지정된 적을 사살
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j]>0) {
						count++;
					}
				}
			}
			if(count == 0) break;
			else count = 0;
			nextTurn(map, n, m);
		}
	}
	static int max = Integer.MIN_VALUE;
	public static void dfs(int map[][], int output[], int depth, int n, int m, int d, int enemy, int tmp) {
		if(depth == 3) {
			int copy[][] = new int[n+1][m];
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for(int i=0; i<3; i++) {
				copy[n][output[i]] = 2;
			} // 궁수 위치 선정
			depends(copy, d, n, m, enemy); // 디펜스 실행
			for(int i=0; i<3; i++) {
				copy[n][output[i]] = 0;
			}
			if(ans>max) max = ans;
			ans = 0;
			return;
		}
		for(int i=tmp; i<m; i++) {
			output[depth] = i;
			tmp++;
			dfs(map, output, depth+1, n, m, d, enemy, tmp);
		}
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt(); // 세로
		int m = scn.nextInt(); // 가로
		int d = scn.nextInt(); // 공격 Range
		int enemy = 0; // 적의 수
		int map[][] = new int[n+1][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j]==1) enemy++; 
			}
		}
		//System.out.println(enemy);
		int output[] = new int[3];
		dfs(map, output, 0, n, m, d, enemy, 0);
		//종료 조건 : 적의 수가 0이 되면 끝..
		System.out.println(max);
	}
}