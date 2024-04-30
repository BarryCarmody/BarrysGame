package Menu;

import Game.Host;

import javax.swing.*;
import java.awt.*;
import Game.Commons;

public class Window extends JFrame implements Runnable {
    private int currentState;
    private Scene currentScene;

    private final KL keyListener = new KL();
    private final ML mouseListener = new ML();

    public Window(String title) {
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        changeState(0);
    }

    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0:
                currentScene = new MenuScene(this, keyListener, mouseListener);
                break;
            case 1:
                currentScene = new GameScene(this, keyListener, mouseListener);
                var ex = new Host();
                ex.setVisible(true);
                break;
            default:
                System.out.println("Unknown scene.");
                currentScene = null;
                break;
        }
    }

    public void update() {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update();
    }

    public void draw(Graphics g) {
        //Graphics2D g2 = (Graphics2D) g; //sets graphics object to a 2D object
        currentScene.draw(g);
    }

    @Override
    public void run() {
        try {
            while (true) {
                update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
