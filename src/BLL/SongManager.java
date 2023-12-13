package BLL;

import BE.Song;
import BLL.util.SongSearcher;
import DAL.ISongDataAccess;
import DAL.db.SongDAO_DB;

import java.io.IOException;
import java.util.List;

public class SongManager {

    private SongSearcher songSearcher = new SongSearcher();

    private ISongDataAccess songDAO;

    public SongManager() throws IOException {

        songDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSongs();
    }

    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createNewSong(Song newSong) throws Exception {
        return songDAO.createSong(newSong);
    }

    public void deleteSong(Song song) throws Exception {
        songDAO.deleteSong(song);
    }

    public void deleteSongFromPlaylist(Song song) throws Exception {
        songDAO.deleteSongFromPlaylist(song);
    }


}
