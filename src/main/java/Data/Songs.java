package Data;

public class Songs {
    private int songId;
    private String songName;
    private String genre;
    private String artist;
    private String duration;
    private String filepath;

    public Songs() {
    }

    public Songs(int songId, String songName, String genre, String artist, String duration, String filepath) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.filepath = filepath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

}
