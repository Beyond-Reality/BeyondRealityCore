package beyondrealitycore.handlers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BeyondRealityCoreCapeEvent
{

	private final String serverLocation = "https://raw.githubusercontent.com/beyondreality/BeyondRealityModPack/master/TeamMembers.txt";
	private final int timeout = 1000;

	private static final Graphics TEST_GRAPHICS = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB).getGraphics();
	private HashMap<String, String> cloaksBeyondReality = new HashMap<String, String>();
	private ArrayList<AbstractClientPlayer> capePlayersBeyondReality = new ArrayList<AbstractClientPlayer>();

	public static BeyondRealityCoreCapeEvent instance;

	public BeyondRealityCoreCapeEvent()
	{
		buildCloakURLDatabase();
		instance = this;
	}

	@SubscribeEvent
	public void onPreRenderSpecials (RenderPlayerEvent.Specials.Pre event)
	{
		if (event.entityPlayer instanceof AbstractClientPlayer)
		{
			AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer) event.entityPlayer;

			if (!capePlayersBeyondReality.contains(abstractClientPlayer))
			{
				String cloakURL = cloaksBeyondReality.get(event.entityPlayer.getDisplayName());

				if (cloakURL == null)
				{
					return;
				}

				capePlayersBeyondReality.add(abstractClientPlayer);

				//abstractClientPlayer.getTextureCape().textureUploaded = false;
				new Thread(new CloakThread(abstractClientPlayer, cloakURL)).start();
				event.renderCape = true;
			}
		}
	}

	public void buildCloakURLDatabase ()
	{
		URL url;
		try
		{
			url = new URL(serverLocation);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			InputStream io = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(io));

			String str;
			int linetracker = 1;
			while ((str = br.readLine()) != null)
			{
				if (!str.startsWith("--"))
				{
					if (str.contains(":"))
					{
						String nick = str.substring(0, str.indexOf(":"));
						String link = str.substring(str.indexOf(":") + 1);
						new Thread(new CloakPreload(link)).start();
						cloaksBeyondReality.put(nick, link);
					}
					else
					{
						System.err.println("[BeyondRealityCapes] [skins.txt] Syntax error on line " + linetracker + ": " + str);
					}
				}
				linetracker++;
			}

			br.close();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private class CloakThread implements Runnable
	{

		//AbstractClientPlayer abstractClientPlayer;
		String cloakURL;

		public CloakThread(AbstractClientPlayer player, String cloak)
		{
			//abstractClientPlayer = player;
			cloakURL = cloak;
		}

		@Override
		public void run ()
		{
			try
			{
				BufferedImage bo = new BufferedImage(2048, 1024, BufferedImage.TYPE_INT_ARGB);	
				bo.getGraphics().drawImage(ImageIO.read(new URL(cloakURL)), 0, 0, null);
				//abstractClientPlayer.getTextureCape().setBufferedImage(bo);
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	private class CloakPreload implements Runnable
	{
		String cloakURL;

		public CloakPreload(String link)
		{
			cloakURL = link;
		}

		@Override
		public void run ()
		{
			try
			{
				TEST_GRAPHICS.drawImage(ImageIO.read(new URL(cloakURL)), 0, 0, null);
				//TEST_GRAPHICS.drawImage(new ImageIcon(new URL(cloakURL)).getImage(), 0, 0, null);
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void refreshCapes ()
	{
		cloaksBeyondReality.clear();
		capePlayersBeyondReality.clear();
		buildCloakURLDatabase();
	}
}
