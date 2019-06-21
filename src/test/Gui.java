package test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

//public class Main extends Application {
//Scanner in = new Scanner(System.in);
//GoMap map = new GoMap();
///**
// * f=1:697,1497 f=2:247,473 f=3:109,209 1.stack 2.queue 3.recur 1.stack 2.staNY
// * 3.back beginX beginY endX endY
// */
//int k = 0, f = 2, numOfRt = 0;// 数组队列下标，放大倍率
//Timeline action;
//int[] rdm = new int[4];
//int x = 37;
//int y = 73;
//int xs = 0;
//int ys = 50;
//
//void draw(Pane root) {
//	numOfRt = 0;
//	for (int i = 0; i < x * f; i += f) {
//		for (int j = 0; j < y * f; j += f) {
//			Rectangle r = new Rectangle();
//			r.setX(j * f + xs);
//			r.setY(i * f + ys);
//			r.setWidth(f * f);
//			r.setHeight(f * f);
//			if (map.trans()[i / f][j / f] == 0) {
//				r.setFill(Color.BLACK);
//			} else {
//				r.setFill(Color.WHITE);
//			}
//			root.getChildren().add(r);
//			numOfRt++;
//		}
//	}
//}
//
//void remove(Pane root) {
//	root.getChildren().removeAll();
//}
//
//@Override
//public void start(Stage primaryStage) {
//	Pane root = new Pane();
//	
//	
//	Button btn1 = new Button("FRESH");
//	Button btn2 = new Button("START");
//	Button btn3 = new Button("STOP");
//	ChoiceBox<String> cb1 = new ChoiceBox<String>(FXCollections.observableArrayList("深度优先算法", "广度优先算法", "回溯法"));
//	ChoiceBox<String> cb2 = new ChoiceBox<String>(FXCollections.observableArrayList("深度优先算法优化版", "深度优先算法", "回溯法"));
//
//	rdm[0] = (int) (Math.random() * (x - 5)) + 2;
//	rdm[2] = (int) (Math.random() * (x - 5)) + 2;
//	rdm[1] = (int) (Math.random() * (y - 5)) + 2;
//	rdm[3] = (int) (Math.random() * (y - 5)) + 2;
//
//	map.setPoint(2, 2, x - 3, y - 3);
//
//	cb1.setLayoutX(0);
//	cb2.setLayoutX(150);
//	btn1.setLayoutX(350);
//	btn2.setLayoutX(570);
//	btn3.setLayoutX(650);
//	root.getChildren().add(cb1);
//	root.getChildren().add(cb2);
//	root.getChildren().add(btn1);
//	root.getChildren().add(btn2);
//	root.getChildren().add(btn3);
//	Scene scene = new Scene(root, 1900, 1000, Color.WHITE);//
//
//	cb1.getSelectionModel().selectedIndexProperty()
//			.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
//				switch (new_val.intValue()) {
//				case 0: {
//					this.remove(root);
//					map.setMap(x, y);
//					map.stack();
//					this.draw(root);
//					break;
//				}
//				case 1: {
//					this.remove(root);
//					map.setMap(x, y);
//					map.queue();
//					this.draw(root);
//					break;
//				}
//				case 2: {
//					this.remove(root);
//					map.setMap(x, y);
//					try {
//						map.recur();
//					} catch (StackOverflowError e) {
//						System.out.println("StackOverflow");
//						break;
//					}
//					this.draw(root);
//					break;
//				}
//				default:
//					System.out.println("FuckYou");
//				}
//
//			});
//
//	cb2.getSelectionModel().selectedIndexProperty()
//			.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
//				switch (new_val.intValue()) {
//				case 0: {
//					map.runStack();
//					break;
//				}
//				case 1: {
//					map.runStackNY();
//					break;
//				}
//				case 2: {
//					try {
//						map.runBack();
//					} catch (StackOverflowError e) {
//						System.out.println("StackOverflow");
//						break;
//					}
//					break;
//				}
//				default:
//					System.out.println("FuckYou");
//				}
//			});
//
//	btn1.setOnAction(e -> {
//		this.remove(root);
//		this.draw(root);
//	});
//	btn2.setOnAction(e -> {
//		this.remove(root);
//		this.draw(root);
//		this.draw(root);
//		// 走
//		k = 0;
//		Object[] aimArray = map.queue.toArray().clone();
//		action = new Timeline(new KeyFrame(Duration.millis(1), e1 -> {
//			if (k >= aimArray.length) {
//				k = 0;
//				action.stop();
//			}
//			int n = 0;
//			while (n++ < 1) {
//				Rectangle r = new Rectangle();
//				int y1 = ((int) (aimArray[k++]) * f * f);
//				int x1 = ((int) (aimArray[k++]) * f * f);
//				r.setX(x1 + xs);
//				r.setY(y1 + ys);
//				r.setWidth(f * f);
//				r.setHeight(f * f);
//				if (map.trans()[y1 / (f * f)][x1 / (f * f)] == 1) {
//					if (k <= 20) {
//						r.setFill(Color.CHARTREUSE);
//					} else if (k >= aimArray.length - 20) {
//						r.setFill(Color.CHARTREUSE);
//					} else {
//						r.setFill(Color.RED);
//					}
//				} else {
//					r.setFill(Color.DARKGRAY);
//				}
//				root.getChildren().add(r);
//				numOfRt++;
//
//			}
//		}));
//		action.setCycleCount(Timeline.INDEFINITE);
//		action.play();
//	});
//	btn3.setOnAction(e -> {
//		action.stop();
//	});
//	root.setOnKeyPressed(e -> {
//		// 退
//		switch (e.getCode()) {
//		case ENTER: {
//			map.count();
//			System.out.println("OK!");
//			System.exit(0);
//			break;
//		}
//		default:
//			break;
//		}
//	});
//
//	primaryStage.setScene(scene);
//	primaryStage.show();
//}
