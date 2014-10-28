package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.data.BannedBlocksForDimension;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DimensionBanHandler
{
    public static void loadBanedBlocks() throws ParserConfigurationException, TransformerException, IOException, SAXException {
        File bannedBlocksFile = new File("config/BeyondRealityCore/bannedBlocksByDimension.xml");


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        if(!bannedBlocksFile.exists())
        {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element dimId = doc.createElement("dimension-id");
            dimId.setAttribute("DimensionId", "-1");
            rootElement.appendChild(dimId);

            Element bannedBlock = doc.createElement("name");
            bannedBlock.appendChild(doc.createTextNode("minecraft:ice"));
            dimId.appendChild(bannedBlock);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config/BeyondRealityCore/bannedBlocksByDimension.xml"));
            transformer.transform(source, result);
        }

        Document doc = dBuilder.parse(bannedBlocksFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("dimension-id");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                NodeList blocks = ((Element) nNode).getElementsByTagName("name");
                for(int i = 0; i < blocks.getLength(); i++)
                    BannedBlocksForDimension.addBannedBlockToDimension(Integer.parseInt(((Element) nNode).getAttribute("DimensionId")), blocks.item(i).getTextContent());
            }

        }
    }
}
