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

import org.bukkit.event.player.*;
import org.bukkit.*;
import java.util.logging.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.*;
import org.bukkit.entity.*;
import java.util.*;

public class FastFall
{
    private static HashMap<String, Float> a;
    private static HashMap<String, Location> a2;
    private static ArrayList<String> a1;
    
    static {
        FastFall.a = new HashMap<String, Float>();
        FastFall.a2 = new HashMap<String, Location>();
        FastFall.a1 = new ArrayList<String>();
    }
    
    public static void ff(final PlayerMoveEvent e) {
        if (!DacStringBase.fastfall_protection) {
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
        if (e.getPlayer().isFlying()) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.FastFall")) {
            return;
        }
        if(FastLadder.dontFastFall.contains(e.getPlayer())) {
        	return;
        }
        
/*        if(!as.containsKey(e.getPlayer().getName()))
        	as.put(e.getPlayer().getName(), e.getFrom().distance(e.getTo()));//Continue
        else
        {
        	if(as.get(e.getPlayer().getName()) == e.getFrom().distance(e.getTo()) || as.get(e.getPlayer().getName())- e.getFrom().distance(e.getTo()) > 1.5)
        	{
        		e.getPlayer().teleport(Flight.getLastBlock(e.getPlayer()));
        		Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.FASTFALL, false));
                Hackers.addFastFall(e.getPlayer());
                if (Hackers.isReadyForFastFallMessage(e.getPlayer())) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
        	}
        	as.remove(e.getPlayer().getName());
        }*/
        
        final double ydelta = e.getFrom().getY() - e.getTo().getY();
        if (ydelta > 0.0 && ydelta >= 4.0) {
            if (FastFall.a2.containsKey(e.getPlayer().getName())) {
                e.getPlayer().teleport(FastFall.a2.get(e.getPlayer().getName()));
            }
            else {
                e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
            }
            if (!e.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType().equals(Material.AIR)) {
                FastFall.a2.put(e.getPlayer().getName(), e.getPlayer().getLocation());
            }
            if (e.getPlayer().getFallDistance() > 0.0f) {
                if (FastFall.a.containsKey(e.getPlayer().getName())) {
                    if (e.getPlayer().getFallDistance() > 0.8f && !FastFall.a1.contains(e.getPlayer().getName())) {
                        if (FastFall.a2.containsKey(e.getPlayer().getName())) {
                            e.getPlayer().teleport(FastFall.a2.get(e.getPlayer().getName()));
                        }
                        else {
                            e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                        }
                        Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.FASTFALL, false));
                    }
                    if (e.getPlayer().getFallDistance() - FastFall.a.get(e.getPlayer().getName()) > 0.8f && !FastFall.a1.contains(e.getPlayer().getName())) {
                        if (FastFall.a2.containsKey(e.getPlayer().getName())) {
                            e.getPlayer().teleport(FastFall.a2.get(e.getPlayer().getName()));
                        }
                        else {
                            e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                        }
                        Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.FASTFALL, false));
                        Hackers.addFastFall(e.getPlayer());
                        if (Hackers.isReadyForFastFallMessage(e.getPlayer())) {
                            if (DacStringBase.log_console) {
                                try {
                                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (DacStringBase.log_player) {
                                for (final Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("Dakata.Admin")) {
                                        try {
                                            p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                        FastFall.a.remove(e.getPlayer().getName());
                    }
                    FastFall.a1.add(e.getPlayer().getName());
                }
                else {
                    FastFall.a.put(e.getPlayer().getName(), e.getPlayer().getFallDistance());
                }
            }
            else {
                if (FastFall.a.containsKey(e.getPlayer().getName())) {
                    FastFall.a.remove(e.getPlayer().getName());
                }
                if (FastFall.a1.contains(e.getPlayer().getName())) {
                    FastFall.a1.remove(e.getPlayer().getName());
                }
            }
            Hackers.addFastFall(e.getPlayer());
            if (!Hackers.isReadyForFastFallMessage(e.getPlayer())) {
                return;
            }
            if (DacStringBase.log_console) {
                try {
                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                }
                catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (DacStringBase.log_player) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("Dakata.Admin")) {
                        try {
                            p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "FastFall").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    public static void clear() {
        FastFall.a.clear();
        FastFall.a1.clear();
        FastFall.a2.clear();
    }
}
