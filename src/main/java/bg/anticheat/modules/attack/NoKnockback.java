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

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.InventoryBuilder;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.Messages;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import bg.anticheat.utils.XMaterial;
import me.clip.placeholderapi.PlaceholderAPI;

public class NoKnockback
{
    private static HashMap<String, Short> times;
    private static HashMap<String, Double> times1;
    
    static {
        NoKnockback.times = new HashMap<String, Short>();
        NoKnockback.times1 = new HashMap<String, Double>();
    }
    
    public static void c(final EntityDamageByEntityEvent e) {
        if (!DacStringBase.noknockback_protection) {
            return;
        }
        if (e.getEntity().hasPermission("Dakata.Bypass.NoKnockback")) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if(PlayerUtils.isNextToBlock(e.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), XMaterial.COBWEB.material()))
        	return;
        if(((Player)e.getEntity()).getFireTicks() > 0)
        	return;
        if(((Player)e.getEntity()).getEyeLocation().getBlock().getRelative(BlockFace.UP).getType() != Material.AIR)
        	return;
        if(((Player)e.getEntity()).getEyeLocation().getBlock().getRelative(BlockFace.SOUTH).getType() != Material.AIR)
        	return;
        if(((Player)e.getEntity()).getEyeLocation().getBlock().getRelative(BlockFace.NORTH).getType() != Material.AIR)
        	return;
        if(((Player)e.getEntity()).getEyeLocation().getBlock().getRelative(BlockFace.EAST).getType() != Material.AIR)
        	return;
        if(((Player)e.getEntity()).getEyeLocation().getBlock().getRelative(BlockFace.WEST).getType() != Material.AIR)
        	return;
        if (PlayerUtils.isInLiquidLoc(e.getEntity().getLocation()) || e.getEntity().isDead() || PlayerUtils.isInWeb(e.getEntity().getLocation()) || PlayerUtils.isInWeb(e.getEntity().getLocation().subtract(0,1,0))) {
            return;
        }
        if (InventoryBuilder.isFreezed((Player)e.getEntity())) {
            return;
        }
        if(((Player)e.getEntity()).isInsideVehicle())
        	return;
        
		if (Main.getThisPlugin().getServer().getPluginManager().isPluginEnabled("Residence")) {
			final ResidenceManager rm = Residence.getInstance().getResidenceManager();
			if (rm.getByLoc(e.getEntity().getLocation()) != null) {
				if (rm.getByLoc(e.getEntity().getLocation()).getPermissions().has("pvp", false)) {
					return;
				}
			}
		}
		
		 if (Main.getThisPlugin().getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
				try{
				if (((WorldGuardPlugin) Main.getThisPlugin().getServer().getPluginManager().getPlugin("WorldGuard"))
						.getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())
						!= null) {
					if(((WorldGuardPlugin) Main.getThisPlugin().getServer().getPluginManager().getPlugin("WorldGuard"))
							.getRegionManager(e.getEntity().getWorld()).getApplicableRegions(e.getEntity().getLocation())
							.queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY)
					return;
				}
				}catch(IncompatibleClassChangeError err){
					
				}
			}
		
        if(e.isCancelled())
        	return;
            times1.put(e.getEntity().getName(), e.getEntity().getLocation().getY());
            Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    if (e.getEntity().getLocation().getY()-times1.get(e.getEntity().getName()) <= Settings.min_noknockback_distance && e.getEntity().getLocation().getY()-times1.get(e.getEntity().getName()) >= 0) {
                        if (NoKnockback.times.containsKey(e.getEntity().getName())) {
                            short i = NoKnockback.times.get(e.getEntity().getName());
                            ++i;
                            NoKnockback.times.put(e.getEntity().getName(), i);
                        }
                        else {
                            NoKnockback.times.put(e.getEntity().getName(), (short)1);
                        }
                        if (NoKnockback.times.get(e.getEntity().getName()) == null) {
                            return;
                        }
                        if (NoKnockback.times.get(e.getEntity().getName()) > Settings.min_noknockback_moves) {
         				   PlayerCheatEvent ass = new PlayerCheatEvent((Player)e.getEntity(), CheatType.NOKNOCKBACK, false);
                       	   Bukkit.getPluginManager().callEvent(ass);
                       	   if(!ass.isCancelled()){
                       		   Hackers.addNoKnockback((Player) e.getEntity());
                       		   if(Hackers.isReadyForNoKnockbackMessage((Player) e.getEntity())) {
                       			Logger.addMessageToFileLog((Player) e.getEntity(), "NoKnockback");
                                   if (DacStringBase.log_console) {
                                       try {
                                           Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "NoKnockback").replaceAll("<player>", ((Player)e.getEntity()).getName()).replaceAll("<world>", ((Player)e.getEntity()).getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
                                       }
                                       catch (Exception e3) {
                                           e3.printStackTrace();
                                       }
                                   }
                                   if (DacStringBase.log_player) {
                                	   if(Main.isUsingPlaceholderAPI())
   										try {
   											Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getEntity(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity())))));
   										} catch (Exception e2) {
   											// TODO Auto-generated catch block
   											e2.printStackTrace();
   										}
   									else
   										try {
   											Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getEntity().getName()).replaceAll("<world>", e.getEntity().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getEntity()))));
   										} catch (Exception e1) {
   											// TODO Auto-generated catch block
   											e1.printStackTrace();
   										}
                                       for (final Player p : Bukkit.getOnlinePlayers()) {
                                           if (p.hasPermission("Dakata.Admin")) {
                                               try {
                                                   p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "NoKnockback").replaceAll("<player>", ((Player)e.getEntity()).getName()).replaceAll("<world>", ((Player)e.getEntity()).getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player)e.getEntity()))));
                                               }
                                               catch (Exception e4) {
                                                   e4.printStackTrace();
                                               }
                                           }
                                       }
                                   }
                       		   }
                       	    }
                       	   NoKnockback.times.remove(e.getEntity().getName());
                        }
                    }
                    else {
                        NoKnockback.times.remove(e.getEntity().getName());
                    }//
                }
            }, 8L);
    }
}
