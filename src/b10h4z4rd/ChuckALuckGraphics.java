package b10h4z4rd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mathias on 22.04.15.
 */
public class ChuckALuckGraphics extends JPanel implements ActionListener{

    public static int players, FRAMEWIDTH, FRAMEHEIGTH = 300;

    private int[] res;
    private DicePanel[] dice;
    private PlayerPanel[] player;
    private JButton b1;

    public ChuckALuckGraphics(){
        dice = new DicePanel[3];
        player = new PlayerPanel[players];

        setLayout(null);

        b1 = new JButton("Go");
        b1.addActionListener(this);
        Dimension size = b1.getPreferredSize();
        b1.setBounds(FRAMEWIDTH - size.width, FRAMEHEIGTH - size.height - 20, size.width, size.height);
        add(b1);

        for (int i = 0; i < players; i++) {
            player[i] = new PlayerPanel(i);
            player[i].setBounds(25 + 125 * i, 0, PlayerPanel.PlayerPanelWidth, PlayerPanel.PlayerPanelHeigth);
            add(player[i]);
        }

        for (int i = 0; i < 3; i++){
            dice[i] = new DicePanel(this, i);
            dice[i].setBounds(10 + 70 * i, FRAMEHEIGTH - 75, 50, 50);
            add(dice[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        res = new int[3];

        for (int i = 0; i < 3; i++){
            dice[i].throwDice();
        }

    }

    public void diceDone(int pos, int num){
        if (res != null) {
            res[pos] = num;
            if (!missingRes()) {
                for (PlayerPanel pp : player) {
                    pp.setResult(res);
                }
                res = null;
                if (players <= 1)
                    b1.setEnabled(false);
            }
        }
    }

    private boolean missingRes(){
        for (int i = 0; i < 3; i++){
            if (res[i] == 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Ask for number of Players
                JTextField jTextField = new JTextField();
                Object[] objs = {"Enter number of Players:", jTextField};

                JOptionPane jOptionPane = new JOptionPane(objs, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

                while (jTextField.getText().isEmpty() || players < 1) {
                    jOptionPane.createDialog("ChuckALuck").setVisible(true);
                    players = Integer.parseInt(jTextField.getText());
                }

                //Init actual Game
                FRAMEWIDTH = 25 * (players + 1) + 100 * players;

                ChuckALuckGraphics chuckALuckGrahics = new ChuckALuckGraphics();

                JFrame frame = new JFrame();
                frame.setLayout(new GridLayout(1, 1));
                frame.setResizable(false);

                chuckALuckGrahics.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGTH);

                frame.setSize(FRAMEWIDTH, FRAMEHEIGTH);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                frame.setContentPane(chuckALuckGrahics);

                frame.setVisible(true);
            }
        });
    }
}
