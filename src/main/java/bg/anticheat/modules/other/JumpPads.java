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


package bg.anticheat.modules.other;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerMoveEvent;

public class JumpPads {
	public static HashMap<String, Short> disable = new HashMap<String, Short>();
	public static ArrayList<String> disableSpeed = new ArrayList<String>();
	public static void a(PlayerMoveEvent e)
	{
		if(e.getFrom().getBlock().getType().name().contains("PLATE") && e.getTo().clone().subtract(0,0.1,0).getBlock().isEmpty()) {
			if(!disableSpeed.contains(e.getPlayer().getName()))
			    disableSpeed.add(e.getPlayer().getName());
		} else if(!e.getFrom().getBlock().getType().name().contains("PLATE") &&
				!e.getTo().getBlock().getType().name().contains("PLATE") && 
				!e.getFrom().getBlock().getRelative(BlockFace.DOWN).isEmpty() && !e.getTo().getBlock().getRelative(BlockFace.DOWN).isEmpty()) {
			if(disableSpeed.contains(e.getPlayer().getName()))
				disable.put(e.getPlayer().getName(), (short) 25);
			disableSpeed.remove(e.getPlayer().getName());
		}
		
		if(!disable.containsKey(e.getPlayer().getName())) {
			if(e.getFrom().getBlock().getType().name().contains("PLATE") && e.getTo().clone().subtract(0,0.1,0).getBlock().isEmpty()){
				disable.put(e.getPlayer().getName(), (short) 15);
			}
		} else {
			if(disable.get(e.getPlayer().getName()) != 0)
				disable.put(e.getPlayer().getName(), (short) (disable.get(e.getPlayer().getName())-1));
			else disable.remove(e.getPlayer().getName());
		}
	}
}
