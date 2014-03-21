/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert
 */
public class DataBase {
    private ArrayList<PlaceData> placeDataList = new ArrayList<PlaceData>();
    
    
    public int size(){
        return placeDataList.size();
    }
    public PlaceData get(int i){
        return placeDataList.get(i);
    }
    public void addData(PlaceData placeData){
        placeDataList.add(placeData);
    }
    public List<PlaceData> getList(){
        return placeDataList.subList(0, (placeDataList.size()-1));
    }
    
    public ArrayList<PlaceData> getArrayList(){
        return placeDataList;
    }
    
    public List<PlaceData> Search(String txt){
        ArrayList<PlaceData> searchplaceData = new ArrayList<PlaceData>();
        for(PlaceData pl:placeDataList){
            for(String s:pl.getDept()){
                if(s.contains(txt)){
                    searchplaceData.add(pl);
                }
            }
        }
        return searchplaceData.subList(0, (searchplaceData.size()-1));
        
    }
    
}
