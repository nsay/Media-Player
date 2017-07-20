package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Player player = new Player("file:///Users/Narith/Documents/Eclipse%20Projects/Media-Player/assets/sample.mp4");
		Scene scene = new Scene(player, 640, 400, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
