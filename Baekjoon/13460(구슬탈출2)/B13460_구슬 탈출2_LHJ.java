package test;

import java.util.*;

public class Main {
	static int N,M;
	static String[][] map;
	static Pair[] ball;
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int ans=Integer.MAX_VALUE; 
	public static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;this.y=y;
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		
		map=new String[N][M];
		ball=new Pair[2];
		for(int i=0; i<N; i++) {
			String temp=sc.next();
			for(int j=0; j<M; j++) {
				map[i][j]=temp.charAt(j)+"";
				if(map[i][j].equals("R")) {
					ball[0]=new Pair(i,j);
				}
				if(map[i][j].equals("B")) {
					ball[1]=new Pair(i,j);
				}
				
			}
		}
		dfs(ball,0,-1);
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	public static Pair move(Pair p,int d,String color) {
		int nx=p.x;
		int ny=p.y;
		while(true) {
			nx=nx+dx[d];
			ny=ny+dy[d];
			if(map[nx][ny].equals("O")) {
				////////
				map[p.x][p.y]=".";
				return null;
			}else if(!map[nx][ny].equals(".")) {
				map[p.x][p.y]=".";
				map[nx-dx[d]][ny-dy[d]]=color;
				return new Pair(nx-dx[d],ny-dy[d]);
			}
		}
	}
	public static Pair[] go(Pair[] b, int d) {
		Pair[] newB=new Pair[2];
		if(d==0) {
			if(b[0].x<=b[1].x) {
				newB[0]=move(b[0],d,"R");
				newB[1]=move(b[1],d,"B");
			}else {
				newB[1]=move(b[1],d,"B");
				newB[0]=move(b[0],d,"R");
			}
		}
		if(d==1) {
			if(b[0].y>=b[1].y) {
				newB[0]=move(b[0],d,"R");
				newB[1]=move(b[1],d,"B");
			}else {
				newB[1]=move(b[1],d,"B");
				newB[0]=move(b[0],d,"R");
			}
		}
		if(d==2) {
			if(b[0].x>=b[1].x) {
				newB[0]=move(b[0],d,"R");
				newB[1]=move(b[1],d,"B");
			}else {
				newB[1]=move(b[1],d,"B");
				newB[0]=move(b[0],d,"R");
			}
		}
		if(d==3) {
			if(b[0].y<=b[1].y) {
				newB[0]=move(b[0],d,"R");
				newB[1]=move(b[1],d,"B");
			}else {
				newB[1]=move(b[1],d,"B");
				newB[0]=move(b[0],d,"R");
			}
		}
		return newB;
	}
	public static void dfs(Pair[] b,int n, int D) {
		if(n>10 || b[1]==null) {
			return;
		}
		if(b[0]==null && b[1]!=null) {
			ans=Math.min(ans, n);
			return;
		}
		for(int d=0; d<4; d++) {
			if(d!=D) {//->if있고 었고는 결과는 같지만 속토차이 많이남
				Pair[] newB=go(b,d);
				dfs(newB,n+1,d);
				//-> 이거 빼먹어서 결과 틀렸었음
				if(newB[0]!=null) {
					map[newB[0].x][newB[0].y]=".";
				}
				if(newB[1]!=null) {
					map[newB[1].x][newB[1].y]=".";
				}
				map[b[0].x][b[0].y]="R";
				map[b[1].x][b[1].y]="B";
				//
			}
		}
	}
}
