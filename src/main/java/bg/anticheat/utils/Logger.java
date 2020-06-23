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

import java.util.*;

import org.bukkit.entity.Player;

import bg.anticheat.dakata.Main;
import me.clip.placeholderapi.PlaceholderAPI;

import java.text.*;
import java.io.*;

public class Logger
{
    private static File f;
    private static Date d;
    private static SimpleDateFormat sdf;
    
    static {
        Logger.f = new File("/plugins/DakataAntiCheat/log.txt");
        Logger.sdf = new SimpleDateFormat("[dd-MM-yy h:mm:ss a]");
    }
    
    public static void logHack(final String hackerName, final String hack, final int ping) {
        if (!Logger.f.exists()) {
            try {
                Logger.f.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(Logger.f);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        Logger.d = new Date();
        try {
            fw.append(String.valueOf(Logger.sdf.format(Logger.d)) + DacStringBase.anticheat_tag.replace("\u0420\u2019\u0412�", "") + hackerName + " is suspected for " + hack + " (ping: " + ping + ")");
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public static void addMessageToFileLog(Player player, String message) {
		String messageC = DacStringBase.log_file_message.replaceAll("%y%", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)))
				.replaceAll("%m%", Integer.toString(Calendar.getInstance().get(Calendar.MONTH)))
				.replaceAll("%d%", Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)))
				.replaceAll("%h%", Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)))
				.replaceAll("%mm%", Integer.toString(Calendar.getInstance().get(Calendar.MINUTE)))
				.replaceAll("%s%", Integer.toString(Calendar.getInstance().get(Calendar.SECOND)));
		
		String message1 = message;
		if(Main.isUsingPlaceholderAPI() && player != null)
			message1 = PlaceholderAPI.setPlaceholders(player, message);
		Main.fileLog.append(","+messageC+message1);
	}
    
    public static void save() {
    	File f = new File("plugins/DakataAntiCheat/logs/");
		File f1 = new File("plugins/DakataAntiCheat/logs/"+Calendar.getInstance().get(Calendar.YEAR)+"-"+Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+".txt");
		if (!f.exists()) {
			f.mkdirs();
			try {
				f1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		PrintWriter ps = null;
		try {
			ps = new PrintWriter(new FileWriter(f1, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		String[] s = Main.fileLog.toString().split(",");
		for(int i = 1;i<= s.length-1;i++) {
			ps.println(s[i]);
		}
		ps.close();
		Main.fileLog.setLength(0);//These two should be together for better performance!
		Main.fileLog.trimToSize();//These two should be together for better performance!
	}
}
