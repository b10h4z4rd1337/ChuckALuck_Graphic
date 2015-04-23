package school;

import java.math.BigInteger;

public class Spieler {
    private int nr;
    private BigInteger es; //Einsatz
    private int z;  //gesetzte Zahl
    private BigInteger g;  //Gewinn
    private BigInteger balance;

    public Spieler(int nr) {
        this.nr = nr;
        balance = BigInteger.valueOf(100);
        g = BigInteger.ZERO;
        es = BigInteger.ZERO;
    }

    public void setzeEinsatz(BigInteger einsatz) {
        es = einsatz;
    }
    
    public void setzeZahl(int zahl) {
        z = zahl;
    }
    
    public void setzeGewinn(BigInteger gewinn) {
        g = gewinn;
        setBalance(getBalance().add(gibGewinn()));
    }
    
    public BigInteger gibEinsatz() {
        return es;
    }

    public long gibZahl() {
        return z;
    }

    public BigInteger gibGewinn() {
        return g;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }
}
