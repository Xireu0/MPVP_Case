package pl.masterpvp.specu.chests.data;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Chest {

    private final String id;
    private final String name;
    private final ItemStack chest;
    private final ItemStack key;
    private final Map<ItemStack, Double> drops;

    public Chest(String id, String name, ItemStack chest, ItemStack key, Map<ItemStack, Double> drops) {
        this.id = id;
        this.name = name;
        this.chest = chest;
        this.key = key;
        this.drops = drops;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemStack getChest() {
        return chest;
    }

    public ItemStack getKey() {
        return key;
    }

    public Map<ItemStack, Double> getDrops() {
        return drops;
    }
}
