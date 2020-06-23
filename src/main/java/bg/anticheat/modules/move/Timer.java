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

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;

public class Timer {
	private static HashMap<String, Short> hackers = new HashMap<String, Short>();
	private static ArrayList<String> hackers1 = new ArrayList<String>();
	private static HashMap<String, Short> hackers2 = new HashMap<String, Short>();
	public static void a(final PlayerMoveEvent e)
	{
		if (DacStringBase.max_player_ping != -1) {
			try {
				if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
					return;
				}
			} catch (Exception e1) {
			}
		}
		                if(!DacStringBase.timer_protection)
		                	return;
				    	if(!e.getPlayer().isOnline() || e.getPlayer().isFlying())
				    		return;
				    	if(e.getPlayer().hasPermission("Dakata.Bypass.Timer"))
				    		return;
				    	if(!hackers.containsKey(e.getPlayer().getName()))
					       hackers.put(e.getPlayer().getName(), (short)1);
				    	else{
				    		short sh = hackers.get(e.getPlayer().getName());
				    		sh++;
				    		hackers.put(e.getPlayer().getName(), sh);
				    	}
					    if(!hackers1.contains(e.getPlayer().getName())){
					    	hackers1.add(e.getPlayer().getName());
					    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
							@Override
							public void run() {
							    if(hackers.get(e.getPlayer().getName()) > Settings.max_packets){
							    	if(hackers.get(e.getPlayer().getName()) > 150){
						    			Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.TIMER, true));
						    			e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "Timer"));
						    			if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(DacStringBase.anticheat_tag+DacStringBase.broadcast.replaceAll("<hack>", "Timer").replaceAll("<player>", e.getPlayer().getName()));
									    hackers.remove(e.getPlayer().getName());
									    hackers1.remove(e.getPlayer().getName());
									    hackers2.remove(e.getPlayer().getName());
							    	}
							        if(!hackers2.containsKey(e.getPlayer().getName()))
							    	   hackers2.put(e.getPlayer().getName(), (short)1);
							    	else{
							    		hackers2.put(e.getPlayer().getName(), (short) (hackers2.get(e.getPlayer().getName())+1));
							    		if(hackers2.get(e.getPlayer().getName())+1 >= 2){
							    			Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.TIMER, true));
							    			e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "Timer"));
							    			if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(DacStringBase.anticheat_tag+DacStringBase.broadcast.replaceAll("<hack>", "Timer").replaceAll("<player>", e.getPlayer().getName()));
										    hackers.remove(e.getPlayer().getName());
										    hackers1.remove(e.getPlayer().getName());
										    hackers2.remove(e.getPlayer().getName());
							    		}
							    	}
							    } else{
							    	if(hackers2.containsKey(e.getPlayer().getName()))
							    		hackers2.put(e.getPlayer().getName(), (short) (hackers2.get(e.getPlayer().getName())-1));
							    }
							    hackers.remove(e.getPlayer().getName());
							    hackers1.remove(e.getPlayer().getName());
							}
						}, Settings.packets_time);}
}
}
