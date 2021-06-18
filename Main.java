import java.awt.*;
import javax.swing.*;

import javax.swing.JTextField;
import java.awt.Dimension;

import java.util.concurrent.ThreadLocalRandom;

public class Main extends JPanel {
    static Player player;
    public int cellsize = 20;
    public int[] apple = new int[2];
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gr = (Graphics2D) g;
        
        //Grid
        gr.setColor(new Color(1, 115, 90));
        for(int x = 0; x < 800; x += cellsize){
            for(int y = 0; y < 800; y += cellsize){
                gr.fillRect(x, y, cellsize, cellsize);
            }
        }
        
        //Player
        gr.setColor(new Color(1, 194, 151));
        for(int i = 0; i < player.size; i++){
            gr.fillRect(player.pos[i][0], player.pos[i][1], cellsize, cellsize);
        }

        //Apples
        gr.setColor(new Color(2, 242, 190));
        gr.fillRect(apple[0], apple[1], cellsize, cellsize);
    }

    public static void main(String[] args) throws InterruptedException {
        JTextField component = new JTextField();
        KeyListenerC keyListener = new KeyListenerC();

        JFrame frame = new JFrame("Snake");
        Main game = new Main();

        player = new Player(game);

        keyListener.player = player;

        component.addKeyListener(keyListener);
        frame.setResizable(false);

        JFrame temp = new JFrame();
        temp.pack();
        Insets insets = temp.getInsets();
        temp = null;
        frame.setSize(new Dimension(insets.left + insets.right + 800,
                    insets.top + insets.bottom + 800));

        frame.setLocationRelativeTo(null);

        frame.add(component);
        frame.add(game);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.spawnApple();

        while (true) {
            player.move();

            game.repaint();

            Thread.sleep(100);
        }
    }

    public void spawnApple(){
        do{
            int x = ThreadLocalRandom.current().nextInt(0, 800);
            int y = ThreadLocalRandom.current().nextInt(0, 800);
            x -= x % 20;
            y -= y % 20;
            apple[0] = x;
            apple[1] = y;
        } while(isPlayerInWay(apple));
    }

    public boolean isPlayerInWay(int[] _pos){
        for(int i = 0; i < player.size; i++){
            if (player.pos[i][0] == _pos[0] && player.pos[i][1] == _pos[1]){
                return true;
            }
        }
        return false;
    }

}