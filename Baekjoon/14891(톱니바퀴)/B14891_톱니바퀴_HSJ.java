package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B14891_Åé´Ï¹ÙÄû_HSJ {
	static LinkedList<Integer>[] list= new LinkedList[4];
	static boolean[] diffCheck = new boolean[3];
	static boolean[] turnCheck = new boolean[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String List;
		for (int i=0;i<4;i++)
			list[i] = new LinkedList<Integer>();
		for(int i = 0; i < 4; i++) {
			List = sc.next();
			for(int j = 0; j < 8; j++) {
				list[i].add(List.charAt(j)-'0');
			}
		}
		int cnt = sc.nextInt();
		for(int i = 0; i < cnt; i++) {
			if(list[0].get(2)!=list[1].get(6)) diffCheck[0] = true;
			if(list[1].get(2)!=list[2].get(6)) diffCheck[1] = true;
			if(list[2].get(2)!=list[3].get(6)) diffCheck[2] = true;
			go(sc.nextInt()-1, sc.nextInt());
			
			diffCheck = new boolean[3];
			turnCheck = new boolean[4];
		}
		System.out.println(getScore());
	}
	
	public static void go(int num, int dir) {
		switch(num) {
		case 0:
			turn(num, dir);
			turnCheck[num] = true;
			if(dir==1) dir = -1;
			else dir = 1;
			if(diffCheck[0] && !turnCheck[num+1]) go(num+1, dir);
			break;
		case 1:
			turn(num, dir);
			turnCheck[num] = true;
			if(dir==1) dir = -1;
			else dir = 1;
			if(diffCheck[0] && !turnCheck[num-1]) go(num-1, dir);
			if(diffCheck[1] && !turnCheck[num+1]) go(num+1, dir);
			break;
		case 2:
			turn(num, dir);
			turnCheck[num] = true;
			if(dir==1) dir = -1;
			else dir = 1;
			if(diffCheck[1] && !turnCheck[num-1]) go(num-1, dir);
			if(diffCheck[2] && !turnCheck[num+1]) go(num+1, dir);
			break;
		case 3:
			turn(num, dir);
			turnCheck[num] = true;
			if(dir==1) dir = -1;
			else dir = 1;
			if(diffCheck[2] && !turnCheck[num-1]) go(num-1, dir);
			break;
		}
		
	}
	
	public static void turn(int n, int d) {
		if(d == 1) {
			list[n].addFirst(list[n].removeLast());
		}else{
			list[n].addLast(list[n].removeFirst());
		}
	}
	
	public static int getScore() {
		int score = 0;
		for(int i = 0; i < 4; i++) {
			switch(i) {
			case 0:
				if(list[i].getFirst() == 1) score += 1;
				break;
			case 1:
				if(list[i].getFirst() == 1) score += 2;
				break;
			case 2:
				if(list[i].getFirst() == 1) score += 4;
				break;
			case 3:
				if(list[i].getFirst() == 1) score += 8;
				break;
			}
			
		}
		return score;
	}

}
