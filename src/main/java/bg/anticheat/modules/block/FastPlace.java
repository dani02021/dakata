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


package bg.anticheat.modules.block;

import org.bukkit.event.block.*;
import org.bukkit.*;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.*;
import java.util.*;

public class FastPlace
{
    private static HashMap<String, Long> placeblock;
    private static ArrayList<String> placeblock1;
    
    static {
        FastPlace.placeblock = new HashMap<String, Long>();
        FastPlace.placeblock1 = new ArrayList<String>();
    }
    
    public static void place(final BlockPlaceEvent e) {
        if (!DacStringBase.fastplace_protection) {
            return;
        }
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if(placeblock1.contains(e.getPlayer().getName())){
        	e.setCancelled(true);
        	return;
        }
        if(BlockUtils.isInstantBreak(e.getBlock().getType(), e.getItemInHand().getType()) || (e.getPlayer().getItemInHand().getType().name().contains("SEEDS")
          		 || e.getPlayer().getItemInHand().getType() == Material.CARROT
           		 || e.getPlayer().getItemInHand().getType() == Material.POTATO
           		 || e.getPlayer().getItemInHand().getType() == Material.MELON_SEEDS
           		 || e.getPlayer().getItemInHand().getType() == Material.PUMPKIN_SEEDS))
        	return;
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
        if (e.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL) || e.getItemInHand().getType() == XMaterial.INK_SAC.material()) {
            return;
        }
        if (FastPlace.placeblock.get(e.getPlayer().getName()) == null) {
        	placeblock.put(e.getPlayer().getName(), System.currentTimeMillis());
        } else {
    		Hackers.addFastPlace(e.getPlayer());
    		
        	if((System.currentTimeMillis()-placeblock.get(e.getPlayer().getName())) < 80){
        		Hackers.addFastPlace(e.getPlayer());
        		
        		if(Hackers.getFastPlaceList(e.getPlayer()).get(e.getPlayer().getName()) > 6){
        			placeblock1.add(e.getPlayer().getName());
        			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
						
						@Override
						public void run() {
							placeblock1.remove(e.getPlayer().getName());
							
						}
					}, 30L);
        		}
        	} else {
        		Hackers.getFastPlaceList(e.getPlayer()).put(e.getPlayer().getName(), (short) (Hackers.getFastPlaceList(e.getPlayer()).get(e.getPlayer().getName())-1));
        	}
        	placeblock.put(e.getPlayer().getName(), System.currentTimeMillis());
        }
    }
}