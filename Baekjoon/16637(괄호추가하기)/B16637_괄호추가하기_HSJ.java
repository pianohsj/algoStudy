package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class B16637_괄호추가하기_HSJ {
	static int N;
	static LinkedList<Integer> nlist = new LinkedList<>();
	static LinkedList<Character> olist = new LinkedList<>();
	static long answer = Long.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String s = sc.next();
		for(int i = 0; i < N; i++) {
			if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*')
				olist.add(s.charAt(i));
			else nlist.add(s.charAt(i)-'0');
		}
		go(nlist.get(0),0);
		System.out.println(answer);
		
	}
	public static void go(long sum, int idx) {
		long tmp = 0;
		if(idx >= olist.size()) {
			if(sum > answer) answer = sum;
		}else {
			//바로 다음꺼 그냥 더할때
			tmp = calculate(sum, nlist.get(idx+1), olist.get(idx));
			go(tmp, idx+1);
			//괄호칠 때
			if(idx+2 < nlist.size()) {
				tmp = calculate(sum,
						  		calculate(nlist.get(idx+1),nlist.get(idx+2),olist.get(idx+1)),
						  		olist.get(idx)
								);
				go(tmp,idx+2);
			}
		}
	}
	
	public static long calculate(long sum, long num, char c) {
		switch(c) {
		case '+':
			return sum+num;
		case '-':
			return sum-num;
		case '*':
			return sum*num;
		default:
			return sum;
		}
	}
	
	

}
