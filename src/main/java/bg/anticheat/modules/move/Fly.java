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
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.InventoryBuilder;
import bg.anticheat.dakata.Main;
import bg.anticheat.modules.other.JumpPads;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class Fly {
	
	
	public static HashMap<String, Short> flyVL = new HashMap<String, Short>();
	
	public static void f(PlayerMoveEvent e)
	{
		if (DacStringBase.max_player_ping != -1) {
			try {
				if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
					return;
				}
			} catch (Exception e1) {
			}
		}
		if(InventoryBuilder.isFreezed(e.getPlayer()))
			return;
		if (!DacStringBase.fly_protection)
			return;
        if (e.getPlayer().hasPermission("Dakata.Bypass.Fly"))
            return;
        
        if(Speed.damagelist2.containsKey(e.getPlayer().getName())) {
			flyVL.remove(e.getPlayer().getName());
			return;
		}
		if (PlayerUtils.isOnEntity(e.getPlayer(), EntityType.BOAT)) {
			flyVL.remove(e.getPlayer().getName());
			return;
		}
		if(e.getPlayer().getEyeLocation().getBlock().isLiquid() || e.getPlayer().getLocation().getBlock().isLiquid()
				|| e.getPlayer().getLocation().clone().add(0,0.55,0).getBlock().isLiquid() || e.getPlayer().getLocation().clone().add(0,0.3,0).getBlock().isLiquid()
				|| e.getPlayer().getLocation().clone().add(0,0.8,0).getBlock().isLiquid()) {
			flyVL.remove(e.getPlayer().getName());
			return;
		}
		if (PlayerUtils.isUsingElytra(e.getPlayer())) {
			flyVL.remove(e.getPlayer().getName());
			return;
		}
		if(e.getPlayer().getAllowFlight()) {
			flyVL.remove(e.getPlayer().getName());
			return;
		}
        if(JumpPads.disable.containsKey(e.getPlayer().getName()))
        	return;
		if((PlayerUtils.isNextToBlock(e.getPlayer().getLocation(), Material.WATER)) &&
				(!e.getPlayer().getLocation().getBlock().isLiquid() && !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid()))
				return;
		//checks
		
		//e.getPlayer().sendMessage("y: "+e.getPlayer().getVelocity().clone().getY()+" speed: "+(e.getTo().distance(e.getFrom())));
		if(e.getTo().getY() >= e.getFrom().getY() && e.getPlayer().getVelocity().clone().getY() < -0.08 &&
				(e.getPlayer().getVelocity().clone().getY() != -0.1552320045166016 && e.getPlayer().getVelocity().clone().getY() != -0.230527368912964 && e.getPlayer().getVelocity().clone().getY() != 0.30431682745754424 && e.getPlayer().getVelocity().clone().getY() != -0.44749789698341763 && e.getPlayer().getVelocity().clone().getY() != -0.32374665784961826 && e.getPlayer().getVelocity().clone().getY() != -0.3031682745754424 && e.getPlayer().getVelocity().clone().getY() != -0.09090330585848257)
				&& !e.getPlayer().getLocation().getBlock().isLiquid() &&
				!PlayerUtils.isOnLadder(e.getPlayer())) {
			//e.getPlayer().sendMessage("HACKER: "+e.getPlayer().getVelocity().clone().getY() + " " + flyVL.get(e.getPlayer().getName()));
			if(e.getPlayer().getVelocity().clone().getY() < -1) {
				//TODO: Definitely is hacking
				PlayerCheatEvent as = new PlayerCheatEvent(e.getPlayer(), CheatType.FLY, false);
				Bukkit.getPluginManager().callEvent(as);
				if(!as.isCancelled()) {
	                Hackers.addFly(e.getPlayer());
	                if (Hackers.isReadyForFlyMessage(e.getPlayer())) {
	                    if (DacStringBase.log_console) {
	                        try {
	                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replace("\u0420\u2019\u0412§", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Fly(mode: def)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
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
	                                    p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Fly(mode: def)").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
	                                }
	                                catch (Exception e4) {
	                                    e4.printStackTrace();
	                                }
	                            }
	                        }
	                    }
	                }
	                e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
				}
                return;
			}
			//TODO: Continue
			if(flyVL.containsKey(e.getPlayer().getName())) {
				flyVL.put(e.getPlayer().getName(), (short) (flyVL.get(e.getPlayer().getName())+1));
			} else flyVL.put(e.getPlayer().getName(), (short) 1);
			//e.getPlayer().sendMessage("A: "+flyVL.get(e.getPlayer().getName()));
			if(PlayerUtils.isUsingPotionEffect(e.getPlayer(), PotionEffectType.JUMP)) {
				a(e.getPlayer(), (short) (5+PlayerUtils.getPotionEffect(e.getPlayer(), PotionEffectType.JUMP).getAmplifier()));
			} else a(e.getPlayer(), (short) 4);
		} else if(e.getFrom().getY() - e.getTo().getY() == 0 && e.getPlayer().getVelocity().clone().getY() == -0.0784000015258789) {
			if(!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid())
			    flyVL.remove(e.getPlayer().getName());
		} else if(e.getFrom().getY() > e.getTo().getY() && e.getPlayer().getVelocity().clone().getY() < -0.0784000015258789) {
			flyVL.remove(e.getPlayer().getName());
		}
	}
	private static void a(Player p, short vl) {
		if(flyVL.get(p.getName()) >= vl)
		{
			PlayerCheatEvent ass = new PlayerCheatEvent(p, CheatType.FLY, false);
			Bukkit.getPluginManager().callEvent(ass);
			if(!ass.isCancelled()){
                Hackers.addFly(p);
                if (Hackers.isReadyForFlyMessage(p)) {
                    if (DacStringBase.log_console) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replace("\u0420\u2019\u0412§", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Fly(mode: vl)").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                    	if(Main.isUsingPlaceholderAPI())
								try {
									Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) p, String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p)))));
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							else
								try {
									Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                        for (final Player p1 : Bukkit.getOnlinePlayers()) {
                            if (p1.hasPermission("Dakata.Admin")) {
                                try {
                                    p1.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Fly(mode: vl)").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
                                }
                                catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                }
                p.teleport(PlayerUtils.getLastOnGroundLocation(p));
			}
			flyVL.remove(p.getName());
		}
	}
}
