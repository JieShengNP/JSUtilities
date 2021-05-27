package io.github.jieshengnp.jsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class GetPosCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("getpos")){
            if (args.length == 0 && sender.hasPermission("jsutilities.getpos")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Location location = player.getLocation();
                    player.sendMessage("§a[JSUtils] §6Current position: X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ());
                } else {
                    sender.sendMessage("§a[JSUtils] §4/getpos <PlayerName>");
                }
                return true;
            } else if (args.length == 1 && sender.hasPermission("jsutilities.getpos.others")){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    Location location = selectedPlayer.getLocation();
                    String dimensionName = location.getWorld().getName();
                    switch(dimensionName) {
                        case "world":
                            dimensionName = "The Overworld(" + dimensionName + ")";
                            break;
                        case "world_nether":
                            dimensionName = "The Nether(" + dimensionName + ")";
                            break;
                        case "world_the_end":
                            dimensionName = "The End(" + dimensionName + ")";
                            break;
                    }
                    if (sender.getName() != selectedPlayer.getName()){
                        sender.sendMessage("§a[JSUtils] §6Current position of " + selectedPlayer.getName() + ":");
                        sender.sendMessage(ChatColor.RED + "Dimension: " + ChatColor.GREEN + dimensionName);
                        sender.sendMessage(ChatColor.RED + "X: " + ChatColor.GREEN + location.getBlockX());
                        sender.sendMessage(ChatColor.RED + "Y: " + ChatColor.GREEN + location.getBlockY());
                        sender.sendMessage(ChatColor.RED + "Z: " + ChatColor.GREEN + location.getBlockZ());
                    } else {
                        sender.sendMessage("§a[JSUtils] §6Current position:");
                        sender.sendMessage(ChatColor.RED + "X: " + ChatColor.GREEN + location.getBlockX());
                        sender.sendMessage(ChatColor.RED + "Y: " + ChatColor.GREEN + location.getBlockY());
                        sender.sendMessage(ChatColor.RED + "Z: " + ChatColor.GREEN + location.getBlockZ());
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
        return null;
    }
}
