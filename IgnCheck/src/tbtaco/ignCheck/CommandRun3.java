package tbtaco.ignCheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandRun3 extends BukkitRunnable{
	private final Main plugin;
	public CommandRun3(Main plugin){this.plugin=plugin;}
	public void run(){
		if(plugin.debugMode){
			plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
					"CommandRun\u00a7d3\u00a75 Running");}
		String urlString = "Removed for Git upload";
		try{
			URL urlNames = new URL(urlString);
			HttpURLConnection cNames = (HttpURLConnection) urlNames.openConnection();
			cNames.setRequestMethod("GET");
			cNames.setRequestProperty("User-Agent", plugin.USER_AGENT);
			plugin.inNames = new BufferedReader(new InputStreamReader(cNames.getInputStream()));
			plugin.responseNames = new StringBuffer();
			new CommandRun4(this.plugin).runTaskTimer(this.plugin, 0, 1);}
		catch(Exception e){
			if(plugin.debugMode){
				plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
						"Try/Catch On CR3.  Details Below... Sorry For Spam :P");
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				plugin.sender.sendMessage(errors.toString());}
			plugin.sender.sendMessage(plugin.Tag()+"Error: Something Messed Up While Trying To Get A Response From Mojang <CR3>");
			plugin.sender.sendMessage(plugin.Tag()+"Ya Sure The Server Is Online And Not Offline?  Often Get This"
					+ " ^ If The Server Is Not Connected To The Internet :/");
			if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}
			this.cancel();}}}