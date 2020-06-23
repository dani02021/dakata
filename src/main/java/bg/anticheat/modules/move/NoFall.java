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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class NoFall {
	public static HashMap<String, Integer> maxUpLoc = new HashMap<String, Integer>();
	public static ArrayList<String> hitted = new ArrayList<String>();
	public static HashMap<String, Integer> maxUpLocHJ = new HashMap<String, Integer>();
	public static HashMap<String, Integer> maxUpLocEnternal = new HashMap<String, Integer>();
	public static HashMap<String, Short> neededDmg = new HashMap<String, Short>();
	
	public static void moveOn(final PlayerMoveEvent e)
	{
		//if(e.isCancelled())
		//	return;
        if(e.getPlayer().getHealth() <= 0) {
   			maxUpLoc.remove(e.getPlayer().getName());
   			return;
        }
		if(e.getPlayer().isInsideVehicle())
			return;
		
		if(e.getPlayer().getLocation().getBlock().isLiquid()) {
			maxUpLoc.remove(e.getPlayer().getName());
			return;
		}
		
		if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.WEB) ||
				e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK)){
			maxUpLoc.remove(e.getPlayer().getName());
			return;
		}
		
		if(PlayerUtils.isOnLadder(e.getPlayer())) {
			maxUpLoc.remove(e.getPlayer().getName());
			return;
		}
		
        if(Main.is110ro1() || Main.is111ro1())
        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION)) {
    			maxUpLoc.remove(e.getPlayer().getName());
    			return;
        	}
        
        if(e.getPlayer().getInventory().getBoots() != null)
           if(e.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_FALL)) {
   			maxUpLoc.remove(e.getPlayer().getName());
   			return;
           }
		
		if(!DacStringBase.nofall_protection)
			return;
		
	    if(e.getPlayer().getAllowFlight())
		    return;
	    
	    if(PlayerUtils.isUsingElytra(e.getPlayer())) {
	    	maxUpLoc.remove(e.getPlayer().getName());
	    	return;
	    }
	    
			if(e.getFrom().getY() > e.getTo().getY()){
				if(!maxUpLoc.containsKey(e.getPlayer().getName())){
				    maxUpLoc.put(e.getPlayer().getName(), (int) e.getFrom().getY());
				}
			}
			
			if(!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty() &&
					!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.WALL_SIGN)) {
				if(maxUpLoc.containsKey(e.getPlayer().getName())) {
					maxUpLocEnternal.put(e.getPlayer().getName(), maxUpLoc.get(e.getPlayer().getName()));
					if((maxUpLocEnternal.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY()-3) >= 1) {
						neededDmg.put(e.getPlayer().getName(), (short) (maxUpLocEnternal.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY()-2));
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
							
							@Override
							public void run() {
								
								if(e.getPlayer().getLocation().getBlock().isLiquid()) {
									maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
									return;
								}
								
								if(PlayerUtils.isNextToBlock(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.WEB) ||
										(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK) &&
												!e.getPlayer().isSneaking())){
									maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
									return;
								}
								
								if(PlayerUtils.isOnLadder(e.getPlayer())) {
									maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
									return;
								}
								
						        if(Main.is110ro1() || Main.is111ro1())
						        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION)) {
						    			maxUpLoc.remove(e.getPlayer().getName());
										maxUpLocEnternal.remove(e.getPlayer().getName());
						    			return;
						        	}
						        
						        if(e.getPlayer().getInventory().getBoots() != null)
						           if(e.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_FALL)) {
						   			maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
						   			return;
						           }
						        
						        if(e.getPlayer().getHealth() <= 0) {
						   			maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
						   			return;
						        }
						        
							    if(PlayerUtils.isUsingElytra(e.getPlayer())) {
							    	maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
							    	return;
							    }
						        
								if(maxUpLocEnternal.containsKey(e.getPlayer().getName())) {
									if(!hitted.contains(e.getPlayer().getName())) {
										//e.getPlayer().sendMessage("1 "+(e.getPlayer().getHealth()+"-"+"(("+maxUpLoc.get(e.getPlayer().getName())+"-"+((int)e.getPlayer().getLocation().getY())+")-3)"));
										//e.getPlayer().sendMessage("2 "+(maxUpLoc.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY()));
										//if(/*e.getPlayer().getHealth()-((maxUpLoc.get(e.getPlayer().getName())-e.getPlayer().getLocation().getY())-3) > 0 &&*/
											if(PlayerUtils.isUsingPotionEffect(e.getPlayer(), PotionEffectType.JUMP)) {
												if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 0)
												{
													a(e, (short) 5, neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 1)
												{
													a(e, (short) 6, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 2)
												{
													a(e, (short) 7, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 3)
												{
													a(e, (short) 8, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 4)
												{
													a(e, (short) 9, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 5)
												{
													a(e, (short) 10, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 6)
												{
													a(e, (short) 11, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 7)
												{
													a(e, (short) 13, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 8)
												{
													a(e, (short) 13, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 9)
												{
													a(e, (short) 14, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 10)
												{
													a(e, (short) 15, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 11)
												{
													a(e, (short) 16, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 12)
												{
													a(e, (short) 17, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 13)
												{
													a(e, (short) 18, (short) neededDmg.get(e.getPlayer().getName()));
												} else if(PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier() == 14)
												{
													a(e, (short) 19, (short) neededDmg.get(e.getPlayer().getName()));
												} else {
													//TODO: Continue to 20
										   			maxUpLoc.remove(e.getPlayer().getName());
										   			maxUpLocEnternal.remove(e.getPlayer().getName());
										   			return;
												}
											} else {
												e.getPlayer().damage(0.0);
												if(e.getPlayer().getHealth()-neededDmg.get(e.getPlayer().getName()) < 0)
													e.getPlayer().setHealth(0);
												else e.getPlayer().setHealth(e.getPlayer().getHealth()-neededDmg.get(e.getPlayer().getName()));
												Hackers.addNoFall(e.getPlayer());
							                    if (Hackers.isReadyForNoFallMessage(e.getPlayer())) {
							                        if (DacStringBase.log_console) {
							                            try {
							                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
							                                        p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
									hitted.remove(e.getPlayer().getName());
									maxUpLoc.remove(e.getPlayer().getName());
									maxUpLocEnternal.remove(e.getPlayer().getName());
								}
								neededDmg.remove(e.getPlayer().getName());
							}
						}, 16L);
					}
					maxUpLoc.remove(e.getPlayer().getName());
				}
			}
	}
	
	public static void playerHit(EntityDamageEvent e)
	{
		if(!(e.getEntity() instanceof Player))
			return;
		if(e.getCause().equals(DamageCause.FALL)){
			if(!hitted.contains(e.getEntity().getName())) {
				if(neededDmg.containsKey(e.getEntity().getName()))
				    if(neededDmg.get(e.getEntity().getName()) > e.getDamage())
					    neededDmg.put(e.getEntity().getName(), (short) (neededDmg.get(e.getEntity().getName())-e.getDamage()));
				hitted.add(e.getEntity().getName());
			}
		}
	}
	
	public static void a(PlayerMoveEvent e, short vl, short heart) {
		if(neededDmg.get(e.getPlayer().getName()) >= vl)
		{
			e.getPlayer().damage(0.0);
			if(e.getPlayer().getHealth()-heart < 0)
				e.getPlayer().setHealth(0);
			else e.getPlayer().setHealth(e.getPlayer().getHealth()-heart);
			Hackers.addNoFall(e.getPlayer());
            if (Hackers.isReadyForNoFallMessage(e.getPlayer())) {
                if (DacStringBase.log_console) {
                    try {
                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
	
	public static void b(PlayerMoveEvent e, short vl, short heart) {
			e.getPlayer().damage(0.0);
			if(e.getPlayer().getHealth()-heart < 0)
				e.getPlayer().setHealth(0);
			else e.getPlayer().setHealth(e.getPlayer().getHealth()-heart);
			Hackers.addNoFall(e.getPlayer());
            if (Hackers.isReadyForNoFallMessage(e.getPlayer())) {
                if (DacStringBase.log_console) {
                    try {
                        Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoFall(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
