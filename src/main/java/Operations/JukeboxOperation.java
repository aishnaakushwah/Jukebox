package Operations;

import DAO.SongsDAO;
import Data.Songs;
import Main.JukeboxImplementation;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeboxOperation {

    public List<Songs> displayAllSongs() throws SQLException, ClassNotFoundException {
        List<Songs> songList = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        String query = "select * from songs";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {

            Songs data = new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

            data.setSongId(resultSet.getInt(1));
            data.setSongName(resultSet.getString(2));
            data.setArtist(resultSet.getString(3));
            data.setDuration(resultSet.getString(4));
            data.setGenre(resultSet.getString(5));
            data.setFilepath(resultSet.getString(6));

            songList.add(data);
        }
        for (Songs songs : songList) {
            System.out.format("%-10s %-35s %-25s %-25s %-25s\n", songs.getSongId(), songs.getSongName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
        }

        return songList;
    }

    public List<Songs> searchBySongName(String songName) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "select * from songs where songName like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, songName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }


    public List<Songs> searchByArtist(String artist) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "select * from songs where artist like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, artist + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }

    public List<Songs> searchByGenre(String genre) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "select * from songs where genre like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, genre + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }

    public void audioPlayer() throws Exception {
        Scanner scanner = new Scanner(System.in);
        AudioPlayer audioPlayer = new AudioPlayer();
        SongsDAO songsDAO = new SongsDAO();

        System.out.println("\nPLEASE SELECT THE OPTION \nEnter 1: Play the song\nEnter 2: Go back to the main menu");
        int choice = scanner.nextInt();

        switch (choice) {

            case (1):
                System.out.println("PLEASE ENTER THE SONG ID YOU WANT TO PLAY");
                int songID = scanner.nextInt();
                audioPlayer.playAllSongs(songsDAO.returnPath(songID));
                break;

            case (2):
                String[] arg = new String[0];
                JukeboxImplementation.main(arg);
                break;

            default:
                System.err.println("PLEASE SELECT THE RIGHT OPTION");
        }
    }
}
