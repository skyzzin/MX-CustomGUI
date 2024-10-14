package org.skyzzin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class ListGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Verifica se há GUIs criadas
            if (!CreateGui.gui.isEmpty()) {
                player.sendMessage(ChatColor.GREEN + "GUIs Created:");

                // Percorre todas as GUIs criadas e as lista
                for (Map.Entry<Inventory, String> entry : CreateGui.gui.entrySet()) {
                    player.sendMessage(ChatColor.YELLOW + "- " + entry.getValue());
                }
            } else {
                // Caso o mapa esteja vazio, informa o jogador
                player.sendMessage(ChatColor.RED + "Not Found GUIs");
            }
        }
        return true;
    }
}
