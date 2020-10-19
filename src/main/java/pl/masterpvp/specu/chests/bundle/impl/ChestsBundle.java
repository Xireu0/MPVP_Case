package pl.masterpvp.specu.chests.bundle.impl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import pl.masterpvp.specu.chests.CasePlugin;
import pl.masterpvp.specu.chests.bundle.Bundle;

import java.io.File;
import java.io.IOException;

public class ChestsBundle implements Bundle {

    private String name = "chests";
    private File file;
    private FileConfiguration configuration;

    @Override
    public void init() {
        createFile();
        reloadData();
    }

    @Override
    public void createFile() {
        Plugin plugin = CasePlugin.getInstance();
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), name + ".yml");
        if(!getFile().exists()){
            plugin.saveResource(name + ".yml", true);
        }
    }

    @Override
    public void saveData() {
        try {
            getConfiguration().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadData() {
        configuration = YamlConfiguration.loadConfiguration(getFile());
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String getName() {
        return name;
    }

}
