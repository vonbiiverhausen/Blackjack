package blackjack;

import javax.swing.*;

public class BlackjackIkkuna {
    private JFrame peliIkkuna;
    private JPanel jPanel1;
    private JLabel lbKortit;
    private JLabel lbNaytaKortit;
    private JLabel lbNaytaPisteet;
    private JLabel lbPelaaja;
    private JLabel lbPisteet;
    
    public BlackjackIkkuna() {
        peliIkkuna = new JFrame();
        jPanel1 = new JPanel();
        lbPelaaja = new JLabel();
        lbKortit = new JLabel();
        lbPisteet = new JLabel();
        lbNaytaPisteet = new JLabel();
        lbNaytaKortit = new JLabel();
        
        peliIkkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
