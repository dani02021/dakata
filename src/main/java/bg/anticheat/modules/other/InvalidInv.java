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

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.logging.*;
import bg.anticheat.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.*;
import org.bukkit.event.entity.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import bg.anticheat.modules.misc.ImposiblleAttack;

import org.bukkit.event.player.*;

public class InvalidInv
{
    private static ArrayList<String> dontWith;
    private static ArrayList<String> doWith;
    
    static {
        InvalidInv.dontWith = new ArrayList<String>();
        InvalidInv.doWith = new ArrayList<String>();
    }
    
    public static void inv(final InventoryClickEvent e) {
        if (!DacStringBase.invalidinventory_protection) {
            return;
        }
        if(!(e.getWhoClicked() instanceof Player))
        	return;
        if(e.getWhoClicked().isInsideVehicle())
        	return;
        //if(e.getWhoClicked().getFallDistance() > 0.0)
        //	return;
        if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
        	return;
        if(PlayerUtils.isInLiquidLoc(e.getWhoClicked().getLocation()))
        	return;
        if(PlayerUtils.isOnLadder((Player) e.getWhoClicked()))
        	return;
        if ((e.getWhoClicked() instanceof Player && InvalidInv.doWith.contains(e.getWhoClicked().getName()))) {
            e.setCancelled(true);
            Bukkit.getPluginManager().callEvent(new PlayerCheatEvent((Player)e.getWhoClicked(), CheatType.INVALIDINV, false));
            e.getWhoClicked().getOpenInventory().close();
            if (DacStringBase.log_console) {
                try {
                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replace("\u0420\u2019\u0412§6", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked()))));
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
                               p.sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getWhoClicked(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv(mode 1)").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked())))));
                        	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv(mode 1)").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked()))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        } else if(!ImposiblleAttack.as.containsKey(e.getWhoClicked().getName())){
        	if(e.getInventory().getType().equals(InventoryType.PLAYER)){
                e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent((Player)e.getWhoClicked(), CheatType.INVALIDINV, false));
                e.getWhoClicked().getOpenInventory().close();
                if (DacStringBase.log_console) {
                    try {
                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replace("\u0420\u2019\u0412§6", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked()))));
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
                                    p.sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getWhoClicked(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv(mode 1)").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked())))));
                            	else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidInv(mode 1)").replaceAll("<player>", e.getWhoClicked().getName()).replaceAll("<world>", e.getWhoClicked().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getWhoClicked()))));
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
        	}
        }
    }
    
    public static void inv3(final EntityDamageByEntityEvent e) {
        if (!DacStringBase.invalidinventory_protection) {
            return;
        }
        if (e.getEntity() instanceof Player) {
        	if(!dontWith.contains(e.getEntity().getName())){
                InvalidInv.dontWith.add(((Player)e.getEntity()).getName());
                Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        InvalidInv.dontWith.remove(((Player)e.getEntity()).getName());
                    }
                }, 30L);
        	}
        }
    }
    
    public static void inv2(final PlayerMoveEvent e) {
        if (InvalidInv.dontWith.contains(e.getPlayer().getName())) {
            return;
        }
        if(e.getFrom().getY() > e.getTo().getY())
        	return;
        if(e.getFrom().distance(e.getTo()) < 0.11D)
        	return;
        if(!doWith.contains(e.getPlayer().getName())){
        	InvalidInv.doWith.add(e.getPlayer().getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
            @Override
            public void run() {
                InvalidInv.doWith.remove(e.getPlayer().getName());
            }
        }, 1L);}
    }
    
    public static void clear() {
        InvalidInv.dontWith.clear();
        InvalidInv.doWith.clear();
    }
}
