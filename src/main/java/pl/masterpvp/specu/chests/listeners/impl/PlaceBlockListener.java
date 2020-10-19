package pl.masterpvp.specu.chests.listeners.impl;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pl.masterpvp.specu.chests.managers.ChestManager;
import pl.masterpvp.specu.chests.utils.Utils;

public class PlaceBlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(event.isCancelled()){
            Utils.sendMsg(event.getPlayer(), "&cBlad! Nie mozesz otwierac skrzynki w tym miejscu!");
            return;
        }
        ItemStack itemStack = event.getPlayer().getItemInHand();
        if(itemStack == null) return;
        if(itemStack.getType() == null) return;
        if(itemStack.getType().equals(Material.AIR)) return;
        if(ChestManager.getKey(itemStack) != null){
            event.setCancelled(true);
            return;
        }
        if(ChestManager.getChest(itemStack) != null){
            event.setCancelled(true);
            ChestManager.openCase(event.getPlayer(), ChestManager.getChest("magic"));
            return;
        }

    }
}
