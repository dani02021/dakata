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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import bg.anticheat.dakata.Main;

public class PlayerUtils {
	private static HashMap<String, Float> fallDist = new HashMap<String, Float>();
	public static HashMap<String, Location> backLoc = new HashMap<String, Location>();
	
	public static Location onGroundLoc(final Location locationUnderPlayer, final String mat) {
		if (!locationUnderPlayer.clone().add(0.0, 0.0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.0, 0.0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0.0, 0.0).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0.0, 0.0);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0.0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0.0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(-0.3, 0.0, -0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.3, 0.0, -0.3);
		}
		if (!locationUnderPlayer.clone().add(-0.3, 0.0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.3, 0.0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0.0, -0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0.0, -0.3);
		}
		return locationUnderPlayer;
	}

	public static boolean isOnBlock(final Player p, final Material mat) {
		final Location loc = p.getLocation().subtract(0.0, 0.01, 0.0);
		return onBlockLoc(loc, mat.name()).getBlock().getType() == mat;
	}

	public static boolean isOnBlock(final Player p, final Material mat, final double down) {
		final Location loc = p.getLocation().subtract(0.0, down, 0.0);
		return onBlockLoc(loc, mat.name()).getBlock().getType() == mat;
	}

	public static boolean isOnGround1(final Player p) {
		final Block loc = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		return loc.getType() != Material.AIR || !loc.getRelative(BlockFace.WEST).isEmpty()
				|| !loc.getRelative(BlockFace.EAST).isEmpty() || !loc.getRelative(BlockFace.NORTH).isEmpty()
				|| !loc.getRelative(BlockFace.SOUTH).isEmpty() || !loc.getRelative(BlockFace.NORTH_WEST).isEmpty()
				|| !loc.getRelative(BlockFace.NORTH_EAST).isEmpty() || !loc.getRelative(BlockFace.SOUTH_EAST).isEmpty()
				|| !loc.getRelative(BlockFace.SOUTH_WEST).isEmpty();
	}

	public static boolean isOnGround1_1(final Player p) {
		//TODO: Check for false positives
		final Block loc = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		if(!loc.isEmpty())
		{
			if(!loc.getRelative(BlockFace.EAST).isEmpty() && !loc.getRelative(BlockFace.WEST).isEmpty() &&
					!loc.getRelative(BlockFace.NORTH).isEmpty() && !loc.getRelative(BlockFace.SOUTH).isEmpty())
			{
				if(!loc.getRelative(BlockFace.NORTH_EAST).isEmpty() && !loc.getRelative(BlockFace.NORTH_WEST).isEmpty() &&
						!loc.getRelative(BlockFace.SOUTH_EAST).isEmpty() && !loc.getRelative(BlockFace.SOUTH_WEST).isEmpty())
					if(p.getFallDistance() == 0)
					   return true;
			}
		} return false;
	}
	public static boolean isOnGroundLoc(Player p, double y) {
		Location loc = p.getLocation().subtract(0, y, 0);
		Block checkblock = onGroundLoc(loc, "AIR").getBlock();
		if (checkblock.getType().isSolid() && checkblock.getType().name().contains("WATER") && checkblock.getType().name().contains("LAVA") && checkblock.getType().name().contains("FLOWER") && checkblock.getType().name().equalsIgnoreCase("LONG_GRASS")) {
			return true;
		}
		if (!p.isOnGround() && !checkblock.getType().isSolid()) {
			return false;
		}

		return true;
	}

	public static boolean isOnGround2Loc(Player p, double ydown) {
		Location loc = p.getLocation().subtract(0, ydown, 0);
		Block checkblock = onGroundLoc(loc, "AIR").getBlock();
		if (checkblock.getType().isSolid() && checkblock.getType().name().contains("WATER") && checkblock.getType().name().contains("LAVA") && checkblock.getType().name().contains("FLOWER") && checkblock.getType().name().equalsIgnoreCase("LONG_GRASS")) {
			return true;
		}
		if (!checkblock.getType().isSolid()) {
			return false;
		}
		return true;
	}

	public static boolean isOnBlock2(Player p, Material mat) {
		Location loc = p.getLocation().subtract(0, 0.01, 0);
		if (onBlockLoc2(loc, mat.name()).getBlock().getType() == mat) {
			return true;
		}
		return false;
	}

	public static Location onBlockLoc2(Location locationUnderPlayer, String mat) {
		if (!locationUnderPlayer.clone().add(0, 0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0, 0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0, 0).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0, 0);
		}
		if (!locationUnderPlayer.clone().add(0, 0, -0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0, 0, -0.3);
		}
		if (!locationUnderPlayer.clone().add(-0.3, 0, 0).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.3, 0, 0);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(-0.3, 0, -0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.3, 0, -0.3);
		}
		if (!locationUnderPlayer.clone().add(-0.3, 0, 0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.3, 0, 0.3);
		}
		if (!locationUnderPlayer.clone().add(0.3, 0, -0.3).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.3, 0, -0.3);
		}
		return locationUnderPlayer;
	}

	public static boolean isOnGround2(final Player p) {
		final Location loc = p.getLocation().subtract(0.0, 0.2, 0.0);
		return onGroundLoc(loc, "AIR").getBlock().getType() != Material.AIR;
	}

	public static boolean isNotOnGround3(final Player player) {
		final Block block1 = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		final Block block2 = block1.getRelative(BlockFace.EAST);
		final Block block3 = block1.getRelative(BlockFace.NORTH_EAST);
		final Block block4 = block1.getRelative(BlockFace.NORTH_WEST);
		final Block block5 = block1.getRelative(BlockFace.SOUTH_EAST);
		final Block block6 = block1.getRelative(BlockFace.SOUTH_WEST);
		final Block block7 = block1.getRelative(BlockFace.SOUTH);
		final Block block8 = block1.getRelative(BlockFace.WEST);
		final Block block9 = block1.getRelative(BlockFace.NORTH);
		return block1.getType() == Material.AIR && block2.getType() == Material.AIR && block3.getType() == Material.AIR
				&& block4.getType() == Material.AIR && block5.getType() == Material.AIR
				&& block5.getType() == Material.AIR && block6.getType() == Material.AIR
				&& block7.getType() == Material.AIR && block8.getType() == Material.AIR
				&& block9.getType() == Material.AIR;
	}

	public static boolean isOnGround4(final Player p) {
		final Block by = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(),
				p.getLocation().getZ()).getBlock();
		final Block bx = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0,
				p.getLocation().getZ()).getBlock();
		final Block b1 = new Location(p.getWorld(), p.getLocation().getX() + 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ() - 1.0).getBlock();
		final Block b2 = new Location(p.getWorld(), p.getLocation().getX() + 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ() + 1.0).getBlock();
		final Block b3 = new Location(p.getWorld(), p.getLocation().getX() - 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ() - 1.0).getBlock();
		final Block b4 = new Location(p.getWorld(), p.getLocation().getX() - 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ() + 1.0).getBlock();
		final Block x1 = new Location(p.getWorld(), p.getLocation().getX() + 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ()).getBlock();
		final Block x2 = new Location(p.getWorld(), p.getLocation().getX() - 1.0, p.getLocation().getY() - 1.0,
				p.getLocation().getZ()).getBlock();
		final Block x3 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0,
				p.getLocation().getZ() + 1.0).getBlock();
		final Block x4 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0,
				p.getLocation().getZ() - 1.0).getBlock();
		final Block z1 = new Location(p.getWorld(), p.getLocation().getX() + 1.0, p.getLocation().getY(),
				p.getLocation().getZ()).getBlock();
		final Block z2 = new Location(p.getWorld(), p.getLocation().getX() - 1.0, p.getLocation().getY(),
				p.getLocation().getZ()).getBlock();
		final Block z3 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(),
				p.getLocation().getZ() + 1.0).getBlock();
		final Block z4 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(),
				p.getLocation().getZ() - 1.0).getBlock();
		return bx.getType() != Material.AIR || b1.getType() != Material.AIR || b2.getType() != Material.AIR
				|| b3.getType() != Material.AIR || b4.getType() != Material.AIR || by.getType() != Material.AIR
				|| z1.getType() != Material.AIR || z2.getType() != Material.AIR || z3.getType() != Material.AIR
				|| z4.getType() != Material.AIR || x1.getType() != Material.AIR || x2.getType() != Material.AIR
				|| x3.getType() != Material.AIR || x4.getType() != Material.AIR;
	}

	public static boolean isUsingElytra(final Player p) {
		try {
			if(Main.is110ro1() || Main.is19ro1() || Main.is19ro2() || Main.is111ro1())
				if(p.isGliding())
					return true;
			return false;
		} catch(NoSuchMethodError n) {
			Class<?> craftPlayer = null;
			try {
				craftPlayer = Class.forName(String.valueOf(Ping.getServerVersion()) + ".entity.CraftPlayer");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final Object converted = craftPlayer.cast(p);
			Method handle = null;
			try {
				handle = converted.getClass().getMethod("getHandle", new Class[0]);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object entityPlayer = null;
			try {
				entityPlayer = handle.invoke(converted, new Object[0]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Method pingField = null;
			try {
				pingField = entityPlayer.getClass().getMethod("cB", new Class[0]);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				return (boolean) pingField.invoke(entityPlayer,new Object[0]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	public static boolean isInWeb(final Player p, final double sub) {
		return isNextToBlock2(p.getLocation(), XMaterial.COBWEB.material(), sub);
	}

	public static boolean isOnEntity(final Player p, final EntityType type) {
		for (final Entity e : p.getNearbyEntities(1.0, 1.0, 1.0)) {
			if (e.getType() == type && e.getLocation().getY() < p.getLocation().getY()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnEntity(final Player p, final EntityType type, final double distance) {
		for (final Entity e : p.getNearbyEntities(1.5, distance, 1.5)) {
			if (e.getType() == type && e.getLocation().getY() < p.getLocation().getY()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnEntity(final Player p) {
		for (final Entity e : p.getNearbyEntities(1.0, 1.0, 1.0)) {
			if (e.getLocation().getY() < p.getLocation().getY()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInEntity(final Player p) {
		for (final Entity e : p.getNearbyEntities(1.0, 1.0, 1.0)) {
			if (e.getLocation().getY() == p.getLocation().getY()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnGround(final Player p) {
		final Location loc = p.getLocation().subtract(0.0, 0.05, 0.0);
		final Block checkblock = onGroundLoc(loc, "AIR").getBlock();
		return checkblock.getType().isSolid() || (checkblock.isLiquid() || checkblock.getType().name().contains("LAVA")
				|| checkblock.getType().name().contains("WATER"));
	}

	public static boolean isOnLadder(final Player p) {
		for (double d = 0.0; d < 3.0; d += 0.1) {
			if (p.getLocation().add(0.0, -d, 0.0).getBlock().getType() == Material.LADDER
					|| (p.getLocation().add(0.0, -d, 0.0).getBlock().getType() == Material.VINE &&
					PlayerUtils.isNextToBlock2Different(p.getLocation(), Material.AIR, 0.2))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnLadder2(final Player p) {
		return p.getLocation().getBlock().getType() == Material.LADDER
				|| p.getLocation().getBlock().getType() == Material.VINE;
	}

	public static Location onBlockLoc(final Location locationUnderPlayer, final String mat) {
		if (locationUnderPlayer.clone().add(0.0, 0.0, 0.5).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.0, 0.0, 0.5);
		}
		if (locationUnderPlayer.clone().add(0.5, 0.0, 0.0).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.5, 0.0, 0.0);
		}
		if (locationUnderPlayer.clone().add(0.5, 0.0, 0.5).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.5, 0.0, 0.5);
		}
		if (locationUnderPlayer.clone().add(-0.5, 0.0, -0.5).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.5, 0.0, -0.5);
		}
		if (locationUnderPlayer.clone().add(-0.5, 0.0, 0.5).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(-0.5, 0.0, 0.5);
		}
		if (locationUnderPlayer.clone().add(0.5, 0.0, -0.5).getBlock().getType().name().contains(mat)) {
			return locationUnderPlayer.clone().add(0.5, 0.0, -0.5);
		}
		return locationUnderPlayer;
	}

	public static boolean isInLiquidLoc(final Location loc) {
		final Location loca = loc.clone();
		return onBlockLoc(loca, "WATER").getBlock().getType() == Material.WATER
				|| onBlockLoc(loca, "LAVA").getBlock().getType() == Material.LAVA;
	}

	public static boolean isUnderBlock(final Player p) {
		final Location loc = p.getLocation().add(0.0, 2.5, 0.0);
		final Location b = getPlayerStandOnBlockLocationss(loc, Material.AIR);
		return b.getBlock().getType().isSolid() && !b.getBlock().getType().name().contains("LADDER")
				&& !b.getBlock().getType().name().contains("VINE");
	}

	public static Location getPlayerStandOnBlockLocationss(final Location locationUnderPlayer, final Material mat) {
		final Location b11 = locationUnderPlayer.clone().add(0.3, 0.0, -0.3);
		if (b11.getBlock().getType() != mat) {
			return b11;
		}
		final Location b12 = locationUnderPlayer.clone().add(-0.3, 0.0, -0.3);
		if (b12.getBlock().getType() != mat) {
			return b12;
		}
		final Location b13 = locationUnderPlayer.clone().add(0.3, 0.0, 0.3);
		if (b13.getBlock().getType() != mat) {
			return b13;
		}
		final Location b14 = locationUnderPlayer.clone().add(-0.3, 0.0, 0.3);
		if (b14.getBlock().getType() != mat) {
			return b14;
		}
		return locationUnderPlayer;
	}
	
	public static boolean isNextToBlock(final Location locationUnderPlayer, final Material mat) {
		if(locationUnderPlayer.getBlock().getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.EAST).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.WEST).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_WEST).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_WEST).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_EAST).getType() == mat)
			return true;
		if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_EAST).getType() == mat)
			return true;
		return false;
	}
	
	public static boolean isNextToBlockDifferent(final Location locationUnderPlayer, final Material mat) {
		if(!locationUnderPlayer.getBlock().getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.EAST).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.WEST).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_WEST).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_WEST).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_EAST).getType().equals(mat))
			return true;
		if(!locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_EAST).getType().equals(mat))
			return true;
		return false;
	}
	public static boolean isNextToBlock2(final Location locationUnderPlayer, final Material mat, double violation) {
		if(locationUnderPlayer.getBlock().getType() == mat)
			return true;
		if(locationUnderPlayer.add(violation,0,0).getBlock().getType() == mat)
			return true;
		if(locationUnderPlayer.add(0,0,violation).getBlock().getType() == mat)
			return true;
		if(locationUnderPlayer.subtract(violation,0,0).getBlock().getType() == mat)
			return true;
		if(locationUnderPlayer.subtract(0,0,violation).getBlock().getType() == mat)
			return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_EAST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_EAST).getType() == mat)
		//	return true;
		return false;
	}
	public static boolean isNextToBlock2Different(final Location locationUnderPlayer, final Material mat, double d) {
		if(!(locationUnderPlayer.getBlock().getType() == mat))
			return true;
		else if(!(locationUnderPlayer.add(d,0,0).getBlock().getType() == mat))
			return true;
		else if(!(locationUnderPlayer.add(0,0,d).getBlock().getType() == mat))
			return true;
		else if(!(locationUnderPlayer.subtract(d,0,0).getBlock().getType() == mat))
			return true;
		else if(!(locationUnderPlayer.subtract(0,0,d).getBlock().getType() == mat))
			return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_EAST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_EAST).getType() == mat)
		//	return true;
		return false;
	}
	public static boolean isNextToBlock2DifferentCheckerClimb(final Location locationUnderPlayer, final Material mat, double d) {
		if(!(locationUnderPlayer.add(d,0,0).getBlock().getType() == mat))
			if(!locationUnderPlayer.add(d,0,0).getBlock().getRelative(BlockFace.UP).isEmpty() &&
					!locationUnderPlayer.add(d,0,0).getBlock().getRelative(BlockFace.UP).isLiquid())
				return true;
		else if(!(locationUnderPlayer.add(0,0,d).getBlock().getType() == mat))
			if(!locationUnderPlayer.add(0,0,d).getBlock().getRelative(BlockFace.UP).isEmpty() &&
					!locationUnderPlayer.add(0,0,d).getBlock().getRelative(BlockFace.UP).isLiquid())
				return true;
		else if(!(locationUnderPlayer.subtract(d,0,0).getBlock().getType() == mat))
			if(!locationUnderPlayer.subtract(d,0,0).getBlock().getRelative(BlockFace.UP).isEmpty() &&
					!locationUnderPlayer.subtract(d,0,0).getBlock().getRelative(BlockFace.UP).isLiquid())
				return true;
		else if(!(locationUnderPlayer.subtract(0,0,d).getBlock().getType() == mat))
			if(!locationUnderPlayer.subtract(0,0,d).getBlock().getRelative(BlockFace.UP).isEmpty() &&
					!locationUnderPlayer.subtract(0,0,d).getBlock().getRelative(BlockFace.UP).isLiquid())
				return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_WEST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.NORTH_EAST).getType() == mat)
		//	return true;
		//if(locationUnderPlayer.getBlock().getRelative(BlockFace.SOUTH_EAST).getType() == mat)
		//	return true;
		return false;
	}

	public static PotionEffect getPotionEffect(final Player p, final PotionEffectType type) {
		PotionEffect effect = null;
		final Iterator<PotionEffect> iterator = p.getActivePotionEffects().iterator();
		while (iterator.hasNext()) {
			effect = iterator.next();
			if (effect.getType() == type) {
				break;
			}
		}
		return effect;
	}

	public static boolean isUsingPotionEffect(final Player p, final PotionEffectType type) {
		for (final PotionEffect pf : p.getActivePotionEffects()) {
			if (pf.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnHalfSlab(final Player p) {
		final Location loc = p.getLocation();
		if (onBlockLoc(loc, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc2 = p.getLocation().subtract(0.0, 1.0, 0.0);
		if (onBlockLoc(loc2, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc3 = p.getLocation().subtract(0.0, 0.5, 0.0);
		if (onBlockLoc(loc3, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc4 = p.getLocation().subtract(0.0, 0.2, 0.0);
		return onBlockLoc(loc4, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW");
	}

	public static boolean isOnHalfSlab(final Player p, final Location loca) {
		if (onBlockLoc(loca, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loca).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc2 = p.getLocation().subtract(0.0, 1.0, 0.0);
		if (onBlockLoc(loc2, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc3 = p.getLocation().subtract(0.0, 0.5, 0.0);
		if (onBlockLoc(loc3, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW")) {
			return true;
		}
		final Location loc4 = p.getLocation().subtract(0.0, 0.2, 0.0);
		return onBlockLoc(loc4, "STEP").getBlock().getType().name().contains("STEP")
				|| getPlayerStandOnBlockLocationSnow(loc2).getBlock().getType().name().contains("SNOW");
	}

	public static boolean isOnStair(final Player p) {
		final Location loc4 = p.getLocation().subtract(0.0, 0.0, 0.0);
		if (onBlockLoc(loc4, "STAIR").getBlock().getType().name().contains("STAIR")) {
			return true;
		}
		final Location loc5 = p.getLocation().subtract(0.0, 0.01, 0.0);
		if (onBlockLoc(loc5, "STAIR").getBlock().getType().name().contains("STAIR")) {
			return true;
		}
		final Location loc6 = p.getLocation().subtract(0.0, 0.5, 0.0);
		if (onBlockLoc(loc6, "STAIR").getBlock().getType().name().contains("STAIR")) {
			return true;
		}
		final Location loc7 = p.getLocation().subtract(0.0, 1.0, 0.0);
		return onBlockLoc(loc7, "STAIR").getBlock().getType().name().contains("STAIR");
	}

	public static Location getPlayerStandOnBlockLocationSnow(final Location locationUnderPlayer) {
		final Location b11 = locationUnderPlayer.clone().add(0.3, 0.0, -0.3);
		if (b11.getBlock().getType().name().contains("SNOW")) {
			return b11;
		}
		final Location b12 = locationUnderPlayer.clone().add(-0.3, 0.0, -0.3);
		if (b12.getBlock().getType().name().contains("SNOW")) {
			return b12;
		}
		final Location b13 = locationUnderPlayer.clone().add(0.3, 0.0, 0.3);
		if (b13.getBlock().getType().name().contains("SNOW")) {
			return b13;
		}
		final Location b14 = locationUnderPlayer.clone().add(-0.3, 0.0, 0.3);
		if (b14.getBlock().getType().name().contains("SNOW")) {
			return b14;
		}
		return locationUnderPlayer;
	}

	public static boolean isOnIce(final Player p) {
		final List<Material> materials = getMaterialsAround(p.getLocation().clone().add(0.0, -0.01, 0.0));
		return materials.contains(Material.ICE) || materials.contains(Material.PACKED_ICE);
	}

	public static boolean isInWeb(final Location loc) {
		return loc.getBlock().getType() == XMaterial.COBWEB.material()
				|| loc.getBlock().getRelative(BlockFace.UP).getType() == XMaterial.COBWEB.material();
	}

	public static List<Material> getMaterialsAround(final Location loc) {
		final List<Material> result = new ArrayList<Material>();
		result.add(loc.getBlock().getType());
		result.add(loc.clone().add(0.3, 0.0, -0.3).getBlock().getType());
		result.add(loc.clone().add(-0.3, 0.0, -0.3).getBlock().getType());
		result.add(loc.clone().add(0.3, 0.0, 0.3).getBlock().getType());
		result.add(loc.clone().add(-0.3, 0.0, 0.3).getBlock().getType());
		return result;
	}

	public static boolean isLocationOnGround(final Location loc) {
		final List<Material> materials = getMaterialsAround(loc.clone().add(0.0, -0.01, 0.0));
		for (final Material m : materials) {
			if (m != null && !MaterialUtils.isUnsolid(m) && m != Material.WATER
					&& m != Material.LAVA) {
				return true;
			}
		}
		return false;
	}
	public static float[] getRotationsNeeded(Player p, Entity entity)
	{
		if(entity == null)
			return null;
		double diffX = entity.getLocation().getX() - p.getLocation().getX();
		double diffY;
		if(entity instanceof LivingEntity)
		{
			LivingEntity entityLivingBase = (LivingEntity)entity;
			diffY =
				entityLivingBase.getLocation().getY()
					+ entityLivingBase.getEyeHeight()
					* 0.9
					- (p.getLocation().getY() + p.getEyeHeight());
		}else
			diffY =
				(entity.getLocation().getY() + entity.getLocation().getY())
					/ 2.0D
					- (p.getLocation().getY() + p.getEyeHeight());
		double diffZ = entity.getLocation().getZ() - p.getLocation().getZ();
		double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
		float yaw =
			(float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[]{
			p.getLocation().getYaw()
				+ wrapAngleTo180(yaw
					- p.getLocation().getYaw()),
				 p.getLocation().getPitch()
				+ wrapAngleTo180(pitch
					-  p.getLocation().getPitch())};
		
	}
    private static float wrapAngleTo180(float f) {
        f %= 360.0F;
        if (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f < -180.0F) {
            f += 360.0F;
        }

        return f;
    }
    
    public static Location getLastOnGroundLocation(Player p){
    	if(backLoc.containsKey(p.getName()))
    		return backLoc.get(p.getName());
    	for(int i = (int) p.getLocation().getY(); i>=0;i--) {
    		Location loc = p.getLocation();
    		loc.setY(i);
    		if(!loc.getBlock().isEmpty() && !loc.getBlock().isLiquid() && !loc.getBlock().getType().equals(XMaterial.FERN.material()))
    			return loc.add(0,1,0);
    	}
    	return p.getLocation().getWorld().getHighestBlockAt(p.getLocation().add(0,1,0)).getLocation();
    }
    
    public static float getFallDistance(Player p){
    	if(fallDist.containsKey(p.getName()))
    		return fallDist.get(p.getName());
    	return -1.0F;
    }
    
    public static void cleanFallDistance(Player p){
    	fallDist.remove(p.getName());
    }
    
    public static double getAngle(Location substracted, Location p)
    {
		double i = substracted.toVector().angle(p.getDirection());
		return i*90;
    }
    public static double getAngle(Location to, Location from, Location p)
    {
		Location v = from.clone();
		Location v1 = to.clone();
		Vector v2 = (v1.subtract(v)).toVector().normalize();
		
		double i = v2.angle(p.getDirection());
		return i*90;
    }
    public static double getAngle(Location to, Location from, Vector p)
    {
		Location v = from.clone();
		Location v1 = to.clone();
		Vector v2 = (v1.subtract(v)).toVector().normalize();
		
		double i = v2.angle(p);
		return i*90;
    }
    //EVENT
    public static void calculateFallDist(PlayerMoveEvent e)
    {
    	//BackLoc
    	if(!e.isCancelled())
    	    if(e.getPlayer().isOnGround() && !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty() &&
    	    		!e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid() && !e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().name().contains("SIGN"))
    	    	if(!((e.getTo().getY() - e.getFrom().getY()) == 0.4200000000000017) && !((e.getTo().getY() - e.getFrom().getY()) == 0.4099999999999966) && !((e.getTo().getY() - e.getFrom().getY() % 1) == 0))
    		        backLoc.put(e.getPlayer().getName(), e.getPlayer().getLocation());
    	//NoFall
    	if(e.getFrom().getY() > e.getTo().getY()) {
	    	if(fallDist.containsKey(e.getPlayer().getName())){
	    		if(e.getPlayer().getFallDistance() > fallDist.get(e.getPlayer().getName()))
	    		{
	    			fallDist.put(e.getPlayer().getName(), e.getPlayer().getFallDistance());
	    		}
	    	} else fallDist.put(e.getPlayer().getName(), e.getPlayer().getFallDistance());
    	}
    }
}
