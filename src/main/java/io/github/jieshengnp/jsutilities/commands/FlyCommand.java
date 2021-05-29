package io.github.jieshengnp.jsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class FlyCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")){
            if (args.length == 0 && sender.hasPermission("jsutilities.fly")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    toggleFlight(player, null);
                } else {
                    sender.sendMessage("§a[JSUtils] §4/fly <PlayerName>");
                }
                return true;
            } else if (args.length == 1 && sender.hasPermission("jsutilities.fly.others")){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    toggleFlight(selectedPlayer, sender);
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
        return null;
    }

    private void toggleFlight(Player player, CommandSender sender){
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage("§a[JSUtils] §6Flight is now §4disabled§6!");
            if (sender != null) {
                if (sender.getName() != player.getName()) {
                    sender.sendMessage("§a[JSUtils] §6Flight set §4disabled §6for " + player.getName() + "!");
                }
            }
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage("§a[JSUtils] §6Flight is now §aenabled§6!");
            if (sender != null) {
                if (sender.getName() != player.getName()) {
                    sender.sendMessage("§a[JSUtils] §6Flight set §aenabled §6for " + player.getName() + "!");
                }
            }
        }
    }
}
