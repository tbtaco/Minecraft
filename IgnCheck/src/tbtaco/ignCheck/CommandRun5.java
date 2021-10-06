package tbtaco.ignCheck;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandRun5 extends BukkitRunnable{
	private final Main plugin;
	public CommandRun5(Main plugin){this.plugin=plugin;}
	public void run(){
		if(plugin.debugMode){
			plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
					"CommandRun\u00a795\u00a75 Running");}
		String inputLine;
		String nameResultsList="";
		try{
			if((inputLine = plugin.inSearch.readLine())!=null){
				plugin.responseSearch.append(inputLine);
				while((inputLine = plugin.inSearch.readLine())!=null){
					plugin.responseSearch.append(inputLine);}
				}//Need To Find A Way To Remove This While Loop... Just Scheduler Won't Work Though... :/
			else{
				try{plugin.inSearch.close();} 
				catch(Exception e){}
				String searchString = plugin.responseSearch.toString();
				String[] searchArray = searchString.split("=\"/s\\?");///////////////////////////////////////////////////
				int count = 0;
				String debugName = "";
				while(count<searchArray.length){
					searchArray[count]=searchArray[count].substring(0, 25);//////////////////////////////////////////////
					if(searchArray[count].charAt(1)=='!'||searchArray[count].charAt(8)=='-'){debugName="~None~";}////////
					else{
						int tempCount = 0;
						while(tempCount<=18){
							if(searchArray[count].charAt(tempCount)=='"'){
								nameResultsList=nameResultsList+" ";
								tempCount=20;}
							else{
								nameResultsList=nameResultsList+searchArray[count].charAt(tempCount);
								debugName=debugName+searchArray[count].charAt(tempCount);
								tempCount=tempCount+1;}}}
					if(plugin.debugMode){
						plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "
							+"CR5 Separator Loop \u00a77"+(count+1)+"\u00a78/\u00a77"+searchArray.length+"\u00a75: "
							+"\u00a78"+searchArray[count]+"\u00a75 = \u00a77"+debugName);}
					debugName="";
					count=count+1;}
				if(nameResultsList.length()<2){
					plugin.sender.sendMessage(plugin.Tag()+"Search For \u00a77\"\u00a73"+plugin.args[plugin.x-1]+"\u00a77\"\u00a76 "
							+ "Didn't Return Any Results");}
				else{
					plugin.sender.sendMessage(plugin.Tag()+"Results Found - Try Searching Some Of The Names Below");
					plugin.sender.sendMessage(plugin.Tag()+"Results: "+nameResultsList);}
				if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;plugin.fullSearch=false;}
				this.cancel();}}
		catch(Exception e){
			if(plugin.debugMode){
				plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "
						+"Try/Catch On CR5.  Details Below... Sorry For Spam :P");
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				plugin.sender.sendMessage(errors.toString());}
			plugin.sender.sendMessage(plugin.Tag()+"Search For \u00a77\"\u00a73"+plugin.args[plugin.x-1]+"\u00a77\"\u00a76 "
					+ "Didn't Return Any Results");
			if(plugin.x==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;plugin.fullSearch=false;}
			this.cancel();}}}