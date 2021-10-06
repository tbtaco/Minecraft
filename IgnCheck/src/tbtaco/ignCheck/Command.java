package tbtaco.ignCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{
	private final Main plugin;
	public Command(Main plugin){this.plugin=plugin;}
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args){
		if(!plugin.playerIsUsingACommand_Lock){
			plugin.playerIsUsingACommand_Lock=true;
			plugin.sender=sender;
			plugin.args=args;
			if((commandLabel.equalsIgnoreCase("Names")||
				commandLabel.equalsIgnoreCase("Name")||
				commandLabel.equalsIgnoreCase("N")||
				commandLabel.equalsIgnoreCase("IgnCheck"))){
				Player player = null;
				boolean isPlayer = true;
				try{player = (Player) plugin.sender;}
				catch(Exception e){isPlayer = false;}
				Date temporaryDate=new Date();    
				plugin.time = new SimpleDateFormat("hh:mm:ss").format(temporaryDate).toString();
				plugin.fullSearch=false;
				if(commandLabel.equalsIgnoreCase("Names")){plugin.tagInfo = "Names";}
				else if(commandLabel.equalsIgnoreCase("Name")){plugin.tagInfo = "Name";}
				else if(commandLabel.equalsIgnoreCase("N")){plugin.tagInfo = "N";}
				else if(commandLabel.equalsIgnoreCase("IgnCheck")){plugin.tagInfo = "IgnCheck";}
				else{plugin.tagInfo = "Error";}
				plugin.playerList = plugin.getServer().getOnlinePlayers().toArray();
				if(!isPlayer||player.isOp()||player.hasPermission("IgnCheck.Commands")||
						player.getUniqueId().toString().equalsIgnoreCase("2bcc247c-8cc8-484f-82cd-687d05aa61c8")){
					if(args.length==0){
						plugin.sender.sendMessage(plugin.Tag()+"Need A Name :P \u00a75/"+plugin.tagInfo+" <Name>");
						plugin.sender.sendMessage(plugin.Tag()+"Can Search Up To 3 Names At Once Separated By Spaces");
						plugin.sender.sendMessage(plugin.Tag()+"More Commands Can Be Seen With \u00a77\"\u00a73/"
								+plugin.tagInfo+" Help\u00a77\"\u00a76 :>");
						plugin.playerIsUsingACommand_Lock=false;}
					else if(args.length==1){
						if(args[0].equalsIgnoreCase("Info")||args[0].equalsIgnoreCase("I")){
							plugin.sender.sendMessage(" ");
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.info();
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.sender.sendMessage(" ");
							plugin.sender.sendMessage(plugin.Tag()+"For All Info Including Updated Stuff, "
									+"\u00a77\"\u00a73/"+plugin.tagInfo+" AllInfo\u00a77\"\u00a76 :P");
							plugin.playerIsUsingACommand_Lock=false;}
						else if(args[0].equalsIgnoreCase("AllInfo")){
							plugin.sender.sendMessage(" ");
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.info();
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.sender.sendMessage("\u00a77[\u00a732\u00a78/\u00a7311\u00a77]\u00a76 "
									+"First Release :>");
							plugin.sender.sendMessage("\u00a77[\u00a732\u00a78/\u00a7319\u00a77]\u00a76 "
									+"Shows A Player's Display Name If Online");
							plugin.sender.sendMessage("\u00a76  Shows Date A Person Changed Their Name "
									+"\u00a77<\u00a73M\u00a77/\u00a73D\u00a77/\u00a73Y\u00a77>");
							plugin.sender.sendMessage("\u00a76  Along With A Few Other Small Fixes Here And There");
							plugin.sender.sendMessage("\u00a77[\u00a733\u00a78/\u00a7319\u00a77]\u00a76 "
									+"Complete Rewrite :P Should No Longer Cause");
							plugin.sender.sendMessage("\u00a76  Any Lag While Waiting For A Response From Mojang");
							plugin.sender.sendMessage("\u00a77[\u00a733\u00a78/\u00a7331\u00a77]\u00a76 "
									+"Support For More Name Changes And Bug Fixes");
							plugin.sender.sendMessage("\u00a77[\u00a737\u00a78/\u00a7313\u00a77]\u00a76 "
									+"Rewrote Lots, Now Supports Unlimited Name");
							plugin.sender.sendMessage("\u00a76  Changes.  Can Now Search Using UUID's, With Or");
							plugin.sender.sendMessage("\u00a76  Without \u00a77'\u00a73-\u00a77'\u00a76 Works.  "
									+"Added Debug And Help Command");
							plugin.sender.sendMessage("\u00a76  If UUID Isn't Found Or Using FullSearch/Debug,");
							plugin.sender.sendMessage("\u00a76  It'll Search The Name On The 3rd Site.  The");
							plugin.sender.sendMessage("\u00a76  Results Are Names Related To The Name Searched,");
							plugin.sender.sendMessage("\u00a76  Either Other Name Changes Or Current Name(s)");
							plugin.sender.sendMessage("\u00a76  Along With Plenty Of Improvements Here And There");
							plugin.sender.sendMessage("\u00a77[\u00a737\u00a78/\u00a7325\u00a77]\u00a76 "
									+"Fix For New 3rd Site Layout And");
							plugin.sender.sendMessage("\u00a76  Switched To 1.8.7 From 1.8.3.");
							plugin.sender.sendMessage("\u00a77[\u00a738\u00a78/\u00a7315\u00a77]\u00a76 "
									+"Small Fixes Here And There :P");
							plugin.sender.sendMessage("\u00a77[\u00a738\u00a78/\u00a7316\u00a77]\u00a76 "
									+"Switched Back To 1.8.3 For Now :o");
							plugin.sender.sendMessage("\u00a77[\u00a738\u00a78/\u00a7319\u00a77]\u00a76 "
									+"Switched Back To Java 7 From Java 8");
							
							//Updates Here...
							
							plugin.sender.sendMessage("\u00a77[\u00a73Future Updates\u00a77]\u00a76 "
									+"Need To Work On The 3rd Site's");
							plugin.sender.sendMessage("\u00a76  Code So It Runs Similar To The Other API Sites");
							plugin.sender.sendMessage("\u00a76  Due To The 3rd Site Being An Actual Site, Not");
							plugin.sender.sendMessage("\u00a76  An API, When They Update Stuff, It Could Cause");
							plugin.sender.sendMessage("\u00a76  The Plugin To Give Odd Responses For Some Searches");
							plugin.sender.sendMessage("\u00a76  It Won't Cause Crashes Or Anything, But Will Need");
							plugin.sender.sendMessage("\u00a76  The Code Updated For Their New Site :P");
							plugin.sender.sendMessage("\u00a75  Any Other Suggestions, Contact Taco :>");
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.playerIsUsingACommand_Lock=false;}
						else if(args[0].equalsIgnoreCase("Help")||args[0].equalsIgnoreCase("H")||args[0].equalsIgnoreCase("?")){
							plugin.sender.sendMessage(plugin.Tag()+"Available Commands\u00a77: \u00a73/IgnCheck\u00a77, "
									+"\u00a73/Names\u00a77, \u00a73/Name\u00a77, \u00a73/N");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+" \u00a77<\u00a73Name\u00a77> "
									+"\u00a76To Search For Their UUID And Name History");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+" \u00a77<\u00a73UUID\u00a77> "
									+"\u00a76To Search Name History For That UUID");
							plugin.sender.sendMessage(plugin.Tag()+"Can Either Be Just Numbers Or With The "
									+"\u00a77'\u00a73-\u00a77'\u00a76 In The Number");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+" FullSearch \u00a77<\u00a73Name\u00a77> "
									+"\u00a76Will Search The Name With All");
							plugin.sender.sendMessage(plugin.Tag()+"Sites Instead Of Stopping Once It Gets The Needed Info");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+" Debug \u00a76Puts The Plugin In Debug "
									+"Mode.  If You Use It,");
							plugin.sender.sendMessage(plugin.Tag()+"Don't Forget To Turn It Back Off So Others Using This");
							plugin.sender.sendMessage(plugin.Tag()+"Plugin Don't Get Spammed With Stuff Too :P");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+" \u00a77<\u00a73Info\u00a77|\u00a73I"
									+"\u00a77>\u00a76 Lists Some Info On This Plugin");
							plugin.sender.sendMessage(plugin.Tag()+"\u00a73/"+plugin.tagInfo+"\u00a77 <\u00a73Help\u00a77|\u00a73H"
									+"\u00a77|\u00a73?\u00a77>\u00a76 Lists This Stuff Again :P");
							plugin.playerIsUsingACommand_Lock=false;}
						else if(args[0].equalsIgnoreCase("Debug")){
							if(plugin.debugMode){
								plugin.debugMode=false;
								plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
										"Plugin Is No Longer In Debug Mode!");}
							else{
								plugin.debugMode=true;
								plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
										"Plugin Is Now In Debug Mode!");}
							plugin.sender.sendMessage("\u00a77[\u00a78"+plugin.tagInfo+"\u00a70-\u00a78Debug\u00a77]\u00a75 "+
									"To Switch Debug Mode Back, \"\u00a73/"+plugin.tagInfo+" Debug\u00a75\" Again :>");
							plugin.playerIsUsingACommand_Lock=false;}
						else{
							new CommandRun1(this.plugin).runTaskTimer(this.plugin, 0, 20);}}
					else if(args.length==2&&args[0].equalsIgnoreCase("FullSearch")){
						plugin.fullSearch=true;
						new CommandRun1(this.plugin).runTaskTimer(this.plugin, 0, 20);}
					else{new CommandRun1(this.plugin).runTaskTimer(this.plugin, 0, 20);}}
				else{
					if(args.length==1){
						if(args[0].equalsIgnoreCase("Info")||args[0].equalsIgnoreCase("I")){
							plugin.sender.sendMessage(" ");
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.info();
							plugin.sender.sendMessage(plugin.lineOfSevens);
							plugin.sender.sendMessage(" ");
							plugin.sender.sendMessage(plugin.Tag()+"You Don't Appear To Have Perms For This Plugin, "
									+"So The Commands You Can Use Are Rather Limited... :P");
							plugin.playerIsUsingACommand_Lock=false;}
						else if(args[0].equalsIgnoreCase("Help")||args[0].equalsIgnoreCase("H")||args[0].equalsIgnoreCase("?")){
							plugin.sender.sendMessage(plugin.Tag()+"You Don't Appear To Have Perms To Use This Plugin, So");
							plugin.sender.sendMessage(plugin.Tag()+"All You Can Do Is List This Help Message And Display");
							plugin.sender.sendMessage(plugin.Tag()+"Some Of The Plugin Info With \u00a77\"\u00a73/"
									+plugin.tagInfo+" Info\u00a77\"\u00a76 :P");
							plugin.playerIsUsingACommand_Lock=false;}
						else if(args[0].equalsIgnoreCase("Debug")){
							plugin.sender.sendMessage(plugin.Tag()+"Shouldn't Be Seeing Debug Stuff >.> "
									+"But Forcing It Off Anyway As You Probably Saw Messages Saying To Type This <.<");
							plugin.debugMode=false;
							plugin.playerIsUsingACommand_Lock=false;}
						else{
							plugin.sender.sendMessage(plugin.Tag()+"\u00a74Error: \u00a7c"
									+"You Don't Appear To Have The Necessary Permissions Added To Use \"\u00a76"
									+"/"+plugin.tagInfo+"\u00a7c\" If You Feel This Is A Mistake, Please Talk To A Staff Member :>");}}
					else{
						plugin.sender.sendMessage(plugin.Tag()+"\u00a74Error: \u00a7c"
								+"You Don't Appear To Have The Necessary Permissions Added To Use \"\u00a76"
								+"/"+plugin.tagInfo+"\u00a7c\" If You Feel This Is A Mistake, Please Talk To A Staff Member :>");}
					plugin.playerIsUsingACommand_Lock=false;}}
			else if(commandLabel.equalsIgnoreCase("CLS")){
				plugin.playerIsUsingACommand_Lock=false;
				plugin.tagInfo="CLS";
				int tempCount = 0;
				if(args.length==0){
					sender.sendMessage(plugin.Tag()+"Clearing Screen...");
						while(tempCount<100){
							sender.sendMessage("");
							tempCount++;}}
				else if(args.length==1){
					Player player = null;
					boolean isPlayer = true;
					try{player = (Player) plugin.sender;}
					catch(Exception e){isPlayer = false;}
					if(!isPlayer||player.isOp()||player.hasPermission("IgnCheck.Commands")||
							player.getUniqueId().toString().equalsIgnoreCase("2bcc247c-8cc8-484f-82cd-687d05aa61c8")){
						try{
							@SuppressWarnings("deprecation")
							Player temporaryPlayer = Bukkit.getPlayer(args[0]);
							temporaryPlayer.sendMessage(plugin.Tag()+"Clearing Screen...");
							sender.sendMessage(plugin.Tag()+"Clearing \u00a7c"+args[0]+"\u00a77's Screen...");
							while(tempCount<100){
								temporaryPlayer.sendMessage("");
								tempCount++;}}
						catch(Exception e){
							sender.sendMessage(plugin.Tag()+"Player \"\u00a7c"+args[0]+"\u00a77\" Not Found...");}}
					else{
						sender.sendMessage(plugin.Tag()+"You Don't Appear To Have Permission To Clear Their Chat :P");}}
				else{
					sender.sendMessage(plugin.Tag()+"Too Many Arguments: /CLS Or /CLS <Name>");}}}
		else{
			sender.sendMessage(plugin.Tag()+"Plugin Is Busy Doing Something Right Now.  "
					+"Please Try Your Command Again In A Second Or Two :>");}
		return true;}}