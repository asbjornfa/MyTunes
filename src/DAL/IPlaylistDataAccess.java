package DAL;

import BE.Playlist;
import BE.Song;

import java.util.List;

public interface IPlaylistDataAccess {

    public List<Playlist> getAllplaylists() throws Exception;

    public Playlist createPlaylist(Playlist playlist) throws Exception;

    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deletePlaylist(Playlist playlist) throws Exception;


    List<Song> getAllSongsInPlaylist(Playlist playlist) throws Exception;

    public void addSongToPlaylist(Song song, Playlist playlist) throws Exception;
}
