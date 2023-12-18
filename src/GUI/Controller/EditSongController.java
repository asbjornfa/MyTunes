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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;



public class EditSongController {

    public TextField txtFName;
    public Button btnChooseF;
    public TextField txtEditTitle;
    public TextField txtEditArtist;
    public TextField txtEditCategory;


    public MainController mainController;



    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    public void ChooseFile(ActionEvent actionEvent) throws SQLException, IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 and wav Files", "*.mp3", "*.wav"));

        // Set the initial directory before showing the dialog
        File initialDirectory = new File("data/audio/");
        chooser.setInitialDirectory(initialDirectory);

        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Path destinationPath = copyFile(selectedFile);
        }

        if (selectedFile != null) {
            txtEditTitle.setText(selectedFile.getName());
            txtFName.setText(selectedFile.getName());
        }
    }


    public void editClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        //closing stage
    }

    public void createNewSong(ActionEvent actionEvent) throws Exception {
        String title = txtEditTitle.getText();
        String artist = txtEditArtist.getText();
        String category = txtEditCategory.getText();
        String filePath = txtFName.getText();

        Song newSong = new Song(-1, title, artist, category, filePath);
        mainController.addSongToTable(newSong);


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

    private Path copyFile(File sourceFile) throws IOException {
        // Get the destination directory within your project
        File destinationDirectory = new File("data/audio/selected");

        // Create the destination directory if it doesn't exist
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdirs();
        }

        // Define the destination path for the copied file
        Path destinationPath = new File(destinationDirectory, sourceFile.getName()).toPath();

        // Copy the selected file to the destination directory
        try {
            Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);


            System.out.println("File copied successfully. Destination Path: " + destinationPath);
        } catch (IOException e){
            displayError(e);
            throw e;
        }
        return destinationPath;
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

}
