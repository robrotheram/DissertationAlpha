/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.animation.LoopMode;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import java.util.ArrayList;
import java.util.HashMap;
import jme3tools.navmesh.NavMesh;
import jme3tools.navmesh.Path;

/**
 *
 * @author Robert
 */
public class Navigation {


    private MotionEvent cameraMotionControl;
    private ChaseCamera chaser;
    private CameraNode camNode;
    private Render rndr;
    private ArrayList<PlaceData> placeData ;
    private HashMap<String, MotionPath> paths;

    public Navigation(ArrayList<PlaceData> pd) {
        this.paths = new HashMap<String, MotionPath>();
        placeData = pd;
    }
  
    public ArrayList<PlaceData> getPlaceData(){
        return placeData;
    }
    public void SetUP(Render r){
         rndr = r;
        Node submesh = (Node) rndr.getRootNode().getChild("Mesh-ogremesh");
       // System.out.println(rndr.getRootNode().getChildren());
        Geometry g = (Geometry) submesh.getChild("Material.002");
        
        
        for(int i = 0; i<placeData.size(); i++){
          for(int j = 0; j<placeData.size(); j++){
              v2(g.getMesh(),placeData.get(i).getcoOrd(),placeData.get(j).getcoOrd(),"p"+i+"-"+j);
              System.out.println("p"+i+"-"+j);
            }  
        }
        setupCam("p0-1");
    }
    
    public ChaseCamera getChaser(){
        return chaser;
    }
    
    public CameraNode getCamNode(){
        return camNode;
    }
    public MotionEvent getContol(){
        return cameraMotionControl;
    }
    
    public void v2 (Mesh m, Vector3f init, Vector3f end, String name){
        NavMesh navmesh = new NavMesh();
    navmesh.loadFromMesh(m);

  // Vector3f init = new Vector3f(4,1,-3); // some initial point
    //Vector3f end = new Vector3f(23,1,3);  //some destination/end point

    Path pathl = new Path();
    boolean buildNavigationPath = navmesh.buildNavigationPath(pathl, navmesh.findClosestCell(init), init, navmesh.findClosestCell(end), end, 0.4f);

    if (buildNavigationPath) {
        
   
        MotionPath path = new MotionPath();
        path.setCycle(false);
        
    for (Path.Waypoint p : pathl.getWaypoints()) {

        Vector3f vf = new Vector3f(p.getPosition().x,0.7f,p.getPosition().z);
        path.addWayPoint(vf);

    // Now you need to create some code to solve this for you. Maybe remebering what was the last waypoint, so you can know which one is the next and how to proceed.
    }
    
    paths.put(name, path);
    
    }
    }
    
    
    
    public void setupCam(String key){
        final MotionPath path = paths.get(key);
        
        camNode = new CameraNode("Motion cam", rndr.getCamera());
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        camNode.setEnabled(false);
        path.setCurveTension(0.15f);
        //path.enableDebugShape(rndr.getAssetManager(), rndr.getRootNode());

        cameraMotionControl = new MotionEvent(camNode, path, 10f);
        cameraMotionControl.setLoopMode(LoopMode.DontLoop);
        
        cameraMotionControl.setLookAt(rndr.getScene().getWorldTranslation(), Vector3f.UNIT_Y);
        cameraMotionControl.setDirectionType(MotionEvent.Direction.Path);

        rndr.getRootNode().attachChild(camNode);
        
        path.addListener(new MotionPathListener() {

            public void onWayPointReach(MotionEvent control, int wayPointIndex) {
                if (path.getNbWayPoints() == wayPointIndex + 1) {
                    rndr.rechangeName(control.getSpatial().getName() + " Finish!!! ");
                    
                    chaser.setEnabled(false);
                        camNode.setEnabled(true);
                        cameraMotionControl.stop();
                        rndr.getFlyByCamera().setEnabled(true);
                        
                } else {
                    rndr.rechangeName(control.getSpatial().getName() + " Reached way point " + wayPointIndex);
                }
            }
        });

        rndr.getFlyByCamera().setEnabled(false);
        chaser = new ChaseCamera(rndr.getCamera(), rndr.getScene());
        chaser.setLookAtOffset(Vector3f.UNIT_Y.add(0, 10, 0));
                
        chaser.registerWithInput(rndr.getInputManager());
        chaser.setSmoothMotion(false);
        chaser.setMaxDistance(50);
        chaser.setDefaultDistance(50);
       

    }

    private static class assetManager {

        public assetManager() {
        }
    }
}