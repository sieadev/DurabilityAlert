package com.siea.durabilityalert;

import com.siea.durabilityalert.listeners.DurabilityChangeEvent;
import com.siea.durabilityalert.util.ReplacePlaceholders;
import org.bukkit.plugin.java.JavaPlugin;

public final class DurabilityAlert extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String prefix = new ReplacePlaceholders().replaceColour(getConfig().getString("prefix"));
        boolean requirePermission = getConfig().getBoolean("requirePermission");
        boolean chatWarning = getConfig().getBoolean("chatWarning");
        boolean titleWarning = getConfig().getBoolean("titleWarning");
        boolean subTitleWarning = getConfig().getBoolean("subTitleWarning");
        String warningMessage = getConfig().getString("warningMessage");
        int minimumDurability = getConfig().getInt("minimumDurability");
        int fadeIn = getConfig().getInt("fadeIn");
        int stay = getConfig().getInt("stay");
        int fadeOut = getConfig().getInt("fadeOut");
        int soundUnderDurability = getConfig().getInt("soundUnderDurability");
        getServer().getConsoleSender().sendMessage(prefix +"Config Successfully Loaded");
        getServer().getPluginManager().registerEvents(new DurabilityChangeEvent(requirePermission, chatWarning, titleWarning, subTitleWarning, warningMessage, minimumDurability, fadeIn, stay, fadeOut, soundUnderDurability), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
