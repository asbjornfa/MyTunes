package GUI.Controller;

import BE.Song;
import DAL.db.MyDatabaseConnector;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
          //  saveMp3ToDatabase(selectedFile.getAbsolutePath());
            txtEditTitle.setText(selectedFile.getName()); //tilføjelse af title
          //  txtFName.setText(selectedFile.getAbsolutePath()); // og filePath
            txtFName.setText(selectedFile.getName());



        }
    }

    private void saveMp3ToDatabase(String filePath) throws IOException {
        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {
            String insertQuery = "INSERT INTO YTMusic.Songs (title, artist, category, filePath) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                String title = txtEditTitle.getText();
                String artist = txtEditArtist.getText();
                String category = txtEditCategory.getText();


                preparedStatement.setString(1, title);
                preparedStatement.setString(2, artist);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, filePath);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void createNewSong(ActionEvent actionEvent) {
        String title = txtEditTitle.getText();
        String artist = txtEditArtist.getText();
        String category = txtEditCategory.getText();
        String filePath = txtFName.getText();

        Song newSong = new Song(-1, title, artist, category, filePath);
        try{
            SongModel.createNewSong(newSong);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }



    /*private String askUserForSongNameOrGenerateIt(String filePath) {
        TextInputDialog dialog = new TextInputDialog("GenereretNavn");
        dialog.setTitle("Indtast sangnavn");
        dialog.setHeaderText("Indtast et navn for sangen eller brug det genererede navn:");
        dialog.setContentText("Sangnavn:");

        Optional<String> result = dialog.showAndWait();

        return result.orElse(generateSongNameFromFileName(filePath));
    }

    private String generateSongNameFromFileName(String filePath) {
        String fileName = new File(filePath).getName();
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        } else {
            return fileName;
        }
    }
    /*public void EditTitle {
        String title = txtEditTitle.getText();
        if (!title.isEmpty()) {
            return title ;
        } else {
            System.out.println("Text field is empty");
            // Do something when the text field is empty
        }
    }*/
}
