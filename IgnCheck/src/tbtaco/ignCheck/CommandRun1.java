package tbtaco.ignCheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandRun1 extends BukkitRunnable{
	private final Main plugin;
	public CommandRun1(Main plugin){
		plugin.x=0;
		this.plugin=plugin;}
	public void run(){
		if(plugin.debugMode){
			plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
					"CommandRun\u00a7a1\u00a75 Running");}
		plugin.isAUUID=false;
		if(plugin.x>=3){
			plugin.sender.sendMessage(plugin.Tag()+"Limit Is 3 Name Searches At Once.  Ignoring Others :P");
			this.cancel();}
		else{
			if(plugin.args[plugin.x].equalsIgnoreCase("tbhacko")||//                         :>
			   plugin.args[plugin.x].equalsIgnoreCase("d1f6f824e64b460cbfdaea73d5d56334")||
			   plugin.args[plugin.x].equalsIgnoreCase("d1f6f824-e64b-460c-bfda-ea73d5d56334")){
				if(plugin.sender instanceof Player){
					String randomName1 = "";String randomName2 = "";String randomName3 = "";
					int[] d = new int[7];String[] ds = new String[7];
					double randNum = Math.abs(Math.floor(Math.random()*(1-9)+1));int randNum2 = (int) randNum;
					switch(randNum2){
						case 0:  randomName1 = "\u00a76\u00a7k***";break;
						case 1:  randomName1 = "\u00a76\u00a7k****";break;
						case 2:  randomName1 = "\u00a76\u00a7k*****";break;
						case 3:  randomName1 = "\u00a76\u00a7k******";break;
						case 4:  randomName1 = "\u00a76\u00a7k*******";break;
						case 5:  randomName1 = "\u00a76\u00a7k********";break;
						case 6:  randomName1 = "\u00a76\u00a7k*********";break;
						case 7:  randomName1 = "\u00a76\u00a7k**********";break;}
					randNum = Math.abs(Math.floor(Math.random()*(1-10)+1));randNum2 = (int) randNum;
					switch(randNum2){
						case 0:  randomName2 = "\u00a76\u00a7k******";break;
						case 1:  randomName2 = "\u00a76\u00a7k*******";break;
						case 2:  randomName2 = "\u00a76\u00a7k********";break;
						case 3:  randomName2 = "\u00a76\u00a7k*********";break;
						case 4:  randomName2 = "\u00a76\u00a7k**********";break;
						case 5:  randomName2 = "\u00a76\u00a7k***********";break;
						case 6:  randomName2 = "\u00a76\u00a7k************";break;
						case 7:  randomName2 = "\u00a76\u00a7k*************";break;
						case 8:  randomName2 = "\u00a76\u00a7k**************";break;}
					randNum = Math.abs(Math.floor(Math.random()*(1-6)+1));randNum2 = (int) randNum;
					switch(randNum2){
						case 0:  randomName3 = "\u00a76\u00a7k*\u00a77\u00a7k*\u00a76\u00a7k*";break;
						case 1:  randomName3 = "\u00a76\u00a7k*\u00a73\u00a7k*\u00a76\u00a7k*\u00a73\u00a7k*";break;
						case 2:  randomName3 = "\u00a76\u00a7k*\u00a77\u00a7k*\u00a76\u00a7k*\u00a77\u00a7k*\u00a76\u00a7k*";break;
						case 3:  randomName3 = "\u00a76\u00a7k*\u00a73\u00a7k*\u00a76\u00a7k*\u00a73\u00a7k*"
								+ "\u00a76\u00a7k*\u00a73\u00a7k*";break;
						case 4:  randomName3 = "\u00a76\u00a7k*\u00a77\u00a7k*\u00a76\u00a7k*\u00a77\u00a7k*"
								+ "\u00a76\u00a7k*\u00a77\u00a7k*\u00a76\u00a7k*";break;}
					randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));//0-11
					d[0] = (int) randNum + 1;if(d[0]<=9){ds[0]="0"+d[0];}else{ds[0]=""+d[0];}//Done!
					randNum = Math.abs(Math.floor(Math.random()*(1-30)+1));//0-11
					d[1] = (int) randNum + 1;if(d[1]<=9){ds[1]="0"+d[1];}else{ds[1]=""+d[1];}
					randNum = Math.abs(Math.floor(Math.random()*(1-70)+1));//0-11
					d[2] = (int) randNum + 1;ds[2]=d[2]+1950+"";
					randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));//0-11
					d[3] = (int) randNum + 1;if(d[3]<=9){ds[3]="0"+d[3];}else{ds[3]=""+d[3];}//Done!
					randNum = Math.abs(Math.floor(Math.random()*(1-30)+1));//0-11
					d[4] = (int) randNum + 1;if(d[4]<=9){ds[4]="0"+d[4];}else{ds[4]=""+d[4];}
					randNum = Math.abs(Math.floor(Math.random()*(1-80)+1));//0-11
					d[5] = (int) randNum + 1;ds[5]=d[5]+2021+"";
					plugin.sender.sendMessage(plugin.Tag()+"\u00a73tbhacko \u00a77= \u00a7377777777\u00a77-\u00a737777\u00a77-"
							+ "\u00a737777\u00a77-\u00a737777\u00a77-\u00a73777777777777");
					plugin.sender.sendMessage(plugin.Tag()+randomName1+"\u00a77 -> "+randomName2+"\u00a77 <\u00a73"+ds[0]+"\u00a77/"
							+ "\u00a73"+ds[1]+"\u00a77/\u00a73"+ds[2]+"\u00a77> -> \u00a76tbhacko \u00a77<\u00a73"+ds[3]+"\u00a77/"
							+ "\u00a73"+ds[4]+"\u00a77/\u00a73"+ds[5]+"\u00a77> <\u00a74~"+randomName3+"\u00a77>");
					if(plugin.fullSearch){
						plugin.sender.sendMessage(plugin.Tag()+"Full Search Failed Due To Them Hax >.> <.< >.>");}}
				else{
					plugin.sender.sendMessage(plugin.Tag()+"\u00a73tbhacko \u00a77= \u00a7377777777\u00a77-\u00a737777\u00a77-"
							+ "\u00a737777\u00a77-\u00a737777\u00a77-\u00a73777777777777");
					plugin.sender.sendMessage(plugin.Tag()+"\u00a77        ->              <  /  /    > -> "
							+ "\u00a76tbhacko \u00a77<  /  /    > <\u00a74~\u00a77       >");}
				if((plugin.x+1)==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}}
			else if(plugin.args[plugin.x].length()==32){
				plugin.isAUUID=true;
				boolean spellCheck = true;
				plugin.UUID=plugin.args[plugin.x].toString();
				plugin.U_U_I_D="";
				int idCount = 0;
				while(idCount<=31){
					if(idCount==8||idCount==12||idCount==16||idCount==20){
						plugin.U_U_I_D=plugin.U_U_I_D+"-";}
					if(plugin.args[plugin.x].charAt(idCount)=='-'){spellCheck=false;}
					plugin.U_U_I_D=plugin.U_U_I_D+plugin.args[plugin.x].charAt(idCount);
					idCount=idCount+1;}
				if(spellCheck){
					new CommandRun3(this.plugin).runTaskLater(this.plugin, 1);}
				else{
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76There Was An Error With The UUID Provided... "
							+ "\u00a77<\u00a73"+plugin.args[plugin.x]+"\u00a77>");
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76Make Sure You Didn't Add Any Extra '-' "
							+ "And That It's Spelled Right :P");
					if((plugin.x+1)==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}}}
			else if(plugin.args[plugin.x].length()==36){
				plugin.isAUUID=true;
				boolean spellCheck = true;
				plugin.U_U_I_D=plugin.args[plugin.x].toString();
				plugin.UUID="";
				int idCount = 0;
				while(idCount<=35){
					if(idCount==8||idCount==13||idCount==18||idCount==23){
						if(plugin.args[plugin.x].charAt(idCount)=='-'){}
						else{spellCheck=false;}}
					else{if(plugin.args[plugin.x].charAt(idCount)=='-'){spellCheck=false;}
						else{plugin.UUID=plugin.UUID+plugin.args[plugin.x].charAt(idCount);}}
					idCount=idCount+1;}
				if(spellCheck){
					new CommandRun3(this.plugin).runTaskLater(this.plugin, 1);}
				else{
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76There Was An Error With The UUID Provided... "
							+ "\u00a77<\u00a73"+plugin.args[plugin.x]+"\u00a77>");
					plugin.sender.sendMessage(plugin.Tag()+"\u00a76Correct Format: \u00a7300000000-0000-0000-0000-000000000000");
					if((plugin.x+1)==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}}}
			else if(plugin.fullSearch&&plugin.x==0){
				plugin.sender.sendMessage(plugin.Tag()+"Running Full Search For \u00a77\"\u00a73"+plugin.args[1]+"\u00a77\"");}
			else{
				String name = plugin.args[plugin.x].toString();
				String urlString = "Removed for Git upload"+name;
				try{
					URL urlID = new URL(urlString);
					HttpURLConnection cID = (HttpURLConnection) urlID.openConnection();
					cID.setRequestMethod("GET");
					cID.setRequestProperty("User-Agent", plugin.USER_AGENT);
					plugin.inID = new BufferedReader(new InputStreamReader(cID.getInputStream()));
					plugin.responseID = new StringBuffer();
					new CommandRun2(this.plugin).runTaskTimer(this.plugin, 0, 1);}
				catch(Exception e){
					if(plugin.debugMode){
						plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
								"Try/Catch On CR1.  Details Below... Sorry For Spam :P");
						StringWriter errors = new StringWriter();
						e.printStackTrace(new PrintWriter(errors));
						plugin.sender.sendMessage(errors.toString());}
					plugin.sender.sendMessage(plugin.Tag()+"Error: Something Messed Up While Trying To Get A Response From Mojang <CR1>");
					plugin.sender.sendMessage(plugin.Tag()+"Ya Sure The Server Is Online And Not Offline?  Often Get This"
							+ " ^ If The Server Is Not Connected To The Internet :/");
					if((plugin.x+1)==plugin.args.length||plugin.x>=3){plugin.playerIsUsingACommand_Lock=false;}}}
			plugin.x=plugin.x+1;
			if(plugin.x==plugin.args.length){this.cancel();}}}}