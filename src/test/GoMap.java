package test;

import java.util.LinkedList;
import java.util.Stack;

public class GoMap extends CreatMap {
	private int bx = -1;
	private int by = -1;
	private int ex = -1;
	private int ey = -1;
	private int keyb = 0;
	private int keys = 0;
	LinkedList<Integer> queue;

	GoMap() {
		queue = new LinkedList<Integer>();
	}

	void setPoint(int bx, int by, int ex, int ey) {
		this.bx = bx;
		this.by = by;
		this.ex = ex;
		this.ey = ey;
	}

	double count() {
		int n1 = 0, n3 = 0, n0 = 0, n8 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					n1++;
				}
				if (map[i][j] == 3) {
					n3++;
				}
				if (map[i][j] == 0) {
					n0++;
				}
				if (map[i][j] == 8) {
					n8++;
				}
			}
		}
		return ((double) n3 / n1);
	}

	int count1() {
		int n = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					n++;
				}
			}
		}
		return n;
	}

	private boolean runBackMethod(int x, int y) {// 無效優化
		this.clear();
		queue.clear();
		keys = 0;

		if (keyb == 1) {
			return false;
		}
		if (x == ex && y == ey) {
			keyb = 1;
			map[x][y] = 1;
//			System.out.println("下图路径数:"+this.count1());
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
			if (keyb == 1) {
				return false;
			}
			map[x][y] = 3;
			queue.pollLast();
			queue.pollLast();
		}
		return true;
	}

	private int way0(Stack<Integer> st, int x, int y, int k) {
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
				keys = 1;
//				System.out.println("下图路径数:"+this.count1());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way1(Stack<Integer> st, int x, int y, int k) {
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
				keys = 1;
//				System.out.println("下图路径数:"+this.count1());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way2(Stack<Integer> st, int x, int y, int k) {
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
				keys = 1;
//				System.out.println("下图路径数:"+this.count1());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int way3(Stack<Integer> st, int x, int y, int k) {
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
				keys = 1;
//				System.out.println("下图路径数:"+this.count1());
//				this.print();
				break;
			}
		}
		return k;
	}

	private int ways(int drect, Stack<Integer> st, int x, int y, int k) {// 策略组
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

	private void runStackMethod(int x, int y) {// 相對最優解
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

	private void runStackMethodNoY(int x, int y) {
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
//				this.draw(x,y);
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
//						System.out.println("下图路径数:"+this.count());
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
//						System.out.println("下图路径数:"+this.count());
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
//						System.out.println("下图路径数:"+this.count());
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
//						System.out.println("下图路径数:"+this.count());
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

	void runBack() {
		queue.clear();
		this.clear();
		keys = 0;
		runBackMethod(bx, by);
	}

	void runStack() {
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethod(bx, by);
	}

	void runStackNY() {
		queue.clear();
		this.clear();
		keys = 0;
		runStackMethodNoY(bx, by);
	}

	void choice(int c, int g, int bx, int by, int ex, int ey) {
		switch (c) {
		case 1:
			this.stack();
			System.out.println("stack creat");
			break;
		case 2:
			this.queue(1);
			System.out.println("queue creat");
			break;
		case 3:
			this.recur();
			System.out.println("recur creat");
			break;
		default:
			System.out.println("FuckYou");
			System.exit(0);
		}
		this.setPoint(bx, by, ex, ey);
		this.map[bx][by] = 8;
		this.map[ex][ey] = 8;
		switch (g) {
		case 1:
			this.runStack();
			System.out.println("stack run");
			break;
		case 2:
			this.runStackNY();
			System.out.println("stackNY run");
			break;
		case 3:
			this.runBack();
			System.out.println("back run");
			break;
		default:
			System.out.println("FuckYou");
			System.exit(0);
		}
	}

}