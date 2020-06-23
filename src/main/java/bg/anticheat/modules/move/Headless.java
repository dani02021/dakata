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

import java.util.*;
import java.util.logging.Level;

import bg.anticheat.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;

public class Headless
{
    private static ArrayList<String> v;
    
    static {
        Headless.v = new ArrayList<String>();
    }
    
    public static void ih(final PlayerMoveEvent e) {
        if (!DacStringBase.headless_protection) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.Headless")) {
            return;
        }
        if (Headless.v.contains(e.getPlayer().getName())) {
            return;
        }
        if (e.getPlayer().getLocation().getPitch() > Settings.max_player_pitch) {
            e.getPlayer().getLocation().setPitch(20.0f);
            Hackers.addHeadless(e.getPlayer());
            if(Hackers.isReadyForHeadlessMessage(e.getPlayer())) {
                if (DacStringBase.log_console) {
                    try {
                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Headless").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                    }
                    catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (DacStringBase.log_player) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("Dakata.Admin")) {
                            try {
                                p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Headless").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }
            }
            Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HEADLESS, false));
        }
    }
    
    public static void oni(final AsyncPlayerPreLoginEvent e) {
        if (!DacStringBase.headless_protection) {
            return;
        }
        if (Bukkit.getPlayer(e.getUniqueId()).hasPermission("Dakata.Bypass.Headless")) {
            return;
        }
        try {
            Headless.v.add(Bukkit.getPlayer(e.getUniqueId()).getName());
            Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    Headless.v.remove(Bukkit.getPlayer(e.getUniqueId()).getName());
                }
            }, 80L);
        } catch(NullPointerException ex) { }
    }
}
