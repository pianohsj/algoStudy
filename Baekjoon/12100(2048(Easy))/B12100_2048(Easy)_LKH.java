import java.util.*;
public class Main {
	static int max = Integer.MIN_VALUE;
	public static void move(int map[][], int dir, int n) {
		LinkedList<Integer> list = new LinkedList<>();
		if(dir == 0 || dir == 1) {
			if(dir == 0) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(map[i][j]>0) {
							list.add(map[i][j]);
							map[i][j] = 0;
						}
					} 
					int j=0;
					while(!list.isEmpty()) {
						map[i][j] = list.remove();
						if(!list.isEmpty()) {
							if(map[i][j] == list.getFirst())
								map[i][j] += list.remove();
						}
						j++;
					}
				}
			}
			else {
				for(int i=0; i<n; i++) {
					for(int j=n-1; j>=0; j--) {
						if(map[i][j]>0) {
							list.add(map[i][j]);
							map[i][j] = 0;
						}
					} 
					int j=n-1;
					while(!list.isEmpty()) {
						map[i][j] = list.remove();
						if(!list.isEmpty()) {
							if(map[i][j] == list.getFirst())
								map[i][j] += list.remove();
						}
						j--;
					}
				}
			}
		}// 0=>왼쪽   / 1=>오른쪽
		else {
			if(dir == 2) {
				for(int i=0; i<n; i++) {
					for(int j=n-1; j>=0; j--) {
						if(map[j][i]>0) {
							list.add(map[j][i]);
							map[j][i] = 0;
						}
					} 
					int j=n-1;
					while(!list.isEmpty()) {
						map[j][i] = list.remove();
						if(!list.isEmpty()) {
							if(map[j][i] == list.getFirst())
								map[j][i] += list.remove();
						}
						j--;
					}
				}
			}
			else {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(map[j][i]>0) {
							list.add(map[j][i]);
							map[j][i] = 0;
						}
					} 
					int j=0;
					while(!list.isEmpty()) {
						map[j][i] = list.remove();
						if(!list.isEmpty()) {
							if(map[j][i] == list.getFirst())
								map[j][i] += list.remove();
						}
						j++;
					}
				}
			}
		}// 2=>위쪽  / 3=>아래쪽
	}
	public static void pickDir(int map[][], int output[], int depth, int n) {
		if(depth == 5) {
			int copy[][] = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					copy[i][j] = map[i][j];
				}
			}
			int tmp[] = {3,0,0,0,0};
			for(int i=0; i<5; i++) {
				move(copy, output[i], n);
			}
			int tmp_max = Integer.MIN_VALUE;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(copy[i][j]>tmp_max) tmp_max = copy[i][j];
				}
			}
			if(tmp_max>max) max = tmp_max;
			return;
		}
		for(int i=0; i<4; i++) {
			output[depth] = i;
			pickDir(map, output, depth+1, n);
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    Scanner scn = new Scanner(System.in);
	    int n = scn.nextInt();
	    int map[][] = new int[n][n];
	    for(int i=0; i<n; i++) {
	    	for(int j=0; j<n; j++) {
	        	map[i][j] = scn.nextInt();
	        }
	    }
	    int output[] = new int[5];
	    pickDir(map, output, 0, n);
	    System.out.println(max);
	}
}