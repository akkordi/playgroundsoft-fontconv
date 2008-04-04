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

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

public class FontListCellRenderer implements ListCellRenderer {
	public Component getListCellRendererComponent(JList list,
			Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel output = new JLabel();
		Font font = (Font)value;
		output.setText(font.getFontName());
		output.setFont(font.deriveFont(16.0f));
		
		if(isSelected)	{
			output.setForeground(SystemColor.textHighlightText);
			output.setBackground(SystemColor.textHighlight);
			output.setOpaque(true);
			list.setFont(font);
		}
		if(cellHasFocus)	{
			output.setBorder(LineBorder.createBlackLineBorder() );
		}
			
		return output;
	}
}