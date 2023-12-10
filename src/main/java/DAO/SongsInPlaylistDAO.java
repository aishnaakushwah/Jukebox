package DAO;

import Data.Songs;
import Main.JukeboxImplementation;
import Operations.AudioPlayer;
import Operations.JukeboxOperation;
import Util.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class SongsInPlaylistDAO {

    public void addSongsIntoPlayList() throws Exception {

        Connection connection = DBConnection.getConnection();
        Scanner scanner = new Scanner(System.in);
        JukeboxOperation jukeOperation = new JukeboxOperation();

        String sql = "SELECT playlistId, playlistName from playlist;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.format("%-15s %-20s\n", "Playlist ID", "Playlist Name");
        System.out.println("------------------------------------");
        while (resultSet.next()) {
            System.out.format("%-15s %-20s\n", resultSet.getInt(1), resultSet.getString(2));
        }
        System.out.println("------------------------------------");

        System.out.println("ENTER THE PLAYLIST ID IN WHICH YOU WANT TO ADD SONGS");
        int playListID = scanner.nextInt();

        System.out.println("DISPLAY SONGS BASED ON FOLLOWING OPTIONS");
        System.out.println("Enter 1 : Display all Songs");
        System.out.println("Enter 2 : Display song by Artist Name");
        System.out.println("Enter 3 : Display song by Genre Type");
        System.out.println("Enter 4 : Display song by Song Name");

        int option = scanner.nextInt();
        switch (option) {

            // DISPLAY ALL SONGS
            case (1):
                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                jukeOperation.displayAllSongs();
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                break;

            //ARTIST NAME
            case (2):
                System.out.println("ENTER THE ARTIST NAME YOU WANT TO DISPLAY");
                scanner.nextLine();
                String artistName = scanner.nextLine();
                List<Songs> songsListOfArtist = jukeOperation.searchByArtist(artistName); //
                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                for (Songs songs : songsListOfArtist) {
                    System.out.format("-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                }
                break;

            // GENRE TYPE
            case (3):
                System.out.println("ENTER THE GENRE TYPE YOU WANT TO DISPLAY");
                String genreType = scanner.nextLine();
                List<Songs> songsList = jukeOperation.searchByGenre(genreType);
                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                for (Songs songs : songsList) {
                    System.out.format("-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                }
                break;

            // SONG NAME
            case (4):
                System.out.println("ENTER THE SONG NAME YOU WANT TO DISPLAY");
                scanner.nextLine();
                String songName = scanner.nextLine();
                List<Songs> songsListBasedOnName = jukeOperation.searchBySongName(songName);
                System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                for (Songs songs : songsListBasedOnName) {
                    System.out.format("-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                }
                break;

            // MAIN MENU
            case (5):
                String[] arg = new String[0];
                JukeboxImplementation.main(arg);
                break;

            default:
                System.err.println("PLEASE SELECT THE RIGHT OPTION");
                option = scanner.nextInt();
        }

        System.out.println("\nENTER THE SONG ID YOU WANT TO ADD TO THE PLAYLIST");
        int songID = scanner.nextInt();

        String sqlUpdate = "INSERT INTO songsInPlaylist(playlistId,songId) values(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, playListID);
        preparedStatement.setInt(2, songID);
        int row = preparedStatement.executeUpdate(); // to execute the query
        if (row != 0) {
            System.out.println("SONG IS ADDED TO THE PLAYLIST");
        }
        System.out.println("\nEnter 1: Add more songs");
        System.out.println("Enter 2: Play the songs in playlist");
        System.out.println("Enter 3: Go back to the main menu");

        int choice = scanner.nextInt();
        do {
            switch (choice) {

                // ADD MORE SONGS
                case (1):
                    addSongsIntoPlayList();
                    break;

                // PLAY PLAYLIST SONGS
                case (2):
                    PlaylistDAO playlistDAO = new PlaylistDAO();
                    List<Songs> playList = playlistDAO.existingPlaylist();
                    System.out.format("%-10s %-35s %-25s %-25s %-25s\n", "Song ID", "Song Name", "Genre", "Artist", "Duration");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    for (Songs songs : playList) {
                        System.out.format("-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getGenre(), songs.getArtist(), songs.getDuration());
                    }
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Enter 1: Play the playlist");
                    System.out.println("Enter 2: Go back to the main menu");
                    int select = scanner.nextInt();
                    switch (select) {
                        case (1):
                            AudioPlayer audioPlayer = new AudioPlayer();
                            audioPlayer.playAllSongs(playList);
                            break;
                        case (2):
                            String[] arg = new String[0];
                            JukeboxImplementation.main(arg);
                            break;
                        default:
                            System.err.println("PLEASE SELECT THE CORRECT OPTION");
                    }
                    break;

                // MAIN MENU
                case (3):
                    String[] arg = new String[0];
                    JukeboxImplementation.main(arg);
                    break;

                default:
                    System.err.println("PLEASE SELECT THE RIGHT OPTION");
                    choice = scanner.nextInt();
                    break;
            }
        } while (choice < 4);
    }
}

