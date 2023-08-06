package com.siea.durabilityalert.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ReplacePlaceholders {
    private String itemName;

    public String replacePlaceholders(Player player, String message) {
        ItemStack item = player.getInventory().getItemInMainHand();
        String itemName = item.getItemMeta().getDisplayName();

        if (itemName == ""){
            itemName = item.getType().toString();
            itemName = itemName.replace("_"," ");
            this.itemName = itemName;
        }

        Damageable damageable = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        int maxDurability = player.getInventory().getItemInMainHand().getType().getMaxDurability();
        assert damageable != null;
        int durability = maxDurability - damageable.getDamage() - 1;

        message = message.replace("%item%", itemName);
        message = message.replace("%durability%", "" + durability);

        // Adding color to the message
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }
}
