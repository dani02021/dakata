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


package bg.anticheat.modules.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class MaxInteracts {
	private static HashMap<String, Short> vlLeft = new HashMap<String, Short>();
	private static HashMap<String, Short> vlRight = new HashMap<String, Short>();
	private static ArrayList<String> player = new ArrayList<String>();
	private static ArrayList<String> player1 = new ArrayList<String>();
	public static void d(final PlayerInteractEvent e)
	{
		if(!DacStringBase.maxinteracts_protection)
			return;
		if(e.getPlayer().hasPermission("Dakata.Bypass.MaxInteracts"))
			return;
		if(e.getPlayer().hasPermission("crackshot.use.all"))
			if(e.getPlayer().getItemInHand().getType() != Material.AIR)
				return;
		if(e.getAction().equals(Action.LEFT_CLICK_AIR)){
		if(vlLeft.containsKey(e.getPlayer().getName()))
			vlLeft.put(e.getPlayer().getName(), (short) (vlLeft.get(e.getPlayer().getName())+1));
		else vlLeft.put(e.getPlayer().getName(), (short) 1);
		if(!player.contains(e.getPlayer().getName())){
			player.add(e.getPlayer().getName());
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
			
			@Override
			public void run() {
				if(vlLeft.get(e.getPlayer().getName()) > Settings.max_interacts_per_second){
					Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(),CheatType.MAX_INTERACTS_LEFT, false));
					Hackers.addMaxInteracts(e.getPlayer());
					if(Hackers.isReadyForMaxInteractsMessage(e.getPlayer())) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "MaxInteracts").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e3) {
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
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("Dakata.Admin")) {
                                    try {
                                        p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "MaxInteracts").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                    }
                                    catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                        }
					}
				}
				player.remove(e.getPlayer().getName());
				vlLeft.remove(e.getPlayer().getName());
			}
		}, 20L);}
		} else{
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
				if(vlRight.containsKey(e.getPlayer().getName()))
					vlRight.put(e.getPlayer().getName(), (short) (vlRight.get(e.getPlayer().getName())+1));
				else vlRight.put(e.getPlayer().getName(), (short) 1);
				if(!player1.contains(e.getPlayer().getName())){
					player1.add(e.getPlayer().getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
					
					@Override
					public void run() {
						if(vlRight.get(e.getPlayer().getName()) > Settings.max_interacts_per_second){
							Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(),CheatType.MAX_INTERACTS_RIGHT, false));
							Hackers.addMaxInteracts(e.getPlayer());
							if(Hackers.isReadyForMaxInteractsMessage(e.getPlayer())) {
		                        if (DacStringBase.log_console) {
		                            try {
		                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "MaxInteracts").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
		                            }
		                            catch (Exception e3) {
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
		                            for (final Player p : Bukkit.getOnlinePlayers()) {
		                                if (p.hasPermission("Dakata.Admin")) {
		                                    try {
		                                        p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "MaxInteracts").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
		                                    }
		                                    catch (Exception e4) {
		                                        e4.printStackTrace();
		                                    }
		                                }
		                            }
		                        }
							}
						}
						player1.remove(e.getPlayer().getName());
						vlRight.remove(e.getPlayer().getName());
					}
				}, 20L);}}
		}
	}
}
