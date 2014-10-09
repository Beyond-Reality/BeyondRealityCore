package com.mcbeyondreality.beyondrealitycore.handlers;

import com.mcbeyondreality.beyondrealitycore.BRCGuiMainMenu;
import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.event.GuiScreenEvent;
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
import java.util.ArrayList;
import java.util.List;

public class GuiHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) throws NoSuchFieldException {
        if (event.gui instanceof GuiMainMenu) {
            Minecraft.getMinecraft().displayGuiScreen(new BRCGuiMainMenu());
        }
    }

    public static List<String> getBrandings()
    {
        List<String>brands = new ArrayList<String>();
        for(int i = 0; i < BeyondRealityCore.bottomLeftBranding.length; i++)
            brands.add(BeyondRealityCore.bottomLeftBranding[i]);
        return brands;
    }

    public static void addCustomServers(Minecraft mc) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        NBTTagList nbttaglist = new NBTTagList();

        File serverFile = new File("config/BeyondRealityCore/beyondrealitycoreServers.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        if(!serverFile.exists())
        {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element server = doc.createElement("server");
            rootElement.appendChild(server);

            Element serverName = doc.createElement("name");
            serverName.appendChild(doc.createTextNode("Beyond Reality"));
            server.appendChild(serverName);

            Element serverIp = doc.createElement("ip");
            serverIp.appendChild(doc.createTextNode("mcbeyondreality.com"));
            server.appendChild(serverIp);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config/BeyondRealityCore/beyondrealitycoreServers.xml"));
            transformer.transform(source, result);
        }

        Document doc = dBuilder.parse(serverFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("server");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                ServerData serverdata = new ServerData(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("ip").item(0).getTextContent());
                nbttaglist.appendTag(serverdata.getNBTCompound());
            }
        }

        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setTag("servers", nbttaglist);

        try {
            CompressedStreamTools.safeWrite(nbttagcompound, new File(mc.mcDataDir, "servers.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}