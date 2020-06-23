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
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class WaterY {
	private static HashMap<String, Short> vl = new HashMap<String, Short>();
	private static HashMap<String, Location> backLocForZeroY = new HashMap<String, Location>();
	public static void onMove(PlayerMoveEvent e) {
        if (!DacStringBase.waterY_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.WaterY")) {
            return;
        }
        if (e.getFrom().distance(e.getTo()) == 0.0) {
            return;
        }
        if(Main.dontCheckForFly.contains(e.getPlayer().getName()))
        	return;
        if(Main.is110ro1() || Main.is111ro1())
        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION))
        		return;
        if(Main.toggleFly.containsKey(e.getPlayer().getName()))
        	return;
        //if (e.getFrom().getY() == e.getTo().getY()) {
        //    return;
        //}
		if(e.getPlayer().isFlying())
			return;
		if(PlayerUtils.isOnEntity(e.getPlayer(), EntityType.BOAT, 4.0)
				|| PlayerUtils.isInEntity(e.getPlayer())
				|| e.getPlayer().isInsideVehicle())
			return;
		//If have permission, ping and e.t.c
		
		if(e.getPlayer().getLocation().getBlock().isLiquid() /*&&
				e.getPlayer().getEyeLocation().getBlock().isLiquid()*/) {
			if(e.getTo().getY() >= e.getFrom().getY()) {
				//e.getPlayer().sendMessage("waterY: "+(e.getTo().getY()-e.getFrom().getY()));
				if(e.getTo().getY()-e.getFrom().getY() > 0.13 || e.getTo().getY()-e.getFrom().getY() == 0 || e.getTo().getY()-e.getFrom().getY() == 0.10000000000000142) {
					if(e.getTo().getY()-e.getFrom().getY() == 0 && e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid() && e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).isLiquid() && (e.getPlayer().getEyeLocation().getBlock().getRelative(BlockFace.UP).isLiquid() || e.getPlayer().getEyeLocation().getBlock().getRelative(BlockFace.UP).isEmpty())) {
						if(vl.containsKey(e.getPlayer().getName()))
							vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
						else {
							backLocForZeroY.put(e.getPlayer().getName(), e.getPlayer().getLocation());
							vl.put(e.getPlayer().getName(), (short) 1);
						}
						
						if(vl.get(e.getPlayer().getName()) > 12) {
							PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.WATER_Y, false);
							Main.getThisPlugin().getServer().getPluginManager().callEvent(ass);
							if(!ass.isCancelled()) {
								if(backLocForZeroY.get(e.getPlayer().getName()) != null)
									e.getPlayer().teleport(backLocForZeroY.get(e.getPlayer().getName()));
								else e.setTo(e.getFrom());
								Hackers.addWaterY(e.getPlayer());
								if(Hackers.isReadyForWaterYMessage(e.getPlayer())) {
		                        if (DacStringBase.log_console) {
		                            try {
		                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
		                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
		                                    }
		                                    catch (Exception e4) {
		                                        e4.printStackTrace();
		                                    }
		                                }
		                            }
		                        }
		                        }
							}
							vl.remove(e.getPlayer().getName());
						}
					}
					if(e.getTo().getY()-e.getFrom().getY() > 0.38 || e.getTo().getY()-e.getFrom().getY() == 0.10000000000000142) {
						if(e.getPlayer().getEyeLocation().getBlock().isLiquid() && e.getTo().getY()-e.getFrom().getY() != 0.10000000000000142) {
							PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.WATER_Y, false);
							Main.getThisPlugin().getServer().getPluginManager().callEvent(ass);
							if(!ass.isCancelled()) {
								e.setCancelled(true);
								Hackers.addWaterY(e.getPlayer());
								if(Hackers.isReadyForWaterYMessage(e.getPlayer())) {
			                        if (DacStringBase.log_console) {
			                            try {
			                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
			                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
			                                    }
			                                    catch (Exception e4) {
			                                        e4.printStackTrace();
			                                    }
			                                }
			                            }
			                        }
								}
							}
						} else {
							if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.EAST).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.WEST).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.NORTH).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SOUTH).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.NORTH_EAST).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.NORTH_WEST).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SOUTH_EAST).isLiquid() &&
									e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SOUTH_WEST).isLiquid()) {
								PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.WATER_Y, false);
								Main.getThisPlugin().getServer().getPluginManager().callEvent(ass);
								if(!ass.isCancelled()) {
									e.setCancelled(true);
									Hackers.addWaterY(e.getPlayer());
									if(Hackers.isReadyForWaterYMessage(e.getPlayer())) {
			                        if (DacStringBase.log_console) {
			                            try {
			                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
			                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
						vl.remove(e.getPlayer().getName());
						return;
					}
					if(e.getPlayer().getEyeLocation().getBlock().isLiquid() && e.getFrom().getY()!=e.getTo().getY()) {
						if(vl.containsKey(e.getPlayer().getName()))
							vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
						else vl.put(e.getPlayer().getName(), (short) 1);
						
						if(vl.get(e.getPlayer().getName()) > 5) {
							PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.WATER_Y, false);
							Main.getThisPlugin().getServer().getPluginManager().callEvent(ass);
							if(!ass.isCancelled()) {
								e.setCancelled(true);
								Hackers.addWaterY(e.getPlayer());
								if(Hackers.isReadyForWaterYMessage(e.getPlayer())) {
		                        if (DacStringBase.log_console) {
		                            try {
		                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
		                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "WaterY").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
		                                    }
		                                    catch (Exception e4) {
		                                        e4.printStackTrace();
		                                    }
		                                }
		                            }
		                        }
		                        }
							}
							vl.remove(e.getPlayer().getName());
						}
					}
					
					
				} else vl.remove(e.getPlayer().getName());
			}
		}
	}
}
