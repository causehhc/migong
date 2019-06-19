package test;

import java.awt.RenderingHints.Key;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//extends Application

public class Main extends Application {
	int i = 0, j = 0, f = 2;

	@Override
	public void start(Stage primaryStage) {

		GoMap map = new GoMap();
		int x = 237;
		int y = 397;
		map.setMap(x, y);
		map.stack();
//		map.queue();
		map.setPoint(2, 2, x - 3, y - 3);
		map.runStack();
//		map.runBack();
		System.out.println("Calculate complete!");

//        Group root = new Group();
		Pane root = new Pane();
		Scene scene = new Scene(root, 1900, 1000, Color.WHITE);

//        Rectangle rect = new Rectangle(190, 395, 20, 5); 
//        Rotate rot = new Rotate(0, 200, 200); 
//        rect.getTransforms().add(rot); 
//        root.getChildren().add(rect); 
//        Ellipse path = new Ellipse(200, 200, 200, 200); 
//        path.setStroke(Color.RED); 
//        path.setFill(null);
//        root.getChildren().add(path);

		root.setOnKeyPressed(e -> {

			switch (e.getCode()) {
			case ENTER:
				int yy = 0;
				while (yy++ < 3 * y) {
					Rectangle r = new Rectangle();
					r.setX(j * f);
					r.setY(i * f);
					r.setWidth(2 * f);
					r.setHeight(2 * f);
					if (map.trans()[i / f][j / f] == 0) {
						r.setFill(Color.BLACK);
					} else if (map.trans()[i / f][j / f] == 8) {
						r.setFill(Color.WHITE);
					} else if (map.trans()[i / f][j / f] == 1) {
						r.setFill(Color.RED);
					} else {
						r.setFill(Color.GREY);
					}
					root.getChildren().add(r);
					j += f;
					if (j >= y * f) {
						j = 0;
						i += f;
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

//        Timeline line = new Timeline(30); 
//	    KeyFrame key1 = new KeyFrame(
//	      new javafx.util.Duration(0), 
//	      new KeyValue(rot.angleProperty(), 0) 
//	    ); 
//	    KeyFrame key2 = new KeyFrame(
//	     new javafx.util.Duration(1000), 
//	     new KeyValue(rot.angleProperty(), 360) 
//	    ); 
//	    line.getKeyFrames().addAll(key1, key2); 
//	    scene.addEventHandler(MouseEvent.MOUSE_CLICKED, evt->{ 
//	     line.playFromStart(); 
//	    });

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