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

public class BlockUtils
{
	public static boolean isInstantBreak(final Material m, final Material inHand) {
		return m == Material.TORCH || m == Material.FLOWER_POT || m == XMaterial.ROSE_RED.material()
				|| m == XMaterial.SUNFLOWER.material() || m == XMaterial.TALL_GRASS.material()
				|| m == Material.RED_MUSHROOM || m == Material.BROWN_MUSHROOM || m == Material.TRIPWIRE
				|| m == Material.TRIPWIRE_HOOK || m == Material.DEAD_BUSH || m == XMaterial.REPEATER.material()
				|| m == XMaterial.COMPARATOR.material() || m == Material.REDSTONE_WIRE
				|| m == XMaterial.REDSTONE_TORCH.material() || m == XMaterial.REDSTONE_TORCH.material()
				|| m == XMaterial.SUGAR_CANE.material() || m == Material.SLIME_BLOCK || m == Material.POTATO
				|| m == Material.CARROT || m == Material.MELON_SEEDS || m == Material.PUMPKIN_SEEDS
				|| m == XMaterial.LILY_PAD.material() || m == Material.WHEAT || m == Material.FLOWER_POT
				|| m == XMaterial.NETHER_WART.material() || m == Material.TNT || m == Material.SNOW
				|| m == Material.WHEAT || m == Material.COCOA || m == Material.DAYLIGHT_DETECTOR
				|| m == XMaterial.DAYLIGHT_DETECTOR.material() || m.name().contains("MUSHROOM")
				|| (m == Material.LADDER) || (m == Material.VINE)
				|| (m == Material.NETHERRACK
						&& (inHand.toString().contains("GOLD") || inHand.toString().contains("gold")))
				|| (m == XMaterial.GRASS.material() && inHand == Material.SHEARS) || m.name().contains("STEM");
	}
    
    public static boolean isWater(final Material m) {
        return m == Material.WATER;
    }
    
    public static boolean isLava(final Material m) {
        return m == Material.LAVA;
    }
    
    public static boolean isLiquid(final Material m) {
        return m == Material.LAVA || m == Material.WATER;
    }
    
    public static boolean isClimable(final Material m) {
        return m == Material.LADDER || m == Material.VINE;
    }
}
