package 백준;

import java.util.Scanner;


//크기가 N*M인 지도
//(r, c) r북쪽으로부터 떨어진 칸 개수 c 서쪽으로부터 떨어진 칸 개수
public class B14499_주사위굴리기_SSR {
	static int N,M;
	static int x, y;
	static int k;
	static int command[];
	static int map[][];
	final static int dx[]={0,0,-1,1};//{1,-1,0,0};
	final static int dy[]={1,-1,0,0};//{0,0,-1,1};
	//static int nx, ny;
	
	static class Dice{
		int top, bottom, e,w,s,n;
		public Dice() {
			this.top=0;
			this.bottom=0;
			this.e=0;
			this.w=0;
			this.s=0;
			this.n=0;
		}
		
		public void moveE() {
			int temp=top;
			top=w;
			w=bottom;
			bottom=e;
			e=temp;
		}
		
		public void moveW() {
			int temp=top;
			top=e;
			e=bottom;
			bottom=w;
			w=temp;
		}
		
		public void moveS() {
			int temp=top;
			top=n;
			n=bottom;
			bottom=s;
			s=temp;
			
		}
		
		public void moveN() {
			int temp=top;
			top=s;
			s=bottom;	
			bottom=n;
			n=temp;
		}
		
		public int getTop() {
			return top;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		x=sc.nextInt();
		y=sc.nextInt();
		k=sc.nextInt();
		
		map=new int[N][M];
		command=new int[k];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				map[i][j]=sc.nextInt();
		
		for(int i=0;i<k;i++)
			command[i]=sc.nextInt();	
		
		solve();	
	}
	
	public static void solve() {
		int nx,ny;
		Dice dice=new Dice();
		
		for(int c:command) {
			nx=x+dx[c-1];
			ny=y+dy[c-1];
			if(nx>=0&&nx<N&&ny>=0&&ny<M) {
				switch(c) {
					case 4://남
						dice.moveS();
						break;
					case 3://북
						dice.moveN();
						break;
					case 2://서
						dice.moveW();
						break;
					case 1: //동
						dice.moveE();
						break;
				}
				
				if(map[nx][ny]==0) {
					map[nx][ny]=dice.bottom;
					
				}else {
					dice.bottom=map[nx][ny];
					map[nx][ny]=0;
				}
				
				System.out.println(dice.getTop());
				
				x=nx;
				y=ny;
			}
		}
		
		
	}

}
