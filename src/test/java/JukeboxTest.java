import DAO.PlaylistDAO;
import DAO.SongsInPlaylistDAO;
import Data.Songs;
import Operations.AudioPlayer;
import Operations.JukeboxOperation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JukeboxTest {
    JukeboxOperation jukeboxOperation;
    AudioPlayer audioPlayer;
    PlaylistDAO playlistDAO;
    SongsInPlaylistDAO songsInPlaylistDAO;
    Songs songs;

    @BeforeEach
    void setUp() {
        jukeboxOperation = new JukeboxOperation();
        audioPlayer = new AudioPlayer();
        songs = new Songs();
        playlistDAO = new PlaylistDAO();
        songsInPlaylistDAO = new SongsInPlaylistDAO();
    }

    @AfterEach
    void tearDown() {
        jukeboxOperation = null;
        audioPlayer = null;
        songs = null;
        playlistDAO = null;
        songsInPlaylistDAO = null;
    }


    @Test
    void displaySongTable() throws SQLException, ClassNotFoundException {
        List<Songs> songsList = jukeboxOperation.displayAllSongs();
        assertEquals(8, songsList.size());
    }

    @Test
    void searchBySongName() throws SQLException, ClassNotFoundException {
        String songName = "Iris";
        List<Songs> songsList = jukeboxOperation.searchBySongName(songName);
        assertEquals(1, songsList.size());
    }

    @Test
    void searchByArtistName() throws SQLException, ClassNotFoundException {
        String artistName = "Anuv Jain";
        List<Songs> songsList = jukeboxOperation.searchByArtist(artistName);
        assertEquals(1, songsList.size());
    }

    @Test
    void searchByGenreType() throws SQLException, ClassNotFoundException {
        String genreType = "Pop";
        List<Songs> songsList = jukeboxOperation.searchByGenre(genreType);
        assertEquals(2, songsList.size());
    }
}
