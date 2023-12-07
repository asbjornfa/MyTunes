package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;



public class EditSongController {

    public TextField txtFName;
    public Button btnChooseF;
    public TextField txtEditTitle;
    public TextField txtEditArtist;
    public TextField txtEditCategory;
    public TextField txtEditTime;

    public void ChooseFile(ActionEvent actionEvent) throws SQLException, IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            txtEditTitle.setText(selectedFile.getName()); // Tilføjelse af title
            txtFName.setText(selectedFile.getName()); // FilePath added
        }
    }


    public void editClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void createNewSong(ActionEvent actionEvent) throws Exception {
        String title = txtEditTitle.getText();
        String artist = txtEditArtist.getText();
        String category = txtEditCategory.getText();
        String filePath = txtFName.getText();

        Song newSong = new Song(-1, title, artist, category, filePath);

        SongModel songModel = new SongModel();


        try{
            songModel.createNewSong(newSong);
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
