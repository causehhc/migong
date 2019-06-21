package test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

//extends Application
public class Main extends Application {
	Scanner in = new Scanner(System.in);
	GoMap map = new GoMap();
	Timeline action;
	int k = 0, f = 2;// 数组队列下标，放大倍率
	int[] rdm = new int[4];
	int winX = 1900, winY = 1000;
	int x = 33, y = 23;

	void draw(GridPane root, int xs, int ys) {
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

	void remove(GridPane root) {
		root.getChildren().clear();
	}

	void animo(GridPane root, int xs, int ys, Object[] aimArray) {
		action = new Timeline(new KeyFrame(Duration.millis(1), e1 -> {
			if (k >= aimArray.length) {
				// TODO
				k = 0;
				action.stop();
			}
			int n = 0;
			while (n++ < 1) {
				Rectangle r = new Rectangle();
				int y1 = ((int) (aimArray[k++]) * f * f);
				int x1 = ((int) (aimArray[k++]) * f * f);
				r.setTranslateX(x1 + xs);
				r.setTranslateY(y1 + ys);
				r.setWidth(f * f);
				r.setHeight(f * f);
				if (map.trans()[y1 / (f * f)][x1 / (f * f)] == 1) {
					if (k <= 20) {
						r.setFill(Color.CHARTREUSE);
					} else if (k >= aimArray.length - 20) {
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
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane root0 = new BorderPane();
		GridPane root1 = new GridPane();
		GridPane root2 = new GridPane();
		GridPane root3 = new GridPane();
		root0.setTop(root1);
		root0.setLeft(root2);
		root0.setRight(root3);

		Button btn4 = new Button("X++");
		Button btn5 = new Button("X--");
		Button btn6 = new Button("Y++");
		Button btn7 = new Button("Y--");
		ChoiceBox<String> cb1 = new ChoiceBox<String>(
				FXCollections.observableArrayList("深度优先算法", "广度优先算法", "回溯法", "NULL"));
		Button btn1 = new Button("FRESH");
		Button btn2 = new Button("START1");//优化版
		Button btn3 = new Button("START2");//未优化版
		rdm[0] = (int) (Math.random() * (x - 5)) + 2;
		rdm[2] = (int) (Math.random() * (x - 5)) + 2;
		rdm[1] = (int) (Math.random() * (y - 5)) + 2;
		rdm[3] = (int) (Math.random() * (y - 5)) + 2;

		btn4.setTranslateX(0);
		btn5.setTranslateX(60);
		btn6.setTranslateX(120);
		btn7.setTranslateX(180);
		cb1.setTranslateX(280);
		btn1.setTranslateX(480);
		btn2.setTranslateX(580);
		btn3.setTranslateX(680);
		root1.getChildren().add(btn4);
		root1.getChildren().add(btn5);
		root1.getChildren().add(btn6);
		root1.getChildren().add(btn7);
		root1.getChildren().add(cb1);
		root1.getChildren().add(btn1);
		root1.getChildren().add(btn2);
		root1.getChildren().add(btn3);

		Scene scene = new Scene(root0, winX, winY, Color.WHITE);//

		map.setMap(x, y);
		map.setPoint(2, 2, x - 3, y - 3);
		this.draw(root2, 0, 25);
		this.draw(root3, -y * f * f, 25);
		btn4.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			y += 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, 0, 25);
			this.draw(root3, -y * f * f, 25);
		});
		btn5.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			y -= 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, 0, 25);
			this.draw(root3, -y * f * f, 25);
		});
		btn6.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			x += 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, 0, 25);
			this.draw(root3, -y * f * f, 25);
		});
		btn7.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			x -= 20;
			map.setMap(x, y);
			map.setPoint(2, 2, x - 3, y - 3);
			this.draw(root2, 0, 25);
			this.draw(root3, -y * f * f, 25);
		});

		cb1.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					switch (new_val.intValue()) {
					case 0: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.stack();
						this.draw(root2, 0, 25);
						this.draw(root3, -y * f * f, 25);
						break;
					}
					case 1: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.queue();
						this.draw(root2, 0, 25);
						this.draw(root3, -y * f * f, 25);
						break;
					}
					case 2: {
						try {
							map.setMap(x, y);
							this.remove(root2);
							this.remove(root3);
							map.recur();
							this.draw(root2, 0, 25);
							this.draw(root3, -y * f * f, 25);
						} catch (StackOverflowError e) {
							System.out.println("StackOverflow");
							break;
						}
						break;
					}
					default:
						map.setMap(x, y);
					}

				});

		btn1.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			this.draw(root2, 0, 25);
			this.draw(root3, -y * f * f, 25);
		});
		btn2.setOnAction(e -> {
			this.remove(root2);
			this.draw(root2, 0, 25);
			map.runStack();
			Object[] aimArray1 = map.queue.toArray().clone();
			this.animo(root2, 0, 25, aimArray1);

		});
		btn3.setOnAction(e -> {
			this.remove(root3);
			this.draw(root3, -y * f * f, 25);
			map.runStackNY();
			Object[] aimArray2 = map.queue.toArray().clone();
			this.animo(root3, -y * f * f, 25, aimArray2);
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}