package BLL;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import DAL.db.PlaylistDAO_DB;

import java.io.IOException;
import java.util.List;

public class PlaylistManager {


    public Playlist createNewPlaylist;
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() throws IOException {
        //songDAO = new
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

}
