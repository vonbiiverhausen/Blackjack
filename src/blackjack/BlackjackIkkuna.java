package blackjack;

import javax.swing.*;
import java.awt.*;

public class BlackjackIkkuna extends JFrame {
    private JFrame peliIkkuna;
    private JPanel peliPaneeli;
    private JLabel lbKortit;
    private JLabel lbNaytaKortit;
    private JLabel lbNaytaPisteet;
    private JLabel lbPelaaja;
    private JLabel lbPisteet;
    
    public BlackjackIkkuna() {
        peliIkkuna = new JFrame();
        peliPaneeli = new JPanel();
        lbPelaaja = new JLabel();
        lbKortit = new JLabel();
        lbPisteet = new JLabel();
        lbNaytaPisteet = new JLabel();
        lbNaytaKortit = new JLabel();
        
        peliIkkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lbPelaaja.setText("Pelaaja");
        lbKortit.setText("Kortit: ");
        lbPisteet.setText("Pisteet: ");
        lbNaytaPisteet.setText("Pisteet tähän");
        
//        peliIkkuna.add(peliPaneeli);
//        peliPaneeli.add(lbPelaaja);
//        peliPaneeli.add(lbKortit);
//        peliPaneeli.add(lbPisteet);
//        peliPaneeli.add(lbNaytaPisteet);
//        peliPaneeli.add(lbNaytaKortit);
        
        GroupLayout peliPaneeliLayout = new GroupLayout(peliPaneeli);
        peliPaneeli.setLayout(peliPaneeliLayout);
        peliPaneeliLayout.setHorizontalGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(peliPaneeliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(peliPaneeliLayout.createSequentialGroup()
                        .addComponent(lbPisteet)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNaytaPisteet))
                    .addGroup(peliPaneeliLayout.createSequentialGroup()
                        .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbKortit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPelaaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNaytaKortit)))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        
        peliPaneeliLayout.setVerticalGroup(
            peliPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peliPaneeliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPelaaja)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKortit)
                    .addComponent(lbNaytaKortit))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPisteet)
                    .addComponent(lbNaytaPisteet))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(peliPaneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(peliPaneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        peliIkkuna.add(peliPaneeli);
        peliIkkuna.setSize(400, 400);
        peliIkkuna.setVisible(true);
        pack();
    }
}
