package tbtaco.MagicCarpet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class MagicCarpet extends JavaPlugin
{
	public static MagicCarpet plugin;
	public static File Config;
	public static ArrayList<MagicCarpets> MagicCarpetPatterns = new ArrayList<MagicCarpets>();
	public static ArrayList<Players> ActiveCarpets = new ArrayList<Players>();
	public static int RefreshRate=777;
	public static String PermissionMember;
	public static String PermissionAdmin;
	public static ArrayList<Players> NamesToRemove = new ArrayList<Players>();
	public void onEnable()
	{
		plugin = this;
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mMagicCarpet\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Enabling Plugin...\u001B[0m");
		try
		{
			Config = new File(getDataFolder(),"config.yml");
			if(!Config.exists())
			{
				Config.getParentFile().mkdirs();
				Methods.SaveFile();
				Methods.LoadFile();
				MagicCarpetPatterns.clear();
			}
			Methods.LoadFile();
		}
		catch(Exception e)
		{
			Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mMagicCarpet\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
					+ "Error Loading Config... Disabling Plugin...\u001B[0m");
			onDisable();
			return;
		}
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), plugin);
		this.getCommand("MagicCarpet").setExecutor(new Command());
		new Timer().runTaskTimer(MagicCarpet.plugin,0,RefreshRate);
		Logger.getLogger("Minecraft").info("\u001B[32m[\u001B[0m\u001B[32;1mMagicCarpet\u001B[0m\u001B[32m]\u001B[0m\u001b[35m "
				+"Successfully Enabled\u001B[0m");
	}
	public void onLoad()
	{
		Logger.getLogger("Minecraft").info("\u001B[33m[\u001B[0m\u001B[33;1mMagicCarpet\u001B[0m\u001B[33m]\u001B[0m\u001b[35m "
				+"Loading...\u001B[0m");
	}
	public void onDisable()
	{
		for(Players player : ActiveCarpets)
			player.RemovePlayer();
		ActiveCarpets.removeAll(NamesToRemove);
		try
		{
			Methods.SaveFile();
		}
		catch(Exception e)
		{
			Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mMagicCarpet\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
					+"Config Did Not Save Correctly!\u001B[0m");
		}
		plugin=null;
		Logger.getLogger("Minecraft").info("\u001B[31m[\u001B[0m\u001B[31;1mMagicCarpet\u001B[0m\u001B[31m]\u001B[0m\u001b[35m "
				+"Successfully Disabled\u001B[0m");
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
}
class Command implements CommandExecutor
{
	public String Tag = "\u00a77[\u00a76MagicCarpet\u00a77] \u00a75";
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender,org.bukkit.command.Command cmd, String label, String[] args) 
	{
		if(MagicCarpet.MagicCarpetPatterns.size()==0)
		{
			MagicCarpet.RefreshRate=777;
			try{Methods.SaveFile();Methods.LoadFile();}catch(Exception e){}
		}
		Player player = null;
		try
		{
			player=(Player)sender;
		}
		catch(Exception e)
		{
			sender.sendMessage(Tag+"This Command Is For Players Only!");
			return true;
		}
		
		if(!player.isOp())
			return false;
		
		if(label.equalsIgnoreCase("MagicCarpet"))
		{
			if(args.length==1&&args[0].equalsIgnoreCase("On"))
			{
				MagicCarpet.ActiveCarpets.add(new Players(player,0));
				player.sendMessage(Tag+"Magic Carpet Turned On!");
			}
			else if(args.length==1&&args[0].equalsIgnoreCase("Off"))
			{
				for (Players tempPlayer : MagicCarpet.ActiveCarpets)
				    if (tempPlayer.Player.getUniqueId().equals(player.getUniqueId()))
				    {
				    	MagicCarpet.NamesToRemove.add(tempPlayer);
				    	tempPlayer.RemovePlayer();
				    }
				player.sendMessage(Tag+"Magic Carpet Turned Off!");
			}
			else if(args.length==1&&(args[0].equalsIgnoreCase("AddCarpet")||args[0].equalsIgnoreCase("Add")))
			{
				String[] map = new String[7*7];
				String[] data = new String[7*7];
				for(int i=0;i<7*7;i++)
				{
					int x = (int)player.getLocation().getX()-3+(i/7);
					int y = (int)player.getLocation().getY()-1;
					int z = (int)player.getLocation().getZ()-3+(i%7);
					if(x<0)x--;
					if(z<0)z--;
					map[i]=player.getWorld().getBlockAt(x,y,z).getType().name();
					data[i]=player.getWorld().getBlockAt(x,y,z).getData()+"";
				}
				MagicCarpet.MagicCarpetPatterns.add(new MagicCarpets(map,data));
				player.sendMessage(Tag+"Block Configuration Saved As #"+(MagicCarpet.MagicCarpetPatterns.size()-1));
			}
			else if(args.length==1&&(args[0].equalsIgnoreCase("RemoveCarpet")||args[0].equalsIgnoreCase("Remove")))
				player.sendMessage(Tag+"Need A Number (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
			else if(args.length==1&&(args[0].equalsIgnoreCase("PreviewCarpet")||args[0].equalsIgnoreCase("Preview")))
				player.sendMessage(Tag+"Need A Number (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
			else if(args.length==1&&(args[0].equalsIgnoreCase("Information")||args[0].equalsIgnoreCase("Info")||args[0].equalsIgnoreCase("I")))
			{
				player.sendMessage(Tag+"The plugin MagicCarpet was made by tbtaco");
				player.sendMessage(Tag+"If you have any questions, talk to him :P");
			}
			else if(args.length==2&&args[0].equalsIgnoreCase("On"))
				if(Integer.parseInt(args[1])<MagicCarpet.MagicCarpetPatterns.size())
				{
					MagicCarpet.ActiveCarpets.add(new Players(player,Integer.parseInt(args[1])));
					player.sendMessage(Tag+"Magic Carpet Turned On!");
				}
				else
					player.sendMessage(Tag+"Number Too High (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
			else if(args.length==2&&args[0].equalsIgnoreCase("Off"))
			{
				Player p = Bukkit.getPlayer(args[1]);
				if(p!=null)
				{
					for (Players tempPlayer : MagicCarpet.ActiveCarpets)
					    if (tempPlayer.Player.getUniqueId().equals(p.getUniqueId()))
					    {
					    	MagicCarpet.NamesToRemove.add(tempPlayer);
					    	tempPlayer.RemovePlayer();
					    }
					player.sendMessage(Tag+"Successfully Turned Off Their Magic Carpet");
				}
				else
					player.sendMessage(Tag+"Could Not Find The Specified Player");
			}
			else if(args.length==2&&(args[0].equalsIgnoreCase("RemoveCarpet")||args[0].equalsIgnoreCase("Remove")))
				if(Integer.parseInt(args[1])<MagicCarpet.MagicCarpetPatterns.size())
				{
					MagicCarpet.MagicCarpetPatterns.remove(Integer.parseInt(args[1]));
					player.sendMessage(Tag+"Magic Carpet Removed");
				}
				else
					player.sendMessage(Tag+"Number Too High (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
			else if(args.length==2&&(args[0].equalsIgnoreCase("PreviewCarpet")||args[0].equalsIgnoreCase("Preview")))
				if(Integer.parseInt(args[1])<MagicCarpet.MagicCarpetPatterns.size())
				{
					for(int i=0;i<7*7;i++)
						player.getWorld().getBlockAt((int)player.getLocation().getX()-3+(i/7),(int)player.getLocation().getY()-1,
								(int)player.getLocation().getZ()-3+(i%7)).setTypeIdAndData(MagicCarpet.MagicCarpetPatterns.get(
										Integer.parseInt(args[1])).Map[i/7][i%7].getId(),MagicCarpet.MagicCarpetPatterns.get(
												Integer.parseInt(args[1])).Data[i/7][i%7],true);
					player.sendMessage(Tag+"Blocks Below You Edited");
				}
				else
					player.sendMessage(Tag+"Number Too High (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
			else if(args.length==3&&args[0].equalsIgnoreCase("On"))
			{
				Player p = Bukkit.getPlayer(args[2]);
				if(p!=null)
					if(Integer.parseInt(args[1])<MagicCarpet.MagicCarpetPatterns.size())
					{
						MagicCarpet.ActiveCarpets.add(new Players(p,Integer.parseInt(args[1])));
						player.sendMessage(Tag+"Successfully Turned On Their Magic Carpet");
					}
					else
						player.sendMessage(Tag+"Number Too High (0-"+(MagicCarpet.MagicCarpetPatterns.size()-1)+")");
				else
					player.sendMessage(Tag+"Could Not Find The Specified Player");
			}
			else if(args.length==1&&args[0].equalsIgnoreCase("TacoWETest")&&player.isOp())
			{
				try
				{
				WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
				Selection sel = we.getSelection(player);
				if(sel.getHeight()==1)
				{
					int y = (int) sel.getMaximumPoint().getY();
					int lowX = (int) sel.getMinimumPoint().getX();
					int lowZ = (int) sel.getMinimumPoint().getZ();
					int highX = (int) sel.getMaximumPoint().getX();
					int highZ = (int) sel.getMaximumPoint().getZ();
					World world = sel.getWorld();
					
					for(int x=lowX;x<=highX;x++)
					{
						for(int z=lowZ;z<=highZ;z++)
						{
							Methods.checkBlock(x,y,z,world);
						}
					}
				}
				else
					player.sendMessage("Error: Height must be 1");
				}
				catch(Exception e){}
				
				
				
			}
			else
				player.sendMessage(Tag+"On/Off/Toggle/AddCarpet/Add/RemoveCarpet/Remove/PreviewCarpet/Preview/Information/Info/I");
			return true;
		}
		return false;
	}
}
class Listeners implements Listener
{
	@EventHandler (priority=EventPriority.NORMAL,ignoreCancelled=true)
	public void playerSneakEvent(PlayerToggleSneakEvent e)
	{
		for(Players player : MagicCarpet.ActiveCarpets)
			if(player.Player.getUniqueId().equals(e.getPlayer().getUniqueId()))
				player.isSneaking=e.isSneaking();
	}
}
class Methods
{
	public static void LoadFile() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(MagicCarpet.Config));
		String line;
		MagicCarpet.RefreshRate = Integer.parseInt(br.readLine().trim().split(":")[1].trim());
		MagicCarpet.PermissionMember = br.readLine().trim().split(":")[1].trim();
		MagicCarpet.PermissionAdmin = br.readLine().trim().split(":")[1].trim();
		br.readLine();br.readLine();br.readLine();
		while((line=br.readLine())!=null)
		{
			for(int i=0;i<7-1;i++)
				line+=br.readLine();
			String[] raw = line.replaceAll("\n","").split(",");
			String[] map = new String[7*7];
			String[] data = new String[7*7];
			for(int i=0;i<7*7;i++)
			{
				String[] parts = raw[i].split(":");
				map[i]=parts[0].trim();
				if(parts.length==1)
					data[i]="0";
				else
					data[i]=parts[1].trim();
			}
			MagicCarpet.MagicCarpetPatterns.add(new MagicCarpets(map,data));
			br.readLine();
		}
		br.close();
	}
	public static void SaveFile() throws Exception
	{
		String[] defaultMap = {"Air","Air","Air","Air","Air","Air","Air",
							   "Air","Air","PURPUR_STAIRS","PURPUR_STAIRS","PURPUR_STAIRS","Air","Air",
							   "Air","PURPUR_STAIRS","PURPUR_STAIRS","PURPUR_PILLAR","PURPUR_STAIRS","PURPUR_STAIRS","Air",
							   "Air","PURPUR_STAIRS","PURPUR_PILLAR","PURPUR_PILLAR","PURPUR_PILLAR","PURPUR_STAIRS","Air",
							   "Air","PURPUR_STAIRS","PURPUR_STAIRS","PURPUR_PILLAR","PURPUR_STAIRS","PURPUR_STAIRS","Air",
							   "Air","Air","PURPUR_STAIRS","PURPUR_STAIRS","PURPUR_STAIRS","Air","Air",
							   "Air","Air","Air","Air","Air","Air","Air"};
		String[] defaultData = {"0","0","0","0","0","0","0",
				                "0","0","6","4","7","0","0",
				                "0","4","4","4","7","4","0",
				                "0","6","8","0","8","7","0",
				                "0","5","6","4","5","5","0",
				                "0","0","6","5","7","0","0",
				                "0","0","0","0","0","0","0"};
		BufferedWriter bw = new BufferedWriter(new FileWriter(MagicCarpet.Config));
		if(MagicCarpet.RefreshRate>500)
		{
			MagicCarpet.RefreshRate=3;
			MagicCarpet.PermissionMember="MagicCarpet.Member";
			MagicCarpet.PermissionAdmin="MagicCarpet.Admin";
		}
		bw.write("Refresh Rate (In Ticks, Lower May Lag More Where Higher May Not Be Fast Enough): "+MagicCarpet.RefreshRate+"\n");
		bw.write("Permission For Using The Basic On/Off Commands: "+MagicCarpet.PermissionMember+"\n");
		bw.write("Permission For Using Admin Commands: "+MagicCarpet.PermissionAdmin+"\n");
		bw.write("-----------------------------------------------------------------------------------\n");
		bw.write("Below Is A Block Map For The Magic Carpets (7 x 7) (If It's A Blank Block, Use Air)\n");
		bw.write("-----------------------------------------------------------------------------------\n");
		if(MagicCarpet.MagicCarpetPatterns.size()==0)
			MagicCarpet.MagicCarpetPatterns.add(new MagicCarpets(defaultMap,defaultData));
		for(MagicCarpets carpet : MagicCarpet.MagicCarpetPatterns)
		{
			for(int i=0;i<carpet.Map.length;i++)
			{
				for(int j=0;j<carpet.Map[i].length;j++)
				{
					if(carpet.Map[i][j]==null)
						bw.write("AIR");
					else
						bw.write(carpet.Map[i][j].toString());
					if(carpet.Data[i][j]!=0)
						bw.write(":"+carpet.Data[i][j]);
					if(!(i+1==carpet.Map.length&&j+1==carpet.Map[i].length))
						bw.write(",");
				}
				bw.write("\n");
			}
			bw.write("-----------------------------------------------------------------------------------\n");
		}
		bw.close();
	}
	public static void checkBlock(int x,int y,int z, World w)
	{
		Block b = w.getBlockAt(x, y, z);
		if(b.getType()==Material.AIR)
		{
			int adjacent = 0;
			if(b.getRelative(BlockFace.NORTH).getType()==Material.AIR||b.getRelative(BlockFace.NORTH).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.EAST).getType()==Material.AIR||b.getRelative(BlockFace.EAST).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.SOUTH).getType()==Material.AIR||b.getRelative(BlockFace.SOUTH).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.WEST).getType()==Material.AIR||b.getRelative(BlockFace.WEST).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.NORTH_EAST).getType()==Material.AIR||b.getRelative(BlockFace.NORTH_EAST).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.NORTH_WEST).getType()==Material.AIR||b.getRelative(BlockFace.NORTH_WEST).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.SOUTH_EAST).getType()==Material.AIR||b.getRelative(BlockFace.SOUTH_EAST).getType()==Material.GLASS)
				adjacent++;
			if(b.getRelative(BlockFace.SOUTH_WEST).getType()==Material.AIR||b.getRelative(BlockFace.SOUTH_WEST).getType()==Material.GLASS)
				adjacent++;
			if(adjacent==8)
				b.setType(Material.GLASS);
		}
		return;
	}
}
class MagicCarpets
{
	public Material[][] Map = new Material[7][7];
	public Byte[][] Data = new Byte[7][7];
	public MagicCarpets(String[] rawMap,String[] rawData)
	{
		for(int i=0;i<rawMap.length;i++)
		{
			Map[i/7][i%7]=Material.getMaterial(rawMap[i]);
			Data[i/7][i%7]=Byte.valueOf(rawData[i]);
		}
	}
}
class Players
{
	public boolean isSneaking=false;
	public Material[][] Map = new Material[7][7];
	public Boolean[][] Edited = new Boolean[7][7];
	public Player Player;
	public int CarpetToUse;
	int locX=0;
	int locY=0;
	int locZ=0;
	int prevX,prevY,prevZ;
	public Players(Player player,int carpet)
	{
		Player = player;
		CarpetToUse = carpet;
		RefreshPlayer();
		for(int i=0;i<7*7;i++)
			Edited[i/7][i%7]=false;
	}
	@SuppressWarnings("deprecation")
	public void UpdateBlocks()
	{
		if(locX==prevX&&locY==prevY&&locZ==prevZ)
			return;
		for(int i=0;i<7*7;i++)
			if(Edited[i/7][i%7])
			{
				Player.getWorld().getBlockAt(prevX-3+(i/7), prevY, prevZ-3+(i%7)).setTypeId(0);
				Edited[i/7][i%7]=false;
			}
		for(int i=0;i<7*7;i++)
			if(Player.getWorld().getBlockAt(locX-3+(i/7), locY, locZ-3+(i%7)).getTypeId()==0)
			{
				Player.getWorld().getBlockAt(locX-3+(i/7), locY, locZ-3+(i%7)).setTypeIdAndData(
						MagicCarpet.MagicCarpetPatterns.get(CarpetToUse).Map[i/7][i%7].getId(),
						MagicCarpet.MagicCarpetPatterns.get(CarpetToUse).Data[i/7][i%7],true);
				Edited[i/7][i%7]=true;
			}
	}
	@SuppressWarnings("deprecation")
	public void RemovePlayer()
	{
		for(int i=0;i<7*7;i++)
			if(Edited[i/7][i%7])
				Player.getWorld().getBlockAt(prevX-3+(i/7), prevY, prevZ-3+(i%7)).setTypeId(0);
		for (Players tempPlayer : MagicCarpet.ActiveCarpets)
		    if (tempPlayer.Player.getUniqueId().equals(Player.getUniqueId()))
		        MagicCarpet.NamesToRemove.add(tempPlayer);
	}
	public boolean RefreshPlayer()
	{
		Player p = Bukkit.getPlayer(Player.getUniqueId());
		if(p==null)
			return false;
		Player = p;
		prevX = locX;
		prevY = locY;
		prevZ = locZ;
		locX = (int)Player.getLocation().getX();
		locY = (int)Player.getLocation().getY()-1;
		locZ = (int)Player.getLocation().getZ();
		if(locX<0)locX--;
		if(locZ<0)locZ--;
		if(isSneaking)
			locY--;
		return true;
	}
}
class Timer extends BukkitRunnable
{
	@Override
	public void run()
	{
		MagicCarpet.ActiveCarpets.removeAll(MagicCarpet.NamesToRemove);
		for(Players player : MagicCarpet.ActiveCarpets)
			if(player.RefreshPlayer())
				player.UpdateBlocks();
			else
				player.RemovePlayer();
	}
}