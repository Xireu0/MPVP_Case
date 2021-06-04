package pl.masterpvp.specu.chests;

import org.bukkit.plugin.java.JavaPlugin;
import pl.masterpvp.specu.chests.bundle.impl.ChestsBundle;
import pl.masterpvp.specu.chests.commands.CommandManager;
import pl.masterpvp.specu.chests.commands.impl.CaseCommand;
import pl.masterpvp.specu.chests.listeners.ListenerManager;
import pl.masterpvp.specu.chests.listeners.impl.InventoryListener;
import pl.masterpvp.specu.chests.listeners.impl.PlaceBlockListener;
import pl.masterpvp.specu.chests.managers.BundleManager;
import pl.masterpvp.specu.chests.managers.ChestManager;

public class CasePlugin extends JavaPlugin {

    private static CasePlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        BundleManager.registerFiles(new ChestsBundle());
        new ChestManager(BundleManager.getFile("chests").getConfiguration());
        CommandManager.registerCommands(new CaseCommand());
        ListenerManager.registerListeners(this, new PlaceBlockListener(), new InventoryListener());
    }

    public static CasePlugin getInstance() {
        return instance;
    }
}
