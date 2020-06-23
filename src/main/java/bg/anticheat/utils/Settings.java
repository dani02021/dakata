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

import org.bukkit.Bukkit;

import bg.anticheat.dakata.Main;

public class Settings {
	public static int max_hit_entityes;
	public static int min_noknockback_moves;
	public static int min_pre_breake_time;
	public static int min_breake_blocks_time;
	public static long min_sign_change_time;
	public static double min_attack_distace;
	public static double min_block_distace;
	public static long min_block_time;
	public static double max_ladder_speed;
	public static int max_glide_moves;
	public static double max_glide_speed;
	public static int max_player_pitch;
	public static double max_highjump_distance;
	public static double max_highjump_potion_distance;
	public static int max_water_moves;
	public static int min_chest_item_time;
	public static long min_regen_time;
	public static long throwe_time;
	public static int max_throwse;
	public static int max_cactus_moves;
	public static double sprint_speed;
	public static double sneak_speed;
	public static double ice_speed;
	public static double water_speed;
	public static double lava_speed;
	public static double item_speed;
	public static double cobweb_speed;
	public static double soulsand_speed;
	public static double soulsand_ice_speed;
	public static double jump_multiply_speed;
	public static double stair_multiply_speed;
	public static double slab_multiply_speed;
	public static int min_speed_violation;
	public static int min_one_ten_regen_time;
	public static double max_lagg_distance;
	public static int max_packets;
	public static long packets_time;
	public static double walk_speed;
	public static double min_noknockback_distance;
	public static int max_interacts_per_second;
	public static int max_moves;
	public static int sprintbackwards_mode;

	static {
		Settings.max_hit_entityes = Main.getSettings().getInt("max-hit-entityes");
		Settings.min_noknockback_moves = Main.getSettings().getInt("min-noknockback-moves");
		Settings.min_noknockback_distance = Main.getSettings().getDouble("min-noknockback-distance");
		Settings.min_pre_breake_time = Main.getSettings().getInt("min-pre-break-time");
		Settings.min_breake_blocks_time = Main.getSettings().getInt("min-break-blocks-time");
		Settings.min_sign_change_time = Main.getSettings().getLong("min-sign-change-time");
		Settings.min_attack_distace = Main.getSettings().getDouble("max-attack-distance");
		Settings.min_block_distace = Main.getSettings().getDouble("max-block-distance");
		Settings.max_ladder_speed = Main.getSettings().getDouble("ladder-speed");
		Settings.max_glide_moves = Main.getSettings().getInt("min-glide-violation");
		Settings.max_glide_speed = Main.getSettings().getDouble("max-glide-speed");
		Settings.max_player_pitch = Main.getSettings().getInt("max-player-pitch");
		Settings.max_highjump_distance = Main.getSettings().getDouble("max-highjump-distance");
		Settings.max_highjump_potion_distance = Main.getSettings().getDouble("max-highjump-potion-distance");
		Settings.max_water_moves = Main.getSettings().getInt("max-water-moves");
		Settings.min_chest_item_time = Main.getSettings().getInt("min-chest-item-time");
		Settings.max_lagg_distance = Main.getSettings().getDouble("max-lagg-distance");
		Settings.sprintbackwards_mode = Main.getSettings().getInt("sprint-backwards-mode");
		Settings.min_regen_time = Main.getSettings().getLong("min-regen-time");
		Settings.max_interacts_per_second = Main.getSettings().getInt("max-interacts-per-second");
		Settings.throwe_time = Main.getSettings().getLong("throw-time");
		Settings.max_throwse = Main.getSettings().getInt("max-throws");
		Settings.max_cactus_moves = Main.getSettings().getInt("max-cactus-moves");
		Settings.min_block_time = Main.getSettings().getLong("min-block-time");
		Settings.max_lagg_distance = Main.getSettings().getDouble("max-lagg-distance");
		Settings.sprint_speed = Main.getSettings().getDouble("sprint-speed");
		Settings.sneak_speed = Main.getSettings().getDouble("sneak-speed");
		Settings.ice_speed = Main.getSettings().getDouble("ice-speed");
		Settings.water_speed = Main.getSettings().getDouble("water-speed");
		Settings.lava_speed = Main.getSettings().getDouble("lava-speed");
		Settings.item_speed = Main.getSettings().getDouble("item-speed");
		Settings.cobweb_speed = Main.getSettings().getDouble("cobweb-speed");
		Settings.soulsand_speed = Main.getSettings().getDouble("soulsand-speed");
		Settings.soulsand_ice_speed = Main.getSettings().getDouble("soulsand-ice-speed");
		Settings.walk_speed = Main.getSettings().getDouble("walk-speed");
		Settings.jump_multiply_speed = Main.getSettings().getDouble("jump-multiply-speed");
		Settings.stair_multiply_speed = Main.getSettings().getDouble("stair-multiply-speed");
		Settings.slab_multiply_speed = Main.getSettings().getDouble("slab-multiply-speed");
		Settings.min_speed_violation = Main.getSettings().getInt("min-speed-violation");
		Settings.min_one_ten_regen_time = Main.getSettings().getInt("min-one-ten-regen-time");
		Settings.max_packets = Main.getSettings().getInt("max-packets");
		Settings.packets_time = Main.getSettings().getLong("packets-time");
		Settings.max_moves = Main.getSettings().getInt("max-moves");
	}

	public static void reloadTags() {
		Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
			@Override
			public void run() {
				Settings.max_hit_entityes = Main.getSettings().getInt("max-hit-entityes");
				Settings.min_noknockback_moves = Main.getSettings().getInt("min-noknockback-moves");
				Settings.min_noknockback_distance = Main.getSettings().getDouble("min-noknockback-distance");
				Settings.min_pre_breake_time = Main.getSettings().getInt("min-pre-break-time");
				Settings.min_breake_blocks_time = Main.getSettings().getInt("min-break-blocks-time");
				Settings.min_sign_change_time = Main.getSettings().getLong("min-sign-change-time");
				Settings.min_attack_distace = Main.getSettings().getDouble("max-attack-distance");
				Settings.min_block_distace = Main.getSettings().getDouble("max-block-distance");
				Settings.max_ladder_speed = Main.getSettings().getDouble("ladder-speed");
				Settings.max_glide_moves = Main.getSettings().getInt("min-glide-violation");
				Settings.max_glide_speed = Main.getSettings().getDouble("max-glide-speed");
				Settings.max_player_pitch = Main.getSettings().getInt("max-player-pitch");
				Settings.min_block_time = Main.getSettings().getLong("min-block-time");
				Settings.max_highjump_distance = Main.getSettings().getDouble("max-highjump-distance");
				Settings.max_highjump_potion_distance = Main.getSettings().getDouble("max-highjump-potion-distance");
				Settings.max_water_moves = Main.getSettings().getInt("max-water-moves");
				Settings.min_chest_item_time = Main.getSettings().getInt("min-chest-item-time");
				Settings.min_regen_time = Main.getSettings().getLong("min-regen-time");
				Settings.max_interacts_per_second = Main.getSettings().getInt("max-interacts-per-second");
				Settings.throwe_time = Main.getSettings().getLong("throw-time");
				Settings.max_throwse = Main.getSettings().getInt("max-throws");
				Settings.max_lagg_distance = Main.getSettings().getDouble("max-lagg-distance");
				Settings.sprintbackwards_mode = Main.getSettings().getInt("sprint-backwards-mode");
				Settings.sprint_speed = Main.getSettings().getDouble("sprint-speed");
				Settings.sneak_speed = Main.getSettings().getDouble("sneak-speed");
				Settings.ice_speed = Main.getSettings().getDouble("ice-speed");
				Settings.water_speed = Main.getSettings().getDouble("water-speed");
				Settings.lava_speed = Main.getSettings().getDouble("lava-speed");
				Settings.item_speed = Main.getSettings().getDouble("item-speed");
				Settings.cobweb_speed = Main.getSettings().getDouble("cobweb-speed");
				Settings.soulsand_speed = Main.getSettings().getDouble("soulsand-speed");
				Settings.soulsand_ice_speed = Main.getSettings().getDouble("soulsand-ice-speed");
				Settings.walk_speed = Main.getSettings().getDouble("walk-speed");
				Settings.jump_multiply_speed = Main.getSettings().getDouble("jump-multiply-speed");
				Settings.stair_multiply_speed = Main.getSettings().getDouble("stair-multiply-speed");
				Settings.slab_multiply_speed = Main.getSettings().getDouble("slab-multiply-speed");
				Settings.min_speed_violation = Main.getSettings().getInt("min-speed-violation");
				Settings.min_one_ten_regen_time = Main.getSettings().getInt("min-one-ten-regen-time");
				Settings.max_packets = Main.getSettings().getInt("max-packets");
				Settings.packets_time = Main.getSettings().getLong("packets-time");
				Settings.max_moves = Main.getSettings().getInt("max-moves");
			}
		}, 5L);
	}
}
