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


package bg.anticheat.modules.misc;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.Logger;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class ImposiblleAttack {
	public static HashMap<String, Long> as = new HashMap<String, Long>();
	public static void a(EntityDamageByEntityEvent e)
	{
		if(DacStringBase.imposibleAttack_protection == false)
			return;
		if(as.containsKey(e.getDamager().getName()))
		{
			   PlayerCheatEvent ass = new PlayerCheatEvent((Player) e.getEntity(), CheatType.IMPOSIBLLEATTACK, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
			e.setCancelled(true);
            Hackers.addImposibleAttack((Player)e.getDamager());
            if(Hackers.isReadyForImposibleAttackMessage((Player)e.getDamager()))
            {
            	 Logger.addMessageToFileLog((Player) e.getDamager(), "ImpossibleAttack");
                if (DacStringBase.log_console) {
                    try {
                    	if(Main.isUsingPlaceholderAPI())
                            Bukkit.getLogger().log(Level.INFO, PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "ImposiblleAttack").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                    	else Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412�2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "ImposiblleAttack").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
                    }
                    catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (DacStringBase.log_player) {
                	if(Main.isUsingPlaceholderAPI())
							try {
								Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						else
							try {
								Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("Dakata.Admin")) {
                            try {
                            	if(Main.isUsingPlaceholderAPI())
                                   p.sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getDamager(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "ImposiblleAttack(Attack while opening menu)").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager())))));
                                else p.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "ImposiblleAttack(Attack while opening menu)").replaceAll("<player>", e.getDamager().getName()).replaceAll("<world>", e.getDamager().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing((Player) e.getDamager()))));
                            }
                            catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }}
            }
		}
	}
	public static void a(InventoryCloseEvent e)
	{
		if(DacStringBase.imposibleAttack_protection == false)
			return;
		as.remove(e.getPlayer().getName());
	}
	public static void a(InventoryOpenEvent e)
	{
		if(DacStringBase.imposibleAttack_protection == false)
			return;
		as.put(e.getPlayer().getName(), System.currentTimeMillis());
	}
}
