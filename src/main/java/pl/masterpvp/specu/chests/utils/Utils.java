package pl.masterpvp.specu.chests.utils;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Utils {

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> color(List<String> list){
        if(list == null || list.isEmpty()) return Lists.newArrayList();
        for(int i = 0; i<list.size(); i++){
            list.set(i, color(list.get(i)));
        }
        return list;
    }

    public static boolean sendMsg(Player player, String text){
        return sendMsg((CommandSender) player, text);
    }

    public static boolean sendMsg(CommandSender commandSender, String text){
        commandSender.sendMessage(color(text));
        return true;
    }

    public static boolean isInteger(String text){
        try {
            Integer.parseInt(text);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean give(Player player, ItemStack itemStack, Location location){
        if(player == null){
            location.getWorld().dropItem(location, itemStack);
            return false;
        }
        if(itemStack != null){
            for(ItemStack is : player.getInventory().addItem(new ItemStack[]{itemStack}).values())
                location.getWorld().dropItem(location, itemStack);
            player.updateInventory();
        }
        return true;
    }
}
