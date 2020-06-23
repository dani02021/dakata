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

import org.bukkit.entity.*;
import org.bukkit.event.player.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.*;
import java.util.logging.Level;

import org.bukkit.*;
import org.bukkit.event.entity.*;

public class AntiCactus
{
    private static ArrayList<Player> damaged;
    public static HashMap<Player, Long> lastMoveTime;
    public static HashMap<Player, Integer> damagetime;
    
    static {
        AntiCactus.damaged = new ArrayList<Player>();
        AntiCactus.damagetime = new HashMap<Player, Integer>();
        AntiCactus.lastMoveTime = new HashMap<Player, Long>();
    }
    
    public static void ac(final PlayerMoveEvent e) {
        if (!DacStringBase.anticactus_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.AntiCactus")) {
            return;
        }
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (e.getPlayer().isDead()) {
            return;
        }
        if (e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }
        if(lastMoveTime.get(e.getPlayer()) != null)
    	    if(System.currentTimeMillis()-lastMoveTime.get(e.getPlayer()) < 400)
    	    	return;
    	lastMoveTime.put((Player) e.getPlayer(), System.currentTimeMillis());
        if (isNextToCactus(e.getPlayer()) || PlayerUtils.isOnBlock(e.getPlayer(), Material.CACTUS)) {
            if (AntiCactus.damaged.contains(e.getPlayer())) {
                AntiCactus.damaged.remove(e.getPlayer());
                if (AntiCactus.damagetime.containsKey(e.getPlayer())) {
                    AntiCactus.damagetime.remove(e.getPlayer());
                }
                return;
            }
            if (!AntiCactus.damaged.contains(e.getPlayer())) {
                if (AntiCactus.damagetime.containsKey(e.getPlayer())) {
                	//e.getPlayer().sendMessage("cactusVL: "+AntiCactus.damagetime.get(e.getPlayer()));
                    if (AntiCactus.damagetime.get(e.getPlayer()) >= Settings.max_cactus_moves) {
                    	PlayerCheatEvent a = new PlayerCheatEvent(e.getPlayer(), CheatType.ANTICACTUS, true);
                    	Bukkit.getPluginManager().callEvent(a);
                    	if(!a.isCancelled()){
                    		Hackers.addAntiCactus(e.getPlayer());
                    		if(Hackers.isReadyForAntiCactusMessage(e.getPlayer())) {
                                if (DacStringBase.log_console) {
                                    try {
                                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "AntiCactus").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                                p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "AntiCactus").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                            }
                                            catch (Exception e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                    }
                                }
                    		}
                            e.getPlayer().kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replaceAll("<hack>", "AntiCactus"));
                            if(DacStringBase.broadcast_kick) Bukkit.broadcastMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replaceAll("<player>", e.getPlayer().getName()).replaceAll("<hack>", "AntiCactus"));
                            return;
                    	}
                    }
                    AntiCactus.damagetime.put(e.getPlayer(), AntiCactus.damagetime.get(e.getPlayer()) + 1);
                }
                else if (!AntiCactus.damagetime.containsKey(e.getPlayer())) {
                    AntiCactus.damagetime.put(e.getPlayer(), 1);
                }
            }
        }
        else {
            if (AntiCactus.damaged.contains(e.getPlayer())) {
                AntiCactus.damaged.remove(e.getPlayer());
            }
            if (AntiCactus.damagetime.containsKey(e.getPlayer())) {
                AntiCactus.damagetime.remove(e.getPlayer());
            }
        }
    }
    
    private static boolean isNextToCactus(final Player p) {
        final Location bb = p.getLocation().add(0.0, 1.0, 0.0);
        final Location bb2 = bb.add(0.301, 0.0, 0.0);
        final Location bb3 = bb.add(0.0, 0.0, 0.301);
        final Location bb4 = bb.subtract(0.301, 0.0, 0.0);
        final Location bb5 = bb.subtract(0.0, 0.0, 0.301);
        final Location b1 = p.getLocation().add(0.301, 0.0, 0.0);
        final Location b2 = p.getLocation().add(0.0, 0.0, 0.301);
        final Location b3 = p.getLocation().subtract(0.301, 0.0, 0.0);
        final Location b4 = p.getLocation().subtract(0.0, 0.0, 0.301);
        return b1.getBlock().getType() == Material.CACTUS || b2.getBlock().getType() == Material.CACTUS || b3.getBlock().getType() == Material.CACTUS || b4.getBlock().getType() == Material.CACTUS || bb2.getBlock().getType() == Material.CACTUS || bb3.getBlock().getType() == Material.CACTUS || bb4.getBlock().getType() == Material.CACTUS || bb5.getBlock().getType() == Material.CACTUS;
    }
    
    public static void ach(final EntityDamageByBlockEvent e) {
        if (e.getDamager() == null || e.getEntity() == null) {
            return;
        }
        if (e.getDamager().getType() == Material.CACTUS && e.getEntity() instanceof Player) {
        	if(!AntiCactus.damaged.contains(e.getEntity()))
        	    AntiCactus.damaged.add((Player)e.getEntity());
            
        }
    }
    
    public static void clear() {
        AntiCactus.damaged.clear();
        AntiCactus.damagetime.clear();
    }
}
