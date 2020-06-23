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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import bg.anticheat.api.CheatType;
import bg.anticheat.api.PlayerCheatEvent;
import bg.anticheat.dakata.Main;
import bg.anticheat.modules.misc.ImposiblleAttack;
import bg.anticheat.modules.other.Elytra;
import bg.anticheat.modules.other.JumpPads;
import bg.anticheat.utils.Hackers;
import bg.anticheat.utils.DacStringBase;
import bg.anticheat.utils.Ping;
import bg.anticheat.utils.PlayerUtils;
import bg.anticheat.utils.Settings;
import bg.anticheat.utils.TPS;
import me.clip.placeholderapi.PlaceholderAPI;

public class Speed
{
    public static HashMap<Player, Integer> setBacks;
    public static HashMap<Player, Location> setBack;
    private static HashMap<String, Double> previousMove;
    private static ArrayList<String> dontIce;
	protected static HashMap<String, Short> damagelist2;
    public static ArrayList<Player> damagelist;
    private static double sprinting;
    private static double sneaking;
    private static double cobweb;
	private static double cobwebSprint;
    private static double soulsand;
    private static double sousand_ice;
    private static double ice;
    private static double water;
    private static double lava;
    private static double jump;
    private static double speedPotion;
    private static double slowPotion;
    private static double stairs;
    private static double slab;
    private static double vlMultiplier;
    private static double maxVL;
	private static double item;
	private static double walk;
    
    static {
        Speed.setBacks = new HashMap<Player, Integer>();
        Speed.setBack = new HashMap<Player, Location>();
        Speed.dontIce = new ArrayList<String>();
        Speed.previousMove = new HashMap<String, Double>();
        Speed.damagelist2 = new HashMap<String, Short>();
        Speed.damagelist = new ArrayList<Player>();
        Speed.sprinting = Settings.sprint_speed;
        Speed.sneaking = Settings.sneak_speed;
        Speed.cobweb = Settings.cobweb_speed;
        Speed.cobwebSprint = 0.1;
        Speed.soulsand = Settings.soulsand_speed;
        Speed.sousand_ice = Settings.soulsand_ice_speed;
        Speed.ice = Settings.ice_speed;
        Speed.water = Settings.water_speed;
        Speed.lava = Settings.lava_speed;
        Speed.jump = Settings.jump_multiply_speed;
        Speed.speedPotion = 1.6;
        Speed.slowPotion = 0.05;
        Speed.vlMultiplier = 3;
        Speed.stairs = Settings.stair_multiply_speed;
        Speed.slab = Settings.slab_multiply_speed;
        Speed.maxVL = Settings.min_speed_violation;
        Speed.item = Settings.item_speed;
        Speed.walk = Settings.walk_speed;
    }
    
    public static void onSpeed(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
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
        if (!DacStringBase.speed_protection) {
            return;
        }
        
        if(damagelist2.containsKey(p.getName()))
        	if(damagelist2.get(p.getName()) > 0) {
        	    damagelist2.put(p.getName(), (short) (damagelist2.get(p.getName())-1));
        	    return;
        	}  else damagelist2.remove(p.getName());
        
        if(JumpPads.disable.containsKey(p.getName()))
        	return;
        if(JumpPads.disableSpeed.contains(p.getName()))
        	return;
        
        if(event.getPlayer().isFlying())
        	return;
        
        if((p.getLocation().clone().subtract(0,0.8,0).getBlock().getType() == Material.ICE ||
        		p.getLocation().clone().subtract(0,0.8,0).getBlock().getType() == Material.PACKED_ICE ||
        		p.getLocation().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.PACKED_ICE ||
        		p.getLocation().clone().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.ICE)) {
        	if(event.getFrom().getY() < event.getTo().getY() && p.isSprinting())
        	    dontIce.add(p.getName());
        } else if(!p.getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty() &&
        		!(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE) && 
        		!(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.PACKED_ICE)) {
        	if(dontIce.contains(p.getName())){
        		dontIce.remove(p.getName());
        	}
        }
        if(Main.toggleFly.containsKey(p.getName())) {
        	if(Main.toggleFly.get(p.getName()) > 0)
        	    Main.toggleFly.put(p.getName(), (short) (Main.toggleFly.get(p.getName())-1));
        	else Main.toggleFly.remove(p.getName());
        	return;
        }
        if(Main.is110ro1() || Main.is111ro1())
        	if(event.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION))
        		return;
        if(Main.is110ro1() || Main.is111ro1())
    		if(event.getPlayer().getItemInHand().getType().equals(Material.CHORUS_FRUIT))
    			return;
        if(Elytra.dontSpeed.contains(p.getName()))
        	return;
        if(event.getFrom().distance(event.getTo()) == 0 || HighJump.dont.contains(event.getPlayer().getName()))
        	return;
        if(p.isOnGround() && damagelist.contains(p))
        	damagelist.remove(p);
        else if(!p.isOnGround() && damagelist.contains(p))
        	return;
        if (p.hasPermission("Dakata.Bypass.Speed") || p.isInsideVehicle()) {
            return;
        }
        if (p.isFlying()) {
           return;
        }
        if (PlayerUtils.isUsingElytra(p)) {
            return;
        }
        if(p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.LADDER &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.VINE &&
        		!p.getLocation().getBlock().isLiquid()) {
        	return;
        }
        if(event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.LADDER &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.VINE &&
        		!event.getTo().getBlock().isLiquid()) {
        	return;
        }
        if(event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.LADDER &&
        		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.VINE &&
        		!event.getFrom().getBlock().isLiquid()) {
        	return;
        }
        
        if(p.getEyeLocation().clone().add(0,0.1,0).getBlock().getType() != Material.AIR &&
        		p.getEyeLocation().clone().add(0,0.1,0).getBlock().getType() != Material.LADDER &&
        		p.getEyeLocation().clone().add(0,0.1,0).getBlock().getType() != Material.VINE) {
        	return;
        }
        
        //Due to false positives with the speed potion
        if(p.hasPotionEffect(PotionEffectType.SPEED))
           if(PlayerUtils.getPotionEffect(p, PotionEffectType.SPEED).getAmplifier() > 40)
        	   return;
        final Location from = event.getFrom();
        final Location to = event.getTo();
        final double dX = from.getX() - to.getX();
        final double dZ = from.getZ() - to.getZ();
        final double distSq = dX * dX + dZ * dZ;
        //p.sendMessage(String.format("%.2f", from.distance(to))+ " ");
        //p.sendMessage("angle: "+PlayerUtils.getAngle(to, from, p.getLocation()));
        double speed = Speed.walk;
        if(p.isSprinting() && !PlayerUtils.isOnLadder(p)) {
    		if(p.getLocation().getPitch() >= 0) {
    			if(p.getLocation().getPitch() < 75 && p.getFoodLevel() >= 6){
    	        	if(PlayerUtils.getAngle(to, from, p.getLocation()) < 200){
         	        	speed = Speed.sprinting; 
    	        	}
    			} else speed = Speed.sprinting;
    		} else {
       		 if((p.getLocation().getPitch() > -75) && p.getFoodLevel() >= 6){
 	        	if(PlayerUtils.getAngle(to, from, p.getLocation()) < 200){
     	        	speed = Speed.sprinting; 
	        	}
 		      } else speed = Speed.sprinting; 
    		}
    		
        }
        if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.ICE) ||
        		p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.PACKED_ICE) ||
        		p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType().equals(Material.PACKED_ICE) ||
        		p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType().equals(Material.ICE))
        {
            speed = Speed.ice;
        }
        //if (p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.LAVA) {
        //    return;
        //}
        if (PlayerUtils.isOnStair(p)) {
        	if(p.isSprinting())
        		speed *= 5.1;
        	else speed *= Speed.stairs;
        }
        //if(p.getWalkSpeed() != 0.2 && from.getY() == to.getY())
        //	return; // CHECK WHEN JUMP
        if (PlayerUtils.isOnHalfSlab(p)) {
            speed *= Speed.slab;
        }
        if (isJumping(p, from, to)) {
            speed *= Speed.jump;
        }
        if (p.isSneaking()) {
            speed = Speed.sneaking;
        }
        if ((p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) && p.getLocation().getBlock().getData() <= 2 && event.getFrom().getY() <= event.getTo().getY()) {
            //if(Main.is110ro1()){
        	try{
            	if(p.getInventory().getBoots() != null) {
            	   if(p.getInventory().getBoots().containsEnchantment(Enchantment.DEPTH_STRIDER)) {
            		   if(p.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) == 1)
                    	   speed = 0.39795;
            		   else if(p.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) == 2)
                    	   speed = 0.649;
            		   else if(p.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) == 3)
                    	   speed = 0.83;
                	   } else speed = Speed.water;
            	   } else speed = Speed.water;}
        	catch(Exception e)
        	{
        		speed = Speed.water;
        	}
            	   //}else speed = Speed.water;
        }
        if ((p.getLocation().getBlock().getType() == Material.STATIONARY_LAVA) && p.getLocation().getBlock().getData() == 0 && event.getFrom().getY() <= event.getTo().getY()) {
        	speed = Speed.lava;
        }
        if (p.isBlocking() ) {
            speed = Speed.item;
        }
        
        if (p.getLocation().clone().subtract(0.0, 0.8, 0.0).getBlock().getType() == Material.SOUL_SAND) {
            if (p.getLocation().clone().subtract(0.0, 1.8, 0.0).getBlock().getType() == Material.ICE) {
                if(PlayerUtils.isNextToBlock2Different(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.SOUL_SAND, 0.1))//Test for a better value
                	speed = Speed.sousand_ice;
            }
            else {
            	if(PlayerUtils.isNextToBlock2Different(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Material.SOUL_SAND, 0.1))//Test for a better value
                   speed = Speed.soulsand;
            }
        }
        if (PlayerUtils.isInWeb(p, 0.3)) {
        	if(event.getFrom().getY() <= event.getTo().getY()) {
        		if(p.isSprinting())
        		    speed = Speed.cobweb;
        		else speed = cobwebSprint;
        	}
        }
        if (p.hasPotionEffect(PotionEffectType.SPEED)) {
        	//speed *= (PlayerUtils.getPotionEffect(p, PotionEffectType.SPEED).getAmplifier() + 1) * speedPotion;
            speed *= (PlayerUtils.getPotionEffect(p, PotionEffectType.SPEED).getAmplifier() + 1) * Speed.speedPotion + 1.3;
        } else if (p.hasPotionEffect(PotionEffectType.SLOW)) {
            speed *= (PlayerUtils.getPotionEffect(p, PotionEffectType.SLOW).getAmplifier() + 1) * Speed.slowPotion + 1.3;
        }
        
        if(p.getOpenInventory().getCursor().getType() != Material.AIR && p.getOpenInventory().getType().equals(InventoryType.CRAFTING))
        	speed = 0;
        if(speed < 0) {
        	speed = Speed.sprinting;
        	if(isJumping(p, from, to))
            	speed *= Speed.jump;
        }
        //if(ImposiblleAttack.as.contains(p.getName()))//Inv open
        //	speed = 0.0;
        
        if (event.getFrom().getY() < event.getTo().getY() && !PlayerUtils.isOnLadder2(p)) {
        	if(!event.getPlayer().isSneaking())
        	{
        		if(PlayerUtils.isInLiquidLoc(p.getLocation()) /*|| PlayerUtils.isInLiquidLoc(p.getEyeLocation())*/)
        			speed *= 0.925; //False positive but helps in some hacks :/
        			//return;
        		else speed *= 0.896;
                if (Speed.previousMove.containsKey(p.getName())) {
                    if (event.getTo().getY() - event.getFrom().getY() == Speed.previousMove.get(p.getName())) {
                        if (Speed.maxVL < 15) {
                            if (Speed.setBacks.containsKey(p)) {
                                Speed.setBacks.put(p, Speed.setBacks.get(p) + 2);
                            }
                            else {
                                Speed.setBacks.put(p, 2);
                            }
                        }
                        else if (Speed.setBacks.containsKey(p)) {
                            Speed.setBacks.put(p, Speed.setBacks.get(p) + 3);
                        }
                        else {
                            Speed.setBacks.put(p, 3);
                        }
                    }
                    Speed.previousMove.remove(p.getName());
                }
                else {
                    Speed.previousMove.put(p.getName(), event.getTo().getY() - event.getFrom().getY());
                }
        	} else speed *= 1.6;
        }//TODO: Not sure about this, take a look (i mean speed *= 0.896)
        speed *= 0.1;
        
        //p.sendMessage(""+(speed == Speed.sprinting*0.896*0.1));
        //if(!from.getBlock().getRelative(BlockFace.DOWN).isEmpty() && to.clone().subtract(0,0.00001,0).getBlock().isEmpty())
        //p.sendMessage(""+distSq+" "+speed+" "/*+(int) (2 * ((distSq - speed)/0.1))+" "+(event.getTo().getY()-event.getFrom().getY())*/);
        if (distSq > speed) {
            if (!Speed.setBack.containsKey(p)) {
                Speed.setBack.put(p, from);
            }
            //p.sendMessage(""+distSq+" "+speed+" "/*+(int) (2 * ((distSq - speed)/0.1))+" "+(event.getTo().getY()-event.getFrom().getY())*/);
            //p.sendMessage(""+distSq+" "+speed+" "+setBacks.get(p)+" "+((int) (2 * ((distSq - speed)/0.1))));
        	if((speed == Speed.jump*Speed.sprinting*0.896*0.1) && !event.getFrom().getBlock().getRelative(BlockFace.DOWN).isEmpty()){//BHop
                if(p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
                		p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.STATIONARY_WATER) {
                	return;
                }
                if(event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
                		event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.STATIONARY_WATER) {
                	return;
                }
                if(event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
                		event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.STATIONARY_WATER) {
                	return;
                }
                if(PlayerUtils.getLastOnGroundLocation(p).getBlock().getType().equals(Material.ICE) ||
                		PlayerUtils.getLastOnGroundLocation(p).getBlock().getType().equals(Material.PACKED_ICE) ||
                		PlayerUtils.getLastOnGroundLocation(p).subtract(0,0.5,0).getBlock().getType().equals(Material.PACKED_ICE) ||
                		PlayerUtils.getLastOnGroundLocation(p).subtract(0,0.5,0).getBlock().getType().equals(Material.ICE))
                	return;
                if(PlayerUtils.isNextToBlock2Different(event.getPlayer().getEyeLocation(), Material.AIR, 0.3))
                	return;
                //p.sendMessage("bhop1: "+(distSq-speed));
        		if(distSq-speed > 0.29)
        		{
        			//p.sendMessage("HACKER!!! "+(distSq-speed));
        	        if(!dontIce.contains(p.getName())){//Stop BHOP on ice, because of false positives
                        if(setBacks.get(p) == null)
                        	setBacks.put(p, 0);
        	        	if(setBacks.get(p) <= 1)
        	        	    Speed.setBacks.put(p, (int) (maxVL));
                        else setBacks.put(p, setBacks.get(p)+2);
                        Hackers.addSpeed(event.getPlayer());
        	        }
        		}
        		} else if(speed == Speed.sprinting*0.1 && !event.getFrom().getBlock().getRelative(BlockFace.DOWN).isEmpty())
        		{
        	        if(p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
        	        if(event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		!event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
        	        if(event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		!event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
        			if(distSq-speed > 0.315)//Good to test for a smaller value
            		{
                        if(setBacks.get(p) == null)
                        	setBacks.put(p, 0);
                        setBacks.put(p, setBacks.get(p)+2);
                        PlayerCheatEvent ass = new PlayerCheatEvent(event.getPlayer(), CheatType.SPEED, false);
                        Bukkit.getPluginManager().callEvent(ass);
                        if(!ass.isCancelled()) {
                        Hackers.addSpeed(event.getPlayer());
                        Hackers.isReadyForSpeedMessage(p);
                        }
            		}
        		} else if(speed == Speed.cobwebSprint*0.896*0.1) {
        			if(distSq-speed > 0.018)
        			{
                        if(setBacks.get(p) == null)
                        	setBacks.put(p, 0);
                        if(setBacks.get(p) <= 1)
        	        	    Speed.setBacks.put(p, (int) (maxVL-2));
                        else setBacks.put(p, setBacks.get(p)+2);
                        PlayerCheatEvent ass = new PlayerCheatEvent(event.getPlayer(), CheatType.SPEED, false);
                        Bukkit.getPluginManager().callEvent(ass);
                        if(!ass.isCancelled()) {
                            p.teleport(setBack.get(p));
                            Hackers.addSpeed(event.getPlayer());
                            Hackers.addSpeed(event.getPlayer());
                            Hackers.isReadyForSpeedMessage(p);
                        }
        			}
        		} else if(speed == Speed.sprinting*0.896*0.1) {
        	        if(p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		p.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
        	        if(event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		!event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
        	        if(event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR &&
        	        		!event.getFrom().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).isLiquid()) {
        	        	return;
        	        }
                    //p.sendMessage("bhop: "+(distSq-speed));
        			if(distSq-speed > 0.34)
        			{
        				//p.sendMessage("hacker "+(distSq-speed));
                        if(setBacks.get(p) == null)
                        	setBacks.put(p, 0);
                        if(setBacks.get(p) <= 1)
        	        	    Speed.setBacks.put(p, (int) (maxVL-2));
                        else setBacks.put(p, setBacks.get(p)+2);
                        PlayerCheatEvent ass = new PlayerCheatEvent(event.getPlayer(), CheatType.SPEED, false);
                        Bukkit.getPluginManager().callEvent(ass);
                        if(!ass.isCancelled()) {
                            p.teleport(setBack.get(p));
                            Hackers.addSpeed(event.getPlayer());
                            Hackers.isReadyForSpeedMessage(p);
                        }
        			}
        		}
        	
            if (!Speed.setBacks.containsKey(p)) {
                Speed.setBacks.put(p, 0);
            }
            else {
                if (Speed.setBacks.get(p) > Speed.maxVL) {
                	Speed.setBacks.remove(p);
                    PlayerCheatEvent ass = new PlayerCheatEvent(event.getPlayer(), CheatType.SPEED, false);
                    Bukkit.getPluginManager().callEvent(ass);
                    if(!ass.isCancelled()) {
                        if(ImposiblleAttack.as.containsKey(p.getName()))
                        	p.getOpenInventory().close();
                        p.teleport(Speed.setBack.get(p));
                        Hackers.addSpeed(event.getPlayer());
                        if (Hackers.isReadyForSpeedMessage(event.getPlayer())) {
                            if (DacStringBase.log_console) {
                                try {
                                    Bukkit.getLogger().log(Level.INFO, String.valueOf(DacStringBase.anticheat_tag.replaceAll("&a", "").replaceAll("&b", "").replaceAll("&6", "").replaceAll("&0", "").replaceAll("&1", "").replaceAll("\ufffd2", "").replaceAll("&2", "").replaceAll("&4", "").replaceAll("&5", "").replaceAll("&7", "").replaceAll("&8", "").replaceAll("&9", "").replaceAll("&c", "").replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")) + DacStringBase.hack_msg.replaceAll("<hack>", "Speed(speed: " + distSq + ", expected: " + speed + ") (mode: "+getMode(speed, p)+")").replaceAll("<player>", event.getPlayer().getName()).replaceAll("<world>", event.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(event.getPlayer()))));
                                }
                                catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (DacStringBase.log_player) {
                            	if(Main.isUsingPlaceholderAPI())
    								try {
    									Main.sendMessagesToAllServers(PlaceholderAPI.setPlaceholders((Player) event.getPlayer(), String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", event.getPlayer().getName()).replaceAll("<world>", event.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(event.getPlayer())))));
    								} catch (Exception e2) {
    									// TODO Auto-generated catch block
    									e2.printStackTrace();
    								}
    							else
    								try {
    									Main.sendMessagesToAllServers(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Criticals").replaceAll("<player>", event.getPlayer().getName()).replaceAll("<world>", event.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(event.getPlayer()))));
    								} catch (Exception e1) {
    									// TODO Auto-generated catch block
    									e1.printStackTrace();
    								}
                                for (final Player pa : Bukkit.getOnlinePlayers()) {
                                    if (pa.hasPermission("Dakata.Admin")) {
                                        try {
                                            pa.sendMessage(String.valueOf(DacStringBase.anticheat_tag) + DacStringBase.hack_msg.replaceAll("<hack>", "Speed(speed = " + distSq + ", expected = " + speed + ") (mode = "+getMode(speed, p)+")").replaceAll("<player>", event.getPlayer().getName()).replaceAll("<world>", event.getPlayer().getWorld().getName()).replaceAll("<tps>", Double.toString(TPS.getTPS())).replaceAll("<ping>", Integer.toString(Ping.getPlayerPing(event.getPlayer()))));
                                        }
                                        catch (Exception e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
                if(dontIce.contains(p.getName()))
                	return;
                final int d = Speed.setBacks.get(p);
                int i = 0;
                if(distSq - speed > 0.02)
                    i = (int) (2 * ((distSq - speed)/0.1));
                if(i > 0)
                    Speed.setBacks.put(p, d+i);
                else Speed.setBacks.put(p, d+1);
                //Speed.setBacks.put(p, 0);
            }
        }
        else {
            Speed.setBack.put(p, from);
            if (Speed.setBacks.containsKey(p)) {
                final int d = Speed.setBacks.get(p);
                if (d >= 2) {
                    if(p.getLocation().getBlock().isLiquid())
                    	if(p.getLocation().getBlock().getData() <= 2 && event.getFrom().getY() > event.getTo().getY())
                    		return;
                    Speed.setBack.remove(p);
                    Speed.setBacks.put(p, d - 2);
                } else setBacks.remove(p);
            }
        }
    }
    
    private static String getMode(double speed, Player p) {
		if(speed == Speed.sprinting*0.1)
			return "sprint";
		if(speed == Speed.sprinting*Speed.jump*0.896*0.1)
			return "sprint_jump";
		else if(speed == Speed.cobweb*0.1)
			return "cobweb";
		else if(speed == Speed.walk*0.1)
			return "walk";
		if(speed == Speed.walk*Speed.jump*0.896*0.1)
			return "walk_jump";
		else if(speed == Speed.ice*0.1)
			return "ice";
		else if(speed == Speed.ice*Speed.jump*0.896*0.1)
			return "ice_y";
		else if(speed == Speed.lava*0.1)
			return "lava";
		else if(speed == Speed.lava*0.1*0.896)
			return "lava_y";
		else if(speed == Speed.water*0.1)
			return "water";
		else if(speed == Speed.water*0.925*0.1)
			return "water_y";
		else if(speed == Speed.walk*Speed.slab*0.1)
			return "walk_slab";
		else if(speed == Speed.sprinting*Speed.slab*0.1)
			return "sprint_slab";
		else if(speed == Speed.cobweb*0.1)
			return "cobweb";
		else if(speed == Speed.sneaking*0.1)
			return "sneaking";
		else if(speed == Speed.sneaking*1.6*0.1)
			return "sneaking";
		else if(speed == Speed.soulsand*0.1)
			return "soulsand";
		else if(speed == Speed.sousand_ice*0.1)
			return "soulsand_ice";
		else if(speed == Speed.stairs*0.1)
			return "stairs";
		else if(speed == Speed.item*0.1)
			return "item";
		else if(speed == Speed.item*0.1*0.896)
			return "item_y";
		else if(speed == 0)
			return "inventory";
		return "undefined";
	}

	private static boolean isJumping(final Player p, final Location from, final Location to) {
		//p.sendMessage("d: "+(to.getY()-from.getY()));
        return (!PlayerUtils.isLocationOnGround(to));
    }
    
    
    public static void onEntytyDmg(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            damagelist.add((Player)e.getEntity());
        }
        damagelist2.put(e.getEntity().getName(), (short) 10);
    }
}
