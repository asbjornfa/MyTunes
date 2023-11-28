package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;

public class MainController {
    @FXML
    private Button btnStopMusic;
    @FXML
    private ListView LstPlayList;
    @FXML
    private Button btnBackwardsMusic;
    @FXML
    private Button btnPlayMusic;
    @FXML
    private ListView lstSongsList;
    @FXML
    private Button btnForwardMusic;
    @FXML
    private Button btnSearchS;
    @FXML
    private TextField txtSearchS;
    @FXML
    private Slider slidVolume;
    @FXML
    private Button btnNewP;
    @FXML
    private Button btnEditP;
    @FXML
    private Button btnDeleteP;
    @FXML
    private Button BtnUpSP;
    @FXML
    private Button btnDownSP;
    @FXML
    private Button btnDeleteSP;
    @FXML
    private Button btnNewS;
    @FXML
    private Button btnEditS;
    @FXML
    private Button btnDeleteS;
    @FXML
    private Button btnCloseS;
    @FXML
    private Label lblSName;
    @FXML
    private ListView lstOnSP;


    private MediaPlayer mediaPlayer;


    public void chooseMusic(MouseEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Your Music");
        File file = chooser.showOpenDialog(null);
        if(file != null){
            String selectedFile = file.toURI().toString();
            Media media = new Media(selectedFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> lblSName.setText(file.getAbsolutePath()));
        }

    }

    public void Play(ActionEvent mouseEvent) {

        mediaPlayer.play();
    }

    public void Stop(ActionEvent actionEvent) {
        mediaPlayer.stop();
    }
}
