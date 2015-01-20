package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.data.AIMMachineRecipe;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AIMMachineRecipeHandler {

    public static ArrayList<AIMMachineRecipe> aim = new ArrayList<AIMMachineRecipe>();

    public static void loadAIMRecipes() throws ParserConfigurationException, TransformerException, IOException, SAXException {
        File aimMachineRecipes = new File("config/BeyondRealityCore/aimMachineRecipes.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        if(!aimMachineRecipes.exists())
        {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("recipes");
            doc.appendChild(rootElement);

            Element recipe = doc.createElement("recipe");
            rootElement.appendChild(recipe);

            Element input = doc.createElement("input");
            input.appendChild(doc.createTextNode("minecraft:apple"));
            recipe.appendChild(input);

            Element output = doc.createElement("output");
            output.appendChild(doc.createTextNode("minecraft:stick"));
            recipe.appendChild(output);

            Element rf = doc.createElement("rf");
            rf.appendChild(doc.createTextNode("10000"));
            recipe.appendChild(rf);

            //TransformerFactory transformerFactory =
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(aimMachineRecipes);
            transformer.transform(source, result);
        }

        Document doc = dBuilder.parse(aimMachineRecipes);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("recipe");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                NodeList input = ((Element) nNode).getElementsByTagName("input");
                NodeList output = ((Element) nNode).getElementsByTagName("output");
                NodeList rf = ((Element) nNode).getElementsByTagName("rf");

                for(int i = 0; i < input.getLength(); i++) {
                    AIMMachineRecipe aimtemp = new AIMMachineRecipe(input.item(i).getTextContent(),
                            output.item(i).getTextContent(),
                            Integer.parseInt(rf.item(i).getTextContent()));
                    aim.add(aimtemp);
                }
            }
        }
    }
}
