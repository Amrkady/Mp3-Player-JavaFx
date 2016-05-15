package mp3;
import java.io.File;
import static java.lang.Math.floor;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JTable;
/**
* The Mp_3  class extends from Application
* and it contain all design of the project
*and contain start function.
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
    int count= 0, count2=0,count3=0;
    boolean flag=true;
      private Listplay ls; 
/**
* This function 
*/    
    @Override
    public void start(Stage primaryStage) {
          ls=new Listplay ();
        //HBox hb=new HBox();
        Controller cl=new Controller();
                
        
        root.setMinSize(448, 380);

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
        openDurectory.setOnAction(e -> cl.openDirectory());

        menuBar.getMenus().add(menu);

        
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.setMaxSize(50, 40);
        menuBar.setStyle("-fx-background-color:#ff8c00");//#3e3737
        menuBar.setTranslateX(-200);
        menuBar.setTranslateY(-170);
       root.getChildren().add(menuBar);
         play = new Button();

       
        play.setTranslateX(-128);
        play.setTranslateY(130);
         imagePlay = new ImageView(
                new Image("images/play.png"));
        imagePlay.setFitWidth(45);
        imagePlay.setFitHeight(45);
        play.setGraphic(imagePlay);
        root.getChildren().add(play);

        //////////////////////////////
         Button viewlist = new Button();
        ImageView imageviewlist = new ImageView(
                new Image("images/List.png"));
        imageviewlist.setFitWidth(25);
        imageviewlist.setFitHeight(30);
        viewlist.setGraphic(imageviewlist);
        
        viewlist.setStyle("-fx-background-color:#ff8c00");
       
          viewlist.setTranslateX(-61);
          viewlist.setTranslateY(-170);
          
         root.getChildren().add(viewlist);
         /////////////////////////
          Button removeSong = new Button();
        ImageView removeimage = new ImageView(
                new Image("images/delete.png"));
        removeimage.setFitWidth(30);
        removeimage.setFitHeight(30);
        removeSong.setGraphic(removeimage);
        
        removeSong.setStyle("-fx-background-color:#ff8c00");
         
          removeSong.setTranslateX(-105);
          removeSong.setTranslateY(-170);
          
         root.getChildren().add(removeSong);
         removeSong.setOnAction(e ->
        { 
            cl.removesong();
        });
        ////////////////
        Button add = new Button();
        ImageView imageadd = new ImageView(
                new Image("images/add.png"));
        imageadd.setFitWidth(25);
        imageadd.setFitHeight(30);
        add.setGraphic(imageadd);
        
        add.setStyle("-fx-background-color:#ff8c00");
         add.setOnAction(e -> cl.add());
          add.setTranslateX(-150);
          add.setTranslateY(-170);
          
         root.getChildren().add(add);
        ImageView imageStop = new ImageView(
                new Image("images/stop.png"));
        imageStop.setFitWidth(45);
        imageStop.setFitHeight(45);
      play.setStyle("-fx-background-color:#3e3737");
        play.setOnAction(e ->
        {
            if(count2==1){
            Status status = mediaPlayer.getStatus();

    if (status == Status.PAUSED
         || status == Status.READY
         || status == Status.STOPPED) {

           mediaPlayer.play();
            play.setGraphic(imagePlay);

                   } else {
                   mediaPlayer.pause();
                play.setGraphic(imageStop);
           }
            }
        }//
        );
        //////////////////////////////////////////////////////
        Button replay = new Button();
        replay.setStyle("-fx-background-color:#3e3737");
        replay.setOnAction(e -> cl.replay());
        replay.setTranslateX(-72);
        replay.setTranslateY(130);
        ImageView imageReplay = new ImageView(
                new Image("images/replay.png"));
        imageReplay.setFitWidth(45);
        imageReplay.setFitHeight(45);
        replay.setGraphic(imageReplay);
        root.getChildren().add(replay);

        Button backword = new Button();
        backword.setStyle("-fx-background-color:#3e3737");
        backword.setOnAction(e -> cl.backward());
        backword.setTranslateX(-180);
        backword.setTranslateY(130);
        ImageView imageBackword = new ImageView(
                new Image("images/back.png"));
        imageBackword.setFitWidth(45);
        imageBackword.setFitHeight(45);
        backword.setGraphic(imageBackword);
        root.getChildren().add(backword);

         forward = new Button();
         forward.setStyle("-fx-background-color:#3e3737");
        forward.setOnAction(e -> cl.forward());
        forward.setTranslateX(-15);
        forward.setTranslateY(130);
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
        volslider.setTranslateY(130);
        root.getChildren().add(volslider);
        
         //Label soundImage=new Label();
//         soundImage.setTranslateX(75);
//         soundImage.setTranslateY(130);
          
         Button sound=new Button();
            sound.setTranslateX(65);
         sound.setTranslateY(130);
         sound.setStyle("-fx-background-color:#3e3737");
       // sound.setFitWidth(45);
       // sound.setFitHeight(45);
         ImageView imageSound = new ImageView(
                new Image("images/sound.png"));
        imageSound.setFitWidth(45);
        imageSound.setFitHeight(45);
         sound.setGraphic(imageSound);
           
            ImageView imagemuteSound = new ImageView(
                new Image("images/mute.png"));
        imagemuteSound.setFitWidth(45);
        imagemuteSound.setFitHeight(45);
         root.getChildren().add(sound);
         sound.setOnAction(e -> {
             if(flag){
                 flag=false;
                 cl.muteSound();
                 sound.setGraphic(imagemuteSound);
             }
             else
             {
                 flag=true;
                volslider.setValue(200);
                 sound.setGraphic(imageSound);
             }
           
         });
        
           
          
          
          ImageView imagemusic = new ImageView(
                new Image("images/music.png"));
         imagemusic.setFitWidth(220);
         
         imagemusic.setFitHeight(240);
         imagemusic.setTranslateX(-120);
         imagemusic.setTranslateY(-30);
         
          Label songname=new Label("Player");
         songname.setScaleX(3);
         songname.setScaleY(3);
         
         songname.setStyle("-fx-text-fill:#ff8c00;");
         songname.setTranslateX(-20);
         songname.setTranslateY(-30);
         //imagemusic.setGraphic(imagemusic);
//          
         Label mp3=new Label("MP3");
         mp3.setScaleX(3);
         mp3.setScaleY(3);
         
         mp3.setStyle("-fx-text-fill:#ff8c00;");
         mp3.setTranslateX(-70);
         mp3.setTranslateY(-80);
            listview = new ListView<String>();
        listview.setMaxSize(270, 240);
        //listview.setTranslateX(150);
        //listview.setTranslateY(-30);
        listview.setStyle("-fx-background-color:darkgray");
        root.getChildren().add(imagemusic);
        StackPane root_2=new StackPane();
          root_2.getChildren().add(songname);
          root_2.getChildren().add(mp3);
             cl.listPlay();
         
        
            viewlist.setOnAction(e -> 
             {
                if(count3==0) 
                {
                   count3=1; 
                   root_2.getChildren().removeAll(songname,mp3);
                   root_2.getChildren().add(listview);
                 }
                 else
                {
                 root_2.getChildren().add(songname);
                 root_2.getChildren().add(mp3);
                 root_2.getChildren().removeAll(listview);
                 count3=0;   
                }  
             });
//            
             
             root_2.setMaxSize(270,240);
             root_2.setTranslateX(140);
             root_2.setTranslateY(-30);
           //  root_2.getChildren().add(listview);
             root.getChildren().add(root_2);

         Label clockImage=new Label();
         clockImage.setTranslateX(-195);
         clockImage.setTranslateY(220);
         
        ImageView imageTime = new ImageView(
                new Image("images/clock.png"));
        imageTime.setFitWidth(40);
        imageTime.setFitHeight(40);
        clockImage.setGraphic(imageTime);
        
      // pn.add(clockImage, 0, 2);
       
        root.getChildren().add(clockImage);
   
        Label songtime=new Label();
        songtime.setTranslateX(155);
        songtime.setTranslateY(220);
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
        timeslider.setTranslateY(220);
        root.getChildren().add(timeslider);
        
         
         pn.setStyle("-fx-background-color:#3e3737");//221f1f//3e3737
         
           time =new Label();
        time.setStyle("-fx-background-color:gray");
               time.setScaleX(1.6);
               time.setScaleY(1.6);
             time.setTranslateX(145);
            time.setTranslateY(220);
            root.getChildren().add(time);
        
          pn.add(root,0, 0);
        Scene scene = new Scene(pn, 500, 460);
         
        primaryStage.setTitle("mp3 player");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //primaryStage.getFullScreenExitHint();
      
        primaryStage.show();
    }

/**
* This class contained run function that call the forward function.
*/
     public class Listplay implements Runnable
     {

        @Override
        public void run() {
             Controller cl=new Controller();
                if(count2==1)
           {
       cl.forward();
           }
         
         
             
        }
    }
  /**
* This class contained all function that dealing with the sound
* such as add  and open file and replay and play and stop ...etc.
*/
    public class Controller {  
/**
* This function is  used to open window to select one song.
*/    
    public void add() {
         try{
       FileChooser fc = new FileChooser();
        File chosen = fc.showOpenDialog(null);
        String path = chosen.getAbsolutePath();
        listview.getItems().add(chosen.getName());
         arraylist.add(path);
         }catch (Exception  ex)
                 {}
    }
/**
* This function is  used to open window to select one song.
*/    
    public void openFile() {
        count2=1;
        try{
              
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3"));
        File chosen = fc.showOpenDialog(null);
        String path = chosen.getAbsolutePath();
       
        listview.getItems().clear();
          arraylist=new ArrayList();
        listview.getItems().add(chosen.getName());
         arraylist.add(path);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        // time();
             volumeSlider();
             timeSlider();
              timer();
        }
        catch (Exception ex){}
       
    }
/**
* This function is  used to open window to select many of songs.
*/
    public void openDirectory() {
         count2=1;
        try{
        arraylist = new ArrayList<>();
           DirectoryChooser chooser = new DirectoryChooser();
               
            FileChooser fc1 = new FileChooser();
      
               
             File fc = chooser.showDialog(null);
               
             File[] chosen = fc.listFiles();
                  
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
       // listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listview.getItems().clear();
        for (int i = 0; i < chosen.length; i++) {
             String ext= chosen[i].getName();
                 ext= fileExtension(ext);
             //System.out.println("name "+ ext);
           if(ext.equals("mp3")   || ext.equals("mp4")||ext.equals("M4A") )
           {
           
            arraylist.add(chosen[i].getAbsolutePath());
             
            listview.getItems().add(chosen[i].getName());
           }
        }
        

        String path = chosen[0].getAbsolutePath();

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
         
          mediaPlayer.setOnEndOfMedia(ls);
        timer();

       volumeSlider();
       timeSlider();

        }
        catch (Exception ex){}
  
    }
/**
* This function is  used to open window to select many of songs.
*/
    public String fileExtension(String filename)
      { 
          String fileextension="";
          filename=filename.trim();
        for(int i=0; i<filename.length();i++)
        {
          if(filename.charAt(i)=='.')
              fileextension= filename.substring(i+1,filename.length());
        }
      return fileextension;
      }
/**
* This function is  used to replay the selected and running song.
*/
     public void replay() {
      
        try{
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
        }
        catch (Exception ex){}
    }
/**
* This function is  used to move from the current song to the previous song..
*/
    public void backward() {
        try{
        if (count > 0) {
            --count;
            mediaPlayer.stop();
            String path = arraylist.get(count);
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            volumeSlider();
           timer();
            timeSlider();
        }
        }
        catch (Exception ex){}

    }
/**
* This function is  used to move from the current song to the next song.
*/
    public void forward() {
        try{
        if (count < arraylist.size() - 1) {
            ++count;
            mediaPlayer.stop();
            String path = arraylist.get(count);
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
        
            mediaPlayer.play();
             mediaPlayer.setOnEndOfMedia(ls);
          volumeSlider();
           timer();
           timeSlider();
        }
        }
        catch (Exception ex){}

    }
/**
* This function is  used to run the paused selected song.
*/
    public void play() {
        try{
             mediaPlayer.play();
          
       
        }
        catch (Exception ex){}
    }
/**
* This function is  used to paused the current running song.
*/
    public void stop() {
        try{
        mediaPlayer.pause();
        }
        catch (Exception ex){}
    }
/**
* This function is responsible for the time of the running song.
*/
    public void timeSlider()
    {
    
    try{
        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) -> timeslider.setMax(newDuration.toSeconds()));

        timeslider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                mediaPlayer.seek(Duration.seconds(timeslider.getValue()));
            }
        });

        timeslider.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (!timeslider.isValueChanging()) {
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
            }
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeslider.isValueChanging()) {
                timeslider.setValue(newTime.toSeconds());
            }
        
        });
    }
    catch (Exception ex){}
    }
/**
* This function is responsible for the degree of the volume of running song.
*/
    public void volumeSlider()
    {
    try{
      volslider.setValue(mediaPlayer.getVolume() *200);
        volslider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(javafx.beans.Observable observable) {
                mediaPlayer.setVolume(volslider.getValue() /200);
            }
        });
    }
    catch (Exception ex){}
    }
/**
* This function is responsible for update the finished time of the running song.
*/
    public void updateValues() {
     if (time != null && timeslider != null && duration != null) {
         Platform.runLater(() -> {
            Duration currentTime = mediaPlayer.getCurrentTime();
            time.setText(formatTime(currentTime, duration));
            timeslider.setDisable(duration.isUnknown());
            if (!timeslider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeslider.isValueChanging()) {
                timeslider.setValue(currentTime.toSeconds());
    }
});
}
}
/**
 * This method is responsible for appearing the time in specific format
 * @param elapsed This is the first parameter to formatTime method
 * @param duration This is the second parameter to formatTime method
 * @return String This returns format  of time.
 */
   private  String formatTime(Duration elapsed, Duration duration) {
     int intElapsed = (int) floor(elapsed.toSeconds());
     int elapsedHours = intElapsed / (60 * 60);
     
        if (elapsedHours > 0) {
              intElapsed -= elapsedHours * 60 * 60;
            }
        
       int elapsedMinutes = intElapsed / 60;
       int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
               int intDuration = (int) floor(duration.toSeconds());
               int durationHours = intDuration / (60 * 60);
        if (durationHours > 0) {
              intDuration -= durationHours * 60 * 60;
        }
        
        int durationMinutes = intDuration / 60;
        int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;
        if (durationHours > 0) {
              return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
                 durationHours, durationMinutes, durationSeconds);
        } else 
            {
            return String.format("%02d:%02d/%02d:%02d",
            elapsedMinutes, elapsedSeconds, durationMinutes,durationSeconds);
            }
            } else {
                 if (elapsedHours > 0) {
                    return String.format("%d:%02d:%02d", elapsedHours,
                    elapsedMinutes, elapsedSeconds);
                   } else
                     {
                     return String.format("%02d:%02d", elapsedMinutes,elapsedSeconds);
                     }
    }
   }
/**
*this function when we selected many of songs and one running when it finished
* move to the next song automatically
*/
         public   void listPlay()
    {    
        
        listview.setOnMouseClicked((MouseEvent e) ->
        {
           if(e.getClickCount()>1)
           {
             // arraylist.add(chosen.get(0).getAbsolutePath());
              String path=listview.getSelectionModel().getSelectedItem();
               double index=listview.getSelectionModel().getSelectedIndex();
               for (int i = 0; i < arraylist.size(); i++) {
                   if(i==index)
                   {
                    Status status = mediaPlayer.getStatus();
                     path=arraylist.get(i);
                     if (status == Status.PAUSED
                     || status == Status.READY
                       || status == Status.PLAYING ) 
                         {
                           mediaPlayer.stop();
                         }
                     Controller cl=new Controller();
                     media = new Media(new File(path).toURI().toString());
                     mediaPlayer = new MediaPlayer(media);
                     mediaPlayer.play();
                      cl.volumeSlider();
                      cl.timeSlider();
                        timer();
                        play.setGraphic(imagePlay);
                        count=listview.getSelectionModel().getSelectedIndex();
                        mediaPlayer.setOnEndOfMedia(ls);
                     listview.getSelectionModel().clearSelection();
                     
                   }
                   
               }
               
           
           }
        
        
        });
        
        
         
    }
/**
*this function call update values function.  
*/
         public void timer() {
        mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
            updateValues();
        });
        
        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            updateValues();
        });
        
        /////////////////////////////////////////////////
    }
/**
*this function used to mute the sound of the song and if it muted this function appear .  
*/  
         public void muteSound()
  {
      volslider.setValue(0);
  }
/**
*this function used to when select song and use this function the song deleted from the list of songs.  
*/  
  public void removesong()
  {
     int index=0;
          if(count2==1){
              
               if(listview.getSelectionModel().getSelectedIndex()>=0){
                    if(listview.getSelectionModel().getSelectedIndex()==0)
                         forward();
                        arraylist.remove(listview.getSelectionModel().getSelectedIndex());
                        listview.getItems().remove(listview.getSelectionModel().getSelectedIndex());
               }
               if(count>index)
                   count--;
       }
      }
  
  
  
  
 };
/**
* The Mp_3  program implements an application that
* dealing with mp3 player.
* @author Our team
* @version 1.0
* @since 2016-4-31
*/
    public static void main(String[] args) {
        launch(args);
    }

    
}
