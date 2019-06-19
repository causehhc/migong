package test;

import java.util.LinkedList;
import java.util.Stack;

public class GoMap extends CreatMap {
	private int bx = -1;
	private int by = -1;
	private int ex = -1;
	private int ey = -1;
	private int keyb = 0;

	GoMap() {

	}

	void setPoint(int bx, int by, int ex, int ey) {
		this.bx = bx;
		this.by = by;
		this.ex = ex;
		this.ey = ey;
	}

	private int count() {
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

	private boolean pathMapMethod(int x, int y) {
		if (keyb == 1) {
			return false;
		}
		if (x == ex && y == ey) {
			keyb = 1;
			map[x][y] = 1;
//			System.out.println("下图路径数:"+this.count());
//			this.print();
		}
		if (map[x][y] == 8) {
			map[x][y] = 1;
			if (pathMapMethod(x, y + 1)) {
				if (pathMapMethod(x - 1, y)) {
					if (pathMapMethod(x, y - 1)) {
						pathMapMethod(x + 1, y);
					}
				}
			}
			if (keyb == 1) {
				return false;
			}
			map[x][y] = 3;
		}
//		this.draw(x,y);
		return true;
	}

	private void runStackMethod(int x, int y) {
		Stack<Integer> st = new Stack<Integer>();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		st.push(y);
		st.push(x);
		int key = 0;// 解个数控制
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
						key = 1;
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
						key = 1;
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
						key = 1;
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
						key = 1;
						break;
					}
				}
				if (key == 1) {
					break;
				}
				if (k == 0 || (x == ex && y == ey)) {
					map[x][y] = 3;
					st.pop();
					st.pop();
					break;
				}
			}
			if (key == 1) {
				break;
			}
		} while (!st.empty());
	}

	void runBack() {
		pathMapMethod(bx, by);
//		this.draw();
	}

	void runStack() {
		runStackMethod(bx, by);
//		this.draw();
	}
}