package tbtaco.ignCheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandRun2 extends BukkitRunnable{
	private final Main plugin;
	public CommandRun2(Main plugin){this.plugin=plugin;}
	public void run(){
		if(plugin.debugMode){
			plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
					"CommandRun\u00a7b2\u00a75 Running");}
		String inputLine;
		try{
			if((inputLine = plugin.inID.readLine())!=null){
				plugin.responseID.append(inputLine);}
			else{
				try{plugin.inID.close();} 
				catch(Exception e){}
				plugin.UUID=plugin.responseID.substring(7, 39);
				plugin.U_U_I_D="";
				int idCount = 0;
				while(idCount<=31){
					if(idCount==8||idCount==12||idCount==16||idCount==20){
						plugin.U_U_I_D=plugin.U_U_I_D+"-";}
						plugin.U_U_I_D=plugin.U_U_I_D+plugin.UUID.charAt(idCount);
						idCount=idCount+1;}
				new CommandRun3(this.plugin).runTaskLater(this.plugin, 1);
				this.cancel();}}
		catch(Exception e){
			if(plugin.debugMode){
				plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
						"Try/Catch On CR2.  Details Below... Sorry For Spam :P");
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				plugin.sender.sendMessage(errors.toString());}
			plugin.sender.sendMessage(plugin.Tag()+"A UUID Could Not Be Found For "
					+ "\u00a77\"\u00a73"+plugin.args[plugin.x-1]+"\u00a77\"\u00a76");
			plugin.sender.sendMessage(plugin.Tag()+"Be Sure It's Spelled Right And It's Their Current Name :P");
			if(plugin.args[(plugin.x-1)].length()>=30){
				if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}}
			else{
				plugin.sender.sendMessage(plugin.Tag()+"Attempting To Search For Other Names Tied To This One...");
				String name = plugin.args[plugin.x-1].toString();
				String urlString = plugin.ThirdPartySite+name;
				try{
					URL urlSearch = new URL(urlString);
					HttpURLConnection cSearch = (HttpURLConnection) urlSearch.openConnection();
					cSearch.setRequestMethod("GET");
					cSearch.setRequestProperty("User-Agent", plugin.USER_AGENT);
					plugin.inSearch=new BufferedReader(new InputStreamReader(cSearch.getInputStream()));
					plugin.responseSearch=new StringBuffer();
					new CommandRun5(this.plugin).runTaskTimer(this.plugin, 1, 1);}
				catch(Exception e2){
					if(plugin.debugMode){
						plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "
								+"Try/Catch On CR2.  Details Below... Sorry For Spam :P");
						StringWriter errors = new StringWriter();
						e2.printStackTrace(new PrintWriter(errors));
						plugin.sender.sendMessage(errors.toString());}
					plugin.sender.sendMessage(plugin.Tag()+"Error: Something Messed Up While Trying To Get A Response <CR2>");
					plugin.sender.sendMessage(plugin.Tag()+"Ya Sure The Server Is Online And Not Offline?  Often Get This"
							+" ^ If The Server Is Not Connected To The Internet :/");
					if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}};}
			this.cancel();}}}