import java.util.*;
public class Main {
	static int dx[] = {0,-1,1,0};
	static int dy[] = {-1,0,0,1};
	public static class Pair{
		int x, y, len;
		Pair(int x, int y, int len){
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	public static Pair move(int map[][], int sx, int sy, int size, int n) {
		Pair result;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, 0));
		boolean isFinish = false;
		int len = 0;
		int rx = sx;
		int ry = sy;
		int visited[][] = new int[n][n];
		visited[sy][sx] = 1;
		LinkedList<Pair> sub = new LinkedList<>();
		int min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			//if(isFinish)break;
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			len = p.len;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || visited[ny][nx] == 1) continue;
				else if(size<map[ny][nx]) continue;
				else {
					if(size>map[ny][nx] && map[ny][nx]>0 && len+1<=min) {
						min = len+1;
						sub.add(new Pair(nx, ny, len+1));
					} // 가장 가까운 물고기를 발견했을 경우
					else {
						q.add(new Pair(nx, ny, len+1));
						visited[ny][nx] = 1;
					}
				}
			}
		}
		int tmpY = Integer.MAX_VALUE;
		int tmpX = Integer.MAX_VALUE;
		for(int i=0; i<sub.size(); i++) {
			int tmp = sub.get(i).y;
			if(tmp<tmpY) tmpY = tmp;
		}
		for(int i=0; i<sub.size(); i++) {
			int tmp = sub.get(i).x;
			int tmp2 = sub.get(i).y;
			if(tmp<tmpX && tmpY == tmp2) tmpX = tmp;
		}
		if(tmpX != Integer.MAX_VALUE || tmpY != Integer.MAX_VALUE) {
			rx = tmpX;
			ry = tmpY;
		}
		result = new Pair(rx, ry, min);
		return result;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int sx = 0;
		int sy = 0;
		int map[][] = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j] == 9) {
					sx = j;
					sy = i;
				}
			}
		}
		int nx = sx;
		int ny = sy;
		int nLen = 0;
		int px = sx;
		int py = sy;
		int size = 2;
		int eat = 0;
		while(true) {
			if(eat == size) {
				size++;
				eat = 0;
			}
			map[py][px] = 0;
			Pair tmp = move(map, nx, ny, size, n);
			nx = tmp.x;
			ny = tmp.y;
			nLen +=tmp.len;
			//System.out.println(tmp.len);
			if(nx == px && ny == py) {
				nLen -=tmp.len;
				break;
			}
			else {
				px = nx;
				py = ny;
				eat++;
			}
		}
		System.out.println(nLen);
		
	   
	}
}
