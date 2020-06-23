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


package bg.anticheat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ping {
	public static int getPlayerPing(final Player player)throws Exception {
		try {
			int ping = 0;
			final Class<?> craftPlayer = Class.forName(String.valueOf(getServerVersion()) + ".entity.CraftPlayer");
			final Object converted = craftPlayer.cast(player);
			final Method handle = converted.getClass().getMethod("getHandle", new Class[0]);
			final Object entityPlayer = handle.invoke(converted, new Object[0]);
			final Field pingField = entityPlayer.getClass().getField("ping");
			ping = pingField.getInt(entityPlayer);
			return ping;
		} catch (Exception ex) {
			return -1;
		}
	}

	static String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replaceAll("/", ".");
	}
}
