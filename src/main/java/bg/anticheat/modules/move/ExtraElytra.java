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

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;

public class ExtraElytra
{
	
    public static void ee(final PlayerMoveEvent e) {
        if (!DacStringBase.extraelytra_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.ExtraElytra")) {
            return;
        }
        if(Main.is110ro1())
        	return;
        if (e.getPlayer().isFlying()) {
            return;
        }
        if (e.getFrom().getY() >= e.getTo().getY()) {
            return;
        }
        if (PlayerUtils.isUsingElytra(e.getPlayer()) && e.getTo().getY() - e.getFrom().getY() > 0.5) {
            PlayerCheatEvent as = new PlayerCheatEvent(e.getPlayer(), CheatType.EXTRAELYTRA, false);
            Bukkit.getPluginManager().callEvent(as);
            if(!as.isCancelled()){
                e.setCancelled(true);
                final ItemStack a = e.getPlayer().getInventory().getChestplate();
                if (a == null)
                	return;
                e.getPlayer().getInventory().setChestplate((ItemStack)null);
                e.getPlayer().getInventory().addItem(a);
                Hackers.addExtraElytra(e.getPlayer());
                if (Hackers.isReadyForExtraElytraMessage(e.getPlayer())) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "ExtraElytra").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "ExtraElytra").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
            }
            }
        }
    }
}
