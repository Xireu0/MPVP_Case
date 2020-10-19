package pl.masterpvp.specu.chests.managers;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.masterpvp.specu.chests.CasePlugin;
import pl.masterpvp.specu.chests.data.Chest;
import pl.masterpvp.specu.chests.utils.RandomUtil;
import pl.masterpvp.specu.chests.utils.Utils;
import pl.masterpvp.specu.chests.utils.itemstack.ItemBuilder;
import pl.masterpvp.specu.chests.utils.itemstack.ParseItemStack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChestManager {

    private static Map<String, Chest> chests = new LinkedHashMap<>();
    private static List<UUID> open = Lists.newArrayList();

    public ChestManager(FileConfiguration fileConfiguration){
        for(String id : fileConfiguration.getConfigurationSection("chests").getKeys(false)){
            String name = fileConfiguration.getString("chests."+id+".name");
            ItemStack chest = ParseItemStack.toItemStack(fileConfiguration.getString("chests."+id+".chest"));
            ItemStack key = ParseItemStack.toItemStack(fileConfiguration.getString("chests."+id+".key"));
            Map<ItemStack, Double> drops = new LinkedHashMap<>();
            for(String c : fileConfiguration.getConfigurationSection("chests."+id+".drops").getKeys(false)){
                double chance = Double.valueOf(c);
                ItemStack itemStack = ParseItemStack.toItemStack(fileConfiguration.getString("chests."+id+".drops."+c+""));
                drops.put(itemStack, chance);
            }
            getChests().put(id, new Chest(id,name,chest,key,drops));
        }
    }

    public static ItemStack randomItem(Chest chest){
        for(Map.Entry<ItemStack, Double> entry : chest.getDrops().entrySet()){
            if(RandomUtil.getChance(entry.getValue())){
                return entry.getKey();
            }
        }
        return randomItem(chest);
    }

    public static List<ItemStack> randomItems(Chest chest){
        List<ItemStack> items = Lists.newArrayList();
        for(int i = 0; i<15; i++){
            items.add(randomItem(chest));
        }
        return items;
    }

    public static void openCase(Player player, Chest chest){
        if(getOpen().contains(player.getUniqueId())){
            Utils.sendMsg(player, "&cBlad! Otwierasz juz skrzynke!");
            return;
        }
        if(!player.getInventory().containsAtLeast(chest.getKey(), 1)){
            Utils.sendMsg(player, "&cBlad! Nie posiadasz klucza!");
            return;
        }
        player.getInventory().removeItem(new ItemStack(chest.getKey()));
        player.getInventory().removeItem(new ItemStack(chest.getChest()));
        getOpen().add(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(null, 27, Utils.color("&8Otwieranie skrzynki"));
        ItemStack blank = new ItemBuilder(Material.STAINED_GLASS_PANE).setAmount(1).setDurbality((short)15).setName("&8").build();
        for(int i = 0; i<inventory.getSize(); i++){
            if(i<=8 || i>= 18){
                inventory.setItem(i, blank);
            }
        }
        inventory.setItem(4, new ItemBuilder(Material.TORCH).setName("&cWygrana").build());
        inventory.setItem(22, new ItemBuilder(Material.TORCH).setName("&cWygrana").build());
        List<ItemStack> drops = randomItems(chest);
        int time = 0;
        player.openInventory(inventory);
        for(int i = 0; i<15; i++){
            int finalI = i;
            int finalI1 = i;
            new BukkitRunnable() {
              @Override
              public void run() {
                  if(player == null){
                      Utils.give(null, drops.get(10), player.getLocation());
                      return;
                  }
                  if(player.getOpenInventory() == null) {
                      player.openInventory(inventory);
                  }else{
                      Inventory inv = player.getOpenInventory().getTopInventory();
                      if(inv.getTitle() == null || !inv.getTitle().equalsIgnoreCase(Utils.color("&8Otwieranie skrzynki"))){
                          player.openInventory(inventory);
                      }
                  }
                  if(inventory.getItem(9) == null) {
                      inventory.setItem(9, drops.get(0));
                  }else if(inventory.getItem(10) == null){
                      inventory.setItem(9, drops.get(1));
                      inventory.setItem(10, drops.get(0));
                  }else if(inventory.getItem(11) == null){
                      inventory.setItem(9, drops.get(2));
                      inventory.setItem(10, drops.get(1));
                      inventory.setItem(11, drops.get(0));
                  }else if(inventory.getItem(12) == null){
                      inventory.setItem(9, drops.get(3));
                      inventory.setItem(10, drops.get(2));
                      inventory.setItem(11, drops.get(1));
                      inventory.setItem(12, drops.get(0));
                  }else if(inventory.getItem(13) == null){
                      inventory.setItem(9, drops.get(4));
                      inventory.setItem(10, drops.get(3));
                      inventory.setItem(11, drops.get(2));
                      inventory.setItem(12, drops.get(1));
                      inventory.setItem(13, drops.get(0));
                  }else if(inventory.getItem(14) == null){
                      inventory.setItem(9, drops.get(5));
                      inventory.setItem(10, drops.get(4));
                      inventory.setItem(11, drops.get(3));
                      inventory.setItem(12, drops.get(2));
                      inventory.setItem(13, drops.get(1));
                      inventory.setItem(14, drops.get(0));
                  }else if(inventory.getItem(15) == null){
                      inventory.setItem(9, drops.get(6));
                      inventory.setItem(10, drops.get(5));
                      inventory.setItem(11, drops.get(4));
                      inventory.setItem(12, drops.get(3));
                      inventory.setItem(13, drops.get(2));
                      inventory.setItem(14, drops.get(1));
                      inventory.setItem(15, drops.get(0));
                  }else if(inventory.getItem(16) == null){
                      inventory.setItem(9, drops.get(7));
                      inventory.setItem(10, drops.get(6));
                      inventory.setItem(11, drops.get(5));
                      inventory.setItem(12, drops.get(4));
                      inventory.setItem(13, drops.get(3));
                      inventory.setItem(14, drops.get(2));
                      inventory.setItem(15, drops.get(1));
                      inventory.setItem(16, drops.get(0));
                  }else if(inventory.getItem(17) == null) {
                      inventory.setItem(9, drops.get(8));
                      inventory.setItem(10, drops.get(7));
                      inventory.setItem(11, drops.get(6));
                      inventory.setItem(12, drops.get(5));
                      inventory.setItem(13, drops.get(4));
                      inventory.setItem(14, drops.get(3));
                      inventory.setItem(15, drops.get(2));
                      inventory.setItem(16, drops.get(1));
                      inventory.setItem(17, drops.get(0));
                  }else {
                      int a = finalI1;
                      inventory.setItem(17, inventory.getItem(16));
                      inventory.setItem(16, inventory.getItem(15));
                      inventory.setItem(15, inventory.getItem(14));
                      inventory.setItem(14, inventory.getItem(13));
                      inventory.setItem(13, inventory.getItem(12));
                      inventory.setItem(12, inventory.getItem(11));
                      inventory.setItem(11, inventory.getItem(10));
                      inventory.setItem(10, inventory.getItem(9));
                      inventory.setItem(9, drops.get(finalI));
                      if(a == 14){
                          Utils.give(player, drops.get(10), player.getLocation());
                          new BukkitRunnable() {
                              @Override
                              public void run() {
                                  if(player != null && player.getOpenInventory() != null){
                                      if(player.getOpenInventory().getTopInventory().getTitle().equalsIgnoreCase(Utils.color("&8Otwieranie skrzynki"))){
                                          player.closeInventory();
                                          getOpen().remove(player.getUniqueId());
                                      }
                                  }
                              }
                          }.runTaskLater(CasePlugin.getInstance(), 10l);
                      }
                  }
              }
          }.runTaskLater(CasePlugin.getInstance(), time);
          time += 10;
        }
    }

    public static Chest getChest(ItemStack itemStack){
        ItemStack is = new ItemStack(itemStack);
        for(Chest chest : getChests().values()){
            is.setAmount(chest.getChest().getAmount());
            if(is.equals(chest.getChest())){
                return chest;
            }
        }
        return null;
    }

    public static Chest getKey(ItemStack itemStack){
        ItemStack is = new ItemStack(itemStack);
        for(Chest chest : getChests().values()){
            is.setAmount(chest.getKey().getAmount());
            if(is.equals(chest.getKey())){
                return chest;
            }
        }
        return null;
    }

    public static Chest getChest(String s){
        return getChests().get(s);
    }

    public static Map<String, Chest> getChests() {
        return chests;
    }

    public static List<UUID> getOpen() {
        return open;
    }
}
