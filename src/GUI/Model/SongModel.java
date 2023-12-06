package GUI.Model;

import BE.Song;
import BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SongModel {

    private ObservableList<Song> songsToBeViewed;

    private SongManager songManager;


    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public void createNewSong(Song newSong) throws Exception {
        Song s = songManager.createNewSong(newSong);
        songsToBeViewed.add(s);
    }


    public ObservableList<Song> getObservableSongs() {

        return songsToBeViewed;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }



    public void deleteSong(Song song) throws Exception {
        songManager.deleteSong(song);
        songsToBeViewed.remove(song);
    }

}
