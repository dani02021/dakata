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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.Messages;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class Reach
{
	
	private static HashMap<String, Short> vl = new HashMap<String, Short>();
	
    public static void reachTesti(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
        	if(e.getEntity() instanceof ComplexLivingEntity)
        		return;
        	if(e.getCause().equals(DamageCause.THORNS))
        		return;
            if (!DacStringBase.reach_attack_protection) {
                return;
            }
            if (((Player)e.getDamager()).hasPermission("Dakata.Bypass")) {
                return;
            }
            if (DacStringBase.max_player_ping != -1) {
                try {
                    if (Ping.getPlayerPing((Player)e.getDamager()) > DacStringBase.max_player_ping) {
                        return;
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            final Player p = (Player)e.getDamager();
            if (p.hasPermission("Dakata.Bypass.Reach.Attack")) {
                return;
            }
            if (p.getLocation().distance(e.getEntity().getLocation()) > Settings.min_attack_distace) {
				   PlayerCheatEvent ass = new PlayerCheatEvent((Player) e.getDamager(), CheatType.REACH_ATTACK, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()) {
               		   e.setCancelled(true);
               		   if(vl.containsKey(e.getDamager().getName())) {
               			   vl.put(e.getDamager().getName(), (short) (vl.get(e.getDamager().getName())+1));
               		   } else vl.put(e.getDamager().getName(), (short) 1);
               		   if(vl.get(e.getDamager().getName()) >= 3) {
        				   PlayerCheatEvent ass1 = new PlayerCheatEvent((Player) e.getDamager(), CheatType.REACH_ATTACK, true);
                       	   Bukkit.getPluginManager().callEvent(ass1);
                       	   if(!ass1.isCancelled()) {
                       		 Logger.addMessageToFileLog((Player) e.getDamager(), "Reach(Attack)");
                   			   e.setCancelled(true);
                       	   }
               			   vl.put(e.getDamager().getName(), (short) (vl.get(e.getDamager().getName())-1));
               		   }
                   }
            } else vl.remove(e.getDamager().getName());
        }
    }
    
    public static void blokKirildiginda(final BlockBreakEvent e) {
        if (!DacStringBase.reach_blockbreak_protection) {
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
        final Player p = e.getPlayer();
        if (p.hasPermission("Dakata.Bypass.Reack.Block")) {
            return;
        }
        final Block b = e.getBlock();
        if (b.getType() == Material.TNT && p.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
            return;
        }
        if (p.getLocation().distance(b.getLocation()) > Settings.min_block_distace) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.REACH_BLOCK, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
            e.setCancelled(true);
            p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.uyari_msg.replaceAll("<hack>", "Reach"));
            Hackers.addReach(e.getPlayer());
            if (Hackers.isReadyForReachMessage(e.getPlayer())) {
            	 Logger.addMessageToFileLog(e.getPlayer(), "Reach(Block)");
                if (DacStringBase.log_console) {
                    try {
                    	if(Main.isUsingPlaceholderAPI())
                           Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders(p, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Reach(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                    	else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Reach(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        if (p2.hasPermission("Dakata.Admin")) {
                            try {
                            	if(Main.isUsingPlaceholderAPI())
                                	p2.sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Reach(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                            	else p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Reach(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }}
            }
        }
    }
}
