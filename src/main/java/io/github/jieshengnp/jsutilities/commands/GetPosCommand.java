package io.github.jieshengnp.jsutilities.commands;

import org.bukkit.Bukkit;
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
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Location location = player.getLocation();
                    player.sendMessage("§a[JSUtils] §6Current position: X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ());
                } else {
                    sender.sendMessage("§a[JSUtils] §4/getpos <PlayerName>");
                }
                return true;
            } else if (args.length == 1){
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);
                if (selectedPlayer != null){
                    Location location = selectedPlayer.getLocation();
                    if (sender.getName() != selectedPlayer.getName()){
                        sender.sendMessage("§a[JSUtils] §6Current position of " + selectedPlayer.getName() + ": X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ());
                    } else {
                        sender.sendMessage("§a[JSUtils] §6Current position: X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ());
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
        return null;
    }
}
