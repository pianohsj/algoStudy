import java.util.*;
public class Solution {
	static class Pair{
		int x;
		int y;
		int range;
		Pair(int x, int y, int range){
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	static int min = Integer.MAX_VALUE;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static void reset(int map[][], int w, int h) {
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<w; i++) {
			for(int j=h-1; j>=0; j--) {
				if(map[j][i]>0) {
					q.add(map[j][i]);	
				}
				map[j][i] = 0;
			}
			int tmp = h-1;
			int index = 0;
			while(!q.isEmpty()) {
				map[tmp][i] = q.remove();
				tmp--;
			}
			
		}

	}
	
	public static void renewH(int map[][], int maxH[], int w, int h) {
		Arrays.fill(maxH, -1);
		int tmp =0 ;
		for(int a=0; a<h; a++) {
			for(int b=0; b<w; b++) {
				if(tmp<w) {
					if(map[a][b]>0 && maxH[b]==-1) {
						maxH[b] = a;
						tmp++;
					}
					else if(a== h-1 && map[a][b] == 0 && maxH[b] == -1) {
						maxH[b] = 0;
					}
				}
			}
		}
	}
	public static void dropBall(int map[][], int output, int maxH[], int w, int h) {
		int sx = output;
		int sy = maxH[output];
		Stack<Pair>s = new Stack<>();

		s.add(new Pair(sx, sy, map[sy][sx]));
		map[sy][sx] = 0;
		while(!s.isEmpty()) {
			Pair p = s.pop();
			int x = p.x;
			int y = p.y;
			int range = p.range;

			if(range>0) {
				for(int i=0; i<4; i++) {
					for(int j=1; j<=range; j++) {
						
						int nx = x+dx[i]*j;
						int ny = y+dy[i]*j;
						if(nx<0 || nx>w-1 || ny<0 || ny>h-1 || range == 0) continue;
						else if(Math.abs(x-nx)>=range || Math.abs(y-ny)>=range) continue;
						else {
							if(map[ny][nx]>1)
								s.add(new Pair(nx, ny, map[ny][nx]));
							map[ny][nx] = 0;
						}	
					}
					
				}	
			}
			
		}
		reset(map, w, h);
		renewH(map, maxH, w, h);
		// 0을 정리하기
	}
	public static void simulation(int map[][], int output[], int maxH[], int w, int h, int n, int depth) {
		if(depth == n) {
			int simuMap[][] = new int[h][w];
			int tmpH[] = new int[w];
			for(int i=0; i<w; i++) {
				tmpH[i] = maxH[i];
			}
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					simuMap[i][j] = map[i][j];
				}
			}
			for(int i=0; i<output.length; i++) {
				dropBall(simuMap, output[i], tmpH, w, h);
			}
			int cnt = 0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(simuMap[i][j]>0) {
						cnt++;
					}
				}
			}
			
			if(cnt<min) min = cnt;
			return;
		}
		
		for(int i=0; i<w; i++) {
			output[depth] = i;
			simulation(map, output, maxH, w, h, n, depth+1);
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int testcase = scn.nextInt();
		for(int i=1; i<=testcase; i++) {
			int n = scn.nextInt(); // 구슬
			int w = scn.nextInt(); // 가로
			int h = scn.nextInt(); // 세로
			int maxH[] = new int[w];
			int map[][] = new int[h][w];
			for(int a=0; a<h; a++) {
				for(int b=0; b<w; b++) {
					map[a][b] = scn.nextInt();
				}
			}
			renewH(map, maxH, w, h); // 입력부
			int output[] = new int[n];
			simulation(map, output, maxH, w, h, n, 0);
			System.out.println("#"+i+" "+min);
			min = Integer.MAX_VALUE;
			
		}
	}
}