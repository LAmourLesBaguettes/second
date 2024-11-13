package week09;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MonsterGame extends JFrame {
    private JPanel gamePanel = new GamePanel("@", "M", 'q', 200); // 게임 패널

    public MonsterGame() {
        setTitle("Monster Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(gamePanel); // GamePanel을 컨텐트팬으로 사용한다.
        setSize(300, 300);
        setVisible(true);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus(); // GamePanel이 키를 입력받을 수 있도록 포커스를 설정한다.
    }

    // 게임이 진행되는 패널
    class GamePanel extends JPanel {
        private String avatarChar;
        private String monsterChar;
        private char quitChar;
        private long monsterDelay;
        private JLabel avatar;
        private JLabel monster;
        final int AVATAR_MOVE = 10;

        public GamePanel(String avatarChar, String monsterChar, char quitChar, long monsterDelay) {
            this.avatarChar = avatarChar;
            this.monsterChar = monsterChar;
            this.quitChar = quitChar;
            this.monsterDelay = monsterDelay;

            avatar = new JLabel(avatarChar);
            monster = new JLabel(monsterChar);

            setLayout(null);
            addKeyListener(new MyKeyListener());

            avatar.setLocation(50, 50);
            avatar.setSize(15, 15);
            avatar.setForeground(Color.RED);
            add(avatar);

            monster.setLocation(200, 5);
            monster.setSize(15, 15);
            add(monster);

            MonsterThread monsterThread = new MonsterThread(monster, avatar, monsterDelay);
            monsterThread.start();

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e)