package org.skyzzin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.skyzzin.util.ConfigYML;
import org.skyzzin.util.SlotData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateGui implements CommandExecutor {
    public static Map<Inventory, String> gui = new HashMap<>();
    public static Map<String,String> guiAction = new HashMap<>();

    public static void Add(InventoryType type, String guiName, List<SlotData> slotData) {
        if (type != null && guiName != null) {
            Material material;
            ItemStack itemStack;
            ItemMeta meta;
            Inventory inv = Bukkit.createInventory(null, type, guiName);

            for (SlotData slot : slotData) {
                material = Material.valueOf(slot.getType().toUpperCase());
                itemStack = new ItemStack(material);

                meta = itemStack.getItemMeta();
                meta.setDisplayName(slot.getName());

                itemStack.setItemMeta(meta);

                inv.setItem(Integer.parseInt(slot.getIndex()), itemStack);
                // Agora associamos a ação diretamente com o índice do slot
                guiAction.put(guiName + "_" + slot.getIndex(), slot.action);
            }

            gui.put(inv, guiName);
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /cgui <inventoryType> <guiName>");
                return false;
            }

            InventoryType inventoryType;
            try {
                inventoryType = InventoryType.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(ChatColor.RED + "Invalid inventory type.");
                return false;
            }

            String gui_name = args[1];


            Inventory current_inventory = Bukkit.createInventory(null, inventoryType, gui_name);

            gui.put(current_inventory, gui_name);

            player.sendMessage( ChatColor.RED + "[Console] " +
                    ChatColor.YELLOW + "create your GUIS in config.yml here not possible editable and here command debbuger then gui's created not is saved");
            player.sendMessage(ChatColor.GREEN + "GUI Created: " + gui_name);

        }
        return true;
    }
}
