package pl.croolyy.gameruleplaceholder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GamerulePlaceholderPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new GameruleExpansion(this).register();
            getLogger().info("Registered internal PlaceholderAPI expansion for gamerules.");
        } else {
            getLogger().warning("PlaceholderAPI not found. Placeholders will be unavailable.");
        }
    }
}
