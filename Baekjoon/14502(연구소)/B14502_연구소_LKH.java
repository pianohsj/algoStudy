import java.util.*;
public class Main{
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static LinkedList<Pair> virus = new LinkedList<>();
	static int max = 0;
	static public class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void countingSafe(int arr[][], int visited[][], int newWall[][], int n, int m) {
		int count = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if((arr[i][j] == 0 || visited[i][j] == 1) && newWall[i][j] == 0) {
					count++;
				}
			}
		}
		if(max<count) {
			max = count;
		}
	} // 안전 지역 영역 개수 카운팅 함수
	public static void spreadVirus(int arr[][], int a, int b, int n, int m) {
		int visited[][] = new int[n][m];
		Stack<Pair> s = new Stack<>();
		s.push(new Pair(a,b));
		while(!s.isEmpty()) {
			Pair p = s.pop();
			int x = p.x;
			int y = p.y;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>m-1 || ny<0 || ny>n-1 || visited[ny][nx] == 1) continue;
				else if(arr[ny][nx] == 0) {
					visited[ny][nx] = 1;
					arr[ny][nx] = 3;
					s.push(new Pair(nx, ny));
				}
			}
		}
	} // 바이러스 퍼트리는 함수
	public static void release(int arr[][], int newWall[][],int check[][], int a, int b, int n ,int m) {
		int visited[][] = new int[n][m]; // 복구 되는 영역
		boolean isFail = false;
		Stack<Pair> s = new Stack<>();
		s.push(new Pair(a, b));
		check[b][a] = 1;
		visited[b][a] = 1;
		while(!s.isEmpty()) {
			Pair p = s.pop();
			int x = p.x;
			int y = p.y;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>m-1 || ny<0 || ny>n-1 || visited[ny][nx] == 1) continue;
				else if(arr[ny][nx] == 2) {
					isFail = true;
					//break; // 영역 근처에 바이러스가 있으면 Fail
				}
				else if(arr[ny][nx] == 3 && newWall[ny][nx]!=1) {
					visited[ny][nx] = 1;
					check[ny][nx] = 1;
					s.push(new Pair(nx, ny));
				}
			}
		}
		if(isFail) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(visited[i][j] == 1) {
						check[i][j] = 2;
					}
				}
			}
		}
		countingSafe(arr, check, newWall, n, m);
	}
	
	
	
	public static void setWall(int arr[][], int visited[][], int depth, int n, int m) {
		if(depth == 3) {
			int check[][] = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if((arr[i][j] == 3 || arr[i][j] == 0) && check[i][j] == 0 && visited[i][j]!=1) {
						release(arr, visited, check, j, i, n, m);
					}
				}	
				
				
			}
			return;
		}// 세 개의 벽을 세웠을 때 
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(visited[i][j]==0 && (arr[i][j]==0 || arr[i][j] == 3)) {
					visited[i][j] = 1;
					setWall(arr, visited, depth+1, n, m);
					visited[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int arr[][] = new int[n][m];
		int visited[][] = new int[n][m];
		
		// 지도의 크기 입력
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				arr[i][j] = scn.nextInt();
			}
		} // 지도의 내용 입력
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 2) {
					virus.add(new Pair(j, i));
				}
			}
		}// 바이러스 위치 파악하기
		for(int i=0; i<virus.size(); i++) {
			Pair p = virus.get(i);
			spreadVirus(arr, p.x, p.y, n, m);
		} // 바이러스를 먼저 퍼뜨린다.

		setWall(arr, visited, 0, n, m);
		System.out.println(max);
		// 새로운 벽 3개의 위치 설정하는 함수
		// 벽 입력
		// 바이러스 퍼트리기 - 완
		// 0의 개수 확인 - 완
		
	}
}
