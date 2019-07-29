import java.util.*;
public class Main {
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static int bfs(int map[][], int visited[][], int sy, int sx, int n) {
		visited[sy][sx] = 1;
		int size = 1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy));
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || visited[ny][nx] == 1 || map[ny][nx] == 0) continue;
				else {
					visited[ny][nx] = 1;
					q.add(new Pair(nx, ny));
					size++;
				}
			}
		}
		return size;
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int map[][] = new int[n][n];
		int visited[][] = new int[n][n];
		String arr[] = new String[n];
		ArrayList<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int i=0; i<n; i++) {
			String str = scn.next();
			arr = str.split("");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(arr[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1 && visited[i][j] == 0) {
					int tmp = bfs(map, visited, i, j, n);
					list.add(tmp);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
        }	
	}	
}