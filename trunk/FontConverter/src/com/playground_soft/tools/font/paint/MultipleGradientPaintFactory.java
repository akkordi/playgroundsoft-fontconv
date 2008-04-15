package com.playground_soft.tools.font.paint;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import org.apache.batik.ext.awt.MultipleGradientPaint;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXGradientChooser;
import org.jdesktop.swingx.JXPanel;

public class MultipleGradientPaintFactory extends JDialog implements
		PaintFactory, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878666022768360301L;
	private JXGradientChooser chooser = new JXGradientChooser(); 
	private MultipleGradientPaint value = chooser.getGradient();
	private JXButton okBtn;
	private JXButton cancelBtn;
	
	public MultipleGradientPaintFactory(Frame frame, boolean modal) {
		super(frame, true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Multiple Gradient Paint");
        setResizable(false);
        
		this.setLayout(new BorderLayout());
		this.add(chooser,BorderLayout.CENTER);
		
		JXPanel btnPanel = new JXPanel();
		btnPanel.setLayout(new FlowLayout());
		
		okBtn = new JXButton("OK");
		cancelBtn = new JXButton("Cancel");
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		okBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		this.add(btnPanel,BorderLayout.SOUTH);
		
		pack();
	}

	@Override
	public Paint getPaint() {
		return value;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src == okBtn){
			value = chooser.getGradient();
			setVisible(false);
		} 
		else if(src == cancelBtn){
			setVisible(false);
		}	
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(visible)
			chooser.setGradient(value);
	}
	
	public String toString(){
		return "Multiple Gradient";
	}

}
