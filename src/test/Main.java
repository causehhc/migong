package test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

//import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

//extends Application
public class Main {
	GoMap map = new GoMap();
	Timeline action;
	int k = 0, f = 2;// TimeLine下标，默认放大倍率为2
	int winX = 1900, winY = 1000;// stage大小
	int x = 103, y = 103;// 迷宫大小
	int xs = 25, ys = 0;// 迷宫偏移量
/*
	void draw(GridPane root, int xs, int ys) {// 展示Pane
		try {
			for (int i = 0; i < x * f; i += f) {
				for (int j = 0; j < y * f; j += f) {
					Rectangle r = new Rectangle();
					r.setTranslateX(j * f + xs);
					r.setTranslateY(i * f + ys);
					r.setWidth(f * f);
					r.setHeight(f * f);
					if (map.trans()[i / f][j / f] == 0) {
						r.setFill(Color.BLACK);
					} else {
						r.setFill(Color.WHITE);
					}
					root.getChildren().add(r);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("fuck");
		}
	}

	void remove(GridPane root) {// 清空节点
		root.getChildren().clear();
	}

	void animo(GridPane root, int xs, int ys, Object[] aimArray, int key, int num) {// 时间轴动画
		action = new Timeline(new KeyFrame(Duration.millis(1), e1 -> {
			if (k >= aimArray.length) {
				// TODO
				k = 0;
				action.stop();
			}
			Rectangle r = new Rectangle();
			int y1 = ((int) (aimArray[k++]) * f * f);
			int x1 = ((int) (aimArray[k++]) * f * f);
			r.setTranslateX(x1 + xs);
			r.setTranslateY(y1 + ys);
			r.setWidth(f * f);
			r.setHeight(f * f);
			if (k < 3) {
				r.setFill(Color.RED);// 起点为红色

			} else if (k > aimArray.length - 1) {
				r.setFill(Color.RED);// 终点为红色
			} else if (map.trans()[y1 / (f * f)][x1 / (f * f)] == 1) {
				if (k == 0) {
					r.setFill(Color.CHARTREUSE);// 路径为绿色
				} else {
					if (k < num + 2) {
						r.setFill(Color.DARKGRAY);// 广度开始遍历时为灰色
					} else if (k == num + 2) {
						r.setFill(Color.RED);// 广度遍历结束为红色
					} else {
						r.setFill(Color.CHARTREUSE);// 广度反向寻路为绿色
					}
				}
			} else {
				r.setFill(Color.DARKGRAY);// 深度无效路径为灰色
			}
			root.getChildren().add(r);

		}));
		action.setCycleCount(Timeline.INDEFINITE);
		action.play();
	}

	@Override
	public void start(Stage primaryStage) {

		// 定义按钮
		BorderPane root0 = new BorderPane();
		GridPane root1 = new GridPane();
		GridPane root2 = new GridPane();
		GridPane root3 = new GridPane();
		Button btn4 = new Button("X++");
		Button btn5 = new Button("X--");
		Button btn6 = new Button("Y++");
		Button btn7 = new Button("Y--");
		ChoiceBox<String> cb1 = new ChoiceBox<String>(
				FXCollections.observableArrayList("DFS_Stack", "DFS_Recall", "BFS_Strong", "BFS_Week", "Null"));
		Button btn1 = new Button("FRESH");
		Button btn2 = new Button("DFS-Vector");// dfs优化版
		Button btn3 = new Button("DFS-Random");// dfs未优化版
		Button btn8 = new Button("BFS-Vector");// bfs优化版
		Button btn11 = new Button("BFS-Random");// bfs未优化版
		Label lb1 = new Label("计算量: ");
		Label lb2 = new Label("计算量: ");
		CheckBox checkBox = new CheckBox("Random");
		Button btn9 = new Button("+");
		Button btn10 = new Button("-");

		// 设置按钮位置并加入节点
		root0.setTop(root1);
		root0.setLeft(root2);
		root0.setRight(root3);
		btn4.setTranslateX(0);
		btn5.setTranslateX(60);
		btn6.setTranslateX(120);
		btn7.setTranslateX(180);
		btn9.setTranslateX(230);
		btn10.setTranslateX(270);
		cb1.setTranslateX(350);
		btn1.setTranslateX(480);
		btn2.setTranslateX(580);
		btn3.setTranslateX(690);
		btn8.setTranslateX(810);
		btn11.setTranslateX(920);
		lb1.setTranslateX(0);
		lb1.setTranslateY(25);
		lb2.setTranslateX(1000);
		lb2.setTranslateY(25);
		checkBox.setTranslateX(1070);
		root1.getChildren().add(btn4);
		root1.getChildren().add(btn5);
		root1.getChildren().add(btn6);
		root1.getChildren().add(btn7);
		root1.getChildren().add(cb1);
		root1.getChildren().add(btn1);
		root1.getChildren().add(btn2);
		root1.getChildren().add(btn3);
		root1.getChildren().add(btn8);
		root1.getChildren().add(lb1);
		root1.getChildren().add(lb2);
		root1.getChildren().add(checkBox);
		root1.getChildren().add(btn9);
		root1.getChildren().add(btn10);
		root1.getChildren().add(btn11);

		// 初始化地图
		Scene scene = new Scene(root0, winX, winY, Color.WHITE);
		map.setMap(x, y);
		map.setPoint(2, 2, x - 3, y - 3);
		this.draw(root2, ys, xs);
		this.draw(root3, -y * f * f, xs);

		// 按钮事件
		btn4.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			y += 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn5.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			y -= 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn6.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			x += 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn7.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			x -= 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn9.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			f++;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn10.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			f--;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		cb1.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					switch (new_val.intValue()) {
					case 0: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.stack();
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}
					case 1: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.recur();
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}
					case 2: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.queue(1);// BFS_Strong
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}
					case 3: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.queue(0);// BFS_week
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}

					default:
						map.setMap(x, y);
					}

				});

		btn1.setOnAction(e -> {
			action.stop();
			map.clear();
			map.queue.clear();
			this.remove(root2);
			this.remove(root3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});

		btn2.setOnAction(e -> {
			this.remove(root2);
			this.draw(root2, ys, xs);
			k = 0;
			map.clear();
			map.runStack();// DFS_Vector
			lb1.setText("计算量: " + map.count1());
			Object[] aimArray1 = map.queue.toArray().clone();
			this.animo(root2, 0, xs, aimArray1, 0, 0);
		});

		btn3.setOnAction(e -> {
			this.remove(root2);
			this.draw(root2, ys, xs);
			k = 0;
			map.clear();
			map.runStackNY();// DFS_Random
			lb1.setText("计算量: " + map.count1());
			Object[] aimArray2 = map.queue.toArray().clone();
			this.animo(root2, 0, xs, aimArray2, 0, 0);
		});

		btn8.setOnAction(e -> {
			this.remove(root3);
			this.draw(root3, -y * f * f, xs);
			k = 0;
			map.clear();
			int num = map.runQueue();// BFS_Vctor
			lb2.setText("计算量: " + map.count2());
			Object[] aimArray3 = map.queue.toArray().clone();
			this.animo(root3, -y * f * f, xs, aimArray3, 1, num);
		});

		btn11.setOnAction(e -> {
			this.remove(root3);
			this.draw(root3, -y * f * f, xs);
			k = 0;
			map.clear();
			int num = map.runQueueNY();// BFS_Random
			lb2.setText("计算量: " + map.count2());
			Object[] aimArray4 = map.queue.toArray().clone();
			this.animo(root3, -y * f * f, xs, aimArray4, 1, num);

		});

		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				if (checkBox.isSelected()) {
					int[] rdm = new int[4];
					rdm[0] = (int) (Math.random() * (x - 5) + 2);
					rdm[2] = (int) (Math.random() * (x - 5) + 2);
					rdm[1] = (int) (Math.random() * (y - 5) + 2);
					rdm[3] = (int) (Math.random() * (y - 5) + 2);
					map.clear();
					map.setPoint(rdm[0], rdm[1], rdm[2], rdm[3]);
				} else {
					map.clear();
					map.setPoint(2, 2, x - 3, y - 3);
				}
			}
		});

		// showstage
		primaryStage.setScene(scene);
		primaryStage.show();
	}
*/
	public static void main(String[] args) {
//		launch(args);
		GoMap obj=new GoMap();
		int x=17,y=67;
		obj.setMap(x, y);
		obj.choice(1, 1, 2, 2, x-3, y-3);
		obj.print();
	}
}
