package test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class oldMain0 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	GoMap map=new GoMap();
    	int i=247;
		int j=477;
    	
		map.setMap(i, j);
//		map.stack();
//		map.queue();
		map.setPoint(2, 2, i-3, j-3);
		map.runMap();
		
        primaryStage.setTitle("Title");
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720, Color.WHITE);
        
        for(int i1=0;i1<i*2;i1+=2) {
        	for(int j1=0;j1<j*2;j1+=2) {
        		Rectangle r = new Rectangle();
	        	r.setX(j1*2);
			    r.setY(i1*2);
//			    if(map.trans()[i1/2][j1/2]==1) {
//			    	r.setWidth(3);
//				    r.setHeight(3);
//			    }
//			    else {
			    	r.setWidth(4);
				    r.setHeight(4);	
//			    }
			    if(map.trans()[i1/2][j1/2]==0) {
			    	r.setFill(Color.BLACK);
			    }
			    else if(map.trans()[i1/2][j1/2]==8){
			    	r.setFill(Color.WHITE);
			    }
			    else {
			    	r.setFill(Color.RED);
			    }
			    root.getChildren().add(r);
        	}
        	
        }
        
        
        

        

        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

