package b10h4z4rd;

import school.Chuckaluck;
import school.Spieler;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

/**
 * Created by Mathias on 22.04.15.
 */
public class PlayerPanel extends JPanel {

    public static final int PlayerPanelWidth = 100, PlayerPanelHeigth = 200;

    private Spieler spieler;
    private JLabel balance_label, nr_label, win_label;
    private JTextField guessField, betField;

    public PlayerPanel(int nr){
        setLayout(null);
        spieler = new Spieler(nr);

        nr_label = new JLabel("<html> <u> Player " + nr + "</u> </html>");
        Dimension size = nr_label.getPreferredSize();
        nr_label.setBounds((PlayerPanelWidth / 2) - (size.width / 2), 0, size.width, size.height);

        balance_label = new JLabel("Balance: " + spieler.getBalance());
        balance_label.setBounds(5, size.height + 10, 100, size.height);

        win_label = new JLabel("Won: " + 0);
        win_label.setBounds(5, size.height * 2 + 20, 100, size.height);

        JLabel guess_label = new JLabel("Guess:");
        Dimension size2 = guess_label.getPreferredSize();
        guess_label.setBounds(5, size.height * 3 + 35, size2.width, size.height);

        guessField = new JTextField();
        Dimension size3 = guessField.getPreferredSize();
        guessField.setBounds(size2.width + 5, size.height * 3 + 30, 90 - size2.width, size3.height);

        JLabel bet_Label = new JLabel("Bet: ");
        Dimension size4 = bet_Label.getPreferredSize();
        bet_Label.setBounds(5, size.height * 4 + 45, size4.width, size4.height);

        betField = new JTextField();
        size3 = betField.getPreferredSize();
        betField.setBounds(size4.width + 5, size.height * 4 + 40, 90 - size4.width, size3.height);

        add(nr_label);
        add(balance_label);
        add(win_label);
        add(guess_label);
        add(guessField);
        add(bet_Label);
        add(betField);
    }

    private void getGuess(){
        int i;
        if (!guessField.getText().isEmpty())
            i = Integer.parseInt(guessField.getText());
        else
            i = 0;

        spieler.setzeZahl(i);
    }

    private void getBet(){
        BigInteger bi;
        if (!betField.getText().isEmpty())
            bi = new BigInteger(betField.getText());
        else
            bi = BigInteger.ZERO;

        spieler.setzeEinsatz(bi);
    }

    public void setResult(int... num){
        if (Chuckaluck.biggerThanZero(spieler.getBalance())) {
            getGuess();
            getBet();
            spieler.setzeGewinn(spieler.gibEinsatz().multiply(BigInteger.valueOf(howOften(spieler.gibZahl(), num))));
            update();
        }
    }

    private void update(){
        balance_label.setText("Balance: " + spieler.getBalance());
        win_label.setText("Won: " + spieler.gibGewinn());

        if (spieler.gibGewinn().compareTo(BigInteger.ZERO) == -1)
            win_label.setForeground(Color.RED);
        else if (spieler.gibGewinn().compareTo(BigInteger.ZERO) == 1)
            win_label.setForeground(Color.GREEN);
        else
            win_label.setForeground(Color.BLACK);

        if (!Chuckaluck.biggerThanZero(spieler.getBalance())){
            betField.setText("");
            guessField.setText("");
            betField.setEditable(false);
            guessField.setEditable(false);
            nr_label.setForeground(Color.RED);
            ChuckALuckGraphics.players--;
        }
    }

    private static int howOften(long num, int... nums){
        int res = -1;

        if (num == nums[0])
            res++;

        if (num == nums[1])
            res++;

        if (num == nums[2])
            res++;

        return res;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
