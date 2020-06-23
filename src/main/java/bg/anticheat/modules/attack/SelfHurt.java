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


package bg.anticheat.modules.attack;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;

public class SelfHurt
{
    public static void self(final EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (((Player)e.getDamager()).hasPermission("Dakata.Bypass.SelfHurt")) {
            return;
        }
        if (((Player)e.getDamager()).getName().equals(((Player)e.getEntity()).getName())) {
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
            if (DacStringBase.log_console) {
                try {
                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replace("\u0420\u2019\u0412§6", "")) + ((Player)e.getDamager()).getName() + " may use SelfHurt " + "(try to attack yourself)" + " (ping:" + Ping.getPlayerPing((Player)e.getDamager()) + ")");
                }
                catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (DacStringBase.log_player) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("Dakata.Admin")) {
                        try {
                            p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + ((Player)e.getDamager()).getName() + " may use SelfHurt " + "(try to attack yourself)" + " (ping:" + Ping.getPlayerPing((Player)e.getDamager()) + ")");
                        }
                        catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
            ((Player)e.getDamager()).sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "You can't hurt yourself");
            e.setCancelled(true);
        }
    }
}
