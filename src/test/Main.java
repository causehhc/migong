package test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Scanner;
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
	/**
	 * f=1:697,1497 f=2:247,473 f=3:109,209 1.stack 2.queue 3.recur 1.stack 2.staNY
	 * 3.back beginX beginY endX endY
	 */
	int k = 0 ,f = 2, numOfRt = 0;// 数组队列下标，放大倍率
	Timeline action;
	int[] rdm = new int[4];
	int x = 37;
	int y = 73;
	int xs = 0;
	int ys = 50;

	void draw(Pane root) {
		numOfRt = 0;
		for (int i = 0; i < x * f; i += f) {
			for (int j = 0; j < y * f; j += f) {
				Rectangle r = new Rectangle();
				r.setX(j * f + xs);
				r.setY(i * f + ys);
				r.setWidth(f * f);
				r.setHeight(f * f);
				if (map.trans()[i / f][j / f] == 0) {
					r.setFill(Color.BLACK);
				} else {
					r.setFill(Color.WHITE);
				}
				root.getChildren().add(r);
				numOfRt++;
			}
		}
	}

	void remove(Pane root) {
		root.getChildren().remove(5, 5 + numOfRt);
	}
	
	void clear() {
		for (int i = 0; i < map.trans().length; i++) {
			for (int j = 0; j < map.trans()[0].length; j++) {
				if(map.trans()[i][j]==1||map.trans()[i][j]==3) {
					map.trans()[i][j]=8;
				}
			}
		}
	}

	@Override
	public void start(Stage primaryStage) {

		Pane root = new Pane();

		Button btn1 = new Button("FRESH");
		Button btn2 = new Button("START");
		Button btn3 = new Button("STOP");
		ChoiceBox<String> cb1 = new ChoiceBox<String>(FXCollections.observableArrayList("深度优先算法", "广度优先算法", "回溯法"));
		ChoiceBox<String> cb2 = new ChoiceBox<String>(FXCollections.observableArrayList("深度优先算法优化版", "深度优先算法", "回溯法"));

		rdm[0] = (int) (Math.random() * (x - 5)) + 2;
		rdm[2] = (int) (Math.random() * (x - 5)) + 2;
		rdm[1] = (int) (Math.random() * (y - 5)) + 2;
		rdm[3] = (int) (Math.random() * (y - 5)) + 2;

		map.setPoint(2, 2, x - 3, y - 3);

		cb1.setLayoutX(0);
		cb1.setLayoutY(0);
		cb2.setLayoutX(150);
		cb2.setLayoutY(0);
		btn1.setLayoutX(350);
		btn1.setLayoutY(0);
		btn2.setLayoutX(570);
		btn2.setLayoutY(0);
		btn3.setLayoutX(650);
		btn3.setLayoutY(0);
		root.getChildren().add(cb1);
		root.getChildren().add(cb2);
		root.getChildren().add(btn1);
		root.getChildren().add(btn2);
		root.getChildren().add(btn3);
		Scene scene = new Scene(root, 1900, 1000, Color.WHITE);//

		cb1.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					switch (new_val.intValue()) {
					case 0: {
						this.remove(root);
						map.setMap(x, y);
						map.stack();
						this.draw(root);
						break;
					}
					case 1: {
						this.remove(root);
						map.setMap(x, y);
						map.queue();
						this.draw(root);
						break;
					}
					case 2: {
						this.remove(root);
						map.setMap(x, y);
						map.recur();
						this.draw(root);
						break;
					}
					default:
						System.out.println("FuckYou");
					}

				});

		cb2.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					switch (new_val.intValue()) {
					case 0: {
						this.remove(root);
						this.clear();
						this.draw(root);
						map.queue.clear();
						map.draw();
						map.runStack();
						map.draw();
						break;
					}
					case 1: {
						this.remove(root);
						this.clear();
						this.draw(root);
						map.queue.clear();
						map.draw();
						map.runStackNY();
						map.draw();
						break;
					}
					case 2: {
						this.remove(root);
						this.clear();
						this.draw(root);
						map.queue.clear();
						map.draw();
						map.runBack();
						map.draw();
						break;
					}
					default:
						System.out.println("FuckYou");
					}
				});
		
		btn1.setOnAction(e -> {
			this.remove(root);
			this.draw(root);
		});
		btn2.setOnAction(e -> {
			// 走
			Object[] aimArray =map.queue.toArray().clone();
			action = new Timeline(new KeyFrame(Duration.millis(1), e1 -> {
				if (k >= aimArray.length) {
					k=0;
					action.stop();
				}
				int n = 0;
				while (n++ < 1) {
					Rectangle r = new Rectangle();
					int y1 = ((int) (aimArray[k++]) * f * f);
					int x1 = ((int) (aimArray[k++]) * f * f);
					r.setX(x1 + xs);
					r.setY(y1 + ys);
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
					numOfRt++;

				}
			}));
			action.setCycleCount(Timeline.INDEFINITE);
			action.play();
		});
		btn3.setOnAction(e->{
			action.stop();
		});
		root.setOnKeyPressed(e -> {
			// 退
			switch (e.getCode()) {
			case ENTER: {
				map.count();
				System.out.println("OK!");
				System.exit(0);
				break;
			}
			default:
				break;
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
//		Scanner in = new Scanner(System.in);
//		GoMap map = new GoMap();
//		int i = 27;
//		int j = 97;
//		map.setMap(i, j);
//		/**
//		 * 1.stack 2.queue 3.recur 1.stack 2.staNY 3.back beginX beginY endX endY
//		 */
//		map.choice(1, 1,2,2,i-3,j-3);
//		map.print();
	}
}