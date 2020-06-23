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

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class InvalidHead
{
    private static HashMap<String, Float> firstLoc;
    private static HashMap<String, Float> secondLoc;
    
    static {
    	firstLoc = new HashMap<String, Float>();
    	secondLoc = new HashMap<String, Float>();
    }
    
    public static void sca(final PlayerMoveEvent e) {
        if (e.getPlayer().isFlying()) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.InvalidHead")) {
            return;
        }
        if (!DacStringBase.scaffoldwalk_protection) {
            return;
        }//TEST - PITCH + YAW OR ONLY PITCH ALSO ADD VIOLATION
        if (firstLoc.containsKey(e.getPlayer().getName())) {
            if (!secondLoc.containsKey(e.getPlayer().getName())) {
            	secondLoc.put(e.getPlayer().getName(), e.getPlayer().getLocation().getPitch()+e.getPlayer().getLocation().getYaw());
            }
            else if (secondLoc.get(e.getPlayer().getName()) > firstLoc.get(e.getPlayer().getName())) {
                if (secondLoc.get(e.getPlayer().getName()) - firstLoc.get(e.getPlayer().getName()) > 50.0F) {
                    if (e.getPlayer().getLocation().getPitch()+e.getPlayer().getLocation().getYaw() == firstLoc.get(e.getPlayer().getName())) {
                    	firstLoc.remove(e.getPlayer().getName());
                    	secondLoc.remove(e.getPlayer().getName());
                    	Hackers.addInvalidHead(e.getPlayer());
                    	if(Hackers.isReadyForInvalidHeadMessage(e.getPlayer()))
                    		if(Hackers.getInvalidHeadViolation(e.getPlayer()) > (int)DacStringBase.violation_time/8){
                    			PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.INVALIDHEAD, true);
                    			Bukkit.getPluginManager().callEvent(ass);
                    			if(!ass.isCancelled()){
                                    e.getPlayer().kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replaceAll("<hack>", "Invalid Head"));
                                    if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replaceAll("<hack>", "Invalid Head").replaceAll("<player>", e.getPlayer().getName()));
                    			}
                    		}
                    }
                    else {
                    	firstLoc.remove(e.getPlayer().getName());
                    	secondLoc.remove(e.getPlayer().getName());
                    }
                }
                else {
                	firstLoc.remove(e.getPlayer().getName());
                	secondLoc.remove(e.getPlayer().getName());
                }
            }
            else if (firstLoc.get(e.getPlayer().getName()) - secondLoc.get(e.getPlayer().getName()) > 40.0f) {
                if (e.getPlayer().getLocation().getPitch()+e.getPlayer().getLocation().getYaw() == firstLoc.get(e.getPlayer().getName())) {
                	firstLoc.remove(e.getPlayer().getName());
                	secondLoc.remove(e.getPlayer().getName());
                	secondLoc.remove(e.getPlayer().getName());
                	Hackers.addInvalidHead(e.getPlayer());
                	if(Hackers.isReadyForInvalidHeadMessage(e.getPlayer())) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidHead").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                        p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidHead").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                    }
                                    catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                        }
                	}
                }
                else {
                	firstLoc.remove(e.getPlayer().getName());
                	secondLoc.remove(e.getPlayer().getName());
                }
            }
            else {
            	firstLoc.remove(e.getPlayer().getName());
            	secondLoc.remove(e.getPlayer().getName());
            }
        }
        else {
        	firstLoc.put(e.getPlayer().getName(), e.getPlayer().getLocation().getPitch()+e.getPlayer().getLocation().getYaw());
        }
    }
    
    public static void clear() {
    	firstLoc.clear();
    	secondLoc.clear();
    }
}
