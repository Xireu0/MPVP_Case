package pl.masterpvp.specu.chests.utils.itemstack;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.masterpvp.specu.chests.utils.Utils;

import java.util.List;
import java.util.Map;

public class ParseItemStack {

    public static String toString(ItemStack itemStack){
        if(itemStack == null){
            return "empty";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("type="+itemStack.getType()+" ");
        stringBuilder.append("amount="+itemStack.getAmount()+" ");
        stringBuilder.append("data="+itemStack.getDurability()+" ");
        if(itemStack.hasItemMeta()){
            if(itemStack.getItemMeta().hasDisplayName()){
                stringBuilder.append("name="+itemStack.getItemMeta().getDisplayName().replace(" ", "_")+" ");
            }
            if(itemStack.getItemMeta().hasLore()){
                String lore = "";
                for(String line : itemStack.getItemMeta().getLore()){
                    lore += line.replace(" ", "_") + "%nl%";
                }
                lore = (lore.substring(0, lore.length() - 4));
                stringBuilder.append("lore="+lore+" ");
            }
        }
        if(itemStack.getEnchantments() != null && !itemStack.getEnchantments().isEmpty()){
            for(Map.Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()){
                stringBuilder.append(EnchantmentUtil.enchantmentMap.get(entry.getKey()) + "="+entry.getValue() + " ");
            }
        }
        return stringBuilder.toString();
    }

    public static ItemStack toItemStack(String result){
        if(result.equals("empty")){
            return new ItemBuilder(Material.AIR).build();
        }
        if(!result.contains(" ")){
            return new ItemBuilder(Material.AIR).build();
        }
        String[] args = result.split(" ");
        ItemBuilder item = new ItemBuilder(Material.AIR);
        for(String arg : args){
            String[] trim = arg.split("=");
            String key = trim[0];
            String value = trim[1].toString();
            if(key.equals("type")){
                if (Utils.isInteger(value)) {
                    item.setType(Material.getMaterial(Integer.valueOf(value)));
                }else{
                    item.setType(Material.getMaterial(value));
                }
            }else if(key.equals("amount")) {
                item.setAmount(Integer.parseInt(value));
            }else if(key.equals("data")) {
                item.setDurbality(Short.parseShort(value));
            }else if(key.equals("name")) {
                item.setName(value.replace("_", " "));
            }else if(key.equals("lore")) {
                String[] lines = value.split("%nl%");
                for (String line : lines) {
                    item.addLine(line.replace("_", " "));
                }
            }else{
                Enchantment enchantment = EnchantmentUtil.stringMap.get(key);
                Integer integer = Integer.parseInt(value);
                if(enchantment != null){
                    item.addEnchantment(enchantment, integer);
                }
            }
        }
        return item.build();
    }

    public static ItemStack setNameLore(ItemStack itemStack, String name, String... lore){
        ItemStack is = new ItemStack(itemStack);{
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(Utils.color(name));
            List<String> l = Lists.newArrayList();
            for(String s : lore){
                l.add(Utils.color(s));
            }
            meta.setLore(l);
            is.setItemMeta(meta);
        }
        return is;
    }
}
