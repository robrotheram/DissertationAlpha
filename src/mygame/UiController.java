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
import com.jme3.ui.Picture;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextFieldChangedEvent;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
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
    private Admin admin = new Admin();
    private DataBase placeData;
    final int ox = -43;
    final int oz = -14;
    private String path;
    private Lang language;
    
    public void SetUP(Render r, Navigation n, boolean b, String p, Lang l ) {
        rndr = r;
        language  = l;
        path = p;
        nav = n;
        placeData = n.getPlaceData();
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(rndr.getAssetManager(),
                                                          rndr.getInputManager(),
                                                          rndr.getAudioRenderer(),
                                                          rndr.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        if(b){
            nifty.fromXml("Interface/newbutton.xml","run", this);
            rndr.setCamLocation(new Vector3f(5,1,-1));
            rndr.getFlyByCamera().setEnabled(true);
            rndr.getFlyByCamera().setDragToRotate(true);
            
        }else{
            nifty.fromXml("Interface/newbutton.xml","begin", this);
                         rndr.getFlyByCamera().setEnabled(false);
           rndr.getFlyByCamera().setDragToRotate(true);
        }

         // attach the nifty display to the gui view port as a processor
        rndr.getGuiViewPort().addProcessor(niftyDisplay);

        // disable the fly cam
        
        rndr.getInputManager().setCursorVisible(true);
        
            Picture pic = new Picture("pin");
            pic.setImage(rndr.getAssetManager(), "Interface/pics/pin.png", true);
            pic.setWidth(35);
            pic.setHeight(35);
            pic.setPosition(-50,0);
                
            rndr.getGuiNode().attachChild(pic);    

           if(!b){
            for(String str : nifty.getAllScreensName()){
                 Element niftyElement = nifty.getScreen(str).findElementByName("Adminbtn");
                 if(niftyElement !=null){
                     nifty.removeElement(nifty.getScreen(str), niftyElement);
                 }
            }
           }
           rndr.setUpdate(true);
           setUPLang();
  }
    
    public void setClosebyPos(int i){
        closeByPos = i;
    }
 public void doNothing(){
     rndr.setUpdate(true);
      nifty.gotoScreen("run");
    
  }
 
 public void admin(){
        rndr.setUpdate(false);
       rndr.getGuiNode().detachChildNamed("pin");
       nifty.gotoScreen("admin");
 }
 public void addPlace(){
     String name = getTextFeildString("PlaceTitle");
     String des = getTextFeildString("PlaceDesc");
     String dept = getTextFeildString("PlaceDept");
     
     System.out.println(name+des+dept);
     admin.addPlace(name,des,dept, rndr.getCamera().getLocation());
     doNothing();
 }
 public void writeFile(){
     admin.write(path);
     doNothing();
 }
 
 
 public String getTextFeildString(String id){
    TextField txt = nifty.getCurrentScreen().findNiftyControl(id, TextField.class);
// swap old with new text
     String output = null;
      if(txt !=null){
           output = txt.getRealText();
      }
      return output;
 }
 
 public void select(){
     rndr.getModels().resetLOD();
     Vector3f pos = rndr.getCamera().getLocation();
      for(int i = 0; i< nav.getPlaceData().size();i++){
            if(pos.distance(nav.getPlaceData().get(i).getCo_ord())<=2){
                
                setClosebyPos(i);
            }
        }
     
      rndr.setUpdate(true);
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
      if (name.equals("Map") && !keyPressed) {
            showMap();
      }else if (name.equals("Pause") && !keyPressed) {
            showList();
      }
    }
  };
  public void showList(){
      
        
           rndr.getGuiNode().detachChildNamed("pin");
           nifty.gotoScreen("start");
           rndr.getFlyByCamera().setEnabled(false);
           rndr.getFlyByCamera().setDragToRotate(true);
         
          
  }
  public void showMap(){
      Vector3f pos = rndr.getCamera().getLocation();
        System.out.print("x:"+pos.x);
        System.out.print("y:"+pos.y);
        System.out.println("z:"+pos.z);
        
        
       float x = (pos.x-ox)*12;
        float z = (float) ((-pos.z+20)*18.5); 
         System.out.print("x:"+x);
        System.out.println("z:"+z);
        
        Picture pic = new Picture("pin");
            pic.setImage(rndr.getAssetManager(), "Interface/pics/pin.png", true);
            pic.setWidth(35);
            pic.setHeight(35);
            pic.setPosition(x,z);
            rndr.getGuiNode().attachChild(pic);
            nifty.gotoScreen("test");
  }
  public void setLabelText(String s){
      
      Element niftyElement = nifty.getCurrentScreen().findElementByName("text");
// swap old with new text
      if(niftyElement !=null){
           niftyElement.getRenderer(TextRenderer.class).setText("You are near by: "+s);
      }
  }

    
    public void bind(Nifty nifty, Screen screen) {
        rndr.getGuiNode().detachChildNamed("pin");
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
    
    @NiftyEventSubscriber(id="search")

    public void onTextChanged(final String id, final TextFieldChangedEvent event)
    {
        String searchText = event.getText();
        System.out.println("text:"+searchText );
        Screen screen = nifty.getScreen("start");
        ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
        listBox.clear();
        List<PlaceData> Searchlist = placeData.Search(searchText);
        if(searchText.equals("")){
            listBox.addAllItems(placeData.getList());
        }else if(Searchlist.size()>0){
            listBox.addAllItems(Searchlist);
        }
        
    }
    
  @NiftyEventSubscriber(id="myListBox")
  public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
     
    List<Integer> selection = event.getSelectionIndices();
    if(selection.size()>0){
        newLocationPos = selection.get(0);
    }
  }
  
  public void setUPLang(){
      for(String str : nifty.getAllScreensName()){
                 Button adminButton = nifty.getScreen(str).findNiftyControl("Adminbtn", Button.class);
                 Button mapButton = nifty.getScreen(str).findNiftyControl("Map", Button.class);
                 Button listButton = nifty.getScreen(str).findNiftyControl("List", Button.class);
                 Button okButton = nifty.getScreen(str).findNiftyControl("buttonOk", Button.class);
                 Button cancelButton = nifty.getScreen(str).findNiftyControl("buttonCancel", Button.class);
                 Label desc = nifty.getScreen(str).findNiftyControl("desc", Label.class);
                 Label desc2 = nifty.getScreen(str).findNiftyControl("desc2", Label.class);
                 Label desc3 = nifty.getScreen(str).findNiftyControl("desc3", Label.class);
                 
                 try{
                    if(adminButton !=null){
                        //System.out.println("===========================>>>"+language);
                       adminButton.setText(language.getAdminButtonText());
                    }
                 }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                 try{
                    if(mapButton !=null){
                       mapButton.setText(language.getMapButtonText());

                    }
                }catch(NullPointerException e){
                     e.printStackTrace();
                }
                try{
                    if(listButton !=null){
                        listButton.setText(language.getListButtonText());
                    }
                    }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                try{
                    if(okButton !=null){
                        okButton.setText(language.getOkButtonText());
                    }
                    }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                try{
                    if(cancelButton !=null){
                        cancelButton.setText(language.getCancelButtonText());
                    }
                    }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                try{
                    if(desc !=null){
                        desc.setText(language.getDescriptionButtonText());
                    }
                    }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                try{
                    if(desc2 !=null){
                        desc2.setText(language.getDescriptionLine2ButtonText());
                    }
                    }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                try{
                    if(desc3 !=null){
                        desc3.setText(language.getDescriptionLine3ButtonText());
                    }
                 }catch(NullPointerException e){
                     e.printStackTrace();
                 }
                 
                 
                 
            }
  }
  



}
