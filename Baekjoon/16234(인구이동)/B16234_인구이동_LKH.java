import java.util.*;
public class Main {
	static class Pair{
		int x;
		int y;
		int prePeople;
		Pair(int x, int y, int prePeople){
			this.x = x;
			this.y = y;
			this.prePeople = prePeople;
			
		}
	}
	static boolean flag = false;
	static int cnt = 0;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	public static void movePeople(int visited[][], ArrayList<Pair> list, int sum, int cnt, int n) {
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			visited[y][x] = sum/cnt;
			
		}
	}
	public static void bfs(int map[][], int visited[][], int sx, int sy, int lowLimit, int highLimit, int n) {
		int count = 0;
		Queue<Pair> q = new LinkedList<>();
		ArrayList<Pair> list = new ArrayList<>();
		q.add(new Pair(sx, sy, map[sy][sx]));
		list.add(new Pair(sx, sy, map[sy][sx]));
		visited[sy][sx] = 1;
		int sum = map[sy][sx];
 		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int people = p.prePeople;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || nx>n-1 || ny<0 || ny>n-1 || visited[ny][nx] == 1) continue;
				int tmp = Math.abs(map[ny][nx] - people);
				if(tmp>=lowLimit && tmp<=highLimit){
					visited[ny][nx] = 1;
					sum += map[ny][nx];
					list.add(new Pair(nx, ny, map[ny][nx]));
					q.add(new Pair(nx, ny, map[ny][nx]));
					count++;
				}
			}
		}
 		
 		if(list.size()>1) {
 			movePeople(map, list, sum, count+1, n);
 			flag = true;
 		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int l = scn.nextInt();
		int r = scn.nextInt();
		int map[][] = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		
		while(true) {
			flag = false;
			int visited[][] = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(visited[i][j] == 0) {
						bfs(map, visited, j, i, l, r, n);
					}
				}
			}
			
			if(flag) cnt++;
			else break;
		}	
		System.out.println(cnt);
	}
}