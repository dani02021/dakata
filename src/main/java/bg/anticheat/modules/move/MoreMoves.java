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
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class MoreMoves {
	private static ArrayList<String> as = new ArrayList<String>();
	public static ArrayList<String> dont = new ArrayList<String>();
	private static HashMap<String, Short> as1 = new HashMap<String, Short>();
	private static HashMap<String, Location> setBack = new HashMap<String, Location>();
	public static void onMove(final PlayerMoveEvent e){
		if(Main.dontCheckOnSpawn.contains(e.getPlayer().getName()))
			return;
		
		if(dont.contains(e.getPlayer().getName()))
			return;
		
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			return;
		if(!DacStringBase.moreMoves_protection)
			return;
		if(as1.containsKey(e.getPlayer().getName()))
			as1.put(e.getPlayer().getName(), (short) (as1.get(e.getPlayer().getName())+1));
		else {
			setBack.put(e.getPlayer().getName(), e.getPlayer().getLocation());
			as1.put(e.getPlayer().getName(), (short) 1);
		}
		if(!as.contains(e.getPlayer().getName())){
			as.add(e.getPlayer().getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
				
				@Override
				public void run() {
					
					if(as1.containsKey(e.getPlayer().getName())){
						try {
							//e.getPlayer().sendMessage(""+as1.get(e.getPlayer().getName()));
							if(Ping.getPlayerPing(e.getPlayer()) <= 120 && TPS.getTPS() > 16.0){
								a(e, Settings.max_moves);
							} else if(Ping.getPlayerPing(e.getPlayer()) <= 180 && TPS.getTPS() > 13.0){
								a(e, Settings.max_moves+5);
							} else if(Ping.getPlayerPing(e.getPlayer()) > 210 && TPS.getTPS() > 8.0){
								a(e, Settings.max_moves+10);
							} else if(TPS.getTPS() < 8.0){
								return;
							} else a(e, Settings.max_moves+5);

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					as.remove(e.getPlayer().getName());
					as1.remove(e.getPlayer().getName());
				}
			}, 3L);
			}
	}
	
	private static void a(PlayerMoveEvent e, int maxMoves) {
		if(as1.get(e.getPlayer().getName()) > maxMoves) {
			PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.MOREMOVES, false);
			Bukkit.getPluginManager().callEvent(ass);
			if(!ass.isCancelled()){
				if(setBack.containsKey(e.getPlayer().getName())){
					e.getPlayer().teleport(setBack.get(e.getPlayer().getName()));
				} else e.setCancelled(true);
				Hackers.addMoreMoves(e.getPlayer());
				if (Hackers.isReadyForMoreMovesMessage(e.getPlayer())) {
					if (DacStringBase.log_console) {
						try {
							Bukkit.getLogger().log(Level.INFO,
									String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "")
											.replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "")
											.replaceAll("&1", "").replaceAll("\u0412�2", "").replaceAll("&2", "")
											.replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "")
											.replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "")
											.replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", ""))
									+ DacStringBase.hack_msg.replaceAll("<hack>", "MoreMoves")
											.replaceAll("<player>", e.getPlayer().getName())
											.replaceAll("<world>", e.getPlayer().getWorld().getName())
											.replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>",
													Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
					if (DacStringBase.log_player) {
                    	if(Main.isUsingPlaceholderAPI())
								try {
									Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer())))));
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							else
								try {
									Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getPlayer().getName()).replaceAll("<world>", e.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						for (final Player pa : Bukkit.getOnlinePlayers()) {
							if (pa.hasPermission("Dakata.Admin")) {
								try {
									pa.sendMessage(
											String.valueOf(DacStringBase.anticheat_tag)
													+ DacStringBase.hack_msg.replaceAll("<hack>", "MoreMoves")
															.replaceAll("<player>", e.getPlayer().getName())
															.replaceAll("<world>",
																	e.getPlayer().getWorld().getName())
													.replaceAll("<tps>", Double.toString(TPS.getTPS()))
													.replaceAll("<ping>",
															Integer.toString(Ping.getPlayerPing(e.getPlayer()))));
								} catch (Exception e4) {
									e4.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
}
