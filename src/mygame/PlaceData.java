/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author Robert
 */
public class PlaceData {
    private String name;
    private String desc;
    private Vector3f co_ord;
    public PlaceData(String name, String desc, String x, String y, String z){
        this.name = name;
        this.desc = desc;
        this.co_ord = new Vector3f(Float.parseFloat(x),Float.parseFloat(y),Float.parseFloat(z));         
    }
    public String getName(){
        return name;
    }
    
    public String getDesc(){
        return desc;
    }
    
    public Vector3f getcoOrd(){
        return co_ord;
    }
}
