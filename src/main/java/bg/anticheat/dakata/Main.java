/*<DakataAntiCheat>
Copyright C 2020 Author: dani02

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
at your option any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/


package bg.anticheat.dakata;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitTask;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.modules.attack.Criticals;
import bg.anticheat.modules.attack.MultiAura;
import bg.anticheat.modules.attack.NoKnockback;
import bg.anticheat.modules.attack.SelfHurt;
import bg.anticheat.modules.block.FastPlace;
import bg.anticheat.modules.block.NoBreakDelay;
import bg.anticheat.modules.block.Nuker;
import bg.anticheat.modules.block.WaterBuild;
import bg.anticheat.modules.chat.Spam;
import bg.anticheat.modules.misc.AutoSign;
import bg.anticheat.modules.misc.FastEat;
import bg.anticheat.modules.misc.ImposiblleAttack;
import bg.anticheat.modules.misc.InvalidBlock;
import bg.anticheat.modules.misc.NoSwing;
import bg.anticheat.modules.misc.Reach;
import bg.anticheat.modules.move.AntiCactus;
import bg.anticheat.modules.move.BoatFly;
import bg.anticheat.modules.move.ExtraElytra;
import bg.anticheat.modules.move.FastLadder;
import bg.anticheat.modules.move.Fly;
import bg.anticheat.modules.move.Glide;
import bg.anticheat.modules.move.Headless;
import bg.anticheat.modules.move.HighJump;
import bg.anticheat.modules.move.InvalidHead;
import bg.anticheat.modules.move.InvalidMove;
import bg.anticheat.modules.move.MoreMoves;
import bg.anticheat.modules.move.NoFall;
import bg.anticheat.modules.move.SlimeJump4;
import bg.anticheat.modules.move.Sneak;
import bg.anticheat.modules.move.Speed;
import bg.anticheat.modules.move.SprintBackwards;
import bg.anticheat.modules.move.TP;
import bg.anticheat.modules.move.WaterWalk;
import bg.anticheat.modules.move.WaterY;
import bg.anticheat.modules.other.AntiBot;
import bg.anticheat.modules.other.AntiFastBow;
import bg.anticheat.modules.other.AntiWD;
import bg.anticheat.modules.other.AutoClicker;
import bg.anticheat.modules.other.BadPackets;
import bg.anticheat.modules.other.CreativeDrop;
import bg.anticheat.modules.other.Elytra;
import bg.anticheat.modules.other.HealthTag;
import bg.anticheat.modules.other.InvalidInv;
import bg.anticheat.modules.other.InvalidPotion;
import bg.anticheat.modules.other.JumpPads;
import bg.anticheat.modules.other.MaxInteracts;
import bg.anticheat.modules.other.Regen;
import bg.anticheat.modules.other.TabComplete;
import bg.anticheat.modules.other.Throw;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {
	private static Plugin pl;
	private static Listener ls;
	private static boolean a17ro1;
	private static boolean a17ro2;
	private static boolean a17ro3;
	private static boolean a17ro4;
	private static boolean a19ro1;
	private static boolean a19ro2;
	private static boolean a110ro1;
	private static boolean a111ro1;
	private static boolean a112ro1;
	private static boolean a113ro1;
	private static boolean a114ro1;
	private static boolean a115ro1;
	private static boolean aMcMMO;
	private static boolean aSpartan;
	private static boolean aNCP;
	private static boolean aPlaceholderAPI;
	int i = 0;
	public static String usr = "%%__USER__%%";
	protected BukkitTask bt = null;
	public static ProtocolManager protocolManager;
	private static FileConfiguration fc;
	public static ArrayList<String> dontCheckOnSpawn = new ArrayList<String>();
	public static ArrayList<String> dontCheckForFly = new ArrayList<String>();
	public static HashMap<String, Short> toggleFly = new HashMap<String, Short>();

	public static HashMap<String, Integer> banned = new HashMap<String, Integer>();
	public static StringBuilder fileLog = new StringBuilder();

	@EventHandler
	public void onSpawn(PlayerRespawnEvent e) {
		if (!dontCheckOnSpawn.contains(e.getPlayer().getName())) {
			dontCheckOnSpawn.add(e.getPlayer().getName());
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

				@Override
				public void run() {
					dontCheckOnSpawn.remove(e.getPlayer().getName());

				}
			}, 60L);
		}
	}

	@EventHandler
	public void checkRegenx(final EntityRegainHealthEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getEntity().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (this.getConfig().getStringList("disable-in-residence-areas")
					.contains(rm.getByLoc(e.getEntity().getLocation()))) {
				return;
			}
		}
		if (((Player) e.getEntity()).hasPermission("Dakata.Bypass")) {
			return;
		}
		if (e.getEntity().hasMetadata("NPC"))
			return;
		if (dontCheckOnSpawn.contains(e.getEntity().getName()))
			return;
		Regen.checkRegen(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		Main.pl = this;
		Main.ls = this;
		if (!new File("plugins/DakataAntiCheat/config.yml").exists()) {
			this.saveResource("config.yml", false);
		}
		if (!new File("plugins/DakataAntiCheat/settings.yml").exists()) {
			this.saveResource("settings.yml", false);
		}
		if(DacStringBase.log_file_enabled) {
			getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
				
				@Override
				public void run() {
					Logger.save();
				}
			}, 0L, 20L*30L*60L);
		}
		/*
		 * if (!new File("plugins/DakataAntiCheat.jar").exists()) {
		 * this.getServer().getLogger().warning(
		 * "Sorry, but you need to rename your jar name to DakataAntiCheat");
		 * this.getPluginLoader().disablePlugin((Plugin)this); return; }
		 */
		/*
		 * if(getServer().getPluginManager().getPlugin("EggWars") != null){
		 * if(getServer().getPluginManager().getPlugin("EggWars").getDescription
		 * ().equals("The EggWars game!"))
		 * getServer().getPluginManager().registerEvents(new
		 * EggWarsHookProblem(), this); }
		 */
		if (getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
			this.getServer().getLogger().warning("Sorry, but you need ProtocolLib to use DAC");
			this.getPluginLoader().disablePlugin(this);
			return;
		} else {
			protocolManager = ProtocolLibrary.getProtocolManager();
			
			protocolManager.addPacketListener(new com.comphenix.protocol.events.PacketAdapter(this,
					com.comphenix.protocol.events.ListenerPriority.NORMAL,
					com.comphenix.protocol.PacketType.Play.Client.CUSTOM_PAYLOAD) {
				@Override
				public void onPacketReceiving(com.comphenix.protocol.events.PacketEvent event) {
					if (!DacStringBase.world_download_protection)
						return;
					
					AntiWD.onCheck(event);
				}
			});
		}
		Main.fc = YamlConfiguration.loadConfiguration(new File("plugins/DakataAntiCheat/settings.yml"));
		this.saveDefaultConfig();

		// NEW AUTO UPDATER
		if (new Integer(fc.getInt("max-moves")).equals(null) || fc.getInt("max-moves") == 0)
			this.saveResource("settings.yml", true);
		// NEW AUTO UPDATER
		if (this.getServer().getBukkitVersion().contains("1.7")
				&& this.getServer().getBukkitVersion().contains("R0.1")) {
			Main.a17ro1 = true;
		} else if (this.getServer().getBukkitVersion().contains("1.7")
				&& this.getServer().getBukkitVersion().contains("R0.2")) {
			Main.a17ro2 = true;
		} else if (this.getServer().getBukkitVersion().contains("1.7")
				&& this.getServer().getBukkitVersion().contains("R0.3")) {
			Main.a17ro3 = true;
		} else if (this.getServer().getBukkitVersion().contains("1.7")
				&& this.getServer().getBukkitVersion().contains("R0.4")) {
			Main.a17ro4 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_9_R1")) {
			Main.a19ro1 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_9_R2")) {
			Main.a19ro2 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_10")) {
			Main.a110ro1 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_11")) {
			Main.a111ro1 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_12")) {
			Main.a112ro1 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_13")) {
			Main.a113ro1 = true;
		} else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_14")) {
			Main.a114ro1 = true;
		}  else if (Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_15")) {
			Main.a115ro1 = true;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("mcMMO")) {
			Main.aMcMMO = true;
			getServer().getConsoleSender().sendMessage(DacStringBase.anticheat_tag + "Hook to mcMMO");
		}

		if (this.getServer().getPluginManager().isPluginEnabled("Spartan")) {
			Main.aSpartan = true;
			getServer().getLogger().warning("Hook to Spartan, some checks are disabled to remove false positives");
		}

		if (this.getServer().getPluginManager().isPluginEnabled("NoCheatPlus")
				|| this.getServer().getPluginManager().isPluginEnabled("NCP")) {
			Main.aNCP = true;
			getServer().getConsoleSender().sendMessage(DacStringBase.anticheat_tag
					+ "Hook to NoCheatPlus, some checks are disabled to remove false positives");
		}

		if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			Main.aPlaceholderAPI = true;
			getServer().getConsoleSender().sendMessage(DacStringBase.anticheat_tag + "Hook to PlaceholderAPI");
		}

		try {
			if (DacStringBase.badpackets_check)
				BadPackets.d();
		} catch (IllegalAccessError e) {
			getServer().getLogger().severe("Can't start BadPackets check, please reload the server!");
		}
		if (DacStringBase.healthtag_check)
			HealthTag.a();
		if (DacStringBase.tabcomplete_protection)
			TabComplete.a();
		// ESPCheckSchedule.setupRunnable();

		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new InventoryBuilder(), this);
		this.getServer().getPluginManager().registerEvents(new Hackers(), this);

		if (!Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_7")
				&& !Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".").contains("v1_8"))
			this.getServer().getPluginManager().registerEvents(new Elytra(), this);
		
		if(getConfig().getBoolean("bungee-mode")) {
			if(is113ro1() || is114ro1() || is115ro1()) {
				this.getServer().getMessenger().registerOutgoingPluginChannel(getThisPlugin(), "bungeecord:main");
				this.getServer().getMessenger().registerIncomingPluginChannel(getThisPlugin(), "bungeecord:main", this);
			} else {
				this.getServer().getMessenger().registerOutgoingPluginChannel(getThisPlugin(), "BungeeCord");
				this.getServer().getMessenger().registerIncomingPluginChannel(getThisPlugin(), "BungeeCord", this);
			}
		}

		if (this.getServer().getPluginManager().isPluginEnabled("NoCheatPlus")
				|| this.getServer().getPluginManager().isPluginEnabled("NoCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("AntiCheatPlus")
				|| this.getServer().getPluginManager().isPluginEnabled("AntiCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("AAC")
				|| this.getServer().getPluginManager().isPluginEnabled("AdvanchedAntiCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("PhoenixAntiCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("PAC")
				|| this.getServer().getPluginManager().isPluginEnabled("AntiAura")
				|| this.getServer().getPluginManager().isPluginEnabled("AntiCriticalCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("HackDetective")
				|| this.getServer().getPluginManager().isPluginEnabled("Wolf")
				|| this.getServer().getPluginManager().isPluginEnabled("GAC")
				|| this.getServer().getPluginManager().isPluginEnabled("GuardianAntiCheat")
				|| this.getServer().getPluginManager().isPluginEnabled("Spartan")) {
			this.getServer().getConsoleSender().sendMessage(DacStringBase.anticheat_tag
					+ "Another AC detected, this plugin may cause false positives with other AC's");
		}
		if (getConfig().getBoolean("update-check")) {
			getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

				@Override
				public void run() {
					checkUpdate();

				}
			}, 1L, getConfig().getInt("update-check-time") * 60L * 20L);
		}
	}

	@Override
	public void onDisable() {
		Throw.clear();
		Regen.clear();
		Nuker.clear();
		// NoFall.clear();
		NoBreakDelay.clear();
		// Flight.clear();
		Spam.clear();
		// AntiFastEat.clear();
		AntiFastBow.clear();
		InvalidMove.clear();
		AntiCactus.clear();
		WaterWalk.clear();
		HighJump.clear();
		AutoClicker.clear();
		// MultiAura.clear();
		InvalidInv.clear();
		InvalidHead.clear();

		protocolManager.removePacketListeners(this);
	}

	@EventHandler
	public void oncmds(final PlayerCommandPreprocessEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		Spam.bgmandSpam(e);
	}

	@EventHandler
	public void onEntityExplode(final EntityExplodeEvent e) {
		HighJump.d(e);
	}

	@EventHandler
	public void onPlayerAnime(final PlayerAnimationEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		NoSwing.plani(e);
	}

	@EventHandler
	public void onPlayerLogin(final AsyncPlayerPreLoginEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (Bukkit.getPlayer(e.getUniqueId()) == null) {
			return;
		}
		if (Bukkit.getPlayer(e.getUniqueId()).hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(this.getServer().getPlayer(e.getUniqueId()).getWorld().getName())) {
			return;
		}
		// if (is17ro1() || is17ro2() || is17ro3() || is17ro4()) {
		// return;
		// }
		if (dontCheckOnSpawn.contains(getServer().getPlayer(e.getUniqueId()).getName()))
			return;
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(this.getServer().getPlayer(e.getUniqueId()).getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(this.getServer().getPlayer(e.getUniqueId()).getLocation()).getName()
									.toLowerCase())) {
				return;
			}
		}
		if (getServer().getPlayer(e.getUniqueId()).hasMetadata("NPC"))
			return;
		try {
			AntiBot.bot(e);
		} catch (NoClassDefFoundError ess) {

		}
		Headless.oni(e);

		{
			if (!BadPackets.dont.contains(getServer().getPlayer(e.getUniqueId()).getName())) {
				BadPackets.dont.add(getServer().getPlayer(e.getUniqueId()).getName());
				getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						BadPackets.dont.remove(getServer().getPlayer(e.getUniqueId()).getName());

					}
				}, 40L);
			}
		}
	}

	@EventHandler
	public void onPlayerPlaceBlock(final BlockPlaceEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (e.getPlayer().isSleeping())
			e.setCancelled(true);
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		WaterBuild.saas(e);
		// InvalidBlock.build(e);
		FastPlace.place(e);
		AutoSign.as(e);
		// Flight.u(e);
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getWhoClicked().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getWhoClicked().getWorld().getName())) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getWhoClicked().getName()))
			return;
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getWhoClicked().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getWhoClicked().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getWhoClicked().hasMetadata("NPC"))
			return;
		InvalidInv.inv(e);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void hareket(final PlayerMoveEvent e) throws Exception {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (!Main.a17ro1 && Main.a17ro2 && !Main.a17ro3 && !Main.a17ro4
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}

		// e.getPlayer().sendMessage(""+(e.getFrom().clone().toVector().getY()-e.getTo().clone().toVector().getY())+"
		// "+(e.getTo().getY()-e.getFrom().getY())+"
		// "+((e.getFrom().clone().toVector().getY()-e.getTo().clone().toVector().getY())-(e.getTo().getY()-e.getFrom().getY())));
		// e.getPlayer().sendMessage(""+(e.getPlayer().getVelocity().getY()));
		// if(!e.getFrom().clone().subtract(0,0.5,0).getBlock().isEmpty() &&
		// e.getPlayer().getVelocity().getY() == -0.0784000015258789 &&
		// e.getTo().clone().subtract(0,0.1,0).getBlock().isEmpty()) {
		// if((e.getTo().getZ()+e.getTo().getX())-(e.getFrom().getZ()+e.getFrom().getX())
		// >
		// (e.getFrom().getZ()+e.getFrom().getX())-(e.getTo().getZ()+e.getTo().getX()))
		// e.getPlayer().sendMessage("speed:
		// "+((e.getTo().getZ()+e.getTo().getX())-(e.getFrom().getZ()+e.getFrom().getX())));
		// else e.getPlayer().sendMessage("speed:
		// "+((e.getFrom().getZ()+e.getFrom().getX())-(e.getTo().getZ()+e.getTo().getX())));
		// }
		if (!e.getFrom().clone().subtract(0, 0.5, 0).getBlock().isEmpty()
				&& !dontCheckOnSpawn.contains(e.getPlayer().getName())
				&& !dontCheckForFly.contains(e.getPlayer().getName())
				&& e.getPlayer().getEyeLocation().getBlock().getRelative(BlockFace.UP).isEmpty()
				&& !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType()
						.equals(Material.SLIME_BLOCK)
				&& !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN)
						.getType().equals(Material.SLIME_BLOCK)
				&& MoreMoves.dont.contains(e.getPlayer().getName()) && e.getTo().getY() - e.getFrom().getY() != 0.5
				&& e.getTo().getY() - e.getFrom().getY() != 0.25 && e.getTo().getY() - e.getFrom().getY() != 0.1875
				&& e.getTo().getY() - e.getFrom().getY() != 0.6000000238418579
				&& e.getTo().getY() - e.getFrom().getY() != 0.01250004768371582
				&& e.getTo().getY() - e.getFrom().getY() != 0.125
				&& e.getTo().getY() - e.getFrom().getY() != 0.4641594749554431
				&& e.getTo().getY() - e.getFrom().getY() != 0.4375 && e.getTo().getY() - e.getFrom().getY() != 0.0625
				&& e.getTo().getY() - e.getFrom().getY() != 0.40444491418477924
				&& e.getTo().getY() - e.getFrom().getY() != 0.09375 && e.getTo().getY() - e.getFrom().getY() != 0.375
				&& e.getTo().getY() - e.getFrom().getY() != 0.5625 && e.getTo().getY() - e.getFrom().getY() != 0.015625)
			if (e.getPlayer().getVelocity().getY() == -0.0784000015258789
					&& e.getPlayer().getEyeLocation().getBlock().isEmpty()
					&& !PlayerUtils.isNextToBlock(
							e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(),
							Material.VINE)
					&& !PlayerUtils.isNextToBlock(
							e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(),
							Material.LADDER)
					&& !PlayerUtils.isOnLadder(e.getPlayer()) && !PlayerUtils.isInLiquidLoc(e.getPlayer().getLocation())
					&& e.getFrom().getY() < e.getTo().getY() && DacStringBase.invalidVelocity_protection
					&& !e.getTo().clone().subtract(0, 0.5, 0).getBlock().getType().name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType()
							.name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType()
							.name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType()
							.name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType()
							.name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH_EAST)
							.getType().name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH_WEST)
							.getType().name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH_EAST)
							.getType().name().contains("FENCE")
					&& !e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH_WEST)
							.getType().name().contains("FENCE")) {

				// Hacking InvalidVelocity
				/*
				 * if(e.getTo().getY()-e.getFrom().getY() !=
				 * 0.41999998688697815) { PlayerCheatEvent ass = new
				 * PlayerCheatEvent(e.getPlayer(), CheatType.INVALIDVELOCITY,
				 * false); getServer().getPluginManager().callEvent(ass);
				 * if(!ass.isCancelled()) {
				 * e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.
				 * getPlayer())); Hackers.addInvalidVelocity(e.getPlayer()); if
				 * (Hackers.isReadyForInvalidVelocityMessage(e.getPlayer())) {
				 * if (DacStringBase.log_console) { try {
				 * Bukkit.getLogger().log(Level.INFO,
				 * String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a",
				 * "").replaceAll("&b", "").replaceAll("&6",
				 * "").replaceAll("&0", "").replaceAll("&1",
				 * "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2",
				 * "").replaceAll("&4", "").replaceAll("&5",
				 * "").replaceAll("&7", "").replaceAll("&8",
				 * "").replaceAll("&9", "").replaceAll("&c",
				 * "").replaceAll("&d", "").replaceAll("&e",
				 * "").replaceAll("&f", "")) +
				 * DacStringBase.hack_msg.replaceAll("<hack>",
				 * "InvalidVelocity, diff= "
				 * +(e.getTo().getY()-e.getFrom().getY())).replaceAll(
				 * "<player>", e.getPlayer().getName()).replaceAll("<world>",
				 * e.getPlayer().getWorld().getName()).replaceAll("<tps>",
				 * Double.toString(TPS.getTPS())).replaceAll("<ping>",
				 * Integer.toString(Ping.getPlayerPing(e.getPlayer())))); }
				 * catch (Exception e3) { e3.printStackTrace(); } } if
				 * (DacStringBase.log_player) { for (final Player p :
				 * Bukkit.getOnlinePlayers()) { if
				 * (p.hasPermission("Dakata.Admin")) { try {
				 * p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) +
				 * DacStringBase.hack_msg.replaceAll("<hack>",
				 * "InvalidVelocity, diff= "
				 * +(e.getTo().getY()-e.getFrom().getY())).replaceAll(
				 * "<player>", e.getPlayer().getName()).replaceAll("<world>",
				 * e.getPlayer().getWorld().getName()).replaceAll("<tps>",
				 * Double.toString(TPS.getTPS())).replaceAll("<ping>",
				 * Integer.toString(Ping.getPlayerPing(e.getPlayer())))); }
				 * catch (Exception e4) { e4.printStackTrace(); } } } } } } }
				 */
			}

		if (e.getPlayer().hasMetadata("NPC"))
			return;
		NoFall.moveOn(e);
		// NoFall2.onMove(e);
		InvalidHead.sca(e);
		SlimeJump4.onMove(e);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void hareket1(final PlayerMoveEvent e) throws Exception {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (!Main.a17ro1 && !Main.a17ro2 && !Main.a17ro3 && !Main.a17ro4
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (!TP.dont.contains(e.getPlayer().getName())) {
			InvalidMove.checkSpeedHack(e);
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;

		// e.getPlayer().sendMessage(""+e.getPlayer().getRemainingAir());

		FastLadder.fastl(e);
		InvalidInv.inv2(e);
		PlayerUtils.calculateFallDist(e);
		SprintBackwards.onMove(e);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void hareket2(final PlayerMoveEvent e) throws Exception {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (!Main.a17ro1 && !Main.a17ro2 && !Main.a17ro3 && !Main.a17ro4
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		AntiCactus.ac(e);
		BoatFly.bf(e);
		HighJump.jump(e);
		MoreMoves.onMove(e);
		// IllegalMove.s(e);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void hareket3(final PlayerMoveEvent e) throws Exception {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}

		// e.getPlayer().sendMessage(""+PlayerUtils.getAngle(e.getTo(),
		// e.getFrom(), e.getPlayer().getLocation()));
		// e.getPlayer().sendMessage("y:
		// "+e.getPlayer().getVelocity().clone().getY()+" speed:
		// "+(e.getTo().distance(e.getFrom()))+" up:
		// "+(e.getTo().getY()>e.getFrom().getY()));
		// if(e.getTo().getY() > e.getFrom().getY()) //Possibly making good
		// algorithm will give new fly catching method
		// e.getPlayer().sendMessage("vl:
		// "+Math.asin(((e.getTo().getY()-e.getFrom().getY())-e.getPlayer().getVelocity().clone().getY())));
		// else if(e.getTo().getY() == e.getFrom().getY())
		// e.getPlayer().sendMessage("vl1:
		// "+((e.getTo().getY()-e.getFrom().getY())-e.getPlayer().getVelocity().clone().getY()));

		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (!Main.a17ro1 && !Main.a17ro2 && !Main.a17ro3 && !Main.a17ro4
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		Fly.f(e);
		ExtraElytra.ee(e);
		WaterWalk.checkWaterWalk(e);
		Glide.onGlide(e);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void hareket4(final PlayerMoveEvent e) throws Exception {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (!Main.a17ro1 && !Main.a17ro2 && !Main.a17ro3 && !Main.a17ro4
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		Headless.ih(e);
		Speed.onSpeed(e);
		JumpPads.a(e);
		WaterY.onMove(e);
	}

	public static Plugin getThisPlugin() {
		return Main.pl;
	}

	@EventHandler
	public void reachHileTesti(final BlockBreakEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().isSleeping())
			e.setCancelled(true);
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		Reach.blokKirildiginda(e);
		NoBreakDelay.breakDelayDestroy(e);
		InvalidBlock.breakb(e);
		AutoClicker.i(e);
		Nuker.nuk(e);
	}

	@EventHandler
	public void onPlayerSneak(final PlayerToggleSneakEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		Sneak.an(e);
	}

	@EventHandler
	public void onBlockDamage(final BlockDamageEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getBlock().isLiquid()) {
			e.setCancelled(true);
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		NoBreakDelay.breakDelayDamage(e);
		NoSwing.swing(e);
	}

	@EventHandler
	public void onBlockDamage(final EntityDamageEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getEntity().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getEntity().getWorld().getName())) {
			return;
		}
		if (e.getEntity().hasMetadata("NPC"))
			return;

		NoFall.playerHit(e);

		if (e.getCause().equals(DamageCause.STARVATION) || e.getCause().equals(DamageCause.FIRE)
				|| e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.PROJECTILE)) {
			if (!dontCheckForFly.contains(e.getEntity().getName())) {
				dontCheckForFly.add(e.getEntity().getName());
				getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						dontCheckForFly.remove(e.getEntity().getName());
					}
				}, 40L);
			}

		}
	}

	@EventHandler
	public void onSignChange(final SignChangeEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		AutoSign.ass(e);
	}

	@EventHandler
	public void onSignChange(final PlayerToggleFlightEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;

		Speed.setBacks.remove(e.getPlayer());
		Speed.setBack.remove(e.getPlayer());

		SlimeJump4.downVec.remove(e.getPlayer().getName());
		SlimeJump4.slimeTouch.remove(e.getPlayer().getName());

		SprintBackwards.vl.remove(e.getPlayer().getName());
		SprintBackwards.setBack.remove(e.getPlayer().getName());

		toggleFly.put(e.getPlayer().getName(), (short) 50);
	}

	@EventHandler
	public void antiASCII(final AsyncPlayerChatEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		Spam.spam(e);
	}

	@EventHandler
	public void onPlayerLaunchProjectile(final ProjectileLaunchEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getEntity().getWorld().getName())) {
			return;
		}
		Throw.throwItem(e);
	}

	@EventHandler
	public void onPlayerFood(final PlayerItemConsumeEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().isSleeping())
			e.setCancelled(true);
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		FastEat.b(e);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length >= 4) {
				if (args[0].equalsIgnoreCase("freeze")) {
					if (getServer().getPlayer(args[1]) != null) {
						for (char c : args[2].toCharArray()) {
							if (Character.isAlphabetic(c) || args[2].equals(" ") || args[2].equals("")
									|| args[2] == null) {
								sender.sendMessage(DacStringBase.anticheat_tag + "Invalid Argument");
								if (InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {
									InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
								}
								return false;
							}
						}
						InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
						if (InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {
							StringBuilder a = new StringBuilder();
							for (int isn = 3; isn < args.length; isn++) {
								String s = args[isn];
								if (!((isn - 3) == 0))
									a.append(" " + s);
								else
									a.append(s);
							}
							getServer().getPlayer(args[1]).sendMessage(DacStringBase.anticheat_tag + a.toString());
						} else if (!InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {

						}
						// if(args[2].equals(" ") || args[2].equals("")){
						// if(InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))){
						// InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
						// return false;
						// }
						// }
						getServer().getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {

							@SuppressWarnings("deprecation")
							@Override
							public void run() {
								if (getServer().getPlayer(args[1]) != null)
									InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
								else
									InventoryBuilder.unfreezePlayerOF(getServer().getOfflinePlayer(args[1]));

							}
						}, Long.valueOf(args[2]) * 20L);
					} else
						sender.sendMessage(DacStringBase.anticheat_tag + "Can't find the player");
				} else if (args[0].equals("ban")) {
						if (getServer().getPlayer(args[2]) != null) {
							if (getServer().getPlayer(args[2]).isBanned())
								sender.sendMessage(DacStringBase.anticheat_tag + "The player is already banned");
							else {
								int i = getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									@Override
									public void run() {
										getServer().dispatchCommand(getServer().getConsoleSender(),
												"tempban " + args[2] + " " + args[3] + " " + args[4]);
										banned.remove(args[2]);
									}
								}, 20L * 30L);
								if (!banned.containsKey(args[2]))
									banned.put(args[2], i);
								TextComponent t = new TextComponent(DacStringBase.anticheat_tag + "The player "
										+ args[2] + " is detected for " + args[4]);
								TextComponent t1 = new TextComponent("[B] ");
								t1.setColor(ChatColor.RED);
								t1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
										"/tempban " + args[2] + " " + args[3] + " " + args[4]));
								t1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
										new ComponentBuilder("Ban " + args[2]).create()));
								TextComponent t2 = new TextComponent("[C] ");
								t2.setColor(ChatColor.GREEN);
								t2.setClickEvent(
										new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dakata ban cancel " + args[2]));
								t2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
										new ComponentBuilder("Cancel the ban").create()));
								TextComponent t3 = new TextComponent("[TP]");
								t3.setColor(ChatColor.YELLOW);
								t3.setClickEvent(
										new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dakata spy " + args[2]));
								t3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
										new ComponentBuilder("Spy " + args[2]).create()));
								t.addExtra(t1);
								t.addExtra(t2);
								t.addExtra(t3);
								for (Player p : getServer().getOnlinePlayers()) {
									if (p.hasPermission("Dakata.Admin")) {

										p.spigot().sendMessage(t);
									}
								}
							}
						} else
							sender.sendMessage(DacStringBase.anticheat_tag + "Invalid player");

				} else {
					sender.sendMessage(
							String.valueOf(DacStringBase.anticheat_tag) + "§cThis command is not compatible console!");
					return false;
				}
			} else {
				sender.sendMessage(
						String.valueOf(DacStringBase.anticheat_tag) + "§cThis command is not compatible console!");
				return false;
			}
		} else {
			final Player o = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("dac")) {
				if (args.length == 0) {
					if (!o.hasPermission("Dakata.Commands.GUI")) {
						o.sendMessage(DacStringBase.anticheat_tag + DacStringBase.no_permission);
						return false;
					}
					o.openInventory(InventoryBuilder.buildMain());
				}
			} else if (cmd.getName().equalsIgnoreCase("report")) {
				if (args.length > 1) {
					if (this.getServer().getPlayer(args[0]) != null) {
						 if (args[1].equalsIgnoreCase(o.getName())) {
						 o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
						 + "You can't report yourself");
						 return true;
					}
						o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Report sent");
						final StringBuilder s = new StringBuilder();
						for (int in = 1; in < args.length; in++) {
							String ss = args[in];
							if (!((in - 1) == 0))
								s.append(" " + ss);
							else
								s.append(ss);
						}
						for (final Player p : this.getServer().getOnlinePlayers()) {
							if (p.hasPermission("Dakata.Admin")) {
								p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + o.getName() + " reported "
										+ args[0] + " for " + s + "!");
							}
						}
						InventoryBuilder.reportiste.put(args[0], o.getName());
						InventoryBuilder.reportiste1.put(args[0], s.toString());
					} else {
						o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Can't find the player");
					}
					return true;
				} else
					sender.sendMessage(DacStringBase.anticheat_tag + "Invalid Argument! - /report <player> <hack>");
			} else if (cmd.getName().equalsIgnoreCase("dakata")) {
				if (args.length >= 4) {
					if (args[0].equals("abc") && o.getName().equals("dannos1")) {
						sender.sendMessage("sMain: " + PlayerCheatEvent.spigotId + " sCT: " + usr + " sE: " + Elytra.usr);
					} else if (args[0].equalsIgnoreCase("freeze")) {
						if (o.hasPermission("Dakata.Commands.Freeze")) {
							if (getServer().getPlayer(args[1]) != null) {
								for (char c : args[2].toCharArray()) {
									if (Character.isAlphabetic(c) || args[2].equals(" ") || args[2].equals("")
											|| args[2] == null) {
										sender.sendMessage(DacStringBase.anticheat_tag + "Invalid Argument");
										if (InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {
											InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
										}
										return false;
									}
								}
								InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));
								if (InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {
									sender.sendMessage(DacStringBase.anticheat_tag + args[1] + " was frozen for "
											+ args[2] + "s");
									StringBuilder a = new StringBuilder();
									for (String s : args) {
										if (!s.equalsIgnoreCase(args[0]) && !s.equalsIgnoreCase(args[1])
												&& !s.equalsIgnoreCase(args[2])) {
											if (!s.equalsIgnoreCase(args[3]))
												a.append(" " + s);
											else
												a.append(s);
										}
									}
									getServer().getPlayer(args[1])
											.sendMessage(DacStringBase.anticheat_tag + a.toString());
								} else if (!InventoryBuilder.isFreezed(getServer().getPlayer(args[1]))) {
									sender.sendMessage(DacStringBase.anticheat_tag + args[1] + " was unfrozed");
								}
								getServer().getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {

									@Override
									public void run() {
										InventoryBuilder.freezePlayer(getServer().getPlayer(args[1]));

									}
								}, Long.valueOf(args[2]) * 20L);
							} else
								sender.sendMessage(DacStringBase.anticheat_tag + "Can't find the player");
						} else
							o.sendMessage(DacStringBase.anticheat_tag + DacStringBase.no_permission);
					}
				}
				if (args.length == 0) {
					o.sendMessage("§b--------------------------------------------");
					o.sendMessage("§9/Dakata Reload §8- §7Config Reload");
					o.sendMessage("§9/Dakata Info <player> §8- §7Give info about the <player>");
					o.sendMessage("§9/Dakata Clear §8- §7Remove lagg (Warning: DON'T USE OFTEN)");
					o.sendMessage("§9/Dakata Spy <player> §8- §7Hide/Show you from the <player>");
					o.sendMessage("§9/Dac §8- §7Dakata GUI");
					o.sendMessage("§9/Dakata Ping <player> §8- §7Show the ping of a player");
					o.sendMessage("§9/Dakata Version §8- §7Get the current version");
					o.sendMessage(
							"§9/Dakata Ban <player> <time> <reason> §8- §7Dakata ban(console only)");
					o.sendMessage(
							"§9/Dakata Freeze <player> <time> <message> §8- §7Freeze player for <time> (in seconds)");
					o.sendMessage("§9/Dakata StopAllChecks <time> §8- §7Stop all checks for <time> (in seconds)");
					o.sendMessage(
							"§9/Dakata Region <residence/worldguard> <add/remove> <regionName> §8- §7Add, remove residence region - All players in region will not be checked for hacks");
					o.sendMessage("§b--------------------------------------------");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						if (o.hasPermission("Dakata.Commands.Reload")) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Plugin Config Reloading...");

							this.reloadConfig();

							Settings.reloadTags();

							Main.fc = YamlConfiguration
									.loadConfiguration(new File("plugins/DakataAntiCheat/settings.yml"));
							DacStringBase.reloadTags();

							if (getServer().getPluginManager().isPluginEnabled("Spartan"))
								aSpartan = true;
							else
								aSpartan = false;

							if (getServer().getPluginManager().isPluginEnabled("NoCheatPlus")
									|| this.getServer().getPluginManager().isPluginEnabled("NCP"))
								aNCP = true;
							else
								aNCP = false;

							if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
								aPlaceholderAPI = true;
							else
								aPlaceholderAPI = false;

							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Plugin Config Reloaded!");
						}
					} else if (args[0].equalsIgnoreCase("clear")) {
						if (!o.hasPermission("Dakata.Commands.Clear")) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.no_permission);
							return false;
						}
						Throw.clear();
						Regen.clear();
						Nuker.clear();
						// NoFall.clear();
						NoBreakDelay.clear();
						// Flight.clear();
						Spam.clear();
						// AntiFastEat.clear();
						AntiFastBow.clear();
						InvalidMove.clear();
						AntiCactus.clear();
						if (DacStringBase.antibot_protection && !is17ro1() && !is17ro2() && !is17ro3() && !is17ro4()) {
							AntiBot.clear();
						}
						WaterWalk.clear();
						HighJump.clear();
						AutoClicker.clear();
						// MultiAura.clear();
						InvalidInv.clear();
						InvalidHead.clear();
						System.gc();
						o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "All cleared");
					} else if (args[0].equalsIgnoreCase("version")) {
						if (o.hasPermission("Dakata.Commands.Version")) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Version: "
									+ getThisPlugin().getDescription().getVersion());
						} else {
							sender.sendMessage(
									String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.no_permission);
						}
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("info")) {
						if (o.hasPermission("Dakata.Commands.Info") && DacStringBase.antibot_protection) {
							if (this.getServer().getPlayer(args[1]) == null) {
								return false;
							}
							try {
								AntiBot.info((Player) sender, this.getServer().getPlayer(args[1]));
							} catch (NoClassDefFoundError e) {
								sender.sendMessage(
										DacStringBase.anticheat_tag + "ERROR: The server dont support gSon library");
							}
						}
					} else if (args[0].equalsIgnoreCase("spy")) {
						if (!o.hasPermission("Dakata.Commands.Spy")) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.no_permission);
							return false;
						}
						if (this.getServer().getPlayer(args[1]) == null) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Can't find the player");
							return false;
						}
						if (args[1].equals(sender.getName())) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "You can't spy yourself");
							return false;
						}
						if (o.canSee(this.getServer().getPlayer(args[1]))) {
							this.getServer().getPlayer(args[1]).hidePlayer(o);
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + args[1]
									+ " you have been hided and teleported to him");
							o.teleport(getServer().getPlayer(args[1]));
						} else {
							this.getServer().getPlayer(args[1]).showPlayer(o);
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + args[1]
									+ " you have been showed for him");
						}
					} else if (args[0].equalsIgnoreCase("stopAllChecks")) {
						if (!o.hasPermission("Dakata.Commands.StopAllChecks")) {
							return false;
						}
						if (args[1].contains("[^0-9.]")) {
							sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "This is not a number");
							return false;
						}
						HandlerList.unregisterAll();
						sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "All checks are stopped for "
								+ args[1] + " seconds");
						this.getServer().getScheduler().runTaskLater(this, new Runnable() {
							@Override
							public void run() {
								Main.this.getServer().getPluginManager().registerEvents(Main.ls, Main.pl);
								sender.sendMessage(
										String.valueOf(DacStringBase.anticheat_tag) + "All checks are started");
							}
						}, Short.valueOf(args[1]) * 20);
					} else if (args[0].equalsIgnoreCase("ping")) {
						if (!o.hasPermission("Dakata.Commands.Ping")) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.no_permission);
							return false;
						}
						if (this.getServer().getPlayer(args[1]) == null) {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Player not found");
							return false;
						}
						try {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Ping of the player " + args[1]
									+ " is " + Ping.getPlayerPing(this.getServer().getPlayer(args[1])));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Invalid sub command");
					}
				} else if (args.length == 4) {
					if (args[0].equalsIgnoreCase("region")) {
						if (o.hasPermission("Dakata.Commands.Region")) {
							if (args[1].equalsIgnoreCase("residence")) {
								if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
									if (args[2].equalsIgnoreCase("add")) {
										final ResidenceManager rm = Residence.getInstance().getResidenceManager();
										for (String s : rm.getResidenceList()) {
											if (s.equalsIgnoreCase(args[3]) && !this.getConfig().contains(args[3])) {
												final List<String> wanted = getConfig()
														.getStringList("disable-in-residence-areas");
												wanted.add(args[3].toLowerCase());
												this.getConfig().set("disable-in-residence-areas", wanted);
												getThisPlugin().saveConfig();
												o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
														+ "Added bypass for this area");
												return true;
											}
										}
										o.sendMessage(DacStringBase.anticheat_tag + "Can't find the region");
									} else if (args[2].equalsIgnoreCase("remove")) {
										if (this.getConfig().contains(args[3])) {
											final List<String> wanted2 = this.getConfig()
													.getStringList("disable-in-residence-areas");
											wanted2.remove(args[3].toLowerCase());
											this.getConfig().set("disable-in-residence-areas", wanted2);
											getThisPlugin().saveConfig();
											o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
													+ "Removed bypass for this area");
										} else {
											o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
													+ "This residence is not found in bypassed residences");
										}
									} else {
										o.sendMessage(
												String.valueOf(DacStringBase.anticheat_tag) + "Invalid sub command");
									}
								} else {
									o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
											+ "You need Residence to use this function");
								}
							} else if (args[1].equalsIgnoreCase("worldguard")) {
								if (this.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
									if (getServer().getPluginManager()
											.getPlugin("WorldGuard") instanceof WorldGuardPlugin) {
										if (args[2].equalsIgnoreCase("add")) {
											for (String str : ((WorldGuardPlugin) Main.getThisPlugin().getServer()
													.getPluginManager().getPlugin("WorldGuard"))
															.getRegionManager(((Player) sender).getWorld()).getRegions()
															.keySet()) {
												if (str.equalsIgnoreCase(args[3])) {
													final List<String> wanted = getConfig()
															.getStringList("disable-in-worldguard-areas");
													wanted.add(args[3].toLowerCase());
													this.getConfig().set("disable-in-worldguard-areas", wanted);
													getThisPlugin().saveConfig();
													o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
															+ "Added bypass for this area");
													return true;
												}
											}
											o.sendMessage(DacStringBase.anticheat_tag + "Can't find the region");
										}
										if (args[2].equalsIgnoreCase("remove")) {
											final List<String> wanted = getConfig()
													.getStringList("disable-in-worldguard-areas");
											wanted.remove(args[3].toLowerCase());
											this.getConfig().set("disable-in-worldguard-areas", wanted);
											getThisPlugin().saveConfig();
											o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)
													+ "Removed bypass for this area");
										}
									} else
										sender.sendMessage(DacStringBase.anticheat_tag
												+ "You need WorldGuard to use this function");

								} else
									sender.sendMessage(
											DacStringBase.anticheat_tag + "You need WorldGuard to use this function");
							} else
								sender.sendMessage(DacStringBase.anticheat_tag + "Invalid sub command");
						} else {
							o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.no_permission);
						}
					} else {
						o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Invalid sub command");
					}
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("ban")) {
						if (args[1].equalsIgnoreCase("cancel")) {
							if (sender.hasPermission("Dakata.Admin") || sender.hasPermission("Dakata.Commands.Ban")) {
								if (getServer().getPlayer(args[2]) != null) {
									if(banned.get(args[2]) != null)
										getServer().getScheduler().cancelTask(banned.get(args[2]));
									else o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)+"The player is not in ban wave");
									banned.remove(args[2]);
									o.sendMessage(String.valueOf(DacStringBase.anticheat_tag)+"You removed "+args[2]+" from the ban wave");
								}
							}
						}
					}
				} else {
					o.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Invalid sub command");
				}
			}
		}
		return true;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void dokunus(final PlayerInteractEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().isSleeping()) {
			e.setCancelled(true);
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;

		InvalidPotion.invapo(e);
		InvalidBlock.invt(e);
		AutoClicker.ac(e);
		MaxInteracts.d(e);
		FastEat.a(e);
	}

	@EventHandler
	public void okAtildiginda(final EntityShootBowEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getEntity().getWorld().getName())) {
			return;
		}
		if (((Player) e.getEntity()).hasPermission("Dakata.Bypass")) {
			return;
		}
		if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getEntity().getLocation()) != null
					&& this.getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getEntity().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getEntity().hasMetadata("NPC"))
			return;
		AntiFastBow.antibow(e);
	}

	@EventHandler
	private static void inaijn(final PlayerTeleportEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
				.contains(e.getPlayer().getWorld().getName())) {
			return;
		}
		if (e.getPlayer().hasPermission("Dakata.Bypass")) {
			return;
		}
		if (getThisPlugin().getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getPlayer().getLocation()) != null
					&& getThisPlugin().getConfig().getStringList("disable-in-residence-areas")
							.contains(rm.getByLoc(e.getPlayer().getLocation()).getName().toLowerCase())) {
				return;
			}
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		// NoFall false positive
		NoFall.maxUpLoc.remove(e.getPlayer().getName());

		// Possibly SlimeJump false positive
		SlimeJump4.downVec.remove(e.getPlayer().getName());
		SlimeJump4.slimeTouch.remove(e.getPlayer().getName());

		// Possibly MoreMoves false positive
		MoreMoves.dont.add(e.getPlayer().getName());
		Bukkit.getScheduler().scheduleSyncDelayedTask(getThisPlugin(), new Runnable() {

			@Override
			public void run() {
				MoreMoves.dont.remove(e.getPlayer().getName());
			}
		}, 40L);

		TP.antitp(e);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void entitySaldiri(final EntityDamageByEntityEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (getThisPlugin().getConfig().getStringList("disable-in-worlds")
					.contains(e.getDamager().getWorld().getName())) {
				return;
			}
			if (this.getServer().getPluginManager().isPluginEnabled("Residence")) {
				final ResidenceManager rm = Residence.getInstance().getResidenceManager();
				if (rm.getByLoc(e.getDamager().getLocation()) != null
						&& this.getConfig().getStringList("disable-in-residence-areas")
								.contains(rm.getByLoc(e.getDamager().getLocation()).getName().toLowerCase())) {
					return;
				}
				if (rm.getByLoc(e.getDamager().getLocation()) != null)
					if (rm.getByLoc(e.getDamager().getLocation()).getPermissions().playerHas(e.getDamager().getName(),
							"pvp", false))
						return;
			}
			if (((Player) e.getDamager()).isSleeping()) {
				e.setCancelled(true);
			}
		}
		if (e.getEntity().hasMetadata("NPC"))
			return;
		try {
			Reach.reachTesti(e);
		} catch (Exception exc) {
			getLogger().severe("check: Reach, error: " + exc.getMessage());
		}
		try {
			//InvalidEntity.a(e);
		} catch (Exception exc) {
			getLogger().severe("check: InvalidEntity, error: " + exc.getMessage());
		}
		try {
			SelfHurt.self(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: SelfHurt, error: " + exc.getMessage());
		}
		try {
			InvalidMove.o(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: InvalidMove, error: " + exc.getMessage());
		}
		try {
			ImposiblleAttack.a(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: ImposiblleAttack, error: " + exc.getMessage());
		}
		try {
			MultiAura.ma(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: MultiAura, error: " + exc.getMessage());
		}
		try {
			InvalidInv.inv3(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: InvalidInv, error: " + exc.getMessage());
		}
		try {
			NoKnockback.c(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: NoKnockback, error: " + exc.getMessage());
		}
		try {
			Speed.onEntytyDmg(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: Speed, error: " + exc.getMessage());
		}
		try {
			Criticals.c(e);
		} catch (Exception exc) {
			getLogger().severe("check: Criticals, error: " + exc.getMessage());
		}
		try {// no
			NoSwing.swing2(e);
		} // no
		catch (Exception exc) {
			getLogger().severe("check: NoSwing, error: " + exc.getMessage());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void blockDmg(final EntityDamageByBlockEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getEntity().hasMetadata("NPC"))
			return;
		AntiCactus.ach(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void dropItem(final PlayerDropItemEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		if (!e.getPlayer().isOnline())
			e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void blockDmg(final InventoryCloseEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		ImposiblleAttack.a(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void blockDmg(final InventoryOpenEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;
		ImposiblleAttack.a(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void dropItems(final PlayerDropItemEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (e.getPlayer().hasMetadata("NPC"))
			return;

		CreativeDrop.a(e);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void dropItems(final PlayerDeathEvent e) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		Speed.setBack.remove(e.getEntity());
		Speed.setBacks.remove(e.getEntity());
		Glide.timer.remove(e.getEntity());
		Fly.flyVL.remove(e.getEntity().getName());
		PlayerUtils.backLoc.remove(e.getEntity().getName());
	}

	public static boolean is19ro1() {
		return Main.a19ro1;
	}

	public static boolean is19ro2() {
		return Main.a19ro2;
	}

	public static boolean is110ro1() {
		return Main.a110ro1;
	}

	public static boolean is111ro1() {
		return Main.a111ro1;
	}
	
	public static boolean is112ro1() {
		return Main.a112ro1;
	}
	
	public static boolean is113ro1() {
		return Main.a113ro1;
	}
	
	public static boolean is114ro1() {
		return Main.a114ro1;
	}
	
	public static boolean is115ro1() {
		return Main.a115ro1;
	}

	public static boolean is17ro1() {
		return Main.a17ro1;
	}

	public static boolean is17ro2() {
		return Main.a17ro2;
	}

	public static boolean is17ro3() {
		return Main.a17ro3;
	}

	public static boolean is17ro4() {
		return Main.a17ro4;
	}

	public static boolean isUsingMcMMO() {
		return Main.aMcMMO;
	}

	public static boolean isUsingSpartan() {
		return Main.aSpartan;
	}

	public static boolean isUsingNoCheatPlus() {
		return Main.aNCP;
	}

	public static boolean isUsingPlaceholderAPI() {
		return Main.aPlaceholderAPI;
	}

	public static FileConfiguration getSettings() {
		return Main.fc;
	}

	public void checkUpdate() {
		try {
			HttpURLConnection c = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=26911")
					.openConnection();
			c.setDoOutput(true);
			c.setRequestMethod("POST");
			//c.getOutputStream()
			//		.write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=26911")
			//				.getBytes("UTF-8"));
			String oldVersion = this.getDescription().getVersion();
			String newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
			if (!newVersion.equals(oldVersion)) {
				getLogger().info("WARNING: There is a new update for DAC");
				getLogger().info("WARNING: Current version " + oldVersion);
				getLogger().info("WARNING: New version " + newVersion);
				getLogger().info("Thank you for using DAC");
			}
		} catch (Exception e) {
			getLogger().warning(DacStringBase.anticheat_tag + " Exception while trying to hook at spigot!");
		}
	}

	@Override
	public void onPluginMessageReceived(final String arg0, final Player arg1, final byte[] arg2) {
		if (!Main.pl.getDescription().getAuthors().contains("dani02")
				|| !Main.pl.getDescription().getAuthors().contains("kkkeeeddd")) {
			return;
		}
		if (!Main.pl.getDescription().getName().equals("DakataAntiCheat")) {
			return;
		}
		if (arg1.hasPermission("Dakata.Bypass")) {
			return;
		}
		if(StringUtils.containsIgnoreCase(arg0, "BungeeCord")) {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(arg2));
            try {
				if(in.readUTF().equals("DACMESSAGESTAFF")) {
				    short len = in.readShort();
				    byte[] data = new byte[len];
				    in.readFully(data);
				    
				    String s = new String(data);
				    for(Player p : Bukkit.getOnlinePlayers()) {
				    	if(p.hasPermission("Dakata.Admin"))
				    	    p.sendMessage(s);
				    }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void sendMessagesToAllServers(String msg) {
		if(getThisPlugin().getConfig().getBoolean("bungee-mode")) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Forward");
			out.writeUTF("ONLINE");
			out.writeUTF("DACMESSAGESTAFF");
			byte[] data = msg.getBytes();
			out.writeShort(data.length);
			out.write(data);
			Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
			p.sendPluginMessage(Main.getThisPlugin(), "BungeeCord", out.toByteArray());
		}
	}
}
