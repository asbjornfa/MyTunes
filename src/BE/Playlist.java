package BE;

public class Playlist {

    private int pid;

    private String playlist_name;

    public Playlist(int pid, String playList_name) {
        this.pid = pid;
        this.name = name;
    }

    public int getpId() {

        return pid;
    }

    public String getPName() {

        return name;
    }

    public void setName() {

        this.name = name;
    }


    public String toString() {

        return pid + ": " + name;
    }


}
