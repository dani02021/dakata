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

import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.*;
import org.bukkit.entity.*;
import bg.anticheat.utils.*;

public class Throw
{
    static HashMap<String, Short> COOLDOWN;
    static ArrayList<String> player;
    
    static {
        Throw.COOLDOWN = new HashMap<String, Short>();
        Throw.player = new ArrayList<String>();
    }
    
    public static void throwItem(final ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Snowball) {
            if (!DacStringBase.throw_snowball_protection) {
                return;
            }
            final Snowball sw = (Snowball)e.getEntity();
            if (sw.getShooter() instanceof Player) {
                final Player p = (Player)sw.getShooter();
                if (DacStringBase.max_player_ping != -1) {
                    try {
                        if (Ping.getPlayerPing(p) > DacStringBase.max_player_ping) {
                            return;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (p.hasPermission("Dakata.Bypass.Throw")) {
                    return;
                }
                if (Throw.COOLDOWN.containsKey(p.getName())) {
                    short a = Throw.COOLDOWN.get(p.getName());
                    ++a;
                    Throw.COOLDOWN.put(p.getName(), a);
                }
                else {
                    Throw.COOLDOWN.put(p.getName(), (short)1);
                }
                if(!player.contains(p.getName())){
                	player.add(p.getName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        if (Throw.COOLDOWN.get(p.getName()) == null) {
                            return;
                        }
                        if (Throw.COOLDOWN.get(p.getName()) >= 10) {
                        	PlayerCheatEvent ev = new PlayerCheatEvent(p, CheatType.THROW_SNOWBALL, true);
                        	Bukkit.getPluginManager().callEvent(ev);
                        	if(!ev.isCancelled()) {
                            	Logger.addMessageToFileLog(p, "Throw(Snowball)");
                            	e.setCancelled(true);
                                p.kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replaceAll("<hack>", "Throw"));
                                if(DacStringBase.broadcast_kick)
                                	Bukkit.broadcastMessage(Messages.broadcast(p, "Throw"));
                        	}
                        }
                        Throw.COOLDOWN.remove(p.getName());
                        player.remove(p.getName());
                    }
                }, 5L);}
            }
        }
        if (e.getEntity() instanceof Egg) {
            if (!DacStringBase.throw_egg_protection) {
                return;
            }
            final Egg egg = (Egg)e.getEntity();
            if (egg.getShooter() instanceof Player) {
                final Player p = (Player)egg.getShooter();
                if (DacStringBase.max_player_ping != -1) {
                    try {
                        if (Ping.getPlayerPing(p) > DacStringBase.max_player_ping) {
                            return;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (Throw.COOLDOWN.containsKey(String.valueOf(p.getName()) + "egg")) {
                    short a = Throw.COOLDOWN.get(String.valueOf(p.getName()) + "egg");
                    ++a;
                    Throw.COOLDOWN.put(String.valueOf(p.getName()) + "egg", a);
                }
                else {
                    Throw.COOLDOWN.put(String.valueOf(p.getName()) + "egg", (short)1);
                }
                if(!player.contains(p.getName()+"egg")){
                	player.add(p.getName()+"egg");
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        if (Throw.COOLDOWN.get(String.valueOf(p.getName()) + "egg") == null) {
                            return;
                        }
                        if (Throw.COOLDOWN.get(String.valueOf(p.getName()) + "egg") > Settings.max_throwse) {
                        	PlayerCheatEvent ev = new PlayerCheatEvent(p, CheatType.THROW_EGG, true);
                        	Bukkit.getPluginManager().callEvent(ev);
                        	if(!ev.isCancelled()) {
                            	Logger.addMessageToFileLog(p, "Throw(Egg)");
                            	e.setCancelled(true);
                                p.kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replaceAll("<hack>", "Throw"));
                                if(DacStringBase.broadcast_kick)
                                	Bukkit.broadcastMessage(Messages.broadcast(p, "Throw"));
                        	}
                        }
                        Throw.COOLDOWN.remove(String.valueOf(p.getName()) + "egg");
                        player.remove(p.getName()+"egg");
                    }
                }, Settings.throwe_time);}
            }
        }
    }
    
    public static void clear() {
        Throw.COOLDOWN.clear();
    }
}
