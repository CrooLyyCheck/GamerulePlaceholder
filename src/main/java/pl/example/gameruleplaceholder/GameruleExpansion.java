package pl.croolyy.gameruleplaceholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameruleExpansion extends PlaceholderExpansion {

    private final GamerulePlaceholderPlugin plugin;

    public GameruleExpansion(GamerulePlaceholderPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "gamerule";
    }

    @Override
    public @NotNull String getAuthor() {
        return "croolyy";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        // Now supports:
        // - <rule>_<world>           => previous behavior
        // - currentworld_<rule>      => new behavior, takes player's current world
        if (params.startsWith("currentworld_")) {
            String ruleName = params.substring("currentworld_".length());
            if (ruleName.isEmpty()) {
                return "";
            }
            // Requires player context
            if (offlinePlayer == null || !offlinePlayer.isOnline()) {
                return "";
            }
            Player player = offlinePlayer.getPlayer();
            if (player == null) {
                return "";
            }
            World world = player.getWorld();
            return getRuleValueAsString(world, ruleName);
        }

        // Fallback: <rule>_<world>
        int idx = params.lastIndexOf('_');
        if (idx <= 0 || idx >= params.length() - 1) {
            return "";
        }

        String ruleName = params.substring(0, idx);
        String worldName = params.substring(idx + 1);

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return "";
        }

        return getRuleValueAsString(world, ruleName);
    }

    private String getRuleValueAsString(World world, String ruleName) {
        GameRule<?> rule = GameRule.getByName(ruleName);
        if (rule == null) {
            return "";
        }
        Object valueObj = world.getGameRuleValue((GameRule) rule);
        if (valueObj == null) {
            return "";
        }
        return String.valueOf(valueObj);
    }
}
