package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import GUI.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public TableColumn<Song, String> colTitle, colArtist, colCategory;
    @FXML
    public TableView<Song> tblSongs;
    public TableView tblPlaylist;
    @FXML
    private Button btnStopMusic;
    @FXML
    private ListView<Song> LstPlayList;
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

    private MusicPlayer musicPlayer;

    private SongModel songModel;


    public MainController() {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }

        musicPlayer = new MusicPlayer();
    }

    private void displayError(Throwable t) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void PlaySong(ActionEvent actionEvent) {
        playSelectedMusic();
    }

    public void StopSong(ActionEvent actionEvent) {
        stopSelectedMusic();
    }

    public void PauseSong(ActionEvent actionEvent) {
        pauseSelectedMusic();
    }

    public void TableUpdate() {
        tblSongs.refresh();
    }

    // Overvej om de her selected methoder skal med, tror man kan skrive det nemmere.
    private void playSelectedMusic() {
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            String filePath = "data/audio/" + selectedSong.getFilePath();
            musicPlayer.play(filePath);
        }
    }


    private void stopSelectedMusic() {

        musicPlayer.stop();
    }

    private void pauseSelectedMusic() {
        musicPlayer.pause();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TableView colums + BE getters
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Sets items in tblSongs
        tblSongs.setItems(songModel.getObservableSongs());


        txtSearchS.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        // Sets starting volume to 100
       /* slidVolume.setValue(mediaPlayer.getVolume() *100);
        slidVolume.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(slidVolume.getValue() / 100);
            }
        }); */
    }

    public void closeMain(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void clickOpenEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditSongWindow.fxml"));
            Parent root = loader.load();

            EditSongController editSongController = loader.getController();

            editSongController.setMainController(this);



            Stage editStage = new Stage();
            editStage.setTitle("Edit Song");
            editStage.setScene(new Scene(root));

            // Access the controller of the EditSong window if needed

            // Show the EditSong window
            editStage.show();
        } catch (IOException e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    /*
    public void Volume(MouseEvent mouseEvent) {
        mediaPlayer.setVolume(slidVolume.getValue() * 100);
        mediaPlayer.play();
    } */

    public void DeleteSong(ActionEvent actionEvent) {
        Song selectedSongs = tblSongs.getSelectionModel().getSelectedItem();
        if (selectedSongs != null) {
            try {
                songModel.deleteSong(selectedSongs);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }

        }
    }

    public void NewPlaylist(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewPlaylistWindow.fxml"));
            Parent root = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("New Playlist");
            editStage.setScene(new Scene(root));

            // Access the controller of the EditSong window if needed
            //EditSongController editSongController = loader.getController();

        } catch (IOException e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void addSongToTable(Song song) {
        tblSongs.getItems().add(song);
    }

    public void Volume(MouseEvent mouseEvent) {
    }
}
