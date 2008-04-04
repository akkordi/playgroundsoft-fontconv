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
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

public class BasicFontRenderer extends FontRenderer {

	private Paint fillClr;
	private Paint outlineClr;
	private Stroke outlineStrk;

	public BasicFontRenderer(Paint fillClr, Paint outlineClr, Stroke outlineStrk) {
		this.fillClr = fillClr;
		this.outlineClr = outlineClr;
		this.outlineStrk = outlineStrk;
	}

	@Override
	protected void draw(Graphics2D graphic) {
		Shape shape = this.getShape();
		if(shape == null)
			return;
		Shape outline = outlineStrk.createStrokedShape(shape);
		Rectangle rect = outline.getBounds();
		AffineTransform af = new AffineTransform();
		af.setToTranslation(-rect.x, -rect.y);
		graphic.setTransform(af);
		graphic.setStroke(outlineStrk);

		graphic.setPaint(fillClr);
		graphic.fill(shape);

		graphic.setPaint(outlineClr);
		graphic.draw(shape);

	}

	@Override
	public GlyphMetrics getGlyphMetrics() {
		Graphics2D graphic = this.getGraphics();

		Font font = this.getFont();
		int character = this.getCharacter();
		
		if(graphic == null || font == null){
			return null;
		}
		int[] charBuf = new int[1];
		charBuf[0] = character;
		GlyphVector glyphVector = font.createGlyphVector
			(graphic.getFontRenderContext(), charBuf);
		
		GlyphMetrics metrics = glyphVector.getGlyphMetrics(0);

		return metrics;
	}

	@Override
	protected Dimension getSize() {
		
		Shape shape = this.getShape();
		if(shape==null)
			return null;
		
		Shape outline = outlineStrk.createStrokedShape(shape);
		Dimension output = outline.getBounds().getSize();
		output.width+=1;
		output.height+=1;
		return output;
	}
}
