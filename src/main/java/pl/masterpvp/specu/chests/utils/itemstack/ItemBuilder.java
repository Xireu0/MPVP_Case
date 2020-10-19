package pl.masterpvp.specu.chests.utils.itemstack;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.masterpvp.specu.chests.utils.Utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private Material material;
    private int amount;
    private short durbality;
    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantmentMap;

    public ItemBuilder(Material material) {
        this.material = material;
        this.amount = 1;
        this.durbality = 0;
        this.name = null;
        this.lore = Lists.newArrayList();
        this.enchantmentMap = new LinkedHashMap<Enchantment, Integer>();
    }

    public ItemBuilder setType(Material material){
        this.material = material;
        return this;
    }

    public ItemBuilder setAmount(int amount){
        this.amount = amount;
        return this;
    }

    public ItemBuilder setDurbality(short durbality){
        this.durbality = durbality;
        return this;
    }

    public ItemBuilder setName(String name){
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        this.lore = lore;
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantmentMap){
        this.enchantmentMap = enchantmentMap;
        return this;
    }

    public ItemBuilder addLine(String line){
        this.lore.add(line);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer value){
        this.enchantmentMap.put(enchantment, value);
        return this;
    }

    public ItemStack build(){
        ItemStack itemStack = new ItemStack(getMaterial());{
            itemStack.setAmount(getAmount());
            itemStack.setDurability(getDurbality());
            ItemMeta meta = itemStack.getItemMeta();
            if(getName() != null){
                meta.setDisplayName(Utils.color(getName()));
            }
            if(getLore() != null && !getLore().isEmpty()){
                meta.setLore(Utils.color(getLore()));
            }
            itemStack.setItemMeta(meta);
            if(getEnchantmentMap() != null && !getEnchantmentMap().isEmpty()){
                itemStack.addUnsafeEnchantments(getEnchantmentMap());
            }
        }
        return itemStack;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public short getDurbality() {
        return durbality;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Map<Enchantment, Integer> getEnchantmentMap() {
        return enchantmentMap;
    }
}
