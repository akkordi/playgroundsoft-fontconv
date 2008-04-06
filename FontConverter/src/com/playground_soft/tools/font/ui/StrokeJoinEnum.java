/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.playground_soft.tools.font.ui;
import java.awt.BasicStroke;
/**
 *
 * @author Administrator
 */
public enum StrokeJoinEnum {
    Bevel(BasicStroke.JOIN_BEVEL,"Bevel"),
    Miter(BasicStroke.JOIN_MITER, "Miter"),
    Round(BasicStroke.JOIN_ROUND, "Round");
    
    private final int value;
    private final String name;
    private StrokeJoinEnum(int value, String name){
        this.value = value;
        this.name = name;
    }
    
    public int getValue(){return value;}
    @Override
    public String toString(){
        return name;
    }
}
