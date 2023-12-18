package GUI.Controller;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import GUI.MusicPlayer;
import javafx.beans.InvalidationListener;
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
    public TableColumn<Playlist, String> colPName;
    @FXML
    public TableView<Song> tblSongs;
    @FXML
    public TableView<Playlist> tblPlaylist;
    @FXML
    private ListView lstSP;
    @FXML
    private Button btnStopMusic;
    @FXML
    private Button btnPlayMusic;
    @FXML
    private Button btnSearchS;
    @FXML
    private TextField txtSearchS;
    @FXML
    private Label lblSName;


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



    public void PlaySong(ActionEvent actionEvent) {
        playSelectedMusic();
        playSelectedSPMusic();
    }

    public void StopSong(ActionEvent actionEvent) {

        stopSelectedMusic();
    }

    // Works, but no need for this in our program.
    public void PauseSong(ActionEvent actionEvent) {
        pauseSelectedMusic();
    }

    // Method for playing the selected song outside a playlist.
    private void playSelectedMusic() {
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            String filePath = "data/audio/" + selectedSong.getFilePath();
            musicPlayer.play(filePath);
            lblSName.setText(selectedSong.getTitle());
        }
    }

    // Method for playing the selected song in our playlist.
    private void playSelectedSPMusic() {
        Song selectedSong = (Song) lstSP.getSelectionModel().getSelectedItem();
        if(selectedSong != null) {
            String filePath = "data/audio/" + selectedSong.getFilePath();
            musicPlayer.play(filePath);
            lblSName.setText(selectedSong.getTitle());
        }
    }

    private void stopSelectedMusic() {
        musicPlayer.stop();
    }

    // Works but no need for this in our program.
    private void pauseSelectedMusic() {
        musicPlayer.pause();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TableView colums Song
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        // TableView colums playlist
        colPName.setCellValueFactory(new PropertyValueFactory<>("name"));

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Sets items in tblSongs
        if (songModel != null) {
            tblSongs.setItems(songModel.getObservableSongs());
        }

        // Sets items in tblPlaylist
        if (playlistModel != null) {
            tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        }


        // Method that uses the listner function to see whats inside each playlist
        tblPlaylist.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        try {
                            // When a new item is selected, set the items of lstSP ListView
                            // to the songs associated with the selected playlist using playlistModel
                            lstSP.setItems(playlistModel.getSongsForPlaylist(newValue));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        txtSearchS.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                // When the text property changes, invoke the searchSong method on the songModel
                // This method is responsible for updating the search results based on the new input value
                songModel.searchSong(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void closeMain(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void clickOpenEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditSongWindow.fxml"));
            Parent root = loader.load();

            // Retrieve the controller associated with the loaded FXML file.
            EditSongController editSongController = loader.getController();
            // Set the main controller (this) in the EditSongController for communication.
            editSongController.setMainController(this);

            Stage editStage = new Stage();
            editStage.setTitle("Edit Song");
            editStage.setScene(new Scene(root));

            editStage.show();
        } catch (IOException e) {
            // DisplayError method is called.
            displayError(e);
            e.printStackTrace();
        }
    }


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

    public void AddSongsToPlaylist(ActionEvent actionEvent) {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();

        if (selectedPlaylist == null || selectedSong == null) {
            alertbox("Could not add song to playlist", "You did not select a song or playlist");
        }
        if (selectedPlaylist != null && selectedSong != null) {
            try {
                playlistModel.addSongsToPlaylist(selectedSong, selectedPlaylist);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void DeleteSongInPlaylist(ActionEvent actionEvent) {
        Song selectedSongsInPlaylist = (Song) lstSP.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        if (selectedSongsInPlaylist != null && selectedPlaylist != null) {
            try {
                playlistModel.deleteSongFromPlaylist(selectedSongsInPlaylist, selectedPlaylist);
                System.out.println("MainController");
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }

        }
    }
    //Error section.
        private void displayError(Throwable t) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something went wrong");
            alert.setHeaderText(t.getMessage());
            alert.showAndWait();
    }

        private void alertbox (String title, String content){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }

}
