package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistWindow {
    public TextField txtNewPName;

    public Playlist createNewPlaylist(ActionEvent actionEvent) throws Exception {
        String playList_name = txtNewPName.getText();


        Playlist newPlaylist = new Playlist(-1, playList_name);
        PlaylistModel playlistmodel = new PlaylistModel();



        try{
            playlistmodel.createNewPlaylist(newPlaylist);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
        return newPlaylist;
        /*Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();

         */
    }



    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }








}