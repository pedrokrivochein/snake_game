import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListenerC extends KeyAdapter {
    public Player player;

    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyChar() == 'w' || evt.getKeyCode() == 38) player.direction(0, -1);
        else if(evt.getKeyChar() == 'a' || evt.getKeyCode() == 37) player.direction(-1, 0);
        else if(evt.getKeyChar() == 's' || evt.getKeyCode() == 40) player.direction(0, 1);
        else if(evt.getKeyChar() == 'd' || evt.getKeyCode() == 39) player.direction(1, 0);
    }
}