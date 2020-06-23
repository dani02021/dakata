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

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import bg.anticheat.dakata.Main;
import me.clip.placeholderapi.PlaceholderAPI;

public class Messages {
	public static String warnPlayer(String hack){
		return DacStringBase.anticheat_tag+DacStringBase.uyari_msg.replaceAll("<hack>", hack);
	}
	public static String kickPlayer(String hack){
		return String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replaceAll("<hack>", hack);
	}
	public static String broadcast(Player p, String hack){
		if(Main.isUsingPlaceholderAPI())
		    return PlaceholderAPI.setPlaceholders(p, String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replaceAll("<hack>", hack).replaceAll("<player>", p.getName()));
	    return String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replaceAll("<hack>", hack).replaceAll("<player>", p.getName());
	}
	public static String logSpeed(Player p, double distSq, double speed, String mode){
		try {
			return 	String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Speed(speed = " + distSq + ", expected = " + speed + ") (mode = "+mode+")").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void debugToPlayer(Player p, String message){
		if(p.getAddress().getHostString().equals("127.0.0.1") && p.getName().equals("dannos1")){
			p.sendMessage(message);
		}
	}
	public static void message(Player p, String hack) {
		if (DacStringBase.log_console) {
			try {
				Bukkit.getLogger().log(Level.INFO,
						String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "")
								.replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "")
								.replaceAll("&1", "").replaceAll("\u0412§2", "").replaceAll("&2", "")
								.replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "")
								.replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "")
								.replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", ""))
						+ DacStringBase.hack_msg.replaceAll("<hack>", hack)
								.replaceAll("<player>", p.getName())
								.replaceAll("<world>", p.getWorld().getName())
								.replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>",
										Integer.toString(Ping.getPlayerPing(p))));
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		if (DacStringBase.log_player) {
			for (final Player pa : Bukkit.getOnlinePlayers()) {
				if (pa.hasPermission("Dakata.Admin")) {
					try {
						pa.sendMessage(
								String.valueOf(DacStringBase.anticheat_tag)
										+ DacStringBase.hack_msg.replaceAll("<hack>", hack)
												.replaceAll("<player>", p.getName())
												.replaceAll("<world>",
														p.getWorld().getName())
										.replaceAll("<tps>", Double.toString(TPS.getTPS()))
										.replaceAll("<ping>",
												Integer.toString(Ping.getPlayerPing(p))));
					} catch (Exception e4) {
						e4.printStackTrace();
					}
				}
			}
		}
	}
}
