package blackjack;
import javax.swing.*;
import java.awt.*;

public class AsetaPanos extends JFrame {
    private int panos,
            maxPanos = 50,
            minPanos = 0;
    private JButton btnAloitaPeli,
            btnMinus1,
            btnMinus5,
            btnPeruuta,
            btnPlus1,
            btnPlus5;
    private JLabel lbPanos;
    private JTextField tfPanos;
    private PeliMoottori pelimoottori;
    
    public int haePanos() {
        return this.panos;
    }
    
    public void muutaPanos(int muutos) {
        // Jos muutos ylittää maksimiarvon, panos asetetaan maksimipanokseksi
        // Jos muutos alittaa minimiarvon, panos asetetaan minimipanokseksi
        if (this.panos + muutos > maxPanos) {
            this.panos = maxPanos;
        } else if (this.panos + muutos < minPanos) {
            this.panos = minPanos;
        } else {
            this.panos += muutos;
        }
        
        naytaPanos();
    }
    
    public void naytaPanos() {
        this.tfPanos.setText(String.valueOf(this.panos));
    }
    
    public void piilotaIkkuna() {
        this.setVisible(false);
    }
    
    public void asetaPelimoottori(PeliMoottori pm) {
        this.pelimoottori = pm;
    }
    
    public AsetaPanos() {
        this.panos = 1;
        btnPlus5 = new JButton();
        btnPlus1 = new JButton();
        btnMinus1 = new JButton();
        btnMinus5 = new JButton();
        lbPanos = new JLabel();
        btnAloitaPeli = new JButton();
        btnPeruuta = new JButton();
        tfPanos = new JTextField();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        btnPlus5.setText("+5");
        btnPlus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // +5 -painikkeen toiminto
                muutaPanos(5);
            }
        });

        btnPlus1.setText("+1");
        btnPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // +1 painikkeen toiminto
                muutaPanos(1);
            }
        });

        btnMinus1.setText("-1");
        btnMinus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // -1 -painikkeen toiminto
                muutaPanos(-1);
            }
        });

        btnMinus5.setText("-5");
        btnMinus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // -5 -painikkeen toiminto
                muutaPanos(-5);
            }
        });

        lbPanos.setText("Aseta panos ( "+minPanos+" - "+maxPanos+" )");

        btnAloitaPeli.setText("Aloita peli");
        btnAloitaPeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Aloita peli-painikkeen toiminto
                // Aloita uusi peli määrätyllä panoksella
                // Piilota Panos-ikkuna
                pelimoottori.aloitaPeli(haePanos());
                piilotaIkkuna();
                
            }
        });

        btnPeruuta.setText("Peruuta");
        btnPeruuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Peruuta-painikkeen toiminto
                // Piilota Panos-ikkuna
                piilotaIkkuna();
            }
        });

        tfPanos.setEditable(false);
        tfPanos.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tfPanos.setHorizontalAlignment(JTextField.CENTER);
        tfPanos.setText(String.valueOf(haePanos()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tfPanos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lbPanos)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAloitaPeli, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnPeruuta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnPlus5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPlus1/*, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE*/)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnMinus1/*, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE*/)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnMinus5/*, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE*/))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPanos)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPanos, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(btnPlus5)
                    .addComponent(btnPlus1)
                    .addComponent(btnMinus1)
                    .addComponent(btnMinus5))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAloitaPeli)
                    .addComponent(btnPeruuta))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
}
