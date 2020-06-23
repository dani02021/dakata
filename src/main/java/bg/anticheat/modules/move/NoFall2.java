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
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class NoFall2 {
	private static HashMap<String, Short> ham = new HashMap<String, Short>();
	public static void onMove(PlayerMoveEvent e){
		if(!DacStringBase.nofall_protection)
			return;
		if(e.getPlayer().getLocation().getBlock().isLiquid())
			return;
		if(e.getPlayer().isInsideVehicle())
			return;
		if(e.getPlayer().isFlying())
			return;
		if(e.getPlayer().getHealth() <= 0)
			return;
		if(Main.dontCheckForFly.contains(e.getPlayer().getName()))
			return;
		if(e.getFrom().getY() <= e.getTo().getY())
			return;
		for(int i = (int) e.getPlayer().getLocation().getY(); i > e.getPlayer().getLocation().getY()-30; i--)
			if(e.getPlayer().getLocation().clone().subtract(0, i, 0).getBlock().getType().equals(Material.SLIME_BLOCK))
				return;
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			return;
		
		if(e.getFrom().getY() - e.getTo().getY() >= 0.08){
			if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()){
				if(e.getPlayer().getFallDistance() == 0)
				{
					if(ham.containsKey(e.getPlayer().getName())) {
						ham.put(e.getPlayer().getName(), (short) (ham.get(e.getPlayer().getName())+1));
					} else ham.put(e.getPlayer().getName(), (short) 1);
					if(ham.get(e.getPlayer().getName()) > 4) {
						if(NoFall.maxUpLoc.containsKey(e.getPlayer().getName())) {
							if(NoFall.maxUpLoc.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY() >= 4) {
								e.getPlayer().damage(0);
								if(e.getPlayer().getHealth()-((NoFall.maxUpLoc.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY())-3) > 0) {
									e.getPlayer().setHealth(e.getPlayer().getHealth()-((NoFall.maxUpLoc.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY())-3));
								} else e.getPlayer().setHealth(0);
							}
						} else {
							if(e.getPlayer().getHealth() >= 1.0) {
								e.getPlayer().damage(0);
								e.getPlayer().setHealth(e.getPlayer().getHealth()-1);
							} else e.getPlayer().setHealth(0);
						}
						//ham.remove(e.getPlayer().getName());
						Hackers.addNoFall(e.getPlayer());
	                    if (Hackers.isReadyForNoFallMessage(e.getPlayer())) {
	                        if (DacStringBase.log_console) {
	                            try {
	                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
	                            for (final Player p2 : Bukkit.getOnlinePlayers()) {
	                                if (p2.hasPermission("Dakata.Admin")) {
	                                    try {
	                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
	                                    }
	                                    catch (Exception e4) {
	                                        e4.printStackTrace();
	                                    }
	                                }
	                            }
	                        }
	                    }
					}
				} else ham.remove(e.getPlayer().getName());
			}
		}
	}
}
