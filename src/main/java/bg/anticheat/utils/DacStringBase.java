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

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;

import bg.anticheat.dakata.Main;

public class DacStringBase
{
    public static String no_permission;
    public static String anticheat_tag;
    public static String broadcast;
    public static String kick_mesaj;
    public static String uyari_msg;
    public static String swear_msg;
    public static String spam_kick_msg;
    public static String ad_msg;
    public static String hack_msg;
    public static String block_cmd_msg;
    public static String log_file_message;
    public static boolean log_file_enabled;
    public static boolean chat_swear_protection;
    public static boolean chat_advertising_ip_protection;
    public static boolean chat_advertising_web_protection;
    public static boolean noknockback_protection;
    public static boolean fastplace_protection;
    public static boolean nobreakdelay_protection;
    public static boolean nuker_protection;
    public static boolean autosign_protection;
    public static boolean invalidblock_break;
    public static boolean invalidblock_interact;
    public static boolean invalidblock_place;
    public static boolean noslowdown_food_protection;
    public static boolean noswing_blockbreak_protection;
    public static boolean noswing_attack_protection;
    public static boolean reach_blockbreak_protection;
    public static boolean throw_egg_protection;
    public static boolean throw_snowball_protection;
    public static boolean invalidinventory_protection;
    public static boolean scaffoldwalk_protection;
    public static boolean regen_protection;
    public static boolean invalidpotion_protection;
    public static boolean cheststeal_protection;
    public static boolean fasteat_protection;
    public static boolean fastbow_protection;
    public static boolean waterwalk_protection;
    public static boolean phase_protection;
    public static boolean noweb_protection;
    public static boolean nofall_protection;
    public static boolean invalidmove_protection;
    public static boolean high_jump_protection;
    public static boolean headless_protection;
    public static boolean fly_protection;
    public static boolean fastfall_protection;
    public static boolean extraelytra_protection;
    public static boolean boatfly_protection;
    public static boolean anticactus_protection;
    public static boolean fastladder_protection;
    public static boolean multiaura_protection;
    public static boolean reach_attack_protection;
    public static boolean chat_spam_protection;
    public static boolean command_spam_protection;
    public static boolean chat_caps_protection;
    public static boolean speed_protection;
    public static boolean glide_protection;
    public static boolean imposibleAttack_protection;
    public static boolean killaura_protection;
    public static String antispam_mesaj;
    public static String crit_func;
    public static long command_per_second;
    public static long chat_per_second;
    public static boolean log_console;
    public static boolean log_player;
	public static boolean broadcast_kick;
	public static boolean bungee_mode;
	public static int min_delay;
	
    public static boolean antibot_protection;
    public static boolean criticals_protection;
    public static int max_player_ping;
    public static boolean world_download_protection;
    public static String world_download_command;
    public static int clicks_per_second;
    public static long time_per_message;
    public static long violation_time;
    
    public static List<?> swearwords;
    public static List<?> disable_in_worlds;
    public static List<?> blocked_cmd;
    
    public static Map<String, Object> flyThreshold;
    public static Map<String, Object> boatFlyThreshold;
    public static Map<String, Object> extraElytraThreshold;
    public static Map<String, Object> fastFallThreshold;
    public static Map<String, Object> fastLadderThreshold;
    public static Map<String, Object> glideThreshold;
    public static Map<String, Object> highJumpThreshold;
    public static Map<String, Object> noFallThreshold;
    public static Map<String, Object> speedThreshold;
    public static Map<String, Object> autoSignThreshold;
    public static Map<String, Object> reachThreshold;
    public static Map<String, Object> noSlowdownThreshold;
    public static Map<String, Object> noSwingBlockThreshold;
    public static Map<String, Object> fastPlaceThreshold;
    public static Map<String, Object> noBreakDelayThreshold;
    public static Map<String, Object> fastBowThreshold;
    public static Map<String, Object> chestStealThreshold;
    public static Map<String, Object> fastEatThreshold;
    public static Map<String, Object> invalidInventoryThreshold;
    public static Map<String, Object> invalidPotionThreshold;
    public static Map<String, Object> regenThreshold;
    public static Map<String, Object> killAuraThreshold;
    public static Map<String, Object> criticalsThreshold;
    public static Map<String, Object> imposibleAttackThreshold;
	public static Map<String, Object> slimeJumpThreshold;
	public static Map<String, Object> invalidHeadThreshold;
	public static Map<String, Object> illegalMoveThreshold;
	public static Map<String, Object> moreMovesThreshold;
	public static Map<String, Object> waterYThreshold;
	public static Map<String, Object> invalidVelocityThreshold;
	public static Map<String, Object> noKnockbackThreshold;
	public static Map<String, Object> antiCactusThreshold;
	public static Map<String, Object> waterWalkThreshold;
	public static Map<String, Object> maxInteractsThreshold;
	public static Map<String, Object> headlessThreshold;
	
	public static boolean killaurathrublocks_playersonly;
	public static boolean badpackets_check;
	public static boolean healthtag_check;
	public static String tabcomplete_message;
	public static boolean tabcomplete_protection;
	public static boolean timer_protection;
	public static boolean illegalmove_protection;
	public static boolean moreMoves_protection;
	public static boolean waterY_protection;
	public static boolean invalidVelocity_protection;
	public static boolean creativeDrop_protection;
	public static boolean maxinteracts_protection;
	public static boolean slimeJump_protection;
	public static boolean invalidHead_protection;
    
    static {
        DacStringBase.no_permission = Main.getThisPlugin().getConfig().getString("non-permission").replaceAll("&", "�");
        DacStringBase.anticheat_tag = Main.getThisPlugin().getConfig().getString("anticheat-tag").replaceAll("&", "�");
        DacStringBase.broadcast = Main.getThisPlugin().getConfig().getString("broadcast-msg").replaceAll("&", "�");
        DacStringBase.kick_mesaj = Main.getThisPlugin().getConfig().getString("kick-msg").replaceAll("&", "�");
        DacStringBase.uyari_msg = Main.getThisPlugin().getConfig().getString("warn-msg").replaceAll("&", "�");
        DacStringBase.swear_msg = Main.getThisPlugin().getConfig().getString("swear-msg").replaceAll("&", "�");
        DacStringBase.spam_kick_msg = Main.getThisPlugin().getConfig().getString("spam-kick-msg").replaceAll("&", "�");
        DacStringBase.ad_msg = Main.getThisPlugin().getConfig().getString("ad-msg").replaceAll("&", "�");
        DacStringBase.hack_msg = Main.getThisPlugin().getConfig().getString("hack-msg").replaceAll("&", "�");
        DacStringBase.block_cmd_msg = Main.getThisPlugin().getConfig().getString("block-cmd-msg").replaceAll("&", "�");
        DacStringBase.log_file_message = Main.getThisPlugin().getConfig().getString("Log.File.message");
        DacStringBase.log_file_enabled = Main.getThisPlugin().getConfig().getBoolean("Log.File.enabled");
        DacStringBase.chat_swear_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Swear.enabled");
        DacStringBase.chat_advertising_ip_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Advertising.IP.enabled");
        DacStringBase.chat_advertising_web_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Advertising.Web.enabled");
        DacStringBase.bungee_mode = Main.getThisPlugin().getConfig().getBoolean("bungee-mode");
        DacStringBase.min_delay = Main.getThisPlugin().getConfig().getInt("min-delay");
        DacStringBase.noknockback_protection = Main.getThisPlugin().getConfig().getBoolean("NoKnockback.enabled");
        DacStringBase.fastplace_protection = Main.getThisPlugin().getConfig().getBoolean("FastPlace.enabled");
        DacStringBase.nobreakdelay_protection = Main.getThisPlugin().getConfig().getBoolean("NoBreakDelay.enabled");
        DacStringBase.nuker_protection = Main.getThisPlugin().getConfig().getBoolean("Nuker.enabled");
        DacStringBase.killaurathrublocks_playersonly = Main.getThisPlugin().getConfig().getBoolean("KillAura.playersOnly");
        DacStringBase.autosign_protection = Main.getThisPlugin().getConfig().getBoolean("AutoSign.enabled");
        DacStringBase.imposibleAttack_protection = Main.getThisPlugin().getConfig().getBoolean("ImposibleAttack.enabled");
        DacStringBase.invalidblock_break = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Break.enabled");
        DacStringBase.invalidblock_interact = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Interact.enabled");
        DacStringBase.invalidblock_place = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Place.enabled");
        DacStringBase.noslowdown_food_protection = Main.getThisPlugin().getConfig().getBoolean("Noslowdown.Food.enabled");
        DacStringBase.noswing_blockbreak_protection = Main.getThisPlugin().getConfig().getBoolean("NoSwing.Block.Damage.enabled");
        DacStringBase.noswing_attack_protection = Main.getThisPlugin().getConfig().getBoolean("NoSwing.Attack.enabled");
        DacStringBase.reach_blockbreak_protection = Main.getThisPlugin().getConfig().getBoolean("Reach.Block.Break.enabled");
        DacStringBase.throw_egg_protection = Main.getThisPlugin().getConfig().getBoolean("Throw.Egg.enabled");
        DacStringBase.throw_snowball_protection = Main.getThisPlugin().getConfig().getBoolean("Throw.Snowball.enabled");
        DacStringBase.invalidinventory_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidInventory.enabled");
        DacStringBase.scaffoldwalk_protection = Main.getThisPlugin().getConfig().getBoolean("ScaffoldWalk.enabled");
        DacStringBase.regen_protection = Main.getThisPlugin().getConfig().getBoolean("Regen.enabled");
        DacStringBase.invalidpotion_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidPotion.enabled");
        DacStringBase.cheststeal_protection = Main.getThisPlugin().getConfig().getBoolean("ChestSteal.enabled");
        DacStringBase.fasteat_protection = Main.getThisPlugin().getConfig().getBoolean("FastEat.enabled");
        DacStringBase.fastbow_protection = Main.getThisPlugin().getConfig().getBoolean("FastBow.enabled");
        DacStringBase.waterwalk_protection = Main.getThisPlugin().getConfig().getBoolean("WaterWalk.enabled");
        DacStringBase.phase_protection = Main.getThisPlugin().getConfig().getBoolean("Phase.enabled");
        DacStringBase.noweb_protection = Main.getThisPlugin().getConfig().getBoolean("NoWeb.enabled");
        DacStringBase.nofall_protection = Main.getThisPlugin().getConfig().getBoolean("NoFall.enabled");
        DacStringBase.invalidmove_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidMove.enabled");
        DacStringBase.reach_attack_protection = Main.getThisPlugin().getConfig().getBoolean("Reach.Attack.enabled");
        DacStringBase.high_jump_protection = Main.getThisPlugin().getConfig().getBoolean("HighJump.enabled");
        DacStringBase.headless_protection = Main.getThisPlugin().getConfig().getBoolean("Headless.enabled");
        DacStringBase.fly_protection = Main.getThisPlugin().getConfig().getBoolean("Fly.enabled");
        DacStringBase.fastfall_protection = Main.getThisPlugin().getConfig().getBoolean("FastFall.enabled");
        DacStringBase.extraelytra_protection = Main.getThisPlugin().getConfig().getBoolean("ExtraElytra.enabled");
        DacStringBase.slimeJump_protection = Main.getThisPlugin().getConfig().getBoolean("SlimeJump.enabled");
        DacStringBase.boatfly_protection = Main.getThisPlugin().getConfig().getBoolean("BoatFly.enabled");
        DacStringBase.anticactus_protection = Main.getThisPlugin().getConfig().getBoolean("AntiCactus.enabled");
        DacStringBase.fastladder_protection = Main.getThisPlugin().getConfig().getBoolean("FastLadder.enabled");
        DacStringBase.time_per_message = Main.getThisPlugin().getConfig().getLong("time-per-message");
        DacStringBase.multiaura_protection = Main.getThisPlugin().getConfig().getBoolean("MultiAura.enabled");
        DacStringBase.chat_spam_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Spam.enabled");
        DacStringBase.command_spam_protection = Main.getThisPlugin().getConfig().getBoolean("Command.Spam.enabled");
        DacStringBase.chat_caps_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.CAPS.enabled");
        DacStringBase.speed_protection = Main.getThisPlugin().getConfig().getBoolean("Speed.enabled");
        DacStringBase.glide_protection = Main.getThisPlugin().getConfig().getBoolean("Glide.enabled");
        DacStringBase.killaura_protection = Main.getThisPlugin().getConfig().getBoolean("KillAura.enabled");
        DacStringBase.antispam_mesaj = Main.getThisPlugin().getConfig().getString("antispam-msg").replaceAll("&", "�");
        DacStringBase.crit_func = Main.getThisPlugin().getConfig().getString("Criticals.function");
        //DacStringBase.nofall_func = Main.getThisPlugin().getConfig().getString("NoFall.function");
        DacStringBase.command_per_second = Main.getThisPlugin().getConfig().getLong("command-per-second");
        DacStringBase.chat_per_second = Main.getThisPlugin().getConfig().getLong("chat-per-second");
        DacStringBase.log_console = Main.getThisPlugin().getConfig().getBoolean("log-console");
        DacStringBase.log_player = Main.getThisPlugin().getConfig().getBoolean("log-player");
        DacStringBase.broadcast_kick = Main.getThisPlugin().getConfig().getBoolean("broadcast-kick");
        DacStringBase.antibot_protection = Main.getThisPlugin().getConfig().getBoolean("AntiBot.enabled");
        DacStringBase.criticals_protection = Main.getThisPlugin().getConfig().getBoolean("Criticals.enabled");
        DacStringBase.max_player_ping = Main.getThisPlugin().getConfig().getInt("max-player-ping");
        DacStringBase.world_download_protection = Main.getThisPlugin().getConfig().getBoolean("WorldDownloader.enabled");
        DacStringBase.world_download_command = Main.getThisPlugin().getConfig().getString("WorldDownloader.command");
        DacStringBase.tabcomplete_protection = Main.getThisPlugin().getConfig().getBoolean("TabComplete.enabled");
        DacStringBase.tabcomplete_message = Main.getThisPlugin().getConfig().getString("TabComplete.message").replaceAll("&", "�");
        DacStringBase.timer_protection = Main.getThisPlugin().getConfig().getBoolean("Timer.enabled");
        DacStringBase.illegalmove_protection = Main.getThisPlugin().getConfig().getBoolean("IllegalMove.enabled");
        DacStringBase.moreMoves_protection = Main.getThisPlugin().getConfig().getBoolean("MoreMoves.enabled");
        DacStringBase.waterY_protection = Main.getThisPlugin().getConfig().getBoolean("WaterY.enabled");
        DacStringBase.invalidVelocity_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidVelocity.enabled");
        DacStringBase.creativeDrop_protection = Main.getThisPlugin().getConfig().getBoolean("CreativeDrop.enabled");
        DacStringBase.invalidHead_protection = Main.getThisPlugin().getConfig().getBoolean("ScaffoldWalk.enabled");
        DacStringBase.maxinteracts_protection = Main.getThisPlugin().getConfig().getBoolean("MaxInteracts.enabled");
        DacStringBase.badpackets_check = Main.getThisPlugin().getConfig().getBoolean("BadPackets.enabled");
        DacStringBase.healthtag_check = Main.getThisPlugin().getConfig().getBoolean("HealthTag.enabled");
        DacStringBase.clicks_per_second = Main.getThisPlugin().getConfig().getInt("clicks-per-second");
        DacStringBase.violation_time = Main.getThisPlugin().getConfig().getLong("violation-reset-time");
        DacStringBase.swearwords = Main.getThisPlugin().getConfig().getList("Chat.Swear.swearwords");
        DacStringBase.disable_in_worlds = Main.getThisPlugin().getConfig().getList("disable-in-worlds");
        DacStringBase.blocked_cmd = Main.getThisPlugin().getConfig().getList("Command.blocked-cmd");
        DacStringBase.flyThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Fly.threshold").getValues(true);
        DacStringBase.boatFlyThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("BoatFly.threshold").getValues(true);
        DacStringBase.extraElytraThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ExtraElytra.threshold").getValues(true);
        DacStringBase.fastFallThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastFall.threshold").getValues(true);
        DacStringBase.fastLadderThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastLadder.threshold").getValues(true);
        DacStringBase.glideThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Glide.threshold").getValues(true);
        DacStringBase.highJumpThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("HighJump.threshold").getValues(true);
        DacStringBase.noFallThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoFall.threshold").getValues(true);
        DacStringBase.speedThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Speed.threshold").getValues(true);
        DacStringBase.autoSignThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("AutoSign.threshold").getValues(true);
        DacStringBase.reachThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Reach.threshold").getValues(true);
        DacStringBase.noSlowdownThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoSlowdown.threshold").getValues(true);
        DacStringBase.noSwingBlockThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoSwing.Block.Damage.threshold").getValues(true);
        DacStringBase.fastPlaceThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastPlace.threshold").getValues(true);
        DacStringBase.noBreakDelayThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoBreakDelay.threshold").getValues(true);
        DacStringBase.fastBowThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastBow.threshold").getValues(true);
        DacStringBase.chestStealThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ChestSteal.threshold").getValues(true);
        DacStringBase.fastEatThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastEat.threshold").getValues(true);
        DacStringBase.invalidInventoryThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidInventory.threshold").getValues(true);
        DacStringBase.invalidPotionThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidPotion.threshold").getValues(true);
        DacStringBase.imposibleAttackThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ImposibleAttack.threshold").getValues(true);
        DacStringBase.regenThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Regen.threshold").getValues(true);
        DacStringBase.killAuraThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("KillAura.threshold").getValues(true);
        DacStringBase.criticalsThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Criticals.threshold").getValues(true);
        DacStringBase.slimeJumpThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("SlimeJump.threshold").getValues(true);
        DacStringBase.invalidHeadThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ScaffoldWalk.threshold").getValues(true);
        DacStringBase.illegalMoveThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("IllegalMove.threshold").getValues(true);
        DacStringBase.moreMovesThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("MoreMoves.threshold").getValues(true);
        DacStringBase.waterYThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("WaterY.threshold").getValues(true);
        DacStringBase.invalidVelocityThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidVelocity.threshold").getValues(true);
        DacStringBase.noKnockbackThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoKnockback.threshold").getValues(true);
        DacStringBase.antiCactusThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("AntiCactus.threshold").getValues(true);
        DacStringBase.waterWalkThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("WaterWalk.threshold").getValues(true);
        DacStringBase.maxInteractsThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("MaxInteracts.threshold").getValues(true);
        DacStringBase.headlessThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Headless.threshold").getValues(true);
    }
    
    public static void reloadTags() {
        Bukkit.getScheduler().runTaskLater(Main.getThisPlugin(), new Runnable() {
            public void run() {
                DacStringBase.anticheat_tag = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("anticheat-tag").replaceAll("&", "�"));
                DacStringBase.no_permission = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("non-permission").replaceAll("&", "�"));
                DacStringBase.broadcast = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("broadcast-msg").replaceAll("&", "�"));
                DacStringBase.kick_mesaj = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("kick-msg").replaceAll("&", "�"));
                DacStringBase.uyari_msg = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("warn-msg").replaceAll("&", "�"));
                DacStringBase.spam_kick_msg = Main.getThisPlugin().getConfig().getString("spam-kick-msg").replaceAll("&", "�");
                DacStringBase.antispam_mesaj = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("antispam-msg").replaceAll("&", "�"));
                DacStringBase.block_cmd_msg = Main.getThisPlugin().getConfig().getString("block-cmd-msg").replaceAll("&", "�");
                DacStringBase.chat_per_second = Main.getThisPlugin().getConfig().getLong("chat-per-second");
                DacStringBase.command_per_second = Main.getThisPlugin().getConfig().getLong("command-per-second");
                DacStringBase.log_console = Main.getThisPlugin().getConfig().getBoolean("log-console");
                DacStringBase.bungee_mode = Main.getThisPlugin().getConfig().getBoolean("bungee-mode");
                DacStringBase.min_delay = Main.getThisPlugin().getConfig().getInt("min-delay");
                DacStringBase.broadcast_kick = Main.getThisPlugin().getConfig().getBoolean("broadcast-kick");
                DacStringBase.antibot_protection = Main.getThisPlugin().getConfig().getBoolean("AntiBot.enabled");
                DacStringBase.criticals_protection = Main.getThisPlugin().getConfig().getBoolean("Criticals.enabled");
                DacStringBase.max_player_ping = Main.getThisPlugin().getConfig().getInt("max-player-ping");
                DacStringBase.swearwords = Main.getThisPlugin().getConfig().getList("Chat.Swear.swearwords");
                DacStringBase.swear_msg = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("swear-msg").replaceAll("&", "�"));
                DacStringBase.ad_msg = DacStringBase.replaceColorcode(Main.getThisPlugin().getConfig().getString("ad-msg").replaceAll("&", "�"));
                DacStringBase.log_file_message = Main.getThisPlugin().getConfig().getString("Log.message");
                DacStringBase.log_file_enabled = Main.getThisPlugin().getConfig().getBoolean("Log.enabled");
                DacStringBase.log_player = Main.getThisPlugin().getConfig().getBoolean("log-player");
                DacStringBase.clicks_per_second = Main.getThisPlugin().getConfig().getInt("clicks-per-second");
                DacStringBase.world_download_protection = Main.getThisPlugin().getConfig().getBoolean("WorldDownloader.enabled");
                DacStringBase.chat_swear_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Swear.enabled");
                DacStringBase.chat_caps_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.CAPS.enabled");
                DacStringBase.chat_advertising_ip_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Advertising.IP.enabled");
                DacStringBase.chat_advertising_web_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Advertising.Web.enabled");
                DacStringBase.chat_spam_protection = Main.getThisPlugin().getConfig().getBoolean("Chat.Spam.enabled");
                DacStringBase.command_spam_protection = Main.getThisPlugin().getConfig().getBoolean("Command.Spam.enabled");
                DacStringBase.noknockback_protection = Main.getThisPlugin().getConfig().getBoolean("NoKnockback.enabled");
                DacStringBase.fastplace_protection = Main.getThisPlugin().getConfig().getBoolean("FastPlace.enabled");
                DacStringBase.nobreakdelay_protection = Main.getThisPlugin().getConfig().getBoolean("NoBreakDelay.enabled");
                DacStringBase.nuker_protection = Main.getThisPlugin().getConfig().getBoolean("Nuker.enabled");
                DacStringBase.killaurathrublocks_playersonly = Main.getThisPlugin().getConfig().getBoolean("KillAura.playersOnly");
                DacStringBase.autosign_protection = Main.getThisPlugin().getConfig().getBoolean("AutoSign.enabled");
                DacStringBase.invalidblock_break = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Break.enabled");
                DacStringBase.invalidblock_interact = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Interact.enabled");
                DacStringBase.invalidinventory_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidInventory.enabled");
                DacStringBase.scaffoldwalk_protection = Main.getThisPlugin().getConfig().getBoolean("ScaffoldWalk.enabled");
                DacStringBase.reach_attack_protection = Main.getThisPlugin().getConfig().getBoolean("Reach.Attack.enabled");
                DacStringBase.invalidblock_place = Main.getThisPlugin().getConfig().getBoolean("InvalidBlock.Place.enabled");
                DacStringBase.noslowdown_food_protection = Main.getThisPlugin().getConfig().getBoolean("NoSlowdown.Food.enabled");
                DacStringBase.noswing_blockbreak_protection = Main.getThisPlugin().getConfig().getBoolean("NoSwing.Block.Damage.enabled");
                DacStringBase.noswing_attack_protection = Main.getThisPlugin().getConfig().getBoolean("NoSwing.Attack.enabled");
                DacStringBase.imposibleAttack_protection = Main.getThisPlugin().getConfig().getBoolean("ImposibleAttack.enabled");
                DacStringBase.reach_blockbreak_protection = Main.getThisPlugin().getConfig().getBoolean("Reach.Block.Break.enabled");
                DacStringBase.throw_egg_protection = Main.getThisPlugin().getConfig().getBoolean("Throw.Egg.enabled");
                DacStringBase.throw_snowball_protection = Main.getThisPlugin().getConfig().getBoolean("Throw.Snowball.enabled");
                DacStringBase.regen_protection = Main.getThisPlugin().getConfig().getBoolean("Regen.enabled");
                DacStringBase.invalidpotion_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidPotion.enabled");
                DacStringBase.crit_func = Main.getThisPlugin().getConfig().getString("Criticals.function");
                DacStringBase.time_per_message = Main.getThisPlugin().getConfig().getLong("time-per-message");
                //DacStringBase.nofall_func = Main.getThisPlugin().getConfig().getString("NoFall.function");
                DacStringBase.cheststeal_protection = Main.getThisPlugin().getConfig().getBoolean("ChestSteal.enabled");
                DacStringBase.fasteat_protection = Main.getThisPlugin().getConfig().getBoolean("FastEat.enabled");
                DacStringBase.fastbow_protection = Main.getThisPlugin().getConfig().getBoolean("FastBow.enabled");
                DacStringBase.killaura_protection = Main.getThisPlugin().getConfig().getBoolean("KillAura.enabled");
                DacStringBase.reach_attack_protection = Main.getThisPlugin().getConfig().getBoolean("Reach.Attack.enabled");
                DacStringBase.waterwalk_protection = Main.getThisPlugin().getConfig().getBoolean("WaterWalk.enabled");
                DacStringBase.phase_protection = Main.getThisPlugin().getConfig().getBoolean("Phase.enabled");
                DacStringBase.nofall_protection = Main.getThisPlugin().getConfig().getBoolean("NoFall.enabled");
                DacStringBase.invalidmove_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidMove.enabled");
                DacStringBase.high_jump_protection = Main.getThisPlugin().getConfig().getBoolean("HighJump.enabled");
                DacStringBase.headless_protection = Main.getThisPlugin().getConfig().getBoolean("Headless.enabled");
                DacStringBase.fly_protection = Main.getThisPlugin().getConfig().getBoolean("Fly.enabled");
                DacStringBase.fastfall_protection = Main.getThisPlugin().getConfig().getBoolean("FastFall.enabled");
                DacStringBase.extraelytra_protection = Main.getThisPlugin().getConfig().getBoolean("ExtraElytra.enabled");
                DacStringBase.boatfly_protection = Main.getThisPlugin().getConfig().getBoolean("BoatFly.enabled");
                DacStringBase.speed_protection = Main.getThisPlugin().getConfig().getBoolean("Speed.enabled");
                DacStringBase.anticactus_protection = Main.getThisPlugin().getConfig().getBoolean("AntiCactus.enabled");
                DacStringBase.antibot_protection = Main.getThisPlugin().getConfig().getBoolean("AntiBot.enabled");
                DacStringBase.fastladder_protection = Main.getThisPlugin().getConfig().getBoolean("FastLadder.enabled");
                DacStringBase.world_download_command = Main.getThisPlugin().getConfig().getString("WorldDownloader.command");
                DacStringBase.multiaura_protection = Main.getThisPlugin().getConfig().getBoolean("MultiAura.enabled");
                DacStringBase.swearwords = Main.getThisPlugin().getConfig().getList("Chat.Swear.swearwords");
                DacStringBase.tabcomplete_protection = Main.getThisPlugin().getConfig().getBoolean("TabComplete.enabled");
                DacStringBase.tabcomplete_message = Main.getThisPlugin().getConfig().getString("TabComplete.message").replaceAll("&", "�");
                DacStringBase.timer_protection = Main.getThisPlugin().getConfig().getBoolean("Timer.enabled");
                DacStringBase.illegalmove_protection = Main.getThisPlugin().getConfig().getBoolean("IllegalMove.enabled");
                DacStringBase.moreMoves_protection = Main.getThisPlugin().getConfig().getBoolean("MoreMoves.enabled");
                DacStringBase.waterY_protection = Main.getThisPlugin().getConfig().getBoolean("WaterY.enabled");
                DacStringBase.invalidVelocity_protection = Main.getThisPlugin().getConfig().getBoolean("InvalidVelocity.enabled");
                DacStringBase.creativeDrop_protection = Main.getThisPlugin().getConfig().getBoolean("CreativeDrop.enabled");
                DacStringBase.invalidHead_protection = Main.getThisPlugin().getConfig().getBoolean("ScaffoldWalk.enabled");
                DacStringBase.maxinteracts_protection = Main.getThisPlugin().getConfig().getBoolean("MaxInteracts.enabled");
                DacStringBase.badpackets_check = Main.getThisPlugin().getConfig().getBoolean("BadPackets.enabled");
                DacStringBase.healthtag_check = Main.getThisPlugin().getConfig().getBoolean("HealthTag.enabled");
                DacStringBase.violation_time = Main.getThisPlugin().getConfig().getLong("violation-reset-time");
                DacStringBase.hack_msg = Main.getThisPlugin().getConfig().getString("hack-msg").replaceAll("&", "�");
                DacStringBase.disable_in_worlds = Main.getThisPlugin().getConfig().getList("disable-in-worlds");
                DacStringBase.blocked_cmd = Main.getThisPlugin().getConfig().getList("Command.blocked-cmd");
                DacStringBase.flyThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Fly.threshold").getValues(true);
                DacStringBase.boatFlyThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("BoatFly.threshold").getValues(true);
                DacStringBase.extraElytraThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ExtraElytra.threshold").getValues(true);
                DacStringBase.fastFallThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastFall.threshold").getValues(true);
                DacStringBase.fastLadderThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastLadder.threshold").getValues(true);
                DacStringBase.glideThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Glide.threshold").getValues(true);
                DacStringBase.highJumpThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("HighJump.threshold").getValues(true);
                DacStringBase.noFallThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoFall.threshold").getValues(true);
                DacStringBase.speedThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Speed.threshold").getValues(true);
                DacStringBase.autoSignThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("AutoSign.threshold").getValues(true);
                DacStringBase.reachThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Reach.threshold").getValues(true);
                DacStringBase.imposibleAttackThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ImposibleAttack.threshold").getValues(true);
                DacStringBase.noSlowdownThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoSlowdown.threshold").getValues(true);
                DacStringBase.noSwingBlockThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoSwing.Block.Damage.threshold").getValues(true);
                DacStringBase.fastPlaceThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastPlace.threshold").getValues(true);
                DacStringBase.noBreakDelayThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoBreakDelay.threshold").getValues(true);
                DacStringBase.fastBowThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastBow.threshold").getValues(true);
                DacStringBase.chestStealThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ChestSteal.threshold").getValues(true);
                DacStringBase.fastEatThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("FastEat.threshold").getValues(true);
                DacStringBase.invalidInventoryThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidInventory.threshold").getValues(true);
                DacStringBase.invalidPotionThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidPotion.threshold").getValues(true);
                DacStringBase.regenThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Regen.threshold").getValues(true);
                DacStringBase.killAuraThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("KillAura.threshold").getValues(true);
                DacStringBase.criticalsThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Criticals.threshold").getValues(true);
                DacStringBase.slimeJumpThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("SlimeJump.threshold").getValues(true);
                DacStringBase.invalidHeadThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("ScaffoldWalk.threshold").getValues(true);
                DacStringBase.illegalMoveThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("IllegalMove.threshold").getValues(true);
                DacStringBase.moreMovesThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("MoreMoves.threshold").getValues(true);
                DacStringBase.waterYThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("WaterY.threshold").getValues(true);
                DacStringBase.invalidVelocityThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("InvalidVelocity.threshold").getValues(true);
                DacStringBase.noKnockbackThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("NoKnockback.threshold").getValues(true);
                DacStringBase.antiCactusThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("AntiCactus.threshold").getValues(true);
                DacStringBase.waterWalkThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("WaterWalk.threshold").getValues(true);
                DacStringBase.maxInteractsThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("MaxInteracts.threshold").getValues(true);
                DacStringBase.headlessThreshold = Main.getThisPlugin().getConfig().getConfigurationSection("Headless.threshold").getValues(true);
            }
        }, 5L);
    }
    
    public static String replaceColorcode(final String s) {
        return s.replaceAll("&", "�");
    }
}
