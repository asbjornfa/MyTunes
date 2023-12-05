package BE;

public class Song {

    private int id;

    private String title;

    private String artist;

    private String category;

    public Song(int id, String title, String artist, String category) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
    }


    public int getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getArtist() {

        return artist;
    }

    public String getCategory() {

        return category;
    }



    public void setTitle() {

        this.title = title;
    }

    public void setArtist() {

        this.artist = artist;
    }

    public void setCategory() {

        this.category = category;
    }

    @Override
    public String toString() {

        return id + ": " + title + " ("+artist+")" + category;
    }


}
