package DAL.db;

import BE.Song;
import DAL.ISongDataAccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    private MyDatabaseConnector databaseConnector;

    public SongDAO_DB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }


    @Override
    public List<Song> getAllSongs() throws Exception {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM YTMusic.Songs";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("song_id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                String filePath = rs.getString("filePath");

                Song song = new Song(id, title, artist, category, filePath);
                allSongs.add(song);
            }
            return allSongs;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    @Override
    public Song createSong(Song song) throws Exception {

        String sql = "INSERT INTO YTMusic.Songs (title, artist, category, filePath) VALUES (?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            //bind our parameters
            stmt.setString(1,song.getTitle());
            stmt.setString(2,song.getArtist());
            stmt.setString(3,song.getCategory());
            stmt.setString(4,song.getFilePath());

            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            Song createdSong = new Song(id, song.getTitle(), song.getArtist(), song.getCategory(), song.getFilePath());

            return createdSong;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add song", ex);
        }
    }

    @Override
    public void updateSong(Song song) throws Exception {
        throw new UnsupportedOperationException();
        /*String sql = "UPDATE YTMusic.Song SET title = ?, artist = ?, category = ? WHERE Song_id = ?";

         */
    }
    @Override
    public Song deleteSong(Song song) throws Exception {
        String sql = "DELETE FROM YTMusic.Songs WHERE song_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, song.getId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }
        return song;
    }

    @Override
    public Song deleteSongFromPlaylist(Song song) throws Exception {
        String sql = "DELETE FROM YTMusic.SongsInPlaylist WHERE song_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, song.getId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }
        return song;
    }

    public List<Song> searchSongs(String query) throws Exception {
        throw new UnsupportedOperationException();
    }

}

