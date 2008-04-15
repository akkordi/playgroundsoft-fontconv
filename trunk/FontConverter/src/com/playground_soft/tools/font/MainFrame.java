/*
 * MainFrame2.java
 *
 * Created on April 5, 2008, 3:04 AM
 */
package com.playground_soft.tools.font;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.playground_soft.tools.font.ui.EnumModel;
import com.playground_soft.tools.font.ui.FontListCellRenderer;
import com.playground_soft.tools.font.ui.FontPanel;
import com.playground_soft.tools.font.ui.StrokeCapEnum;
import com.playground_soft.tools.font.ui.StrokeJoinEnum;
import com.playground_soft.tools.font.ui.paint.PaintFactory;
import com.playground_soft.tools.font.ui.paint.PaintFactoryModel;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.error.ErrorInfo;
import org.jvnet.substance.*;
import org.jvnet.substance.skin.SkinInfo;
import org.jvnet.substance.theme.ThemeInfo;

/**
 * 
 * @author Administrator
 */
public class MainFrame extends JXFrame implements ActionListener {

	private static final long serialVersionUID = -8931712173506781725L;
	private com.playground_soft.tools.font.ui.FontPanel fontPnl;
	private FontAttributePanel fontAttribPanel;
	private FillAttributePanel fillAttribPanel;
	private BorderAttributePanel borderAttribPanel;
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu helpMenu;

	private JMenuItem exportMenuItem;
	private JMenuItem prefMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem aboutMenuItem;

	private JXButton updateBtn;

	/** Creates new form MainFrame */
	public MainFrame() {
		try {
			Configuration.init();
			initComponents();
			updateFontPnl();
		} catch (Exception ex) {
			/*
			ErrorInfo info = new ErrorInfo("Error", "Unable to create GUI", 
					null, null, ex, Level.SEVERE, null);
			JXErrorPane.showDialog(this, info);*/
			
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
					ex);
			this.dispose();
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 */

	private void initComponents() throws UnsupportedLookAndFeelException {
		// Set Substance Look and Feel
		SubstanceLookAndFeel lookAndFeel = new SubstanceLookAndFeel();
		Map<String, SkinInfo> skins = SubstanceLookAndFeel.getAllSkins();
		String currentSkinName = Configuration.get("SkinName");
		if(currentSkinName != null)
			SubstanceLookAndFeel.setSkin(skins.get(currentSkinName).getClassName());
		
		UIManager.setLookAndFeel(lookAndFeel);

		SwingUtilities.updateComponentTreeUI(this);

		// Menu
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		helpMenu = new JMenu("Help");
		menubar.add(helpMenu);

		exportMenuItem = new JMenuItem("Export");
		fileMenu.add(exportMenuItem);
		exportMenuItem.addActionListener(this);
		fileMenu.add(new JSeparator());
		prefMenuItem = new JMenuItem("Preference");
		prefMenuItem.addActionListener(this);
		fileMenu.add(prefMenuItem);
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(this);
		fileMenu.add(exitMenuItem);

		aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(this);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		// Main/Preview Panel
		JTabbedPane mainPanel = new JTabbedPane();
		contentPane.add(mainPanel, BorderLayout.CENTER);

		fontPnl = new FontPanel();
		mainPanel.add(new JScrollPane(fontPnl), "Glyph Preview");
		// Configure Panel
		JXTaskPaneContainer configPanel = new JXTaskPaneContainer();
		contentPane.add(configPanel, BorderLayout.EAST);

		fontAttribPanel = new FontAttributePanel();
		configPanel.add(fontAttribPanel);
		fillAttribPanel = new FillAttributePanel(this);
		configPanel.add(fillAttribPanel);
		borderAttribPanel = new BorderAttributePanel(this);
		configPanel.add(borderAttribPanel);
		updateBtn = new JXButton("Update");
		configPanel.add(updateBtn);
		updateBtn.addActionListener(this);

		// Everything's done
		this.setTitle("PlaygroundSoft Font Converter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 500));
		this.pack();
	}

	private void export() {
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
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if (src == updateBtn) {
			updateFontPnl();
		} else if (src == aboutMenuItem) {
			new AboutDialog(this, true).setVisible(true);
		} else if (src == exitMenuItem) {
			this.dispose();
		} else if (src == exportMenuItem) {
			this.export();
		} else if (src == prefMenuItem){
			new ConfigurationDlg(this, true).setVisible(true);
			SwingUtilities.updateComponentTreeUI(this);
		}

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				System.setProperty("sun.awt.noerasebackground", "true");
				new MainFrame().setVisible(true);
			}
		});
	}

	private void updateFontPnl() {
		Font font = fontAttribPanel.getSelectedFont();
		fontPnl.setDisplayFont(font);

		Stroke stroke = borderAttribPanel.getStroke();
		GlyphRenderer renderer = new GlyphRenderer(fillAttribPanel.getPaint(),
				borderAttribPanel.getPaint(), stroke);
		fontPnl.setGlyphRenderer(renderer);
		fontPnl.repaint();
	}

	private class FontAttributePanel extends JXTaskPane {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3573090612291135013L;
		private JComboBox fontCb;
		private JSpinner sizeSpn;

		public FontAttributePanel() {
			setTitle("Font Attribute");
			fontCb = new JComboBox();
			fontCb.setRenderer(new FontListCellRenderer());

			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			fontCb.setModel(new DefaultComboBoxModel(ge.getAllFonts()));

			sizeSpn = new JSpinner();
			sizeSpn.setModel(new SpinnerNumberModel(32.0, 1.0, 500.0, 0.1));

			setLayout(new FormLayout("right:pref, 4dlu, default",
					"pref, 4dlu, pref"));
			CellConstraints cc = new CellConstraints();
			this.add(new JXLabel("Font :"), cc.xy(1, 1));
			this.add(fontCb, cc.xy(3, 1));
			this.add(new JXLabel("Size :"), cc.xy(1, 3));
			this.add(sizeSpn, cc.xy(3, 3));

			fontCb.setSelectedIndex(0);
		}

		public Font getSelectedFont() {
			return ((Font) fontCb.getSelectedItem())
					.deriveFont(((Double) sizeSpn.getValue()).floatValue());
		}
	}

	private class FillAttributePanel extends JXTaskPane implements
			ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -389952657735404738L;
		private JComboBox paintType;
		private JXButton configBtn;

		public FillAttributePanel(JFrame parent) {
			this.setTitle("Fill Attribute");
			paintType = new JComboBox();
			paintType.setModel(new PaintFactoryModel(parent));
			paintType.setSelectedIndex(0);
			configBtn = new JXButton("Configure");
			configBtn.addActionListener(this);
			setLayout(new FormLayout(
					"right:pref, 4dlu, default, 4dlu, default", "pref"));
			CellConstraints cc = new CellConstraints();
			this.add(new JXLabel("Painter :"), cc.xy(1, 1));
			this.add(paintType, cc.xy(3, 1));
			this.add(configBtn, cc.xy(5, 1));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JDialog dialog = (JDialog) paintType.getSelectedItem();
			dialog.setVisible(true);
		}

		public Paint getPaint() {
			return ((PaintFactory) paintType.getSelectedItem()).getPaint();
		}
	}

	private class BorderAttributePanel extends JXTaskPane implements
			ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2466523215782774202L;
		private JComboBox paintType;
		private JComboBox borderCapCb;
		private JComboBox borderJoinCb;
		private JXButton configBtn;
		private JSpinner sizeSpn;
		private JSpinner borderMiterSpn;

		public BorderAttributePanel(JFrame parent) {
			this.setTitle("Fill Attribute");
			paintType = new JComboBox();
			paintType.setModel(new PaintFactoryModel(parent));
			paintType.setSelectedIndex(0);
			configBtn = new JXButton("Configure");
			configBtn.addActionListener(this);
			sizeSpn = new JSpinner();
			sizeSpn.setModel(new SpinnerNumberModel(2.0, 0.0, 10.0, 0.1));

			borderCapCb = new JComboBox();
			borderCapCb.setModel(new EnumModel<StrokeCapEnum>(
					StrokeCapEnum.class));
			borderCapCb.setSelectedIndex(0);
			borderJoinCb = new JComboBox();
			
			borderMiterSpn = new JSpinner();
			borderMiterSpn
					.setModel(new SpinnerNumberModel(2.0, 0.0, 10.0, 0.1));
			
			borderJoinCb.setModel(new EnumModel<StrokeJoinEnum>(
					StrokeJoinEnum.class));
			borderJoinCb.addActionListener(this);
			borderJoinCb.setSelectedIndex(0);

			borderMiterSpn = new JSpinner();
			borderMiterSpn
					.setModel(new SpinnerNumberModel(2.0, 0.0, 10.0, 0.1));
			borderMiterSpn.setEnabled(false);

			setLayout(new FormLayout(
					"right:pref, 4dlu, default, 4dlu, default",
					"pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,4dlu, pref"));
			CellConstraints cc = new CellConstraints();
			this.add(new JXLabel("Painter :"), cc.xy(1, 1));
			this.add(paintType, cc.xy(3, 1));
			this.add(configBtn, cc.xy(5, 1));
			this.add(new JXLabel("Size :"), cc.xy(1, 3));
			this.add(sizeSpn, cc.xyw(3, 3, 3));
			this.add(new JXLabel("Cap :"), cc.xy(1, 5));
			this.add(borderCapCb, cc.xyw(3, 5, 3));
			this.add(new JXLabel("Join :"), cc.xy(1, 7));
			this.add(borderJoinCb, cc.xyw(3, 7, 3));
			this.add(new JXLabel("Mitter :"), cc.xy(1, 9));
			this.add(borderMiterSpn, cc.xyw(3, 9, 3));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object src = arg0.getSource();
			if(src == configBtn){
				JDialog dialog = (JDialog) paintType.getSelectedItem();
				dialog.setVisible(true);
			}
			else if( src == borderJoinCb){
				borderMiterSpn.setEnabled((StrokeJoinEnum) (borderJoinCb
						.getSelectedItem()) == StrokeJoinEnum.Miter);
			}
		}

		public Paint getPaint() {
			return ((PaintFactory) paintType.getSelectedItem()).getPaint();
		}

		public Stroke getStroke() {
			return new BasicStroke(((Double) sizeSpn.getValue()).floatValue(),
					((StrokeCapEnum) (borderCapCb.getSelectedItem()))
							.getValue(), ((StrokeJoinEnum) (borderJoinCb
							.getSelectedItem())).getValue(),
					((Double) this.borderMiterSpn.getValue()).floatValue());
		}
	}
}
