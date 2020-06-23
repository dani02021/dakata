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

import org.bukkit.event.player.*;

import org.bukkit.*;

import bg.anticheat.utils.*;
import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import org.bukkit.entity.*;
import java.util.*;

import java.net.*;
import java.io.*;

public class AntiBot
{
    private static HashMap<String, Short> hm1;
    
    static {
        AntiBot.hm1 = new HashMap<String, Short>();
    }
    
    public static void bot(final AsyncPlayerPreLoginEvent e) {
        if (e.getAddress().getHostAddress().equals("127.0.0.1")) {
            return;
        }
        if (Bukkit.getPlayer(e.getUniqueId()).hasPermission("Dakata.Bypass.AntiBot")) {
            return;
        }
        if (!DacStringBase.antibot_protection) {
            return;
        }
        if (AntiBot.hm1.containsKey(e.getAddress().getHostAddress())) {
            short i = AntiBot.hm1.get(e.getAddress().getHostAddress());
            ++i;
            AntiBot.hm1.put(e.getAddress().getHostAddress(), i);
        }
        else {
            AntiBot.hm1.put(e.getAddress().getHostAddress(), (short)1);
        }
        if (AntiBot.hm1.get(e.getAddress().getHostAddress()) > 2) {
            for (final Player p : Bukkit.getOnlinePlayers()) {
                if (p.getAddress().getHostName().equals(AntiBot.hm1.get(e.getAddress().getHostAddress()))) {
                    e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, (String)null);
                    AntiBot.hm1.remove(e.getAddress().getHostAddress());
                }
            }
        }
        Scanner s = null;
        try {
            s = new Scanner(new URL("http://botscout.com/test/?ip=" + e.getAddress().getHostAddress()).openStream());
        }
        catch (IOException e3) {
            //e3.printStackTrace();
        }
        if (s.findInLine("Y") != null) {
        	Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(Bukkit.getPlayer(e.getUniqueId()), CheatType.BOT_PROXY, false));
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, String.valueOf(DacStringBase.anticheat_tag) + "Proxy detected");
            Bukkit.getPlayer(e.getUniqueId()).setWhitelisted(false);
        }
        else {
            e.allow();
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
            Bukkit.getPlayer(e.getUniqueId()).setWhitelisted(true);
        }
        s.close();
        
        //Check 3
        URL oracle = null;
		try {
			oracle = new URL("http://legacy.iphub.info/api.php?ip="+e.getAddress().getHostAddress());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        BufferedReader in = null;
		try {
			in = new BufferedReader(
			new InputStreamReader(oracle.openStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}

        String inputLine;
        try {
			while ((inputLine = in.readLine()) != null)
				for(String f : inputLine.split("<proxy>"))
					if(f.contains("proxy")){
						f = f.replaceAll("</proxy>", "");
			            if(f.equals("1")){
			            	Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(Bukkit.getPlayer(e.getUniqueId()), CheatType.BOT_PROXY, false));
			                e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
			                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, String.valueOf(DacStringBase.anticheat_tag) + "Proxy detected");
			                Bukkit.getPlayer(e.getUniqueId()).setWhitelisted(false);
			            }
					}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
        try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
    }
    
    public static void info(final Player sender, final Player p) {
        if (p.getAddress().getHostName().equals("127.0.0.1")) {
            sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "You can't get info about that player");
            return;
        }
        if (!DacStringBase.antibot_protection) {
            sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Antibot protection is disabled");
        }
        URL ur = null;
        try {
            ur = new URL("http://ip-api.com/json/" + p.getAddress().getHostName());
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        HttpURLConnection localHttpURLConnection = null;
        try {
            localHttpURLConnection = (HttpURLConnection)ur.openConnection();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            localHttpURLConnection.connect();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
        final com.google.gson.JsonParser localJsonParser = new com.google.gson.JsonParser();
        com.google.gson.JsonElement localJsonElement = null;
        try {
            localJsonElement = localJsonParser.parse(new InputStreamReader((InputStream)localHttpURLConnection.getContent()));
        }
        catch (com.google.gson.JsonIOException | com.google.gson.JsonSyntaxException | IOException ex2) {
            ex2.printStackTrace();
        }
        final com.google.gson.JsonObject localJsonObject = localJsonElement.getAsJsonObject();
        if (localJsonObject.get("status").getAsString().equals("fail")) {
            sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "The database can't send info about the player");
            return;
        } 
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Country: " + localJsonObject.get("country").getAsString());
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Region: " + localJsonObject.get("regionName").getAsString());
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "City: " + localJsonObject.get("city").getAsString());
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Time zone: " + localJsonObject.get("timezone").getAsString());
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "ISP: " + localJsonObject.get("isp").getAsString());
        sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Zip code: " + localJsonObject.get("zip").getAsString());
        } catch(NoClassDefFoundError ex)
        {
        	try {
            	final com.google.gson.JsonParser localJsonParser = new com.google.gson.JsonParser();
            	com.google.gson.JsonElement localJsonElement = null;
                try {
                    localJsonElement = localJsonParser.parse(new InputStreamReader((InputStream)localHttpURLConnection.getContent()));
                }
                catch (com.google.gson.JsonIOException |com.google.gson.JsonSyntaxException | IOException ex2) {
                    ex2.printStackTrace();
                }
                final com.google.gson.JsonObject localJsonObject = localJsonElement.getAsJsonObject();
                if (localJsonObject.get("status").getAsString().equals("fail")) {
                    sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "The database can't send info about the player");
                    return;
                }
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Country: " + localJsonObject.get("country").getAsString());
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Region: " + localJsonObject.get("regionName").getAsString());
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "City: " + localJsonObject.get("city").getAsString());
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Time zone: " + localJsonObject.get("timezone").getAsString());
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "ISP: " + localJsonObject.get("isp").getAsString());
                sender.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Zip code: " + localJsonObject.get("zip").getAsString());
        	} catch(NoClassDefFoundError ex1) {
        		
        	}
        }
        }
    
    public static void clear() {
        if (Main.is17ro1() || Main.is17ro2() || Main.is17ro3() || Main.is17ro4()) {
            return;
        }
        AntiBot.hm1.clear();
    }
}
