import java.util.*;
public class Main {
	static class Tree implements Comparable<Tree>{
		int x,y,age;
		Tree(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Tree t) {
			return this.age >= t.age ? 1: -1;
		}
	}
	
	public static void spring(PriorityQueue<Tree> pq, int tree[][], int status[][], int n) {
		//int size = pq.size();
		int power = 0;
		int age = 0;
		PriorityQueue<Tree> newQ = new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Tree t = pq.remove();
			power = status[t.y][t.x];
			age = t.age;
			if(age<=power) {
				status[t.y][t.x] -= age;
				newQ.add(new Tree(t.x, t.y, age+1));
			} // 이 나무는 아직 살 수 있습니다.
			else {
				int pow = age/2;
				tree[t.y][t.x] += pow;
			} // 죽을 경우
		}
		while(!newQ.isEmpty()) {
			pq.add(newQ.remove());
		}
	}
	public static void summer(int tree[][], int status[][], int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				status[i][j] += tree[i][j]/2;
			}
		}
	}
	public static void fall(PriorityQueue<Tree> pq, int tree[][], int n) {
		LinkedList<int[]> list = new LinkedList<>();
		//int pair[] = new int[2];
		for(Tree t : pq) {
			int x = t.x;
			int y = t.y;
			int age = t.age;
			if(age%5==0) {
				int pair[] = new int[2];
				pair[0] = t.x;
				pair[1] = t.y;
				list.add(pair);
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			int pair[] = list.get(i);
			int x = pair[0];
			int y = pair[1];
			if(x-1>=0 && x-1<=n-1 && y-1>=0 && y-1<=n-1)
				pq.add(new Tree(x-1,y-1,1)); // r-1 c-1
			if(x-1>=0 && x-1<=n-1 && y>=0 && y<=n-1)
				pq.add(new Tree(x-1,y,1)); // r-1 c
			if(x-1>=0 && x-1<=n-1 && y+1>=0 && y+1<=n-1)
				pq.add(new Tree(x-1,y+1,1)); // r-1 c+1
			if(x>=0 && x<=n-1 && y-1>=0 && y-1<=n-1)
				pq.add(new Tree(x,y-1,1)); // r c-1
			if(x>=0 && x<=n-1 && y+1>=0 && y+1<=n-1)
				pq.add(new Tree(x,y+1,1)); // r c+1
			if(x+1>=0 && x+1<=n-1 && y-1>=0 && y-1<=n-1)
				pq.add(new Tree(x+1,y-1,1)); // r+1 c-1
			if(x+1>=0 && x+1<=n-1 && y>=0 && y<=n-1)
				pq.add(new Tree(x+1,y,1)); // r+1 c
			if(x+1>=0 && x+1<=n-1 && y+1>=0 && y+1<=n-1)
				pq.add(new Tree(x+1,y+1,1)); // r+1 c+1
			
		}
	}
	public static void winter(int status[][], int energy[][], int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				status[i][j] += energy[i][j];
			}
		}
	}
	public static void simulation(PriorityQueue<Tree> pq, int tree[][], int status[][], int energy[][], int n, int k) {
		int year = 0;
		while(year<k) {
			// 봄
			spring(pq, tree, status, n);
			// 여름
			summer(tree, status, n);
			// 가을
			fall(pq, tree, n);
			// 겨울
			winter(status, energy, n);
			year++;
		}
	}
	public static void main(String[] args){
	// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt(); // 땅의 크기
		int m = scn.nextInt(); // 나무의 개수
		int k = scn.nextInt(); // 기간
		int energy[][] = new int[n][n]; // 겨울에 추가되는 양분의 정보
		int tree[][] = new int[n][n]; // 나무의 위치와 나이 정보
		int status[][] = new int[n][n]; // 현재 땅이 갖고 있는 양분의 정보
		LinkedList<Tree> list = new LinkedList<>();
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				status[i][j] = 5;
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				energy[i][j] = scn.nextInt();
			}
		}
		
		for(int i=0; i<m; i++) {
			int x = scn.nextInt();
			int y = scn.nextInt();
			int age = scn.nextInt();
			pq.add(new Tree(x-1,y-1,age));
		}
		simulation(pq, tree, status, energy, n, k);
		System.out.println(pq.size());
		// 입력부
	}
}