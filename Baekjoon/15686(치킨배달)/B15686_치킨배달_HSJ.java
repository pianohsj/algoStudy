package baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B15686_치킨배달_HSJ {
	static int N, M;
	static Queue<Pair> house = new LinkedList<>();
	static Queue<Pair> chicken =  new LinkedList<>();
	static int[] answer;
	static class Pair{
		int x, y,len;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		Pair(int x, int y, int len){
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int kind = 0;
		int min = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				kind = sc.nextInt();
				if(kind == 1) {
					house.add(new Pair(i,j,0));
				}else if(kind == 2) {
					chicken.add(new Pair(i,j));
				}
			}
		}
		answer = new int[house.size()];
		
		calculate();
		Arrays.sort(answer);
		for(int i = 0; i < M; i++) {
			min+=answer[i];
		}
		System.out.println(min);
	}
	
	public static void calculate() {
		for(int i = 0; i < house.size(); i ++) {
			for(int j = 0; j < chicken.size(); j++) {
				if(answer[i] == 0) {
					answer[i] = Math.abs(house.peek().x-chicken.peek().x)+Math.abs(house.peek().y-chicken.peek().y);
				}else {
					answer[i] = Math.min(answer[i], Math.abs(house.peek().x-chicken.peek().x)+Math.abs(house.peek().y-chicken.peek().y));
				}
				chicken.add(chicken.remove());
			}
			house.add(house.remove());
		}
	}

}
