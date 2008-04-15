/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.playground_soft.tools.font.paint;

import com.playground_soft.tools.font.ui.ColorChooserDlg;
import java.awt.Paint;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class SolidColorPaintFactory extends ColorChooserDlg implements PaintFactory {

    public SolidColorPaintFactory(JFrame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    public String toString() {
        return "Solid Color";
    }

    public Paint getPaint() {
        return getColor();
    }
}
