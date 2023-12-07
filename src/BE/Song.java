package BE;

public class Song {

    private int id;

    private String title;

    private String artist;

    private String category;

    private String filePath;

    public Song(int id, String title, String artist, String category, String filePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filePath = filePath;
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

    public String getFilePath() {

        return filePath;
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

    public void setFilePath() {
        this.filePath = filePath;
    }

    @Override
    public String toString() {

        return id + ": " + title + " ("+artist+")" + category;
    }


}
