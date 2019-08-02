import java.util.*;
public class Solution {
	static int dx[] = {0,0,1,0,-1};
	static int dy[] = {0,-1,0,1,0};
	public static class Pair{
		int x, y, len;
		Pair(int x, int y, int len){
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	public static void printMap(int map[][][], int bcInfo[][]) {
		for(int i=0; i<bcInfo.length; i++) {
			int limit = bcInfo[i][2];
			int power = bcInfo[i][3];
			int visited[][] = new int[10][10];
			Queue<Pair> q = new LinkedList<>();
			q.add(new Pair(bcInfo[i][0]-1, bcInfo[i][1]-1, 0));
			map[i][bcInfo[i][1]-1][bcInfo[i][0]-1] = i+1;
			visited[bcInfo[i][1]-1][bcInfo[i][0]-1] = 1;
			while(!q.isEmpty()) {
				Pair p = q.remove();
				int x = p.x;
				int y = p.y;
				int now_len = p.len;
				for(int a=1; a<5; a++) {
					int nx = x+dx[a];
					int ny = y+dy[a];
					if(nx<0 || nx>9 || ny>9 || ny<0 || visited[ny][nx] == 1) continue;
					else {
						map[i][ny][nx] = i+1;
						visited[ny][nx] = 1;
						if(now_len+1<limit)
							q.add(new Pair(nx, ny, now_len+1));
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int m = scn.nextInt();
			int a = scn.nextInt();
			int maps[][][] = new int[a][10][10];
			int userA[] = new int[m];
			int userB[] = new int[m];
			int bcInfo[][] = new int[a][4];
			for(int i=0; i<m; i++) userA[i] = scn.nextInt();
			for(int i=0; i<m; i++) userB[i] = scn.nextInt();
			for(int i=0; i<a; i++) {
				for(int j=0; j<4; j++) {
					bcInfo[i][j] = scn.nextInt();
				}
			}
			// ÀÔ·ÂºÎ
			printMap(maps, bcInfo);
			int locaA[] = {0,0};
			int locaB[] = {9,9};
			int sumA[] = new int[m+1];
			int sumB[] = new int[m+1];
			int sum[] = new int[m+1];
			for(int i=0; i<=m; i++) {
				int arrA[] = new int[a];
				int arrB[] = new int[a];
				for(int j=0; j<a; j++) {
					int tmpA = maps[j][locaA[1]][locaA[0]];
					int tmpB = maps[j][locaB[1]][locaB[0]];
					if(tmpA>0) {
						arrA[tmpA-1]++;
					}
					if(tmpB>0) {
						arrB[tmpB-1]++;
					}
				}
				int max = 0;
				int max_same = 0;
				for(int j=0; j<a; j++) {
					int maxA = 0;
					int tmp = 0;
					int tmp2 = 0;
					if(arrA[j]>0) maxA = bcInfo[j][3]; 
					for(int k=0; k<a; k++) {
						int maxB = 0;
						if(arrB[k]>0) maxB = bcInfo[k][3];
						if(j==k && arrA[j]>0 && arrB[k]>0) {
							tmp2 = maxA;
							if(max_same<tmp2) max_same = tmp2;
						}
						else {
							tmp = maxA+maxB;
							if(max<tmp) max = tmp;
						}
					}
				}
				if(max_same>max) max = max_same;
				sum[i] = max;
				if(i<m) {
					locaA[1] = locaA[1]+dy[userA[i]];
					locaA[0] = locaA[0]+dx[userA[i]];
					locaB[1] = locaB[1]+dy[userB[i]];
					locaB[0] = locaB[0]+dx[userB[i]];	
				}
			}
			int result = 0;
			for(int i=0; i<=m; i++) {
				result += sum[i];
			}
			System.out.println("#"+t+" "+result);
		}
	}	
}