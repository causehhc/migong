package test;

import java.util.LinkedList;
import java.util.Stack;

public class GoMap extends CreatMap {
	private int bx = -1;// 初始化入点x坐标
	private int by = -1;// 初始化入点y坐标
	private int ex = -1;// 初始化出点x坐标
	private int ey = -1;// 初始化出点y坐标
	private int keys = 0;// 生成结果判断标记
	LinkedList<Integer> queue;// 通用队列
	private LinkedList<Integer> queue2;// 广度寻路队列
	private LinkedList<int[][]> queue3;// 路径数组记录
	private int[][] copyMap;
	private int[][] copyMapBest;
	private int[][] copyMapBad;

	GoMap() {
		queue = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
		queue3 = new LinkedList<int[][]>();
	}

	void setPoint(int bx, int by, int ex, int ey) {// 设置入点和出点
		this.bx = bx;
		this.by = by;
		this.ex = ex;
		this.ey = ey;
		map[bx][by] = map[ex][ey] = 8;
	}

	private static int count(int[][] map) {// 计算1
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

	private void best(LinkedList<int[][]> queue, int x, int y) {//返回最短路径地图
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

	int[][] copy() {//记录路径地图
		copyMap = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	int count1() {// 深度优先效率计算
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

	int count2() {// 广度优先效率计算
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

	private boolean runBackMethod(int x, int y) {// 生成多条路径专用，回溯法，多路径演示
//		if (keys == 1) {
//			return false;
//		}
		if (x == ex && y == ey) {
			map[x][y] = 1;
			queue3.offer(this.copy());//queue3++
//			keys = 1;
			/*
			 * 解释
			 */
			
//			System.out.println("下图路径数:" + count(map));
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
			map[x][y] = 8;// 3一结果8多结果
		}
		return true;
	}

	private void runStackMethodNoY(int x, int y) {// 深度未优化
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
//						System.out.println("下图路径数:"+count(map));
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
//						System.out.println("下图路径数:"+count(map));
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
//						System.out.println("下图路径数:"+count(map));
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
//						System.out.println("下图路径数:"+count(map));
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

	private void runQueueMethodNoY(int x, int y) {// 广度未优化
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
//						System.out.println("下图路径数:" +count(map));
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
//						System.out.println("下图路径数:" +count(map));
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
//						System.out.println("下图路径数:" + count(map));
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
//						System.out.println("下图路径数:" + count(map));
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

	private int way0(Stack<Integer> st, int x, int y, int k) {// 深度子策略组
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
//				System.out.println("下图路径数:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way1(Stack<Integer> st, int x, int y, int k) {// 深度子策略组
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
//				System.out.println("下图路径数:"+this.count(map)());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way2(Stack<Integer> st, int x, int y, int k) {// 深度子策略组
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
//				System.out.println("下图路径数:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way3(Stack<Integer> st, int x, int y, int k) {// 深度子策略组
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
//				System.out.println("下图路径数:"+count(map));
//				this.print();
				break;
			}
		}
		return k;
	}

	private int ways(int drect, Stack<Integer> st, int x, int y, int k) {// 深度策略组
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

	private void runStackMethod(int x, int y) {// 深度-优化
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
				if ((ex >= x && ey > y) || (ex > x && ey >= y)) {// 策略1

					if ((ex - x) >= (ey - y))
						k = ways(3021, st, x, y, k);
					else
						k = ways(0312, st, x, y, k);

				}
				if ((ex >= x && ey < y) || (ex > x && ey <= y)) {// 策略2
					if ((ex - x) >= (y - ey))
						k = ways(3201, st, x, y, k);
					else
						k = ways(2310, st, x, y, k);

				}
				if ((ex <= x && ey < y) || (ex < x && ey <= y)) {// 策略3

					if ((x - ex) >= (y - ey))
						k = ways(1203, st, x, y, k);
					else
						k = ways(2130, st, x, y, k);

				}
				if ((ex <= x && ey > y) || (ex < x && ey >= y)) {// 策略4
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

	private int way0(LinkedList<Integer> que, int x, int y, int k) {// 广度子策略组
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
//				System.out.println("下图路径数:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way1(LinkedList<Integer> que, int x, int y, int k) {// 广度子策略组
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
//				System.out.println("下图路径数:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way2(LinkedList<Integer> que, int x, int y, int k) {// 广度子策略组
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
//				System.out.println("下图路径数:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int way3(LinkedList<Integer> que, int x, int y, int k) {// 广度子策略组
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
//				System.out.println("下图路径数:" + count(map));
//				this.print();
				keys = 1;
				break;
			}
		}
		return k;
	}

	private int ways(int drect, LinkedList<Integer> que, int x, int y, int k) {// 广度策略组
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

	private void runQueueMethod(int x, int y) {// 广度-优化
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
				if ((ex >= x && ey > y) || (ex > x && ey >= y)) {// 策略1

					if ((ex - x) >= (ey - y))
						k = ways(3021, que, x, y, k);
					else
						k = ways(0312, que, x, y, k);

				}
				if ((ex >= x && ey < y) || (ex > x && ey <= y)) {// 策略2
					if ((ex - x) >= (y - ey))
						k = ways(3201, que, x, y, k);
					else
						k = ways(2310, que, x, y, k);

				}
				if ((ex <= x && ey < y) || (ex < x && ey <= y)) {// 策略3

					if ((x - ex) >= (y - ey))
						k = ways(1203, que, x, y, k);
					else
						k = ways(2130, que, x, y, k);

				}
				if ((ex <= x && ey > y) || (ex < x && ey >= y)) {// 策略4
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

	private int refactor() {// 广度反向寻路
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

	private boolean judge(int nx, int ny, int sx, int sy) {// 广度反向寻路判断
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

	int runBack() {// 回溯法
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
	
	void runStack() {// 深度-优化调用
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethod(bx, by);
	}

	void runStackNY() {// 深度-未优化调用
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethodNoY(bx, by);
	}

	int runQueue() {// 广度-优化调用
		queue.clear();
		this.clear();
		keys = 0;
		runQueueMethod(bx, by);
		return this.refactor();
	}

	int runQueueNY() {// 广度-未优化调用
		queue.clear();
		this.clear();
		keys = 0;
		runQueueMethodNoY(bx, by);
		return this.refactor();
	}

	void choice(int c, int g, int bx, int by, int ex, int ey) {// 选择器
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