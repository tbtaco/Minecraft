package tbtaco.ignCheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandRun4 extends BukkitRunnable{
	private final Main plugin;
	public CommandRun4(Main plugin){this.plugin=plugin;}
	public void run(){
		if(plugin.debugMode){
			plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
					"CommandRun\u00a7e4\u00a75 Running");}
		String inputLine;
		boolean errorWithUUID=false;
		try{
			if((inputLine = plugin.inNames.readLine())!=null){
				plugin.responseNames.append(inputLine);}
			else{
				try{
					plugin.inNames.close();
					this.cancel();}//Added This Recently As It'd Try To Continue Running CR4 Even After Finishing In Some Cases...
				catch(Exception e){}
				String mixedNames="";
				try{mixedNames=plugin.responseNames.toString();}
				catch(Exception e){};
				String namesArray[] = null;
				try{namesArray=plugin.Separator(mixedNames);}
				catch(Exception e){errorWithUUID=true;}
				if(!errorWithUUID){
					plugin.playerList=plugin.sender.getServer().getOnlinePlayers().toArray();
					String currentName="";
					if(namesArray.length==4){currentName=namesArray[3];}
					else{currentName=namesArray[namesArray.length-4];}
					String coloredUUID="";
					int idCount = 0;
					while(idCount<=35){
						if(idCount==0||idCount==9||idCount==14||idCount==19||idCount==24){coloredUUID=coloredUUID+"\u00a73";}
						else if(idCount==8||idCount==13||idCount==18||idCount==23){coloredUUID=coloredUUID+"\u00a77";}
						coloredUUID=coloredUUID+plugin.U_U_I_D.charAt(idCount);
						idCount=idCount+1;}
					if(plugin.debugMode){
						int debugC=0;
						while(debugC<namesArray.length){
							plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
									"\u00a75"+debugC+"\u00a77: \u00a75"+namesArray[debugC]);
							debugC=debugC+1;}}
					String finalList = "";
					int nameCount = (namesArray.length+1)/6;
					if(nameCount==0){finalList=finalList+"\u00a76"+namesArray[3];}
					else{
						int count = 0;
						while(count<nameCount){
							if(plugin.debugMode){
								plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
										"Multiple Names Loop: Name "+(count+1)+" Running <Total: "+nameCount+">");}
							if(count==0){finalList=finalList+"\u00a76"+namesArray[3];}
							else{
								finalList=finalList+"\u00a77 -> ";
								finalList=finalList+"\u00a76"+namesArray[((count+1)*6)-5];
								plugin.timestamp=namesArray[((count+1)*6)-2];
								finalList=finalList+"\u00a77"+plugin.time();}
							count=count+1;}}
					plugin.playerExactName=currentName;
					finalList=finalList+plugin.onlineName();
					plugin.sender.sendMessage(plugin.Tag()+"\u00a73"+currentName+" \u00a77=\u00a73 "+coloredUUID);
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76"+finalList);
					if(plugin.debugMode||plugin.fullSearch){
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
						catch(Exception e){
							if(plugin.debugMode){
								plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
									"Try/Catch On CR2.  Details Below... Sorry For Spam :P");
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								plugin.sender.sendMessage(errors.toString());}
							plugin.sender.sendMessage(plugin.Tag()+"Error: Something Messed Up While Trying To Get A Response <CR2>");
							plugin.sender.sendMessage(plugin.Tag()+"Ya Sure The Server Is Online And Not Offline?  Often Get This"
								+ " ^ If The Server Is Not Connected To The Internet :/");
							if(plugin.x==plugin.args.length||plugin.x>=3){
								plugin.playerIsUsingACommand_Lock=false;
								plugin.fullSearch=false;}};}}
				else{
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76The UUID "
							+ "\u00a77\"\u00a73"+plugin.args[plugin.x-1]+"\u00a77\"\u00a76 Doesn't Exist");
					plugin.sender.sendMessage(plugin.Tag()+"Make Sure You Didn't Add Any Extra '-' And That It's Spelled Right :P");}
				if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}
				this.cancel();}}
		catch(Exception e){
			if(plugin.debugMode){
				plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
						"Try/Catch On CR4.  Details Below... Sorry For Spam :P");
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				plugin.sender.sendMessage(errors.toString());}
			plugin.sender.sendMessage(plugin.Tag()+"Error: Something Messed Up While Trying To Get A Response From Mojang <CR4>");
			plugin.sender.sendMessage(plugin.Tag()+"Seeing This ^ Mojang Probably Gave An Odd Response This Plugin Wasn't Expecting");
			plugin.sender.sendMessage(plugin.Tag()+"Please Let Taco Know What Command Caused This So He Can Look Into Fixing It :P");
			if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}
			this.cancel();}}}