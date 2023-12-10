package Main;

import DAO.PlaylistDAO;
import DAO.SongsDAO;
import DAO.SongsInPlaylistDAO;
import Data.Songs;
import Operations.AudioPlayer;
import Operations.JukeboxOperation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JukeboxImplementation {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AudioPlayer audioPlayerObj = new AudioPlayer();
        SongsDAO songsDAO = new SongsDAO();
        PlaylistDAO playlistDAOObj = new PlaylistDAO();
        SongsInPlaylistDAO songsInPlaylistDAOObj = new SongsInPlaylistDAO();
        JukeboxOperation jukeboxOperation = new JukeboxOperation();

        System.out.println("WELCOME TO THE JUKEBOX");
        int opt = 0;
        while (opt != 3) {
            System.out.println("=================================================");
            System.out.println("PLEASE SELECT THE OPTION");
            System.out.println("\nEnter 1: Display Songs\nEnter 2: Create new Playlist\nEnter 3: Check Out your Existing Playlists\nEnter 4: Exit");
            System.out.println("=================================================");
            opt = scanner.nextInt();
            try {

                switch (opt) {

                    // DISPLAY SONG
                    case (1):
                        System.out.println("DISPLAY SONGS BASED ON FOLLOWING OPTIONS");
                        System.out.println("\nEnter 1 : Display all Songs\nEnter 2 : Display song by Artist Name\nEnter 3 : Display song by Genre Type\nEnter 4 : Display song by Song Name\nEnter 5 : Go back to the main menu");
                        System.out.println("=================================================");
                        int option = scanner.nextInt();

                        switch (option) {

                            // DISPLAY ALL SONGS
                            case (1):
                                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                                List<Songs> displayAllSongs = jukeboxOperation.displayAllSongs();
                                System.out.println("----------------------------------------------------------------------------------------------------------------------");

                                System.out.println("\nEnter 1: Play a song\nEnter 2: Go back to the main menu");
                                int choice = scanner.nextInt();

                                switch (choice) {

                                    // PLAY A SONGS
                                    case (1):
                                        System.out.println("ENTER THE SONG ID WHICH YOU WANT TO PLAY");
                                        int song_ID = scanner.nextInt();
                                        audioPlayerObj.playAllSongs(songsDAO.returnPath(song_ID));
                                        break;

                                    // GO BACK TO THE MAIN MENU
                                    case (2):
                                        String[] arg = new String[0];
                                        JukeboxImplementation.main(arg);
                                        break;

                                    default:
                                        System.err.println("PLEASE SELECT THE RIGHT OPTION");
                                }
                                break;

                            // DISPLAY SONGS BY ARTIST NAME
                            case (2):
                                System.out.println("ENTER THE ARTIST NAME YOU WANT TO DISPLAY");
                                scanner.nextLine();
                                String artist = scanner.nextLine();
                                List<Songs> songsListOfArtist = jukeboxOperation.searchByArtist(artist);
                                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                                for (Songs songs : songsListOfArtist) {
                                    System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                                }
                                jukeboxOperation.audioPlayer();
                                break;

                            // DISPLAY SONGS BY GENRE NAME
                            case (3):
                                System.out.println("ENTER THE GENRE TYPE YOU WANT TO DISPLAY");
                                String genre = scanner.nextLine();
                                List<Songs> songsListOfGenre = jukeboxOperation.searchByGenre(genre);
                                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                                for (Songs songs : songsListOfGenre) {
                                    System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                                }
                                jukeboxOperation.audioPlayer();
                                break;

                            // DISPLAY SONGS BY SONG NAME
                            case (4):
                                System.out.println("ENTER THE SONG NAME YOU WANT TO DISPLAY");
                                scanner.nextLine();
                                String songName = scanner.nextLine();
                                List<Songs> songsListBasedOnName = jukeboxOperation.searchBySongName(songName);
                                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                                for (Songs songs : songsListBasedOnName) {
                                    System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                                }
                                jukeboxOperation.audioPlayer();
                                break;

                            // GO BACK TO THE MAIN MENU
                            case (5):
                                String[] arg = new String[0];
                                JukeboxImplementation.main(arg);
                                break;

                            default:
                                System.err.println("PLEASE SELECT THE RIGHT OPTION");
                        }
                        break;

                    // CREATE A NEW PLAYLIST
                    case (2):
                        playlistDAOObj.createAPlayList();
                        break;

                    // CHECK OUT YOUR EXISTING PLAYLIST
                    case (3):
                        List<Songs> playlistEx = playlistDAOObj.existingPlaylist();
                        System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------");
                        for (Songs songs : playlistEx) {
                            System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Enter 1: Play the entire playlist");
                        System.out.println("Enter 2: Play a particular song from the playlist");
                        System.out.println("Enter 3: Add songs into the existing playlist");
                        System.out.println("Enter 4: Go back to the main menu");
                        int select = scanner.nextInt();

                        switch (select) {

                            // PLAY THE ENTIRE PLAYLIST
                            case (1):
                                audioPlayerObj.playAllSongs(playlistEx);
                                JukeboxImplementation.main(args);
                                break;

                            // PLAY A SONG FROM PLAYLIST
                            case (2):
                                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                                for (Songs songs : playlistEx) {
                                    System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                                }
                                System.out.println("ENTER THE SONG ID WHICH YOU WANT TO PLAY");
                                int song_id = scanner.nextInt();
                                audioPlayerObj.playAllSongs(songsDAO.returnPath(song_id));
                                break;

                            // ADD SONG INTO EXISTING PLAYLIST
                            case 3:
                                songsInPlaylistDAOObj.addSongsIntoPlayList();
                                break;

                            // GO BACK TO MAIN MENU
                            case 4:
                                JukeboxImplementation.main(args);
                                break;
                        }
                        break;

                    // EXIT
                    case (4):
                        System.exit(0);
                }
            }
            catch (UnsupportedAudioFileException | LineUnavailableException | SQLException | IOException | ClassNotFoundException e) {
                System.out.println("e = " + e);
            }
        }
    }
}

