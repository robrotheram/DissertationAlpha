package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bounding.BoundingVolume;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.io.File;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robert
 */
public class Render extends SimpleApplication {
    private Spatial scene;
    private BitmapText wayPointsText;
    private Navigation nav;
    private UiController ui;
    ArrayList<PlaceData> placeData;
    
    
    
    
  
    public Render(){
        this.start();
        setDisplayFps(false);
        setDisplayStatView(false);
    }

    @Override
    public void simpleInitApp() {
        assetManager.registerLoader(XMLLoader.class, "rrf.xml");
        // Type cast to your result class X here
        placeData = (ArrayList<PlaceData>) assetManager.loadAsset("Data/Data.rrf.xml");
        nav = new Navigation(placeData);
        ui = new UiController();
        
        Texture west = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysRight2048.png");
        Texture east = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysLeft2048.png");
        Texture north = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysFront2048.png");
        Texture south = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysBack2048.png");
        Texture up = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysUp2048.png");
        Texture down = assetManager.loadTexture("Textures/CloudyLightRays/CloudyLightRaysDown2048.png");

        Spatial sky = SkyFactory.createSky(assetManager, west, east, north, south, up, down, Vector3f.UNIT_XYZ);
        rootNode.attachChild(sky);

        scene = assetManager.loadModel("Scenes/Scene5/small.blend.j3o");
        AmbientLight al = new AmbientLight();
        flyCam.setMoveSpeed(10);

        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        rootNode.attachChild(scene);
        


        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        wayPointsText = new BitmapText(guiFont, false);
        wayPointsText.setSize(guiFont.getCharSet().getRenderedSize());
        flyCam.setDragToRotate(true);
        guiNode.attachChild(wayPointsText);
        nav.SetUP(this);
        ui.SetUP(this, nav);
     
    }
    
    @Override
    public void simpleUpdate(float tpf){
        Vector3f pos = cam.getLocation();
        /*System.out.print("x:"+pos.x);
        System.out.print("y:"+pos.y);
        System.out.println("z:"+pos.z);*/
        for(int i = 0; i< nav.getPlaceData().size();i++){
            if(pos.distance(nav.getPlaceData().get(i).getcoOrd())<=2){
                ui.setLabelText(nav.getPlaceData().get(i).getName());
                ui.setClosebyPos(i);
            } else{
            }
        }
    }
    public Spatial getScene(){
        return scene;
    }
    public void rechangeName(String txt){
        
        wayPointsText.setText(txt);
        
    }
    public void setCamLocation(Vector3f e){
        cam.setLocation(new Vector3f(10,0,10));
        cam.update();
        System.out.println("12345678");
    }
    
    
    
}
