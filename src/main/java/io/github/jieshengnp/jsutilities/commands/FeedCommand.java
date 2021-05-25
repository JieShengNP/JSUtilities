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
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setFoodLevel(20);
                    player.sendMessage("§a[JSUtils] §6You have been fed!");
                } else {
                    sender.sendMessage("§a[JSUtils] §4/feed <PlayerName>");
                }
                return true;
            } else if (args.length == 1){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    selectedPlayer.setFoodLevel(20);
                    if (sender.getName() != selectedPlayer.getName()){
                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been fed by " + sender.getName() + "!");
                    } else {
                        selectedPlayer.sendMessage("§a[JSUtils] §6You have been fed!");
                    }
                    return true;
                } else {
                    sender.sendMessage("§a[JSUtils] §4Invalid player name '" + args[0] + "'!");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            List<String> playerListNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getOnlinePlayers().size()];
            Bukkit.getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++){
                if(players[i].getName().startsWith(args[0])) {
                    playerListNames.add(players[i].getName());
                }
            }
            return playerListNames;
        }
        else{
            return new ArrayList<>();
        }
    }
}
