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

import org.bukkit.Bukkit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Messages;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;

public class BadPackets {
	public static HashMap<String, Short> a = new HashMap<String, Short>();
	public static ArrayList<String> as = new ArrayList<String>();
	public static ArrayList<String> dont = new ArrayList<String>();
	public static void d()
	{
		Main.protocolManager.addPacketListener(
				  new PacketAdapter(Main.getThisPlugin(), ListenerPriority.NORMAL, 
				          PacketType.Play.Client.FLYING) {
				    @Override
				    public void onPacketReceiving(final PacketEvent event) {
				    	try {
							if (DacStringBase.max_player_ping != -1) {
								try {
									if (Ping.getPlayerPing(event.getPlayer()) > DacStringBase.max_player_ping) {
										return;
									}
								} catch (Exception e1) {
								}
							}
							if(dont.contains(event.getPlayer().getName()))
								return;
							if(Main.dontCheckOnSpawn.contains(event.getPlayer().getName()))
								return;
					    	if(!event.getPlayer().isOnline())
					    		return;
					    	if(event.getPlayer().hasPermission("Dakata.Bypass.BadPackets"))
					    		return;
					    	if(a.containsKey(event.getPlayer().getName()))
					    	{
					    		short aa = 0;
								if(a.get(event.getPlayer().getName()) == null)
									return;
					    		aa = a.get(event.getPlayer().getName());
					    		aa++;
					    		a.put(event.getPlayer().getName(), aa);
					    	} else a.put(event.getPlayer().getName(), (short) 1);
					    	if(!as.contains(event.getPlayer().getName())){
					    		as.add(event.getPlayer().getName());
						    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
									
									@Override
									public void run() {
										if(a.get(event.getPlayer().getName()) != null)
										if(a.get(event.getPlayer().getName()) >= 120){
											event.setCancelled(true);
											event.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.kick_mesaj.replaceAll("<hack>", "BadPackets"));
											if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(Messages.broadcast(event.getPlayer(), "BadPackets"));
										}
										a.remove(event.getPlayer().getName());
										as.remove(event.getPlayer().getName());
									}
								}, 3L);
					    	}
				    	} catch(NullPointerException e)
				    	{
				    		
				    	}
				    }
				});
	}
}
