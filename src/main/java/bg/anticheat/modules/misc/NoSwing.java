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
import org.bukkit.enchantments.Enchantment;

import java.util.logging.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.*;
import bg.anticheat.utils.Logger;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

public class NoSwing
{
    private static ArrayList<String> sw;
    private static ArrayList<String> sw1;
    
    static {
        NoSwing.sw = new ArrayList<String>();
        NoSwing.sw1 = new ArrayList<String>();
    }
    
    public static void swing(final BlockDamageEvent e) {
        if (!DacStringBase.noswing_blockbreak_protection) {
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
        if (BlockUtils.isInstantBreak(e.getBlock().getType(), e.getPlayer().getItemInHand().getType())) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.NoSwing")) {
            return;
        }
        if(e.getPlayer().getItemInHand().containsEnchantment(Enchantment.DIG_SPEED))
        	return;
        if (!NoSwing.sw.contains(e.getPlayer().getName())) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.NOSWING_BLOCK, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
            e.setCancelled(true);
            Hackers.addNoSwingBlock(e.getPlayer());
            if (Hackers.isReadyForNoSwingBlockMessage(e.getPlayer())) {
            	 Logger.addMessageToFileLog(e.getPlayer(), "NoSwing(Block)");
                if (DacStringBase.log_console) {
                    try {
                    	if(Main.isUsingPlaceholderAPI())
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoSwing(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoSwing(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                    for (final Player p1 : Bukkit.getOnlinePlayers()) {
                        if (p1.hasPermission("Dakata.Admin")) {
                            try {
                            	if(Main.isUsingPlaceholderAPI())
                                    p1.sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoSwing(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                            	else p1.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoSwing(Block)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }}
            }
        }
        else {
            NoSwing.sw.remove(e.getPlayer().getName());
        }
    }
    
    public static void swing2(final EntityDamageByEntityEvent e) {
        if (!DacStringBase.noswing_attack_protection) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (((Player)e.getDamager()).hasPermission("Dakata.Bypass")) {
            return;
        }
        if (((Player)e.getDamager()).hasPermission("Dakata.Bypass.NoSwing")) {
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
        if (!NoSwing.sw1.contains(((Player)e.getDamager()).getName())) {
			   PlayerCheatEvent ass = new PlayerCheatEvent((Player) e.getDamager(), CheatType.NOSWING_ATTACK, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
           		 Logger.addMessageToFileLog((Player) e.getDamager(), "NoSwing(Attack)");
            e.setCancelled(true);
            ((Player)e.getDamager()).sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Please turn off NoSwing hack!");
        }}
        else {
            NoSwing.sw1.remove(((Player)e.getDamager()).getName());
        }
    }
    
    public static void plani(final PlayerAnimationEvent e) {
        if (!DacStringBase.noswing_blockbreak_protection && !DacStringBase.noswing_attack_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.NoSwing")) {
            return;
        }
        NoSwing.sw.add(e.getPlayer().getName());
        NoSwing.sw1.add(e.getPlayer().getName());
    }
    
    public static void clear() {
        NoSwing.sw.clear();
    }
}
