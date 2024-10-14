package org.skyzzin;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.commands.CreateGui;
import org.skyzzin.commands.RegisterCommands;
import org.skyzzin.util.ConfigYML;
import org.skyzzin.util.SlotData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MxCustomGui extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new RegisterCommands(this);

        List<Map<String, Object>> guis = ConfigYML.loadGuis(this);

        for (Map<String, Object> gui : guis) {
            try {
                String type = gui.get("type").toString().toUpperCase();
                String title = gui.get("title").toString();

                InventoryType inventoryType;
                List<SlotData> list_slot_data = new ArrayList<>();

                if(gui.containsKey("slots"))
                {
                    List<Map<String,Object>> slots = (List<Map<String, Object>>) gui.get("slots");

                    for(Map<String,Object> slot : slots)
                    {
                        SlotData slotData = new SlotData();

                        String slotType = slot.get("type").toString();
                        String index = slot.get("index").toString();
                        String action = slot.get("action").toString();
                        String name = slot.get("name").toString();

                        slotData.setAction(action);
                        slotData.setName(name);
                        slotData.setType(slotType);
                        slotData.setIndex(index);


                        list_slot_data.add(slotData);
                    }

                    inventoryType = InventoryType.valueOf(type);

                    new CreateGui().Add(inventoryType, title,list_slot_data);
                }

                getLogger().info("======================================");
                getLogger().info("GUI criada: " + title + " com tipo " + type);

            } catch (IllegalArgumentException e) {
                getLogger().warning("Tipo de inventário inválido: " + gui.get("type"));
            } catch (Exception e) {
                getLogger().severe("Erro ao criar GUI: " + e.getMessage());
            }
        }
    }

    @Override
    public void onDisable() {
        // Código de desativação
    }
}
