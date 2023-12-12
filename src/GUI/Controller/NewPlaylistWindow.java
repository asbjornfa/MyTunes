package GUI.Controller;

import BE.Playlist;
import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPlaylistWindow {
    public TextField txtNewPName;

    public MainController mainController;



    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    public void createNewPlaylist(ActionEvent actionEvent) throws Exception {
        String name = txtNewPName.getText();


        Playlist newPlaylist = new Playlist(-1, name);
        mainController.addPlaylistToTable(newPlaylist);

        PlaylistModel playlistmodel = new PlaylistModel();

        try{
            playlistmodel.createNewPlaylist(newPlaylist);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }



    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }








}