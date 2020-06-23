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


package bg.anticheat.modules.misc;

import org.bukkit.block.*;
import org.bukkit.event.player.*;
import org.bukkit.util.BlockIterator;
import org.bukkit.*;

import java.util.ArrayList;
import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.*;

import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.Player;

public class InvalidBlock
{
	private static ArrayList<String> hacker = new ArrayList<String>();
	
    public static void build(final BlockPlaceEvent e) {
        if (!DacStringBase.invalidblock_place) {
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
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
        	return;
        if (BlockUtils.isClimable(e.getBlock().getType())) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.InvalidBlock")) {
            return;
        }
        
 	   hacker.remove(e.getPlayer().getName());
        
 	  if(checkForHack(e.getPlayer(), e.getBlock()))
		   e.setCancelled(true);
    }
    
    public static void invt(final PlayerInteractEvent e) {
        if (!DacStringBase.invalidblock_interact) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.InvalidBlock")) {
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
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType().equals(XMaterial.ENCHANTING_TABLE.material())
        		|| e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.ENDER_CHEST)
        		|| e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST) || e.getClickedBlock().getType().equals(Material.DISPENSER)
        		|| e.getClickedBlock().getType().equals(Material.FURNACE) || e.getClickedBlock().getType().equals(XMaterial.FURNACE.material())
        		|| e.getClickedBlock().getType().name().contains("BUTTON")
        		|| e.getClickedBlock().getType().equals(Material.ITEM_FRAME) || e.getClickedBlock().getType().equals(XMaterial.CRAFTING_TABLE.material())
        		|| e.getClickedBlock().getType().equals(XMaterial.COMPARATOR.material()) || e.getClickedBlock().getType().equals(Material.JUKEBOX)
        		|| e.getClickedBlock().getType().equals(Material.ANVIL) || e.getClickedBlock().getType().name().contains("BED"))) {
            
      	   hacker.remove(e.getPlayer().getName());
             
      	 if(checkForHack(e.getPlayer(), e.getClickedBlock()))
  		   e.setCancelled(true);
        }
    }
    
    @SuppressWarnings("deprecation")
	public static void breakb(final BlockBreakEvent e) {
		        if (!DacStringBase.invalidblock_break) {
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
		        if (e.getBlock().getType().equals(Material.LADDER) || e.getBlock().getType().equals(Material.VINE)) {
		            return;
		        }
		        if (e.getPlayer().hasPermission("Dakata.Bypass.InvalidBlock")) {
		            return;
		        }
		        if (e.getPlayer().getItemInHand().containsEnchantment(Enchantment.DIG_SPEED)) {
		            return;
		        }
		        if (e.getBlock().isLiquid()) {
					   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.LIQUID, false);
	               	   Bukkit.getPluginManager().callEvent(ass);
	               	   if(!ass.isCancelled()){
		                   e.setCancelled(true);
		                   e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Please turn off Liquids hack");
		               }
	            }
		        
            	   hacker.remove(e.getPlayer().getName());
            	   
            	   if(checkForHack(e.getPlayer(), e.getBlock()))
            		   e.setCancelled(true);
    }
    
    public static boolean checkForHack(Player p, Block b) {
        BlockIterator it = new BlockIterator(p, (int) Math.ceil(p.getEyeLocation().distance(b.getLocation())));
        while(it.hasNext()) {
        	final Block blockPL = it.next();
        	if(!blockPL.isEmpty() && !blockPL.equals(b)) {
        		if(!BlockUtils.isLiquid(blockPL.getType()) && !b.getType().name().contains("BED")
        				&& !BlockUtils.isInstantBreak(blockPL.getType(), p.getItemInHand().getType())
        				&& !blockPL.getType().name().contains("SLAB") && !(blockPL.getType().name().contains("PLATE"))
        				&& !(blockPL.getType() == XMaterial.PISTON_HEAD.material()) && !(blockPL.getType().name().contains("SIGN"))
        				&& !blockPL.getType().name().contains("SEEDS") && !(blockPL.getType().name().contains("STEP"))
        				&& !blockPL.getType().name().contains("HEAD") && !(blockPL.getType() == Material.BREWING_STAND)
        				&& !(blockPL.getType() == Material.LEVER) && !(blockPL.getType() == Material.ANVIL)
        				&& !(blockPL.getType() == XMaterial.IRON_BARS.material())) {
        			if(!hacker.contains(p.getName()))
        				hacker.add(p.getName());
        		}
        	} else if(blockPL.equals(b)) {
    			if(hacker.contains(p.getName())) {
 				   PlayerCheatEvent ass = new PlayerCheatEvent(p, CheatType.INVALIDBLOCK_BREAK, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()) {
                       return true;
                   }
               	   hacker.remove(p.getName());
               	   return false;
    			}
        	}
        }
        
        return false;
    }
}
