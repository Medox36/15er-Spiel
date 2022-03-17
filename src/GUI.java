import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;

public class GUI implements ActionListener{

    private int[] pos;
    private final String[] check;
    private final JButton[] jButtons;
    private final JButton button;
    private final JLabel textLabel;
    private final Icon[] icons;

    public GUI() {
        check = new String[] {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png","10.png","11.png","12.png","13.png","14.png","15.png","16.png"};
        pos = new int[] {1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12, 13, 14, 15, 16};
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        jButtons = new JButton[16];
        button = new JButton();
        textLabel = new JLabel();
        icons = new Icon[16];
        for (int i = 0; i < 16; i++) {
            icons[i] = new ImageIcon(Objects.requireNonNull(GUI.class.getResource("images/" + (i + 1) + ".png")));
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("15er - Spiel");

        textLabel.setBackground(new Color(50, 50, 50));
        textLabel.setForeground(new Color(78, 216, 172));
        textLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        textLabel.setText(" 15er");
        textLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 500, 100);

        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setBackground(new Color(150, 150, 150));

        button.setText("shuffle");
        button.setFont(new Font("Ink Free",Font.BOLD, 23));
        button.setBounds(350, 25, 120,45);
        button.setBackground(new Color( 123, 125, 125 ));
        button.setFocusable(false);
        button.addActionListener(this);

        for (int i = 0; i < 16; i++) {
            jButtons[i] = new JButton();
            buttonPanel.add(jButtons[i]);
            jButtons[i].setFont(new Font("Ink Free", Font.BOLD, 50));
            jButtons[i].setBackground(new Color(248, 249, 249));
            jButtons[i].setIcon(icons[i]);
            jButtons[i].setFocusable(false);
            jButtons[i].addActionListener(this);
        }

        textLabel.add(button);
        titlePanel.add(textLabel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstMove();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            shuffle();
        } else {
            int iOut;
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == jButtons[i]) {
                    iOut = checkEmpty(i);
                    if (iOut == 17) {
                        textLabel.setText(" Error");
                        break;
                    } else if (iOut == 16) {
                        textLabel.setText(" Exception");
                        break;
                    } else if (iOut < -1 || iOut > 17) {
                        textLabel.setText(" Nope!");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        System.exit(-1);
                    } else {
                        textLabel.setText(" 15er");
                        pos[iOut] = i + 1;
                        pos[i] = iOut + 1;
                        Icon tempIconIn = jButtons[i].getIcon();
                        Icon tempIconOut = jButtons[iOut].getIcon();
                        jButtons[iOut].setIcon(tempIconIn);
                        jButtons[i].setIcon(tempIconOut);
                        break;
                    }
                }
            }
            check();
        }
    }

    private void firstMove() {
        shuffle();
    }

    private void check() {
        int w = 0;
        for (int i = 0; i < 15; i++) {
            File file = new File(String.valueOf(jButtons[i].getIcon()));
            if (file.getName().equals(check[i])) {
                w++;
            }
        }
        if (w == 15) {
            complete();
        }
    }

    private int checkEmpty(int i) {
        int iOut;
        if (i == 0) {
            if (jButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (jButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else {
                iOut = 16;
            }
        } else if (i == 1) {
            if (jButtons[0].getIcon() == icons[15]) {
                iOut = 0;
            } else if (jButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (jButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else {
                iOut = 16;
            }
        } else if (i == 2) {
            if (jButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (jButtons[3].getIcon() == icons[15]) {
                iOut = 3;
            } else if (jButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else {
                iOut = 16;
            }
        } else if (i == 3) {
            if (jButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (jButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else {
                iOut = 16;
            }
        } else if (i == 4) {
            if (jButtons[0].getIcon() == icons[15]) {
                iOut = 0;
            } else if (jButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (jButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else {
                iOut = 16;
            }
        } else if (i == 5) {
            if (jButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (jButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else if (jButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (jButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else {
                iOut = 16;
            }
        } else if (i == 6) {
            if (jButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (jButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (jButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else if (jButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else {
                iOut = 16;
            }
        } else if (i == 7) {
            if (jButtons[3].getIcon() == icons[15]) {
                iOut = 3;
            } else if (jButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (jButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else {
                iOut = 16;
            }
        } else if (i == 8) {
            if (jButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else if (jButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (jButtons[12].getIcon() == icons[15]) {
                iOut = 12;
            } else {
                iOut = 16;
            }
        } else if (i == 9) {
            if (jButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (jButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else if (jButtons[10].getIcon() == icons[15]) {
                    iOut = 10;
            } else if (jButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else {
                iOut = 16;
            }
        } else if (i == 10) {
            if (jButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (jButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (jButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else if (jButtons[14].getIcon() == icons[15]) {
                iOut = 14;
            } else {
                iOut = 16;
            }
        } else if (i == 11) {
            if (jButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else if (jButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else if (jButtons[15].getIcon() == icons[15]) {
                iOut = 15;
            } else {
                iOut = 16;
            }
        } else if (i == 12) {
            if (jButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else if (jButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else {
                iOut = 16;
            }
        } else if (i == 13) {
            if (jButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (jButtons[12].getIcon() == icons[15]) {
                iOut = 12;
            } else if (jButtons[14].getIcon() == icons[15]) {
                iOut = 14;
            } else {
                iOut = 16;
            }
        } else if (i == 14) {
            if (jButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else if (jButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else if (jButtons[15].getIcon() == icons[15]) {
                iOut = 15;
            } else {
                iOut = 16;
            }
        } else if (i == 15) {
            if (jButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else if (jButtons[14].getIcon() == icons[15]) {
                iOut = 14;
            } else {
                iOut = 16;
            }
        } else {
            iOut = 17;
        }
        return iOut;
    }

    private void complete() {
        for (int i = 0; i < 16; i++) {
            jButtons[i].setBackground(new Color(89, 215, 69, 255));
            jButtons[i].setEnabled(false);
        }
        textLabel.setText(" Done!");
    }

    private void shuffle() {
        reset();
        for (int i=0; i<1000; i++){
            change(((int) (Math.random() * 16)), ((int) (Math.random() * 16)));
        }
        for (int i = 0; i < 16; i++) {
            jButtons[i].setEnabled(true);
            jButtons[i].setBackground(new Color(248, 249, 249));
        }
        textLabel.setText(" 15er");

        for (int i = 0; i < 16; i++) {
            jButtons[i].setIcon(icons[(pos[i] - 1)]);
        }
    }

    private void reset() {
        pos = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < 16; i++) {
            jButtons[i].setIcon(icons[i]);
        }
    }

    private void change(int a, int b){
        if (a == 16) {
            a = 15;
        }
        if (b == 16) {
            b = 15;
        }
        int storage = pos[a];
        pos[a] = pos[b];
        pos[b] = storage;
    }
}