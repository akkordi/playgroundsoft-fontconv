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
package com.playground_soft.tools.font.renderer;

import java.awt.*;
import java.awt.font.*;
import java.awt.image.*;

/**
 * @author Administrator
 *
 */
public abstract class FontRenderer {
	private int character = Character.getNumericValue('A');

	private BufferedImage image;
	private Shape shape;
	private Font font;
	private Graphics2D graphics2d;
	public FontRenderer() {
		image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		graphics2d = image.createGraphics();
	}

	protected abstract void draw(Graphics2D g2);

	protected abstract Dimension getSize();

	public final Image getGlyphImage(){
		return image;
	}

	protected final int getCharacter() {
		return character;
	}

	protected final Font getFont() {
		return font;
	}

	public abstract GlyphMetrics getGlyphMetrics();

	protected final Graphics2D getGraphics() {
		return graphics2d;
	}

	protected final Shape getShape() {
		return shape;
	}

	public final void invalidate() {
		int[] charBuf = new int[1];
		charBuf[0] = character;
		Font font = this.getFont();

		GlyphVector glyphVector = font.createGlyphVector(
				graphics2d.getFontRenderContext(), 
				charBuf);
		shape = glyphVector.getGlyphOutline(0);
		Dimension size = this.getSize();
		if(size.width<=0 || size.height <=0)
			return;
		image = new BufferedImage(size.width, size.height,
				BufferedImage.TYPE_4BYTE_ABGR);
		graphics2d = image.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		shape = glyphVector.getGlyphOutline(0);
		
		this.draw(graphics2d);
	}

	public final void setCharacter(int character) {
		this.character = character;
		invalidate();
	}
	
	public final void setFont(Font font){
		this.font = font;
		invalidate();
	}
}
