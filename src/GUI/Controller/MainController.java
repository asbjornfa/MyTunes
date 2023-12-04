package GUI.Controller;

import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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

    private SongModel songModel;

    public MainController() {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    private void displayError(Throwable t) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    // This method is moved to 'EditSongWindow'

    /*public void chooseMusic(MouseEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Your Music");
        File file = chooser.showOpenDialog(null);
        if(file != null){
            String selectedFile = file.toURI().toString();
            Media media = new Media(selectedFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> lblSName.setText(file.getAbsolutePath()));
        }

    } */

    public void Play(ActionEvent mouseEvent) {

        mediaPlayer.play();
    }

    public void Stop(ActionEvent actionEvent) {

        mediaPlayer.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
