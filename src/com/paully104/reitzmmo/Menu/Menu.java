package com.paully104.reitzmmo.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.paully104.reitzmmo.ConfigFiles.API.plugin;

/**
 * Created by Paul on 4/26/2017.
 */
public class Menu implements Listener {

    //The Idea is to make these obsolute with a nifty menu :)
    //sender.sendMessage(ChatColor.GOLD + "~ReitzRPGMMO main  menu listing commands~");
    //sender.sendMessage(ChatColor.GOLD + "1. /Reitz Stats");
    //sender.sendMessage(ChatColor.GOLD + "2. /Reitz Fix");
    //sender.sendMessage(ChatColor.GOLD + "3. /Rparty");

    public static final Inventory GUI_MENU = Bukkit.createInventory(null, 9, "Reitz Menu");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.

    static {
        //Icons

        createDisplay(Material.BED, GUI_MENU, 0, "Home","Teleport to your home point!");
        createDisplay(Material.WOOD_AXE, GUI_MENU, 2, "Stats","Get your combat stats!");
        createDisplay(Material.WORKBENCH, GUI_MENU, 3, "Fix Health","If your health is bugged fix it");
        createDisplay(Material.BANNER, GUI_MENU, 4, "Party","Get the party commands!");
        createDisplay(Material.SPECTRAL_ARROW, GUI_MENU, 5, "Weaponskills","Apply weaponskills");
        createDisplay(Material.ARMOR_STAND, GUI_MENU, 6, "Fix EXP","Fix floating EXP");
        createDisplay(Material.MAP, GUI_MENU, 7, "Town Menu","Teleport to a town");
        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event)
    {
        System.out.println(event.getDestination().getName());
        if (event.getDestination().getName().equals(GUI_MENU.getName())) {

            System.out.println("InventoryMoveEvent on GUI_MENU");
            System.out.println(event.getDestination().getName());
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.getName().equals(GUI_MENU.getName())) {
            if(clicked.hasItemMeta()) {
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Home")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    double health = player.getHealth();
                    player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                    player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            if(player.getHealth() >= health)
                            {
                                player.teleport(player.getBedSpawnLocation()); // Adds dirt

                            }
                            else
                            {
                                player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                            }
                            //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                        }
                    }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE


                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Stats")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.performCommand("Reitz Stats"); // Adds dirt

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Fix Health")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.performCommand("Reitz FixHealth"); // Adds dirt

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Party")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.performCommand("RParty"); // Adds dirt

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Weaponskills")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.openInventory(Weaponskill_Menu.WEAPONSKILL_MENU); // Adds dirt

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Fix EXP")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.performCommand("Reitz FixEXP"); // Adds dirt

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Town Menu")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.openInventory(Town_Menu.TOWN_MENU); // Adds dirt

                }
                else
                {
                    event.setCancelled(true);

                }
            }


        }

    }

    private static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        inv.setItem(Slot, item);

    }

}
