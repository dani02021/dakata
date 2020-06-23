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


package bg.anticheat.modules.attack;

import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import bg.anticheat.utils.*;
import org.bukkit.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;

public class MultiAura
{
    private static HashMap<String, UUID> a;
    private static HashMap<String, Short> a1;
    private static ArrayList<String> player = new ArrayList<String>();
    
    static {
        MultiAura.a = new HashMap<String, UUID>();
        MultiAura.a1 = new HashMap<String, Short>();
    }
    
    public static void ma(final EntityDamageByEntityEvent e) {
        if (!DacStringBase.multiaura_protection) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (((Player)e.getDamager()).hasPermission("Dakata.Bypass.MultiAura")) {
            return;
        }
        if (((Player)e.getDamager()).hasPermission("Dakata.Bypass")) {
            return;
        }
        if (MultiAura.a.containsKey(((Player)e.getDamager()).getName())) {
            if (!MultiAura.a.get(((Player)e.getDamager()).getName()).equals(e.getEntity().getUniqueId())) {
                if (MultiAura.a1.containsKey(((Player)e.getDamager()).getName())) {
                    short s = MultiAura.a1.get(((Player)e.getDamager()).getName());
                    ++s;
                    MultiAura.a1.put(((Player)e.getDamager()).getName(), s);
                    if(Main.is19ro1() || Main.is19ro2() || Main.is110ro1() || Main.is111ro1() || Main.is112ro1())
                    	if(!(e.getEntity() instanceof Player)){
                            MultiAura.a1.remove(((Player)e.getDamager()).getName());
                            MultiAura.a.remove(((Player)e.getDamager()).getName());
                    		return;
                    		}
                    if (MultiAura.a1.get(((Player)e.getDamager()).getName()) > Settings.max_hit_entityes) {
                    	PlayerCheatEvent a = new PlayerCheatEvent((Player)e.getDamager(), CheatType.MULTIAURA, true);
                    	Bukkit.getPluginManager().callEvent(a);
                    	if(!a.isCancelled()) {
                    		Logger.addMessageToFileLog((Player) e.getDamager(), "MultiAura");
                            ((Player)e.getDamager()).kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replace("<hack>", "MultiAura"));
                            if(DacStringBase.broadcast_kick)Bukkit.broadcastMessage(Messages.broadcast((Player) e.getDamager(), "MultiAura"));
                    	}
                        MultiAura.a1.remove(((Player)e.getDamager()).getName());
                        MultiAura.a.remove(((Player)e.getDamager()).getName());
                        return;
                    }
                }
                else {
                    MultiAura.a1.put(((Player)e.getDamager()).getName(), (short)1);
                }
            }
        }
        else {
            MultiAura.a.put(((Player)e.getDamager()).getName(), e.getEntity().getUniqueId());
        }
        if(!player.contains(e.getDamager().getName())){
        	player.add(e.getDamager().getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
            @Override
            public void run() {
                MultiAura.a1.remove(((Player)e.getDamager()).getName());
                MultiAura.a.remove(((Player)e.getDamager()).getName());
                player.remove(e.getDamager().getName());
            }
        }, 8L);}
    }
    
    public static void clear() {
        MultiAura.a.clear();
        MultiAura.a1.clear();
    }
}
