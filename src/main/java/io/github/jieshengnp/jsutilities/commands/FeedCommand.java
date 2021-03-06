package io.github.jieshengnp.jsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("feed")){
            if (args.length == 0 && sender.hasPermission("jsutilities.feed")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setFoodLevel(20);
                    player.sendMessage("§a[JSUtils] §6You have been fed!");
                } else {
                    sender.sendMessage("§a[JSUtils] §4/feed <PlayerName>");
                }
                return true;
            } else if (args.length == 1 && sender.hasPermission("jsutilities.feed.others")){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    selectedPlayer.setFoodLevel(20);
                    if (sender.getName() != selectedPlayer.getName()){
                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been fed by " + sender.getName() + "!");
                        sender.sendMessage("§a[JSUtils] §6" + selectedPlayer.getName() + " has been fed!");
                    } else {
                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been fed!");
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
