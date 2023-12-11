package BLL;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import DAL.db.PlaylistDAO_DB;
import BE.Song;
import java.io.IOException;
import java.util.List;

public class PlaylistManager {

    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() throws IOException {

        playlistDAO = new PlaylistDAO_DB();
    }

    public List<Playlist> getAllplaylists() throws Exception {
        return playlistDAO.getAllplaylists();
    }

    public Playlist createNewPlaylist(Playlist newPlaylist) throws Exception {
        return playlistDAO.createPlaylist(newPlaylist);
    }

    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistDAO.deletePlaylist(playlist);
    }

    /*public void addSongToPlaylist(Song song, Playlist playlist){

    }
    public void removeSongFromPlaylist(Song song, Playlist playlist){

    }

     */

}
