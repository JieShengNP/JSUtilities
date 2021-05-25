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
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.sendMessage("§a[JSUtils] §6You have been healed!");
                } else {
                    sender.sendMessage("§a[JSUtils] §4/heal <PlayerName>");
                }
                return true;
            } else if (args.length == 1){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    double maxHealth = selectedPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    selectedPlayer.setHealth(maxHealth);
                    if (sender.getName() == selectedPlayer.getName())
                    selectedPlayer.sendMessage("§a[JSUtils] §6You have been healed by " + sender.getName() + "!");
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
