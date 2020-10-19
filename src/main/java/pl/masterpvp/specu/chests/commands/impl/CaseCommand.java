package pl.masterpvp.specu.chests.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.masterpvp.specu.chests.data.Chest;
import pl.masterpvp.specu.chests.managers.ChestManager;
import pl.masterpvp.specu.chests.utils.Utils;

import java.util.Collections;

public class CaseCommand extends BukkitCommand {
    public CaseCommand() {
        super("case", "Give key or chest to player!", "/case [chest/key] [id] [amount] [nick/all]", Collections.emptyList());
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(args.length < 4){
            return Utils.sendMsg(commandSender, "&cBlad! Minimalna ilosc argumentow to: &94");
        }
        boolean c = true;
        if(args[0].equalsIgnoreCase("key")){
            c = false;
        }
        final Chest chest = ChestManager.getChests().get(args[1].toLowerCase());
        if(chest == null){
            return Utils.sendMsg(commandSender, "&cBlad! Skrzynka o tym ID nie istnieje!");
        }
        if (!Utils.isInteger(args[2])) {
            return Utils.sendMsg(commandSender, "&cBlad! Podana ilosc jest nieprawidłowa");
        }
        final  int amount = Integer.valueOf(args[2]);
        ItemStack is = new ItemStack(chest.getChest());
        if(!c) is = new ItemStack(chest.getKey());
        is.setAmount(amount);
        if(args[3].equalsIgnoreCase("all")){
            if(Bukkit.getOnlinePlayers() == null || Bukkit.getOnlinePlayers().isEmpty() || Bukkit.getOnlinePlayers().size() == 0){
                return Utils.sendMsg(commandSender, "&cBlad! Na serwerze nie ma zadnego gracza!");
            }
            Bukkit.broadcastMessage(Utils.color("&4✪ &cCASE &8» &6Kazdy gracz otrzymal"+(!c ? ": klucz do:" : ":")+" &7"+chest.getName()+" &6w ilosci: &7x"+amount+""));
            for(final Player player : Bukkit.getOnlinePlayers()){
                Utils.give(player, is, player.getLocation());
            }
            return true;
        }
        Player player = Bukkit.getPlayer(args[3]);
        if(player == null){
            return Utils.sendMsg(commandSender, "&cBlad! Ten gracz jest offline!");
        }
        Utils.sendMsg(player, "&4✪ &cCASE &8» &6Otrzymales"+(!c ? ": klucz do:" : ":")+" &7" + chest.getName() + " &6w ilosci: &7x" + amount + "");
        Utils.give(player, is, player.getLocation());
        Utils.sendMsg(commandSender, "&cSkrzynki zostaly dostarczone graczowi!");
        return false;
    }
}
