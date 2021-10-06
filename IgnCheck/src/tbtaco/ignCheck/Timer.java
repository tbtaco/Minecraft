package tbtaco.ignCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable{
	private final Main plugin;
	public Timer(Main plugin){
		this.plugin=plugin;}
	public void run(){
		try{
			if(plugin.debugMode){
				Date temporaryDate=new Date();    
				String time = new SimpleDateFormat("hh:mm:ss").format(temporaryDate).toString();
				plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]"
						+ "\u00a75 Plugin Is Currently In Debug Mode!  Don't Forget To Turn It Off Again With \"\u00a73/"
						+ plugin.tagInfo+" Debug\u00a75\" Or Anyone Who Uses This Plugin Will See Debug Stuff :P");
				UUID tacoID = java.util.UUID.fromString("2bcc247c-8cc8-484f-82cd-687d05aa61c8");
				Player taco = Bukkit.getPlayer(tacoID);
				taco.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a78 "
						+plugin.min*1200+"-"+plugin.min+"-"+plugin.time+"-"+time+" /"+plugin.tagInfo+" "+plugin.sender.getName());}}
		catch(Exception e){}
		plugin.playerIsUsingACommand_Lock=false;//Added So The Thing Won't Need A Reload To Reset This Value
		plugin.min=plugin.min+1;}}