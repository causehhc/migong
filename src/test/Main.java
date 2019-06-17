package test;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	static void begin(int map[][]) {
		for(int i=2;i<map.length-1;i+=2) {
			for(int j=2;j<map[i].length-1;j+=2) {
				map[i][j]=1;
			}
		}
	}
	
	static void print(int map[][]) {
		for(int i=1;i<map.length-1;i++) {
			for(int j=1;j<map[i].length-1;j++) {
				if(map[i][j]==0) {
					System.out.print("\u001b[40m0\u001b[0m");
				}
				else {
					System.out.print("\u001b[37m8\u001b[0m");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void draw(int map[][]) {
		Scanner in = new Scanner(System.in);
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		in.nextLine();
	}
	
	static boolean judge(int map[][],int x,int y) {
		if(map[x][y+2]+map[x][y+1]==1||map[x-2][y]+map[x-1][y]==1||map[x][y-2]+map[x][y-1]==1||map[x+2][y]+map[x+1][y]==1) {
			return true;
		}
		return false;
	}
	
	static boolean recursion(int map[][],int x,int y) {
//		System.out.println("push in:"+x+"+"+y);
//		draw(map);
		while(judge(map,x,y)) {
			int d=(int)(Math.random()*4);
			switch(d) {
				case 0:{
					if(map[x][y+1]+map[x][y+2]==1) {
						map[x][y+1]=map[x][y+2]=8;
						recursion(map,x,y+2);
					}
					break;
				}
				case 1:{
					if(map[x-1][y]+map[x-2][y]==1) {
						map[x-1][y]=map[x-2][y]=8;
						recursion(map,x-2,y);
					}
					break;
				}
				case 2:{
					if(map[x][y-1]+map[x][y-2]==1) {
						map[x][y-1]=map[x][y-2]=8;
						recursion(map,x,y-2);
					}
					break;
				}
				case 3:{
					if(map[x+1][y]+map[x+2][y]==1) {
						map[x+1][y]=map[x+2][y]=8;
						recursion(map,x+2,y);
					}
					break;
				}
			}
		}
//		System.out.println("pop out:"+x+"+"+y);
		return false;
	}
	
	static void stack(int map[][],int x,int y) {
		Stack<Integer> st=new Stack<Integer>(); 
		st.push(y);
		st.push(x);
		do {	
			x=st.pop();
			y=st.pop();
			st.push(y);
			st.push(x);
			while(judge(map,x,y)) {
				int d=(int)(Math.random()*4);
				switch(d) {
					case 0:{
						if(map[x][y+1]+map[x][y+2]==1) {
							map[x][y+1]=map[x][y+2]=8;
							st.push(y+2);
							st.push(x);
						}
						break;
					}
					case 1:{
						if(map[x-1][y]+map[x-2][y]==1) {
							map[x-1][y]=map[x-2][y]=8;
							st.push(y);
							st.push(x-2);
						}
						break;
					}
					case 2:{
						if(map[x][y-1]+map[x][y-2]==1) {
							map[x][y-1]=map[x][y-2]=8;
							st.push(y-2);
							st.push(x);
						}
						break;
					}
					case 3:{
						if(map[x+1][y]+map[x+2][y]==1) {
							map[x+1][y]=map[x+2][y]=8;
							st.push(y);
							st.push(x+2);
						}
						break;
					}
				}
				x=st.pop();
				y=st.pop();
				st.push(y);
				st.push(x);
//				System.out.println("push in:"+x+"+"+y);
//				System.out.print("stack:");
//				for(Integer q : st){
//		            System.out.print(q+" ");
//		        }
//				System.out.println();
//				draw(map);
			}
			x=st.pop();
			y=st.pop();
//			System.out.println("pop out:"+x+"+"+y);
		}while(!st.empty());
	}
	
	static void queue(int map[][],int x,int y) {
		LinkedList<Integer> que = new LinkedList<Integer>();
		que.offer(x);
		que.offer(y);   
		do {
			
			Integer[] temp = new Integer[que.size()];
			que.toArray(temp);
			int n;
			do{
				n=(int)(Math.random()*que.size());
			}while(n%2!=0);
			x=temp[n];
			y=temp[n+1];
			
			while(judge(map,x,y)) {
				int d=(int)(Math.random()*4);
				switch(d) {
					case 0:{
						if(map[x][y+1]+map[x][y+2]==1) {
							map[x][y]=map[x][y+1]=map[x][y+2]=8;
							que.offer(x);
							que.offer(y+2);
						}
						break;
					}
					case 1:{
						if(map[x-1][y]+map[x-2][y]==1) {
							map[x][y]=map[x-1][y]=map[x-2][y]=8;
							que.offer(x-2);
							que.offer(y);
						}
						break;
					}
					case 2:{
						if(map[x][y-1]+map[x][y-2]==1) {
							map[x][y]=map[x][y-1]=map[x][y-2]=8;
							que.offer(x);
							que.offer(y-2);
						}
						break;
					}
					case 3:{
						if(map[x+1][y]+map[x+2][y]==1) {
							map[x][y]=map[x+1][y]=map[x+2][y]=8;
							que.offer(x+2);
							que.offer(y);
						}
						break;
					}
				}
				
//				System.out.print("queue:");
//				for(Integer q : que){
//		            System.out.print(q+" ");
//		        }
//				System.out.println();
//				draw(map);
			}
			
			que.remove(n);
			que.remove(n);
//			System.out.print("queue:");
//			for(Integer q : que){
//	            System.out.print(q+" ");
//	        }
//			System.out.println();
//			draw(map);
		}while(!que.isEmpty());
		
	}
	
	public static void main(String[] args) {
		int[][][] map=new int[3][17][107];
		begin(map[0]);
		begin(map[1]);
		begin(map[2]);
//		draw(map[0]);
		
		recursion(map[0],2,2);
		print(map[0]);
		
		stack(map[1],2,2);
		print(map[1]);
		
		queue(map[2],10,2);
		print(map[2]);
		
	}
}
