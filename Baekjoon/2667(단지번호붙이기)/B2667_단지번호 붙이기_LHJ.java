import java.util.*;
public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		map=new int[N][N];
		visited=new boolean[N][N];
		for(int i=0; i<N; i++) {
			String line = sc.next();
			for(int j=0; j<N; j++) {
				map[i][j]=line.charAt(j)-'0';
			}
		}
		count=new ArrayList<Integer>();
		int num=1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]==false && map[i][j]!=0) {
					count.add(0);
					solve(i,j,num);
					num+=1;
				}
			}
		}
		Collections.sort(count);
		System.out.println(count.size());
		for(int i=0; i<count.size(); i++) {
			System.out.println(count.get(i));
		}
	}
	public static void solve(int x, int y, int num) {
		visited[x][y]=true;
		map[x][y]=num;
		count.set(num-1, count.get(num-1)+1);
		if(x+1<N && visited[x+1][y]==false && map[x+1][y]!=0) {
			solve(x+1,y,num);
		}
		if(x-1>=0 && visited[x-1][y]==false && map[x-1][y]!=0) {
			solve(x-1,y,num);
		}
		if(y+1<N && visited[x][y+1]==false && map[x][y+1]!=0) {
			solve(x,y+1,num);
		}
		if(y-1>=0 && visited[x][y-1]==false && map[x][y-1]!=0) {
			solve(x,y-1,num);
		}
	}

}
