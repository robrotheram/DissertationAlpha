package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bounding.BoundingVolume;
import com.jme3.font.BitmapText;
import com.jme3.input.FlyByCamera;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
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
    private DataBase placeData;
    private Models model;
    private FilterPostProcessor fpp;
    private FogFilter fog;
    private boolean update;
    private boolean admin;
    private String path;
    private String langID;
    private Languages l;
    
  
    public Render(boolean b,String p, String langID){
        admin  = b;
        this.langID = langID;
        path = p;
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1280,720);
        settings.setTitle("Swansea Univeristy");
        this.setShowSettings(false);
        this.setSettings(settings);
        this.start();
        setDisplayFps(false);
        setDisplayStatView(false);
    }

    @Override
    public void simpleInitApp() {
        
        assetManager.registerLoader(PlaceDataXMLLoader.class, "rrf.xml");
        // Type cast to your result class X here
        placeData = (DataBase) assetManager.loadAsset("Data/Data.rrf.xml");
        assetManager.registerLoader(LangXMLLoader.class, "lang.xml");
        // Type cast to your result class X here
        l = (Languages) assetManager.loadAsset("Data/Languages.lang.xml");
        settings.setTitle(l.getLang(langID).getTitle());
        nav = new Navigation(placeData);
        ui = new UiController();
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Skysphere.jpg", true));
        scene = assetManager.loadModel("Scenes/Scene6/small.j3o");
        AmbientLight al = new AmbientLight();
        flyCam.setMoveSpeed(10);
        model = new Models(this);  
        model.load();
        model.addCulling();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        rootNode.attachChild(scene);
        cam.setLocation(new Vector3f(2,50,2));
        cam.lookAt(new Vector3f(2.8528273f,-0.9993293f,0.036614537f), Vector3f.UNIT_Y);
        FilterPostProcessor fpp=new FilterPostProcessor(assetManager);
        fog=new FogFilter();
        fog.setFogColor(new ColorRGBA(0.9f, 0.9f, 0.9f, 1.0f));
        fog.setFogDistance(35);
        fog.setFogDensity(1.4f);
        fpp.addFilter(fog);
        viewPort.addProcessor(fpp);

        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        wayPointsText = new BitmapText(guiFont, false);
        wayPointsText.setSize(guiFont.getCharSet().getRenderedSize());
        flyCam.setDragToRotate(true);
        guiNode.attachChild(wayPointsText);
        nav.SetUP(this);
        System.err.println("=================>"+l.getLang(langID).getAdminButtonText());
        ui.SetUP(this, nav, admin, path,l.getLang(langID));
     
    }
    public void setUpdate(boolean b){
        update = b;
    }
    @Override
    public void simpleUpdate(float tpf){
        if(update){model.updatelod();}
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
    public Navigation getNav(){
        return nav;
    }
     public Models getModels(){
        return model;
    }
     public FlyByCamera getFlyCam(){
         return flyCam;
     }
    
    
}
