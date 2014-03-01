/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert
 */
public class UiController implements ScreenController {
    private Render rndr;
    private Navigation nav; 
    private Nifty nifty;
    private int closeByPos;
    private int newLocationPos;
    ArrayList<PlaceData> placeData;
    
    
    public void SetUP(Render r, Navigation n) {
        rndr = r;
        nav = n;
        placeData = n.getPlaceData();
    // You can map one or several inputs to one named action
    rndr.getInputManager().addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
    rndr.getInputManager().addMapping("Map",  new KeyTrigger(KeyInput.KEY_M));
    // Add the names to the action listener.
    rndr.getInputManager().addListener(actionListener,"Pause");
    rndr.getInputManager().addListener(actionListener,"Map");
    
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(rndr.getAssetManager(),
                                                          rndr.getInputManager(),
                                                          rndr.getAudioRenderer(),
                                                          rndr.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/newbutton.xml","begin", this);


         // attach the nifty display to the gui view port as a processor
        rndr.getGuiViewPort().addProcessor(niftyDisplay);

        // disable the fly cam
        
        rndr.getInputManager().setCursorVisible(true);


 
  }
    
    public void setClosebyPos(int i){
        closeByPos = i;
    }
 public void doNothing(){
      nifty.gotoScreen("run");
     rndr.getFlyByCamera().setEnabled(true);
     rndr.getFlyByCamera().setDragToRotate(false);
      System.out.println("butotom pressed");
      rndr.setCamLocation(new Vector3f(0,0,0));
  }
 
 
 public void select(){
     nifty.gotoScreen("run");
     rndr.getFlyByCamera().setEnabled(true);
     rndr.getFlyByCamera().setDragToRotate(false);
      
      
     nav.setupCam("p"+closeByPos+"-"+newLocationPos);
     nav.getChaser().setEnabled(false);
        nav.getCamNode().setEnabled(true);
        nav.getContol().play();  
 }
 
  private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Pause") && !keyPressed) {
        nav.getChaser().setEnabled(false);
        nav.getCamNode().setEnabled(true);
        nav.getContol().play();  
      }else if (name.equals("Map") && !keyPressed) {
          
         
        //Element niftyElement = nifty.getCurrentScreen().findElementByName("text");
            // swap old with new text
           nifty.gotoScreen("start");
           rndr.getFlyByCamera().setEnabled(false);
           rndr.getFlyByCamera().setDragToRotate(true);
          //  niftyElement.getRenderer(TextRenderer.class).setText("124");
          
      }
    }
  };
  
  public void setLabelText(String s){
      
      Element niftyElement = nifty.getCurrentScreen().findElementByName("text");
// swap old with new text
      if(niftyElement !=null){
           niftyElement.getRenderer(TextRenderer.class).setText(s);
      }
  }

    public void bind(Nifty nifty, Screen screen) {
        screen = nifty.getScreen("start");
        ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
        for(int i=0; i<placeData.size(); i++){
            listBox.addItem(placeData.get(i).getName());

        }
    }

    public void onStartScreen() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     /**
   * When the selection of the ListBox changes this method is called.
   */
  @NiftyEventSubscriber(id="myListBox")
  public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
    List<Integer> selection = event.getSelectionIndices();
    newLocationPos = selection.get(0);
  }

}
