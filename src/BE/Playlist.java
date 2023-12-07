package BE;

public class Playlist {

    private int id;

    private String playlist_name;

    public Playlist(int id, String playList_name) {
        this.id = id;
        this.playlist_name = playList_name;
    }

    public int getId() {
        return id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name() {
        this.playlist_name = playlist_name;
    }
}
