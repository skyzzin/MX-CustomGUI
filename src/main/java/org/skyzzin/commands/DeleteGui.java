package org.skyzzin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Iterator;
import java.util.Map;

public class DeleteGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!CreateGui.gui.isEmpty()) {
                player.sendMessage(ChatColor.GREEN + "GUIs Created:");

                Iterator<Map.Entry<Inventory, String>> iterator = CreateGui.gui.entrySet().iterator();
                while (iterator.hasNext())
                {
                    Map.Entry<Inventory, String> entry = iterator.next();

                    if(entry.getValue().equals(args[0]))
                    {
                        iterator.remove();
                        player.sendMessage(ChatColor.GREEN + "GUI removed: " + args[0]);

                    }
                }

            } else {
                player.sendMessage(ChatColor.RED + "Gui Not found.");
            }
        }
        return true;
    }
}
