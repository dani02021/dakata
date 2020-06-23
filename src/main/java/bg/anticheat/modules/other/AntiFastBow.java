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

public class AntiFastBow
{
    static ArrayList<String> COOLDOWN;
    
    static {
        AntiFastBow.COOLDOWN = new ArrayList<String>();
    }
    
    public static void antibow(final EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!DacStringBase.fastbow_protection) {
                return;
            }
            if (((Player)e.getEntity()).hasPermission("Dakata.Bypass.FastBow")) {
                return;
            }
            if (DacStringBase.max_player_ping != -1) {
                try {
                    if (Ping.getPlayerPing((Player)e.getEntity()) > DacStringBase.max_player_ping) {
                        return;
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            final Player player = (Player)e.getEntity();
            if (e.getForce() == 1.0f) {
                if (AntiFastBow.COOLDOWN.contains(player.getName())) {
                    e.setCancelled(true);
                    PlayerCheatEvent ass = new PlayerCheatEvent(player, CheatType.FASTBOW, false);
                    Bukkit.getPluginManager().callEvent(ass);
                    if(!ass.isCancelled()) {
                    	 Logger.addMessageToFileLog(player, "FastBow");
                        final int i = player.getInventory().getHeldItemSlot();
                        if(player.getInventory().getHeldItemSlot() < 7)
                        	player.getInventory().setHeldItemSlot(i + 1);
                        else player.getInventory().setHeldItemSlot(i - 1);
                        Hackers.addFastBow((Player)e.getEntity());
                        if (Hackers.isReadyForFastBowMessage((Player)e.getEntity())) {
                            if (DacStringBase.log_console) {
                                try {
                                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastBow").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
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
                                for (final Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("Dakata.Admin")) {
                                        try {
                                        	if(Main.isUsingPlaceholderAPI())
                                                p.sendMessage(PlaceholderAPI.setPlaceholders(player, String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastBow").replaceAll("<player>", player.getName()).replaceAll("<world>", player.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(player)))));
                                        	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastBow").replaceAll("<player>", player.getName()).replaceAll("<world>", player.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(player))));
                                        }
                                        catch (Exception e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                } else {
                AntiFastBow.COOLDOWN.add(player.getName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        AntiFastBow.COOLDOWN.remove(player.getName());
                    }
                }, 20L);}
            }
        }
    }
    
    public static void clear() {
        AntiFastBow.COOLDOWN.clear();
    }
}
