package GUI.Controller;

import DAL.db.MyDatabaseConnector;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class EditSongWindow {

    public TextField txtFName;
    public Button btnChooseF;
    private MediaPlayer mediaPlayer;
    public void ChooseFile(ActionEvent actionEvent) throws SQLException, IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

        File selectedFile = chooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            saveMp3ToDatabase(selectedFile.getAbsolutePath());
        }
    }

    private void saveMp3ToDatabase(String filePath) throws SQLException, IOException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {
            String insertQuery = "INSERT INTO Songs (Title, Artist, Songlenght) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                String Title = askUserForSongNameOrGenerateIt(filePath);
                String Artist = askUserForSongNameOrGenerateIt(filePath);
                String Songlenght = askUserForSongNameOrGenerateIt(filePath);

                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Artist);
                preparedStatement.setString(3, Songlenght);
                preparedStatement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String askUserForSongNameOrGenerateIt(String filePath) {
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
}
