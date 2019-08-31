import java.util.*;
public class Main {
	public static class Pair{
		int x, y;
		String puyo;
		Pair(int x, int y, String puyo){
			this.x = x;
			this.y = y;
			this.puyo = puyo;
		}
	}
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static boolean check = false;
	public static void find(String map[][], int sx, int sy, String puyo) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, puyo));
		LinkedList<Pair> list = new LinkedList<>();
		int visited[][] = new int[12][6];
		visited[sy][sx] = 1;
		list.add(new Pair(sx, sy, puyo));
		int cnt = 0;
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			String pu = p.puyo;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>5 || ny>11 || visited[ny][nx] == 1) continue;
				else if(!map[ny][nx].equals(pu) || map[ny][nx].equals(".")) continue; 
				else {
					visited[ny][nx] = 1;
					q.add(new Pair(nx,ny, map[ny][nx]));
					list.add(new Pair(nx,ny, map[ny][nx]));
				}
			}
		}
		if(list.size()>=4) {
			check = true;
			for(int i=0; i<list.size(); i++) {
				Pair p =list.get(i);
				map[p.y][p.x] = ".";
			}
		}
		
	}
	public static void refresh(String map[][]) {
		Queue<String> q = new LinkedList<>();
		for(int i=0; i<6; i++) {
			for(int j=11; j>=0; j--) {
				if(!map[j][i].equals(".")) {
					q.add(map[j][i]);
					map[j][i] = ".";
				}
			}
			int tmp = 11;
			while(!q.isEmpty()) {
				map[tmp][i] = q.remove();
				tmp--;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String map[][] = new String[12][6];
		for(int i=0; i<12; i++) {
			String str = scn.nextLine();
			map[i] = str.split("");
		}
		boolean isFinish = false;
		int ans = 0;
		while(true) {
			if(isFinish) break;
			int count = 0;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(!map[i][j].equals(".")) {
						find(map, j, i, map[i][j]);
						if(check) {
							count++;
							check = false;
						}
					}
				}
			}
			if(count==0) {
				isFinish = true;
			}
			else {
                ans++;
				refresh(map);
			}
		}
		System.out.println(ans);
	}
}