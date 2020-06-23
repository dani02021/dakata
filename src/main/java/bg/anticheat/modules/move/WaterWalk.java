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

import org.bukkit.event.player.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;

public class WaterWalk
{
    private static HashMap<String, Short> h;
    
    static {
        WaterWalk.h = new HashMap<String, Short>();
    }
    
    public static void checkWaterWalk(final PlayerMoveEvent e) {
        if (!DacStringBase.waterwalk_protection) {
            return;
        }
        if(e.getPlayer().isInsideVehicle())
        	return;
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
        if(PlayerUtils.isUsingElytra(e.getPlayer()))
        	return;
        if (e.getPlayer().hasPermission("Dakata.Bypass.Jesus")) {
            return;
        }
        if (e.getFrom().distance(e.getTo()) == 0.0) {
            return;
        }
        if (e.getFrom().getY() < e.getTo().getY()) {
            return;
        }
        if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation(), Material.WATER_LILY))
        	return;
        if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation(), Material.CARPET) || PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().subtract(0,0.1,0)).contains(Material.CARPET))
        	return;
        final Location loc = e.getPlayer().getLocation().subtract(0.0, 0.35, 0.0);
        if (((loc.getBlock().getType().equals(Material.STATIONARY_WATER) || loc.getBlock().getType().equals(Material.WATER)) || ((loc.getBlock().getType().equals(Material.STATIONARY_LAVA)) || loc.getBlock().getType().equals(Material.LAVA))) && e.getPlayer().getLocation().getBlock().getType().equals(Material.AIR) && checkNotOnGround(e.getPlayer())) {
        	if(loc.getBlock().getType() == Material.WATER || loc.getBlock().getType() == Material.LAVA){
        		if(loc.getBlock().getData() <= 3){
                	if (WaterWalk.h.containsKey(e.getPlayer().getName())) {
                        short i = WaterWalk.h.get(e.getPlayer().getName());
                        ++i;
                        WaterWalk.h.put(e.getPlayer().getName(), i);
                    }
                    else {
                        WaterWalk.h.put(e.getPlayer().getName(), (short)1);
                    }
        		}
        	} else{
            	if (WaterWalk.h.containsKey(e.getPlayer().getName())) {
                    short i = WaterWalk.h.get(e.getPlayer().getName());
                    ++i;
                    WaterWalk.h.put(e.getPlayer().getName(), i);
                }
                else {
                    WaterWalk.h.put(e.getPlayer().getName(), (short)1);
                }
        	}
            if (WaterWalk.h.get(e.getPlayer().getName()) > Settings.max_water_moves) {
                e.setCancelled(true);
                Hackers.addWaterWalk(e.getPlayer());
                if(Hackers.isReadyForWaterWalkMessage(e.getPlayer())) {
                    Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.WATERWALK, false));
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
                WaterWalk.h.remove(e.getPlayer().getName());
            }
        }
        else if (WaterWalk.h.containsKey(e.getPlayer().getName())) {
            WaterWalk.h.remove(e.getPlayer().getName());
        }
    }
    
    private static boolean checkNotOnGround(final Player player) {
        final Block block1 = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        final Block block2 = block1.getRelative(BlockFace.EAST);
        final Block block3 = block1.getRelative(BlockFace.NORTH_EAST);
        final Block block4 = block1.getRelative(BlockFace.NORTH_WEST);
        final Block block5 = block1.getRelative(BlockFace.SOUTH_EAST);
        final Block block6 = block1.getRelative(BlockFace.SOUTH_WEST);
        final Block block7 = block1.getRelative(BlockFace.SOUTH);
        final Block block8 = block1.getRelative(BlockFace.WEST);
        final Block block9 = block1.getRelative(BlockFace.NORTH);
        return block1.getType() == Material.STATIONARY_WATER && block2.getType() == Material.STATIONARY_WATER && block3.getType() == Material.STATIONARY_WATER && block4.getType() == Material.STATIONARY_WATER && block5.getType() == Material.STATIONARY_WATER && block5.getType() == Material.STATIONARY_WATER && block6.getType() == Material.STATIONARY_WATER && block7.getType() == Material.STATIONARY_WATER && block8.getType() == Material.STATIONARY_WATER && block9.getType() == Material.STATIONARY_WATER;
    }
    
    public static void clear() {
        WaterWalk.h.clear();
    }
}
