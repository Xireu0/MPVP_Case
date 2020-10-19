package pl.masterpvp.specu.chests.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;

public class ListenerManager {

    public static void registerListener(Listener listener, Plugin plugin){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(listener, plugin);
    }

    public static void registerListeners(Plugin plugin, Listener... listeners){
        Arrays.asList(listeners).forEach(listener -> {
            registerListener(listener, plugin);
        });
    }
}
