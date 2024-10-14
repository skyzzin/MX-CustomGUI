package org.skyzzin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.skyzzin.util.SlotData;

import java.util.Map;

public class InventoryListener implements Listener {

    public InventoryListener()
    {

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getInventory();

        if (CreateGui.gui.containsKey(clickedInventory)) {
            String guiName = CreateGui.gui.get(clickedInventory);
            int slotIndex = event.getRawSlot();

            event.setCancelled(true);

            String actionKey = guiName + "_" + slotIndex;
            if (CreateGui.guiAction.containsKey(actionKey)) {
                String action = CreateGui.guiAction.get(actionKey);

                //event.getWhoClicked().sendMessage(ChatColor.GREEN + "Ação: " + action);

                String command = action.replace("@p",event.getWhoClicked().getName());

                if(!action.contains("@p"))
                {
                    event.getWhoClicked().sendMessage(ChatColor.RED + "[Console] Command Executed: " + ChatColor.YELLOW +  command);
                    event.getWhoClicked().sendMessage("command did not define player example: kill @p");
                }

                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),command);
            }
        }
    }



    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event)
    {
        Inventory inventory= event.getInventory();

        if(CreateGui.gui.containsKey(inventory))
        {
            event.setCancelled(true);
        }
    }
}
