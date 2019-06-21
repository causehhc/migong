package test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

//extends Application
public class Main extends Application {
	GoMap map = new GoMap();
	Timeline action;
	int k = 0, f = 2;// TimeLine下标，放大倍率
	int[] rdm = new int[4];
	int winX = 1900, winY = 1000;
	//全文小写坐标均为倒置（历史遗留问题）
	int x = 33, y = 23;
	int xs=50,ys=0;

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
				FXCollections.observableArrayList("DFS_Stack", "DFS_Recall","BFS_Strong",  "BFS_Week", "Null"));
		Button btn1 = new Button("FRESH");
		Button btn2 = new Button("DFS-Vector");//优化版
		Button btn3 = new Button("DFS-Random");//未优化版
		Button btn8 = new Button("DFS-Recall");//半优化版
		Label lb1=new Label("sb");
		Label lb2=new Label("sb");
		
		root0.setTop(root1);
		root0.setLeft(root2);
		root0.setRight(root3);
		btn4.setTranslateX(0);
		btn5.setTranslateX(60);
		btn6.setTranslateX(120);
		btn7.setTranslateX(180);
		cb1.setTranslateX(250);
		btn1.setTranslateX(380);
		btn2.setTranslateX(480);
		btn3.setTranslateX(600);
		btn8.setTranslateX(740);
		lb1.setTranslateY(30);
		lb2.setTranslateY(50);
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

		Scene scene = new Scene(root0, winX, winY, Color.WHITE);
		map.setMap(x, y);
		map.setPoint(2, 2, x - 3, y - 3);
		this.draw(root2, ys, xs);
		this.draw(root3, -y * f * f, xs);
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
						try {
							map.setMap(x, y);
							this.remove(root2);
							this.remove(root3);
							map.recur();
							this.draw(root2, ys, xs);
							this.draw(root3, -y * f * f, xs);
						} catch (StackOverflowError e) {
							new AlertBox().display("Waring", "StackOverflow!");
							break;
						}
						break;
					}
					case 2: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.queue(1);//BFS_Strong
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}
					case 3: {
						map.setMap(x, y);
						this.remove(root2);
						this.remove(root3);
						map.queue(0);//BFS_week
						this.draw(root2, ys, xs);
						this.draw(root3, -y * f * f, xs);
						break;
					}
					
					default:
						map.setMap(x, y);
					}

				});
		btn1.setOnAction(e -> {
			this.remove(root2);
			this.remove(root3);
			this.draw(root2, ys, xs);
			this.draw(root3, -y * f * f, xs);
		});
		btn2.setOnAction(e -> {
			this.remove(root2);
			this.draw(root2, ys, xs);
			map.runStack();//DFS_Vector
			lb1.setText("回溯率:"+map.count());
			Object[] aimArray1 = map.queue.toArray().clone();
			this.animo(root2, 0, xs, aimArray1);
		});
		btn3.setOnAction(e -> {
			this.remove(root3);
			this.draw(root3, -y * f * f, xs);
			map.runStackNY();//DFS_Random
			lb2.setText("回溯率:"+map.count());
			Object[] aimArray2 = map.queue.toArray().clone();
			this.animo(root3, -y * f * f, xs, aimArray2);
		});
		btn8.setOnAction(e -> {
			try {
				this.remove(root3);
				this.draw(root3, -y * f * f, xs);
				map.runBack();//DFS_Recall
				lb2.setText("回溯率:"+map.count());
				Object[] aimArray3 = map.queue.toArray().clone();
				this.animo(root3, -y * f * f, xs, aimArray3);
			} catch (StackOverflowError e1) {
				new AlertBox().display("Waring", "StackOverflow!");
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}