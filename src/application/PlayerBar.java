package application;


import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class PlayerBar extends HBox {

	Button 		playButton 	= new Button("||");
	Label  		volumeLabel 	= new Label("Volume: ");
	Slider 		time 		= new Slider();
	Slider 		volume 		= new Slider();
	MediaPlayer 	player;
	
	public PlayerBar(MediaPlayer play) {
		
		player = play;
		
		//Positioning MediaPlayer
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5,5,6,5));
		
		//Set sliders widths
		volume.setPrefWidth(80);
		volume.setValue(100);
		HBox.setHgrow(time, Priority.ALWAYS);
		
		//Set static size for playButton
		playButton.setPrefWidth(30);
		
		//Add buttons, sliders & labels to PlayerBar itself
		getChildren().add(playButton);
		getChildren().add(time);
		getChildren().add(volumeLabel);
		getChildren().add(volume);
		
		//Add playButton actions for play & pause
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				Status status = player.getStatus();
				
				if(status == Status.PLAYING) {
					//Reset video once it pasts the end
					if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
						player.seek(player.getStartTime());
						player.play();
					}
					//Pause video at any other location
					else {
						player.pause();
						playButton.setText(">");
					}
				}
				
				//Change button to play if not playing
				if(status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
					player.play();
					playButton.setText("||");
				}
			}
		});
		
		//Update time slider with current time
		player.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				Platform.runLater(new Runnable() {
					//Function to update time value
					public void run() {
						time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);	
					}
				});
			}
		});	
		

		//Update video with current time
		time.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if(time.isPressed()) {
					player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
				}
			}
		});
		
		//Update volume slider with current volume
		volume.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if(volume.isPressed()) {
					player.setVolume(volume.getValue()/100);
				}
			}
		});
		
	}
}
