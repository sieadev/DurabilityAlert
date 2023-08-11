package com.siea.durabilityalert.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import com.siea.durabilityalert.util.*;
import org.bukkit.inventory.meta.Damageable;

public class DurabilityChangeEvent implements Listener {


    private final boolean requirePermission;
    private final String warningMessage;
    private final boolean subTitleWarning;
    private final boolean chatWarning;
    private final boolean titleWarning;
    private final int minumumDurability;
    private final int fadeOut;
    private final int fadeIn;
    private final int stay;

    public DurabilityChangeEvent(boolean requirePermission, boolean chatWarning, boolean titleWarning, boolean subTitleWarning, String warningMessage, int minimumDurability, int fadeIn, int stay, int fadeOut) {
        this.requirePermission = requirePermission;
        this.chatWarning = chatWarning;
        this.titleWarning = titleWarning;
        this.subTitleWarning = subTitleWarning;
        this.warningMessage = warningMessage;
        this.minumumDurability = minimumDurability;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @EventHandler
    public void DurabilityChanged(PlayerItemDamageEvent e){
        Player p = e.getPlayer();

        if (requirePermission){
            if (!p.hasPermission("durabilityAlert.alert")){
                return;
            }
        }

        if (p.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR){
            return;
        }

        Damageable damageable = (Damageable) e.getItem().getItemMeta();
        int maxDurability = e.getItem().getType().getMaxDurability();
        assert damageable != null;
        int durability = maxDurability - damageable.getDamage() - 1;
        if (!(durability < minumumDurability)){
            return;
        }

        String message = new ReplacePlaceholders().replacePlaceholders(p, warningMessage, e.getItem());

        if (chatWarning){
            p.sendMessage(message);
        }

        if (titleWarning && subTitleWarning){
            p.sendTitle(message, message, fadeIn,stay,fadeOut);
            return;
        }

        if (titleWarning){
            p.sendTitle(message, "", fadeIn,stay,fadeOut);
        }

        if (subTitleWarning){
            p.sendTitle("", message, fadeIn,stay,fadeOut);
        }

    }
}
