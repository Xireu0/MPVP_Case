package pl.masterpvp.specu.chests.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandManager {

    private static CommandMap commandMap = null;

    public static CommandMap getCommandMap() {
        if (commandMap == null) {
            try {
                Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(Bukkit.getServer());
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
        return commandMap;
    }

    public static void registerCommand(BukkitCommand bukkitCommand){
        getCommandMap().register("masterpvp", bukkitCommand);
    }

    public static void registerCommands(BukkitCommand... bukkitCommands){
        Arrays.asList(bukkitCommands).forEach(bukkitCommand -> {
            registerCommand(bukkitCommand);
        });
    }
}
