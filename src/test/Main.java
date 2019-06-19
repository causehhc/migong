package test;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.application.Application;

//extends Application

public class Main extends Application {
	int k = 0, f = 1;

	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		GoMap map = new GoMap();
		int x = 237;
		int y = 397;
		map.setMap(x, y);
		map.setPoint(2, 2, x - 3, y - 3);

		map.stack();
//		map.queue();
		map.runStack();
//		map.runBack();
		Object[] aimArray = map.catchPoint().toArray().clone();
//		System.out.println(aimArray[0]);

		for (int i1 = 0; i1 < x * f; i1 += f) {
			for (int j1 = 0; j1 < y * f; j1 += f) {
				Rectangle r = new Rectangle();
				r.setX(j1 * f);
				r.setY(i1 * f);
				r.setWidth(2 * f);
				r.setHeight(2 * f);
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
		root.setOnKeyPressed(e -> {

			switch (e.getCode()) {
			case ENTER:
				int n = 0;
				while (n++ < 30) {
					Rectangle r = new Rectangle();
					r.setY((int) (aimArray[k++]) * f);
					r.setX((int) (aimArray[k++]) * f);
					r.setWidth(2 * f);
					r.setHeight(2 * f);
					r.setFill(Color.RED);
					root.getChildren().add(r);
					if (k == aimArray.length) {
//						System.exit(0);
					}
				}
				break;

			default:
				break;
			}

		});

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
//		map.stack();
////		map.recur();
////		map.queue();
//		
//		map.setPoint(2, 2,i-3,j-3);
////		map.runBack();
//		map.runStack();
	}
}