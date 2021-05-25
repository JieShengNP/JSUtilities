package io.github.jieshengnp.jsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HealCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("heal")){
            if (args.length == 0 && sender.hasPermission("jsutilities.heal")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.sendMessage("§a[JSUtils] §6You have been healed!");
                } else {
                    sender.sendMessage("§a[JSUtils] §4/heal <PlayerName>");
                }
                return true;
            } else if (args.length == 1 && sender.hasPermission("jsutilities.heal.others")){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    double maxHealth = selectedPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    selectedPlayer.setHealth(maxHealth);
                    if (sender.getName() != selectedPlayer.getName()) {
                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been healed by " + sender.getName() + "!");
                        sender.sendMessage("§a[JSUtils] §6" + selectedPlayer.getName() + " has been fed!");
                    } else {

                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been healed!");
                    }
                    return true;
                } else {
                    sender.sendMessage("§a[JSUtils] §4Invalid player name '" + args[0] + "'!");
                }
            } else {
                sender.sendMessage("§a[JSUtils] §4You do not have permission!");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            return null;
        }
        else{
            return new ArrayList<>();
        }
    }
}
