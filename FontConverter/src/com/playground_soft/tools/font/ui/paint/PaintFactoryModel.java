/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.playground_soft.tools.font.ui.paint;

import com.playground_soft.tools.font.ui.paint.*;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class PaintFactoryModel extends AbstractListModel implements
		ComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7496322629638611011L;
	private Vector<PaintFactory> builder = new Vector<PaintFactory>();
	private PaintFactory selectedItem = null;

	public PaintFactoryModel(JFrame parent) {
		builder.add(new SolidColorPaintFactory(parent, true));
		builder.add(new GradientPaintFactory(parent, true));
		builder.add(new MultipleGradientPaintFactory(parent,true));
	}

	public int getSize() {
		return builder.size();
	}

	public Object getElementAt(int index) {
		return builder.elementAt(index);
	}

	public void setSelectedItem(Object anItem) {
		selectedItem = (PaintFactory) anItem;
	}

	public Object getSelectedItem() {
		return selectedItem;
	}
}
