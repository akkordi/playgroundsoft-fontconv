package com.playground_soft.tools.font;

import java.awt.font.GlyphMetrics;
import java.awt.geom.AffineTransform;

public class RasterGlyphMetrics {
	public float advanceX;
	public float advanceY;
	
	public float bearingX;
	public float bearingY;
	
	public RasterGlyphMetrics(){
		advanceX = 0;
		advanceY = 0;
		bearingX = 0;
		bearingY = 0;
	}
	
	public RasterGlyphMetrics( GlyphMetrics metrics, AffineTransform af){
		advanceX = metrics.getAdvanceX();
		advanceY = metrics.getAdvanceY();
		bearingX = -(float)af.getTranslateX() + metrics.getLSB();
		bearingY = -(float)af.getTranslateY();
	}
}
