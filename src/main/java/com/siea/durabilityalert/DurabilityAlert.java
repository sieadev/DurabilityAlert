package com.siea.durabilityalert;

import com.siea.durabilityalert.listeners.DurabilityChangeEvent;
import com.siea.durabilityalert.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class DurabilityAlert extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        boolean requirePermission = getConfig().getBoolean("requirePermission");
        boolean chatWarning = getConfig().getBoolean("chatWarning");
        boolean titleWarning = getConfig().getBoolean("titleWarning");
        boolean subTitleWarning = getConfig().getBoolean("subTitleWarning");
        String warningMessage = getConfig().getString("warningMessage");
        int minimumDurability = getConfig().getInt("minimumDurability");
        int fadeIn = getConfig().getInt("fadeIn");
        int stay = getConfig().getInt("stay");
        int fadeOut = getConfig().getInt("fadeOut");
        getServer().getPluginManager().registerEvents(new DurabilityChangeEvent(requirePermission, chatWarning, titleWarning, subTitleWarning, warningMessage, minimumDurability, fadeIn, stay, fadeOut), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
