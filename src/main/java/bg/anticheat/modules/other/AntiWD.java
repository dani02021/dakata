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

import org.apache.commons.lang3.StringUtils;

import com.comphenix.protocol.events.PacketEvent;

import bg.anticheat.dakata.Main;
import bg.anticheat.utils.DacStringBase;

public class AntiWD {
	public static void onCheck(PacketEvent event) {
		try {
			if (StringUtils.containsIgnoreCase(event.getPacket().getStrings().getValues().get(0), "WDL"))
				if (event.getPlayer().isOnline())
					Main.getThisPlugin().getServer().getScheduler().runTask(Main.getThisPlugin(),
							() -> Main.getThisPlugin().getServer().dispatchCommand(
									Main.getThisPlugin().getServer().getConsoleSender(),
									DacStringBase.world_download_command.replaceAll("<player>",
											event.getPlayer().getName())));
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}
}
