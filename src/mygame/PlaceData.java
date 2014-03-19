/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author Robert
 */
public class PlaceData {
    private String name;
    private String desc;
    private Vector3f co_ord;
    private ArrayList<String> dept = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Vector3f getCo_ord() {
        return co_ord;
    }

    public void setCo_ord(Vector3f co_ord) {
        this.co_ord = co_ord;
    }

    public ArrayList<String> getDept() {
        return dept;
    }

    public void setDept(ArrayList<String> dept) {
        this.dept = dept;
    }
    
    public String getDeptAt(int i){
        return this.dept.get(i);
    }
            
    
    public PlaceData(String name, String desc, String x, String y, String z, ArrayList<String> dpt){
        this.name = name;
        this.desc = desc;
        this.co_ord = new Vector3f(Float.parseFloat(x),Float.parseFloat(y),Float.parseFloat(z));  
        this.dept = dpt;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
