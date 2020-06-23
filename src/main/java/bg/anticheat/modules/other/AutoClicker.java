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

import org.bukkit.event.player.*;
import org.bukkit.*;
import java.util.logging.*;
import org.bukkit.entity.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import java.util.*;
import org.bukkit.event.block.*;
import bg.anticheat.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;

public class AutoClicker
{
    private static HashMap<String, Short> a;
    private static ArrayList<String> as;
    private static ArrayList<String> dont;
    
    static {
        a = new HashMap<String, Short>();
        as = new ArrayList<String>();
        dont = new ArrayList<String>();
    }
    
    public static void ac(final PlayerInteractEvent e) {
        if (AutoClicker.as.contains(e.getPlayer().getName())) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.AutoClicker")) {
            return;
        }
        if (DacStringBase.clicks_per_second == -1) {
            return;
        }
        if(e.getAction().equals(Action.PHYSICAL) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))
        	return;
        if (AutoClicker.a.containsKey(e.getPlayer().getName())) {
            short s = AutoClicker.a.get(e.getPlayer().getName());
            ++s;
            AutoClicker.a.put(e.getPlayer().getName(), s);
            if (AutoClicker.a.get(e.getPlayer().getName()) > DacStringBase.clicks_per_second) {
                if (AutoClicker.a.get(e.getPlayer().getName()) > 130) {
                	Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.AUTOCLICKER, true));
                    e.getPlayer().kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replace("<hack>", "BadPackets"));
                    Bukkit.broadcastMessage(Messages.broadcast(e.getPlayer(), "BadPackets"));
                }
                if (AutoClicker.a.get(e.getPlayer().getName()) == null) {
                    return;
                }
                e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.AUTOCLICKER, false));
                if(!dont.contains(e.getPlayer().getName())){
                	dont.add(e.getPlayer().getName());
                	Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
						
						@Override
						public void run() {
							dont.remove(e.getPlayer().getName());
							
						}
					}, DacStringBase.time_per_message*20);
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoClicker(clicks = " + AutoClicker.a.get(e.getPlayer().getName()) + ")").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                        p.sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoClicker(clicks = " + AutoClicker.a.get(e.getPlayer().getName()) + ")").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                                	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "AutoClicker(clicks = " + AutoClicker.a.get(e.getPlayer().getName()) + ")").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }}
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    AutoClicker.a.remove(e.getPlayer().getName());
                }
            }, 20L);
        }
        else {
            AutoClicker.a.put(e.getPlayer().getName(), (short)1);
        }
    }
    
    public static void i(final BlockBreakEvent e) {
        if (BlockUtils.isInstantBreak(e.getBlock().getType(), e.getPlayer().getItemInHand().getType())) {
            if(!as.contains(e.getPlayer().getName()))
            	AutoClicker.as.add(e.getPlayer().getName());
        }
        Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
            @Override
            public void run() {
                AutoClicker.as.remove(e.getPlayer().getName());
            }
        }, 5L);
    }
    
    public static void clear() {
        AutoClicker.a.clear();
    }
}
