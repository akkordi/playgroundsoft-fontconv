/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.playground_soft.tools.font;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class Configuration {

    private static Properties properties;
    private static final String CONFIG_FILENAME = "config.conf";
    private static File configFile = new File(CONFIG_FILENAME);

    public static void init() throws IOException {
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        FileReader fileReader = new FileReader(configFile);
        properties = new Properties();
        properties.load(fileReader);
        fileReader.close();
    }

    public static void save() throws IOException {
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(configFile);
        properties.store(fileWriter, "FontConv");
        fileWriter.close();
    }
    
    public static String get(String key){
        return properties.getProperty(key);
    }
    
    public static void set(String key, String value){
        properties.setProperty(key, value);
    }
}
