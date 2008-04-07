/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.playground_soft.tools.font.ui;

import com.playground_soft.tools.font.ui.paint.*;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class PaintBuilderModel extends AbstractListModel implements ComboBoxModel {

    private Vector<PaintBuilder> builder = new Vector<PaintBuilder>();
    private PaintBuilder selectedItem = null;

    public PaintBuilderModel(JFrame parent) {
        builder.add(new SolidColorPaintBuilder(parent, true));
        builder.add(new GradientPaintBuilder(parent,true));
    }

    public int getSize() {
        return builder.size();
    }

    public Object getElementAt(int index) {
        return builder.elementAt(index);
    }

    public void setSelectedItem(Object anItem) {
        selectedItem = (PaintBuilder) anItem;
    }

    public Object getSelectedItem() {
        return selectedItem;
    }
}
