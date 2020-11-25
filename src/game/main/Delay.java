package game.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Delay {

    private DelayListener delayListener;
    private int currentTime = 0;
    private int time;
    private boolean stop;

    public Delay() {
        stop = false;

    }

    public void start(int time, DelayListener delayListener) {
        this.time = time;
        if (stop == false) {
            this.delayListener = delayListener;
            currentTime++;
            if (currentTime >= time) {
                this.delayListener.run();
                currentTime = 0;
            }
        }

    }

    public void stop() {
        stop = true;
    }

    public interface DelayListener {

        public void run();
    }
}
