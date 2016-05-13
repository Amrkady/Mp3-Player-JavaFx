/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author alkady
 */
public class Mp_3 extends Application {
     GridPane pn=new GridPane();
   StackPane root = new StackPane();
     Label songTimer;
   Label time;
   ImageView imagePlay;
   Button play;
   Button forward;
    Slider volslider;
    Slider timeslider;
    public ListView<String> listview;
    private MediaPlayer mediaPlayer;
    private Media media;
    Duration duration;
    private static final double MIN_CHANGE = 0.5;
    ArrayList<String> arraylist;
    int count= 0, count2=0;

    @Override
    public void start(Stage primaryStage) {
     root.setMinSize(448, 320);
       Controller cl=new Controller();
        MenuBar menuBar = new MenuBar();
       Menu menu = new Menu();
       ImageView file = new ImageView(
                new Image("images/file.png"));
        file.setFitWidth(25);
        file.setFitHeight(25);
        menu.setGraphic (file);
        MenuItem openFile = new MenuItem("open file");
        
        menu.getItems().add(openFile);
        openFile.setOnAction(e -> cl.openFile());
        MenuItem openDurectory = new MenuItem("open Dir");
        menu.getItems().add(openDurectory);
//       openDurectory.setOnAction(e -> cl.openMultiFiles());

        menuBar.getMenus().add(menu);

        
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.setMaxSize(400, 20);
        menuBar.setStyle("-fx-background-color:#3e3737");//
        menuBar.setTranslateX(-20);
        menuBar.setTranslateY(-145);

        root.getChildren().add(menuBar);
         play = new Button();
//        play.setStyle("-fx-background-color:#3e3737");
//        play.setOnAction(e ->cl.play());
       
        play.setTranslateX(-128);
        play.setTranslateY(110);
         imagePlay = new ImageView(
                new Image("images/play.png"));
        imagePlay.setFitWidth(45);
        imagePlay.setFitHeight(45);
        play.setGraphic(imagePlay);
        root.getChildren().add(play);

//     
        ImageView imageStop = new ImageView(
                new Image("images/stop.png"));
        imageStop.setFitWidth(45);
        imageStop.setFitHeight(45);
//        stop.setGraphic(imageStop);
//        root.getChildren().add(stop);
///////////////////////////////////////////////////////////////////////
        
        
       // play = new Button();
        play.setStyle("-fx-background-color:#3e3737");
//        play.setOnAction(e ->
//        {
//            if(count2==1){
//            MediaPlayer.Status status = mediaPlayer.getStatus();
//
//    if (status == MediaPlayer.Status.PAUSED
//         || status == MediaPlayer.Status.READY
//         || status == MediaPlayer.Status.STOPPED) {
//
//           mediaPlayer.play();
//            play.setGraphic(imagePlay);
//
//                   } else {
//                   mediaPlayer.pause();
//                play.setGraphic(imageStop);
//           }
//            }
//        }//
       // );
        //////////////////////////////////////////////////////
        Button replay = new Button();
        replay.setStyle("-fx-background-color:#3e3737");
        //replay.setOnAction(e -> cl.replay());
        replay.setTranslateX(-72);
        replay.setTranslateY(110);
        ImageView imageReplay = new ImageView(
                new Image("images/replay.png"));
        imageReplay.setFitWidth(45);
        imageReplay.setFitHeight(45);
        replay.setGraphic(imageReplay);
        root.getChildren().add(replay);

        Button backword = new Button();
        backword.setStyle("-fx-background-color:#3e3737");
//        backword.setOnAction(e -> cl.back());
        backword.setTranslateX(-180);
        backword.setTranslateY(110);
        ImageView imageBackword = new ImageView(
                new Image("images/back.png"));
        imageBackword.setFitWidth(45);
        imageBackword.setFitHeight(45);
        backword.setGraphic(imageBackword);
        root.getChildren().add(backword);

         forward = new Button();
         forward.setStyle("-fx-background-color:#3e3737");
       // forward.setOnAction(e -> cl.forward());
        forward.setTranslateX(-15);
        forward.setTranslateY(110);
         ImageView imageForward = new ImageView(
                new Image("images/forward.png"));
        // imageForward.setStyle("-fx-background-color:gray");
        imageForward.setFitWidth(45);
        imageForward.setFitHeight(45);
        forward.setGraphic(imageForward);
        root.getChildren().add(forward);

        volslider = new Slider();
        volslider.setMaxSize(100, 10);
        volslider.setTranslateX(150);
        volslider.setTranslateY(110);
        root.getChildren().add(volslider);
        
         Label soundImage=new Label();
         soundImage.setTranslateX(75);
         soundImage.setTranslateY(110);
         
        ImageView imageSound = new ImageView(
                new Image("images/sound.png"));
        imageSound.setFitWidth(45);
        imageSound.setFitHeight(45);
        soundImage.setGraphic(imageSound);
        root.getChildren().add(soundImage);
        
        
         listview = new ListView<String>();
        listview.setMaxSize(450, 200);
        listview.setTranslateX(10);
        listview.setTranslateY(-20);
        listview.setStyle("-fx-background-color:darkgray");
        root.getChildren().add(listview);
         //    listPlay();
         
//        
        
        //hb.getChildren().add(root);
        //pn.add(root,0, 1);
        
         Label clockImage=new Label();
         clockImage.setTranslateX(-195);
         clockImage.setTranslateY(180);
        ImageView imageTime = new ImageView(
                new Image("images/clock.png"));
        imageTime.setFitWidth(40);
        imageTime.setFitHeight(40);
        clockImage.setGraphic(imageTime);
        
      // pn.add(clockImage, 0, 2);
        StackPane root2 = new StackPane();
        root.getChildren().add(clockImage);
   
        Label songtime=new Label();
        songtime.setTranslateX(145);
        songtime.setTranslateY(180);
        songtime.setText("/");
          ImageView imageTimer = new ImageView(
                new Image("images/slash.png"));
            imageTimer.setFitWidth(30);
            imageTimer.setFitHeight(30);
           songtime.setGraphic(imageTimer);
          root.getChildren().add(songtime);
        timeslider = new Slider();
        timeslider.setMaxSize(250, 10);
        timeslider.setTranslateX(-40);
        timeslider.setTranslateY(180);
        root.getChildren().add(timeslider);
        
         pn.setStyle("-fx-background-color:#3e3737");//221f1f//3e3737
       // pn.setStyle();
       /////////////////////////////////////////////////////////////////////////////////////////
         
         //time.setTextFill(Color.YELLOW);
         
           time =new Label();
        time.setStyle("-fx-background-color:gray");
               time.setScaleX(1.6);
               time.setScaleY(1.6);
             time.setTranslateX(135);
            time.setTranslateY(180);
            root.getChildren().add(time);
         // time.setPrefWidth(80);
 
        
         
         
          pn.add(root,0, 0);
        Scene scene = new Scene(pn, 460, 400);
         
        primaryStage.setTitle("mp3 player");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public class Controller {
   
    public void openFile() {
        count2=1;
        try{

        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3"));
        File chosen = fc.showOpenDialog(null);
        String path = chosen.getAbsolutePath();
        listview.getItems().clear();
        listview.getItems().add(chosen.getName());
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
      
//             volumeSlider();
//             timeSlider();
//              timer();
        }
        catch (Exception ex){}
       
    }

   
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
