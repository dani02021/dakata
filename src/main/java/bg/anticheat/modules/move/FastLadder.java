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
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class FastLadder
{
	private static HashMap<Player, Short> vl = new HashMap<Player, Short>();
	private static HashMap<Player, Location> tp = new HashMap<Player, Location>();
	public static ArrayList<Player> dontFastFall = new ArrayList<Player>();
    public static void fastl(final PlayerMoveEvent e) {
        if (!DacStringBase.fastladder_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.FastLadder")) {
            return;
        }
        if (e.getFrom().distance(e.getTo()) == 0.0) {
            return;
        }
        if (e.getFrom().getY() >= e.getTo().getY()) {
            return;
        }
        if (e.getPlayer().isFlying()) {
            return;
        }
        if (PlayerUtils.isOnLadder(e.getPlayer())) {
        	//e.getPlayer().sendMessage(""+(e.getTo().getY()-e.getFrom().getY()));
                    if (e.getTo().getY()-e.getFrom().getY() >= Settings.max_ladder_speed) {
                    	if(e.getTo().getY()-e.getFrom().getY() >= 1) {
                    		dontFastFall.add(e.getPlayer());
                    		if(tp.containsKey(e.getPlayer())) {
                                e.getPlayer().teleport(tp.get(e.getPlayer()));
                            	tp.remove(e.getPlayer());
                    		} else e.setCancelled(true);
                            
                        	vl.remove(e.getPlayer());
                        	
                        	PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.FASTLADDER, false);
                            Bukkit.getPluginManager().callEvent(ass);
                            if(!ass.isCancelled()) {
                                Hackers.addFastLadder(e.getPlayer());
                                if (Hackers.isReadyForFastLadderMessage(e.getPlayer())) {
                                    if (DacStringBase.log_console) {
                                        try {
                                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastLadder").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                            return;
                    	}
                    	
                    	if(vl.containsKey(e.getPlayer()))
                    		vl.put(e.getPlayer(), (short) (vl.get(e.getPlayer())+1));
                    	else vl.put(e.getPlayer(), (short) 1);
                    	if(!tp.containsKey(e.getPlayer()))
                    		tp.put(e.getPlayer(), e.getFrom());
                    	if(vl.containsKey(e.getPlayer())){
                    		if(vl.get(e.getPlayer()) >= 12) {
                    			dontFastFall.add(e.getPlayer());
                    			if(tp.containsKey(e.getPlayer()))
                                    e.getPlayer().teleport(tp.get(e.getPlayer()));
                                else e.setCancelled(true);
                                
                            	vl.remove(e.getPlayer());
                            	tp.remove(e.getPlayer());
                            	
                            	PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.FASTLADDER, false);
                                Bukkit.getPluginManager().callEvent(ass);
                                if(!ass.isCancelled()) {
                                    Hackers.addFastLadder(e.getPlayer());
                                    if (Hackers.isReadyForFastLadderMessage(e.getPlayer())) {
                                        if (DacStringBase.log_console) {
                                            try {
                                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastLadder").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                    		}
                    	}
                    } else {
                    	vl.remove(e.getPlayer());
                    	tp.remove(e.getPlayer());
                    }
        } else{
        	vl.remove(e.getPlayer());
        	tp.remove(e.getPlayer());
        }
    }
}
