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

import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import bg.anticheat.dakata.*;
import org.bukkit.block.*;

public class MaterialUtils
{
    private static List<Material> swords;
    private static List<Material> foods;
    private static List<Material> slowWeapons;
    private static List<Material> unsolidMaterials;
    
    static {
        MaterialUtils.unsolidMaterials = Arrays.asList(Material.AIR, XMaterial.SIGN.material(), XMaterial.WALL_SIGN.material(), Material.TRIPWIRE, Material.TRIPWIRE_HOOK, XMaterial.SUGAR_CANE.material(), XMaterial.FERN.material(), Material.FLOWER_POT, XMaterial.DANDELION.material());
    }
    
    public static List<Material> getSwords() {
        if (MaterialUtils.swords == null) {
            (MaterialUtils.swords = new ArrayList<Material>()).add(XMaterial.WOODEN_SWORD.material());
            MaterialUtils.swords.add(XMaterial.GOLDEN_SWORD.material());
            MaterialUtils.swords.add(Material.STONE_SWORD);
            MaterialUtils.swords.add(Material.IRON_SWORD);
            MaterialUtils.swords.add(Material.DIAMOND_SWORD);
        }
        return MaterialUtils.swords;
    }
    
    public static boolean isSword(final Material m) {
        return getSwords().contains(m);
    }
    
    public static boolean isSword(final ItemStack stack) {
        return stack != null && isSword(stack.getType());
    }
    
    public static List<Material> getFoods() {
        if (MaterialUtils.foods == null) {
            (MaterialUtils.foods = new ArrayList<Material>()).add(Material.APPLE);
            MaterialUtils.foods.add(Material.APPLE);
            MaterialUtils.foods.add(Material.BREAD);
            MaterialUtils.foods.add(XMaterial.PORKCHOP.material());
            MaterialUtils.foods.add(XMaterial.COOKED_PORKCHOP.material());
            MaterialUtils.foods.add(Material.GOLDEN_APPLE);
            MaterialUtils.foods.add(XMaterial.COD.material());
            MaterialUtils.foods.add(XMaterial.COOKED_COD.material());
            MaterialUtils.foods.add(Material.COOKIE);
            MaterialUtils.foods.add(Material.MELON);
            MaterialUtils.foods.add(XMaterial.BEEF.material());
            MaterialUtils.foods.add(Material.COOKED_BEEF);
            MaterialUtils.foods.add(XMaterial.CHICKEN.material());
            MaterialUtils.foods.add(Material.COOKED_CHICKEN);
            MaterialUtils.foods.add(Material.ROTTEN_FLESH);
            MaterialUtils.foods.add(Material.SPIDER_EYE);
            MaterialUtils.foods.add(XMaterial.CARROT.material());
            MaterialUtils.foods.add(XMaterial.POTATO.material());
            MaterialUtils.foods.add(Material.CARROT);
            MaterialUtils.foods.add(Material.POTATO);
            MaterialUtils.foods.add(Material.BAKED_POTATO);
            MaterialUtils.foods.add(Material.POISONOUS_POTATO);
            MaterialUtils.foods.add(Material.PUMPKIN_PIE);
            MaterialUtils.foods.add(XMaterial.MUSHROOM_STEW.material());
            MaterialUtils.foods.add(Material.CAKE);
            MaterialUtils.foods.add(Material.COOKIE);
            if (!Main.is17ro1() && !Main.is17ro2() && !Main.is17ro3() && !Main.is17ro4()) {
                MaterialUtils.foods.add(Material.RABBIT);
                MaterialUtils.foods.add(Material.COOKED_RABBIT);
                MaterialUtils.foods.add(Material.RABBIT_STEW);
                MaterialUtils.foods.add(Material.MUTTON);
                MaterialUtils.foods.add(Material.COOKED_MUTTON);
            }
            if(Main.is19ro1() || Main.is19ro2() || Main.is110ro1() || Main.is111ro1() || Main.is112ro1() || Main.is113ro1() || Main.is114ro1() || Main.is115ro1()) {
            	foods.add(Material.BEETROOT);
            	foods.add(Material.BEETROOT_SOUP);
            	foods.add(Material.CHORUS_FRUIT);
            }
            if(Main.is113ro1() || Main.is114ro1() || Main.is115ro1()) {
            	foods.add(Material.DRIED_KELP);
            }
            if(Main.is114ro1() || Main.is115ro1()) {
            	foods.add(Material.SUSPICIOUS_STEW);
            	foods.add(Material.SWEET_BERRIES);
            }
        }
        return MaterialUtils.foods;
    }
    
    public static boolean isFood(final Material m) {
    	if(foods == null)
    		getFoods();
        return getFoods().contains(m);
    }
    
    public static boolean isFood(final ItemStack stack) {
        return stack != null && isFood(stack.getType());
    }
    
    public static long getFoodEatTime(final Material m) {
        if(isFood(m)) {
        	if(m.equals(Material.APPLE)) {
        		return 1400;
        	} else if(m.equals(XMaterial.MUSHROOM_STEW.material())) {
        		return 1400;
        	} else if(m.equals(Material.BREAD)) {
        		return 1400; //So for all its random ~1400 is good value
        		
        	}
        }
        return 0;
    }
    
    public static long getFoodEatTime(final ItemStack stack) {
    	return getFoodEatTime(stack.getType());
    }
    
    public static boolean isUsable(final Material m) {
        return isSword(m) || isFood(m) || m == Material.BOW;
    }
    
    public static boolean isUsable(final ItemStack stack) {
        return stack != null && isUsable(stack.getType());
    }
    
    public static List<Material> getSlowWeapons() {
        if (MaterialUtils.slowWeapons == null) {
            (MaterialUtils.slowWeapons = new ArrayList<Material>()).add(XMaterial.WOODEN_AXE.material());
            MaterialUtils.slowWeapons.add(XMaterial.GOLDEN_AXE.material());
            MaterialUtils.slowWeapons.add(Material.STONE_AXE);
            MaterialUtils.slowWeapons.add(Material.IRON_AXE);
            MaterialUtils.slowWeapons.add(Material.DIAMOND_AXE);
            MaterialUtils.slowWeapons.add(XMaterial.WOODEN_SHOVEL.material());
            MaterialUtils.slowWeapons.add(XMaterial.GOLDEN_SHOVEL.material());
            MaterialUtils.slowWeapons.add(XMaterial.STONE_SHOVEL.material());
            MaterialUtils.slowWeapons.add(XMaterial.IRON_SHOVEL.material());
            MaterialUtils.slowWeapons.add(XMaterial.DIAMOND_SHOVEL.material());
            MaterialUtils.slowWeapons.add(XMaterial.WOODEN_PICKAXE.material());
            MaterialUtils.slowWeapons.add(XMaterial.GOLDEN_PICKAXE.material());
            MaterialUtils.slowWeapons.add(Material.STONE_PICKAXE);
            MaterialUtils.slowWeapons.add(Material.IRON_PICKAXE);
            MaterialUtils.slowWeapons.add(Material.DIAMOND_PICKAXE);
            MaterialUtils.slowWeapons.add(XMaterial.WOODEN_HOE.material());
            MaterialUtils.slowWeapons.add(XMaterial.GOLDEN_HOE.material());
            MaterialUtils.slowWeapons.add(Material.STONE_HOE);
            MaterialUtils.slowWeapons.add(Material.IRON_HOE);
            MaterialUtils.slowWeapons.add(Material.DIAMOND_HOE);
        }
        return MaterialUtils.slowWeapons;
    }
    
    public static boolean isSlowWeapon(final Material m) {
        return getSlowWeapons().contains(m);
    }
    
    public static boolean isSlowWeapon(final ItemStack stack) {
        return stack != null && isSlowWeapon(stack.getType());
    }
    
    public static List<Material> getUnsolidMaterials() {
        return MaterialUtils.unsolidMaterials;
    }
    
    public static boolean isUnsolid(final Material m) {
        return getUnsolidMaterials().contains(m);
    }
    
    public static boolean isUnsolid(final Block b) {
        return isUnsolid(b.getType());
    }
}