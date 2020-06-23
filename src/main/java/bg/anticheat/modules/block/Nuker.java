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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.nossr50.api.AbilityAPI;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.BlockUtils;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.XMaterial;

public class Nuker
{
    private static HashMap<String, Short> breakBlock;
    private static ArrayList<String> breakBlock1;
    
    static {
        Nuker.breakBlock = new HashMap<String, Short>();
        Nuker.breakBlock1 = new ArrayList<String>();
    }
    
    public static void nuk(final BlockBreakEvent e) {
        if (!DacStringBase.nuker_protection) {
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
        if (Main.isUsingMcMMO()) {
            if (AbilityAPI.berserkEnabled(e.getPlayer())) {
                return;
            }
            if (AbilityAPI.superBreakerEnabled(e.getPlayer())) {
                return;
            }
        }
        if(e.getPlayer().getGameMode()!= GameMode.CREATIVE)
        	return;
        if (e.getPlayer().hasPermission("Dakata.Bypass.Nuker")) {
            return;
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.CreativeNuker")) {
            return;
        }
        if (e.getPlayer().getItemInHand().containsEnchantment(Enchantment.DIG_SPEED)) {
            return;
        }
        if (BlockUtils.isInstantBreak(e.getBlock().getType(), e.getPlayer().getItemInHand().getType()) || (e.getPlayer().getItemInHand().getType().name().contains("SEEDS")
          		 || e.getPlayer().getItemInHand().getType() == Material.CARROT
           		 || e.getPlayer().getItemInHand().getType() == Material.POTATO
           		 || e.getPlayer().getItemInHand().getType() == Material.MELON_SEEDS
           		 || e.getPlayer().getItemInHand().getType() == Material.PUMPKIN_SEEDS && e.getBlock().getType() == XMaterial.FARMLAND.material())) {
            return;
        }
        final Iterator<PotionEffect> iterator = e.getPlayer().getActivePotionEffects().iterator();
        if (iterator.hasNext()) {
            final PotionEffect pf = iterator.next();
            if (pf.getType().equals(PotionEffectType.FAST_DIGGING)) {
                return;
            }
        }
        	if(!Nuker.breakBlock.containsKey(e.getPlayer().getName()))
        	   Nuker.breakBlock.put(e.getPlayer().getName(), (short) 1);
        	else Nuker.breakBlock.put(e.getPlayer().getName(), (short) (Nuker.breakBlock.get(e.getPlayer().getName())+1));
            if(!Nuker.breakBlock1.contains(e.getPlayer().getName())) {
            	breakBlock1.add(e.getPlayer().getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                	//e.getPlayer().sendMessage(""+Nuker.breakBlock.get(e.getPlayer().getName()));
                	
                    Nuker.breakBlock.remove(e.getPlayer().getName());
                    breakBlock1.remove(e.getPlayer().getName());
                }
            }, Settings.min_breake_blocks_time);
            
            }
            
    		if(Nuker.breakBlock.get(e.getPlayer().getName()) > 5) { //Check the time
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.NUKER, false);
            	   Bukkit.getPluginManager().callEvent(ass);
            	   if(!ass.isCancelled()) {
            		   Logger.addMessageToFileLog(e.getPlayer(), "Nuker");
             //if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(Messages.broadcast(e.getPlayer(), "CreativeNuker"));
                       e.setCancelled(true);
                   }
                Nuker.breakBlock.remove(e.getPlayer().getName());
     		}
    }
    
    public static void clear() {
        Nuker.breakBlock.clear();
    }
}
