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
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.BlockIterator;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class InvalidEntity {
	public static void a(EntityDamageByEntityEvent e)
	{
		if(!(e.getDamager() instanceof Player))
			return;
		if(!DacStringBase.killaura_protection)
			return;
		if(DacStringBase.killaurathrublocks_playersonly)
			if(!(e.getEntity() instanceof Player))
				return;
		if(((Player)e.getDamager()).isBlocking())
			e.setCancelled(true);
		if(e.getDamager().getLocation().distance(e.getEntity().getLocation()) < 2.0)
			return;
        if (DacStringBase.max_player_ping != -1) {
            try {
                if (Ping.getPlayerPing((Player) e.getDamager()) > DacStringBase.max_player_ping) {
                    return;
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		/*if(e.getDamager().hasPermission("Dakata.Bypass.KillAura"))
			return;
			e.getDamager().sendMessage(("1 different: "+(((Math.max(e.getDamager().getLocation().getPitch()+e.getDamager().getLocation().getYaw(),
        					(PlayerUtils.getRotationsNeeded((Player) e.getDamager(), e.getEntity())[0]+PlayerUtils.getRotationsNeeded((Player) e.getDamager(),
        							e.getEntity())[1]))))

        					-(Math.min(e.getDamager().getLocation().getPitch()+e.getDamager().getLocation().getYaw()
        							, (PlayerUtils.getRotationsNeeded((Player) e.getDamager(),
        									e.getEntity())[0]+PlayerUtils.getRotationsNeeded((Player) e.getDamager(),
        											e.getEntity())[1]))))));*/
		BlockIterator a = new BlockIterator((LivingEntity) e.getDamager(), (int)Math.ceil(e.getDamager().getLocation().distance(e.getEntity().getLocation())));
		while(a.hasNext())
		{
			Block as = a.next();
			if(!as.isEmpty())
               if (!(as.getType() == Material.LEVER) && !(as.getType() == Material.FENCE) && !(as.getType() == Material.REDSTONE) &&
            		   !(as.getType() == Material.FENCE_GATE) && !(as.getType() == Material.TRAP_DOOR) &&
            		   !(as.getType() == Material.STONE_BUTTON) && !(as.getType() == Material.WOOD_BUTTON) &&
            		   !(as.getType() == Material.STEP) && !(as.getType() == Material.WOOD_STEP) &&
            		   !(as.getType() == Material.PISTON_MOVING_PIECE) && !(as.getType() == Material.LADDER) &&
            		   !(as.getType() == Material.FLOWER_POT) && !(as.getType() == Material.SNOW) && !(as.getType() == Material.SIGN) &&
            		   !(as.getType() == Material.SIGN_POST) && !(as.getType() == Material.WALL_SIGN) &&
            		   !(as.getType() == Material.TRIPWIRE_HOOK) && !(as.getType() == Material.TRIPWIRE) &&
            		   !(as.getType() == Material.TORCH) && !(as.getType() == Material.REDSTONE_TORCH_OFF) &&
            		   !(as.getType() == Material.REDSTONE_TORCH_ON) && !(as.getType() == Material.RAILS) &&
            		   !(as.getType() == Material.ACTIVATOR_RAIL) && !(as.getType() == Material.DETECTOR_RAIL) &&
            		   !(as.getType() == Material.POWERED_RAIL) && !(as.getType() == Material.GOLD_PLATE) &&
            		   !(as.getType() == Material.WOOD_PLATE) && !(as.getType() == Material.IRON_PLATE) &&
            		   !(as.getType() == Material.STONE_PLATE) && !(as.getType() == Material.REDSTONE_WIRE) &&
            		   !(as.getType() == Material.BANNER) && !(as.isLiquid()) && !(as.getType() == Material.GRASS) &&
            		   !(as.getType() == Material.BROWN_MUSHROOM) && !(as.getType() == Material.RED_MUSHROOM) &&
            		   !(as.isLiquid() && !(as.getType() == Material.LONG_GRASS))) {
            	   
           		   Hackers.addKillAuraThruBlocks((Player) e.getDamager());
        		   if(Hackers.isReadyForKillAuraThruBlocksMessage((Player) e.getDamager()))
        		   {
        			   if(Hackers.getKillAuraThruBlocksViolationLevel((Player) e.getDamager()) >= 5){
        				   PlayerCheatEvent ass = new PlayerCheatEvent((Player)e.getDamager(), CheatType.KILLAURA, false);
                       	   Bukkit.getPluginManager().callEvent(ass);
                       	   if(!ass.isCancelled()) {
                       		Logger.addMessageToFileLog((Player) e.getDamager(), "InvalidEntity");
                               if (DacStringBase.log_console) {
                                   try {
                                	   if(Main.isUsingPlaceholderAPI())
                                           Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                                	   else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
                                   }
                                   catch (Exception e2) {
                                       e2.printStackTrace();
                                   }
                               }
                               if (DacStringBase.log_player) {
                                   for (final Player p2 : Bukkit.getOnlinePlayers()) {
                                       if (p2.hasPermission("Dakata.Admin")) {
                                           try {
                                        	   if(Main.isUsingPlaceholderAPI())
                                                  p2.sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                                        	   else p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
                                           }
                                           catch (Exception e3) {
                                               e3.printStackTrace();
                                           }
                                       }
                                   }
                               }
               			    e.setCancelled(true);
                       	   }
        			   }
        		   }
            }
			if(as.getWorld().getNearbyEntities(as.getLocation(), 1, 1, 1).contains(e.getEntity()))
				return;
			if(as.isLiquid())
				return;
		}
		Hackers.addKillAuraThruBlocks((Player) e.getDamager());
		if(Hackers.isReadyForKillAuraThruBlocksMessage((Player) e.getDamager()))
		{
			if(Hackers.getKillAuraThruBlocksViolationLevel((Player) e.getDamager()) >= 5){
				   PlayerCheatEvent ass = new PlayerCheatEvent((Player)e.getDamager(), CheatType.KILLAURA, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
                if (DacStringBase.log_console) {
                    try {
                 	   if(Main.isUsingPlaceholderAPI())
                           Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                	   else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (DacStringBase.log_player) {
                    for (final Player p2 : Bukkit.getOnlinePlayers()) {
                        if (p2.hasPermission("Dakata.Admin")) {
                            try {
                         	   if(Main.isUsingPlaceholderAPI())
                                   p2.sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                         	   else p2.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidEntity").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
                e.setCancelled(true);
               	   }
               	   }
		}
	}
}
