package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistModel {

    private ObservableList<Playlist> playlistsToBeViewed;

    private PlaylistManager playlistManager;


    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed= FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllplaylists());
    }

    public void createNewPlaylist(Playlist newPlaylist) throws Exception {
        Playlist p = playlistManager.createNewPlaylist(newPlaylist);
        playlistsToBeViewed.add(p);
    }


    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistsToBeViewed;
    }


    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistManager.deletePlaylist(playlist);
        playlistsToBeViewed.remove(playlist);
    }

    public ObservableList<Song> getSongsForPlaylist(Playlist playlist) throws Exception {
        int playlistId = playlist.getpId();
        return FXCollections.observableArrayList(playlistManager.getAllSongsInPlaylist(playlistId));
    }

}
