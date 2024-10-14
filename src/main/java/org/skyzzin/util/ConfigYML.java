package org.skyzzin.util;

import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.List;

public class ConfigYML {

    public static List<Map<String, Object>> loadGuis(JavaPlugin plugin) {
        Yaml yaml = new Yaml();

        // Obtenha o arquivo config.yml da pasta de dados do plugin
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        // Verifica se o arquivo existe
        if (!configFile.exists()) {
            plugin.getLogger().warning("config.yml não encontrado na pasta de dados do plugin!");
            return null;
        }

        try (InputStream inputStream = new FileInputStream(configFile)) {
            // Carrega o arquivo YAML
            Map<String, Object> data = yaml.load(inputStream);

            // Verifica se a seção 'guis' existe
            if (data != null && data.containsKey("guis")) {
                // Retorna a lista de GUIs do arquivo de configuração
                return (List<Map<String, Object>>) data.get("guis");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Caso a seção 'guis' não exista ou ocorra um erro, retorna null
        return null;
    }
}
