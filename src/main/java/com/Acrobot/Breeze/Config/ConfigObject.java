package com.Acrobot.Breeze.Config;

/**
 * @author Acrobot
 */
public class ConfigObject {
    public Object value;
    public String comment;
    
    public ConfigObject(Object value, String comment){
        this.value = value;
        this.comment = comment;
    }
    
    public ConfigObject(Object value){
        this.value = value;
    }
}
