/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.playground_soft.tools.font.ui;

import java.util.EnumSet;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Administrator
 */
public class EnumModel<E> extends AbstractListModel implements ComboBoxModel{

    private E selected = null;
    private E[] values = null;
    
    public EnumModel(Class enumClass){
        values = (E[]) EnumSet.allOf(enumClass).toArray();
    }
    public int getSize() {
        return values.length;
    }

    public Object getElementAt(int index) {
        return values[index];
    }

    public void setSelectedItem(Object anItem) {
        selected = (E)anItem;
    }

    public Object getSelectedItem() {
        return selected;
    }

}
