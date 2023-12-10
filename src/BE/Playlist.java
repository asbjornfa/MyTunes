package BE;

public class Playlist {

    private int pid;

    private String playlist_name;

    public Playlist(int pid, String playList_name) {
        this.pid = pid;
        this.playlist_name = playList_name;
    }

    public int getpId() {

        return pid;
    }

    public String getPlaylist_name() {

        return playlist_name;
    }

    public void setPlaylist_name() {

        this.playlist_name = playlist_name;
    }


    public String toString() {

        return pid + ": " + playlist_name;
    }


}
