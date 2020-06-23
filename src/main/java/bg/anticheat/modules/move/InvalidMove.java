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
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;

public class InvalidMove
{
    private static ArrayList<Player> dontX;
    private static ArrayList<Player> dontZ;
    private static ArrayList<Player> dontWith;
    
    static {
        InvalidMove.dontX = new ArrayList<Player>();
        InvalidMove.dontZ = new ArrayList<Player>();
        InvalidMove.dontWith = new ArrayList<Player>();
    }
    
    private static void crossControl(final Player p, final Location loc, final double topFark) {
       if (topFark > Settings.max_lagg_distance) {
           p.teleport(loc);
           Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(p, CheatType.INVALIDMOVE, false));
               if (DacStringBase.log_console) {
                   try {
                       Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\u0420\u2019\u0412§2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidMove").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
                   }
                   catch (Exception e4) {
                       e4.printStackTrace();
                   }
               }
               if (DacStringBase.log_player) {
                   for (final Player p3 : Bukkit.getOnlinePlayers()) {
                       if (p3.hasPermission("Dakata.Admin")) {
                           try {
                               p3.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "InvalidMove").replaceAll("<player>", p.getName()).replaceAll("<world>", p.getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(p))));
                           }
                           catch (Exception e5) {
                               e5.printStackTrace();
                           }
                       }
                   }
        }
        InvalidMove.dontWith.remove(p);}
    }
    
    public static void checkSpeedHack(final PlayerMoveEvent e) {
        if (!DacStringBase.invalidmove_protection) {
            return;
        }
        if(TP.dont.contains(e.getPlayer().getName()))
        	return;
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
        final Player p = e.getPlayer();
        if (e.getFrom().getY() > e.getTo().getY()) {
            return;
        }
        if (p.hasPermission("Dakata.Bypass.Blink")) {
            return;
        }
        if (p.getAllowFlight()) {
            return;
        }
        if(Speed.damagelist2.containsKey(e.getPlayer().getName()))
        	return;
        if(Main.is110ro1() || Main.is111ro1())
        	if(e.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION))
        		return;
    	if(Main.is110ro1() || Main.is111ro1())
    		if(e.getPlayer().getItemInHand().getType().equals(Material.CHORUS_FRUIT))
    			return;
        if (p.getVehicle() != null) {
            return;
        }
        if (HighJump.isWasOnSlime(p.getName())) {
            return;
        }
        if (e.getTo().getX() != e.getFrom().getX() && e.getTo().getZ() != e.getFrom().getZ()) {
            final Vector vel = p.getVelocity();
            if (vel.getX() != 0.0 || vel.getZ() != 0.0) {
                return;
            }
            for (final PotionEffect s : p.getActivePotionEffects()) {
                if (s.getType().getName() == "SPEED") {
                    return;
                }
            }
            if (InvalidMove.dontWith.contains(p)) {
                return;
            }
            try {
                if (e.getPlayer().isOnGround()) {
                    if (e.getFrom().distance(e.getTo()) > 1.5) {
                    	Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.INVALIDMOVE, true));
                        e.setTo(e.getFrom());
                        e.getPlayer().kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replace("<hack>", "Invalid Move"));
                        if(DacStringBase.broadcast_kick)
                        	Bukkit.broadcastMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replace("<hack>", "Invalid Move").replace("<player>", e.getPlayer().getName()));
                    }
                }
                else if (e.getFrom().distance(e.getTo()) > 1.8) {
                	Bukkit.getPluginManager().callEvent(new PlayerCheatEvent(e.getPlayer(), CheatType.INVALIDMOVE, true));
                    e.setTo(e.getFrom());
                    e.getPlayer().kickPlayer(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.kick_mesaj.replace("<hack>", "Invalid Move"));
                    if(DacStringBase.broadcast_kick)
                    	Bukkit.broadcastMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.broadcast.replace("<hack>", "Invalid Move").replace("<player>", e.getPlayer().getName()));
                }
            }
            catch (Exception e3) {
                e3.printStackTrace();
            }
            InvalidMove.dontWith.add(p);
            final Location loc = e.getFrom();
            final double x1 = e.getFrom().getX();
            final double z1 = e.getFrom().getZ();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                @Override
                public void run() {
                    final double x2 = e.getPlayer().getLocation().getX();
                    final double z2 = e.getPlayer().getLocation().getZ();
                    double fark1;
                    if (x1 > x2) {
                        fark1 = x1 - x2;
                    }
                    else {
                        fark1 = x2 - x1;
                    }
                    double fark2;
                    if (z1 > z2) {
                        fark2 = z1 - z2;
                    }
                    else {
                        fark2 = z2 - z1;
                    }
                    final double topFark = fark1 + fark2;
                    crossControl(e.getPlayer(), loc, topFark);
                }
            }, 6L);
        }
    }
    
    public static void o(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (((Player)e.getEntity()).hasPermission("Dakata.Bypass")) {
            return;
        }
        if(!InvalidMove.dontWith.contains(e.getEntity()))
        	InvalidMove.dontWith.add((Player)e.getEntity());
        Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
            @Override
            public void run() {
                if (InvalidMove.dontWith.contains(e.getEntity())) {
                    InvalidMove.dontWith.remove(e.getEntity());
                }
            }
        }, 30L);
    }
    
    public static void clear() {
        InvalidMove.dontWith.clear();
        InvalidMove.dontX.clear();
        InvalidMove.dontZ.clear();
    }
}
