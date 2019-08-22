package baekjoon;
import java.util.LinkedList;
import java.util.Scanner;

public class B12100_2048Easy_HSJ {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int MAX;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		MAX = Integer.MIN_VALUE;
		//move(map, 0, 0);
		
		for(int i =0; i < 4; i++) {
			move(map, i, 0);
		}
		
		//dfs(map, 0);
		System.out.println(MAX);
	}
	
	public static void dfs(int[][] m, int depth) {
		if(depth == 5) {
			//max값 구하기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					//System.out.print(m[i][j]+" ");
					MAX = Math.max(MAX, m[i][j]);
					//System.out.println(MAX);
				}
				//System.out.println("");
			}
			//System.out.println("");
			return;
		}else {
			
			for(int i = 0; i < 4; i++) {
				//System.out.print(i);
				move(m, i, depth);
				//move(m, i, depth-1);
			}
			
		}
	}
	
	public static void move(int[][] m, int dir, int d) {
		LinkedList<Integer> list = new LinkedList<>();
		int[][] nMap = new int[N][N];
		switch(dir) {
		case 0://아래
			for(int i = 0; i < N; i++) {
				for(int j = N-1; j >= 0; j--) {
					if(m[j][i] != 0) {
						list.add(m[j][i]);
					}
					
				}
				
				int idx = N-1;
				for(int j = 0; j < list.size(); j++,idx--) {
					if(j == list.size()-1) {
						nMap[idx][i] = list.get(j);
					}else {
						if(list.get(j).intValue()==list.get(j+1).intValue()) {
							nMap[idx][i] = list.get(j)*2;
							++j;
						}else {
							nMap[idx][i] = list.get(j);
						}
					} 
					
				}
				list.clear();//초기화
			}
			dfs(nMap, d+1);
			break;
		case 1://위
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(m[j][i] != 0) {
						list.add(m[j][i]);
					}
				}
				int idx = 0;
				for(int j = 0; j < list.size(); j++,idx++) {
					if(j == list.size()-1) {
						nMap[idx][i] = list.get(j);
					}else {
						if(list.get(j).intValue()==list.get(j+1).intValue()) {
							nMap[idx][i] = list.get(j)*2;
							++j;
						}else {
							nMap[idx][i] = list.get(j);
						}
					} 
				}
				list.clear();//초기화
			}
			dfs(nMap, d+1);
			break;
		case 2://오른쪽
			for(int i = 0; i < N; i++) {
				for(int j = N-1; j >= 0; j--) {
					if(m[i][j] != 0) {
						list.add(m[i][j]);
					}
				}
				int idx = N-1;
				for(int j = 0; j < list.size(); j++,idx--) {
					if(j == list.size()-1) {
						nMap[i][idx] = list.get(j);
					}else {
						if(list.get(j).intValue()==list.get(j+1).intValue()) {
							nMap[i][idx] = list.get(j)*2;
							++j;
						}else {
							nMap[i][idx] = list.get(j);
						}
					} 
				}
				list.clear();//초기화
			}
			dfs(nMap, d+1);
			break;
		case 3://왼쪽
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(m[i][j] != 0) {
						list.add(m[i][j]);
					}
				}
				int idx =0;
				for(int j = 0; j < list.size(); j++,idx++) {
					if(j == list.size()-1) {
						nMap[i][idx] = list.get(j);
					}else {
						if(list.get(j).intValue()==list.get(j+1).intValue()) {
							nMap[i][idx] = list.get(j)*2;
							++j;
						}else {
							nMap[i][idx] = list.get(j);
						}
					} 
				}
				list.clear();//초기화
			}
			dfs(nMap, d+1);
			break;
		}
	}

}
