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
import org.bukkit.event.block.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import java.util.logging.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.*;
import bg.anticheat.utils.Logger;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.entity.*;

public class InvalidPotion
{
    public static void invapo(final PlayerInteractEvent e) {
        if (e.getPlayer().hasPermission("Dakata.Bypass.InvalidPotion")) {
            return;
        }
        if (!DacStringBase.invalidpotion_protection) {
            return;
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!e.getPlayer().getItemInHand().getType().equals(Material.POTION)) {
                return;
            }
            if (e.getPlayer().getItemInHand() == null || e.getPlayer().getItemInHand().getType() == Material.AIR) {
                return;
            }
            if(Potion.fromItemStack(e.getPlayer().getItemInHand()).getType() == null){
            	e.setCancelled(true);
            	return;
            }
            if(Potion.fromItemStack(e.getPlayer().getItemInHand()).getType().equals(PotionType.INSTANT_HEAL) || Potion.fromItemStack(e.getPlayer().getItemInHand()).getType().equals(PotionType.INSTANT_DAMAGE))
            	return;
            if ((Potion.fromItemStack(e.getPlayer().getItemInHand()).getEffects().size() == 0 || Potion.fromItemStack(e.getPlayer().getItemInHand()).getEffects().size() >= 15) && !e.getPlayer().getItemInHand().isSimilar(new ItemStack(Material.POTION, 1, (short)0)) && Potion.fromItemStack(e.getPlayer().getItemInHand()).isSplash()) {
                Hackers.addInvalidPotion(e.getPlayer());
                if (Hackers.isReadyForInvalidPotionMessage(e.getPlayer())) {
                	Logger.addMessageToFileLog(e.getPlayer(), "InvalidPotion");
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidPotion").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                	if(Main.isUsingPlaceholderAPI())
                                        p.sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidPotion").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
                                	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidPotion").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }
                }
                e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.INVALIDPOTION, false));
                e.getPlayer().setItemInHand((ItemStack)null);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.uyari_msg.replace("<hack>", "InvalidPotion"));
            }
        }
    }
}
