package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistModel {

    private ObservableList<Playlist> playlistsToBeViewed;

    private ObservableList<Song> songsInPlaylistToBeViewed;

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
        return FXCollections.observableArrayList(playlistManager.getAllSongsInPlaylist(playlist));
    }

    public void addSongsToPlaylist(Song selectedSong, Playlist selectedPlaylist) {
        if (selectedSong == null || selectedPlaylist == null) {
            throw new IllegalArgumentException("Selected song and playlist cannot be null.");
        }

        try {
            // Add the selected song to the selected playlist using PlaylistManager
            playlistManager.addSongToPlaylist(selectedSong, selectedPlaylist);

            // Update the local model to reflect the changes
            int index = playlistsToBeViewed.indexOf(selectedPlaylist);
            playlistsToBeViewed.set(index, selectedPlaylist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSongFromPlaylist(Song selectedSongsInPlaylist, Playlist selectedPlaylist) {
        songsInPlaylistToBeViewed = FXCollections.observableArrayList();
        playlistManager.deletePlaylistSong(selectedPlaylist, selectedSongsInPlaylist);
        System.out.println("PlaylistModel");
    }
}
