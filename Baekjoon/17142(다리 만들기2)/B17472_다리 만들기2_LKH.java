import java.util.*;
public class Main {
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static class Grape implements Comparable<Grape>{
		int first, end, size;
		Grape(int first, int end, int size){
			this.first = first;
			this.end = end;
			this.size = size;
		}
		@Override
		public int compareTo(Grape g) {
			return this.size >= g.size ? 1 :-1;
		}
		
	}
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void numbering(int map[][], int newMap[][], int num, int sx, int sy, int n, int m) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy));
		int visited[][] = new int[n][m];
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>m-1 || ny>n-1 || visited[ny][nx] == 1 || map[ny][nx]==0) continue;
				else {
					newMap[ny][nx] = num;
					visited[ny][nx] = 1;
					q.add(new Pair(nx, ny));
				}
			}
		}
	}
	public static void length(int map[][], int price[][], int sx, int sy, int n, int m) {
		int len = 0;
		int i = 0;
		int nx = sx;
		int ny = sy;
		int num = map[sy][sx];
		while(true) {
			if(i==4) break;
			nx = nx+dx[i];
			ny = ny+dy[i];
			if(nx<0||ny<0 ||nx>m-1 || ny>n-1 || map[ny][nx] == num) {
				nx = sx;
				ny = sy;
				i++;
				len = 0;
				continue;
			}
			else if(map[ny][nx]>0 && map[ny][nx]!=num) {
				if(price[num][map[ny][nx]] == 0 && len>1) {
					price[num][map[ny][nx]] = len;
				}
				else if(price[num][map[ny][nx]]>len && len>1) {
					 price[num][map[ny][nx]] = len;
				}
				nx = sx;
				ny = sy;
				i++;
				len = 0;
				continue;
			}
			len++;
		}
	}
	static int min = Integer.MAX_VALUE;

	public static void searching(PriorityQueue<Grape> pq, int s[], int num) {
			int edgeCount = 0;
			int i =0;
			int v1, v2;
			int s1, s2;
			int result[] = new int[num-1];
			while(!pq.isEmpty()) {
				Grape g = pq.remove();
				v1 = g.first;
				v2 = g.end;
				s1 = s[v1];
				s2 = s[v2];
				if(s1 != s2) {
					for(int j=1; j<s.length; j++) {
						if(s[j]==s2) {
							s[j] = s1;
						}
					}
					result[edgeCount] = g.size;
					edgeCount++;
				}
				i++;
			}
			
			int sum = 0;
			boolean isFail = false;
			for(int j=0; j<result.length; j++) {
				if(result[j]==0) {
					isFail = true;
					break;
				}
				sum += result[j];
			}
			if(!isFail)
				min = sum;
			
			
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][m];
		int newMap[][] = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		int num = 1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1 && newMap[i][j] == 0) {
					newMap[i][j] = num;
					numbering(map, newMap, num, j, i, n, m);
					num++;
				}
			}
		}
		int price[][] = new int[num][num];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(newMap[i][j]>0) {
					length(newMap, price, j, i, n, m);
				}
			}
		}
		PriorityQueue<Grape> pq = new PriorityQueue<>();
		for(int i=1; i<num; i++) {
			for(int j=1; j<num; j++) {
				if(price[i][j]>0) {
					pq.add(new Grape(j, i, price[i][j]));
				}
			}
		}
		int s[] = new int[num];
		for(int i =1; i<num; i++) {
			s[i] = i;
		}
		searching(pq, s, num-1);
					
		if(min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
	}
	
}
