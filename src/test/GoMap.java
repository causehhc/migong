package test;

import java.util.LinkedList;
import java.util.Stack;

public class GoMap extends CreatMap {
	private int bx = -1;// ��ʼ�����x����
	private int by = -1;// ��ʼ�����y����
	private int ex = -1;// ��ʼ������x����
	private int ey = -1;// ��ʼ������y����
	private int keys = 0;// ���ɽ���жϱ��
	LinkedList<Integer> queue;// ͨ�ö���
	private LinkedList<Integer> queue2;// ���Ѱ·����
	private LinkedList<int[][]> queue3;// ·�������¼
	private int[][] copyMap;
	private int[][] copyMapBest;
	private int[][] copyMapBad;

	GoMap() {
		queue = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
		queue3 = new LinkedList<int[][]>();
	}

	void setPoint(int bx, int by, int ex, int ey) {// �������ͳ���
		this.bx = bx;
		this.by = by;
		this.ex = ex;
		this.ey = ey;
		map[bx][by] = map[ex][ey] = 8;
	}

	private static int count(int[][] map) {// ����1
		int n1 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					n1++;
				}
			}
		}
		return n1;
	}

	private void best(LinkedList<int[][]> queue, int x, int y) {//�������·����ͼ
		copyMapBest = new int[x][y];
		copyMapBad= new int[x][y];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				copyMapBest[i][j] = 1;
			}
		}
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			if (count(queue.get(i)) < count(copyMapBest)) {
				copyMapBest = queue.get(i).clone();
			}
			if (count(queue.get(i)) > count(copyMapBad)) {
				copyMapBad = queue.get(i).clone();
			}
		}
	}

	int[][] copy() {//��¼·����ͼ
		copyMap = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	int count1() {// �������Ч�ʼ���
		int n1 = 0, n3 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					n1++;
				}
				if (map[i][j] == 3) {
					n3++;
				}
			}
		}
		return (n3 * 2 + n1);
	}

	int count2() {// �������Ч�ʼ���
		int n1 = 0, n3 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					n1++;
				}
				if (map[i][j] == 3) {
					n3++;
				}
			}
		}
		return (n3 + n1 * 2);
	}

	private boolean runBackMethod(int x, int y) {// ���ɶ���·��ר�ã����ݷ�����·����ʾ
//		if (keys == 1) {
//			return false;
//		}
		if (x == ex && y == ey) {
			map[x][y] = 1;
			queue3.offer(this.copy());//queue3++
//			keys = 1;
			/*
			 * ����
			 */
			
//			System.out.println("��ͼ·����:" + count(map));
//			this.print();
		}
		if (map[x][y] == 8) {
			map[x][y] = 1;
			queue.offer(x);
			queue.offer(y);
			if (runBackMethod(x, y + 1)) {
				if (runBackMethod(x - 1, y)) {
					if (runBackMethod(x, y - 1)) {
						runBackMethod(x + 1, y);
					}
				}
			}
//			if (keys == 1) {
//				return false;
//			}
			map[x][y] = 8;// 3һ���8����
		}
		return true;
	}

	private void runStackMethodNoY(int x, int y) {// ���δ�Ż�
		Stack<Integer> st = new Stack<Integer>();
		st.push(y);
		st.push(x);
		queue.offer(x);
		queue.offer(y);
		do {
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			while (true) {
				int k = 0;
				while (map[x][y + 1] == 8 && k == 0) {
					k = 1;
					st.push(y + 1);
					st.push(x);
					x = st.pop();
					y = st.pop();
					st.push(y);
					st.push(x);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:"+count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x - 1][y] == 8 && k == 0) {
					k = 1;
					st.push(y);
					st.push(x - 1);
					x = st.pop();
					y = st.pop();
					st.push(y);
					st.push(x);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:"+count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x][y - 1] == 8 && k == 0) {
					k = 1;
					st.push(y - 1);
					st.push(x);
					x = st.pop();
					y = st.pop();
					st.push(y);
					st.push(x);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:"+count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x + 1][y] == 8 && k == 0) {
					k = 1;
					st.push(y);
					st.push(x + 1);
					x = st.pop();
					y = st.pop();
					st.push(y);
					st.push(x);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:"+count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				x = st.pop();
				y = st.pop();
				st.push(y);
				st.push(x);
				queue.offer(x);
				queue.offer(y);

//				System.out.print(queue.toString());
//				System.out.println();
//				this.draw();

				if (keys == 1) {
					break;
				}
				if (k == 0 || (x == ex && y == ey)) {
					map[x][y] = 3;
					queue.pollLast();
					queue.pollLast();
					st.pop();
					st.pop();
					break;
				}
			}
			if (keys == 1) {
				break;
			}
		} while (!st.empty());
	}

	private void runQueueMethodNoY(int x, int y) {// ���δ�Ż�
		LinkedList<Integer> que = new LinkedList<Integer>();
		que.offer(x);
		que.offer(y);
		queue.offer(x);
		queue.offer(y);
		map[x][y] = 1;
		do {

			int n = 0;
			Integer[] temp = new Integer[que.size()];
			que.toArray(temp);
			while (true) {
				x = temp[n];
				y = temp[n + 1];
				int k = 0;
				while (map[x][y + 1] == 8 && k == 0) {
					k = 1;
					que.offer(x);
					que.offer(y + 1);
					y = que.pollLast();
					x = que.pollLast();
					que.offer(x);
					que.offer(y);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:" +count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x - 1][y] == 8 && k == 0) {
					k = 1;
					que.offer(x - 1);
					que.offer(y);
					y = que.pollLast();
					x = que.pollLast();
					que.offer(x);
					que.offer(y);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:" +count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x][y - 1] == 8 && k == 0) {
					k = 1;
					que.offer(x);
					que.offer(y - 1);
					y = que.pollLast();
					x = que.pollLast();
					que.offer(x);
					que.offer(y);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:" + count(map));
//						this.print();
						keys = 1;
						break;
					}
				}
				while (map[x + 1][y] == 8 && k == 0) {
					k = 1;
					que.offer(x + 1);
					que.offer(y);
					y = que.pollLast();
					x = que.pollLast();
					que.offer(x);
					que.offer(y);
					map[x][y] = 1;
					if (x == ex && y == ey) {
						k = 1;
//						System.out.println("��ͼ·����:" + count(map));
//						this.print();
						keys = 1;
						break;
					}
				}

				if (keys == 1) {
					break;
				}
				if (k == 0 || (x == ex && y == ey)) {
					map[x][y] = 3;
					que.remove(n);
					que.remove(n);
					break;
				}

				y = que.pollLast();
				x = que.pollLast();
				que.offer(x);
				que.offer(y);
				queue.offer(x);
				queue.offer(y);

//				System.out.print(queue.toString());
//				System.out.println();
//				System.out.print(que.toString());
//				System.out.println();
//				this.draw();

			}
			n += 2;
			if (keys == 1) {
				break;
			}
		} while (!que.isEmpty());
	}

	private int way0(Stack<Integer> st, int x, int y, int k) {// ����Ӳ�����
		while (map[x][y + 1] == 8 && k == 0) {
			k = 1;
			st.push(y + 1);
			st.push(x);
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			if (x == ex && y == ey) {
				keys = 1;
//				System.out.println("��ͼ·����:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way1(Stack<Integer> st, int x, int y, int k) {// ����Ӳ�����
		while (map[x - 1][y] == 8 && k == 0) {
			k = 1;
			st.push(y);
			st.push(x - 1);
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			if (x == ex && y == ey) {
				keys = 1;
//				System.out.println("��ͼ·����:"+this.count(map)());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way2(Stack<Integer> st, int x, int y, int k) {// ����Ӳ�����
		while (map[x][y - 1] == 8 && k == 0) {
			k = 1;
			st.push(y - 1);
			st.push(x);
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			if (x == ex && y == ey) {
				keys = 1;
//				System.out.println("��ͼ·����:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way3(Stack<Integer> st, int x, int y, int k) {// ����Ӳ�����
		while (map[x + 1][y] == 8 && k == 0) {

			k = 1;
			st.push(y);
			st.push(x + 1);
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			if (x == ex && y == ey) {
				keys = 1;
//				System.out.println("��ͼ·����:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int ways(int drect, Stack<Integer> st, int x, int y, int k) {// ��Ȳ�����
		if (drect == 3021) {
			k = this.way3(st, x, y, k);
			k = this.way0(st, x, y, k);
			k = this.way2(st, x, y, k);
			k = this.way1(st, x, y, k);
		}
		if (drect == 0312) {
			k = this.way0(st, x, y, k);
			k = this.way3(st, x, y, k);
			k = this.way1(st, x, y, k);
			k = this.way2(st, x, y, k);
		}
		if (drect == 3201) {
			k = this.way3(st, x, y, k);
			k = this.way2(st, x, y, k);
			k = this.way0(st, x, y, k);
			k = this.way1(st, x, y, k);
		}
		if (drect == 2310) {
			k = this.way2(st, x, y, k);
			k = this.way3(st, x, y, k);
			k = this.way1(st, x, y, k);
			k = this.way0(st, x, y, k);
		}
		if (drect == 1203) {
			k = this.way1(st, x, y, k);
			k = this.way2(st, x, y, k);
			k = this.way0(st, x, y, k);
			k = this.way3(st, x, y, k);
		}
		if (drect == 2130) {
			k = this.way2(st, x, y, k);
			k = this.way1(st, x, y, k);
			k = this.way3(st, x, y, k);
			k = this.way0(st, x, y, k);
		}
		if (drect == 1023) {
			k = this.way1(st, x, y, k);
			k = this.way0(st, x, y, k);
			k = this.way2(st, x, y, k);
			k = this.way3(st, x, y, k);
		}
		if (drect == 0132) {
			k = this.way0(st, x, y, k);
			k = this.way1(st, x, y, k);
			k = this.way3(st, x, y, k);
			k = this.way2(st, x, y, k);
		}
		return k;
	}

	private void runStackMethod(int x, int y) {// ���-�Ż�
		Stack<Integer> st = new Stack<Integer>();
		st.push(y);
		st.push(x);
		queue.offer(x);
		queue.offer(y);
		do {
			x = st.pop();
			y = st.pop();
			st.push(y);
			st.push(x);
			map[x][y] = 1;
			while (true) {
				int k = 0;
				if ((ex >= x && ey > y) || (ex > x && ey >= y)) {// ����1

					if ((ex - x) >= (ey - y))
						k = ways(3021, st, x, y, k);
					else
						k = ways(0312, st, x, y, k);

				}
				if ((ex >= x && ey < y) || (ex > x && ey <= y)) {// ����2
					if ((ex - x) >= (y - ey))
						k = ways(3201, st, x, y, k);
					else
						k = ways(2310, st, x, y, k);

				}
				if ((ex <= x && ey < y) || (ex < x && ey <= y)) {// ����3

					if ((x - ex) >= (y - ey))
						k = ways(1203, st, x, y, k);
					else
						k = ways(2130, st, x, y, k);

				}
				if ((ex <= x && ey > y) || (ex < x && ey >= y)) {// ����4
					if ((x - ex) >= (ey - y))
						k = ways(1023, st, x, y, k);
					else
						k = ways(0132, st, x, y, k);

				}
				x = st.pop();
				y = st.pop();
				st.push(y);
				st.push(x);
				queue.offer(x);
				queue.offer(y);

//				System.out.print(queue.toString());
//				System.out.println();
//				this.draw();

				if (keys == 1) {
					break;
				}
				if (k == 0 || (x == ex && y == ey)) {

					queue.pollLast();
					queue.pollLast();
					st.pop();
					st.pop();
					map[x][y] = 3;
					break;
				}
			}
			if (keys == 1) {
				break;
			}
		} while (!st.empty());
	}

	private int way0(LinkedList<Integer> que, int x, int y, int k) {// ����Ӳ�����
		while (map[x][y + 1] == 8 && k == 0) {
			k = 1;
			que.offer(x);
			que.offer(y + 1);
			y = que.pollLast();
			x = que.pollLast();
			que.offer(x);
			que.offer(y);
			map[x][y] = 1;
			if (x == ex && y == ey) {
//				System.out.println("��ͼ·����:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way1(LinkedList<Integer> que, int x, int y, int k) {// ����Ӳ�����
		while (map[x - 1][y] == 8 && k == 0) {
			k = 1;
			que.offer(x - 1);
			que.offer(y);
			y = que.pollLast();
			x = que.pollLast();
			que.offer(x);
			que.offer(y);
			map[x][y] = 1;
			if (x == ex && y == ey) {
//				System.out.println("��ͼ·����:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way2(LinkedList<Integer> que, int x, int y, int k) {// ����Ӳ�����
		while (map[x][y - 1] == 8 && k == 0) {
			k = 1;
			que.offer(x);
			que.offer(y - 1);
			y = que.pollLast();
			x = que.pollLast();
			que.offer(x);
			que.offer(y);
			map[x][y] = 1;
			if (x == ex && y == ey) {
//				System.out.println("��ͼ·����:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way3(LinkedList<Integer> que, int x, int y, int k) {// ����Ӳ�����
		while (map[x + 1][y] == 8 && k == 0) {
			k = 1;
			que.offer(x + 1);
			que.offer(y);
			y = que.pollLast();
			x = que.pollLast();
			que.offer(x);
			que.offer(y);
			map[x][y] = 1;
			if (x == ex && y == ey) {
//				System.out.println("��ͼ·����:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int ways(int drect, LinkedList<Integer> que, int x, int y, int k) {// ��Ȳ�����
		if (drect == 3021) {
			k = this.way3(que, x, y, k);
			k = this.way0(que, x, y, k);
			k = this.way2(que, x, y, k);
			k = this.way1(que, x, y, k);
		}
		if (drect == 0312) {
			k = this.way0(que, x, y, k);
			k = this.way3(que, x, y, k);
			k = this.way1(que, x, y, k);
			k = this.way2(que, x, y, k);
		}
		if (drect == 3201) {
			k = this.way3(que, x, y, k);
			k = this.way2(que, x, y, k);
			k = this.way0(que, x, y, k);
			k = this.way1(que, x, y, k);
		}
		if (drect == 2310) {
			k = this.way2(que, x, y, k);
			k = this.way3(que, x, y, k);
			k = this.way1(que, x, y, k);
			k = this.way0(que, x, y, k);
		}
		if (drect == 1203) {
			k = this.way1(que, x, y, k);
			k = this.way2(que, x, y, k);
			k = this.way0(que, x, y, k);
			k = this.way3(que, x, y, k);
		}
		if (drect == 2130) {
			k = this.way2(que, x, y, k);
			k = this.way1(que, x, y, k);
			k = this.way3(que, x, y, k);
			k = this.way0(que, x, y, k);
		}
		if (drect == 1023) {
			k = this.way1(que, x, y, k);
			k = this.way0(que, x, y, k);
			k = this.way2(que, x, y, k);
			k = this.way3(que, x, y, k);
		}
		if (drect == 0132) {
			k = this.way0(que, x, y, k);
			k = this.way1(que, x, y, k);
			k = this.way3(que, x, y, k);
			k = this.way2(que, x, y, k);
		}
		return k;
	}

	private void runQueueMethod(int x, int y) {// ���-�Ż�
		LinkedList<Integer> que = new LinkedList<Integer>();
		que.offer(x);
		que.offer(y);
		queue.offer(x);
		queue.offer(y);
		map[x][y] = 1;
		do {
			int n = 0;
			Integer[] temp = new Integer[que.size()];
			que.toArray(temp);
			while (true) {
				x = temp[n];
				y = temp[n + 1];
				int k = 0;
				if ((ex >= x && ey > y) || (ex > x && ey >= y)) {// ����1

					if ((ex - x) >= (ey - y))
						k = ways(3021, que, x, y, k);
					else
						k = ways(0312, que, x, y, k);

				}
				if ((ex >= x && ey < y) || (ex > x && ey <= y)) {// ����2
					if ((ex - x) >= (y - ey))
						k = ways(3201, que, x, y, k);
					else
						k = ways(2310, que, x, y, k);

				}
				if ((ex <= x && ey < y) || (ex < x && ey <= y)) {// ����3

					if ((x - ex) >= (y - ey))
						k = ways(1203, que, x, y, k);
					else
						k = ways(2130, que, x, y, k);

				}
				if ((ex <= x && ey > y) || (ex < x && ey >= y)) {// ����4
					if ((x - ex) >= (ey - y))
						k = ways(1023, que, x, y, k);
					else
						k = ways(0132, que, x, y, k);

				}

				if (keys == 1) {
					break;
				}
				if (k == 0 || (x == ex && y == ey)) {
					map[x][y] = 3;
					que.remove(n);
					que.remove(n);
					break;
				}
				y = que.pollLast();
				x = que.pollLast();
				que.offer(x);
				que.offer(y);
				queue.offer(x);
				queue.offer(y);

//				System.out.print(queue.toString());
//				System.out.println();
//				System.out.print(que.toString());
//				System.out.println();
//				this.draw();
			}
			n += 2;
			if (keys == 1) {
				break;
			}
		} while (!que.isEmpty());
	}

	private int refactor() {// ��ȷ���Ѱ·
		int num = queue.size();
		for (int i = 0; i < queue.size(); i++) {
			queue2.offer((Integer) queue.get(i));
		}
		int nx = ex;
		int ny = ey;
		int sy = queue2.pollLast();
		int sx = queue2.pollLast();
		do {
			if (judge(nx, ny, sx, sy)) {
				nx = sx;
				ny = sy;
				queue.offer(sx);
				queue.offer(sy);
				map[sx][sy] = 1;
			}
			sy = queue2.pollLast();
			sx = queue2.pollLast();
//			System.out.println(queue2.toString());
//			this.draw();
		} while (!queue2.isEmpty());
		map[sx][sy] = 1;
		return num;
	}

	private boolean judge(int nx, int ny, int sx, int sy) {// ��ȷ���Ѱ·�ж�
		if (nx == sx) {
			if (ny == sy + 1 || ny == sy - 1) {
				return true;
			}
		}
		if (ny == sy) {
			if (nx == sx + 1 || nx == sx - 1) {
				return true;
			}
		}
		return false;
	}

	int runBack() {// ���ݷ�
		queue3.clear();
		this.clear();
		runBackMethod(bx, by);
		best(queue3, map.length, map[0].length);
		return queue3.size();
	}
	
	int[][] getMapBest(){
		return  copyMapBest;
	}
	
	int[][] getMapBad(){
		return copyMapBad;
	}
	
	void runStack() {// ���-�Ż�����
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethod(bx, by);
	}

	void runStackNY() {// ���-δ�Ż�����
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethodNoY(bx, by);
	}

	int runQueue() {// ���-�Ż�����
		queue.clear();
		this.clear();
		keys = 0;
		runQueueMethod(bx, by);
		return this.refactor();
	}

	int runQueueNY() {// ���-δ�Ż�����
		queue.clear();
		this.clear();
		keys = 0;
		runQueueMethodNoY(bx, by);
		return this.refactor();
	}

	void choice(int c, int g, int bx, int by, int ex, int ey) {// ѡ����
		switch (c) {
		case 1:
			System.out.println("stack creat");
			this.stack();
			break;
		case 2:
			System.out.println("queue creat");
			this.queue(1);
			break;
		case 3:
			System.out.println("recur creat");
			this.recur();
			break;
		default:
			System.exit(0);
		}
		this.setPoint(bx, by, ex, ey);
		this.map[bx][by] = 8;
		this.map[ex][ey] = 8;
		switch (g) {
		case 1:
			System.out.println("stack run");
			this.runStack();
			break;
		case 2:
			System.out.println("stackNY run");
			this.runStackNY();
			break;
		case 3:
			System.out.println("queue run");
			this.runQueue();
			break;
		case 4:
			System.out.println("queueNY run");
			this.runQueueNY();
			break;
		case 5:
			System.out.println("back run");
			this.runBack();
			break;
		default:
			System.exit(0);
		}
	}

}