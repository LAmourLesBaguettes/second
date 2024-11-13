import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AvatarMonsterGame extends JPanel implements ActionListener {
    private int avatarX = 50;
    private int avatarY = 50;
    private int monsterX = 100;
    private int monsterY = 100;
    private final int STEP = 5;
    private final Timer timer;

    public AvatarMonsterGame() {
        setFocusable(true);
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> avatarY -= STEP;
                    case KeyEvent.VK_DOWN -> avatarY += STEP;
                    case KeyEvent.VK_LEFT -> avatarX -= STEP;
                    case KeyEvent.VK_RIGHT -> avatarX += STEP;
                }
                repaint();
            }
        });
        timer = new Timer(200, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("@", avatarX, avatarY);
        g.drawString("M", monsterX, monsterY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (avatarX > monsterX) {
            monsterX += STEP;
        } else if (avatarX < monsterX) {
            monsterX -= STEP;
        }
        if (avatarY > monsterY) {
            monsterY += STEP;
        } else if (avatarY < monsterY) {
            monsterY -= STEP;
        }
        if (Math.abs(avatarX - monsterX) < STEP && Math.abs(avatarY - monsterY) < STEP) {
            JOptionPane.showMessageDialog(this, "Game Over! The monster caught you!");
            System.exit(0);
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Monster Chase Game");
        AvatarMonsterGame game = new AvatarMonsterGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
