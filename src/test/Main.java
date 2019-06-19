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
	 * f=1:697,1497
	 * f=2:247,473
	 * f=3:109,209
	 */
	int k = 0, f = 2;
	Timeline action;
	@Override
	public void start(Stage primaryStage) {
		Scanner in=new Scanner(System.in);
		Pane root = new Pane();
		GoMap map = new GoMap();
		int x = 47;
		int y = 73;
		map.setMap(x, y);
		map.setPoint(2, 2, x-3, y-3);
		map.stack();
//		map.queue();
		map.runStack();
//		map.runStackNY();
//		map.runBack();
		
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
		System.out.println("Calculate complete!");

		Scene scene = new Scene(root, 1900, 1000, Color.WHITE);
		
		//зп
		Object[] aimArray = map.queue.toArray().clone();
		action = new Timeline(new KeyFrame(Duration.millis(1), e -> {
				if (k >= aimArray.length) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.count();
					System.out.println("OK!");
					System.exit(0);
				}
				int n = 0;
				while (n++ < 1) {
					Rectangle r = new Rectangle();
					int y1 = ((int) (aimArray[k++]) * f*f );
					int x1 = ((int) (aimArray[k++]) * f*f);
//					System.out.println(y1 + " " + x1);
					r.setX(x1);
					r.setY(y1);
					r.setWidth(f*f);
					r.setHeight(f*f);
					if (map.trans()[y1 / (f*f)][x1 / (f*f)] == 1) {
						r.setFill(Color.RED);
					} else {
						r.setFill(Color.GREY);
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
//		Scanner in=new Scanner(System.in);
//		GoMap map=new GoMap();
//		int i=17;
//		int j=67;
//		map.setMap(i, j);
//		
//		
//		map.stack();
////		map.recur();
////		map.queue();
//		map.print();
//		in.nextLine();
//		map.setPoint(2, 2,i-3,j-3);
////		map.runBack();
////		map.draw();
//		map.runStack();
//		map.print();
	}
}