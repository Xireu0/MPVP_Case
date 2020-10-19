package pl.masterpvp.specu.chests.utils.itemstack;

import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnchantmentUtil {

    public static Map<Enchantment, String> enchantmentMap = new LinkedHashMap<Enchantment, String>();
    public static Map<String, Enchantment> stringMap = new LinkedHashMap<String, Enchantment>();

    static{
        Arrays.asList(Enchantment.values()).stream().forEach(enchant -> enchantmentMap.put(enchant, enchant.getName()));
        Arrays.asList(Enchantment.values()).stream().forEach(enchant -> stringMap.put(enchant.getName(), enchant));
    }

}
