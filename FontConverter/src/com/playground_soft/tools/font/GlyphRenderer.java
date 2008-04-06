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
package com.playground_soft.tools.font;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

/**
 * @author Administrator
 *
 */
public class GlyphRenderer {
    private int character = Character.getNumericValue('A');
    private BufferedImage image;
    private Shape shape;
    private Font font;
    private Graphics2D graphics2d;
    private Paint fillClr;
    private Paint outlineClr;
    private Stroke outlineStrk;

    public GlyphRenderer(Paint fillClr, Paint outlineClr, Stroke outlineStrk) {
        this.fillClr = fillClr;
        this.outlineClr = outlineClr;
        this.outlineStrk = outlineStrk;

        image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        graphics2d = image.createGraphics();
    }

    public GlyphRenderer(GlyphRenderer src){
        this.fillClr = src.fillClr;
        this.outlineClr = src.outlineClr;
        this.outlineStrk = src.outlineStrk;

        image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        graphics2d = image.createGraphics();
    }
    
    public BufferedImage getGlyphImage() {
        return image;
    }

    public final void invalidate() {
        if (font == null || graphics2d == null) {
            return;
        }
        int[] charBuf = new int[1];
        charBuf[0] = character;

        GlyphVector glyphVector = font.createGlyphVector(
                graphics2d.getFontRenderContext(),
                charBuf);
        shape = glyphVector.getGlyphOutline(0);
        Dimension size = this.getSize();
        if (size.width <= 0 || size.height <= 0) {
            return;
        }
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

    public final void setFont(Font font) {
        this.font = font;
        invalidate();
    }

    protected void draw(Graphics2D graphic) {
        if (shape == null) {
            return;
        }
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

    public GlyphMetrics getGlyphMetrics() {
        if (graphics2d == null || font == null) {
            return null;
        }
        int[] charBuf = new int[1];
        charBuf[0] = character;
        GlyphVector glyphVector = font.createGlyphVector(graphics2d.getFontRenderContext(), charBuf);

        GlyphMetrics metrics = glyphVector.getGlyphMetrics(0);

        return metrics;
    }

    protected Dimension getSize() {
        if (shape == null) {
            return null;
        }

        Shape outline = outlineStrk.createStrokedShape(shape);
        Dimension output = outline.getBounds().getSize();
        output.width += 1;
        output.height += 1;
        return output;
    }
}
