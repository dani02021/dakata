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


package bg.anticheat.modules.attack;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class Criticals
{
    public static void c(final EntityDamageByEntityEvent e) {
        if (PlayerUtils.isInLiquidLoc(e.getDamager().getLocation())) {
            return;
        }
        if (PlayerUtils.isInWeb(e.getDamager().getLocation())) {
            return;
        }
        if(!DacStringBase.criticals_protection)
        	return;
        if (e.getDamager() instanceof Player) {
            final Player p = (Player)e.getDamager();
            if (PlayerUtils.isOnLadder2(p)) {
                return;
            }
            if(e.getDamager().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_WATER)
            	return;
            if(e.getDamager().getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR)
            	return;
            //if (HighJump.upMoves.get(p.getName()) == null) {
            
        	//Wurst crits bug
            //if(e.getDamage() < 1)
            //	as(p, e);
            
            //p.sendMessage(""+e.getDamager().getFallDistance()+" "+p.getVelocity().getY() + " "+(getCritical(p) == e.getDamage())+" "+e.getDamage());
                if ((e.getDamager().getFallDistance() == 0 || (e.getDamager().getVelocity().getY() < -0.07 && e.getDamager().getVelocity().getY() > -0.08) || e.getDamager().getVelocity().getY() > 0) && getCritical(p) == e.getDamage()) {
                	as(p, e);
                } else if(getCritical(p) != e.getDamage())
                	if(Hackers.getCriticalsList().containsKey(p.getName()))
                	    Hackers.getCriticalsList().put(p.getName(), (short) (Hackers.getCriticalsList().get(p.getName())-1));
            }
//            else if (e.getDamage() == getCritical(p) && HighJump.upMoves.get(p.getName()) <= 3) {
//                Hackers.addCriticals(p);
//                if (Hackers.isReadyForCriticalsMessage(p)) {
//                    if (DacStringBase.log_console) {
//                        try {
//                            Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
//                        }
//                        catch (Exception e2) {
//                            e2.printStackTrace();
//                        }
//                    }
//                    if (DacStringBase.log_player) {
//                        for (final Player p2 : Bukkit.getOnlinePlayers()) {
//                            if (p2.hasPermission("Dakata.Admin")) {
//                                try {
//                                    p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + p.getName() + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
//                                }
//                                catch (Exception e3) {
//                                    e3.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//                if (DacStringBase.crit_func.equals("cansel")) {
//                    e.setCancelled(true);
//                }
//                else if (DacStringBase.crit_func.equals("divide")) {
//                    e.setDamage(e.getDamage() / 1.5);
//                }
//                Bukkit.getPluginManager().callEvent(new PlayerCheatEvent((Player)e.getDamager(), CheatType.CRITICALS, false));
//            }
//        }
    }
    
    public static double getCritical(final Player p) {
    	
        double critical = 1.5;
        if (p.getItemInHand().getType() == Material.WOOD_SWORD) {
            critical *= 5.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.STONE_SWORD) {
            critical *= 6.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.GOLD_SWORD) {
            critical *= 5.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.IRON_SWORD) {
            critical *= 7.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
            critical *= 8.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.WOOD_AXE) {
            critical *= 4.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.STONE_AXE) {
            critical *= 5.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.GOLD_AXE) {
            critical *= 4.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.IRON_AXE) {
            critical *= 6.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.DIAMOND_AXE) {
            critical *= 7.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.WOOD_PICKAXE) {
            critical *= 3.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
            critical *= 4.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.GOLD_PICKAXE) {
            critical *= 3.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
            critical *= 5.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
            critical *= 6.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.WOOD_SPADE) {
            critical *= 2.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.STONE_SPADE) {
            critical *= 3.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.GOLD_SPADE) {
            critical *= 2.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.IRON_SPADE) {
            critical *= 4.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        else if (p.getItemInHand().getType() == Material.DIAMOND_SPADE) {
            critical *= 5.0;
            if (p.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                for (int i = 0; i < p.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL); ++i) {
                    critical += 1.25;
                }
            }
        }
        return critical;
    }
    
    public static void as(Player p, EntityDamageByEntityEvent e) {
    	PlayerCheatEvent a = new PlayerCheatEvent(p, CheatType.CRITICALS, false);
    	Bukkit.getPluginManager().callEvent(a);
    	if(!a.isCancelled()){
    		Hackers.addCriticals(p);
                if (Hackers.isReadyForCriticalsMessage(p, (short) 8)) {
                	Logger.addMessageToFileLog(p, "Criticals");
                    if (DacStringBase.log_console) {
                        try {
                        	if(Main.isUsingPlaceholderAPI())
                                Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders(p, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p)))));
                        	else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (DacStringBase.log_player) {
                        for (final Player p2 : Bukkit.getOnlinePlayers()) {
                            if (p2.hasPermission("Dakata.Admin")) {
                            	if(Main.isUsingPlaceholderAPI())
        							try {
        								Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p)))));
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
                            }
                        }
                    }
        	}
                if (DacStringBase.crit_func.equals("cancel")) {
                    e.setCancelled(true);
                }
                else if (DacStringBase.crit_func.equals("divide")) {
                    e.setDamage(e.getDamage() / 1.5);
                } else e.setCancelled(true);
        }
    }
}
