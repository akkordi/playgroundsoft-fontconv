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
package com.playground_soft.tools.font.ui;

import javax.swing.*;
import java.awt.*;

public class FontPanel extends JPanel {
	private static final long serialVersionUID = -4301477046578935597L;

	GlyphPanel glyphPanels[];

	public FontPanel() {
		setLayout(new GridLayout(0, 8, 5, 5));
		glyphPanels = new GlyphPanel[0x80];
		FontMetrics metrics = this.getFontMetrics(this.getFont());
		Dimension d = new Dimension(metrics.getHeight()*4,metrics.getHeight()*4);
		for (int i = 0; i < glyphPanels.length; i++) {
			glyphPanels[i] = new GlyphPanel(i);
			glyphPanels[i].setPreferredSize(d);
			this.add(glyphPanels[i]);
		}
		this.invalidate();
	}

	public void setDisplayFont(Font font) {
		FontMetrics metrics = this.getFontMetrics(font);
		Dimension d = new Dimension(metrics.getHeight(),metrics.getHeight());
		for (int i = 0; i < glyphPanels.length; i++) {
			glyphPanels[i].setFont(font);
			glyphPanels[i].setPreferredSize(d);
		}
		this.invalidate();
	}

	public Image[] getGlyphImage(){
		Image[] output = new Image[glyphPanels.length];
		for(int i = 0;i<output.length;i++){
			output[i] = glyphPanels[i].getGlyphImage();
		}
		
		return output;
	}
}
