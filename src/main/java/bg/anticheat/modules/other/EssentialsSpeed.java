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

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import bg.anticheat.dakata.Main;

public class EssentialsSpeed {
	public static ArrayList<String> speed = new ArrayList<String>();
	public static void v(PlayerCommandPreprocessEvent e)
	{
		if(e.getMessage().startsWith("/speed"))
		{
			if(!e.getPlayer().hasPermission("essentials.speed"))
				return;
			if(!Main.getThisPlugin().getServer().getPluginManager().isPluginEnabled("Essentials"))
				return;
			String com = e.getMessage().replace("/speed ", "");
			if(Integer.parseInt(com) == 1)
			{
				if(speed.contains(e.getPlayer().getName()))
				{
					speed.remove(e.getPlayer().getName());
				}
			} else if(Integer.parseInt(com) > 1)
			{
				if(!speed.contains(e.getPlayer().getName()))
				{
					speed.add(e.getPlayer().getName());
				}
					
			}
		}
	}
	public static void c(PlayerQuitEvent e)
	{
		if(speed.contains(e.getPlayer().getName()))
		{
			speed.remove(e.getPlayer().getName());
		}
	}
	public static void clear()
	{
		speed.clear();
	}
}
