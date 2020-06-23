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

import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.utils.DacStringBase;

public class InvalidMove2 {
	private static HashMap<String, Double> prMove = new HashMap<String, Double>();
	private static HashMap<String, Short> vl = new HashMap<String, Short>();
	public static void ss(PlayerMoveEvent e)
	{
		//checks
//		if(e.getTo().getY() > e.getFrom().getY()){
//			if(e.getPlayer().getVelocity().getY() < 0){
//				if(vl.containsKey(e.getPlayer().getName())){
//					vl.put(e.getPlayer().getName(), vl.get(e.getPlayer().getName()+1));
//					if(vl.get(e.getPlayer().getName()) >= 3){
//						e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "CheckerClimb"));
//					}
//				} else vl.put(e.getPlayer().getName(), (short) 1);
//			} else vl.remove(e.getPlayer().getName());
//		}
		if(e.getTo().getY() > e.getFrom().getY()){
			if(prMove.containsKey(e.getPlayer().getName())){
				if(e.getPlayer().getVelocity().getY() >= prMove.get(e.getPlayer().getName())){
					if(vl.containsKey(e.getPlayer().getName())){
						vl.put(e.getPlayer().getName(), (short) (vl.get(e.getPlayer().getName())+1));
						if(vl.get(e.getPlayer().getName()) >= 2){
							vl.remove(e.getPlayer().getName());
							e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "CheckerClimb"));
						}
					} else vl.put(e.getPlayer().getName(), (short) 1);
				} else {
					prMove.remove(e.getPlayer().getName());
					prMove.put(e.getPlayer().getName(), e.getPlayer().getVelocity().getY());
				}
			} else prMove.put(e.getPlayer().getName(), e.getPlayer().getVelocity().getY());
		} else{
			vl.remove(e.getPlayer().getName());
			prMove.remove(e.getPlayer().getName());
		}
	}
}
