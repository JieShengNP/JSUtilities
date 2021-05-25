package io.github.jieshengnp.jsutilities;

import io.github.jieshengnp.jsutilities.commands.HealCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class JSUtilities extends JavaPlugin {
    @Override
    public void onEnable(){
        getCommand("heal").setExecutor(new HealCommand());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[JSUtilities] is now Enabled.");
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[JSUtilities] is now Disabled.");
    }
}
