package tbtaco.ignCheck;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Main plugin;
	public final String USER_AGENT = "Mozilla/5.0";
	public boolean playerIsUsingACommand_Lock;
	public boolean isAUUID;
	public boolean debugMode;
	public boolean fullSearch;
	public CommandSender sender;
	public String args[];
	public String tagInfo;
	public int x;
	public BufferedReader inID;
	public BufferedReader inNames;
	public BufferedReader inSearch;
	public String UUID;
	public String U_U_I_D;
	public StringBuffer responseID;
	public StringBuffer responseNames;
	public StringBuffer responseSearch;
	public String playerExactName;
	public Object[] playerList;
	public String timestamp;
	public int min;
	public String time;
	public String ThirdPartySite = "Removed for Git upload";//Site prefix
	public String lineOfSevens = "\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~"
			+"\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~"
			+"\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~"
			+"\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~\u00a737\u00a77~\u00a767\u00a77~";
	public void onEnable(){
		plugin = this;
		plugin.x=0;
		plugin.debugMode=false;
		plugin.min=0;
		plugin.time="-[None]-";
		plugin.playerIsUsingACommand_Lock=false;
		this.getCommand("Names").setExecutor(new Command(this));
		this.getCommand("Name").setExecutor(new Command(this));
		this.getCommand("N").setExecutor(new Command(this));
		this.getCommand("IgnCheck").setExecutor(new Command(this));
		new Timer(this).runTaskTimer(this, 0, 1200);
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mIgnCheck\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Successfully Enabled\u001B[0m");
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mIgnCheck\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"\"IgnCheck Help\" To List Commands For This Plugin\u001B[0m");}
	public void onLoad(){
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mIgnCheck\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");}
	public void onDisable(){
		plugin = null;
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mIgnCheck\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");}
	public String Tag(){
		String newTag="";
		if(plugin.tagInfo=="Names"){
			String a = "";
			String b = "";
			double randNum = Math.abs(Math.floor(Math.random()*(1-4)+1));
			int randNum2 = (int) randNum;
			switch (randNum2){
			case 0:  a = "\u00a7f";break;
			case 1:  a = "\u00a77";break;
			case 2:  a = "\u00a78";break;}
			randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));
			randNum2 = (int) randNum;
			switch (randNum2){
				case 0:  b = "\u00a73"+"N"+"\u00a7b"+"a"+"\u00a73"+"m"+"\u00a7b"+"e"+"\u00a73"+"s";break;
				case 1:  b = "\u00a7b"+"N"+"\u00a73"+"a"+"\u00a7b"+"m"+"\u00a73"+"e"+"\u00a7b"+"s";break;
				case 2:  b = "\u00a76"+"N"+"\u00a7e"+"a"+"\u00a76"+"m"+"\u00a7e"+"e"+"\u00a76"+"s";break;
				case 3:  b = "\u00a7e"+"N"+"\u00a76"+"a"+"\u00a7e"+"m"+"\u00a76"+"e"+"\u00a7e"+"s";break;
				case 4:  b = "\u00a71"+"N"+"\u00a79"+"a"+"\u00a71"+"m"+"\u00a79"+"e"+"\u00a71"+"s";break;
				case 5:  b = "\u00a79"+"N"+"\u00a71"+"a"+"\u00a79"+"m"+"\u00a71"+"e"+"\u00a79"+"s";break;
				case 6:  b = "\u00a74"+"N"+"\u00a7c"+"a"+"\u00a74"+"m"+"\u00a7c"+"e"+"\u00a74"+"s";break;
				case 7:  b = "\u00a7c"+"N"+"\u00a74"+"a"+"\u00a7c"+"m"+"\u00a74"+"e"+"\u00a7c"+"s";break;
				case 8:  b = "\u00a75"+"N"+"\u00a7d"+"a"+"\u00a75"+"m"+"\u00a7d"+"e"+"\u00a75"+"s";break;
				case 9:  b = "\u00a7d"+"N"+"\u00a75"+"a"+"\u00a7d"+"m"+"\u00a75"+"e"+"\u00a7d"+"s";break;
				case 10:  b = "\u00a72"+"N"+"\u00a7a"+"a"+"\u00a72"+"m"+"\u00a7a"+"e"+"\u00a72"+"s";break;
				case 11:  b = "\u00a7a"+"N"+"\u00a72"+"a"+"\u00a7a"+"m"+"\u00a72"+"e"+"\u00a7a"+"s";break;}
			newTag=a+"["+b+a+"]"+"\u00a76"+" ";}
		else if(plugin.tagInfo=="Name"){
			String a = "";
			String b = "";
			double randNum = Math.abs(Math.floor(Math.random()*(1-4)+1));
			int randNum2 = (int) randNum;
			switch (randNum2){
			case 0:  a = "\u00a7f";break;
			case 1:  a = "\u00a77";break;
			case 2:  a = "\u00a78";break;}
			randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));
			randNum2 = (int) randNum;
			switch (randNum2){
				case 0:  b = "\u00a73"+"N"+"\u00a7b"+"a"+"\u00a73"+"m"+"\u00a7b"+"e";break;
				case 1:  b = "\u00a7b"+"N"+"\u00a73"+"a"+"\u00a7b"+"m"+"\u00a73"+"e";break;
				case 2:  b = "\u00a76"+"N"+"\u00a7e"+"a"+"\u00a76"+"m"+"\u00a7e"+"e";break;
				case 3:  b = "\u00a7e"+"N"+"\u00a76"+"a"+"\u00a7e"+"m"+"\u00a76"+"e";break;
				case 4:  b = "\u00a71"+"N"+"\u00a79"+"a"+"\u00a71"+"m"+"\u00a79"+"e";break;
				case 5:  b = "\u00a79"+"N"+"\u00a71"+"a"+"\u00a79"+"m"+"\u00a71"+"e";break;
				case 6:  b = "\u00a74"+"N"+"\u00a7c"+"a"+"\u00a74"+"m"+"\u00a7c"+"e";break;
				case 7:  b = "\u00a7c"+"N"+"\u00a74"+"a"+"\u00a7c"+"m"+"\u00a74"+"e";break;
				case 8:  b = "\u00a75"+"N"+"\u00a7d"+"a"+"\u00a75"+"m"+"\u00a7d"+"e";break;
				case 9:  b = "\u00a7d"+"N"+"\u00a75"+"a"+"\u00a7d"+"m"+"\u00a75"+"e";break;
				case 10:  b = "\u00a72"+"N"+"\u00a7a"+"a"+"\u00a72"+"m"+"\u00a7a"+"e";break;
				case 11:  b = "\u00a7a"+"N"+"\u00a72"+"a"+"\u00a7a"+"m"+"\u00a72"+"e";break;}
			newTag=a+"["+b+a+"]"+"\u00a76"+" ";}
		else if(plugin.tagInfo=="IgnCheck"){
			String a = "";
			String b = "";
			double randNum = Math.abs(Math.floor(Math.random()*(1-4)+1));
			int randNum2 = (int) randNum;
			switch (randNum2){
			case 0:  a = "\u00a7f";break;
			case 1:  a = "\u00a77";break;
			case 2:  a = "\u00a78";break;}
			randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));
			randNum2 = (int) randNum;
			switch (randNum2){
				case 0:  b = "\u00a73"+"I"+"\u00a7b"+"g"+"\u00a73"+"n"+"\u00a7b"+"C"+"\u00a73"+"h"+"\u00a7b"+"e"+"\u00a73"+"c"+"\u00a7b"
						+"k";break;
				case 1:  b = "\u00a7b"+"I"+"\u00a73"+"g"+"\u00a7b"+"n"+"\u00a73"+"C"+"\u00a7b"+"h"+"\u00a73"+"e"+"\u00a7b"+"c"+"\u00a73"
						+"k";break;
				case 2:  b = "\u00a76"+"I"+"\u00a7e"+"g"+"\u00a76"+"n"+"\u00a7e"+"C"+"\u00a76"+"h"+"\u00a7e"+"e"+"\u00a76"+"c"+"\u00a7e"
						+"k";break;
				case 3:  b = "\u00a7e"+"I"+"\u00a76"+"g"+"\u00a7e"+"n"+"\u00a76"+"C"+"\u00a7e"+"h"+"\u00a76"+"e"+"\u00a7e"+"c"+"\u00a76"
						+"k";break;
				case 4:  b = "\u00a71"+"I"+"\u00a79"+"g"+"\u00a71"+"n"+"\u00a79"+"C"+"\u00a71"+"h"+"\u00a79"+"e"+"\u00a71"+"c"+"\u00a79"
						+"k";break;
				case 5:  b = "\u00a79"+"I"+"\u00a71"+"g"+"\u00a79"+"n"+"\u00a71"+"C"+"\u00a79"+"h"+"\u00a71"+"e"+"\u00a79"+"c"+"\u00a71"
						+"k";break;
				case 6:  b = "\u00a74"+"I"+"\u00a7c"+"g"+"\u00a74"+"n"+"\u00a7c"+"C"+"\u00a74"+"h"+"\u00a7c"+"e"+"\u00a74"+"c"+"\u00a7c"
						+"k";break;
				case 7:  b = "\u00a7c"+"I"+"\u00a74"+"g"+"\u00a7c"+"n"+"\u00a74"+"C"+"\u00a7c"+"h"+"\u00a74"+"e"+"\u00a7c"+"c"+"\u00a74"
						+"k";break;
				case 8:  b = "\u00a75"+"I"+"\u00a7d"+"g"+"\u00a75"+"n"+"\u00a7d"+"C"+"\u00a75"+"h"+"\u00a7d"+"e"+"\u00a75"+"c"+"\u00a7d"
						+"k";break;
				case 9:  b = "\u00a7d"+"I"+"\u00a75"+"g"+"\u00a7d"+"n"+"\u00a75"+"C"+"\u00a7d"+"h"+"\u00a75"+"e"+"\u00a7d"+"c"+"\u00a75"
						+"k";break;
				case 10:  b = "\u00a72"+"I"+"\u00a7a"+"g"+"\u00a72"+"n"+"\u00a7a"+"C"+"\u00a72"+"h"+"\u00a7a"+"e"+"\u00a72"+"c"+"\u00a7a"
						+"k";break;
				case 11:  b = "\u00a7a"+"I"+"\u00a72"+"g"+"\u00a7a"+"n"+"\u00a72"+"C"+"\u00a7a"+"h"+"\u00a72"+"e"+"\u00a7a"+"c"+"\u00a72"
						+"k";break;}
			newTag=a+"["+b+a+"]"+"\u00a76"+" ";}
		else if(plugin.tagInfo=="N"){
			String a = "";
			String b = "";
			double randNum = Math.abs(Math.floor(Math.random()*(1-4)+1));
			int randNum2 = (int) randNum;
			switch (randNum2){
			case 0:  a = "\u00a7f";break;
			case 1:  a = "\u00a77";break;
			case 2:  a = "\u00a78";break;}
			randNum = Math.abs(Math.floor(Math.random()*(1-13)+1));
			randNum2 = (int) randNum;
			switch (randNum2){
				case 0:  b = "\u00a71"+"N";break;
				case 1:  b = "\u00a72"+"N";break;
				case 2:  b = "\u00a73"+"N";break;
				case 3:  b = "\u00a74"+"N";break;
				case 4:  b = "\u00a75"+"N";break;
				case 5:  b = "\u00a76"+"N";break;
				case 6:  b = "\u00a79"+"N";break;
				case 7:  b = "\u00a7a"+"N";break;
				case 8:  b = "\u00a7b"+"N";break;
				case 9:  b = "\u00a7c"+"N";break;
				case 10:  b = "\u00a7d"+"N";break;
				case 11:  b = "\u00a7e"+"N";break;}
			newTag=a+"["+b+a+"]"+"\u00a76"+" ";}
		else if(plugin.tagInfo=="CLS"){
			newTag="\u00a78[\u00a77CLS\u00a78]\u00a77 ";}
		else{
			newTag="\u00a74"+"["+"\u00a7c"+"Error-IgnCheck_Tag_Error"+"\u00a74"+"]"+"\u00a76"+" ";}
		return newTag;}
	public void info(){
		plugin.sender.sendMessage("\u00a74 *\u00a75Plugin Name:\u00a76 IgnCheck"
				+"\u00a74                    *\u00a75Version:\u00a76 7.7.7");
		plugin.sender.sendMessage("\u00a74 *\u00a75Permission:\u00a76 IgnCheck.Commands"
				+"\u00a74   *\u00a75Creator:\u00a76 tbtaco"
				+" \u00a78<\u00a74~\u00a76T\u00a77a\u00a76c\u00a77o\u00a78>");
		plugin.sender.sendMessage("\u00a74 *\u00a75Main Commands: \u00a73/IgnCheck\u00a77"
				+", \u00a73/Names\u00a77, \u00a73/Name\u00a77, Or \u00a73/N");
		plugin.sender.sendMessage("\u00a74 *\u00a75Bukkit Version: "
				+"\u00a78<\u00a77bukkit\u00a78-\u00a741.8.3\u00a78-\u00a77R0.1"
				+"\u00a78-\u00a74SNAPSHOT\u00a78-\u00a77shaded\u00a78>");
		plugin.sender.sendMessage("\u00a74 *\u00a75Removed for Git upload");
		plugin.sender.sendMessage("\u00a74 *\u00a77Removed for Git upload");
		plugin.sender.sendMessage("\u00a74 *\u00a77Removed for Git upload");
		plugin.sender.sendMessage("\u00a74 *\u00a75More Info Found At: \u00a77Removed for Git upload");
		plugin.sender.sendMessage("\u00a74 *\u00a75Also Uses: \u00a77Removed for Git upload");
		plugin.sender.sendMessage("\u00a74 *\u00a77The Sites Have Limits To How Many Results They");
		plugin.sender.sendMessage("\u00a74 *\u00a77Give Per Minute, So Be Sure \u00a74***NOT***\u00a77 To Spam :P");
		plugin.sender.sendMessage("\u00a74 *\u00a75Contact Info: "
				+"\u00a77tbtaco@hotmail.com  \u00a73~\u00a76Or In-Game Of Course\u00a73~");}
	public String time(){
		String date = "";
		long longTimestamp = Long.parseLong(plugin.timestamp);
		Timestamp newTimestamp = new Timestamp(longTimestamp);
		Date newDate = new Date(newTimestamp.getTime());
		String dateArray[] = newDate.toString().split("-");
		date=" \u00a77<\u00a73"+dateArray[1]+"\u00a77/\u00a73"+dateArray[2]+"\u00a77/\u00a73"+dateArray[0]+"\u00a77>\u00a76";
		return date;}
	@SuppressWarnings("deprecation")
	public String onlineName(){
		String returnedName = "";
		int playerListLength = plugin.playerList.length;
		int x = 0;
		if(playerListLength==0){}
		else{
			do{
				try{
					String displayName=plugin.getServer().getPlayerExact(plugin.playerExactName).getDisplayName();
					returnedName=returnedName+" \u00a77<\u00a76"+displayName+"\u00a77>\u00a76";
					x=9000;}
				catch(Exception e){}
				x=x+1;}
			while(x<playerListLength);}
		return returnedName;}
	public String[] Separator(String mixedNames){
		String[] Names = new String[0];
		String filteredNames = "";
		int x = 0;
		int l = mixedNames.length();
		do{
			if((mixedNames.charAt(x)==':')||
			   (mixedNames.charAt(x)=='(')||
			   (mixedNames.charAt(x)==')')||
			   (mixedNames.charAt(x)=='[')||
			   (mixedNames.charAt(x)==']')||
			   (mixedNames.charAt(x)=='{')||
			   (mixedNames.charAt(x)=='}')||
			   (mixedNames.charAt(x)==',')){}
			else{
				filteredNames=filteredNames+mixedNames.charAt(x);}
			x=x+1;}
		while(x<l);
		Names = filteredNames.split("\"");
		return Names;}}
