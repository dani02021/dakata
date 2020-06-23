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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import bg.anticheat.dakata.Main;
import bg.anticheat.modules.move.Speed;

public class Elytra implements Listener {
	public static String usr = "%%__USER__%%";
	public static ArrayList<String> dontSpeed = new ArrayList<String>();
	
	@EventHandler
	public void a(final EntityToggleGlideEvent e)
	{
		if(!(e.getEntity() instanceof Player))
			return;
	    Speed.setBacks.remove(e.getEntity());
	    if(!dontSpeed.contains(e.getEntity().getName()))
	    	dontSpeed.add(e.getEntity().getName());
	    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
			
			@Override
			public void run() {
				dontSpeed.remove(e.getEntity().getName());
				
			}
		}, 60L);
	}
}
