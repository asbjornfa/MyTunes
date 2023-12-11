package BE;
import java.util.List;

public class Playlist {

    private int pid;
    private String name;
    /*private List<Song> songs;

    public List<Song> getSongs(){
        return songs;
    }
    public void setSongs(){
        this.songs = songs;
    }

     */
    public Playlist(int pid, String name) {
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
