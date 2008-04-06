/*
 * MainFrame2.java
 *
 * Created on April 5, 2008, 3:04 AM
 */
package com.playground_soft.tools.font;

import com.playground_soft.tools.font.ui.EnumModel;
import com.playground_soft.tools.font.ui.FontListCellRenderer;
import com.playground_soft.tools.font.ui.PaintBuilderModel;
import com.playground_soft.tools.font.ui.StrokeCapEnum;
import com.playground_soft.tools.font.ui.StrokeJoinEnum;
import com.playground_soft.tools.font.ui.paint.PaintBuilder;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author  Administrator
 */
public class MainFrame extends javax.swing.JFrame {

    private Font[] fonts = null;
    private PaintBuilder fillPaintBuilder = null;
    private PaintBuilder borderPaintBuilder = null;

    /** Creates new form MainFrame2 */
    public MainFrame() {
        try {
            Configuration.init();
            String lnfname = Configuration.get("LookAndFeelClassname");
            if (lnfname != null) {
                UIManager.setLookAndFeel(lnfname);
            }
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            fonts = ge.getAllFonts();
            initComponents();
            fillPaintCb.setModel(new PaintBuilderModel(this));
            fillPaintCb.setSelectedIndex(0);

            borderPaintCb.setModel(new PaintBuilderModel(this));
            borderPaintCb.setSelectedIndex(0);

            borderCapCb.setModel(new EnumModel<StrokeCapEnum>(StrokeCapEnum.class));
            borderCapCb.setSelectedIndex(0);
            
            borderJoinCb.setModel(new EnumModel<StrokeJoinEnum>(StrokeJoinEnum.class));
            borderJoinCb.setSelectedIndex(0);
            
            updateFontPnl();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fontPnl = new com.playground_soft.tools.font.ui.FontPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        configPnl1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        fontListCb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        sizeSpn = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        fillPaintCb = new javax.swing.JComboBox();
        fillOptionBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        borderPaintCb = new javax.swing.JComboBox();
        borderPaintOptionBtn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        borderWidthSpn = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        borderCapCb = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        borderJoinCb = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        borderMiterSpn = new javax.swing.JSpinner();
        updateBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Playground Soft -- Font Converter (Milestone 1 Release)");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/images/export.png"))); // NOI18N
        jButton1.setText("Export");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        fontPnl.setName("fontPnl"); // NOI18N
        jScrollPane1.setViewportView(fontPnl);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Font"));

        fontListCb.setModel(new DefaultComboBoxModel(fonts) );
        fontListCb.setRenderer(new FontListCellRenderer());

        jLabel9.setText("Font");

        sizeSpn.setModel(new javax.swing.SpinnerNumberModel(24.0d, 0.0d, 255.0d, 0.10000000149011612d));

        jLabel10.setText("Size");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fontListCb, 0, 194, Short.MAX_VALUE)
                    .addComponent(sizeSpn, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fontListCb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(sizeSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Fill"));

        jLabel11.setText("Paint");

        fillPaintCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fillPaintCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillPaintCbActionPerformed(evt);
            }
        });

        fillOptionBtn.setText("Option...");
        fillOptionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillOptionBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fillPaintCb, 0, 196, Short.MAX_VALUE))
                    .addComponent(fillOptionBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(fillPaintCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillOptionBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Border"));

        jLabel12.setText("Paint");

        borderPaintCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        borderPaintCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderPaintCbActionPerformed(evt);
            }
        });

        borderPaintOptionBtn.setText("Option...");
        borderPaintOptionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderPaintOptionBtnActionPerformed(evt);
            }
        });

        jLabel13.setText("Width");

        borderWidthSpn.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), null, Float.valueOf(0.01f)));

        jLabel14.setText("Cap");

        borderCapCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Joint");

        borderJoinCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        borderJoinCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderJoinCbActionPerformed(evt);
            }
        });

        jLabel16.setText("Mitter Limit");

        borderMiterSpn.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), null, Float.valueOf(0.01f)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borderPaintOptionBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(borderCapCb, 0, 168, Short.MAX_VALUE)
                            .addComponent(borderWidthSpn, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(borderPaintCb, 0, 168, Short.MAX_VALUE)
                            .addComponent(borderJoinCb, 0, 168, Short.MAX_VALUE)
                            .addComponent(borderMiterSpn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(borderPaintCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borderPaintOptionBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(borderWidthSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(borderCapCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(borderJoinCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(borderMiterSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configPnl1Layout = new javax.swing.GroupLayout(configPnl1);
        configPnl1.setLayout(configPnl1Layout);
        configPnl1Layout.setHorizontalGroup(
            configPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        configPnl1Layout.setVerticalGroup(
            configPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateBtn)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(configPnl1);

        jMenu1.setText("File");

        jMenuItem2.setText("Export");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator3);

        jMenuItem4.setText("Configuration");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Help");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new AboutDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File dir = chooser.getSelectedFile();
        Image[] images = fontPnl.getGlyphImage();

        for (int i = 0; i < images.length; i++) {
            String filename = String.format("%1$#06X.png", i);
            File f = new File(dir, filename);

            try {
                ImageIO.write((RenderedImage) images[i], "png", f);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_exportActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new ConfigurationDlg(this, true).setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void fillOptionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillOptionBtnActionPerformed
        ((JDialog)fillPaintBuilder).setVisible(true);
}//GEN-LAST:event_fillOptionBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        this.updateFontPnl();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void fillPaintCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillPaintCbActionPerformed
       fillPaintBuilder = (PaintBuilder)fillPaintCb.getSelectedItem();
}//GEN-LAST:event_fillPaintCbActionPerformed

    private void borderPaintCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borderPaintCbActionPerformed
        borderPaintBuilder = (PaintBuilder)borderPaintCb.getSelectedItem();
    }//GEN-LAST:event_borderPaintCbActionPerformed

    private void borderPaintOptionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borderPaintOptionBtnActionPerformed
        ((JDialog)borderPaintBuilder).setVisible(true);
    }//GEN-LAST:event_borderPaintOptionBtnActionPerformed

    private void borderJoinCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borderJoinCbActionPerformed
        borderMiterSpn.setEnabled(((StrokeJoinEnum)borderJoinCb.getSelectedItem()) == StrokeJoinEnum.Miter);
}//GEN-LAST:event_borderJoinCbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private void updateFontPnl() {
        float size = ((Double) sizeSpn.getValue()).floatValue();
        Font font = (Font) fontListCb.getSelectedItem();
        fontPnl.setDisplayFont(font.deriveFont(size));
        
        BasicStroke stroke = new BasicStroke(
                ((Float)borderWidthSpn.getValue()).floatValue(),
                ((StrokeCapEnum)(borderCapCb.getSelectedItem())).getValue(),
                ((StrokeJoinEnum)(borderJoinCb.getSelectedItem())).getValue(),
                ((Float)this.borderMiterSpn.getValue()).floatValue()
                );
        GlyphRenderer renderer = new GlyphRenderer(fillPaintBuilder.getPaint(),
                borderPaintBuilder.getPaint(),
                stroke);
        fontPnl.setGlyphRenderer(renderer);
        fontPnl.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox borderCapCb;
    private javax.swing.JComboBox borderJoinCb;
    private javax.swing.JSpinner borderMiterSpn;
    private javax.swing.JComboBox borderPaintCb;
    private javax.swing.JButton borderPaintOptionBtn;
    private javax.swing.JSpinner borderWidthSpn;
    private javax.swing.JPanel configPnl1;
    private javax.swing.JButton fillOptionBtn;
    private javax.swing.JComboBox fillPaintCb;
    private javax.swing.JComboBox fontListCb;
    private com.playground_soft.tools.font.ui.FontPanel fontPnl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JSpinner sizeSpn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
