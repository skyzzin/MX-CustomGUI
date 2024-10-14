package org.skyzzin.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class RegisterCommands {
    public RegisterCommands(JavaPlugin plugin)
    {
        plugin.getCommand("cgui").setExecutor(new CreateGui());
        plugin.getCommand("opengui").setExecutor(new OpenGui());
        plugin.getCommand("lgui").setExecutor(new ListGui());
        plugin.getCommand("dgui").setExecutor(new DeleteGui());

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(),plugin);

    }
}
