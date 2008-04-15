package com.playground_soft.tools.font;

import java.awt.Image;
import java.awt.font.GlyphMetrics;
import java.awt.geom.AffineTransform;

public class RasterGlyph {
	private final Image glyphImage;
	private final RasterGlyphMetrics metrics;
	private final int charCode;
	
	public RasterGlyph(int charCode, Image glyphImage, GlyphMetrics metrics, AffineTransform af ){
		this.charCode = charCode;
		this.glyphImage = glyphImage;
		this.metrics = new RasterGlyphMetrics(metrics, af);
	}
	
	public Image getImage(){
		return glyphImage;
	}
	
	public RasterGlyphMetrics getMetrics(){
		return metrics;
	}
	
	public int getCharactor(){
		return charCode;
	}
}
