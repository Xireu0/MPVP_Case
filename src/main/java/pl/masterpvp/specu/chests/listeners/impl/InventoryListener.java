package pl.masterpvp.specu.chests.listeners.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import pl.masterpvp.specu.chests.utils.Utils;

public class InventoryListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onClick(InventoryClickEvent event){
        if(event.isCancelled()) return;
        if(event.getInventory().getTitle().equalsIgnoreCase(Utils.color("&8Otwieranie skrzynki"))){
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(event.isCancelled()) return;
        Player player = event.getPlayer();
        if(player.getOpenInventory() == null) return;
        if(player.getOpenInventory().getTopInventory().getTitle() == null) return;
        if(player.getOpenInventory().getTopInventory().getTitle().equalsIgnoreCase(Utils.color("&8Otwieranie skrzynki"))){
            event.setCancelled(true);
            return;
        }
    }
}
