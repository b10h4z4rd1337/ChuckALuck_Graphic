package b10h4z4rd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mathias on 22.04.15.
 */
public class ChuckALuckGraphics extends JPanel implements ActionListener{

    public ChuckALuckGraphics(){
        setLayout(null);
        JButton b1 = new JButton("Go");
        PlayerPanel playerPanel = new PlayerPanel(0);

        b1.addActionListener(this);

        Insets insets = getInsets();
        Dimension size = b1.getPreferredSize();
        b1.setBounds(380 - size.width - insets.left, 325 + insets.top, size.width, size.height);
        playerPanel.setBounds(insets.left, insets.top, 100, 400);

        add(b1);
        add(playerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChuckALuckGraphics chuckALuckGrahics = new ChuckALuckGraphics();

                JFrame frame = new JFrame();
                frame.setLayout(new GridLayout(1, 1));

                Insets insets = frame.getInsets();
                chuckALuckGrahics.setBounds(insets.left, insets.top, 400, 400);

                frame.setSize(400, 400);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                frame.setContentPane(chuckALuckGrahics);

                frame.setVisible(true);
            }
        });
    }
}
