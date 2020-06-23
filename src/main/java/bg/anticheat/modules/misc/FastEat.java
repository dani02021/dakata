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

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.MaterialUtils;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class FastEat {
	private static HashMap<String, Long> startTime = new HashMap<String, Long>();
	public static void a(PlayerInteractEvent e) {
    	if (e.getPlayer().hasPermission("Dakata.Bypass.FastEat")) {
            return;
        }
    	
    	if(e.getItem() == null)
    		return;
    	
        if (!DacStringBase.fasteat_protection) {
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
        
		if(MaterialUtils.isFood(e.getMaterial())) {
			startTime.put(e.getPlayer().getName(), System.currentTimeMillis());
		}
	}
	
	public static void b(PlayerItemConsumeEvent e) {
    	if (e.getPlayer().hasPermission("Dakata.Bypass.FastEat")) {
            return;
        }
    	
    	if(e.getItem() == null)
    		return;
    	
        if (!DacStringBase.fasteat_protection) {
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
        
		if(startTime.containsKey(e.getPlayer().getName())) {
			if((System.currentTimeMillis()-startTime.get(e.getPlayer().getName())) < 1400) {
				if((System.currentTimeMillis()-startTime.get(e.getPlayer().getName())) < 1100) {
					//Definitely is hack
					PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.FASTEAT, true);
	                Bukkit.getPluginManager().callEvent(ass);
	                if(!ass.isCancelled()) {
	                	 Logger.addMessageToFileLog(e.getPlayer(), "FastEat");
	    				e.setCancelled(true);
	    	                Hackers.addFastEat(e.getPlayer());
	    	                if (Hackers.isReadyForFastEatMessage(e.getPlayer())) {
	    	                    if (DacStringBase.log_console) {
	    	                        try {
	    	                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replace("\u0420\u2019\u0412�", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastEat").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
	    	                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastEat").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
	    	                                }
	    	                                catch (Exception e4) {
	    	                                    e4.printStackTrace();
	    	                                }
	    	                            }
	    	                        }
	    	                    }
	    	                }
	                }
	                return;
				}
				//Check if move packets are more than usual
			}
			//e.getPlayer().sendMessage(""+(System.currentTimeMillis()-startTime.get(e.getPlayer().getName()))+" "+BadPackets.a.get(e.getPlayer().getName()));
			startTime.put(e.getPlayer().getName(), System.currentTimeMillis());
		}
	}
}
