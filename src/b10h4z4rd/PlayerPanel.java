package b10h4z4rd;

import school.Spieler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mathias on 22.04.15.
 */
public class PlayerPanel extends JPanel {

    private Spieler spieler;

    public PlayerPanel(int nr){
        setLayout(null);
        spieler = new Spieler(nr);
        JLabel nr_label = new JLabel("Player " + nr);
        Insets insets = getInsets();
        Dimension size = nr_label.getPreferredSize();
        nr_label.setBounds(insets.left, insets.top, size.width, size.height);
        add(nr_label);
    }
}
