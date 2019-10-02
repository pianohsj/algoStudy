import java.util.*;
public class Main {
	static int N,M,K;
	static int[][] plus;
	static int[][] value;
	static int[] dx= {-1,-1,-1,0,0,1,1,1};
	static int[] dy= {-1,0,1,-1,1,-1,0,1};
	public static class Tree{
		int x,y,age;
		Tree(int x,int y,int age){
			this.x=x;
			this.y=y;
			this.age=age;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		plus=new int[N][N];
		value=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				plus[i][j]=sc.nextInt();
			}
		}
		PriorityQueue<Tree> Trees=new PriorityQueue<Tree>((Tree a,Tree b)-> a.age-b.age);
		for(int i=0; i<M; i++) {
			Trees.add(new Tree(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt()));
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				value[i][j]=5;
			}
		}
		for(int k=0; k<K; k++) {
			Queue<Tree> alive=new LinkedList<Tree>();
			Queue<Tree> dead=new LinkedList<Tree>();
			while(!Trees.isEmpty()) {
				Tree t=Trees.poll();
				if(value[t.x][t.y]>=t.age) {
					value[t.x][t.y]-=t.age;
					alive.add(new Tree(t.x,t.y,t.age+1));
				}else {
					dead.add(t);
				}
			}
			while(!dead.isEmpty()) {
				Tree t=dead.poll();
				value[t.x][t.y]+=t.age/2;
			}
			while(!alive.isEmpty()) {
				Tree t=alive.poll();
				if(t.age%5==0) {
					for(int d=0; d<8; d++) {
						int nx=t.x+dx[d];
						int ny=t.y+dy[d];
						if(nx>=0 && ny>=0 && nx<N && ny<N) {
							Trees.add(new Tree(nx,ny,1));
						}
					}
				}
				Trees.add(t);
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					value[i][j]+=plus[i][j];
				}
			}
		}
		System.out.println(Trees.size());
	}
}
