/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert
 */
public class Admin {
    private DataBase db = new DataBase();
    
    
    public void addPlace(String name, String desc, String dept, Vector3f coOrd){
        db.addData(new PlaceData(
                name, 
                desc, 
                Float.toString(coOrd.x),
                Float.toString(coOrd.y),
                Float.toString(coOrd.z),
                new ArrayList<String>(Arrays.asList(dept.split(",")))));
        JOptionPane.showMessageDialog(null, "Place: "+name+" Was Succesfuyly added");
    }
    
    
    public void write(String path){
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +"<root>";
        for(PlaceData pd : db.getArrayList()){
            System.out.println(pd.getName());
            output += "<place>\n" ;
            output +="<placeName>"+pd.getName()+"</placeName>\n";
            output +="<placeDesc>"+pd.getDesc()+"</placeDesc>\n";
            output +="<xcordinate>"+Float.toString(pd.getCo_ord().x)+"</xcordinate>\n";
            output +="<ycordinate>"+Float.toString(pd.getCo_ord().y)+"</ycordinate>\n";
            output +="<zcordinate>"+Float.toString(pd.getCo_ord().z)+"</zcordinate>\n";
            
            
            output +="<departmentList>\n";
            for(String s :pd.getDept()){
                output +="<department>"+s+"</department>\n";
            }
            output +="</departmentList>\n";
            output +="</place>\n";
        }
        output +="</root>\n";
        writeFile(output, path);
    }
    
    
    public void writeFile(String text, String path ){
         
          try {
          File file = new File(path);
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          output.write(text);
          output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
          JOptionPane.showMessageDialog(null, "File Was Succesfuyly added");
    }
}
