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

import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.block.BlockFace;

import java.util.logging.*;
import bg.anticheat.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.potion.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import bg.anticheat.modules.other.JumpPads;

import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;

public class HighJump
{
    private static ArrayList<String> a;
    protected static ArrayList<String> dont;
    public static HashMap<String, Short> upMoves;
	private static HashMap<String, Double> previousMove;
    
    static {
        HighJump.a = new ArrayList<String>();
        HighJump.dont = new ArrayList<String>();
        HighJump.upMoves = new HashMap<String, Short>();
        HighJump.previousMove = new HashMap<String, Double>();
    }
    
    public static void jump(final PlayerMoveEvent e) {
        if (!DacStringBase.high_jump_protection) {
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
        if (e.getPlayer().hasPermission("Dakata.Bypass.HighJump")) {
            return;
        }
		if(Main.dontCheckForFly.contains(e.getPlayer().getName()))
			return;
		if(TP.dont.contains(e.getPlayer().getName()))
			return;
        if(Main.is110ro1() || Main.is111ro1())
        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION))
        		return;
        if(Main.is110ro1() || Main.is111ro1())
    		if(e.getPlayer().getItemInHand().getType().equals(Material.CHORUS_FRUIT))
    			return;
        if (e.getPlayer().isFlying()) {
            return;
        }
		for (final Entity en : e.getPlayer().getNearbyEntities(1.0, 1.0, 1.0)) {
			if (en.getType() == EntityType.BOAT) {
				return;
			}
		}
        if(JumpPads.disable.containsKey(e.getPlayer().getName()) && e.getFrom().clone().subtract(0,0.1,0).getBlock().isEmpty()
        		&& !e.getTo().clone().subtract(0,0.1,0).getBlock().isEmpty()){
        	return;
        }
        if (e.getPlayer().isInsideVehicle()) {
            return;
        }
        if (HighJump.dont.contains(e.getPlayer().getName())) {
            return;
        }
        if (e.getPlayer().getLocation().clone().subtract(0.0, 1.0, 0.0).getBlock().getType().equals(Material.PISTON_MOVING_PIECE) || e.getPlayer().getLocation().getBlock().getType().equals(Material.PISTON_MOVING_PIECE)) {
            return;
        }
        if (e.getPlayer().isInsideVehicle()) {
            return;
        }
        if (PlayerUtils.isOnLadder(e.getPlayer())) {
            return;
        }
        if(Main.dontCheckOnSpawn.contains(e.getPlayer().getName()))
        	return;
        if (PlayerUtils.isInLiquidLoc(e.getTo())) {
            return;
        }
        if (e.getFrom().getY() < e.getTo().getY()) {
            if (HighJump.a.contains(e.getPlayer().getName())) {
                return;
            }
            if (HighJump.upMoves.containsKey(e.getPlayer().getName())) {
                short s = HighJump.upMoves.get(e.getPlayer().getName());
                ++s;
                HighJump.upMoves.put(e.getPlayer().getName(), s);
            }
            else {
                HighJump.upMoves.put(e.getPlayer().getName(), (short)1);
            }
    		if (HighJump.previousMove.containsKey(e.getPlayer().getName())) {
    			//if (e.getTo().getY() == HighJump.previousMove.get(e.getPlayer().getName()) && e.getPlayer().getLocation().subtract(0,0.3,0).getBlock().isEmpty()) {
                //    Flight.counts.put(e.getPlayer().getName(), Flight.counts.get(e.getPlayer().getName())+1);
    			//}
    			HighJump.previousMove.remove(e.getPlayer().getName());
    		} else {
    			HighJump.previousMove.put(e.getPlayer().getName(), e.getTo().getY() - e.getFrom().getY());
    		}
    		//e.getPlayer().sendMessage("jump: "+(e.getTo().getY() - e.getFrom().getY()));
    		if(!e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SNOW) && 
    				!e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SNOW_BLOCK))
            if ((e.getTo().getY() - e.getFrom().getY() == 0.4200000000000017 || e.getTo().getY() - e.getFrom().getY() == 0.4000000000000057 || e.getTo().getY() - e.getFrom().getY() == 0.4099999999999966 || e.getTo().getY() - e.getFrom().getY() == 0.3681288095787778 || e.getTo().getY() - e.getFrom().getY() == 0.10999999999999943 || e.getTo().getY() - e.getFrom().getY() == 0.5 || e.getTo().getY() - e.getFrom().getY() == 1.0) && !e.getPlayer().hasPotionEffect(PotionEffectType.JUMP) || e.getTo().getY() - e.getFrom().getY() == 0.31179034424889096 || e.getTo().getY() - e.getFrom().getY() > 2.0) {
            	if(e.getTo().getY() - e.getFrom().getY() == 0.5) {
            		if(!PlayerUtils.isNextToBlock(e.getTo().clone().subtract(0,0.1,0).getBlock().getLocation(), Material.SKULL) &&
            				!PlayerUtils.isNextToBlock(e.getTo().clone().subtract(0,0.1,0).getBlock().getLocation(), Material.CAKE_BLOCK) &&
            				!PlayerUtils.isNextToBlock(e.getTo().clone().subtract(0,0.1,0).getBlock().getLocation(), Material.SNOW)) {
            			if(!e.getTo().clone().subtract(0,0.1,0).getBlock().getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.EAST).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.WEST).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_EAST).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_WEST).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_EAST).getType().name().contains("SLAB") &&
            					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_WEST).getType().name().contains("SLAB")) {

                    			if(!e.getTo().clone().subtract(0,0.1,0).getBlock().getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.EAST).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.WEST).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_EAST).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_WEST).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_EAST).getType().name().contains("STEP") &&
                    					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_WEST).getType().name().contains("STEP")) {
                    				
                        			if(!e.getTo().clone().subtract(0,0.1,0).getBlock().getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.EAST).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.WEST).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_EAST).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.NORTH_WEST).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_EAST).getType().name().contains("STAIRS") &&
                        					!e.getTo().clone().subtract(0,0.1,0).getBlock().getRelative(BlockFace.SOUTH_WEST).getType().name().contains("STAIRS")) {
                        				
                        				if(!e.getTo().clone().subtract(0,0.5,0).getBlock().getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH_EAST).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH_WEST).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH_EAST).getType().name().contains("FENCE") &&
                            					!e.getTo().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH_WEST).getType().name().contains("FENCE")) {
                        					if(PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()).contains(Material.FENCE) &&
                        							PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()).contains(Material.ACACIA_FENCE) &&
                        							PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()).contains(Material.BIRCH_FENCE) &&
                        							PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()).contains(Material.DARK_OAK_FENCE) &&
                        							PlayerUtils.getMaterialsAround(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()).contains(Material.SPRUCE_FENCE)) {
                                            	Hackers.addHighJump(e.getPlayer());
                                                if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                                                    if (DacStringBase.log_console) {
                                                        try {
                                                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                                                if(PlayerUtils.getLastOnGroundLocation(e.getPlayer()).getY() < e.getTo().getY())
                                                e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                                                else e.setCancelled(true);
                                                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                                                return;
                        					}
                        				}
                        			}
                			}
            			}
            		}
            		
            		return;
            	}
            	
            	if(PlayerUtils.isOnEntity(e.getPlayer(), EntityType.BOAT, 4.0)
        				|| PlayerUtils.isInEntity(e.getPlayer())
        				|| e.getPlayer().isInsideVehicle())
        			return;
            	
            	Hackers.addHighJump(e.getPlayer());
                if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 2)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                if(PlayerUtils.getLastOnGroundLocation(e.getPlayer()).getY() < e.getTo().getY())
                e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                else e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                return;
            }
    		/* I HAVE NO IDEA WHAT IS THIS XD
    		if(((e.getFrom().clone().toVector().getY()-e.getTo().clone().toVector().getY())-(e.getTo().getY()-e.getFrom().getY())) % 1 == 0 && !e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SNOW) && 
    				!e.getTo().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SNOW_BLOCK)) {
    			e.setCancelled(true);
    			
            	Hackers.addHighJump(e.getPlayer());
                if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 3)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 3) ").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
                e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                return;
    		}
    		*/
            if (e.getTo().getY() - e.getFrom().getY() > Settings.max_highjump_distance && !e.getPlayer().hasPotionEffect(PotionEffectType.JUMP) && e.getPlayer().getVelocity().getY() < 0) {
                
            	
            	if(NoFall.maxUpLoc.get(e.getPlayer().getName()) != null) {
            		if(NoFall.maxUpLoc.get(e.getPlayer().getName())-((int)e.getPlayer().getLocation().getY()) >= 4) {
            			NoFall.maxUpLocHJ.put(e.getPlayer().getName(), NoFall.maxUpLoc.get(e.getPlayer().getName())-((int)e.getPlayer().getLocation().getY()));
            			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
							
							@Override
							public void run() {
								if(!NoFall.hitted.contains(e.getPlayer().getName()) && NoFall.maxUpLocHJ.containsKey(e.getPlayer().getName())) {
									//e.getPlayer().sendMessage(""+(NoFall.maxUpLocHJ.get(e.getPlayer().getName())+" "+((int)e.getPlayer().getLocation().getY())));
									NoFall.b(e, (short)4, (NoFall.maxUpLocHJ.get(e.getPlayer().getName()).shortValue()));
								} else NoFall.hitted.remove(e.getPlayer().getName());
								NoFall.maxUpLocHJ.remove(e.getPlayer().getName());
							}
						}, 5L);
            		}
            	}
            	
            	Hackers.addHighJump(e.getPlayer());
                if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 1)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Dakata.Admin")) {
                                try {
                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump(mode 1) ").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if(PlayerUtils.getLastOnGroundLocation(e.getPlayer()).getY() < e.getTo().getY())
                e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                else e.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                return;
            }
            /*for (final PotionEffect p2 : e.getPlayer().getActivePotionEffects()) {
                if (e.getPlayer().isInsideVehicle()) {
                    return;
                }
                if (!p2.getType().equals((Object)PotionEffectType.JUMP)) {
                    continue;
                }
                if (p2.getAmplifier() < 2) {
                    if (e.getTo().getY() - e.getFrom().getY() <= Settings.max_highjump_potion_distance || !e.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
                        continue;
                    }
                    Hackers.addHighJump(e.getPlayer());
                    if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (DacStringBase.log_player) {
                            for (final Player p3 : Bukkit.getOnlinePlayers()) {
                                if (p3.hasPermission("Dakata.Admin")) {
                                    try {
                                        p3.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                    }
                                    catch (Exception e5) {
                                        e5.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    if(PlayerUtils.getLastOnGroundLocation(e.getPlayer()).getY() < e.getTo().getY())
                       e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                    else e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                }
                else {
                    if (p2.getAmplifier() != 3 || e.getTo().getY() - e.getFrom().getY() <= 0.73 || !e.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
                        continue;
                    }
                    if (e.getPlayer().isInsideVehicle()) {
                        return;
                    }
                    Hackers.addHighJump(e.getPlayer());
                    if (Hackers.isReadyForHighJumpMessage(e.getPlayer())) {
                        if (DacStringBase.log_console) {
                            try {
                                Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (DacStringBase.log_player) {
                            for (final Player p3 : Bukkit.getOnlinePlayers()) {
                                if (p3.hasPermission("Dakata.Admin")) {
                                    try {
                                        p3.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "HighJump").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
                                    }
                                    catch (Exception e5) {
                                        e5.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
                    Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.HIGHJUMP, false));
                }
            }*/
        } else {
            if (e.getPlayer().isInsideVehicle()) {
                return;
            }
            if (!Main.is17ro1() && !Main.is17ro2() && !Main.is17ro3() && !Main.is17ro4() && e.getPlayer().getLocation().clone().subtract(0.0, 1.0, 0.0).getBlock().getType().equals(Material.SLIME_BLOCK)) {
                if(!a.contains(e.getPlayer().getName()))
            	    HighJump.a.add(e.getPlayer().getName());
                return;
            } else if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isBlock() &&
            		e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SLIME_BLOCK))
            	a.remove(e.getPlayer().getName());
            if (HighJump.a.contains(e.getPlayer().getName())) {
                HighJump.a.remove(e.getPlayer().getName());
            }
            if (HighJump.upMoves.containsKey(e.getPlayer().getName())) {
                HighJump.upMoves.remove(e.getPlayer().getName());
            }
        }
    }
    
    public static boolean isWasOnSlime(final String playerName) {
        return HighJump.a.contains(playerName);
    }
    
    public static void clear() {
        HighJump.a.clear();
    }
    
    public static void d(final EntityExplodeEvent e) {
        if (!DacStringBase.high_jump_protection) {
            return;
        }
        Entity[] entities;
        for (int length = (entities = e.getEntity().getLocation().getChunk().getEntities()).length, i = 0; i < length; ++i) {
            final Entity en = entities[i];
            if (en instanceof Player) {
                if(en.getLocation().distance(e.getEntity().getLocation()) > 10.0)
                	return;
                if(!dont.contains(((Player)en).getName())){
                HighJump.dont.add(((Player)en).getName());
                Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        HighJump.dont.remove(((Player)en).getName());
                    }
                }, 30L);}
            }
        }
    }
    
    public static void e(final BlockExplodeEvent e) {
        Entity[] entities;
        for (int length = (entities = e.getBlock().getLocation().getChunk().getEntities()).length, i = 0; i < length; ++i) {
            final Entity en = entities[i];
            if (en instanceof Player) {
                if(en.getLocation().distance(e.getBlock().getLocation()) > 10.0)
                	return;
                if(!dont.contains(((Player)en).getName())){
                HighJump.dont.add(((Player)en).getName());
                Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        HighJump.dont.remove(((Player)en).getName());
                    }
                }, 30L);}
            }
        }
    }
}
