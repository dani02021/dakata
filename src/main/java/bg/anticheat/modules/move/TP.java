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

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import bg.anticheat.dakata.*;

public class TP
{
    public static ArrayList<String> dont;
    public static HashMap<String, Short> dont2;
    
    static {
        TP.dont = new ArrayList<String>();
        TP.dont2 = new HashMap<String, Short>();
    }
    
    public static void antitp(final PlayerTeleportEvent e) {
        if (!TP.dont.contains(e.getPlayer().getName())) {
            TP.dont.add(e.getPlayer().getName());
            Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    TP.dont.remove(e.getPlayer().getName());
                }
            }, 40L);
        }
        TP.dont2.put(e.getPlayer().getName(), (short) 10);
    }
}
