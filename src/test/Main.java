package test;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		GoMap map=new GoMap();
		if(in.nextLine().equals("9")) {
			System.out.println("����ģʽ");
			int i=17;
			int j=57;
//			map.setMap(i, j);
//			map.stack();
			map.queue();
			map.setPoint(2, 2, i-3, j-3);
			map.runMap();
		}
		else{
			System.out.println("��ͼ��С: (x,y����) ���ո�ָ���");
			map.setMap(in.nextInt(), in.nextInt());
			System.out.println();
			map.print();
			System.out.println("���ɷ���: 1.������ȣ�ջ�� 2.������ȣ��ݹ飩 3.������ȣ�����)");
			int s=in.nextInt();
			switch(s) {
				case 1:map.stack();break;
				case 2:map.recur();break;
				case 3:map.queue();break;
				default:System.out.println("fuck me"+in.nextLine()); System.exit(0);
			}
			map.print();
			System.out.println("ѡ��������: �����ݺ�ᣩ��ǰ��n����ɫ0�ͼ�10*n�����ո�ָ���");
			map.setPoint(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			System.out.println();
			map.runMap();
		}
	}
}