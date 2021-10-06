package tbtaco.TacosEntityPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TacosEntityPlugin extends JavaPlugin
{
	public static TacosEntityPlugin plugin;
	public void onEnable()
	{
		plugin = this;
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mTacosEntityPlugin\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Enabling Plugin...\u001B[0m");
		this.getCommand("TacosEntityPlugin").setExecutor(new Command());
		this.getCommand("Entity").setExecutor(new Command());
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mTacosEntityPlugin\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Successfully Enabled\u001B[0m");
	}
	public void onLoad()
	{
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mTacosEntityPlugin\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");
	}
	public void onDisable()
	{
		plugin=null;
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mTacosEntityPlugin\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
}
class Command implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender,org.bukkit.command.Command cmd, String label, String[] args) 
	{
		boolean console = false;
		if(!(sender instanceof Player))
			console = true;
		Player player = null;
		if(!console)
			player = (Player) sender;
		if(console||player.hasPermission("TacosEntityPlugin.Entity"))
		{
			if(args.length==0)
				Methods.commands(sender);
			else if(args.length==1&&(args[0].equalsIgnoreCase("Information")||
									 args[0].equalsIgnoreCase("Info")||
									 args[0].equalsIgnoreCase("I")||
									 args[0].equalsIgnoreCase("Help")||
									 args[0].equalsIgnoreCase("H")||
									 args[0].equalsIgnoreCase("Version")||
									 args[0].equalsIgnoreCase("Ver")||
									 args[0].equalsIgnoreCase("V")||
									 args[0].equalsIgnoreCase("About")||
									 args[0].equalsIgnoreCase("A")||
									 args[0].equalsIgnoreCase("?")))
			{
				Methods.info(sender);
				Methods.commands(sender);
			}
			else if(args.length>=1&&(args[0].equalsIgnoreCase("Count")||
									 args[0].equalsIgnoreCase("C")||
									 args[0].equalsIgnoreCase("Number")||
									 args[0].equalsIgnoreCase("Num")||
									 args[0].equalsIgnoreCase("N")||
									 args[0].equalsIgnoreCase("Amount")||
									 args[0].equalsIgnoreCase("A")||
									 args[0].equalsIgnoreCase("Total")||
									 args[0].equalsIgnoreCase("T")))
			{
				if(args.length==3&&(args[1].equalsIgnoreCase("All")||
								    args[1].equalsIgnoreCase("A")||
								    args[1].equalsIgnoreCase("Everyone")||
								    args[1].equalsIgnoreCase("Everybody")||
								    args[1].equalsIgnoreCase("E")))
				{
					try
					{
						int radius = Integer.parseInt(args[2]);
						if(radius<=0)
							throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
						@SuppressWarnings("unchecked")
						List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
						for(Player p : players)
						{
							EntitySearch search = new EntitySearch(p,radius);
							search.PrintAmount(sender);
						}
						if(players.size()==0)
							sender.sendMessage(Methods.tag()+"Nobody Is Online...");
					}
					catch(Exception e)
					{
						sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
						sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
					}
				}
				else if(args.length==2)
				{
					if(console)
						sender.sendMessage(Methods.tag()+"Console Must Specify A Player To Use This Command!");
					else
					{
						try
						{
							int radius = Integer.parseInt(args[1]);
							if(radius<=0)
								throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
							EntitySearch search = new EntitySearch(player,radius);
							search.PrintDetails(sender);
						}
						catch(Exception e)
						{
							sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
							sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
						}
					}
				}
				else if(args.length==3)
				{
					Player p = Bukkit.getPlayer(args[1]);
					if(p!=null&&p.isOnline())
					{
						try
						{
							int radius = Integer.parseInt(args[2]);
							if(radius<=0)
								throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
							EntitySearch search = new EntitySearch(p,radius);
							search.PrintDetails(sender);
						}
						catch(Exception e)
						{
							sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
							sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
						}
					}
					else
						sender.sendMessage(Methods.tag()+"Player Was Not Found...");
				}
				else
					Methods.commands(sender);
			}
			else if(args.length>=1&&(args[0].equalsIgnoreCase("Remove")||
									 args[0].equalsIgnoreCase("Rem")||
									 args[0].equalsIgnoreCase("R")||
									 args[0].equalsIgnoreCase("Delete")||
									 args[0].equalsIgnoreCase("Del")||
									 args[0].equalsIgnoreCase("D")||
									 args[0].equalsIgnoreCase("Clear")||
									 args[0].equalsIgnoreCase("Purge")||
									 args[0].equalsIgnoreCase("P")||
									 args[0].equalsIgnoreCase("Kill")||
									 args[0].equalsIgnoreCase("K")))
			{
				if(args.length>=3&&(args[1].equalsIgnoreCase("All")||
									args[1].equalsIgnoreCase("A")||
									args[1].equalsIgnoreCase("Everyone")||
									args[1].equalsIgnoreCase("Everybody")||
									args[1].equalsIgnoreCase("E")))
				{
					try
					{
						int radius = Integer.parseInt(args[2]);
						if(radius<=0)
							throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
						String filter = "";
						if(args.length>3)
							for(int i=3;i<args.length;i++)
							{
								if(i!=3)
									filter+=" ";
								filter+=args[i];
							}
						boolean lightning = false;
						if(filter.contains("-l")||filter.contains("-L"))
						{
							lightning = true;
							filter = filter.replaceAll("-l ", "").replaceAll(" -l", "").replaceAll("-L ", "").replaceAll(" -L", "").replaceAll("-l", "").replaceAll("-L", "");
						}
						@SuppressWarnings("unchecked")
						List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
						for(Player p : players)
						{
							EntitySearch search = new EntitySearch(p,radius);
							int removed = 0;
							if(filter.length()==0)
								removed = search.RemoveAll(lightning);
							else
								removed = search.Remove(lightning, filter);
							sender.sendMessage(Methods.tag()+"Entities Removed Within "+radius+" Blocks Of "+p.getDisplayName()+"\u00a73: \u00a7b"+removed);
						}
						if(players.size()==0)
							sender.sendMessage(Methods.tag()+"Nobody Is Online...");
					}
					catch(Exception e)
					{
						sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
						sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
					}
				}
				else
				{
					if(args.length>=3)
					{
						Player p = Bukkit.getPlayer(args[1]);
						if(p!=null&&p.isOnline())
						{
							try
							{
								int radius = Integer.parseInt(args[2]);
								if(radius<=0)
									throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
								String filter = "";
								if(args.length>=4)
									for(int i=3;i<args.length;i++)
									{
										if(i!=3)
											filter+=" ";
										filter+=args[i];
									}
								boolean lightning = false;
								if(filter.contains("-l")||filter.contains("-L"))
								{
									lightning = true;
									filter = filter.replaceAll("-l ", "").replaceAll(" -l", "").replaceAll("-L ", "").replaceAll(" -L", "").replaceAll("-l", "").replaceAll("-L", "");
								}
								EntitySearch search = new EntitySearch(p,radius);
								int removed = 0;
								if(filter.length()==0)
									removed = search.RemoveAll(lightning);
								else
									removed = search.Remove(lightning, filter);
								sender.sendMessage(Methods.tag()+"Entities Removed Within "+radius+" Blocks Of "+p.getDisplayName()+"\u00a73: \u00a7b"+removed);
							}
							catch(Exception e)
							{
								sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
								sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
							}
						}
						else
						{
							try
							{
								int radius = Integer.parseInt(args[1]);
								if(radius<=0)
									throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
								String filter = "";
								for(int i=2;i<args.length;i++)
								{
									if(i!=2)
										filter+=" ";
									filter+=args[i];
								}
								boolean lightning = false;
								if(filter.contains("-l")||filter.contains("-L"))
								{
									lightning = true;
									filter = filter.replaceAll("-l ", "").replaceAll(" -l", "").replaceAll("-L ", "").replaceAll(" -L", "").replaceAll("-l", "").replaceAll("-L", "");
								}
								EntitySearch search = new EntitySearch(player,radius);
								int removed = 0;
								if(filter.length()==0)
									removed = search.RemoveAll(lightning);
								else
									removed = search.Remove(lightning, filter);
								sender.sendMessage(Methods.tag()+"Entities Removed Within "+radius+" Blocks: \u00a7b"+removed);
							}
							catch(Exception e)
							{
								sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
								sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
							}
						}
					}
					else if(args.length==2)
					{
						try
						{
							int radius = Integer.parseInt(args[1]);
							if(radius<=0)
								throw new Exception("Radius was less than or equal to 0, a non-zero positive integer is needed!");
							EntitySearch search = new EntitySearch(player,radius);
							int removed = search.RemoveAll(false);
							sender.sendMessage(Methods.tag()+"Entities Removed Within "+radius+" Blocks: \u00a7b"+removed);
						}
						catch(Exception e)
						{
							sender.sendMessage(Methods.tag()+"An error occured, check that your radius is a non-zero positive integer!");
							sender.sendMessage(Methods.tag()+"If this error does not go away, report the bug to Taco :P");
						}
					}
					else
						Methods.commands(sender);
				}
			}
			else
				Methods.commands(sender);
		}
		else
			player.sendMessage(Methods.tag()+"You Don't Appear To Have Permission For This Plugin!");
		return true;
	}
}
class Methods
{
	public static char c = '\u00a7';
	public static String tag()
	{
		return "\u00a77[\u00a76Entity\u00a77] \u00a73";
	}
	public static void info(CommandSender sender)
	{
		sender.sendMessage(tag()+"Plugin: TacosEntityPlugin    Version: 7.77.777.77.7    Author: tbtaco");
		sender.sendMessage(tag()+"Permission: TacosEntityPlugin.Entity    Email: tbtaco@hotmail.com");
	}
	public static void commands(CommandSender sender)
	{
		sender.sendMessage(tag()+"/Entity");
		sender.sendMessage(tag()+"/Entity Count(/C/Number/Num/N/Amount/A/Total/T) Radius");
		sender.sendMessage(tag()+"/Entity Count Player Radius");
		sender.sendMessage(tag()+"/Entity Count All(/A/Everyone/Everybody/E) Radius");
		sender.sendMessage(tag()+"/Entity Remove(/Rem/R/Delete/Del/D/Clear/Purge/P/Kill/K) Radius <Optional Text To Filter Removals, -L To Strike Lightning>");
		sender.sendMessage(tag()+"/Entity Remove Player Radius <O>");
		sender.sendMessage(tag()+"/Entity Remove All Radius <O>");
		sender.sendMessage(tag()+"/Entity Help(/H/Information/Info/I/About/A/Version/Ver/V/?)");
	}
}
class EntitySearch
{
	String playerName;
	int rad;
	public ArrayList<Entities> entities = new ArrayList<Entities>();
	public ArrayList<EntityStack> sortedEntities = new ArrayList<EntityStack>();
	public EntitySearch(Player p, int radius)
	{
		playerName = p.getDisplayName();
		rad = radius;
		List<Entity> list = p.getNearbyEntities(radius, radius, radius);
		for(Entity e : list)
			entities.add(new Entities(e));
		for(Entities e : entities)
		{
			boolean added = false;
			for(int i=0;i<sortedEntities.size();i++)
				if(sortedEntities.get(i).Add(e))
					added = true;
			if(!added)
				sortedEntities.add(new EntityStack(e));
		}
	}
	public int RemoveAll(boolean lightning)
	{
		int removed = 0;
		while(entities.size()>0)
		{
			if(entities.get(0).name.equals("Player"))
				entities.remove(0);
			else
			{
				if(lightning)
					entities.get(0).entity.getLocation().getWorld().strikeLightningEffect(entities.get(0).entity.getLocation());
				entities.remove(0).entity.remove();
			}
			removed++;
		}
		return removed;
	}
	public int Remove(boolean lightning, String param)
	{
		boolean keepChecking = true;
		int removed = 0;
		while(keepChecking)
		{
			keepChecking = false;
			for(int i=0;i<entities.size();i++)
			{
				if(entities.get(i).rawName.toLowerCase().contains(param.toLowerCase()))
				{
					if(entities.get(i).name.equals("Player"))
						entities.remove(i);
					else
					{
						if(lightning)
							entities.get(i).entity.getLocation().getWorld().strikeLightningEffect(entities.get(i).entity.getLocation());
						entities.remove(i).entity.remove();
					}
					keepChecking = true;
					removed++;
					break;
				}
			}
		}
		return removed;
	}
	public void PrintDetails(CommandSender sender)
	{
		PrintAmount(sender);
		for(EntityStack e : sortedEntities)
			sender.sendMessage("\u00a7"+e.color+" -"+e.entities.name+"\u00a7"+e.color+": "+e.count);
	}
	public void PrintAmount(CommandSender sender)
	{
		String color = "b";
		int size = entities.size();
		if(size>=2500)color="4";
		else if(size>=1000)color="c";
		else if(size>=250)color="2";
		else if(size>=100)color="a";
		sender.sendMessage(Methods.tag()+"Entities Within "+rad+" Blocks Of "+playerName+"\u00a73: \u00a7"+color+size);
	}
}
class Entities
{
	public String name;
	public String rawName;
	public Entity entity;
	@SuppressWarnings("deprecation")
	Entities(Entity e)
	{
		entity = e;
		try
		{
			if(e instanceof Item)
			{
				Item item = (Item) e;
				name = item.getItemStack().getItemMeta().getDisplayName();
				if(name==null)
					name = item.getName();
				name+="("+item.getItemStack().getAmount()+")";
			}
			else if(e instanceof LivingEntity)
			{
				LivingEntity le = (LivingEntity) e;
				if(le.getCustomName()!=null)
					name = e.getType().getName()+"\u00a78[\u00a77"+le.getCustomName()+"\u00a78]\u00a77";
				else
					name = e.getType().getName();
			}
			else
				name = e.getType().getName();
			if(e instanceof Monster)
				name+="\u00a7c<\u00a74H\u00a7c>\u00a77";
		}
		catch(Exception ex)
		{
			name = e.toString();
		}
		if(name==null)
			name="Player";
		if(name.length()>=4&&name.substring(0,4).equalsIgnoreCase("null"))
			name="NPC/Other";
		if(name.length()>=11&&name.substring(0,11).equalsIgnoreCase("armor_stand"))
			name="Armor Stand";
		if(name.length()>=5&&name.substring(0, 5).equalsIgnoreCase("Craft"))
			name = name.substring(5, name.length()-1);
		name = name.replaceAll("item.item.","").replaceAll("item.tile.","");
		name = name.replaceAll("_", " ");
		name = WordUtils.capitalize(name);
		rawName = name.replaceAll("&.","").replaceAll("\u00a7.","");
	}
}
class EntityStack
{
	public Entities entities;
	public int count = 1;
	public char color = '0';
	EntityStack(Entities e)
	{
		entities = e;
		switch(e.entity.getType())
		{
		case AREA_EFFECT_CLOUD: 	color = '9'; break;
		case ARMOR_STAND: 			color = '1'; break;
		case ARROW: 				color = 'e'; break;
		case BAT: 					color = '2'; break;
		case BLAZE: 				color = '4'; break;
		case BOAT: 					color = 'b'; break;
		case CAVE_SPIDER: 			color = '4'; break;
		case CHICKEN: 				color = '2'; break;
		case COMPLEX_PART: 			color = 'b'; break;
		case COW: 					color = '2'; break;
		case CREEPER: 				color = '4'; break;
		case DONKEY: 				color = '2'; break;
		case DRAGON_FIREBALL: 		color = 'e'; break;
		case DROPPED_ITEM: 			color = '6'; break;
		case EGG: 					color = 'e'; break;
		case ELDER_GUARDIAN: 		color = '5'; break;
		case ENDERMAN: 				color = '4'; break;
		case ENDERMITE: 			color = '4'; break;
		case ENDER_CRYSTAL: 		color = '3'; break;
		case ENDER_DRAGON: 			color = '5'; break;
		case ENDER_PEARL: 			color = 'e'; break;
		case ENDER_SIGNAL: 			color = 'e'; break;
		case EVOKER: 				color = '5'; break;
		case EVOKER_FANGS: 			color = '5'; break;
		case EXPERIENCE_ORB: 		color = 'a'; break;
		case FALLING_BLOCK: 		color = '3'; break;
		case FIREBALL: 				color = 'e'; break;
		case FIREWORK: 				color = 'e'; break;
		case FISHING_HOOK: 			color = 'e'; break;
		case GHAST: 				color = '4'; break;
		case GIANT: 				color = '5'; break;
		case GUARDIAN: 				color = '5'; break;
		case HORSE: 				color = '2'; break;
		case HUSK: 					color = '5'; break;
		case IRON_GOLEM: 			color = '2'; break;
		case ITEM_FRAME: 			color = 'c'; break;
		case LEASH_HITCH: 			color = 'b'; break;
		case LIGHTNING: 			color = 'c'; break;
		case LINGERING_POTION: 		color = '9'; break;
		case LLAMA: 				color = '2'; break;
		case LLAMA_SPIT: 			color = '2'; break;
		case MAGMA_CUBE: 			color = '4'; break;
		case MINECART: 				color = 'b'; break;
		case MINECART_CHEST: 		color = 'b'; break;
		case MINECART_COMMAND: 		color = 'b'; break;
		case MINECART_FURNACE: 		color = 'b'; break;
		case MINECART_HOPPER: 		color = 'b'; break;
		case MINECART_MOB_SPAWNER: 	color = 'b'; break;
		case MINECART_TNT: 			color = 'b'; break;
		case MULE: 					color = '2'; break;
		case MUSHROOM_COW: 			color = '2'; break;
		case OCELOT: 				color = '2'; break;
		case PAINTING: 				color = 'c'; break;
		case PIG: 					color = '2'; break;
		case PIG_ZOMBIE: 			color = '4'; break;
		case PLAYER: 				color = '5'; break;
		case POLAR_BEAR: 			color = '2'; break;
		case PRIMED_TNT: 			color = 'c'; break;
		case RABBIT: 				color = '2'; break;
		case SHEEP: 				color = '2'; break;
		case SHULKER: 				color = '5'; break;
		case SHULKER_BULLET: 		color = '5'; break;
		case SILVERFISH: 			color = '5'; break;
		case SKELETON: 				color = '4'; break;
		case SKELETON_HORSE: 		color = '4'; break;
		case SLIME: 				color = '4'; break;
		case SMALL_FIREBALL: 		color = 'e'; break;
		case SNOWBALL: 				color = 'e'; break;
		case SNOWMAN: 				color = '2'; break;
		case SPECTRAL_ARROW: 		color = 'e'; break;
		case SPIDER: 				color = '4'; break;
		case SPLASH_POTION: 		color = '3'; break;
		case SQUID: 				color = '5'; break;
		case STRAY: 				color = '5'; break;
		case THROWN_EXP_BOTTLE: 	color = 'e'; break;
		case TIPPED_ARROW: 			color = 'e'; break;
		case UNKNOWN: 				color = '8'; break;
		case VEX: 					color = '5'; break;
		case VILLAGER: 				color = '5'; break;
		case VINDICATOR: 			color = '5'; break;
		case WEATHER: 				color = 'c'; break;
		case WITCH: 				color = '4'; break;
		case WITHER: 				color = '5'; break;
		case WITHER_SKELETON: 		color = '5'; break;
		case WITHER_SKULL: 			color = '5'; break;
		case WOLF: 					color = '2'; break;
		case ZOMBIE: 				color = '4'; break;
		case ZOMBIE_HORSE: 			color = '4'; break;
		case ZOMBIE_VILLAGER: 		color = '5'; break;
		default: 					color = '8'; break;
		}
	}
	public boolean Add(Entities e)
	{
		if(e.name.equals(entities.name))
		{
			count++;
			return true;
		}
		return false;
	}
}