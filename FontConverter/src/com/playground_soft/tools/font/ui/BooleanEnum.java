/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.playground_soft.tools.font.ui;

/**
 *
 * @author Administrator
 */
public enum BooleanEnum {
    True(true,"True"),
    False(false,"False");
    
    private final boolean value;
    private final String name;
    private BooleanEnum(boolean value, String name){
        this.value = value;
        this.name = name;
    }
    
    @Override 
    public String toString(){
        return name;
    }
    
    public boolean getValue(){
        return value;
    }
}
