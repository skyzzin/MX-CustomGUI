package org.skyzzin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;

import java.util.Map;

public class OpenGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;



            String gui_name = args[0];


            sender.sendMessage(gui_name);

            for(Map.Entry<Inventory,String> entry : CreateGui.gui.entrySet())
            {
                if(entry.getValue().equals(gui_name))
                {
                    player.openInventory(entry.getKey());
                }
            }
            player.sendMessage(ChatColor.RED + "GUI Not found");
        }
        return true;
    }
}
