package sk.uniza.fri.engine.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {

    private Clip clip;

    /**
     * Konstruktor tiredy MusicPlayer, ide o jednoduchy prehrávac hudby
     */
    public MusicPlayer () {
        Thread musicThread = new Thread(() -> {
            File music = new File("music/menu.wav");
            try {
                this.clip = AudioSystem.getClip();
                this.clip.open(AudioSystem.getAudioInputStream(music));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    /**
     * metoda start() spusti hudbu
     */
    public void start () {
        if (this.clip != null) {
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * metoda stop() zastavi hudbu
     */
    public void stop () {
        this.clip.stop();
    }

}
