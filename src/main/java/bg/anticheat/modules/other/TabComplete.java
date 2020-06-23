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

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.DacStringBase;

public class TabComplete {
	public static void a()
	{
		Main.protocolManager.addPacketListener(
				  new PacketAdapter(Main.getThisPlugin(), ListenerPriority.NORMAL, PacketType.Play.Server.TAB_COMPLETE) {
				    @Override
				    public void onPacketSending(PacketEvent event) {
				        if (event.getPacketType() == PacketType.Play.Server.TAB_COMPLETE) {
				           if (!(event.getPlayer().hasPermission("Dakata.Bypass.TabComplete"))){
				        	   event.setCancelled(true);
				        	   event.getPlayer().sendMessage(DacStringBase.tabcomplete_message);
				           }
				        }
				    }
				});
	}
}
