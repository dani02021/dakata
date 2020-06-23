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

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;

public class BoatFly //CHECK IT
{
	private static HashMap<String, Double> vl = new HashMap<String, Double>();
    public static void bf(final PlayerMoveEvent e) {
        if (!DacStringBase.boatfly_protection) {
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
        if (!e.getPlayer().isInsideVehicle()) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.BoatFly")) {
            return;
        }
        if (!e.getPlayer().getVehicle().getType().equals(EntityType.BOAT)) {
            return;
        }
        if(!PlayerUtils.isInLiquidLoc(e.getPlayer().getLocation()) &&
        !PlayerUtils.isInLiquidLoc(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()) //||
        //e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() != Material.AIR
        ){
        	//e.getPlayer().sendMessage("lol2");
        	if(vl.containsKey(e.getPlayer().getName())){
            	//e.getPlayer().sendMessage("lol3 "+vl.get(e.getPlayer().getName()));
        		if(vl.get(e.getPlayer().getName()) > 0.6) {
                	//e.getPlayer().sendMessage("lol4");
                            if (!e.getPlayer().isInsideVehicle()) {
                                return;
                            }
                            PlayerCheatEvent a = new PlayerCheatEvent(e.getPlayer(), CheatType.BOATFLY, false);
                        	Bukkit.getPluginManager().callEvent(a);
                        	if(!a.isCancelled()){
                               	Hackers.addBoatFly(e.getPlayer());
                                if (Hackers.isReadyForBoatFlyMessage(e.getPlayer())) {
                                    e.getPlayer().leaveVehicle();
                                    if (DacStringBase.log_console) {
                                        try {
                                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "BoatFly").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                        }
                                        catch (Exception e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    if (DacStringBase.log_player) {
                                        for (final Player p : Bukkit.getOnlinePlayers()) {
                                            if (p.hasPermission("Dakata.Admin")) {
                                                try {
                                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "BoatFly").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                                }
                                                catch (Exception e2) {
                                                    e2.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                }
                        	}
        		} else vl.put(e.getPlayer().getName(), vl.get(e.getPlayer().getName())+e.getTo().getY()-e.getFrom().getY());
        	} else vl.put(e.getPlayer().getName(), e.getTo().getY()-e.getFrom().getY());
        } else vl.remove(e.getPlayer().getName());
    }
    
    public static boolean checkNotOnGround(final Player player) {
        final Block block1 = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        final Block block2 = block1.getRelative(BlockFace.EAST);
        final Block block3 = block1.getRelative(BlockFace.NORTH_EAST);
        final Block block4 = block1.getRelative(BlockFace.NORTH_WEST);
        final Block block5 = block1.getRelative(BlockFace.SOUTH_EAST);
        final Block block6 = block1.getRelative(BlockFace.SOUTH_WEST);
        final Block block7 = block1.getRelative(BlockFace.SOUTH);
        final Block block8 = block1.getRelative(BlockFace.WEST);
        final Block block9 = block1.getRelative(BlockFace.NORTH);
        return block1.getType() == Material.AIR && block2.getType() == Material.AIR && block3.getType() == Material.AIR && block4.getType() == Material.AIR && block5.getType() == Material.AIR && block5.getType() == Material.AIR && block6.getType() == Material.AIR && block7.getType() == Material.AIR && block8.getType() == Material.AIR && block9.getType() == Material.AIR;
    }
}
