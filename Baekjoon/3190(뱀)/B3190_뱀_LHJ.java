import java.util.*;
public class Main {
	static int N,K,L;
	static int[][] map;
	static String[] dir;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int time=0;
	static LinkedList<Pair> snake;
	static public class Pair{
		public int x,y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static int changeDir(int n,String dir) {
		if(dir==null) {
			return n;
		}
		if(dir.equals("L")) {
			if(n==0) {
				return 3;
			}else {
				return n-1;
			}
		}else if(dir.equals("D")) {
			if(n==3) {
				return 0;
			}else {
				return n+1;
			}
		}else {
			return n;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		K=sc.nextInt();
		map=new int[N+2][N+2];
		for(int i=0; i<K; i++) {
			map[sc.nextInt()][sc.nextInt()]=1;
		}
		L=sc.nextInt();
		dir=new String[10000];
		for(int i=0; i<L; i++) {
			dir[sc.nextInt()]=sc.next();
		}
		map[1][1]=2;
		for(int i=0; i<N+2; i++) {
			for(int j=0; j<N+2; j++) {
				if(i==0 || i==N+1 || j==0 || j==N+1) {
					map[i][j]=3;
				}
			}
		}
		snake=new LinkedList<Pair>();
		snake.add(new Pair(1,1));
		int d=1;
		while(true) {
			time+=1;
			int x=snake.getFirst().x+dx[d];
			int y=snake.getFirst().y+dy[d];
			if(map[x][y]==2 || map[x][y]==3) {
				break;
			}else {
				if(map[x][y]==1) {
					snake.addFirst(new Pair(x,y));
					map[x][y]=2;
				}else {
					snake.addFirst(new Pair(x,y));
					map[x][y]=2;
					Pair r=snake.removeLast();
					map[r.x][r.y]=0;
				}
				d=changeDir(d,dir[time]);
			}
//			for(int i=0; i<N+2; i++) {
//				for(int j=0; j<N+2; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println(d+" "+dir[time]);
		}
		System.out.println(time);
	}

}
