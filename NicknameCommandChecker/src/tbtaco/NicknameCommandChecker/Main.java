package tbtaco.NicknameCommandChecker;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static final String PLUGIN_NAME = "NicknameCommandChecker";
	public static Main MainClass;
	public void onEnable()
	{
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1m"+PLUGIN_NAME+"\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Enabling Plugin...\u001B[0m");
		MainClass = this;
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), MainClass);
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1m"+PLUGIN_NAME+"\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Successfully Enabled\u001B[0m");
	}
	public void onLoad()
	{
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1m"+PLUGIN_NAME+"\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");
	}
	public void onDisable()
	{
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1m"+PLUGIN_NAME+"\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");
		MainClass = null;
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
}
class Listeners implements Listener
{
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void playerCommandPreprocessEvent(PlayerCommandPreprocessEvent e)
	{
		String[] commands = {"/nick", "/enick", "/essentials.nick", "/essentials.enick", "/nickname", "/enickname", "/essentials.nickname", "/essentials.enickname"};
		String[] parts = e.getMessage().toLowerCase().split(" ");
		if(parts.length==0||parts.length>=4)
			return;
		boolean nicknameCommand = false;
		for(String s : commands)
			if(s.equalsIgnoreCase(parts[0]))
				nicknameCommand = true;
		if(!nicknameCommand)
			return;
		if(parts.length==1)
		{
			e.getPlayer().sendMessage("\u00a7fColors: \u00a70&0\u00a7f, \u00a71&1\u00a7f, \u00a72&2\u00a7f, \u00a73&3\u00a7f, \u00a75&5\u00a7f, \u00a76&6\u00a7f, \u00a77&7\u00a7f, \u00a78&8\u00a7f, \u00a79&9\u00a7f, \u00a7A&A\u00a7f, \u00a7B&B\u00a7f, \u00a7C&C\u00a7f, \u00a7D&D\u00a7f, \u00a7E&E\u00a7f, \u00a7F&F\u00a7f");
			return;
		}
		String playername = e.getPlayer().getName().toLowerCase();
		String nickname = parts[1].replaceAll("\u00a7", "&");
		if(nickname.equalsIgnoreCase("off"))
			return;
		if(parts.length==3)
		{
			playername = parts[1].toLowerCase();
			nickname = parts[2];
		}
		if(nickname.contains("&4"))
		{
			Methods.handleIllegal(e, "\u00a74You can't use this color (&4) in nicknames.");
			return;
		}
		nickname = nickname.replaceAll("&.", "");
		if(!playername.contains(nickname))
			Methods.handleIllegal(e, "\u00a74The nickname \""+nickname+"\" doesn't show up in \""+playername+"\"");
	}
}
class Methods
{
	public static void handleIllegal(PlayerCommandPreprocessEvent e, String message)
	{
		if(!e.getPlayer().hasPermission("NicknameCommandChecker.Bypass"))
			e.setCancelled(true);
		else
			e.getPlayer().sendMessage("\u00a74Bypassing nickname restrictions, but this is what the plugin would have caught:");
		e.getPlayer().sendMessage(message);
	}
}