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

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.logging.*;
import bg.anticheat.utils.*;
import bg.anticheat.utils.Logger;
import me.clip.placeholderapi.PlaceholderAPI;
import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import java.util.*;

public class Regen
{
    private static HashMap<String, Long> regain;
    
    static {
        Regen.regain = new HashMap<String, Long>();
    }
    
    public static void checkRegen(final EntityRegainHealthEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!DacStringBase.regen_protection) {
                return;
            }
            final Player p = (Player)e.getEntity();
            if (DacStringBase.max_player_ping != -1) {
                try {
                    if (Ping.getPlayerPing(p) > DacStringBase.max_player_ping) {
                        return;
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (p.hasPermission("Dakata.Bypass.Regen")) {
                return;
            }
            if(p.getFireTicks() > 0)
            	return;
            if (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
                if (e.getAmount() > 1.0) {
                    Hackers.addRegen(p);
                    if (Hackers.isReadyForRegenMessage(p)) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (DacStringBase.log_player) {
                        	if(Main.isUsingPlaceholderAPI())
    								try {
    									Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getEntity(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity())))));
    								} catch (Exception e2) {
    									// TODO Auto-generated catch block
    									e2.printStackTrace();
    								}
    							else
    								try {
    									Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity()))));
    								} catch (Exception e1) {
    									// TODO Auto-generated catch block
    									e1.printStackTrace();
    								}
                            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                                if (p2.hasPermission("Dakata.Admin")) {
                                    try {
                                    	if(Main.isUsingPlaceholderAPI())
                                            p2.sendMessage(PlaceholderAPI.setPlaceholders(p, String.valueOf(DacStringBase.anticheat_tag) + p.getName() + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity())))));
                                    	else p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + p.getName() + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
                                    }
                                    catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new PlayerCheatEvent((Player)e.getEntity(), CheatType.REGEN, false));
                    return;
                }
                if (Regen.regain.containsKey(p.getName())) {
                    Hackers.addRegen(p);
                    if (Hackers.isReadyForRegenMessage(p)) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (DacStringBase.log_player) {
                        	if(Main.isUsingPlaceholderAPI())
    								try {
    									Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getEntity(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity())))));
    								} catch (Exception e2) {
    									// TODO Auto-generated catch block
    									e2.printStackTrace();
    								}
    							else
    								try {
    									Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity()))));
    								} catch (Exception e1) {
    									// TODO Auto-generated catch block
    									e1.printStackTrace();
    								}
                            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                                if (p2.hasPermission("Dakata.Admin")) {
                                    try {
                                    	if(Main.isUsingPlaceholderAPI())
                                            p2.sendMessage(PlaceholderAPI.setPlaceholders(p, String.valueOf(DacStringBase.anticheat_tag) + p.getName() + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity())))));
                                    	else p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + p.getName() + DacStringBase.hack_msg.replaceAll("<hack>", "Regen").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));                                    }
                                    catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new PlayerCheatEvent((Player)e.getEntity(), CheatType.REGEN, false));
                    p.setFoodLevel(p.getFoodLevel() - 1);
                    Regen.regain.remove(p.getName());
                }
                Regen.regain.put(p.getName(), System.currentTimeMillis());
                if(Main.is19ro1() || Main.is110ro1() || Main.is111ro1() || Main.is112ro1()){
                    Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            Regen.regain.remove(p.getName());
                        }
                    }, Settings.min_one_ten_regen_time);
                } else{
                    Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            Regen.regain.remove(p.getName());
                        }
                    }, Settings.min_regen_time);
                }
            }
        }
    }
    
    public static void clear() {
        Regen.regain.clear();
    }
}
