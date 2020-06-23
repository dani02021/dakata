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


package bg.anticheat.modules.chat;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.utils.*;

public class AntiASCII
{
    public static void controlASCII(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("Dakata.Bypass.Chat.ASCII") || StringUtils.containsIgnoreCase(e.getMessage(), "�")  || StringUtils.containsIgnoreCase(e.getMessage(), "�")  || StringUtils.containsIgnoreCase(e.getMessage(), "�") || StringUtils.containsIgnoreCase(e.getMessage(), "�")) {
            return;
        }
        String removeTRchar = e.getMessage();
        //NOT WORK :/ The server dont read ascii as well
        removeTRchar=removeExtensions(removeTRchar);
        final String rplcChar = removeTRchar.replaceAll("[^\\p{ASCII}]", "");
        if (rplcChar != removeTRchar) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_ASCII, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
           		 Logger.addMessageToFileLog(e.getPlayer(), "AntiASCII");
            e.setCancelled(true);
            p.sendMessage(String.valueOf(DacStringBase.anticheat_tag));}
        }
    }
    
    public static void controlASCIIbgmand(final PlayerCommandPreprocessEvent e) {
        try {
            if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
                return;
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.Chat.ASCII") || StringUtils.containsIgnoreCase(e.getMessage(), "�")  || StringUtils.containsIgnoreCase(e.getMessage(), "�")  || StringUtils.containsIgnoreCase(e.getMessage(), "�") || StringUtils.containsIgnoreCase(e.getMessage(), "�")) {
            return;
        }
        String removeTRchar = e.getMessage();
        removeTRchar=removeExtensions(removeTRchar);
        final String rplcChar = e.getMessage().replaceAll("[^\\p{ASCII}]", "");
        if (rplcChar != removeTRchar) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.COMMAND_ASCII, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
           		 Logger.addMessageToFileLog(e.getPlayer(), "AntiASCII");
           		   e.setCancelled(true);
                   e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag));
           	   }
           	   }
    }
    public static void controlASCIIbgmand(final SignChangeEvent e) {
        try {
            if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
                return;
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        if (e.getPlayer().hasPermission("Dakata.Bypass.Chat.ASCII")) {
            return;
        }
        
        for(String i : e.getLines())
        {
        	i=removeExtensions(i);
        	if(i.equals(i.replaceAll("[^\\p{ASCII}]", "")))
        	{
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.SIGN_ASCII, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		 Logger.addMessageToFileLog(e.getPlayer(), "AntiASCII");
               		   e.setCancelled(true);
                       e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag));
               	   }
               	   }
        }
        }

	private static String removeExtensions(String s) {
		return s;
	}
}
