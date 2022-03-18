import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;

public class GUI implements ActionListener{

    private int[] pos;
    private final String[] check;
    private final JButton[] fieldButtons;
    private final JButton shuffleButton;
    private final JLabel textLabel;
    private final Icon[] icons;

    public GUI() {
        check = new String[] {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png","10.png","11.png","12.png","13.png","14.png","15.png","16.png"};
        pos = new int[] {1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12, 13, 14, 15, 16};
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        fieldButtons = new JButton[16];
        shuffleButton = new JButton();
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

        shuffleButton.setText("shuffle");
        shuffleButton.setFont(new Font("Ink Free",Font.BOLD, 23));
        shuffleButton.setBounds(350, 25, 120,45);
        shuffleButton.setBackground(new Color( 123, 125, 125 ));
        shuffleButton.setFocusable(false);
        shuffleButton.addActionListener(this);

        for (int i = 0; i < 16; i++) {
            fieldButtons[i] = new JButton();
            buttonPanel.add(fieldButtons[i]);
            fieldButtons[i].setFont(new Font("Ink Free", Font.BOLD, 50));
            fieldButtons[i].setBackground(new Color(248, 249, 249));
            fieldButtons[i].setIcon(icons[i]);
            fieldButtons[i].setFocusable(false);
            fieldButtons[i].addActionListener(this);
        }

        textLabel.add(shuffleButton);
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
        if (e.getSource() == shuffleButton) {
            shuffle();
        } else {
            int iOut;
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == fieldButtons[i]) {
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
                        Icon tempIconIn = fieldButtons[i].getIcon();
                        Icon tempIconOut = fieldButtons[iOut].getIcon();
                        fieldButtons[iOut].setIcon(tempIconIn);
                        fieldButtons[i].setIcon(tempIconOut);
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
            File file = new File(String.valueOf(fieldButtons[i].getIcon()));
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
            if (fieldButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (fieldButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else {
                iOut = 16;
            }
        } else if (i == 1) {
            if (fieldButtons[0].getIcon() == icons[15]) {
                iOut = 0;
            } else if (fieldButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (fieldButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else {
                iOut = 16;
            }
        } else if (i == 2) {
            if (fieldButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (fieldButtons[3].getIcon() == icons[15]) {
                iOut = 3;
            } else if (fieldButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else {
                iOut = 16;
            }
        } else if (i == 3) {
            if (fieldButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (fieldButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else {
                iOut = 16;
            }
        } else if (i == 4) {
            if (fieldButtons[0].getIcon() == icons[15]) {
                iOut = 0;
            } else if (fieldButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (fieldButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else {
                iOut = 16;
            }
        } else if (i == 5) {
            if (fieldButtons[1].getIcon() == icons[15]) {
                iOut = 1;
            } else if (fieldButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else if (fieldButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (fieldButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else {
                iOut = 16;
            }
        } else if (i == 6) {
            if (fieldButtons[2].getIcon() == icons[15]) {
                iOut = 2;
            } else if (fieldButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (fieldButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else if (fieldButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else {
                iOut = 16;
            }
        } else if (i == 7) {
            if (fieldButtons[3].getIcon() == icons[15]) {
                iOut = 3;
            } else if (fieldButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (fieldButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else {
                iOut = 16;
            }
        } else if (i == 8) {
            if (fieldButtons[4].getIcon() == icons[15]) {
                iOut = 4;
            } else if (fieldButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (fieldButtons[12].getIcon() == icons[15]) {
                iOut = 12;
            } else {
                iOut = 16;
            }
        } else if (i == 9) {
            if (fieldButtons[5].getIcon() == icons[15]) {
                iOut = 5;
            } else if (fieldButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else if (fieldButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else if (fieldButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else {
                iOut = 16;
            }
        } else if (i == 10) {
            if (fieldButtons[6].getIcon() == icons[15]) {
                iOut = 6;
            } else if (fieldButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (fieldButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else if (fieldButtons[14].getIcon() == icons[15]) {
                iOut = 14;
            } else {
                iOut = 16;
            }
        } else if (i == 11) {
            if (fieldButtons[7].getIcon() == icons[15]) {
                iOut = 7;
            } else if (fieldButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else if (fieldButtons[15].getIcon() == icons[15]) {
                iOut = 15;
            } else {
                iOut = 16;
            }
        } else if (i == 12) {
            if (fieldButtons[8].getIcon() == icons[15]) {
                iOut = 8;
            } else if (fieldButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else {
                iOut = 16;
            }
        } else if (i == 13) {
            if (fieldButtons[9].getIcon() == icons[15]) {
                iOut = 9;
            } else if (fieldButtons[12].getIcon() == icons[15]) {
                iOut = 12;
            } else if (fieldButtons[14].getIcon() == icons[15]) {
                iOut = 14;
            } else {
                iOut = 16;
            }
        } else if (i == 14) {
            if (fieldButtons[10].getIcon() == icons[15]) {
                iOut = 10;
            } else if (fieldButtons[13].getIcon() == icons[15]) {
                iOut = 13;
            } else if (fieldButtons[15].getIcon() == icons[15]) {
                iOut = 15;
            } else {
                iOut = 16;
            }
        } else if (i == 15) {
            if (fieldButtons[11].getIcon() == icons[15]) {
                iOut = 11;
            } else if (fieldButtons[14].getIcon() == icons[15]) {
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
            fieldButtons[i].setBackground(new Color(89, 215, 69, 255));
            fieldButtons[i].setEnabled(false);
        }
        textLabel.setText(" Done!");
    }

    private void shuffle() {
        reset();
        for (int i=0; i<1000; i++){
            change(((int) (Math.random() * 16)), ((int) (Math.random() * 16)));
        }
        for (int i = 0; i < 16; i++) {
            fieldButtons[i].setEnabled(true);
            fieldButtons[i].setBackground(new Color(248, 249, 249));
        }
        textLabel.setText(" 15er");

        for (int i = 0; i < 16; i++) {
            fieldButtons[i].setIcon(icons[(pos[i] - 1)]);
        }
    }

    private void reset() {
        pos = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < 16; i++) {
            fieldButtons[i].setIcon(icons[i]);
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