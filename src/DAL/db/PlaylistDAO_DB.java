package DAL.db;


import BE.Playlist;

import DAL.IPlaylistDataAccess;


import java.io.IOException;
import java.sql.*;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {
    private MyDatabaseConnector databaseConnector;

    public PlaylistDAO_DB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }


    public Playlist createPlaylistPlaylist(Playlist playlist) throws Exception {

        String sql = "INSERT INTO YTMusic.Playlist (playList_name) VALUES (?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //bind our parameters
            stmt.setString(1, playlist.getPlaylist_name());


            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create playlist object and send up the layers
            Playlist createdPLaylist = new Playlist(id, playlist.getPlaylist_name());

            return createdPLaylist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add playlist", ex);
        }
    }

    public void updatePLaylist(Playlist playlist) throws Exception {
        throw new UnsupportedOperationException();
        /*String sql = "UPDATE YTMusic.Song SET title = ?, artist = ?, category = ? WHERE Song_id = ?";

         */
    }

    public Playlist deletePLaylist(Playlist playlist) throws Exception {
        String sql = "DELETE FROM YTMusic.Songs WHERE playlist_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind parameters
            stmt.setInt(1, playlist.getpId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete playlist", ex);
        }
        return playlist;


    }

    @Override
    public List<Playlist> getAllplaylists() throws Exception {
        return null;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) throws Exception {
        return null;
    }

    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {

    }

    @Override
    public Playlist deletePlaylist(Playlist playlist) throws Exception {
        return null;
    }
}