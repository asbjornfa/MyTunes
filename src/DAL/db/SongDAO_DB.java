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
            String sql = "SELECT * FROM dbo.Song";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");

                Song song = new Song(id, title, artist, category);
                allSongs.add(song);
            }
            return allSongs;
        }
        catch (SQLException ex){
            throw new Exception("Could not get songs from database", ex);
        }
    }

    @Override
    public Song createSong(Song song) throws Exception {

        String sql = "INSERT INTO dbo.Song (Title, Artist, Category) VALUES (?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1,song.getTitle());
            stmt.setString(2,song.getArtist());
            stmt.setString(3,song.getCategory());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            Song createdSong = new Song(id, song.getTitle(), song.getArtist(), song.getCategory());

            return createdSong;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    @Override
    public void updateSong(Song song) throws Exception {
        /*
        String sql = "UPDATE Song SET Title = ?, Artist = ?, Category = ? WHERE ID = ?;";

        try (Connection conn = databaseConnector.getConnection());
        PreparedStatement stmt = conn. */
        throw new UnsupportedOperationException();

    }

    @Override
    public Song deleteSong(Song song) throws Exception {
        String sql = "DELETE FROM dbo.Song WHERE ID = ?;";

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
            throw new Exception("Could not create movie", ex);
        }
        return song;
    }

    public List<Song> searchSongs(String query) throws Exception {
        throw new UnsupportedOperationException();
    }
}
