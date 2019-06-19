package test;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

//extends Application

public class Main extends Application {
	/**
	 * f=1:697,1497 f=2:247,473 f=3:109,209
	 */
	int k = 0, num = 0,f = 1;//数组队列下标，开始长度，放大倍率
	Timeline action;

	@Override
	public void start(Stage primaryStage) {
		Scanner in = new Scanner(System.in);
		Pane root = new Pane();
		GoMap map = new GoMap();
		int[] rdm = new int[4];
		int x = 247;
		int y = 473;
		rdm[0] = (int) (Math.random() * (x - 5)) + 2;
		rdm[2] = (int) (Math.random() * (x - 5)) + 2;
		rdm[1] = (int) (Math.random() * (y - 5)) + 2;
		rdm[3] = (int) (Math.random() * (y - 5)) + 2;
		/**
		 ** 1.stack 2.queue 3.recur 1.stack 2.staNY 3.back beginX beginY endX endY
		 */
		map.setMap(x, y);
//		map.choice(1, 1, 2, 2, x - 3, y - 3);
		map.choice(2, 2, x - 3, y - 3, 2, 2);
//		map.choice(1, 1, rdm[0], rdm[1], rdm[2], rdm[3]);
		System.out.println("Calculate complete!");

		for (int i1 = 0; i1 < x * f; i1 += f) {
			for (int j1 = 0; j1 < y * f; j1 += f) {
				Rectangle r = new Rectangle();
				r.setX(j1 * f);
				r.setY(i1 * f);
				r.setWidth(f * f);
				r.setHeight(f * f);
				if (map.trans()[i1 / f][j1 / f] == 0) {
					r.setFill(Color.BLACK);
				} else {
					r.setFill(Color.WHITE);
				}
				root.getChildren().add(r);
			}
		}
		System.out.println("Creat complete!");

		Scene scene = new Scene(root, 1900, 1000, Color.WHITE);

		// 走
		Object[] aimArray = map.queue.toArray().clone();
		action = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			if (k >= aimArray.length) {
				k=aimArray.length-4;
				root.setOnKeyPressed(e1 -> {
					switch (e1.getCode()) {
					case ENTER:{
						map.count();
						System.out.println("OK!");
//						System.exit(0);
						break;	
					}
					default:
						break;
					}
				});
			}
			int n = 0;
			while (n++ < 1) {
				Rectangle r = new Rectangle();
				int y1 = ((int) (aimArray[k++]) * f * f);
				int x1 = ((int) (aimArray[k++]) * f * f);
				r.setX(x1);
				r.setY(y1);
				r.setWidth(f * f);
				r.setHeight(f * f);
				if (map.trans()[y1 / (f * f)][x1 / (f * f)] == 1) {
					if (num <= 20) {
						num++;
						r.setFill(Color.CHARTREUSE);
					} else if (k >= aimArray.length - 60) {
						r.setFill(Color.CHARTREUSE);
					} else {
						r.setFill(Color.RED);
					}
				} else {
					r.setFill(Color.DARKGRAY);
				}
				root.getChildren().add(r);

			}
		}));
		action.setCycleCount(Timeline.INDEFINITE);
		action.play();

		primaryStage.setScene(scene);
		primaryStage.show();
		root.requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
//		Scanner in = new Scanner(System.in);
//		GoMap map = new GoMap();
//		int i = 17;
//		int j = 67;
//		map.setMap(i, j);
//		/**
//		 * 1.stack 2.queue 3.recur 1.stack 2.staNY 3.back beginX beginY endX endY
//		 */
//		map.choice(1, 1, (int) (Math.random() * (i - 5)) + 2, (int) (Math.random() * (j - 5)) + 2,
//				(int) (Math.random() * (i - 5)) + 2, (int) (Math.random() * (i - 5)) + 2);
//		map.print();
	}
}