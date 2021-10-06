package tbtaco.TacoSpecialDropsPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.earth2me.essentials.Essentials;

public class TacoSpecialDropsPlugin extends JavaPlugin
{
	public static TacoSpecialDropsPlugin plugin;
	public static Essentials essentials;
	public static ArrayList<Killer> killers = new ArrayList<Killer>();
	public static ArrayList<Entity> spawnerMobs = new ArrayList<Entity>();
	public static int goal = 1000;
	private File log = new File(getDataFolder(),"ItemDropLog.txt");
	public static ArrayList<String> itemDropLog = new ArrayList<String>();
	public void onEnable()
	{
		plugin = this;
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mTacoSpecialDropsPlugin\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Enabling Plugin...\u001B[0m");
		if(!log.exists())
			log.getParentFile().mkdirs();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(log));
			String line;
			while((line=br.readLine())!=null)
				itemDropLog.add(line);
			br.close();
			essentials = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
			plugin.getCommand("TacoSpecialDropsPlugin").setExecutor(new Command());
			plugin.getCommand("TacoDrawing").setExecutor(new Command());
			plugin.getCommand("TD").setExecutor(new Command());
			Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), plugin);
			Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mTacoSpecialDropsPlugin\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
					+"Successfully Enabled\u001B[0m");
		}
		catch(Exception e)
		{
			Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mTacoSpecialDropsPlugin\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
					+"Could Not Enable... Generating Files, Restart The Plugin To Try Again...\u001B[0m");
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(log));
				bw.close();
			}
			catch(Exception ex){}
		}
		new Timer().runTaskTimer(plugin,200,100);
	}
	public void onLoad()
	{
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mTacoSpecialDropsPlugin\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");
	}
	public void onDisable()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(log));
			for(int i=0;i<itemDropLog.size();i++)
				if(itemDropLog.get(i).trim().length()!=0)
					bw.write(itemDropLog.get(i)+"\n");
			bw.close();
		}
		catch(Exception e){}
		plugin=null;
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mTacoSpecialDropsPlugin\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
}
class Command implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender,org.bukkit.command.Command cmd, String label, String[] args) 
	{
		if(sender instanceof Player)
		{
			Killer killer = null;
			for(Killer k : TacoSpecialDropsPlugin.killers)
				if(k.player.equals(((Player)sender).getName()))
					killer = k;
			if(killer == null)
				sender.sendMessage("\u00a73Hint: Hunt mobs...");
			else
			{
				String s = "s";
				if(killer.kills==1)
					s = "";
				sender.sendMessage("\u00a73Hint: You have killed \u00a76"+killer.kills+
						"\u00a73 mob"+s+" and have an overall score of \u00a76"+String.format("%.2f", killer.score));
			}
			if(!((Player)sender).hasPermission("TacoSpecialDropsPlugin.Staff")||args.length==0)
				return true;
		}
		sender.sendMessage("\u00a73Current stats for all players hunting mobs below:");
		for(Killer k : TacoSpecialDropsPlugin.killers)
			sender.sendMessage("\u00a73  "+k.player+"\u00a73: \u00a76"+k.kills+"\u00a73, Score: \u00a76"+String.format("%.2f", k.score)+"\u00a73, Obtained: \u00a76"+k.dropsCollected+"\u00a73, Goal: \u00a76"+(TacoSpecialDropsPlugin.goal*(k.dropsCollected+1)));
		if(TacoSpecialDropsPlugin.killers.size()==0)
			sender.sendMessage("\u00a73  Nobody Yet...");
		return true;
	}
}
class Listeners implements Listener
{
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void creatureSpawnEvent(CreatureSpawnEvent e)
	{
		if(e.getSpawnReason().equals(SpawnReason.SPAWNER)||
		   e.getSpawnReason().equals(SpawnReason.EGG)||
		   e.getSpawnReason().equals(SpawnReason.SPAWNER_EGG))
					e.getEntity().setMetadata("TacoSpecialDropsPluginMeta", 
					new FixedMetadataValue(TacoSpecialDropsPlugin.plugin, 7));
	}
	@SuppressWarnings("deprecation")
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void entityDeathEvent(EntityDeathEvent e)
	{
		try{
		Entity ent = e.getEntity();
		if(ent instanceof Monster || ent instanceof Animals)
		{
			Player player = e.getEntity().getKiller();
			if(player == null)
				return;
			Killer killer = null;
			for(Killer k : TacoSpecialDropsPlugin.killers)
				if(k.player.equals(player.getName()))
					killer = k;
			if(killer == null)
			{
				TacoSpecialDropsPlugin.killers.add(new Killer(player.getName()));
				killer = TacoSpecialDropsPlugin.killers.get(TacoSpecialDropsPlugin.killers.size()-1);
			}
			killer.kills+=1;
			if(player.isFlying())
				killer.score+=-5.17;
			if(player.getGameMode().equals(GameMode.CREATIVE))
				killer.score+=-17.39;
			if(player.isInvulnerable())
				killer.score+=-10;
			if(TacoSpecialDropsPlugin.essentials.getUser(player).isGodModeEnabled())
				killer.score+=-15.32;
			if(ent.getMetadata("TacoSpecialDropsPluginMeta").size()!=0&&
					ent.getMetadata("TacoSpecialDropsPluginMeta").get(0).asInt()==7)
				killer.score+=-0.07;
			else if(killer.lastKill!=null&&killer.lastKill.getType().equals(ent.getType()))
				killer.score+=0.89;
			else
				killer.score+=1.89;
			Material inHand;
			if(player.getItemInHand()==null)
				inHand=Material.AIR;
			else
			{
				inHand=player.getItemInHand().getType();
				if(player.getItemInHand().getEnchantments().size()==0)
					killer.score+=0.54;
				else
					if(player.getItemInHand().getEnchantments().size()>=3)
						killer.score+=-0.87;
				if(player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)>5)
					killer.score+=(-0.236*player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL));
			}
			switch(inHand){
			case DIAMOND_AXE: killer.score+=-1.32; break;
			case DIAMOND_HOE: killer.score+=0.18; break;
			case DIAMOND_PICKAXE: killer.score+=-0.13; break;
			case DIAMOND_SPADE: killer.score+=-0.05; break;
			case DIAMOND_SWORD: killer.score+=-1.87; break;
			case GOLD_AXE: killer.score+=0.19; break;
			case GOLD_HOE: killer.score+=0.81; break;
			case GOLD_PICKAXE: killer.score+=0.45; break;
			case GOLD_SPADE: killer.score+=0.58; break;
			case GOLD_SWORD: killer.score+=0.39; break;
			case IRON_AXE: killer.score+=0.23; break;
			case IRON_HOE: killer.score+=0.52; break;
			case IRON_PICKAXE: killer.score+=0.38; break;
			case IRON_SPADE: killer.score+=0.47; break;
			case IRON_SWORD: killer.score+=0.19; break;
			case STONE_AXE: killer.score+=1.84; break;
			case STONE_HOE: killer.score+=3.23; break;
			case STONE_PICKAXE: killer.score+=1.97; break;
			case STONE_SPADE: killer.score+=2.06; break;
			case STONE_SWORD: killer.score+=1.74; break;
			case WOOD_AXE: killer.score+=2.78; break;
			case WOOD_HOE: killer.score+=5.58; break;
			case WOOD_PICKAXE: killer.score+=2.97; break;
			case WOOD_SPADE: killer.score+=2.30; break;
			case WOOD_SWORD: killer.score+=3.51; break;
			case AIR: killer.score+=-0.13; break;
			case BOW: killer.score+=-0.07; break;
			case COOKED_FISH: killer.score+=1.56; break;
			case DRAGON_EGG: killer.score+=2.57; break;
			case NETHER_STAR: killer.score+=2.37; break;
			case RABBIT_FOOT: killer.score+=2.64; break;
			case RAW_FISH: killer.score+=2.33; break;
			case SHEARS: killer.score+=2.12; break;
			case SHIELD: killer.score+=0.93; break;
			case SPONGE: killer.score+=1.13; break;
			case STICK: killer.score+=2.17; break;
			default: killer.score+=0.43; break;}
			switch(ent.getType()){
			case BAT: killer.score+=1.74; break;
			case BLAZE: killer.score+=-0.15; break;
			case CAVE_SPIDER: killer.score+=0.04; break;
			case CHICKEN: killer.score+=0.22; break;
			case COW: killer.score+=0.21; break;
			case CREEPER: killer.score+=-0.09; break;
			case DONKEY: killer.score+=0.09; break;
			case ELDER_GUARDIAN: killer.score+=1.89; break;
			case ENDERMAN: killer.score+=0.14; break;
			case ENDERMITE: killer.score+=0.76; break;
			case ENDER_DRAGON: killer.score+=19.93; break;
			case EVOKER: killer.score+=1.10; break;
			case GHAST: killer.score+=3.16; break;
			case GIANT: killer.score+=11.11; break;
			case GUARDIAN: killer.score+=0.43; break;
			case HORSE: killer.score+=-0.20; break;
			case HUSK: killer.score+=1.02; break;
			case IRON_GOLEM: killer.score+=1.49; break;
			case LLAMA: killer.score+=0.32; break;
			case MAGMA_CUBE: killer.score+=1.08; break;
			case MULE: killer.score+=0.02; break;
			case MUSHROOM_COW: killer.score+=1.94; break;
			case OCELOT: killer.score+=-0.43; break;
			case PARROT: killer.score+=0.99; break;
			case PIG: killer.score+=0.25; break;
			case PIG_ZOMBIE: killer.score+=-0.65; break;
			case POLAR_BEAR: killer.score+=0.33; break;
			case RABBIT: killer.score+=0.21; break;
			case SHEEP: killer.score+=0.09; break;
			case SHULKER: killer.score+=0.78; break;
			case SILVERFISH: killer.score+=2.12; break;
			case SKELETON: killer.score+=-0.08; break;
			case SKELETON_HORSE: killer.score+=1.66; break;
			case SLIME: killer.score+=0.16; break;
			case SNOWMAN: killer.score+=1.94; break;
			case SPIDER: killer.score+=0.04; break;
			case SQUID: killer.score+=-0.77; break;
			case VEX: killer.score+=0.59; break;
			case VILLAGER: killer.score+=-5.43; break;
			case VINDICATOR: killer.score+=1.43; break;
			case WITCH: killer.score+=0.73; break;
			case WITHER: killer.score+=5.95; break;
			case WITHER_SKELETON: killer.score+=0.92; break;
			case WOLF: killer.score+=-0.21; break;
			case ZOMBIE: killer.score+=+0.06; break;
			case ZOMBIE_HORSE: killer.score+=1.23; break;
			case ZOMBIE_VILLAGER: killer.score+=-0.28; break;
			default: killer.score+=0.00; break;}
			switch(player.getWorld().getName()){
			case "Nations": killer.score+=-0.14; break;
			case "Nations_the_end": killer.score+=0.39; break;
			case "Nations_nether": killer.score+=-0.13; break;
			case "shops": killer.score+=-1.00; break;
			case "IsleOfDreams": killer.score+=1.00; break;
			case "Lobby": killer.score+=1.00; break;
			case "Lobby_the_end": killer.score+=1.00; break;
			case "MobArena": killer.score+=-0.20; break;
			case "": killer.score+=0.00; break;
			default: killer.score+=-5.00; break;}
			for(ItemStack piece : player.getInventory().getArmorContents())
				if(piece!=null)
				{
					if(piece.getEnchantments().size()==0)
						killer.score+=0.27;
					else if(piece.getEnchantments().size()>3)
						killer.score-=0.27;
					if(piece.getDurability()<5)
						killer.score+=5.63;
					else if(piece.getDurability()>100)
						killer.score+=-0.19;
					if(piece.getTypeId()>=298&&piece.getTypeId()<=301)
						killer.score+=0.68;
					else if(piece.getTypeId()>=310&&piece.getTypeId()<=313)
						killer.score+=-0.31;
					else
						killer.score+=0.20;
					if(piece.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)>4)
						killer.score+=(piece.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)*-0.15);
				}
				else
					killer.score+=0.35;
			if(killer.score>=TacoSpecialDropsPlugin.goal*(killer.dropsCollected+1))
			{
				Bukkit.broadcastMessage("\u00a76[\u00a74Broadcast\u00a76] \u00a7aTaco Ticket #"+(TacoSpecialDropsPlugin.itemDropLog.size()+1)+
						" has just dropped for "+killer.player+"\u00a7a!!!");
				killer.dropsCollected+=1;
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				TacoSpecialDropsPlugin.itemDropLog.add(
						(TacoSpecialDropsPlugin.itemDropLog.size()+1)+" "+dateFormat.format(date)+" "+
						player.getName()+" Kills:"+killer.kills+" Score:"+killer.score+" Goal:"+
						TacoSpecialDropsPlugin.goal*(killer.dropsCollected+1)+" Mob:"+ent.getType().toString()+" Item:"+inHand);
				TacoSpecialDropsPlugin.goal*=3;
				TacoSpecialDropsPlugin.goal/=2;
				String suffix = "th";
				int num = TacoSpecialDropsPlugin.itemDropLog.size();
				if(num!=11&&num!=12&&num!=13&&num!=111&&num!=112&&num!=113&&num!=211&&num!=212&&num!=213)//etc
					if(num%10==1)
						suffix = "st";
					else if(num%10==2)
						suffix = "nd";
					else if(num%10==3)
						suffix = "rd";
				ItemStack item = new ItemStack(Material.NETHER_STAR,1);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
				item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 7);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
				item.setDurability((short)0);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("\u00a76Taco Ticket");
				List<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("\u00a73Give This Ticket To \u00a74~\u00a76T\u00a77a\u00a76c\u00a77o");
				lore.add("\u00a73To Have Your Name Entered");
				lore.add("\u00a73In \u00a76Taco's Christmas Drawing!");
				lore.add("\u00a73More Info On The Board At Spawn");
				lore.add("");
				lore.add("\u00a76This Is The "+TacoSpecialDropsPlugin.itemDropLog.size()+suffix+" Ticket To Drop!");
				if(TacoSpecialDropsPlugin.itemDropLog.size()%7==0)
				{
					lore.add("");
					lore.add("\u00a76Congrats!  You Found A Ticket");
					lore.add("\u00a76With A Number Divisible By 7!");
					lore.add("\u00a76This One Counts As 2 Tickets!!");
				}
				meta.setLore(lore);
				item.setItemMeta(meta);
				ent.getWorld().dropItem(ent.getLocation(), item);
			}
			killer.lastKill=ent;
		}
		}catch(Exception ex){
			TacoSpecialDropsPlugin.plugin.getLogger().info("There Was An Error In The TacoSpecialDropsPlugin Listener Class!");
			for(StackTraceElement elem : ex.getStackTrace())
				TacoSpecialDropsPlugin.plugin.getLogger().info(elem.toString());
		}
	}
}
class Killer
{
	public double score = 0.0;
	public int kills = 0;
	public String player = null;
	public int dropsCollected = 0;
	public Entity lastKill = null;
	public Killer(String p)
	{
		player = p;
	}
}
class Timer extends BukkitRunnable
{
	int uptime = 0;
	int x = 19;
	int y = 12;
	int z = 90;
	String world = "Lobby";
	@Override
	public void run()
	{
		if(uptime==0)
		{
			Location loc = new Location(Bukkit.getWorld(world),x,y,z);
			Sign s = (Sign)loc.getBlock().getRelative(BlockFace.SOUTH).getState();
			s.setLine(0, "\u00a73------------");
			s.setLine(1, "\u00a73Hunt More");
			s.setLine(2, "\u00a73Mobs :P");
			s.setLine(3, "\u00a73------------");
			s.update();
		}
		uptime+=100;
		Location loc = new Location(Bukkit.getWorld(world),x,y,z);
		if(loc.getBlock().getType()==Material.LIGHT_BLUE_GLAZED_TERRACOTTA)
		{
			int hours = (int)(uptime/20.0)/(60*60);
			int minutes = ((int)(uptime/20.0)%(60*60))/60;
			double max = 0.00;
			double min = 0.00;
			for(Killer k : TacoSpecialDropsPlugin.killers)
				if(k.score>max)
					max=k.score;
				else if(k.score<min)
					min=k.score;
			String ss = "s";
			if(hours==1)
				ss="";
			Sign s = (Sign)loc.getBlock().getRelative(BlockFace.NORTH).getState();
			s.setLine(0, "\u00a73------------");
			s.setLine(1, "\u00a73Server Uptime:");
			s.setLine(2, "\u00a73"+hours+" Hour"+ss+", "+minutes+" Min.");
			s.setLine(3, "\u00a73------------");
			s.update();
			s = (Sign)loc.getBlock().getRelative(BlockFace.SOUTH).getState();
			for(Killer k : TacoSpecialDropsPlugin.killers)
			{
				try
				{
					String percent = "------";
					int p = (int)(k.score*100.0/(TacoSpecialDropsPlugin.goal*(k.dropsCollected+1)));
					if(p>=5)
					{
						if(p>=100)
							p=99;
						percent = "("+p+"%)";
					}
					String[] lines = {
							"\u00a73------------",
							"\u00a73"+k.player+"'s",
							"\u00a73Goal: "+(TacoSpecialDropsPlugin.goal*(k.dropsCollected+1)),
							"\u00a73---"+percent+"---"};
					if(Bukkit.getPlayer(k.player).isOnline())
						Bukkit.getPlayer(k.player).sendSignChange(s.getLocation(), lines);
				}
				catch(Exception e){}
			}
			s = (Sign)loc.getBlock().getRelative(BlockFace.EAST).getState();
			s.setLine(0, "\u00a73------------");
			s.setLine(1, "\u00a73Highest Score:");
			s.setLine(2, "\u00a73"+String.format("%.2f", max));
			s.setLine(3, "\u00a73------------");
			s.update();
			s = (Sign)loc.getBlock().getRelative(BlockFace.WEST).getState();
			s.setLine(0, "\u00a73------------");
			s.setLine(1, "\u00a73Lowest Score:");
			s.setLine(2, "\u00a73"+String.format("%.2f", min));
			s.setLine(3, "\u00a73------------");
			s.update();
		}
		double drift = 0.05;
		for(Killer k : TacoSpecialDropsPlugin.killers)
			if(k.score>drift)
				k.score+=-drift;
			else if(k.score<drift)
				k.score+=drift;
	}
}