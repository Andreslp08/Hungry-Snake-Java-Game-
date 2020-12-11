
package game.main;

import java.util.ArrayList;


public class SoundManagment {
    
    public enum SoundType{
        GAME, UI
    }
    
    public static ArrayList<GameSound> SOUNDS = new ArrayList();
    public static int GAME_VOLUME, DEFAULT_GAME_VOLUME = 100;
    public static int UI_VOLUME, DEFAULT_UI_VOLUME = 100;
    
    public static void updateVolume(){
        for (int i = 0; i < SOUNDS.size(); i++) {
            GameSound sound = SOUNDS.get(i);
            if( sound.getSoundType().equals(SoundType.UI)){
                int volume = UI_VOLUME*sound.getVolume()/100;
                sound.setVolume(volume);
            }
           if( sound.getSoundType().equals(SoundType.GAME)){
                int volume = GAME_VOLUME*sound.getVolume()/100;
                sound.setVolume(volume);
            }
        }
    }
    
}
