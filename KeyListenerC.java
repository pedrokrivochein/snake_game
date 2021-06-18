import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListenerC extends KeyAdapter {
    public Player player;

    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyChar() == 'w') player.direction(0, -1);
        else if(evt.getKeyChar() == 'a') player.direction(-1, 0);
        else if(evt.getKeyChar() == 's') player.direction(0, 1);
        else if(evt.getKeyChar() == 'd') player.direction(1, 0);
    }
}