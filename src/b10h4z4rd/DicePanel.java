package b10h4z4rd;

import school.Wuerfel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mathias on 23.04.15.
 */
public class DicePanel extends JPanel {

    private ChuckALuckGraphics chuckALuckGraphics;
    private int num, pos;
    private Wuerfel wuerfel;

    public DicePanel(ChuckALuckGraphics chuckALuckGraphics, int pos){
        setLayout(null);
        this.chuckALuckGraphics = chuckALuckGraphics;
        this.pos = pos;
        wuerfel = new Wuerfel();
        throwDice();
    }

    public void throwDice(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        num = wuerfel.wuerfeln();
                        repaint();
                        Thread.sleep(20);
                    }

                    for (int i = 0; i < 10; i++) {
                        num = wuerfel.wuerfeln();
                        repaint();
                        Thread.sleep(100);
                    }

                    num = wuerfel.wuerfeln();
                    repaint();
                    Thread.sleep(250);
                    num = wuerfel.wuerfeln();
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                chuckALuckGraphics.diceDone(pos, num);
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth(), getHeight());

        switch (num){
            case 1:
                g.fillArc(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10, 0, 360);
                break;
            case 2:
                g.fillArc(10, 30, 10, 10, 0, 360);
                g.fillArc(30, 10, 10, 10, 0, 360);
                break;
            case 3:
                g.fillArc(30, 10, 10, 10, 0, 360);
                g.fillArc(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10, 0, 360);
                g.fillArc(10, 30, 10, 10, 0, 360);
                break;
            case 4:
                g.fillArc(10, 10, 10, 10, 0, 360);
                g.fillArc(10, 30, 10, 10, 0, 360);
                g.fillArc(30, 10, 10, 10, 0, 360);
                g.fillArc(30, 30, 10, 10, 0, 360);
                break;
            case 5:
                g.fillArc(10, 10, 10, 10, 0, 360);
                g.fillArc(10, 30, 10, 10, 0, 360);
                g.fillArc(30, 10, 10, 10, 0, 360);
                g.fillArc(30, 30, 10, 10, 0, 360);
                g.fillArc(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10, 0, 360);
                break;
            case 6:
                g.fillArc(10, 10, 10, 10, 0, 360);
                g.fillArc(10, 20, 10, 10, 0, 360);
                g.fillArc(10, 30, 10, 10, 0, 360);
                g.fillArc(30, 10, 10, 10, 0, 360);
                g.fillArc(30, 20, 10, 10, 0, 360);
                g.fillArc(30, 30, 10, 10, 0, 360);
                break;
            default:
                break;
        }
    }
}
