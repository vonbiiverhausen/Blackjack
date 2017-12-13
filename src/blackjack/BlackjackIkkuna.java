package blackjack;

import javax.swing.*;

public class BlackjackIkkuna extends JFrame {
    private JFrame peliIkkuna;
    JPanel mainPanel;
    GroupLayout mainPanelLayout;

    private JMenu mPeli;
    private JMenuBar mbMain;
    JMenuItem miLopeta;
    JMenuItem miPelaa;
    
    GroupLayout layout;
    
    public BlackjackIkkuna() {
        peliIkkuna = new JFrame();
        mainPanel = new JPanel();
        mainPanelLayout = new GroupLayout(mainPanel);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        peliIkkuna.add(mainPanel);

        mbMain = new JMenuBar();
        mPeli = new JMenu();
        miPelaa = new JMenuItem();
        miLopeta = new JMenuItem();
        
        mPeli.setText("Peli");
        miPelaa.setText("Pelaa");
        mPeli.add(miPelaa);
        miLopeta.setText("Lopeta");
        mPeli.add(miLopeta);
        mbMain.add(mPeli);
        setJMenuBar(mbMain);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack");
        setPreferredSize(new java.awt.Dimension(400, 300));
        
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);

        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        peliIkkuna.setSize(400, 400);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }
    
    public void lisaaPaneeli(JPanel... paneeli) {

        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(paneeli[0], javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneeli[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneeli[0], javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
            .addComponent(paneeli[1], javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );
        
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//        );
    }
}
