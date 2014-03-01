/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Robert
 */
public class XMLLoader implements AssetLoader {
    private ArrayList<PlaceData> placeData = new ArrayList<PlaceData>();

    public Object load(AssetInfo assetInfo) {
        Document doc = createDocFromStream(assetInfo.openStream());
        if (doc != null) {
            doc.getDocumentElement ().normalize ();
            NodeList listOfPersons = doc.getElementsByTagName("place");
            for(int s=0; s<listOfPersons.getLength() ; s++){
                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
                    placeData.add(new PlaceData(
                               getXMLValue((Element)firstPersonNode,"placeName" ),
                                getXMLValue((Element)firstPersonNode,"placeDesc" ),
                                getXMLValue((Element)firstPersonNode,"xcordinate" ),
                                getXMLValue((Element)firstPersonNode,"ycordinate" ),
                                getXMLValue((Element)firstPersonNode,"zcordinate" )));
                    
                }//end of if clause
            }//end of for loop with s var

        }

        // Return an instance of your result class X here
        return placeData;
    }

    public static Document createDocFromStream(InputStream inputStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            return doc;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public String getXMLValue(Element e, String s){
        Element firstPersonElement = e;
        NodeList firstNameList = firstPersonElement.getElementsByTagName(s);
        Element firstNameElement = (Element)firstNameList.item(0);
        NodeList textFNList = firstNameElement.getChildNodes();
        return ((Node)textFNList.item(0)).getNodeValue().trim();
    }
}

