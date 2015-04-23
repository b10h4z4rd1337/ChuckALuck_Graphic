package school;

import java.math.BigInteger;
import java.util.Scanner;

public class Chuckaluck {

    private int spielerzahl;
    private int[] ergebnis;
    private Wuerfel[] wuerfel;
    private Spieler[] spieler;
    private Scanner s;

    public Chuckaluck(int anzahlSpieler) {
        spielerzahl = anzahlSpieler;
        ergebnis = new int[3];
        wuerfel = new Wuerfel[3];
        spieler = new Spieler[spielerzahl];
        s = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            wuerfel[i] = new Wuerfel();
        }

        for (int i = 0; i < spielerzahl; i++) {
            spieler[i] = new Spieler(i);
        }

        System.out.println("Jeder Spieler hat 100 Startkapital");
    }

    public void spieleEinSpiel() {
        for (int i = 0; i < spielerzahl; i++) {
            if (biggerThanZero(spieler[i].getBalance())) {
                System.out.print("Spieler " + i + ": Deine Zahl? ");
                spieler[i].setzeZahl(s.nextInt());
                System.out.print("Spieler " + i + ": Dein Einsatz? ");
                BigInteger satz = new BigInteger(s.next());

                while (satz.compareTo(spieler[i].getBalance()) == 1) {
                    System.out.print("Spieler " + i + ": DU HAST NICHT SO VIEL GELD, DU IDIOT! Versuchs nochmal: ");
                    satz = new BigInteger(s.next());
                }

                spieler[i].setzeEinsatz(satz);
            }
        }

        wuerfeln();

        for (Spieler sp : spieler) {
            if (biggerThanZero(sp.getBalance()))
                sp.setzeGewinn(sp.gibEinsatz().multiply(BigInteger.valueOf(howOften(sp.gibZahl()))));
        }

        zeigeSpielergebnis();

        if (!vorbei()) {
            System.out.println("Weiter (y/n) ?");
            String answ = s.next();
            if (answ.equals("y"))
                spieleEinSpiel();
        }
    }

    private boolean vorbei() {
        for (Spieler sp : spieler){
            if (!biggerThanZero(sp.getBalance())){
                System.out.println("Spieler " + sp.getNr() + " hat kein Geld mehr und scheidet aus!");
                spielerzahl--;
                if (spielerzahl == 1) {
                    kürreGewinner();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean biggerThanZero(BigInteger b1){
        return b1.compareTo(BigInteger.ZERO) == 1;
    }

    private void kürreGewinner(){
        for (Spieler sp : spieler)
            if (biggerThanZero(sp.getBalance()))
                System.out.println("Spieler " + sp.getNr() + " hat gewonnen!");
    }

    private void wuerfeln() {
        for (int i = 0; i < 3; i++) {
            ergebnis[i] = wuerfel[i].wuerfeln();
        }
    }

    private int howOften(long num) {
        int res = -1;

        if (num == ergebnis[0])
            res++;

        if (num == ergebnis[1])
            res++;

        if (num == ergebnis[2])
            res++;

        return res;
    }

    public void zeigeSpielergebnis() {
        System.out.println("Spielergebnis:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Würfel " + i + ": " + ergebnis[i]);
        }
        for (int i = 0; i < spielerzahl; i++) {
            if (biggerThanZero(spieler[i].getBalance()))
                System.out.println("Spieler " + i + ": gesetze Zahl " + spieler[i].gibZahl() + " / Einsatz " + spieler[i].gibEinsatz() + " / Gewinn " + spieler[i].gibGewinn() + " => Guthaben: " + spieler[i].getBalance());
        }
    }
}
