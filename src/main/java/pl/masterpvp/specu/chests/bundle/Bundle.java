package pl.masterpvp.specu.chests.bundle;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public interface Bundle {

    void init();
    void createFile();
    void saveData();
    void reloadData();

    String getName();
    File getFile();
    FileConfiguration getConfiguration();
}
