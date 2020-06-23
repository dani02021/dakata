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


package bg.anticheat.modules.misc;

import org.bukkit.event.block.*;
import org.bukkit.*;
import java.util.logging.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.*;
import bg.anticheat.utils.Logger;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.entity.*;
import java.util.*;

public class AutoSign
{
    private static HashMap<String, Long> as;
    
    static {
        AutoSign.as = new HashMap<String, Long>();
    }
    
    public static void as(final BlockPlaceEvent e) {
        if (!DacStringBase.autosign_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.AutoSign")) {
            return;
        }
        if (e.getBlock().getType().name().contains("SIGN")) {
            AutoSign.as.put(e.getPlayer().getName(), System.currentTimeMillis());
        }
    }
    
    public static void ass(final SignChangeEvent e) {
        if (!DacStringBase.autosign_protection) {
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
        if(!as.containsKey(e.getPlayer().getName()))
        	return;
        if (System.currentTimeMillis() - AutoSign.as.get(e.getPlayer().getName()) < Settings.min_sign_change_time && e.getLine(0).length() >= 2) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.AUTOSIGN, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
        	e.getBlock().breakNaturally();
            Hackers.addAutoSign(e.getPlayer());
            if (Hackers.isReadyForAutoSignMessage(e.getPlayer())) {
            	 Logger.addMessageToFileLog(e.getPlayer(), "AutoSign");
                if (DacStringBase.log_console) {
                    try {
                    	if(Main.isUsingPlaceholderAPI())
                            Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoSign").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                    	else  Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoSign").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                            	if(Main.isUsingPlaceholderAPI())
                                    p.sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoSign").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                            	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoSign").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }}
                }
            }
            AutoSign.as.remove(e.getPlayer().getName());
        }
    }
}
