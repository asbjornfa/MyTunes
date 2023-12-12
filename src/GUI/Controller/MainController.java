package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public TableColumn<Song, String> colTitle, colArtist, colCategory;
    @FXML
    public TableColumn<Playlist, String> colPName;
    @FXML
    public TableView<Song> tblSongs;
    public TableView<Playlist> tblPlaylist;
    @FXML
    private ListView lstSP;
    @FXML
    private Button btnStopMusic;
    @FXML
    private Button btnBackwardsMusic;
    @FXML
    private Button btnPlayMusic;
    @FXML
    private Button btnForwardMusic;
    @FXML
    private Button btnSearchS;
    @FXML
    private TextField txtSearchS;
    @FXML
    private Slider slidVolume;
    @FXML
    private Label lblSName;




    //private MediaPlayer mediaPlayer;

    private MusicPlayer musicPlayer;

    private SongModel songModel;

    private PlaylistModel playlistModel;


    public MainController() {
        try {
            songModel = new SongModel();
            playlistModel = new PlaylistModel();
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

        colPName.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Sets items in tblSongs


        if (songModel != null) {
            tblSongs.setItems(songModel.getObservableSongs());
        }

        if (playlistModel != null) {
            tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        }


        tblPlaylist.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        try {
                            lstSP.setItems(playlistModel.getSongsForPlaylist(newValue));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        //System.out.println("works" + newValue);
                    }
                });

        txtSearchS.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                e.printStackTrace();
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

    public void DeletePlaylist(ActionEvent actionEvent) {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            try {
                playlistModel.deletePlaylist(selectedPlaylist);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }

        }
    }

    public void DeletePlaylistSong() {

    }

    public void NewPlaylist(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewPlaylistWindow.fxml"));
            Parent root = loader.load();

            NewPlaylistWindow newPlaylistWindow = loader.getController();
            newPlaylistWindow.setMainController(this);

            Stage pStage = new Stage();
            pStage.setTitle("New Playlist");
            pStage.setScene(new Scene(root));

            pStage.show();

        } catch (IOException e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void addSongToTable(Song song) {
        tblSongs.getItems().add(song);
    }

    public void addPlaylistToTable(Playlist playlist) {
        tblPlaylist.getItems().add(playlist);
    }

    public void Volume(MouseEvent mouseEvent) {
    }


}
