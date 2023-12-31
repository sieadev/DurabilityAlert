package com.siea.durabilityalert.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ReplacePlaceholders {
    private String itemName;

    public String replacePlaceholders(Player player, String message, ItemStack item) {
        String itemName = item.getItemMeta().getDisplayName();

        if (itemName == ""){
            itemName = item.getType().toString();
            itemName = itemName.replace("_"," ");
            this.itemName = itemName;
        }

        Damageable damageable = (Damageable) item.getItemMeta();
        int maxDurability = item.getType().getMaxDurability();
        assert damageable != null;
        int durability = maxDurability - damageable.getDamage() - 1;

        message = message.replace("%item%", itemName);
        message = message.replace("%durability%", "" + durability);
        message = message.replace("%player%", "" + player.getDisplayName());

        // Adding color to the message
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }
    public String replaceColour(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

}
