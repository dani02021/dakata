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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import bg.anticheat.dakata.Main;

public class Hackers implements Listener
{
	private static HashMap<String, Short> fly;
	private static HashMap<String, Long> flyTime;
    private static HashMap<String, Short> boatfly;
	private static HashMap<String, Long> boatflyTime;
    private static HashMap<String, Short> extraelytra;
	private static HashMap<String, Long> extraelytraTime;
    private static HashMap<String, Short> fastfall;
	private static HashMap<String, Long> fastfallTime;
    private static HashMap<String, Short> fastladder;
	private static HashMap<String, Long> fastladderTime;
    private static HashMap<String, Short> glide;
	private static HashMap<String, Long> glideTime;
    private static HashMap<String, Short> highjump;
	private static HashMap<String, Long> highjumpTime;
    private static HashMap<String, Short> nofall;
	private static HashMap<String, Long> nofallTime;
    private static HashMap<String, Short> speed;
	private static HashMap<String, Long> speedTime;
    private static HashMap<String, Short> autosign;
	private static HashMap<String, Long> autosignTime;
    private static HashMap<String, Short> reach;
	private static HashMap<String, Long> reachTime;
    private static HashMap<String, Short> noslowdown;
	private static HashMap<String, Long> noslowdownTime;
    private static HashMap<String, Short> noswingBlock;
	private static HashMap<String, Long> noswingBlockTime;
    private static HashMap<String, Short> fastplace;
	private static HashMap<String, Long> fastplaceTime;
    private static HashMap<String, Short> nobreakdelay;
	private static HashMap<String, Long> nobreakdelayTime;
    private static HashMap<String, Short> fastbow;
	private static HashMap<String, Long> fastbowTime;
    private static HashMap<String, Short> cheststeal;
	private static HashMap<String, Long> cheststealTime;
    private static HashMap<String, Short> fasteat;
	private static HashMap<String, Long> fasteatTime;
    private static HashMap<String, Short> invalidinv;
	private static HashMap<String, Long> invalidinvTime;
    private static HashMap<String, Short> invalidpotion;
	private static HashMap<String, Long> invalidpotionTime;
    private static HashMap<String, Short> regen;
	private static HashMap<String, Long> regenTime;
    private static HashMap<String, Short> killaurathrublocks;
	private static HashMap<String, Long> killaurathrublocksTime;
    private static HashMap<String, Short> criticals;
	private static HashMap<String, Long> criticalsTime;
    private static HashMap<String, Short> imposibleAttack;
	private static HashMap<String, Long> imposibleAttackTime;
    private static HashMap<String, Short> slimejump;
	private static HashMap<String, Long> slimejumpTime;
    private static HashMap<String, Short> invalidhead;
	private static HashMap<String, Long> invalidheadTime;
	private static HashMap<String, Short> illegalMove;
	private static HashMap<String, Long> illegalMoveTime;
	private static HashMap<String, Short> moreMoves;
	private static HashMap<String, Long> moreMovesTime;
	private static HashMap<String, Short> waterY;
	private static HashMap<String, Long> waterYTime;
	private static HashMap<String, Short> invalidVelocity;
	private static HashMap<String, Long> invalidVelocityTime;
	private static HashMap<String, Short> antiCactus;
	private static HashMap<String, Long> antiCactusTime;
	private static HashMap<String, Short> waterWalk;
	private static HashMap<String, Long> waterWalkTime;
	private static HashMap<String, Short> noKnockback;
	private static HashMap<String, Long> noKnockbackTime;
	private static HashMap<String, Short> maxInteracts;
	private static HashMap<String, Long> maxInteractsTime;
	private static HashMap<String, Short> headless;
	private static HashMap<String, Long> headlessTime;
	
    private static int task;
    
    static {
        Hackers.fly = new HashMap<String, Short>();
        Hackers.flyTime = new HashMap<String, Long>();
        
        Hackers.boatfly = new HashMap<String, Short>();
        Hackers.boatflyTime = new HashMap<String, Long>();
        
        Hackers.extraelytra = new HashMap<String, Short>();
        Hackers.extraelytraTime = new HashMap<String, Long>();
        
        Hackers.fastfall = new HashMap<String, Short>();
        Hackers.fastfallTime = new HashMap<String, Long>();
        
        Hackers.fastladder = new HashMap<String, Short>();
        Hackers.fastladderTime = new HashMap<String, Long>();
        
        Hackers.glide = new HashMap<String, Short>();
        Hackers.glideTime = new HashMap<String, Long>();
        
        Hackers.highjump = new HashMap<String, Short>();
        Hackers.highjumpTime = new HashMap<String, Long>();
        
        Hackers.nofall = new HashMap<String, Short>();
        Hackers.nofallTime = new HashMap<String, Long>();
        
        Hackers.speed = new HashMap<String, Short>();
        Hackers.speedTime = new HashMap<String, Long>();
        
        Hackers.autosign = new HashMap<String, Short>();
        Hackers.autosignTime = new HashMap<String, Long>();
        
        Hackers.reach = new HashMap<String, Short>();
        Hackers.reachTime = new HashMap<String, Long>();
        //Con
        Hackers.noslowdown = new HashMap<String, Short>();
        Hackers.noslowdownTime = new HashMap<String, Long>();
        
        Hackers.noswingBlock = new HashMap<String, Short>();
        Hackers.noswingBlockTime = new HashMap<String, Long>();
        
        Hackers.fastplace = new HashMap<String, Short>();
        Hackers.fastplaceTime = new HashMap<String, Long>();
        
        Hackers.nobreakdelay = new HashMap<String, Short>();
        Hackers.nobreakdelayTime = new HashMap<String, Long>();
        
        Hackers.fastbow = new HashMap<String, Short>();
        Hackers.fastbowTime = new HashMap<String, Long>();
        
        Hackers.cheststeal = new HashMap<String, Short>();
        Hackers.cheststealTime = new HashMap<String, Long>();
        
        Hackers.fasteat = new HashMap<String, Short>();
        Hackers.fasteatTime = new HashMap<String, Long>();
        
        Hackers.invalidinv = new HashMap<String, Short>();
        Hackers.invalidinvTime = new HashMap<String, Long>();
        
        Hackers.invalidpotion = new HashMap<String, Short>();
        Hackers.invalidpotionTime = new HashMap<String, Long>();
        
        Hackers.regen = new HashMap<String, Short>();
        Hackers.regenTime = new HashMap<String, Long>();
        
        Hackers.killaurathrublocks = new HashMap<String, Short>();
        Hackers.killaurathrublocksTime = new HashMap<String, Long>();
        
        Hackers.criticals = new HashMap<String, Short>();
        Hackers.criticalsTime = new HashMap<String, Long>();
        
        Hackers.imposibleAttack = new HashMap<String, Short>();
        Hackers.imposibleAttackTime = new HashMap<String, Long>();
        
        Hackers.slimejump = new HashMap<String, Short>();
        Hackers.slimejumpTime = new HashMap<String, Long>();
        
        Hackers.invalidhead = new HashMap<String, Short>();
        Hackers.invalidheadTime = new HashMap<String, Long>();
        
        Hackers.illegalMove = new HashMap<String, Short>();
        Hackers.illegalMoveTime = new HashMap<String, Long>();
        
    	Hackers.moreMoves = new HashMap<String, Short>();
    	Hackers.moreMovesTime = new HashMap<String, Long>();
    	
    	Hackers.waterY = new HashMap<String, Short>();
    	Hackers.waterYTime = new HashMap<String, Long>();
    	
    	Hackers.invalidVelocity = new HashMap<String, Short>();
    	Hackers.invalidVelocityTime = new HashMap<String, Long>();
    	
    	Hackers.waterWalk = new HashMap<String, Short>();
    	Hackers.waterWalkTime = new HashMap<String, Long>();
    	
    	Hackers.noKnockback = new HashMap<String, Short>();
    	Hackers.noKnockbackTime = new HashMap<String, Long>();
    	
    	Hackers.antiCactus = new HashMap<String, Short>();
    	Hackers.antiCactusTime = new HashMap<String, Long>();
    	
    	Hackers.maxInteracts = new HashMap<String, Short>();
    	Hackers.maxInteractsTime = new HashMap<String, Long>();
    	
    	Hackers.headless = new HashMap<String, Short>();
    	Hackers.headlessTime = new HashMap<String, Long>();
        
        Hackers.task = 0;
    }
    
    public static void addFly(final Player p) {
    	if(!flyTime.containsKey(p.getName()))
    	    flyTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fly.containsKey(p.getName())) {
            short a = Hackers.fly.get(p.getName());
            ++a;
            Hackers.fly.put(p.getName(), a);
        }
        else {
            Hackers.fly.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFlyMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.flyThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fly.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-flyTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)) {
        	flyTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addBoatFly(final Player p) {
    	if(!boatflyTime.containsKey(p.getName()))
    	    boatflyTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.boatfly.containsKey(p.getName())) {
            short a = Hackers.boatfly.get(p.getName());
            ++a;
            Hackers.boatfly.put(p.getName(), a);
        }
        else {
            Hackers.boatfly.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForBoatFlyMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.boatFlyThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.boatfly.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-boatflyTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	boatflyTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addExtraElytra(final Player p) {
    	if(!extraelytraTime.containsKey(p.getName()))
    	    extraelytraTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.extraelytra.containsKey(p.getName())) {
            short a = Hackers.extraelytra.get(p.getName());
            ++a;
            Hackers.extraelytra.put(p.getName(), a);
        }
        else {
            Hackers.extraelytra.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForExtraElytraMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.extraElytraThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.extraelytra.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-extraelytraTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	extraelytraTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addFastFall(final Player p) {
    	if(!fastfallTime.containsKey(p.getName()))
    	    fastfallTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fastfall.containsKey(p.getName())) {
            short a = Hackers.fastfall.get(p.getName());
            ++a;
            Hackers.fastfall.put(p.getName(), a);
        }
        else {
            Hackers.fastfall.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFastFallMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.fastFallThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fastfall.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-fastfallTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	fastfallTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addGlide(final Player p) {
    	if(!glideTime.containsKey(p.getName()))
    		glideTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.glide.containsKey(p.getName())) {
            short a = Hackers.glide.get(p.getName());
            ++a;
            Hackers.glide.put(p.getName(), a);
        }
        else {
            Hackers.glide.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForGlideMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.glideThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.glide.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-glideTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	glideTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addHighJump(final Player p) {
    	if(!highjumpTime.containsKey(p.getName()))
    		highjumpTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.highjump.containsKey(p.getName())) {
            short a = Hackers.highjump.get(p.getName());
            ++a;
            Hackers.highjump.put(p.getName(), a);
        }
        else {
            Hackers.highjump.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForHighJumpMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.highJumpThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
           if(h != null && h.getKey() != null && p.getName() != null && Hackers.highjump.get(p.getName()) != null)
        	   if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.highjump.get(p.getName())))) {
                String value = (String) h.getValue();
                if (value.contains("<player>")) {
                	value = value.replaceAll("<player>", p.getName());
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-highjumpTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	highjumpTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addFastLadder(final Player p) {
    	if(!fastladderTime.containsKey(p.getName()))
    		fastladderTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fastladder.containsKey(p.getName())) {
            short a = Hackers.fastladder.get(p.getName());
            ++a;
            Hackers.fastladder.put(p.getName(), a);
        }
        else {
            Hackers.fastladder.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFastLadderMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.fastLadderThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fastladder.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-fastladderTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	fastladderTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addNoFall(final Player p) {
    	if(!nofallTime.containsKey(p.getName()))
    		nofallTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.nofall.containsKey(p.getName())) {
            short a = Hackers.nofall.get(p.getName());
            ++a;
            Hackers.nofall.put(p.getName(), a);
        }
        else {
            Hackers.nofall.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForNoFallMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noFallThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.nofall.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-nofallTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	nofallTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addSpeed(final Player p) {
    	if(!speedTime.containsKey(p.getName()))
    		speedTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.speed.containsKey(p.getName())) {
            short a = Hackers.speed.get(p.getName());
            ++a;
            Hackers.speed.put(p.getName(), a);
        }
        else {
            Hackers.speed.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForSpeedMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.speedThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.speed.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-speedTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	speedTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addAutoSign(final Player p) {
    	if(!autosignTime.containsKey(p.getName()))
    		autosignTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.autosign.containsKey(p.getName())) {
            short a = Hackers.autosign.get(p.getName());
            ++a;
            Hackers.autosign.put(p.getName(), a);
        }
        else {
            Hackers.autosign.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForAutoSignMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.autoSignThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.autosign.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-autosignTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	autosignTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addReach(final Player p) {
    	if(!reachTime.containsKey(p.getName()))
    		reachTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.reach.containsKey(p.getName())) {
            short a = Hackers.reach.get(p.getName());
            ++a;
            Hackers.reach.put(p.getName(), a);
        }
        else {
            Hackers.reach.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForReachMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.reachThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.reach.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-reachTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	reachTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addNoSlowdown(final Player p) {
    	if(!noslowdownTime.containsKey(p.getName()))
    		noslowdownTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.noslowdown.containsKey(p.getName())) {
            short a = Hackers.noslowdown.get(p.getName());
            ++a;
            Hackers.noslowdown.put(p.getName(), a);
        }
        else {
            Hackers.noslowdown.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForNoSlowdownMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noSlowdownThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.noslowdown.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-noslowdownTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	noslowdownTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addFastPlace(final Player p) {
    	if(!fastplaceTime.containsKey(p.getName()))
    		fastplaceTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fastplace.containsKey(p.getName())) {
            short a = Hackers.fastplace.get(p.getName());
            ++a;
            Hackers.fastplace.put(p.getName(), a);
        }
        else {
            Hackers.fastplace.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFastPlaceMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.fastPlaceThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fastplace.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-fastplaceTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	fastplaceTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static HashMap<String, Short> getFastPlaceList(final Player p) {
    	return fastplace;
    }
    
    public static void addNoBrakDelay(final Player p) {
    	if(!nobreakdelayTime.containsKey(p.getName()))
    		nobreakdelayTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.nobreakdelay.containsKey(p.getName())) {
            short a = Hackers.nobreakdelay.get(p.getName());
            ++a;
            Hackers.nobreakdelay.put(p.getName(), a);
        }
        else {
            Hackers.nobreakdelay.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForNoBreakDelayMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noBreakDelayThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.nobreakdelay.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-nobreakdelayTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	nobreakdelayTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static boolean isReadyForNoBreakDelayMessage(final Player p, short violation) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noBreakDelayThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.nobreakdelay.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(Hackers.nobreakdelay.get(p.getName()) > violation &&
                System.currentTimeMillis()-nobreakdelayTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
                	nobreakdelayTime.remove(p.getName());
                    return true;
                }
        return false;
    }
    
    public static HashMap<String, Short> getNoBreakDelayList() {
    	return nobreakdelay;
    }
    
    public static void addFastBow(final Player p) {
    	if(!fastbowTime.containsKey(p.getName()))
    		fastbowTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fastbow.containsKey(p.getName())) {
            short a = Hackers.fastbow.get(p.getName());
            ++a;
            Hackers.fastbow.put(p.getName(), a);
        }
        else {
            Hackers.fastbow.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFastBowMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.fastBowThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fastbow.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        try{
            if(System.currentTimeMillis()-fastbowTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
            	fastbowTime.remove(p.getName());
                return true;
            }
        } catch(NullPointerException e)
        {
            return true;
        }
        return false;
    }
    
    public static void addInvalidInv(final Player p) {
    	if(!invalidinvTime.containsKey(p.getName()))
    		invalidinvTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.invalidinv.containsKey(p.getName())) {
            short a = Hackers.invalidinv.get(p.getName());
            ++a;
            Hackers.invalidinv.put(p.getName(), a);
        }
        else {
            Hackers.invalidinv.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForInvalidInvMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.invalidInventoryThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.invalidinv.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-invalidinvTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	invalidinvTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addInvalidPotion(final Player p) {
    	if(!invalidpotionTime.containsKey(p.getName()))
    		invalidpotionTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.invalidpotion.containsKey(p.getName())) {
            short a = Hackers.invalidpotion.get(p.getName());
            ++a;
            Hackers.invalidpotion.put(p.getName(), a);
        }
        else {
            Hackers.invalidpotion.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForInvalidPotionMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.invalidPotionThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.invalidpotion.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-invalidpotionTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	invalidpotionTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addRegen(final Player p) {
	if(!regenTime.containsKey(p.getName()))
		regenTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.regen.containsKey(p.getName())) {
            short a = Hackers.regen.get(p.getName());
            ++a;
            Hackers.regen.put(p.getName(), a);
        }
        else {
            Hackers.regen.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForRegenMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.regenThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.regen.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-regenTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	regenTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addChestSteal(final Player p) {
    	if(!cheststealTime.containsKey(p.getName()))
    		cheststealTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.cheststeal.containsKey(p.getName())) {
            short a = Hackers.cheststeal.get(p.getName());
            ++a;
            Hackers.cheststeal.put(p.getName(), a);
        }
        else {
            Hackers.cheststeal.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForChestStealMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.chestStealThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.cheststeal.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-cheststealTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	cheststealTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addFastEat(final Player p) {
    	if(!fasteatTime.containsKey(p.getName()))
    		fasteatTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.fasteat.containsKey(p.getName())) {
            short a = Hackers.fasteat.get(p.getName());
            ++a;
            Hackers.fasteat.put(p.getName(), a);
        }
        else {
            Hackers.fasteat.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForFastEatMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.fastEatThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.fasteat.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-fasteatTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	fasteatTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addKillAuraThruBlocks(final Player p) {
    	if(!killaurathrublocksTime.containsKey(p.getName()))
    		killaurathrublocksTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.killaurathrublocks.containsKey(p.getName())) {
            short a = Hackers.killaurathrublocks.get(p.getName());
            ++a;
            Hackers.killaurathrublocks.put(p.getName(), a);
        }
        else {
            Hackers.killaurathrublocks.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForKillAuraThruBlocksMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.killAuraThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.killaurathrublocks.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-killaurathrublocksTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	killaurathrublocksTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static short getKillAuraThruBlocksViolationLevel(final Player p) {
        return (short) killaurathrublocks.size();
    }
    
    public static void addNoSwingBlock(final Player p) {
    	if(!noswingBlockTime.containsKey(p.getName()))
    		noswingBlockTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.noswingBlock.containsKey(p.getName())) {
            short a = Hackers.noswingBlock.get(p.getName());
            ++a;
            Hackers.noswingBlock.put(p.getName(), a);
        }
        else {
            Hackers.noswingBlock.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForNoSwingBlockMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noSwingBlockThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.noswingBlock.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
            
        }
        if(System.currentTimeMillis()-noswingBlockTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	noswingBlockTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addCriticals(final Player p) {
    	if(!criticalsTime.containsKey(p.getName()))
    		criticalsTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.criticals.containsKey(p.getName())) {
            short a = Hackers.criticals.get(p.getName());
            ++a;
            Hackers.criticals.put(p.getName(), a);
        }
        else {
            Hackers.criticals.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForCriticalsMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.criticalsThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.criticals.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-criticalsTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	criticalsTime.remove(p.getName());
            return true;
        }
        return false;
    }
    public static boolean isReadyForCriticalsMessage(final Player p, short violation) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.criticalsThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.criticals.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(Hackers.criticals.get(p.getName()) > violation &&
                System.currentTimeMillis()-criticalsTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
                	criticalsTime.remove(p.getName());
                    return true;
                }
        return false;
    }
    
    public static HashMap<String, Short> getCriticalsList() {
    return criticals;
    }
    
    public static void addImposibleAttack(final Player p) {
    	if(!imposibleAttackTime.containsKey(p.getName()))
    		imposibleAttackTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.imposibleAttack.containsKey(p.getName())) {
            short a = Hackers.imposibleAttack.get(p.getName());
            ++a;
            Hackers.imposibleAttack.put(p.getName(), a);
        }
        else {
            Hackers.imposibleAttack.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForImposibleAttackMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.imposibleAttackThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.imposibleAttack.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-imposibleAttackTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	imposibleAttackTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addSlimeJump(final Player p) {
    	if(!slimejumpTime.containsKey(p.getName()))
    		slimejumpTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.slimejump.containsKey(p.getName())) {
            short a = Hackers.slimejump.get(p.getName());
            ++a;
            Hackers.slimejump.put(p.getName(), a);
        }
        else {
            Hackers.slimejump.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForSlimeJumpMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.slimeJumpThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.slimejump.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-slimejumpTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	slimejumpTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addInvalidHead(final Player p) {
    	if(!invalidheadTime.containsKey(p.getName()))
    		invalidheadTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.invalidhead.containsKey(p.getName())) {
            short a = Hackers.invalidhead.get(p.getName());
            ++a;
            Hackers.invalidhead.put(p.getName(), a);
        }
        else {
            Hackers.invalidhead.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForInvalidHeadMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.invalidHeadThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.invalidhead.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-invalidheadTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	invalidheadTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static short getInvalidHeadViolation(final Player p) {
    	return invalidhead.get(p.getName());
    }
    
    public static void addIllegalMove(final Player p) {
    	if(!illegalMoveTime.containsKey(p.getName()))
    		illegalMoveTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.illegalMove.containsKey(p.getName())) {
            short a = Hackers.illegalMove.get(p.getName());
            ++a;
            Hackers.illegalMove.put(p.getName(), a);
        }
        else {
            Hackers.illegalMove.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static void removeIllegalMove(final Player p) {
        if (Hackers.illegalMove.containsKey(p.getName())) {
            short a = Hackers.illegalMove.get(p.getName());
            --a;
            Hackers.illegalMove.put(p.getName(), a);
        }
        else {
            Hackers.illegalMove.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static HashMap<String, Short> getIllegalMoveList(final Player p) {
    	return illegalMove;
    }
    
    public static boolean isReadyForIllegalMoveMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.illegalMoveThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.illegalMove.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-illegalMoveTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	illegalMoveTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static short getIllegalMoveViolation(final Player p) {
    	return illegalMove.get(p.getName());
    }
    
    public static void addMoreMoves(final Player p) {
    	if(!moreMovesTime.containsKey(p.getName()))
    		moreMovesTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.moreMoves.containsKey(p.getName())) {
            short a = Hackers.moreMoves.get(p.getName());
            ++a;
            Hackers.moreMoves.put(p.getName(), a);
        }
        else {
            Hackers.moreMoves.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForMoreMovesMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.moreMovesThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.moreMoves.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-moreMovesTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	moreMovesTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addWaterY(final Player p) {
    	if(!waterYTime.containsKey(p.getName()))
    		waterYTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.waterY.containsKey(p.getName())) {
            short a = Hackers.waterY.get(p.getName());
            ++a;
            Hackers.waterY.put(p.getName(), a);
        }
        else {
            Hackers.waterY.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForWaterYMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.waterYThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.waterY.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-waterYTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	waterYTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addInvalidVelocity(final Player p) {
    	if(!invalidVelocityTime.containsKey(p.getName()))
    		invalidVelocityTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.invalidVelocity.containsKey(p.getName())) {
            short a = Hackers.invalidVelocity.get(p.getName());
            ++a;
            Hackers.invalidVelocity.put(p.getName(), a);
        }
        else {
            Hackers.invalidVelocity.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForInvalidVelocityMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.invalidVelocityThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.invalidVelocity.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-invalidVelocityTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	invalidVelocityTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addWaterWalk(final Player p) {
    	if(!waterWalkTime.containsKey(p.getName()))
    		waterWalkTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.waterWalk.containsKey(p.getName())) {
            short a = Hackers.waterWalk.get(p.getName());
            ++a;
            Hackers.waterWalk.put(p.getName(), a);
        }
        else {
            Hackers.waterWalk.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForWaterWalkMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.waterWalkThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.waterWalk.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-waterWalkTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	waterWalkTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addAntiCactus(final Player p) {
    	if(!antiCactusTime.containsKey(p.getName()))
    		antiCactusTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.antiCactus.containsKey(p.getName())) {
            short a = Hackers.antiCactus.get(p.getName());
            ++a;
            Hackers.antiCactus.put(p.getName(), a);
        }
        else {
            Hackers.antiCactus.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForAntiCactusMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.antiCactusThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.antiCactus.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-antiCactusTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	antiCactusTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addNoKnockback(final Player p) {
    	if(!noKnockbackTime.containsKey(p.getName()))
    		noKnockbackTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.noKnockback.containsKey(p.getName())) {
            short a = Hackers.noKnockback.get(p.getName());
            ++a;
            Hackers.noKnockback.put(p.getName(), a);
        }
        else {
            Hackers.noKnockback.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForNoKnockbackMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.noKnockbackThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.noKnockback.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-noKnockbackTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	noKnockbackTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addMaxInteracts(final Player p) {
    	if(!maxInteractsTime.containsKey(p.getName()))
    		maxInteractsTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.maxInteracts.containsKey(p.getName())) {
            short a = Hackers.maxInteracts.get(p.getName());
            ++a;
            Hackers.maxInteracts.put(p.getName(), a);
        }
        else {
            Hackers.maxInteracts.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForMaxInteractsMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.maxInteractsThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.maxInteracts.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-maxInteractsTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	maxInteractsTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    public static void addHeadless(final Player p) {
    	if(!headlessTime.containsKey(p.getName()))
    		headlessTime.put(p.getName(), System.currentTimeMillis());
        if (Hackers.headless.containsKey(p.getName())) {
            short a = Hackers.headless.get(p.getName());
            ++a;
            Hackers.headless.put(p.getName(), a);
        }
        else {
            Hackers.headless.put(p.getName(), (short)1);
        }
        if (Hackers.task == 0) {
            Hackers.task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getThisPlugin(), new Runnable() {
                
                public void run() {
                    Hackers.clearAllChecks(p);
                    Hackers.access$0(0);
                }
            }, DacStringBase.violation_time * 20L);
        }
    }
    
    public static boolean isReadyForHeadlessMessage(final Player p) {
        final Iterator<Map.Entry<String, Object>> iterator = DacStringBase.headlessThreshold.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> h = iterator.next();
            if (h.getKey().equalsIgnoreCase(Short.toString(Hackers.headless.get(p.getName())))) {
                final String value = (String) h.getValue();
                if (value.contains("<player>")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value.replaceAll("<player>", p.getName()));
                }
                else Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
            }
        }
        if(System.currentTimeMillis()-headlessTime.get(p.getName()) > ((DacStringBase.min_delay/20)*1000)){
        	headlessTime.remove(p.getName());
            return true;
        }
        return false;
    }
    
    @EventHandler
    public static void optimized(final PlayerQuitEvent e) {
    	Main.getThisPlugin().getServer().getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
			
			public void run() {
		        clearAllChecks(e.getPlayer());
			}
		}, 5L);
    }
    
    public static void clearAllChecks(final Player p) {
        if (Hackers.autosign.containsKey(p.getPlayer().getName())) {
            Hackers.autosign.remove(p.getPlayer().getName());
        }
        if (Hackers.autosignTime.containsKey(p.getPlayer().getName())) {
            Hackers.autosignTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.boatfly.containsKey(p.getPlayer().getName())) {
            Hackers.boatfly.remove(p.getPlayer().getName());
        }
        if (Hackers.boatflyTime.containsKey(p.getPlayer().getName())) {
            Hackers.boatflyTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fastplace.containsKey(p.getPlayer().getName())) {
            Hackers.fastplace.remove(p.getPlayer().getName());
        }
        if (Hackers.fastplaceTime.containsKey(p.getPlayer().getName())) {
            Hackers.fastplaceTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.cheststeal.containsKey(p.getPlayer().getName())) {
            Hackers.cheststeal.remove(p.getPlayer().getName());
        }
        if (Hackers.cheststealTime.containsKey(p.getPlayer().getName())) {
            Hackers.cheststealTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fasteat.containsKey(p.getPlayer().getName())) {
            Hackers.fasteat.remove(p.getPlayer().getName());
        }
        if (Hackers.fasteatTime.containsKey(p.getPlayer().getName())) {
            Hackers.fasteatTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.extraelytra.containsKey(p.getPlayer().getName())) {
            Hackers.extraelytra.remove(p.getPlayer().getName());
        }
        if (Hackers.extraelytraTime.containsKey(p.getPlayer().getName())) {
            Hackers.extraelytraTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fastbow.containsKey(p.getPlayer().getName())) {
            Hackers.fastbow.remove(p.getPlayer().getName());
        }
        if (Hackers.fastbowTime.containsKey(p.getPlayer().getName())) {
            Hackers.fastbowTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fastfall.containsKey(p.getPlayer().getName())) {
            Hackers.fastfall.remove(p.getPlayer().getName());
        }
        if (Hackers.fastfallTime.containsKey(p.getPlayer().getName())) {
            Hackers.fastfallTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fastladder.containsKey(p.getPlayer().getName())) {
            Hackers.fastladder.remove(p.getPlayer().getName());
        }
        if (Hackers.fastladderTime.containsKey(p.getPlayer().getName())) {
            Hackers.fastladderTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.fly.containsKey(p.getPlayer().getName())) {
            Hackers.fly.remove(p.getPlayer().getName());
        }
        if (Hackers.flyTime.containsKey(p.getPlayer().getName())) {
            Hackers.flyTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.glide.containsKey(p.getPlayer().getName())) {
            Hackers.glide.remove(p.getPlayer().getName());
        }
        if (Hackers.glideTime.containsKey(p.getPlayer().getName())) {
            Hackers.glideTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.highjump.containsKey(p.getPlayer().getName())) {
            Hackers.highjump.remove(p.getPlayer().getName());
        }
        if (Hackers.highjumpTime.containsKey(p.getPlayer().getName())) {
            Hackers.highjumpTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.invalidinv.containsKey(p.getPlayer().getName())) {
            Hackers.invalidinv.remove(p.getPlayer().getName());
        }
        if (Hackers.invalidinvTime.containsKey(p.getPlayer().getName())) {
            Hackers.invalidinvTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.invalidpotion.containsKey(p.getPlayer().getName())) {
            Hackers.invalidpotion.remove(p.getPlayer().getName());
        }
        if (Hackers.invalidpotionTime.containsKey(p.getPlayer().getName())) {
            Hackers.invalidpotionTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.killaurathrublocks.containsKey(p.getPlayer().getName())) {
            Hackers.killaurathrublocks.remove(p.getPlayer().getName());
        }
        if (Hackers.killaurathrublocksTime.containsKey(p.getPlayer().getName())) {
            Hackers.killaurathrublocksTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.nobreakdelay.containsKey(p.getPlayer().getName())) {
            Hackers.nobreakdelay.remove(p.getPlayer().getName());
        }
        if (Hackers.nobreakdelayTime.containsKey(p.getPlayer().getName())) {
            Hackers.nobreakdelayTime.remove(p.getPlayer().getName());
        }
        //if (Hackers.nofall.containsKey(p.getPlayer().getName())) {
        //    Hackers.nofall.remove(p.getPlayer().getName());
        //}
        if (Hackers.noslowdown.containsKey(p.getPlayer().getName())) {
            Hackers.noslowdown.remove(p.getPlayer().getName());
        }
        if (Hackers.noslowdownTime.containsKey(p.getPlayer().getName())) {
            Hackers.noslowdownTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.reach.containsKey(p.getPlayer().getName())) {
            Hackers.reach.remove(p.getPlayer().getName());
        }
        if (Hackers.reachTime.containsKey(p.getPlayer().getName())) {
            Hackers.reachTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.regen.containsKey(p.getPlayer().getName())) {
            Hackers.regen.remove(p.getPlayer().getName());
        }
        if (Hackers.regenTime.containsKey(p.getPlayer().getName())) {
            Hackers.regenTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.speed.containsKey(p.getPlayer().getName())) {
            Hackers.speed.remove(p.getPlayer().getName());
        }
        if (Hackers.speedTime.containsKey(p.getPlayer().getName())) {
            Hackers.speedTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.noswingBlock.containsKey(p.getPlayer().getName())) {
            Hackers.noswingBlock.remove(p.getPlayer().getName());
        }
        if (Hackers.noswingBlockTime.containsKey(p.getPlayer().getName())) {
            Hackers.noswingBlockTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.criticals.containsKey(p.getPlayer().getName())) {
            Hackers.criticals.remove(p.getPlayer().getName());
        }
        if (Hackers.criticalsTime.containsKey(p.getPlayer().getName())) {
            Hackers.criticalsTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.imposibleAttack.containsKey(p.getPlayer().getName())) {
            Hackers.imposibleAttack.remove(p.getPlayer().getName());
        }
        if (Hackers.imposibleAttackTime.containsKey(p.getPlayer().getName())) {
            Hackers.imposibleAttackTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.invalidhead.containsKey(p.getPlayer().getName())) {
            Hackers.invalidhead.remove(p.getPlayer().getName());
        }
        if (Hackers.invalidheadTime.containsKey(p.getPlayer().getName())) {
            Hackers.invalidheadTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.illegalMove.containsKey(p.getPlayer().getName())) {
            Hackers.illegalMove.remove(p.getPlayer().getName());
        }
        if (Hackers.illegalMoveTime.containsKey(p.getPlayer().getName())) {
            Hackers.illegalMoveTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.moreMoves.containsKey(p.getPlayer().getName())) {
            Hackers.moreMoves.remove(p.getPlayer().getName());
        }
        if (Hackers.moreMovesTime.containsKey(p.getPlayer().getName())) {
            Hackers.moreMovesTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.waterY.containsKey(p.getPlayer().getName())) {
            Hackers.waterY.remove(p.getPlayer().getName());
        }
        if (Hackers.waterYTime.containsKey(p.getPlayer().getName())) {
            Hackers.waterYTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.invalidVelocity.containsKey(p.getPlayer().getName())) {
            Hackers.invalidVelocity.remove(p.getPlayer().getName());
        }        
        if (Hackers.invalidVelocityTime.containsKey(p.getPlayer().getName())) {
            Hackers.invalidVelocityTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.waterWalk.containsKey(p.getPlayer().getName())) {
            Hackers.waterWalk.remove(p.getPlayer().getName());
        }        
        if (Hackers.waterWalkTime.containsKey(p.getPlayer().getName())) {
            Hackers.waterWalkTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.maxInteracts.containsKey(p.getPlayer().getName())) {
            Hackers.maxInteracts.remove(p.getPlayer().getName());
        }        
        if (Hackers.maxInteractsTime.containsKey(p.getPlayer().getName())) {
            Hackers.maxInteractsTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.antiCactus.containsKey(p.getPlayer().getName())) {
            Hackers.antiCactus.remove(p.getPlayer().getName());
        }        
        if (Hackers.antiCactusTime.containsKey(p.getPlayer().getName())) {
            Hackers.antiCactusTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.noKnockback.containsKey(p.getPlayer().getName())) {
            Hackers.noKnockback.remove(p.getPlayer().getName());
        }        
        if (Hackers.noKnockbackTime.containsKey(p.getPlayer().getName())) {
            Hackers.noKnockbackTime.remove(p.getPlayer().getName());
        }
        
        if (Hackers.headless.containsKey(p.getPlayer().getName())) {
            Hackers.headless.remove(p.getPlayer().getName());
        }        
        if (Hackers.headlessTime.containsKey(p.getPlayer().getName())) {
            Hackers.headlessTime.remove(p.getPlayer().getName());
        }
    }
    
    static /* synthetic */ void access$0(final int task) {
        Hackers.task = task;
    }
}
