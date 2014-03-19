/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.test;

import java.util.ArrayList;
import mygame.PlaceData;
import mygame.Render;

public class test {
    public static void main(String[] args){
        Render r = new Render();
        r.getAssetManager().registerLoader(mygame.XMLLoader.class, "rrf.xml");
        // Type cast to your result class X here
        r.getAssetManager().loadAsset("Data/Data.rrf.xml");

    }
}   

