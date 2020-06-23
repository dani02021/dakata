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


package bg.anticheat.dakata;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import bg.anticheat.utils.*;

import org.bukkit.event.player.*;

public class InventoryBuilder implements Listener
{
    public static HashMap<Player, Location> freezeliste;
    private static ArrayList<String> lagliste;
    private static HashMap<String, String> wantBan;
    public static HashMap<String, String> reportiste;
    public static HashMap<String, String> reportiste1;
    
    static {
        InventoryBuilder.freezeliste = new HashMap<Player, Location>();
        InventoryBuilder.lagliste = new ArrayList<String>();
        InventoryBuilder.wantBan = new HashMap<String, String>();
        InventoryBuilder.reportiste = new HashMap<String, String>();
        InventoryBuilder.reportiste1 = new HashMap<String, String>();
    }
    
    public static Inventory buildReportList() {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§7Dakata §8- §7Report");
        new Thread("AsyncItemset") {
            @Override
            public void run() {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    //try {
                    //    Thread.sleep(70L);
                    //}
                    //catch (InterruptedException ex) {}
                    if (InventoryBuilder.reportiste.containsKey(p.getName())) {
                        final ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        final SkullMeta headm = (SkullMeta)head.getItemMeta();
                        headm.setOwner(p.getName());
                        headm.setDisplayName("§a" + p.getName());
                        final String[] lore = { "§7Report by: §a" + InventoryBuilder.reportiste.get(p.getName()), "§7Reason:§a " + InventoryBuilder.reportiste1.get(p.getName()) };
                        headm.setLore(InventoryBuilder.generateLore(lore));
                        head.setItemMeta(headm);
                        inv.addItem(new ItemStack[] { head });
                        
                        final ItemStack CC = new ItemStack(Material.COMPASS, 1);
                        ItemMeta aaa = CC.getItemMeta();
                        aaa.setDisplayName("§aBack");
                        CC.setItemMeta(aaa);
                        inv.setItem(53, CC);
                    }
                }
            }
        }.start();
        return inv;
    }
    
    public static Inventory buildReportList1(final String player) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§7Dakata §8- §7Report Commands");
        new Thread("AsyncItemset") {
            @Override
            public void run() {
                try {
                    Thread.sleep(70L);
                }
                catch (InterruptedException ex) {}
                final ItemStack a = new ItemStack(Material.APPLE);
                final ItemMeta aa = a.getItemMeta();
                aa.setDisplayName("§8Teleport");
                a.setItemMeta(aa);
                inv.setItem(10, a);
                final ItemStack a2 = new ItemStack(Material.PAPER);
                final ItemMeta aa2 = a2.getItemMeta();
                aa2.setDisplayName("§8Remove report");
                a2.setItemMeta(aa2);
                inv.setItem(16, a2);
                final ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                final SkullMeta headm = (SkullMeta)head.getItemMeta();
                headm.setOwner(player);
                headm.setDisplayName("§a" + player);
                head.setItemMeta(headm);
                inv.setItem(13, head);
                
                final ItemStack CC = new ItemStack(Material.COMPASS, 1);
                ItemMeta aaa = CC.getItemMeta();
                aaa.setDisplayName("§aBack");
                CC.setItemMeta(aaa);
                inv.setItem(26, CC);
            }
        }.start();
        return inv;
    }
    
    public static Inventory buildPlayerList(final Player opened) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§7Dakata §8- §7Players");
        new Thread("AsyncItemset") {
            @Override
            public void run() {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    try {
                        Thread.sleep(70L);
                    }
                    catch (InterruptedException ex) {}
                    if (!opened.getOpenInventory().getTitle().equalsIgnoreCase("§7Dakata §8- §7Players")) {
                        this.interrupt();
                        return;
                    }
                    final ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                    final SkullMeta headm = (SkullMeta)head.getItemMeta();
                    headm.setOwner(p.getName());
                    headm.setDisplayName("§a" + p.getName());
                    final String[] lore = { "§7Info about §a" + p.getName(), "", "§7OP: §a" + p.isOp(), "§7Is Flying: §a" + p.isFlying(), "§7Is Allowed to Fly: §a" + p.getAllowFlight(), "§7Health: §a" + p.getHealth() + "/" + p.getMaxHealth(), "§7Food: §a" + p.getFoodLevel(), "§7GameMode: §a" + p.getGameMode(), "§7Adress: §a" + p.getAddress().getHostName() };
                    headm.setLore(InventoryBuilder.generateLore(lore));
                    head.setItemMeta(headm);
                    inv.addItem(new ItemStack[] { head });
                    
                    final ItemStack CC = new ItemStack(Material.COMPASS, 1);
                    ItemMeta aaa = CC.getItemMeta();
                    aaa.setDisplayName("§aBack");
                    CC.setItemMeta(aaa);
                    inv.setItem(53, CC);
                }
            }
        }.start();
        return inv;
    }
    
    public static Inventory buildMain() {
        final Inventory inv = Bukkit.createInventory(null, 9, "§7Dakata");
        new Thread("AsyncItemset") {
            @Override
            public void run() {
                final ItemStack a1 = new ItemStack(Material.BOOK_AND_QUILL);
                final ItemMeta aa1 = a1.getItemMeta();
                aa1.setDisplayName("§aConfig Reload");
                a1.setItemMeta(aa1);
                final ItemStack a2 = new ItemStack(Material.IRON_AXE);
                final ItemMeta aa2 = a2.getItemMeta();
                aa2.setDisplayName("§aAdmin GUI");
                a2.setItemMeta(aa2);
                final ItemStack a3 = new ItemStack(Material.BOW);
                final ItemMeta aa3 = a3.getItemMeta();
                aa3.setDisplayName("§aPer World Check");
                a3.setItemMeta(aa3);
                final ItemStack a4 = new ItemStack(Material.BOWL);
                final ItemMeta aa4 = a4.getItemMeta();
                aa4.setDisplayName("§aDisable Check(SOON)");
                a4.setItemMeta(aa4);
                final ItemStack a5 = new ItemStack(Material.BOOKSHELF);
                final ItemMeta aa5 = a5.getItemMeta();
                aa5.setDisplayName("§aReport List");
                a5.setItemMeta(aa5);
                inv.addItem(a1);
                inv.setItem(2, a2);
                inv.setItem(4, a3);
                inv.setItem(6, a4);
                inv.setItem(8, a5);
            }
        }.start();
        return inv;
    }
    
    private static Inventory buildMinutes(final Player punished) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 18, "§7Dakata §8- §7Time");
        new Thread("AsyncItemset1") {
            @Override
            public void run() {
                final ItemStack m15 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m15.getItemMeta().setDisplayName("§715 Minutes");
                final ItemStack m16 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m16.getItemMeta().setDisplayName("§730 Minutes");
                final ItemStack m17 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m17.getItemMeta().setDisplayName("§745 Minutes");
                final ItemStack m18 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m18.getItemMeta().setDisplayName("§760 Minutes");
                final ItemStack m19 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m19.getItemMeta().setDisplayName("§7120 Minutes");
                final ItemStack m20 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                m20.getItemMeta().setDisplayName("§7180 Minutes");
                final ItemStack d1 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                d1.getItemMeta().setDisplayName("§71 Day");
                final ItemStack d2 = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                d2.getItemMeta().setDisplayName("§73 Days");
                final ItemStack p = new ItemStack(Material.THIN_GLASS, 1, (short)4);
                p.getItemMeta().setDisplayName("§7Pernament");
                inv.addItem(new ItemStack[] { m15 });
            }
        }.start();
        return inv;
    }
    
    private static Inventory buildPerWorldCheck() {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§7Dakata §8- §7Worlds");
        new Thread("AsyncItemset1") {
            @Override
            public void run() {
                for (final World w : Bukkit.getWorlds()) {
                    final String[] lore = { "§7Players online: §a" + (w.getPlayers().size()-1), "§7PVP enabled: §a" + w.getPVP(), "§7Allow monsters: §a" + w.getAllowMonsters(), "§7Allow animals: §a" + w.getAllowAnimals(), "§7Difficulty: §a" + w.getDifficulty() };
                    ItemStack i;
                    if (Main.getThisPlugin().getConfig().getStringList("disable-in-worlds").contains(w.getName())) {
                        i = new ItemStack(Material.ENCHANTED_BOOK);
                        final ItemMeta m = i.getItemMeta();
                        m.setDisplayName(w.getName());
                        m.setLore(InventoryBuilder.generateLore(lore));
                        i.setItemMeta(m);
                    }
                    else {
                        i = new ItemStack(Material.BOOK);
                        final ItemMeta m = i.getItemMeta();
                        m.setDisplayName(w.getName());
                        m.setLore(InventoryBuilder.generateLore(lore));
                        i.setItemMeta(m);
                    }
                    inv.addItem(new ItemStack[] { i });
                    
                    final ItemStack CC = new ItemStack(Material.COMPASS, 1);
                    ItemMeta aaa = CC.getItemMeta();
                    aaa.setDisplayName("§aBack");
                    CC.setItemMeta(aaa);
                    inv.setItem(53, CC);
                }
            }
        }.start();
        return inv;
    }
    
    public static Inventory buildPunish(final String punishe) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§7Dakata §8- §7Punish");
        new Thread("AsyncItemset") {
            @Override
            public void run() {
                final Player punished = Bukkit.getPlayerExact(punishe);
                if (punished == null) {
                    return;
                }
                final ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                final SkullMeta headm = (SkullMeta)head.getItemMeta();
                headm.setOwner(punished.getName());
                headm.setDisplayName("§a" + punished.getName());
                final String[] lore = { "§7Infos about §a" + punished.getName(), "", "§7OP: §a" + punished.isOp(), "§7Is Flying: §a" + punished.isFlying(), "§7Is Allowed to Fly: §a" + punished.getAllowFlight(), "§7Health: §a" + punished.getHealth() + "/" + punished.getMaxHealth(), "§7Food: §a" + punished.getFoodLevel(), "§7GameMode: §a" + punished.getGameMode(), "§7Adress: §a" + punished.getAddress().getHostName() };
                headm.setLore(InventoryBuilder.generateLore(lore));
                head.setItemMeta(headm);
                inv.setItem(13, head);
                inv.setItem(9, InventoryBuilder.generateItem(Material.ANVIL, "§cBan(SOON)", null));
                inv.setItem(10, InventoryBuilder.generateItem(Material.GOLD_AXE, "§cKick", null));
                inv.setItem(11, InventoryBuilder.generateItem(Material.BONE, "§cKill", null));
                if (InventoryBuilder.isFreezed(punished)) {
                    inv.setItem(15, InventoryBuilder.generateItem(Material.REDSTONE_BLOCK, "§cUnfreeze", null));
                }
                else {
                    inv.setItem(15, InventoryBuilder.generateItem(Material.PACKED_ICE, "§9Freeze", null));
                }
                if (InventoryBuilder.isLagged(punished)) {
                    inv.setItem(16, InventoryBuilder.generateItem(Material.SLIME_BALL, "§cUnlag", null));
                }
                else {
                    inv.setItem(16, InventoryBuilder.generateItem(Material.SNOW_BALL, "§9Lag", null));
                }
                inv.setItem(17, InventoryBuilder.generateItem(Material.ANVIL, "§cMute(SOON)", null));
                
                final ItemStack CC = new ItemStack(Material.COMPASS, 1);
                ItemMeta aaa = CC.getItemMeta();
                aaa.setDisplayName("§aBack");
                CC.setItemMeta(aaa);
                inv.setItem(26, CC);
            }
        }.start();
        return inv;
    }
    
    public static List<String> generateLore(final String[] text) {
        final List<String> lore = new ArrayList<String>();
        for (final String s : text) {
            lore.add(s);
        }
        return lore;
    }
    
    public static ItemStack generateItem(final Material mat, final String display, final List<String> lore) {
        final ItemStack it = new ItemStack(mat);
        final ItemMeta im = it.getItemMeta();
        im.setDisplayName(display);
        im.setLore(lore);
        it.setItemMeta(im);
        return it;
    }
    
    public static ItemStack generateItem(final Material mat, final String display, final List<String> lore, final int damage) {
        final ItemStack it = new ItemStack(mat);
        final ItemMeta im = it.getItemMeta();
        im.setDisplayName(display);
        im.setLore(lore);
        it.setItemMeta(im);
        it.setDurability((short)damage);
        return it;
    }
    
    @EventHandler
    public void onClose(final InventoryCloseEvent e) {
        if (e.getView().getTitle().equals("§7Dakata §8- §7Time")) {
            InventoryBuilder.wantBan.containsKey(e.getPlayer().getName());
        }
    }
    
    @EventHandler
    public static void onEvent(InventoryOpenEvent e) {
		// Chest with name Dakata bypass code
		if (e.getView().getTitle().contains("Dakata")) {
			if (e.getView().getTitle().equals("§7Dakata §8- §7Players"))
				if (!e.getPlayer().hasPermission("Dakata.GUI.Players")) {
					e.setCancelled(true);
					e.getPlayer().closeInventory();
					e.getPlayer().sendMessage(DacStringBase.replaceColorcode(DacStringBase.anticheat_tag+DacStringBase.no_permission));
				}
			if (e.getView().getTitle().equals("§7Dakata §8- §7Punish"))
				if (!e.getPlayer().hasPermission("Dakata.GUI.Punish")) {
					e.setCancelled(true);
					e.getPlayer().closeInventory();
					e.getPlayer().sendMessage(DacStringBase.replaceColorcode(DacStringBase.anticheat_tag+DacStringBase.no_permission));
				}
			if (e.getView().getTitle().equals("§7Dakata §8- §7Worlds"))
				if (!e.getPlayer().hasPermission("Dakata.GUI.Worlds")) {
					e.setCancelled(true);
					e.getPlayer().closeInventory();
					e.getPlayer().sendMessage(DacStringBase.replaceColorcode(DacStringBase.anticheat_tag+DacStringBase.no_permission));
				}
			if (e.getView().getTitle().equals("§7Dakata §8- §7Report"))
				if (!e.getPlayer().hasPermission("Dakata.GUI.Report")) {
					e.setCancelled(true);
					e.getPlayer().closeInventory();
					e.getPlayer().sendMessage(DacStringBase.replaceColorcode(DacStringBase.anticheat_tag+DacStringBase.no_permission));
				}
		}
	}
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final Inventory i = e.getInventory();
        if (i == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        if (e.getView().getTitle().equalsIgnoreCase("§7Dakata §8- §7Players")) {
        	if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
        		p.openInventory(buildMain());
        	} else {
                p.openInventory(buildPunish(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())));
        	}
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§7Dakata §8- §7Punish")) {
            final Player punished = Bukkit.getPlayerExact(ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()));
            e.setCancelled(true);
            if (e.getSlot() == 10) {
                if (p.getOpenInventory().getTitle().equals("§7Dakata §8- §7Punish") || p.getOpenInventory().getTitle().equals("§7Dakata §8- §Players")) {
                    p.getOpenInventory().close();
                }
                punished.kickPlayer("");
                p.getOpenInventory().close();
            }
            else if (e.getSlot() == 11) {
                punished.setHealth(0.0);
            } else if (e.getSlot() == 15) {
                freezePlayer(punished);
            } else if (e.getSlot() == 16) {
                this.lagPlayer(punished);
            } else if(e.getSlot() == 26) {
            	p.openInventory(buildPlayerList((Player)e.getWhoClicked()));
            	return;
            }
            p.openInventory(buildPunish(ChatColor.stripColor(i.getItem(13).getItemMeta().getDisplayName())));
        }
        else if (e.getView().getTitle().equals("§7Dakata")) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                Main.getThisPlugin().reloadConfig();
                DacStringBase.reloadTags();
                e.getWhoClicked().getOpenInventory().close();
                e.getWhoClicked().sendMessage(String.valueOf(DacStringBase.anticheat_tag) + "Config Reloaded");
            }
            else if (e.getSlot() == 2) {
                e.getWhoClicked().getOpenInventory().close();
                e.getWhoClicked().openInventory(buildPlayerList((Player)e.getWhoClicked()));
            }
            else if (e.getSlot() == 4) {
                e.getWhoClicked().getOpenInventory().close();
                e.getWhoClicked().openInventory(buildPerWorldCheck());
            }
            else if (e.getSlot() == 8) {
                e.getWhoClicked().getOpenInventory().close();
                e.getWhoClicked().openInventory(buildReportList());
            }
        }
        else if (e.getView().getTitle().equals("§7Dakata §8- §7Time")) {
            final Player punished = Bukkit.getPlayerExact(ChatColor.stripColor(e.getInventory().getItem(5).getItemMeta().getDisplayName()));
            if (e.getSlot() == 0) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 15m");
            }
            else if (e.getSlot() == 1) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 30m");
            }
            else if (e.getSlot() == 2) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 45m");
            }
            else if (e.getSlot() == 3) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 60m");
            }
            else if (e.getSlot() == 4) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 120m");
            }
            else if (e.getSlot() == 6) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 180m");
            }
            else if (e.getSlot() == 7) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 1d");
            }
            else if (e.getSlot() == 8) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + punished.getName() + " 3d");
            }
            else if (e.getSlot() == 9) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + punished.getName());
            }
        }
        else if (e.getView().getTitle().equals("§7Dakata §8- §7Worlds")) {
        	if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
        		p.openInventory(buildMain());
        	} else {
                if (Main.getThisPlugin().getConfig().getStringList("disable-in-worlds").contains(e.getCurrentItem().getItemMeta().getDisplayName())) {
                    final List<String> wanted = Main.getThisPlugin().getConfig().getStringList("disable-in-worlds");
                    wanted.remove(e.getCurrentItem().getItemMeta().getDisplayName());
                    Main.getThisPlugin().getConfig().set("disable-in-worlds", wanted);
                    Main.getThisPlugin().saveConfig();
                    e.getCurrentItem().setType(Material.BOOK);
                }
                else {
                    final List<String> wanted = Main.getThisPlugin().getConfig().getStringList("disable-in-worlds");
                    wanted.add(e.getCurrentItem().getItemMeta().getDisplayName());
                    Main.getThisPlugin().getConfig().set("disable-in-worlds", wanted);
                    Main.getThisPlugin().saveConfig();
                    e.getCurrentItem().setType(Material.ENCHANTED_BOOK);
                }
        	}
        	
            e.setCancelled(true);
        }
        else if (e.getView().getTitle().equals("§7Dakata §8- §7Report")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
            	p.openInventory(buildMain());
            } else {
                if (Bukkit.getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())) == null) {
                    return;
                }
                e.getWhoClicked().openInventory(buildReportList1(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", "")));
            }
        }
        else if (e.getView().getTitle().equals("§7Dakata §8- §7Report Commands")) {
            if (Bukkit.getPlayerExact(ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName())) == null) {
                return;
            }
            e.setCancelled(true);
            if (e.getSlot() == 10) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "dakata spy " + e.getInventory().getItem(13).getItemMeta().getDisplayName().replace("§a", ""));
                e.getWhoClicked().getOpenInventory().close();
            }
            else if (e.getSlot() == 16) {
                InventoryBuilder.reportiste.remove(e.getInventory().getItem(13).getItemMeta().getDisplayName().replace("§a", ""));
                InventoryBuilder.reportiste1.remove(e.getInventory().getItem(13).getItemMeta().getDisplayName().replace("§a", ""));
                e.getWhoClicked().getOpenInventory().close();
            } else if (e.getSlot() == 26) {
                e.getWhoClicked().getOpenInventory().close();
                e.getWhoClicked().openInventory(buildReportList());
            }
        }
    }
    
    public static void freezePlayer(final Player p) {
        if (isFreezed(p)) {
            InventoryBuilder.freezeliste.remove(p);
        }
        else {
            InventoryBuilder.freezeliste.put(p, p.getLocation());
        }
    }
    
    public static void unfreezePlayerOF(final OfflinePlayer p) {
            InventoryBuilder.freezeliste.remove(p);
    }
    
    public static boolean isFreezed(final Player p) {
        return InventoryBuilder.freezeliste.containsKey(p);
    }
    
    public static boolean isLagged(final Player p) {
        return InventoryBuilder.lagliste.contains(p);
    }
    
    public static Location getFreezedLocation(final Player p) {
        return InventoryBuilder.freezeliste.get(p);
    }
    
    @SuppressWarnings("deprecation")
	public void lagPlayer(final Player p) {
        if (isLagged(p)) {
            InventoryBuilder.lagliste.remove(p.getName());
        }
        else {
            InventoryBuilder.lagliste.add(p.getName());
            final World bwd = p.getWorld();
            for (int x = 0; x < 22; ++x) {
                if (InventoryBuilder.lagliste.contains(p.getName())) {
                    break;
                }
                for (int z = 0; z < 22 && !InventoryBuilder.lagliste.contains(p.getName()); ++z) {
                    for (int y = 4; y < 9 && !InventoryBuilder.lagliste.contains(p.getName()); ++y) {
                        if (bwd.getBlockTypeIdAt(p.getLocation().getBlockX() + 30 + x, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() + 30 + z) == 0) {
                            final Location loc = new Location(bwd, p.getLocation().getBlockX() + 16 + x, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() + 16 + z);
                            p.sendBlockChange(loc, Material.ENDER_PORTAL, (byte)0);
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        if (isFreezed(e.getPlayer())) {
            e.getPlayer().teleport(getFreezedLocation(e.getPlayer()));
        }
    }
}
