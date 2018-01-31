/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echec;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel
 */
public class Echec extends javax.swing.JFrame {

    private Partie partie = null;
    private Vector2 iniPos, nouvPos;
    private JButton[][] cases = new JButton[8][8];
    private Hashtable<String, Icon> images = new Hashtable<>();
    
    /**
     * Creates new form Echec
     */
    public Echec() {
        initComponents();
        chargerImages();
        IAAbstraite ia = choixModeJeu();
        String config = chargerConfiguration();
        
        if(config == null)
        {
            this.partie = new Partie(ia);
        }
        else
        {
            try
            {
                this.partie = new Partie(config, ia);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Impossible de charger la configuration : " + e.getMessage(), "Erreur de lecture de la configuration", JOptionPane.ERROR_MESSAGE);
                this.partie = new Partie(ia);
            }
        }
        
        updatePlateau();
    }
    
    private void updatePlateau()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if((i + j) % 2 == 0)
                {
                    cases[i][j].setBackground(new Color(240, 240, 240));
                }
                else
                {
                    cases[i][j].setBackground(new Color(102, 102, 102));
                }
                Piece p = partie.getPlateau().getCase(new Vector2(i, j));
                Icon icon = (p != null) ? images.get(p.getImageStr()) : null;
                
                cases[i][j].setIcon(icon);
            }
        }
    }
    
    private void chargerImages()
    {
        
        images.put("PionBlanc", new ImageIcon(new ImageIcon("src/res/images/pion_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("TourBlanc", new ImageIcon(new ImageIcon("src/res/images/tour_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("CavalierBlanc", new ImageIcon(new ImageIcon("src/res/images/cavalier_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("FouBlanc", new ImageIcon(new ImageIcon("src/res/images/fou_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("DameBlanc", new ImageIcon(new ImageIcon("src/res/images/dame_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("RoiBlanc", new ImageIcon(new ImageIcon("src/res/images/roi_blanc.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("PionNoir", new ImageIcon(new ImageIcon("src/res/images/pion_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("TourNoir", new ImageIcon(new ImageIcon("src/res/images/tour_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("CavalierNoir", new ImageIcon(new ImageIcon("src/res/images/cavalier_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("FouNoir", new ImageIcon(new ImageIcon("src/res/images/fou_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("DameNoir", new ImageIcon(new ImageIcon("src/res/images/dame_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        images.put("RoiNoir", new ImageIcon(new ImageIcon("src/res/images/roi_noir.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    }
    
    private IAAbstraite choixModeJeu()
    {
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous jouer contre l'ordinateur ?", "Choix du mode de jeu", JOptionPane.YES_NO_OPTION);

        if(reponse == JOptionPane.YES_OPTION)
        {
            return new IAAleatoire(Couleur.NOIR);
        }

        return null;
    }
    
    private String chargerConfiguration()
    {
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous charger une configuration ?", "Choix de la configuration", JOptionPane.YES_NO_OPTION);

        if(reponse == JOptionPane.YES_OPTION)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("src/res/configs"));
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
               return (chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName());
            }
        }

        return null;
    }
    
    private class ButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            JButton bouton = (JButton) evt.getSource();
            String[] name = bouton.getName().split("");
            Vector2 pos = new Vector2(Integer.valueOf(name[0]), Integer.valueOf(name[1]));
            
            if(iniPos == null)
            {
                iniPos = pos;
                Piece p = partie.getPlateau().getCase(iniPos);
                if(partie.getPlateau().getPieces(partie.getJoueurActuel()).contains(p))
                {
                    List<Vector2> casesJouables = p.getCasesJouables(partie.getPlateau());
                    for(Vector2 v : casesJouables)
                    {
                        cases[v.x][v.y].setBackground(new Color(0, 200, 0));
                    }
                }
                cases[iniPos.x][iniPos.y].setEnabled(false);
            }
            else
            {
                cases[iniPos.x][iniPos.y].setEnabled(true);
                nouvPos = pos;
                if(!nouvPos.equals(iniPos))
                {
                    if(partie.jouerTour(iniPos, nouvPos))
                    {
                        JOptionPane.showMessageDialog(null, "Le Joueur " + partie.getJoueurActuel() + " a gagné !");
                    }
                }
                iniPos = null;
                updatePlateau();
            }
            
            IAAbstraite ia = partie.getIA();
            
            if(ia != null && partie.getJoueurActuel().equals(ia.getCouleur()))
            {
                Vector2[] tabPos = ia.getCoupIA(partie.getPlateau());
                
                if(partie.jouerTour(tabPos[0], tabPos[1]))
                {
                    JOptionPane.showMessageDialog(null, "Le Joueur " + partie.getJoueurActuel() + " a gagné !");
                }
                updatePlateau();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        case_01 = new javax.swing.JButton();
        case_02 = new javax.swing.JButton();
        case_03 = new javax.swing.JButton();
        case_04 = new javax.swing.JButton();
        case_05 = new javax.swing.JButton();
        case_06 = new javax.swing.JButton();
        case_07 = new javax.swing.JButton();
        case_10 = new javax.swing.JButton();
        case_11 = new javax.swing.JButton();
        case_12 = new javax.swing.JButton();
        case_13 = new javax.swing.JButton();
        case_14 = new javax.swing.JButton();
        case_15 = new javax.swing.JButton();
        case_16 = new javax.swing.JButton();
        case_17 = new javax.swing.JButton();
        case_20 = new javax.swing.JButton();
        case_37 = new javax.swing.JButton();
        case_21 = new javax.swing.JButton();
        case_30 = new javax.swing.JButton();
        case_31 = new javax.swing.JButton();
        case_22 = new javax.swing.JButton();
        case_32 = new javax.swing.JButton();
        case_23 = new javax.swing.JButton();
        case_33 = new javax.swing.JButton();
        case_24 = new javax.swing.JButton();
        case_34 = new javax.swing.JButton();
        case_25 = new javax.swing.JButton();
        case_35 = new javax.swing.JButton();
        case_26 = new javax.swing.JButton();
        case_36 = new javax.swing.JButton();
        case_27 = new javax.swing.JButton();
        case_40 = new javax.swing.JButton();
        case_50 = new javax.swing.JButton();
        case_60 = new javax.swing.JButton();
        case_70 = new javax.swing.JButton();
        case_71 = new javax.swing.JButton();
        case_61 = new javax.swing.JButton();
        case_51 = new javax.swing.JButton();
        case_41 = new javax.swing.JButton();
        case_42 = new javax.swing.JButton();
        case_52 = new javax.swing.JButton();
        case_62 = new javax.swing.JButton();
        case_72 = new javax.swing.JButton();
        case_73 = new javax.swing.JButton();
        case_63 = new javax.swing.JButton();
        case_53 = new javax.swing.JButton();
        case_43 = new javax.swing.JButton();
        case_44 = new javax.swing.JButton();
        case_54 = new javax.swing.JButton();
        case_64 = new javax.swing.JButton();
        case_74 = new javax.swing.JButton();
        case_75 = new javax.swing.JButton();
        case_65 = new javax.swing.JButton();
        case_55 = new javax.swing.JButton();
        case_45 = new javax.swing.JButton();
        case_46 = new javax.swing.JButton();
        case_56 = new javax.swing.JButton();
        case_66 = new javax.swing.JButton();
        case_76 = new javax.swing.JButton();
        case_77 = new javax.swing.JButton();
        case_67 = new javax.swing.JButton();
        case_57 = new javax.swing.JButton();
        case_47 = new javax.swing.JButton();
        case_00 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jeu d'Echec");
        setPreferredSize(new java.awt.Dimension(470, 495));
        setResizable(false);

        case_01.setName("01"); // NOI18N
        case_01.setPreferredSize(new java.awt.Dimension(50, 50));

        case_02.setBackground(new java.awt.Color(102, 102, 102));
        case_02.setName("02"); // NOI18N
        case_02.setPreferredSize(new java.awt.Dimension(50, 50));

        case_03.setName("03"); // NOI18N
        case_03.setPreferredSize(new java.awt.Dimension(50, 50));

        case_04.setBackground(new java.awt.Color(102, 102, 102));
        case_04.setActionCommand("case_02");
        case_04.setName("04"); // NOI18N
        case_04.setPreferredSize(new java.awt.Dimension(50, 50));

        case_05.setName("05"); // NOI18N
        case_05.setPreferredSize(new java.awt.Dimension(50, 50));

        case_06.setBackground(new java.awt.Color(102, 102, 102));
        case_06.setActionCommand("case_02");
        case_06.setName("06"); // NOI18N
        case_06.setPreferredSize(new java.awt.Dimension(50, 50));

        case_07.setName("07"); // NOI18N
        case_07.setPreferredSize(new java.awt.Dimension(50, 50));

        case_10.setName("10"); // NOI18N
        case_10.setPreferredSize(new java.awt.Dimension(50, 50));

        case_11.setBackground(new java.awt.Color(102, 102, 102));
        case_11.setName("11"); // NOI18N
        case_11.setPreferredSize(new java.awt.Dimension(50, 50));

        case_12.setName("12"); // NOI18N
        case_12.setPreferredSize(new java.awt.Dimension(50, 50));

        case_13.setBackground(new java.awt.Color(102, 102, 102));
        case_13.setName("13"); // NOI18N
        case_13.setPreferredSize(new java.awt.Dimension(50, 50));

        case_14.setActionCommand("case_02");
        case_14.setName("14"); // NOI18N
        case_14.setPreferredSize(new java.awt.Dimension(50, 50));

        case_15.setBackground(new java.awt.Color(102, 102, 102));
        case_15.setName("15"); // NOI18N
        case_15.setPreferredSize(new java.awt.Dimension(50, 50));

        case_16.setActionCommand("case_02");
        case_16.setName("16"); // NOI18N
        case_16.setPreferredSize(new java.awt.Dimension(50, 50));

        case_17.setBackground(new java.awt.Color(102, 102, 102));
        case_17.setName("17"); // NOI18N
        case_17.setPreferredSize(new java.awt.Dimension(50, 50));

        case_20.setBackground(new java.awt.Color(102, 102, 102));
        case_20.setName("20"); // NOI18N
        case_20.setPreferredSize(new java.awt.Dimension(50, 50));

        case_37.setBackground(new java.awt.Color(102, 102, 102));
        case_37.setName("37"); // NOI18N
        case_37.setPreferredSize(new java.awt.Dimension(50, 50));

        case_21.setName("21"); // NOI18N
        case_21.setPreferredSize(new java.awt.Dimension(50, 50));

        case_30.setName("30"); // NOI18N
        case_30.setPreferredSize(new java.awt.Dimension(50, 50));

        case_31.setBackground(new java.awt.Color(102, 102, 102));
        case_31.setName("31"); // NOI18N
        case_31.setPreferredSize(new java.awt.Dimension(50, 50));

        case_22.setBackground(new java.awt.Color(102, 102, 102));
        case_22.setName("22"); // NOI18N
        case_22.setPreferredSize(new java.awt.Dimension(50, 50));

        case_32.setName("32"); // NOI18N
        case_32.setPreferredSize(new java.awt.Dimension(50, 50));

        case_23.setName("23"); // NOI18N
        case_23.setPreferredSize(new java.awt.Dimension(50, 50));

        case_33.setBackground(new java.awt.Color(102, 102, 102));
        case_33.setName("33"); // NOI18N
        case_33.setPreferredSize(new java.awt.Dimension(50, 50));

        case_24.setBackground(new java.awt.Color(102, 102, 102));
        case_24.setActionCommand("case_02");
        case_24.setName("24"); // NOI18N
        case_24.setPreferredSize(new java.awt.Dimension(50, 50));

        case_34.setActionCommand("case_02");
        case_34.setName("34"); // NOI18N
        case_34.setPreferredSize(new java.awt.Dimension(50, 50));

        case_25.setName("25"); // NOI18N
        case_25.setPreferredSize(new java.awt.Dimension(50, 50));

        case_35.setBackground(new java.awt.Color(102, 102, 102));
        case_35.setName("35"); // NOI18N
        case_35.setPreferredSize(new java.awt.Dimension(50, 50));

        case_26.setBackground(new java.awt.Color(102, 102, 102));
        case_26.setActionCommand("case_02");
        case_26.setName("26"); // NOI18N
        case_26.setPreferredSize(new java.awt.Dimension(50, 50));

        case_36.setActionCommand("case_02");
        case_36.setName("36"); // NOI18N
        case_36.setPreferredSize(new java.awt.Dimension(50, 50));

        case_27.setName("27"); // NOI18N
        case_27.setPreferredSize(new java.awt.Dimension(50, 50));

        case_40.setBackground(new java.awt.Color(102, 102, 102));
        case_40.setName("40"); // NOI18N
        case_40.setPreferredSize(new java.awt.Dimension(50, 50));

        case_50.setName("50"); // NOI18N
        case_50.setPreferredSize(new java.awt.Dimension(50, 50));

        case_60.setBackground(new java.awt.Color(102, 102, 102));
        case_60.setName("60"); // NOI18N
        case_60.setPreferredSize(new java.awt.Dimension(50, 50));

        case_70.setName("70"); // NOI18N
        case_70.setPreferredSize(new java.awt.Dimension(50, 50));

        case_71.setBackground(new java.awt.Color(102, 102, 102));
        case_71.setName("71"); // NOI18N
        case_71.setPreferredSize(new java.awt.Dimension(50, 50));

        case_61.setName("61"); // NOI18N
        case_61.setPreferredSize(new java.awt.Dimension(50, 50));

        case_51.setBackground(new java.awt.Color(102, 102, 102));
        case_51.setName("51"); // NOI18N
        case_51.setPreferredSize(new java.awt.Dimension(50, 50));

        case_41.setName("41"); // NOI18N
        case_41.setPreferredSize(new java.awt.Dimension(50, 50));

        case_42.setBackground(new java.awt.Color(102, 102, 102));
        case_42.setName("42"); // NOI18N
        case_42.setPreferredSize(new java.awt.Dimension(50, 50));

        case_52.setName("52"); // NOI18N
        case_52.setPreferredSize(new java.awt.Dimension(50, 50));

        case_62.setBackground(new java.awt.Color(102, 102, 102));
        case_62.setName("62"); // NOI18N
        case_62.setPreferredSize(new java.awt.Dimension(50, 50));

        case_72.setName("72"); // NOI18N
        case_72.setPreferredSize(new java.awt.Dimension(50, 50));

        case_73.setBackground(new java.awt.Color(102, 102, 102));
        case_73.setName("73"); // NOI18N
        case_73.setPreferredSize(new java.awt.Dimension(50, 50));

        case_63.setName("63"); // NOI18N
        case_63.setPreferredSize(new java.awt.Dimension(50, 50));

        case_53.setBackground(new java.awt.Color(102, 102, 102));
        case_53.setName("53"); // NOI18N
        case_53.setPreferredSize(new java.awt.Dimension(50, 50));

        case_43.setName("43"); // NOI18N
        case_43.setPreferredSize(new java.awt.Dimension(50, 50));

        case_44.setBackground(new java.awt.Color(102, 102, 102));
        case_44.setActionCommand("case_02");
        case_44.setName("44"); // NOI18N
        case_44.setPreferredSize(new java.awt.Dimension(50, 50));

        case_54.setActionCommand("case_02");
        case_54.setName("54"); // NOI18N
        case_54.setPreferredSize(new java.awt.Dimension(50, 50));

        case_64.setBackground(new java.awt.Color(102, 102, 102));
        case_64.setActionCommand("case_02");
        case_64.setName("64"); // NOI18N
        case_64.setPreferredSize(new java.awt.Dimension(50, 50));

        case_74.setActionCommand("case_02");
        case_74.setName("74"); // NOI18N
        case_74.setPreferredSize(new java.awt.Dimension(50, 50));

        case_75.setBackground(new java.awt.Color(102, 102, 102));
        case_75.setName("75"); // NOI18N
        case_75.setPreferredSize(new java.awt.Dimension(50, 50));

        case_65.setName("65"); // NOI18N
        case_65.setPreferredSize(new java.awt.Dimension(50, 50));

        case_55.setBackground(new java.awt.Color(102, 102, 102));
        case_55.setName("55"); // NOI18N
        case_55.setPreferredSize(new java.awt.Dimension(50, 50));

        case_45.setName("45"); // NOI18N
        case_45.setPreferredSize(new java.awt.Dimension(50, 50));

        case_46.setBackground(new java.awt.Color(102, 102, 102));
        case_46.setActionCommand("case_02");
        case_46.setName("46"); // NOI18N
        case_46.setPreferredSize(new java.awt.Dimension(50, 50));

        case_56.setActionCommand("case_02");
        case_56.setName("56"); // NOI18N
        case_56.setPreferredSize(new java.awt.Dimension(50, 50));

        case_66.setBackground(new java.awt.Color(102, 102, 102));
        case_66.setActionCommand("case_02");
        case_66.setName("66"); // NOI18N
        case_66.setPreferredSize(new java.awt.Dimension(50, 50));

        case_76.setActionCommand("case_02");
        case_76.setName("76"); // NOI18N
        case_76.setPreferredSize(new java.awt.Dimension(50, 50));

        case_77.setBackground(new java.awt.Color(102, 102, 102));
        case_77.setName("77"); // NOI18N
        case_77.setPreferredSize(new java.awt.Dimension(50, 50));

        case_67.setName("67"); // NOI18N
        case_67.setPreferredSize(new java.awt.Dimension(50, 50));

        case_57.setBackground(new java.awt.Color(102, 102, 102));
        case_57.setName("57"); // NOI18N
        case_57.setPreferredSize(new java.awt.Dimension(50, 50));

        case_47.setName("47"); // NOI18N
        case_47.setPreferredSize(new java.awt.Dimension(50, 50));

        case_00.setBackground(new java.awt.Color(102, 102, 102));
        case_00.setName("00"); // NOI18N
        case_00.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(case_00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_06, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(case_07, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(case_53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(case_57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(case_00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_06, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(case_07, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(case_03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(case_04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(case_05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cases[0][0] = case_00;
        cases[0][1] = case_01;
        cases[0][2] = case_02;
        cases[0][3] = case_03;
        cases[0][4] = case_04;
        cases[0][5] = case_05;
        cases[0][6] = case_06;
        cases[0][7] = case_07;

        cases[1][0] = case_10;
        cases[1][1] = case_11;
        cases[1][2] = case_12;
        cases[1][3] = case_13;
        cases[1][4] = case_14;
        cases[1][5] = case_15;
        cases[1][6] = case_16;
        cases[1][7] = case_17;

        cases[2][0] = case_20;
        cases[2][1] = case_21;
        cases[2][2] = case_22;
        cases[2][3] = case_23;
        cases[2][4] = case_24;
        cases[2][5] = case_25;
        cases[2][6] = case_26;
        cases[2][7] = case_27;

        cases[3][0] = case_30;
        cases[3][1] = case_31;
        cases[3][2] = case_32;
        cases[3][3] = case_33;
        cases[3][4] = case_34;
        cases[3][5] = case_35;
        cases[3][6] = case_36;
        cases[3][7] = case_37;

        cases[4][0] = case_40;
        cases[4][1] = case_41;
        cases[4][2] = case_42;
        cases[4][3] = case_43;
        cases[4][4] = case_44;
        cases[4][5] = case_45;
        cases[4][6] = case_46;
        cases[4][7] = case_47;

        cases[5][0] = case_50;
        cases[5][1] = case_51;
        cases[5][2] = case_52;
        cases[5][3] = case_53;
        cases[5][4] = case_54;
        cases[5][5] = case_55;
        cases[5][6] = case_56;
        cases[5][7] = case_57;

        cases[6][0] = case_60;
        cases[6][1] = case_61;
        cases[6][2] = case_62;
        cases[6][3] = case_63;
        cases[6][4] = case_64;
        cases[6][5] = case_65;
        cases[6][6] = case_66;
        cases[6][7] = case_67;

        cases[7][0] = case_70;
        cases[7][1] = case_71;
        cases[7][2] = case_72;
        cases[7][3] = case_73;
        cases[7][4] = case_74;
        cases[7][5] = case_75;
        cases[7][6] = case_76;
        cases[7][7] = case_77;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                cases[i][j].addActionListener(new ButtonActionListener());
            }
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Echec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Echec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Echec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Echec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Echec().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton case_00;
    private javax.swing.JButton case_01;
    private javax.swing.JButton case_02;
    private javax.swing.JButton case_03;
    private javax.swing.JButton case_04;
    private javax.swing.JButton case_05;
    private javax.swing.JButton case_06;
    private javax.swing.JButton case_07;
    private javax.swing.JButton case_10;
    private javax.swing.JButton case_11;
    private javax.swing.JButton case_12;
    private javax.swing.JButton case_13;
    private javax.swing.JButton case_14;
    private javax.swing.JButton case_15;
    private javax.swing.JButton case_16;
    private javax.swing.JButton case_17;
    private javax.swing.JButton case_20;
    private javax.swing.JButton case_21;
    private javax.swing.JButton case_22;
    private javax.swing.JButton case_23;
    private javax.swing.JButton case_24;
    private javax.swing.JButton case_25;
    private javax.swing.JButton case_26;
    private javax.swing.JButton case_27;
    private javax.swing.JButton case_30;
    private javax.swing.JButton case_31;
    private javax.swing.JButton case_32;
    private javax.swing.JButton case_33;
    private javax.swing.JButton case_34;
    private javax.swing.JButton case_35;
    private javax.swing.JButton case_36;
    private javax.swing.JButton case_37;
    private javax.swing.JButton case_40;
    private javax.swing.JButton case_41;
    private javax.swing.JButton case_42;
    private javax.swing.JButton case_43;
    private javax.swing.JButton case_44;
    private javax.swing.JButton case_45;
    private javax.swing.JButton case_46;
    private javax.swing.JButton case_47;
    private javax.swing.JButton case_50;
    private javax.swing.JButton case_51;
    private javax.swing.JButton case_52;
    private javax.swing.JButton case_53;
    private javax.swing.JButton case_54;
    private javax.swing.JButton case_55;
    private javax.swing.JButton case_56;
    private javax.swing.JButton case_57;
    private javax.swing.JButton case_60;
    private javax.swing.JButton case_61;
    private javax.swing.JButton case_62;
    private javax.swing.JButton case_63;
    private javax.swing.JButton case_64;
    private javax.swing.JButton case_65;
    private javax.swing.JButton case_66;
    private javax.swing.JButton case_67;
    private javax.swing.JButton case_70;
    private javax.swing.JButton case_71;
    private javax.swing.JButton case_72;
    private javax.swing.JButton case_73;
    private javax.swing.JButton case_74;
    private javax.swing.JButton case_75;
    private javax.swing.JButton case_76;
    private javax.swing.JButton case_77;
    // End of variables declaration//GEN-END:variables
}
