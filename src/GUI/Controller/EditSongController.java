package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;


public class EditSongController {

    public TextField txtFName;
    public Button btnChooseF;
    public TextField txtEditTitle;
    public TextField txtEditArtist;
    public TextField txtEditCategory;
    public TextField txtEditTime;
    private MediaPlayer mediaPlayer;
    public void ChooseFile(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Your Music");
        File file = chooser.showOpenDialog(null);
        if(file != null){
            String selectedFile = file.toURI().toString();
            Media media = new Media(selectedFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> txtFName.setText(file.getAbsolutePath()));
        }
    }
    public void EditTitle {
        String title = txtEditTitle.getText();
        if (!title.isEmpty()) {
            return title ;
        } else {
            System.out.println("Text field is empty");
            // Do something when the text field is empty
        }
    }
}
