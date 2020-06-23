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


package bg.anticheat.modules.block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.XMaterial;
import bg.anticheat.utils.DacStringBase;

public class WaterBuild {
	public static void saas(BlockPlaceEvent e)
	{
		if(!e.isCancelled())
			return;
		if(e.getPlayer().hasPermission("Dakata.Bypass.Liquid"))
			return;
		if(e.getBlock().getType().equals(XMaterial.LILY_PAD.material()))
			return;
		//Checks
		if(e.getBlockAgainst().isLiquid()) {
			PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.LIQUID, false);
			Bukkit.getPluginManager().callEvent(ass);
			if(!ass.isCancelled()){
				 Logger.addMessageToFileLog(e.getPlayer(), "WaterBuild");
			    e.setCancelled(true);
			    e.getPlayer().sendMessage(DacStringBase.anticheat_tag+DacStringBase.uyari_msg.replaceAll("<hack>", "Liquid"));
			}
		}
	}
}
