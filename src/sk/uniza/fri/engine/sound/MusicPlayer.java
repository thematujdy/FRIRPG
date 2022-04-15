package sk.uniza.fri.engine.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class MusicPlayer {

    private Clip clip;

    public MusicPlayer () {
        Thread musicThread = new Thread(() -> {
            File music = new File("music/menu.wav");
            try {
                this.clip = AudioSystem.getClip();
                this.clip.open(AudioSystem.getAudioInputStream(music));
                this.clip.start();
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void start () {
        if (this.clip != null) {
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop () {
        this.clip.stop();
    }

}
