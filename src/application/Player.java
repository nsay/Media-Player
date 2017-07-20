package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {

	Media		media;
	MediaPlayer	player;
	MediaView	view;
	Pane			mpane;
	PlayerBar	bar;
	
	public Player(String file) {
		
		media  = new Media(file);
		player = new MediaPlayer(media);
		view   = new MediaView(player);
		mpane  = new Pane();
		
		//Add view to pane & pane to the center of Player
		mpane.getChildren().add(view);
		setCenter(mpane);
		
		//Add PlayerBar to player
		bar = new PlayerBar(player);
		setBottom(bar);
		
		//Play
		player.play();
		
	}
}