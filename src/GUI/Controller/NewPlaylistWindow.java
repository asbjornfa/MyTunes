package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistWindow {
    public TextField txtNewPName;

    public Playlist createNewPlaylist(ActionEvent actionEvent) throws Exception {
        String playListName = txtNewPName.getText();


        Playlist newPlaylist = new Playlist(-1, playListName);

        return newPlaylist;


        /*Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();*/
    }
}