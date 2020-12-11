package game.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {

    private String path;
    private AudioInputStream soundFile;
    private Clip sound;
    private SoundManagment.SoundType soundType;
    private long soundPosition;
    private int volume = 100;

    public GameSound(String path, SoundManagment.SoundType soundType) {
        this.path = path;
        this.soundType = soundType;
        try {
            soundFile = AudioSystem.getAudioInputStream(getClass().getResource(path));
            sound = AudioSystem.getClip();
            sound.open(soundFile);
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Game sound file exception: " + ex);
        } catch (IOException ex) {
            System.out.println("Game sound file was not founded: " + ex);
        } catch (LineUnavailableException ex) {
            System.out.println("Error to load game sound: " + ex);
        }
        
        SoundManagment.SOUNDS.add(this);

    }

    public void start() {
        sound.setMicrosecondPosition(0);
        if (sound != null) {
            sound.start();
        } else {
            System.out.println("Cannot start audio file.");
        }
    }

    public void stop() {
        sound.setMicrosecondPosition(0);
        if (sound != null) {
            sound.stop();
        } else {
            System.out.println("Cannot stop audio file.");
        }
    }

    public void pause() {
        if (sound != null) {
            soundPosition = sound.getMicrosecondPosition();
            sound.stop();
        } else {
            System.out.println("Cannot pause audio file.");
        }
    }

    public void resume() {
        if (sound != null) {
            sound.setMicrosecondPosition(soundPosition);
            sound.start();
        } else {
            System.out.println("Cannot resume audio file.");
        }
    }

    public void loop(boolean tof) {
        if (tof == true) {
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            sound.loop(0);
        }
    }

    public void setVolume(int volume) {
        FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume / 100.0f) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
    
    public void setDefaultVolume( int volume ){
        this.volume = volume;
           FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume / 100.0f) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    public int getVolume() {
        return volume;
    }
    
    public SoundManagment.SoundType getSoundType(){
        return soundType;
    }

}
