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
import java.awt.font.GlyphMetrics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.playground_soft.tools.font.GlyphRenderer;
import com.playground_soft.tools.font.RasterGlyph;

public class GlyphPanel extends JPanel {
	private int character = 0;
	private GlyphRenderer renderer;
	private GlyphMetrics metrics;

	public GlyphPanel(int character) {
		super();
		this.character = character;
		setPreferredSize(new Dimension(100, 100));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GradientPaint fill = new GradientPaint(0f, 0f, Color.BLUE, 0, 50,
				Color.GREEN, true);
		GradientPaint outline = new GradientPaint(0f, 0f, Color.GRAY, 0, 50,
				Color.BLACK, true);

		renderer = new GlyphRenderer(fill, outline, new BasicStroke(2.0f,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		setRenderer(renderer);
		this.setOpaque(true);

	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		updateMetrics();
	}

	public Image getGlyphImage() {
		return renderer.getGlyphImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension size = this.getSize();
		Image image = renderer.getGlyphImage();
		if (image == null)
			return;
		g.drawImage(image, (size.width - image.getWidth(null)) / 2,
				(size.height - image.getHeight(null)) / 2, null);
	}

	public void setRenderer(GlyphRenderer renderer) {
		this.renderer = renderer;
		renderer.setFont(this.getFont());
		updateMetrics();
	}

	private void updateMetrics() {

		Graphics2D graphic = (Graphics2D) this.getGraphics();
		if (graphic == null) {
			return;
		}
		renderer.setCharacter(character);
		renderer.setFont(this.getFont());

		metrics = renderer.getGlyphMetrics();
		RasterGlyph glyph = renderer.getGlyph();

		if (metrics != null) {
			String tooltip = String.format(
					"Char Code : %1$#06X, "
					+ "Advance-X : %2$.2f, "
					+ "Advance-y : %3$.2f, "
					+ "Bearing-X : %4$.2f, " 
					+ "Bearing-Y : %5$.2f ", 
					glyph.getCharactor(), 
					glyph.getMetrics().advanceX, 
					glyph.getMetrics().advanceY, 
					glyph.getMetrics().bearingX, 
					glyph.getMetrics().bearingY);

			this.setToolTipText(tooltip);
		}
	}
}
