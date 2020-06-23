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

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.Settings;

public class SprintBackwards {
	public static HashMap<String, Short> vl = new HashMap<String, Short>();
	public static HashMap<String, Location> setBack = new HashMap<String, Location>();
	public static void onMove(PlayerMoveEvent e) {
		if(PlayerUtils.isInWeb(e.getPlayer().getLocation()) || PlayerUtils.isInWeb(e.getPlayer().getEyeLocation()) ||
				e.getPlayer().getLocation().getBlock().getType().equals(Material.LADDER) ||
				e.getPlayer().getLocation().getBlock().getType().equals(Material.LADDER))
			return;
		if(PlayerUtils.isInLiquidLoc(e.getPlayer().getLocation()))
			return;
		if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL) && !e.getPlayer().isFlying() && !PlayerUtils.isInLiquidLoc(e.getPlayer().getLocation()) &&
				!Speed.damagelist2.containsKey(e.getPlayer().getName())) {
			if(e.getPlayer().isSprinting()) {
				if(Settings.sprintbackwards_mode == 1) {
		    		if(e.getPlayer().getLocation().getPitch() >= 0) {
		    			if(e.getPlayer().getLocation().getPitch() < 75) {
		    				if(PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()) >= 130)
		    				{
		    					if(vl.containsKey(e.getPlayer().getName())) {
		    						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
		    					} else {
		    						setBack.put(e.getPlayer().getName(), e.getFrom());
		    						vl.put(e.getPlayer().getName(), (short) 1);
		    					}
		    					if(vl.get(e.getPlayer().getName()) >= 15) {
		    						e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
		    						vl.remove(e.getPlayer().getName());
		    						setBack.remove(e.getPlayer().getName());
		    					}
		    				} else {
		    					vl.remove(e.getPlayer().getName());
		    					setBack.remove(e.getPlayer().getName());
		    				}
		    	        	} else if(PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()) >= 200) {
		    					if(vl.containsKey(e.getPlayer().getName())) {
		    						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
		    					} else {
		    						setBack.put(e.getPlayer().getName(), e.getFrom());
		    						vl.put(e.getPlayer().getName(), (short) 1);
		    					}
		    					if(vl.get(e.getPlayer().getName()) >= 12) {
		    						e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
		    						vl.remove(e.getPlayer().getName());
		    						setBack.remove(e.getPlayer().getName());
		    					}
		    	        	}
		    			} else {
		    		       	if((e.getPlayer().getLocation().getPitch() > -75)) {
		    		       		if(PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()) >= 130)
			    				{
			    					if(vl.containsKey(e.getPlayer().getName())) {
			    						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
			    					} else {
			    						setBack.put(e.getPlayer().getName(), e.getFrom());
			    						vl.put(e.getPlayer().getName(), (short) 1);
			    					}
			    					if(vl.get(e.getPlayer().getName()) >= 12) {
			    						e.getPlayer().sendMessage("H2");
			    						e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
			    						vl.remove(e.getPlayer().getName());
			    						setBack.remove(e.getPlayer().getName());
			    					}
			    				} else {
			    					vl.remove(e.getPlayer().getName());
			    					setBack.remove(e.getPlayer().getName());
			    				}
		    		       	} else if(PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()) >= 200) {
		    					if(vl.containsKey(e.getPlayer().getName())) {
		    						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
		    					} else {
		    						setBack.put(e.getPlayer().getName(), e.getFrom());
		    						vl.put(e.getPlayer().getName(), (short) 1);
		    					}
		    					if(vl.get(e.getPlayer().getName()) >= 12) {
		    						e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
		    						vl.remove(e.getPlayer().getName());
		    						setBack.remove(e.getPlayer().getName());
		    					}
		    	        	}
		    			}
				} else {
					//e.getPlayer().sendMessage(""+PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()));
		    		       		if(PlayerUtils.getAngle(e.getTo(), e.getFrom(), e.getPlayer().getLocation()) >= 200)
			    				{
			    					if(vl.containsKey(e.getPlayer().getName())){
			    						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
			    					} else {
			    						setBack.put(e.getPlayer().getName(), e.getFrom());
			    						vl.put(e.getPlayer().getName(), (short) 1);
			    					}
			    					if(vl.get(e.getPlayer().getName()) >= 12){
			    						e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
			    						vl.remove(e.getPlayer().getName());
			    						setBack.remove(e.getPlayer().getName());
			    					}
			    				} else {
		    					vl.remove(e.getPlayer().getName());
		    					setBack.remove(e.getPlayer().getName());
		    				    }
				}
			}
		}
	}
}
