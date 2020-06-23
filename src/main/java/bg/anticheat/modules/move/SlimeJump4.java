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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class SlimeJump4 {
	public static HashMap<String, Double> downVec = new HashMap<String, Double>();
	public static ArrayList<String> slimeTouch = new ArrayList<String>();
	public static void onMove(PlayerMoveEvent e) {
        if (!DacStringBase.slimeJump_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.SlimeJump")) {
            return;
        }
        if (e.getFrom().distance(e.getTo()) == 0.0) {
            return;
        }
        
		if(e.getPlayer().isSneaking()) {
	    	downVec.remove(e.getPlayer().getName());
	    	slimeTouch.remove(e.getPlayer().getName());
			return;
		}
		if(e.getPlayer().isFlying()) {
	    	downVec.remove(e.getPlayer().getName());
	    	slimeTouch.remove(e.getPlayer().getName());
			return;
		}
		if(e.getFrom().getY() > e.getTo().getY()) {
			if(!downVec.containsKey(e.getPlayer().getName())){
		        downVec.put(e.getPlayer().getName(), e.getFrom().getY());
			}
			else {
				if(slimeTouch.contains(e.getPlayer().getName())) {
					//e.getPlayer().sendMessage("dif "+DecimalFormat.getInstance().format((downVec.get(e.getPlayer().getName())-e.getFrom().getY())));
			    	if(downVec.get(e.getPlayer().getName())-e.getFrom().getY() < -1.5){
			    		PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.SLIMEJUMP, false);
			    		Bukkit.getPluginManager().callEvent(ass);
                        if(!ass.isCancelled()){
    			    		Hackers.addSlimeJump(e.getPlayer());
                            if (Hackers.isReadyForSlimeJumpMessage(e.getPlayer())) {
                                if (DacStringBase.log_console) {
                                    try {
                                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "SlimeJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                    for (final Player p1 : Bukkit.getOnlinePlayers()) {
                                        if (p1.hasPermission("Dakata.Admin")) {
                                            try {
                                                p1.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "SlimeJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                            }
                                            catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                    }
                                }
    							e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
    							}
                        }
			    	}
					downVec.remove(e.getPlayer().getName());
			    	slimeTouch.remove(e.getPlayer().getName());
				}
			}
		    if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK)) {
				if(!slimeTouch.contains(e.getPlayer().getName()))
					slimeTouch.add(e.getPlayer().getName());
		    } else if(!e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK) &&
		    		!e.getTo().getBlock().getRelative(BlockFace.DOWN).isEmpty()){
		    	slimeTouch.remove(e.getPlayer().getName());
		    	downVec.remove(e.getPlayer().getName());
		    }
		} else if(e.getFrom().getY() == e.getTo().getY())
		{
		    if(e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK)) {
				if(!slimeTouch.contains(e.getPlayer().getName()))
					slimeTouch.add(e.getPlayer().getName());
		    } else if(!e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK) &&
		    		!e.getTo().getBlock().getRelative(BlockFace.DOWN).isEmpty()){
		    	slimeTouch.remove(e.getPlayer().getName());
		    	downVec.remove(e.getPlayer().getName());
		    }
		}
	}
}
