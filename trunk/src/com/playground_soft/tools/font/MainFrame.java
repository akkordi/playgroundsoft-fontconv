/* Playground Soft -- Font Converter
 * (c)2008 Wutipong Wongsakuldej aka 'mr_tawan'.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.playground_soft.tools.font;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import com.playground_soft.tools.font.ui.*;
public class MainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = -5563888317791064144L;
	private Font[] fonts = null;
	private JComboBox fontListCb;
	private JPanel previewPnl;
	private FontPanel fontPnl;
	private JToolBar toolBar;
	private JToolBar toolBar2;
	private JPanel toolbarPnl;
	private JSpinner sizeSpn;
	private JButton exportBtn;
	private JButton formatBtn;
	
	public MainFrame()	{
		super("Font Converter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		this.setLayout(new BorderLayout());
		GraphicsEnvironment ge = 
			GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = ge.getAllFonts();
		
		toolbarPnl = new JPanel();
		toolbarPnl.setLayout(new BoxLayout(toolbarPnl, BoxLayout.Y_AXIS));
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		toolBar2 = new JToolBar();
		toolBar2.setFloatable(false);
		toolBar2.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		exportBtn = new JButton("Export",new ImageIcon("media/images/icons/export.png"));
		exportBtn.addActionListener(this);
		
		toolBar.add(exportBtn);
		toolBar.addSeparator();
		
		toolBar2.add(new JLabel("Font : "));
		fontListCb = new JComboBox(fonts);
		fontListCb.setRenderer(new FontListCellRenderer());
		
		toolBar2.add(fontListCb);
		toolBar2.add(new JLabel("Size : "));
		sizeSpn = new JSpinner( 
				new SpinnerNumberModel(16.0f,0.0f,255.0f,0.1f));
		toolBar2.add(sizeSpn);
		
		formatBtn = new JButton("Format", new ImageIcon("media/images/icons/format.png"));
		toolBar.add(formatBtn);
		
		previewPnl = new JPanel();
		previewPnl.setLayout(new GridLayout(0,5,5,5));
		fontPnl = new FontPanel();
		
		fontListCb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateFontPnl();
				fontListCb.setFont(((Font)fontListCb.getSelectedItem()).deriveFont(16.0f));
			}
		});
		sizeSpn.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				updateFontPnl();
			}
		});
		toolbarPnl.add(toolBar);
		toolbarPnl.add(toolBar2);
		this.getContentPane().add(toolbarPnl,BorderLayout.NORTH);
		this.getContentPane().add(new JScrollPane(fontPnl), BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
		
		fontListCb.setSelectedIndex(0);
		new AboutDialog(this).setVisible(true);
	}
	public static void main(String[] args)	{
		new MainFrame();
	}
	private void updateFontPnl() {
		float size = ((Double)sizeSpn.getValue()).floatValue();
		Font font = (Font)fontListCb.getSelectedItem();
		fontPnl.setDisplayFont(font.deriveFont(size));
		fontPnl.repaint();
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == exportBtn){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
				return;
			File dir = chooser.getSelectedFile();
			Image[] images = fontPnl.getGlyphImage();

			for(int i = 0;i<images.length;i++){
				String filename = String.format("%1$#06X.png", i);
				File f = new File(dir,filename);
				
				try {
					ImageIO.write((RenderedImage)images[i], "png", f);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}
		
	}
}
