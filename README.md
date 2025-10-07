# GamerulePlaceholder
Lightweight Paper plugin that exposes Minecraft gamerule values through PlaceholderAPI placeholders. Supports querying any world by name and the player’s current world.

# Requirements
Paper 1.21.x (tested on 1.21.6).

PlaceholderAPI installed on the server.

Java 21 to run Paper 1.21.x.

# Installation
Place the plugin JAR into the server’s plugins/ folder.

Ensure PlaceholderAPI is installed and enabled.

Restart the server; the expansion registers automatically at startup.

# Placeholders per world scheme
```%gamerule_<rule>_<world>%```

Returns the value of gamerule <rule> in world <world>.

Example: ```%gamerule_keepInventory_world%``` → “true” or “false”.


# Placeholders scheme for current world
```%gamerule_currentworld_<rule>%```

Returns the gamerule value from the requesting player’s current world.

Requires player context (e.g., scoreboards, chat, GUIs that resolve placeholders for a player).

Example: ```%gamerule_currentworld_doDaylightCycle%```

# Notes

<rule> must match the exact gamerule name as in the server version (e.g., keepInventory, doDaylightCycle, playersSleepingPercentage).

If the world or gamerule doesn’t exist, the placeholder returns an empty string.

Boolean/Integer gamerules are returned as plain text (e.g., “true”, “100”).

# Usage Examples
Scoreboard: Show whether players keep inventory in the overworld: ```%gamerule_keepInventory_world%```

Player context: Show if day/night cycle runs in the player’s current dimension: ```%gamerule_currentworld_doDaylightCycle%```

# Performance
The plugin only reads values via the Paper API and performs no I/O, loops, or async tasks. Overhead is negligible even when placeholders update frequently (e.g., in scoreboards).

# Building From Source
Prerequisites: JDK 21 and Maven.

In the project root, run: mvn clean package.

The plugin JAR will be produced in target/ and can be copied to plugins/.

Paper API and PlaceholderAPI are declared with provided scope; they must exist as plugins on the server.

# Troubleshooting
Plugin loads but placeholders are empty: verify the world name and gamerule spelling; ensure a player context exists for gamerule_currentworld_.
Java version errors: use Java 21 for Paper 1.21.x.

# Credits
Author: CrooLyyCheck

Built for Paper + PlaceholderAPI.
