package com.playground_soft.tools.font.export;

import java.io.IOException;
import java.util.Map;

import com.playground_soft.tools.font.RasterGlyph;

public interface Exporter {
	public void export(Map<Integer, RasterGlyph> glyphMap) throws IOException;
}
