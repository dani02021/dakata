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

import java.util.*;
import org.bukkit.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import java.util.regex.*;
import org.bukkit.event.player.*;
import bg.anticheat.utils.*;

public class Spam
{
    private static HashMap<String, Short> antispam2;
    
    static {
        Spam.antispam2 = new HashMap<String, Short>();
    }
    
    //TODO: FIX THE SPAM CHECK
    
    public static void spam(final AsyncPlayerChatEvent e) {
    	if(e.isCancelled())
    		return;
        if (!e.getPlayer().hasPermission("Dakata.Bypass.Chat.Swear")
        		&& DacStringBase.chat_swear_protection
        		&& DacStringBase.swearwords.contains(e.getMessage())) {
			   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_SWEAR, false);
           	   Bukkit.getPluginManager().callEvent(ass);
           	   if(!ass.isCancelled()){
           		 Logger.addMessageToFileLog(e.getPlayer(), "Spam(Swear)");
            e.setCancelled(true);
            e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.swear_msg);
           	   }
           	   }
        if (!e.getPlayer().hasPermission("Dakata.Bypass.Chat.Advertisement.IP") && DacStringBase.chat_advertising_ip_protection) {
            final Pattern p = Pattern.compile("(?:\\d{1,3}[.,\\-:;\\/()=?}+ ]{1,4}){3}\\d{1,3}");
            final Matcher a = p.matcher(e.getMessage());
            while (a.find()) {
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_ADVERT_IP, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		 Logger.addMessageToFileLog(e.getPlayer(), "Spam(Advert)");
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.ad_msg);
            }}
        }
        if (!e.getPlayer().hasPermission("Dakata.Bypass.Chat.Advertisement.Web") && DacStringBase.chat_advertising_web_protection) {
            final Pattern p = Pattern.compile("[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.(com|ru|net|org|de|jp|uk|br|pl|in|it|fr|au|info|nl|cn|ir|es|cz|biz|ca|kr|eu|ua|za|co|gr|ro|se|tw|vn|mx|ch|tr|at|be|hu|dk|tv|me|ar|us|no|sk|fi|id|cl|nz|by|pt|bg|xyz)\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?");
            final Matcher a = p.matcher(e.getMessage());
            while (a.find()) {
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_ADVERT_WEB, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		 Logger.addMessageToFileLog(e.getPlayer(), "Spam(Advert)");
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.ad_msg);
            }}
        }
        if (!e.getPlayer().hasPermission("Dakata.Bypass.Chat.Caps") && DacStringBase.chat_caps_protection) {
            if (e.getMessage().equals(":D")) {
                return;
            }
            if (e.getMessage().equals(":P")) {
                return;
            }
            if (e.getMessage().equals(":O")) {
                return;
            }
            final String msg = e.getMessage().replaceAll("[0-9]", "");
            final String msg2 = msg.replaceAll("[@:%_\\+.~#?&//=!$*)(<,.>]", "");
            final String msg3 = msg2.replaceAll(" ", "");
            final int msg4 = msg3.replaceAll("[A-Z]", "").length();
            final int msg5 = msg3.replaceAll("[a-z]", "").length();
            if (msg5 > msg4) {
                if (msg3.equals("")) {
                    return;
                }
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_CAPS, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		 Logger.addMessageToFileLog(e.getPlayer(), "Spam(CAPS)");
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Too many caps");
            }}
        }
        /*if (!e.getPlayer().hasPermission("Dakata.Bypass.Chat.Spam") && DacStringBase.chat_spam_protection) {
/*        	if(antispamWords.containsKey(e.getPlayer().getName())) {
        		for(String msg : e.getMessage().split(" ")) {
        			for(String msg1 : antispamWords.get(e.getPlayer().getName())) {
        				if(msg1.equalsIgnoreCase(msg)){
        					if(antispamWordsVL.containsKey(e.getPlayer().getName())) {
        						antispamWordsVL.put(e.getPlayer().getName(), (short) (antispamWordsVL.get(e.getPlayer().getName())+1));
        					} else antispamWordsVL.put(e.getPlayer().getName(), (short) 1);
        				}
        			}
        		}
				if(antispamWordsVL.containsKey(e.getPlayer().getName())) {
					if(e.getMessage().split(" ").length - antispamWordsVL.get(e.getPlayer().getName()) <= 1){
						e.setCancelled(true);
						e.getPlayer().sendMessage(DacStringBase.anticheat_tag+"The message is too similar with the previous one");
					}
					antispamWordsVL.remove(e.getPlayer().getName());
				}
        	}
        	antispamWords.put(e.getPlayer().getName(), e.getMessage().split(" "));
            if (Spam.antispam2.containsKey(e.getPlayer().getName())) {
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_SPAM, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.antispam_mesaj);
               	   }
               	short i = Spam.antispam2.get(e.getPlayer().getName());
                ++i;
                Spam.antispam2.remove(e.getPlayer().getName());
                Spam.antispam2.put(e.getPlayer().getName(), i);
            }
            else {
                Spam.antispam2.put(e.getPlayer().getName(), (short)1);
                
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        Spam.antispam2.remove(e.getPlayer().getName());
                    }
                }, DacStringBase.chat_per_second * 10L);
            }
            if (Spam.antispam2.get(e.getPlayer().getName()) == 4) {
                Bukkit.getScheduler().runTask(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
     				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.CHAT_SPAM, true);
                   	   Bukkit.getPluginManager().callEvent(ass);
                   	   if(!ass.isCancelled()){
                   		   e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.spam_kick_msg);
                   	   }
                    }
                });
            }
        }*/
    }
    
    public static void bgmandSpam(final PlayerCommandPreprocessEvent e) {
        if (DacStringBase.max_player_ping != -1) {
            try {
                if (Ping.getPlayerPing(e.getPlayer()) > DacStringBase.max_player_ping) {
                    return;
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        /*if (!e.getPlayer().hasPermission("Dakata.Bypass.Command.Spam") && DacStringBase.command_spam_protection) {
            if (Spam.antispam2.containsKey(e.getPlayer().getName())) {
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.COMMAND_SPAM, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.antispam_mesaj);
               	   }
                short i = Spam.antispam2.get(e.getPlayer().getName());
                ++i;
                Spam.antispam2.remove(e.getPlayer().getName());
                Spam.antispam2.put(e.getPlayer().getName(), i);
            }
            else {
                Spam.antispam2.put(e.getPlayer().getName(), (short)1);
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    Spam.antispam2.remove(e.getPlayer().getName());
                }
            }, DacStringBase.command_per_second * 20L);
            if (Spam.antispam2.get(e.getPlayer().getName()) == 4) {
				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.COMMAND_SPAM, true);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		   e.getPlayer().kickPlayer(DacStringBase.anticheat_tag+DacStringBase.spam_kick_msg);
               	   }
            }
        }*/
        if (!e.getPlayer().hasPermission("Dakata.Bypass.Command.Blocked-Cmd")) {
            for (int j = 0; j < DacStringBase.blocked_cmd.size(); ++j) {
                if (e.getMessage().equalsIgnoreCase("/" + DacStringBase.blocked_cmd.get(j))) {
 				   PlayerCheatEvent ass = new PlayerCheatEvent(e.getPlayer(), CheatType.COMMAND_BLOCKED_CMD, false);
               	   Bukkit.getPluginManager().callEvent(ass);
               	   if(!ass.isCancelled()){
               		 Logger.addMessageToFileLog(e.getPlayer(), "Spam(Blocked cmd)");
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(DacStringBase.block_cmd_msg);
                    }
                }
            }
        }
    }
    
    public static void clear() {
        Spam.antispam2.clear();
    }
}
