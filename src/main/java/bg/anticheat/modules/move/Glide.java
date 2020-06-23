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


package bg.anticheat.modules.move;

import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;

import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.logging.*;
import bg.anticheat.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.*;

public class Glide {
	public static HashMap<Player, Integer> timer;
	public static HashMap<String, Double> previousMove;

	static {
		Glide.timer = new HashMap<Player, Integer>();
		Glide.previousMove = new HashMap<String, Double>();
	}

	public static void onGlide(final PlayerMoveEvent e) {
		if (DacStringBase.max_player_ping != -1) {
			try {
				if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
					return;
				}
			} catch (Exception e1) {
			}
		}
		if(InventoryBuilder.isFreezed(e.getPlayer()))
			return;
		if (!DacStringBase.glide_protection) {
			return;
		}
		final Player p = e.getPlayer();
		
        if (e.getPlayer().hasPermission("Dakata.Bypass.Glide")) {
            return;
        }
		if (PlayerUtils.isUsingElytra(e.getPlayer())) {
			Glide.timer.remove(p);
		}
		if (PlayerUtils.isOnLadder(p) || PlayerUtils.isInLiquidLoc(p.getLocation())|| PlayerUtils.isInLiquidLoc(p.getEyeLocation()) || p.isInsideVehicle()
				|| p.isSleeping() || p.isDead() || p.isFlying()
				|| (p.getGameMode() == GameMode.CREATIVE && PlayerUtils.isInWeb(e.getPlayer().getLocation()))) {
			Glide.timer.remove(p);
			return;
		}
		if (PlayerUtils.isInLiquidLoc(p.getLocation().subtract(0.0, 0.3, 0.0))) { //Maybe will cause false positive, if do up the value
			return;
		}
		if(PlayerUtils.isInWeb(e.getPlayer().getLocation()) || PlayerUtils.isInWeb(e.getPlayer().getEyeLocation()))
		{
			timer.remove(p);
			return;
		}
		if(PlayerUtils.isOnStair(p) || PlayerUtils.isOnHalfSlab(p) || PlayerUtils.isNextToBlock(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.WEB))
		{
			timer.remove(p);
			return;
		}
		if (!Main.is17ro1() && !Main.is17ro2() && !Main.is17ro3() && !Main.is17ro4()
				&& e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
			Glide.timer.remove(p);
			return;
		}
		if (PlayerUtils.isOnBlock(p, Material.FENCE, 1.5)
				|| p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().name().contains("FENCE")
				|| p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().name().contains("WALL")) {
			Glide.timer.remove(p);
			return;
		}
		if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation(), Material.ANVIL)) {
			Glide.timer.remove(p);
			return;
		}
		if (PlayerUtils.isOnEntity(p, EntityType.BOAT, 5.0)) {
			Glide.timer.remove(p);
			return;
		}
		if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation(), Material.WEB)){
			Glide.timer.remove(p);
			return;
		}
        if(Main.is110ro1() || Main.is111ro1() || Main.is112ro1())
        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION))
        		return;
        if(Main.is110ro1() || Main.is111ro1() || Main.is112ro1())
    		if(e.getPlayer().getItemInHand().getType().equals(Material.CHORUS_FRUIT))
    			return;
		//if(e.getPlayer().isOnGround()) { //It is causing problems if the player or the server lag :(
			//Glide.timer.remove(p);
			//return;
		//}
		if(!e.getPlayer().getLocation().getBlock().isEmpty() ||
				!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()) {
			Glide.timer.remove(p);
			return;
		}
		if ((e.getFrom().getY() < e.getTo().getY())
				|| e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.CARPET ||
				 PlayerUtils.isNextToBlockDifferent(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.AIR)) {
			Glide.timer.remove(p);
			return;
		}
		if (Glide.previousMove.containsKey(e.getPlayer().getName())) {
			if (e.getFrom().getY() - e.getTo().getY() == Glide.previousMove.get(e.getPlayer().getName())) {
				if (Glide.timer.containsKey(p)) {
					Glide.timer.put(p, Glide.timer.get(p) + 2);
				} else {
					Glide.timer.put(p, 2);
				}
			}
			Glide.previousMove.remove(e.getPlayer().getName());
		} else {
			Glide.previousMove.put(e.getPlayer().getName(), e.getFrom().getY() - e.getTo().getY());
		}
		final double ydelta = e.getFrom().getY() - e.getTo().getY();
		if (ydelta < Settings.max_glide_speed) {
			if (Glide.timer.containsKey(p)) {
				if (Glide.timer.get(p) > Settings.max_glide_moves) {
					Glide.timer.remove(p);
					p.teleport(PlayerUtils.getLastOnGroundLocation(p));
					Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.GLIDE, false));
					Hackers.addGlide(e.getPlayer());
					if (Hackers.isReadyForGlideMessage(e.getPlayer())) {
						if (DacStringBase.log_console) {
							try {
								Bukkit.getLogger().log(Level.INFO,
										String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "")
												.replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "")
												.replaceAll("&1", "").replaceAll("\u0412�2", "").replaceAll("&2", "")
												.replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "")
												.replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "")
												.replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", ""))
										+ DacStringBase.hack_msg.replaceAll("<hack>", "Glide")
												.replaceAll("<player>", e.getPlayer().getName())
												.replaceAll("<world>", e.getPlayer().getWorld().getName())
												.replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>",
														Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
							} catch (Exception e3) {
								e3.printStackTrace();
							}
						}
						if (DacStringBase.log_player) {
	                    	if(Main.isUsingPlaceholderAPI())
									try {
										Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
									} catch (Exception e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								else
									try {
										Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							for (final Player pa : Bukkit.getOnlinePlayers()) {
								if (pa.hasPermission("Dakata.Admin")) {
									try {
										pa.sendMessage(
												String.valueOf(DacStringBase.anticheat_tag)
														+ DacStringBase.hack_msg.replaceAll("<hack>", "Glide")
																.replaceAll("<player>", e.getPlayer().getName())
																.replaceAll("<world>",
																		e.getPlayer().getWorld().getName())
														.replaceAll("<tps>", Double.toString(TPS.getTPS()))
														.replaceAll("<ping>",
																Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
									} catch (Exception e4) {
										e4.printStackTrace();
									}
								}
							}
						}
					}
					return;
				}
				Glide.timer.put(p, Glide.timer.get(p) + 1);
			} else {
				Glide.timer.put(p, 0);
			}
		} else {
			Glide.timer.remove(p);
		}
	}
	//public static void as(BlockBreakEvent e)
	//{
	//	if()//Strange bugg - when player destroy block down of him, he is detected for fly - WTF
	//}
}
