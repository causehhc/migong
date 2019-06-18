package test;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		GoMap map=new GoMap();
		if(in.nextLine().equals("9")) {
			System.out.println("测试模式");
			int i=17;
			int j=57;
//			map.setMap(i, j);
//			map.stack();
			map.queue();
			map.setPoint(2, 2, i-3, j-3);
			map.runMap();
		}
		else{
			System.out.println("地图大小: (x,y奇数) （空格分隔）");
			map.setMap(in.nextInt(), in.nextInt());
			System.out.println();
			map.print();
			System.out.println("生成方法: 1.深度优先（栈） 2.深度优先（递归） 3.广度优先（队列)");
			int s=in.nextInt();
			switch(s) {
				case 1:map.stack();break;
				case 2:map.recur();break;
				case 3:map.queue();break;
				default:System.out.println("fuck me"+in.nextLine()); System.exit(0);
			}
			map.print();
			System.out.println("选择入点出点: （先纵后横）（前面n个红色0就加10*n）（空格分隔）");
			map.setPoint(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			System.out.println();
			map.runMap();
		}
	}
}