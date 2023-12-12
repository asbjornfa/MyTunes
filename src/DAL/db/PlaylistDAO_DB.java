package DAL.db;


import BE.Playlist;
import DAL.IPlaylistDataAccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {
    private MyDatabaseConnector databaseConnector;

    public PlaylistDAO_DB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<Playlist> getAllplaylists() throws Exception {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM YTMusic.Playlists";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("playlist_id");
                String name = rs.getString("name");


                Playlist playlist = new Playlist(id, name);
                allPlaylists.add(playlist);
            }
            return allPlaylists;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    public Playlist createPlaylist(Playlist playlist) throws Exception {

        String sql = "INSERT INTO YTMusic.Playlists (name) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //bind our parameters

            stmt.setString(1, playlist.getName());


            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create playlist object and send up the layers
            Playlist createdPLaylist = new Playlist(id, playlist.getName());

            return createdPLaylist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add playlist", ex);
        }
    }

    public void updatePlaylist(Playlist playlist) throws Exception {
        throw new UnsupportedOperationException();
        /*String sql = "UPDATE YTMusic.Song SET title = ?, artist = ?, category = ? WHERE Song_id = ?";

         */
    }

    public Playlist deletePlaylist(Playlist playlist) throws Exception {
        String sql = "DELETE FROM YTMusic.Playlists WHERE playlist_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind parameters
            stmt.setInt(1, playlist.getpId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            //ex.printStackTrace();
            //throw new Exception("Could not delete playlist", ex);
        }


        return playlist;
    }


}