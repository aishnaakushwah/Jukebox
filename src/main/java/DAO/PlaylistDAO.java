package DAO;

import Data.Songs;
import Main.JukeboxImplementation;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistDAO {

    public void createAPlayList() throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection connection = DBConnection.getConnection();

        System.out.println("NAME YOUR PLAYLIST");
        String playlistName = sc.nextLine();

        String sql = "Insert into playlist(playlistName) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, playlistName);
        int count = preparedStatement.executeUpdate();

        if (count != 0) {
            System.out.println("NEW PLAYLIST CREATED");
        }
        System.out.println("\nEnter 1: Add songs to the playlist");
        System.out.println("Enter 2: Go back to the main menu");

        int choice = sc.nextInt();
        do {
            switch (choice) {
                case (1):
                    SongsInPlaylistDAO songsInPlaylistDAO = new SongsInPlaylistDAO();
                    songsInPlaylistDAO.addSongsIntoPlayList();
                    break;
                case (2):
                    String[] arg = new String[0];
                    JukeboxImplementation.main(arg);
            }
        } while (choice < 3);
    }

    public List<Songs> existingPlaylist() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        List<Songs> playListSongs = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT playlistId,playlistName from playlist;");

        System.out.format("%-15s %-20s\n", "Playlist ID", "Playlist Name");
        System.out.println("------------------------------------");
        while (resultSet.next()) {
            System.out.format("%-15s %-20s\n", resultSet.getInt(1), resultSet.getString(2));
        }
        System.out.println("------------------------------------");

        System.out.println("ENTER THE PLAYLIST ID YOU WANT TO OPEN");
        int playListID = scanner.nextInt();
        String sql = "Select songId from songsInPlaylist where playlistId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, playListID);

        ResultSet resultSet1 = preparedStatement.executeQuery();
        String sql3 = "Select * from songs where songId = ?";
        while (resultSet1.next()) {
            int songId = resultSet1.getInt(1);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql3);
            preparedStatement1.setInt(1, songId);
            ResultSet resultSet2 = preparedStatement1.executeQuery();
            while (resultSet2.next()) {
                playListSongs.add(new Songs(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet2.getString(4), resultSet2.getString(5), resultSet2.getString(6)));
            }
        }
        return playListSongs;
    }
}

