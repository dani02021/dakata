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
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.DacStringBase;

public class Sneak {
	private static HashMap<String, Short> hack1 = new HashMap<String, Short>();
	private static ArrayList<String> hack2 = new ArrayList<String>();
	public static void an(final PlayerToggleSneakEvent e)
	{
		if(!e.isSneaking())
			return;
		if(e.getPlayer().hasPermission("Dakata.Bypass.PacketSneak"))
			return;
		//CHECKS
		if(!hack1.containsKey(e.getPlayer().getName()))
			hack1.put(e.getPlayer().getName(), (short) 1);
		else
		{
			hack1.put(e.getPlayer().getName(), (short) (hack1.get(e.getPlayer().getName())+1));
		}
		if(!hack2.contains(e.getPlayer().getName())) {
			hack2.add(e.getPlayer().getName());
			
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(hack1.get(e.getPlayer().getName()) > 20)
					{
						PlayerCheatEvent c = new PlayerCheatEvent(e.getPlayer(),CheatType.PACKET_SNEAK, true);
						Bukkit.getPluginManager().callEvent(c);
						if(c.isCancelled()) {
							e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "Packet Sneak"));
							if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(DacStringBase.anticheat_tag+DacStringBase.broadcast.replaceAll("<hack>", "Packet Sneak").replaceAll("<player>", e.getPlayer().getName()));
						}
					}
					hack2.remove(e.getPlayer().getName());
					hack1.remove(e.getPlayer().getName());
				}
			}, 1000);
		}
	}
}
