/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.HashMap;

/**
 *
 * @author Robert
 */
public class Languages {
    private HashMap<String, Lang> languageMap = new HashMap<String, Lang>();

    public void addLangage(Lang l, String langName){
        languageMap.put(langName, l);
        System.err.println(langName);

    }
    public Lang getLang(String langID){
        return languageMap.get(langID);
    }
}
 




