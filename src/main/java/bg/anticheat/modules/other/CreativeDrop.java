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
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.Messages;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class CreativeDrop {
	private static ArrayList<String> drop = new ArrayList<String>();
	private static HashMap<String, Short> dropedItems = new HashMap<String, Short>();
	public static void a(final PlayerDropItemEvent e) {
        if (!DacStringBase.creativeDrop_protection) {
            return;
        }
        if (DacStringBase.max_player_ping != -1) {
            try {
                if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
                    return;
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.CreativeDrop")) {
            return;
        }
        
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			if(e.getItemDrop().getItemStack().getAmount() == 64) {
				if(!dropedItems.containsKey(e.getPlayer().getName()))
					dropedItems.put(e.getPlayer().getName(), (short) 1);
				else dropedItems.put(e.getPlayer().getName(), (short) (dropedItems.get(e.getPlayer().getName())+1));
				if(!drop.contains(e.getPlayer().getName())) {
					drop.add(e.getPlayer().getName());
					Main.getThisPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
						
						@Override
						public void run() {
							if(dropedItems.containsKey(e.getPlayer().getName())) {
								if(dropedItems.get(e.getPlayer().getName()) >= 13) {
									PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CREATIVEDROP, false);
	                                Bukkit.getPluginManager().callEvent(ass);
	                                if(!ass.isCancelled()) {
	                                	Logger.addMessageToFileLog(e.getPlayer(), "CreativeDrop");
	                                	e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "CreativeDrop"));
	                                	if(DacStringBase.broadcast_kick)Messages.broadcast(e.getPlayer(), "CreativeDrop");
	                                        if (DacStringBase.log_console) {
	                                            try {
	                                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastLadder").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
	                                            }
	                                            catch (Exception e1) {
	                                                e1.printStackTrace();
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
	                                                        p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastLadder").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
	                                                    }
	                                                    catch (Exception e2) {
	                                                        e2.printStackTrace();
	                                                    }
	                                                }
	                                            }
	                                        }
	                                }
								}
								dropedItems.remove(e.getPlayer().getName());
							}
							
							drop.remove(e.getPlayer().getName());
						}
					}, 20L);
				}
			}
		}
	}
}
