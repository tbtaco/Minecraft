package tbtaco.SpeedyShroom;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedyShroom extends JavaPlugin
{
	public static SpeedyShroom plugin;
	public static ItemStack shroom;
	public final static int SpeedBoostLevel = 3;//Would Give Speed III
	public final static int SpeedBoostDuration = 60;//Seconds
	public final static int SpeedBoostCooldown = 5*60;//Seconds
	public static ArrayList<Timer> activeTimers = new ArrayList<Timer>();
	public void onEnable()
	{
		plugin = this;
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mSpeedyShroom\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Enabling Plugin...\u001B[0m");
		plugin.getCommand("SpeedyShroom").setExecutor(new Command());
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), plugin);
		shroom = new ItemStack(Material.RED_MUSHROOM,1);
		shroom.setDurability((short)0);
		shroom.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 3);
		ItemMeta meta = shroom.getItemMeta();
		meta.setDisplayName("\u00a75Speedy Shroom");
		List<String> lore = new ArrayList<String>();
		lore.add("\u00a77Speediness III");
		lore.add("\u00a77Unbreaking III");
		lore.add("");
		lore.add("\u00a75Your expert digging senses led");
		lore.add("\u00a75you to this mystical mushroom!");
		lore.add("");
		lore.add("\u00a75Right click with this to get a");
		//Change the cooldown text below to match the SpeedBoostCooldown variable, 
		//but keep in mind the speed boost/etc won't work with any shroom that doesn't match this exact text.
		lore.add("\u00a75boost of speed! (5min Cooldown)");
		meta.setLore(lore);
		shroom.setItemMeta(meta);
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mSpeedyShroom\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Successfully Enabled\u001B[0m");
	}
	public void onLoad()
	{
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mSpeedyShroom\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");
	}
	public void onDisable()
	{
		plugin=null;
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mSpeedyShroom\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
}
class Command implements CommandExecutor
{
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender,org.bukkit.command.Command cmd, String label, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("\u00a75This command can only be ran by a Player with OP!");
			return true;
		}
		Player player = (Player)sender;
		if(!player.isOp())
		{
			player.sendMessage("\u00a75You must be OP to run this command!");
			return true;
		}
		if(player.getItemInHand().getAmount()!=0)
		{
			player.sendMessage("\u00a75Your hand needs to be empty to run this command!");
			return true;
		}
		player.setItemInHand(SpeedyShroom.shroom);
		player.updateInventory();
		player.sendMessage("\u00a75You have been given a Speedy Shroom!");
		return true;
	}
}
class Listeners implements Listener
{
	private int spamFilter = 0;
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void prepareItemCraftEvent(PrepareItemCraftEvent e)
	{
		ItemStack[] grid = e.getInventory().getMatrix();
		for(ItemStack item : grid)
			if(item!=null&&item.isSimilar(SpeedyShroom.shroom))
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				((Player)e.getView().getPlayer()).sendMessage("\u00a75You're hungry for some mean mushroom stew... but not that hungry...");
			}
	}
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void prepareAnvilEvent(PrepareAnvilEvent e)
	{
		spamFilter+=1;
		if(spamFilter%3==0)
		{
			spamFilter=0;
			ItemStack[] items = e.getInventory().getContents();
			if(items.length>=1&&items[0]!=null&&items[0].isSimilar(SpeedyShroom.shroom))
				((Player)e.getView().getPlayer()).sendMessage("\u00a75Something tells you editing the Speedy Shroom is a bad idea...");
			else if(items.length>=2&&items[1]!=null&&items[1].isSimilar(SpeedyShroom.shroom))
				((Player)e.getView().getPlayer()).sendMessage("\u00a75Something tells you editing the Speedy Shroom is a bad idea...");
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler (priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void playerInteractEvent(PlayerInteractEvent e)
	{
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK&&
				e.getPlayer().getItemInHand()!=null&&
				e.getPlayer().getItemInHand().isSimilar(SpeedyShroom.shroom)&&
					(e.getClickedBlock().getRelative(e.getBlockFace()).getRelative(BlockFace.DOWN).getType()==Material.DIRT||
					e.getClickedBlock().getRelative(e.getBlockFace()).getRelative(BlockFace.DOWN).getType()==Material.GRASS||
					e.getClickedBlock().getRelative(e.getBlockFace()).getRelative(BlockFace.DOWN).getType()==Material.MYCEL||
					e.getClickedBlock().getType()==Material.DIRT||
					e.getClickedBlock().getType()==Material.GRASS||
					e.getClickedBlock().getType()==Material.MYCEL))
		{
			//e.getPlayer().sendMessage("\u00a75Some clever message saying they can't place the shroom...  Decided to just cancel it.");
			e.setCancelled(true);
		}
		if(e.getPlayer().getItemInHand()!=null&&e.getPlayer().getItemInHand().isSimilar(SpeedyShroom.shroom))
		{
			Timer timer = null;
			for(Timer t : SpeedyShroom.activeTimers)
				if(t.getPlayerName().equalsIgnoreCase(e.getPlayer().getName()))
					timer = t;
			if(timer==null)
			{
				PotionEffect potion = new PotionEffect(PotionEffectType.SPEED, SpeedyShroom.SpeedBoostDuration*20, SpeedyShroom.SpeedBoostLevel-1);
				e.getPlayer().addPotionEffect(potion);
				timer = new Timer(e.getPlayer().getName(), SpeedyShroom.SpeedBoostCooldown);
				timer.runTaskTimer(SpeedyShroom.plugin, 0, 20);
				SpeedyShroom.activeTimers.add(timer);
			}
			else
				e.getPlayer().sendMessage("\u00a75"+timer.getTime());
		}
	}
}
class Timer extends BukkitRunnable
{
	private String playerName;
	private int time;
	public String getPlayerName()
	{
		return playerName;
	}
	public String getTime()
	{
		String cooldown = "Cooldown: ";
		int hours = time/(60*60);
		int min = time%(60*60)/60;
		int sec = time%60;
		String sH = "s";
		String sM = "s";
		String sS = "s";
		if(hours==1)
			sH = "";
		if(min==1)
			sM = "";
		if(sec==1)
			sS = "";
		if(hours!=0)
			cooldown+=hours+" Hour"+sH+", "+min+" Minute"+sM+", "+sec+" Second"+sS;
		else if(min!=0)
			cooldown+=min+" Minute"+sM+", "+sec+" Second"+sS;
		else
			cooldown+=sec+" Second"+sS;
		return cooldown;
	}
	public Timer(String playerName, int time)
	{
		this.playerName=playerName;
		this.time=time;
	}
	@Override
	public void run()
	{
		time--;
		if(time==0)
		{
			SpeedyShroom.activeTimers.remove(this);
			this.cancel();
		}
	}
}