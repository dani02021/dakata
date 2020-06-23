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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.PlayerUtils;

public class IllegalMove {
	private static HashMap<String, Short> violation = new HashMap<String, Short>();
	private static HashMap<String, Short> violation1 = new HashMap<String, Short>();
	public static void s(PlayerMoveEvent e)
	{
		if(!DacStringBase.illegalmove_protection)
			return;
		if(e.getPlayer().isFlying())
			return;
		if(e.getPlayer().getFireTicks() > 0)
			return;
		if(Main.isUsingNoCheatPlus())
			return;
		if(e.getPlayer().hasPermission("Dakata.Bypass.IllegalMove"))
			return;
    	try{
        	if(Main.getThisPlugin().getServer().getPluginManager().isPluginEnabled("WorldGuard"))
        	{
        		if(((WorldGuardPlugin)Main.getThisPlugin().getServer().getPluginManager().getPlugin("WorldGuard")).getRegionManager(e.getPlayer().getWorld()).getApplicableRegions(e.getPlayer().getLocation()).queryState(null, DefaultFlag.ENTRY) == StateFlag.State.DENY)
        			return;
        	}
    	} catch(IncompatibleClassChangeError esas){ }
    	if(PlayerUtils.isOnHalfSlab(e.getPlayer()) || PlayerUtils.isOnStair(e.getPlayer()))
    		return;
		//if(PlayerUtils.isNextToBlockDifferent(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.AIR))
		//	return;
		if(!e.getPlayer().getLocation().getBlock().isEmpty() &&
				!e.getPlayer().getLocation().getBlock().isLiquid())
			return;
		if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().name().contains("SHULKER"))
			return;
    	if(e.getPlayer().isInsideVehicle()){
    		violation.remove(e.getPlayer().getName());
    		return;
    		}
    	//e.getPlayer().sendMessage("fDist: "+e.getPlayer().getFallDistance()+" "+(e.getFrom().getY()-e.getTo().getY()));
		if((e.getFrom().getY()-e.getTo().getY() > 0.1 && e.getPlayer().getFallDistance() == 0)
				|| (e.getTo().getY()-e.getFrom().getY() > 0.1 && e.getPlayer().isOnGround() && !e.getPlayer().getLocation().getBlock().isLiquid() && !PlayerUtils.isOnLadder(e.getPlayer()))
				&& e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()
				&& !PlayerUtils.isNextToBlock2Different(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.AIR, 0.3)){
			Hackers.addIllegalMove(e.getPlayer());
			//e.getPlayer().sendMessage("vl: "+Hackers.getIllegalMoveViolation(e.getPlayer()));
			if(Hackers.getIllegalMoveViolation(e.getPlayer()) >= 5){
				if(Hackers.isReadyForIllegalMoveMessage(e.getPlayer())){
					PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.ILLEGAL_MOVE, true);
					Bukkit.getPluginManager().callEvent(ass);
					if(!ass.isCancelled()){
						e.getPlayer().teleport(PlayerUtils.getLastOnGroundLocation(e.getPlayer()));
						e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "Illegal Move"));
						if(DacStringBase.broadcast_kick)
							Bukkit.broadcastMessage(DacStringBase.anticheat_tag+DacStringBase.broadcast.replaceAll("<player>", e.getPlayer().getName()).replaceAll("<hack>", "Illegal Move"));
					}
				}
			}
		} else if(((e.getTo().getY() == e.getFrom().getY() || !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()) && e.getPlayer().isOnGround()) ||
				(e.getFrom().getY() > e.getTo().getY() && e.getPlayer().getFallDistance() > 0)){
			if(!violation1.containsKey(e.getPlayer().getName()))
				violation1.put(e.getPlayer().getName(), (short) 1);
			else violation1.put(e.getPlayer().getName(), (short) (violation1.get(e.getPlayer().getName())+1));
			if(violation1.get(e.getPlayer().getName()) >= 7){
				violation1.remove(e.getPlayer().getName());
				Hackers.getIllegalMoveList(e.getPlayer()).remove(e.getPlayer().getName());
			}
			
			}
	}
}
