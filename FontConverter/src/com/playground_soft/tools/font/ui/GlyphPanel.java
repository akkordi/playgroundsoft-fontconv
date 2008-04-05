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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.font.GlyphMetrics;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.playground_soft.tools.font.renderer.BasicFontRenderer;
import com.playground_soft.tools.font.renderer.FontRenderer;

public class GlyphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int character = 0;
	private FontRenderer renderer;
	private GlyphMetrics metrics;
	public GlyphPanel(int character) {
		super();
		this.character = character;
		setPreferredSize(new Dimension(100, 100));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		GradientPaint fill =new GradientPaint(0f,0f,Color.BLUE,0,50,Color.GREEN,true);
		GradientPaint outline =new GradientPaint(0f,0f,Color.GRAY,0,50,Color.BLACK,true);
		
		renderer = new BasicFontRenderer(fill, outline,
				new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
		setRenderer(renderer);
		this.setOpaque(true);
		updateMetrics();
	}

	public void setFont(Font font) {
		super.setFont(font);
		updateMetrics();
	}
	
	public Image getGlyphImage(){
		return renderer.getGlyphImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension size = this.getSize();
		Image image = renderer.getGlyphImage();
		g.drawImage(image,(size.width-image.getWidth(null))/2,
				(size.height - image.getHeight(null))/2,
				null);
	}

	public void setRenderer(FontRenderer renderer) {
		this.renderer = renderer;
		renderer.setFont(this.getFont());
	}
	
	private void updateMetrics(){
		Graphics2D graphic = (Graphics2D)this.getGraphics();
		if(graphic == null)
			return;
		renderer.setCharacter(character);
		renderer.setFont(this.getFont());

		metrics = renderer.getGlyphMetrics();
		if(metrics != null){
			String tooltip = String.format(
					"Advance-X : %1$.2f, "+
					"Advance-y : %2$.2f, "+
					"Width : %3$.2f, "+
					"Height : %4$.2f ",
					metrics.getAdvanceX(),metrics.getAdvanceY(),
					metrics.getBounds2D().getWidth(),
					metrics.getBounds2D().getHeight());
			
			this.setToolTipText(tooltip);
		}
	}
}
