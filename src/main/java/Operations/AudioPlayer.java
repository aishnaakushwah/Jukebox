package Operations;

import Data.Songs;
import Main.JukeboxImplementation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AudioPlayer {
    List<Songs> songList;
    int songIndex;

    public void playAllSongs(List<Songs> songList) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        this.songList = songList;
        for (int i = 0; i < songList.size(); i++) {
            songIndex = i;
            playAllSongs(songList.get(i).getFilepath());
        }
    }

    public void playAllSongs(String songPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try {
            File file = new File(songPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String res = "";

            while (!res.equals("Q")) {
                System.out.println("P = Play, T = Pause, S = Stop, L = Loop, R = Reset, Q = Quit, N = NextSong, O = PreviousSong, E = Exit");
                System.out.print("Enter your choice: ");

                res = scanner.next();
                res = res.toUpperCase();


                switch (res) {
                    case ("P"): {
                        clip.start();
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }

                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;

                    case ("Q"):
                        clip.close();
                        break;
                    case ("N"):
                        songIndex += 1;
                        clip.close();
                        playAllSongs(songList.get(songIndex).getFilepath());
                        break;
                    case ("O"):
                        songIndex -= 1;
                        clip.close();
                        playAllSongs(songList.get(songIndex).getFilepath());
                        break;
                    case ("E"):
                        String[] arg = new String[0];
                        JukeboxImplementation.main(arg);
                        break;

                    default:
                        System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        res = scanner.next();
                        res = res.toUpperCase();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
