package BLL;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import DAL.db.PlaylistDAO_DB;
import BE.Song;
import java.io.IOException;
import java.util.List;

public class PlaylistManager {

    private IPlaylistDataAccess playlistDAO;

    private  PlaylistDAO_DB playlistDAO_DB;

    public PlaylistManager() throws IOException {

        playlistDAO = new PlaylistDAO_DB();
        this.playlistDAO_DB = new PlaylistDAO_DB();
    }

    public List<Playlist> getAllplaylists() throws Exception {
        return playlistDAO.getAllplaylists();
    }

    public List<Song> getAllSongsInPlaylist(Playlist playlist) throws Exception {
        return playlistDAO.getAllSongsInPlaylist(playlist);
    }

    public Playlist createNewPlaylist(Playlist newPlaylist) throws Exception {
        return playlistDAO.createPlaylist(newPlaylist);
    }

    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistDAO.deletePlaylist(playlist);
    }

    public void addSongToPlaylist(Song song, Playlist playlist) throws Exception {
        // Validate parameters
        if (song == null || playlist == null) {
            throw new IllegalArgumentException("Song and playlist cannot be null.");
        }
        // Call the data access layer to add the song to the playlist
        playlistDAO.addSongToPlaylist(song, playlist);
    }

    public void deletePlaylistSong(Playlist selectedPlaylist, Song selectedSongsInPlaylist) {
        playlistDAO_DB.deleteSongFromPlaylist(selectedPlaylist, selectedSongsInPlaylist);
        System.out.println("PlaylistManager");
    }
}
