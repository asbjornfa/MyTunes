package DAL;

import BE.Song;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getAllSongs() throws Exception;

    public Song createSong(Song song) throws Exception;

    public void updateSong(Song song) throws Exception;

    public Song deleteSong(Song song) throws Exception;

    public Song deleteSongFromPlaylist(Song song) throws Exception;
}
