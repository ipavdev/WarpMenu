package org.ipav.warp;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Arrays;
import java.util.List;

public class Menu implements Listener {
    private final Inventory inventory;
    private final String inventoryName = ChatColor.RED.toString() + ChatColor.BOLD + "             Warps";
    public Menu() {
        this.inventory = Bukkit.createInventory(null, 27, inventoryName);
        for (int i = 0; i < 27; i++)
            this.inventory.setItem(i, CreateItem(Material.GRAY_STAINED_GLASS_PANE, 1, " ", Arrays.asList()));
        this.inventory.setItem(13, CreateItem(Material.ENDER_PEARL, 1, ChatColor.BLUE + "Spawn", Arrays.asList("", ChatColor.DARK_GRAY + "Click to teleport to Spawn!")));
    }
    private ItemStack CreateItem(Material material, int Amount, String Name, List<String> Lore){
        ItemStack item = new ItemStack(material, Amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(Name);
            if (Lore != null) {
                meta.setLore(Lore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }
    public void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equalsIgnoreCase(inventoryName)))  {
            return;
        }
        event.setCancelled(true);
        switch (event.getSlot()){
            case 13:
                event.getWhoClicked().teleport(new Location(Bukkit.getWorld("events"), 0, 100, 0, 0, 0)); //Hard Coded locations on purpose
                break;
            //Add more
            default:
                return;
        }
        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
    }
}
