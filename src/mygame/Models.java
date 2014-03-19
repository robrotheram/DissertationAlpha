/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Robert
 */
public class Models {
    HashMap<String, Spatial> data;
    Render root; 
    private Navigation nav;
    public Models(Render n) {
        this.data = new HashMap<String, Spatial>();
        this.root = n;
        
        addSpatial(
                            "ROAD", 
                            n.getAssetManager().loadModel("Scenes/Scene6/Plane.002.mesh.j3o"),
                            new Quaternion(0,0,0,0),
                            new Vector3f(1.436302f,0f,1.722457f),
                            new Vector3f(44.278320f,1f,17.388672f),
                            true
                            );
        addSpatial(
                            "GRASS", 
                            n.getAssetManager().loadModel("Scenes/Scene6/Plane.001.mesh.j3o"),
                            new Quaternion(0,0,0,1),
                            new Vector3f(-18.513042f,-0.001f,4.9f),
                            new Vector3f(15.877930f,27.640625f,5.880859f),
                            true
                            );
       addSpatial(
                            "technium", 
                            n.getAssetManager().loadModel("Scenes/Scene6/TECH-EDIT.mesh.j3o"),
                            new Quaternion(-0.7f,0,0,0.7f),
                            new Vector3f(4.776403f,0.3f,0f),//translate
                            new Vector3f(0.757479f,0.757479f,0.757479f),//scale
                            true
                            );
       addSpatial(
                            "faraday", 
                            n.getAssetManager().loadModel("Scenes/Scene6/faraday.mesh.j3o"),
                            new Quaternion(-0.023031f,-0.786134f,-0.617440f,0.015193f),
                            new Vector3f(6.7f,0.328f,-0.873658f),//translate
                            new Vector3f(1,1,1),//scale
                            true
                            );
        addSpatial(
                          "liblaw", 
                          n.getAssetManager().loadModel("Scenes/Scene6/lib2mesh-edit.mesh.j3o"),
                          new Quaternion(0,-1,0,0.08f),
                          new Vector3f(41.4f,0.9f,-0.7f),//translate
                          new Vector3f(0.844407f,0.844407f,0.844407f),//scale
                          true
                          );
       addSpatial(
                          "lib", 
                          n.getAssetManager().loadModel("Scenes/Scene6/lib2mesh-com.mesh.j3o"),
                          new Quaternion(0,0.707107f,0.707107f,0),
                          new Vector3f(17.5f,0.63f,1.17f),//translate
                          new Vector3f(0.707053f,0.707053f,0.707053f),//scale
                          true
                          );
       
        
       addSpatial(
                          "kehir", 
                          n.getAssetManager().loadModel("Scenes/Scene6/kh-ed.mesh.j3o"),
                          new Quaternion(-0.706675f,-0.024678f,-0.024678f,0.706675f),
                          new Vector3f(21f,0,-1.78f),//translate
                          new Vector3f(0.552279f,0.552279f,0.552279f),//scale
                          true
                          );
       addSpatial(
                          "tal", 
                          n.getAssetManager().loadModel("Scenes/Scene6/tal-edit.mesh.j3o"),
                          new Quaternion(-0.702012f,-0.084732f,-0.084732f,0.702012f),
                          new Vector3f(14.090718f,0.541f,-0.452409f),//translate
                          new Vector3f(1.214218f,1.214218f,1.214218f),//scale
                          true
                          );

       addSpatial(
                          "fulton", 
                          n.getAssetManager().loadModel("Scenes/Scene6/Fulton.mesh.j3o"),
                         // [-89.99999, 45.0, -1.7075477E-6]
                          new Quaternion(-0.6532814f, 0.27059805f, 0.27059802f, 0.65328145f),
                          new Vector3f(-8.071144f,0.072180f,-14.413345f),//translate
                          new Vector3f(0.060676f,0.060676f,0.060676f),//scale
                          true
                          );
           
        
    }
    public void addSpatial(String name, Spatial build,Quaternion rotate,Vector3f translate, Vector3f scale,boolean hasLOD ){
        build.rotate(rotate);
        build.scale(scale.x,scale.y,scale.z);
        build.setLocalTranslation(translate);
        if(hasLOD){build.setLodLevel(0);}
        this.data.put(name, build);
    }
    public void load(){
        for (Map.Entry<String, Spatial> entry : data.entrySet()) {
            root.getRootNode().attachChild(entry.getValue());
            System.err.print(entry.getKey());
        }
    }
    public void addCulling(){
        for (Map.Entry<String, Spatial> entry : data.entrySet()) {
            Spatial obj = entry.getValue();
            obj.setCullHint(Spatial.CullHint.Dynamic);
            data.put(entry.getKey(), obj);
        }
    }
  
    
    
   public void updatelod(){
       nav = root.getNav();
       Vector3f pos = root.getCamera().getLocation();
       String build = "";
        for(int i = 0; i< nav.getPlaceData().size();i++){
            if(pos.distance(nav.getPlaceData().get(i).getCo_ord())<=6){
                build = nav.getPlaceData().get(i).getName();
            } else{
            }
        }
       if(build.equals("Libuary")){
           setLOD("technium",false);
           setLOD("faraday",false);
           setLOD("liblaw",false);
           setLOD("lib",false);
           setLOD("kehir",false);
           setLOD("tal",false);
           setLOD("fulton",true);
           
       }else if(build.equals("Faraday")){
           setLOD("technium",false);
           setLOD("faraday",false);
           setLOD("liblaw",true);
           setLOD("lib",true);
           setLOD("kehir",true);
           setLOD("tal",false);
           setLOD("fulton",false);
           
       }else if(build.equals("Fulton")){
            setLOD("technium",false);
           setLOD("faraday",false);
           setLOD("liblaw",true);
           setLOD("lib",false);
           setLOD("kehir",true);
           setLOD("tal",false);
           setLOD("fulton",false);
           
       }else if(build.equals("Technium")){
            setLOD("technium",false);
           setLOD("faraday",false);
           setLOD("liblaw",true);
           setLOD("lib",false);
           setLOD("kehir",true);
           setLOD("tal",false);
           setLOD("fulton",false);
           
       }else if(build.equals("Talesin")){
            setLOD("technium",false);
           setLOD("faraday",false);
           setLOD("liblaw",false);
           setLOD("lib",false);
           setLOD("kehir",false);
           setLOD("tal",false);
           setLOD("fulton",false);
           
       }else if(build.equals("Kehir Hardy")){
            setLOD("technium",true);
           setLOD("faraday",false);
           setLOD("liblaw",false);
           setLOD("lib",false);
           setLOD("kehir",false);
           setLOD("tal",true);
           setLOD("fulton",true);
           
       }
        
   }
   public void resetLOD(){
        setLOD("technium",false);
        setLOD("faraday",false);
        setLOD("liblaw",false);
        setLOD("lib",false);
        setLOD("kehir",false);
        setLOD("tal",false);
        setLOD("fulton",false);
   }
    
   public void setLOD(String key, boolean cull){
      Spatial obj = data.get(key);
      if(cull){
          obj.setCullHint(Spatial.CullHint.Always);
      }else{
          obj.setCullHint(Spatial.CullHint.Dynamic);
      }
      data.put(key, obj);
   }
}
