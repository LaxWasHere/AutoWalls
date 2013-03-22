package com.jkush321.autowalls.commands;

import com.jkush321.autowalls.AutoWalls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PriorityCommand implements CommandExecutor {

    private AutoWalls plugin;

    public PriorityCommand(AutoWalls plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {

        if (args.length != 2 && args.length != 3) {
            cmdSender.sendMessage(plugin.wprefix + ChatColor.RED + "Usage : /priority <name> <amount>");
            return true;
        }

        if (!cmdSender.hasPermission("walls.op") || !cmdSender.hasPermission("walls.pri")) {
            cmdSender.sendMessage(plugin.wprefix + ChatColor.RED + "Usage : /priority <name> <amount>");
            return true;
        }

        Player pl = Bukkit.getPlayer(args[0]);

        int a = Integer.parseInt(args[1]);

        if (plugin.config.isSet("votes.players." + pl.getName())) {
            plugin.config.set("votes.players." + pl.getName(), plugin.config.getInt("votes.players." + pl.getName()) + a);
        } else plugin.config.set("votes.players." + pl.getName(), a);
        if (Bukkit.getPlayer(pl.getName()) != null && Bukkit.getPlayer(pl.getName()).isOnline())
            Bukkit.getPlayer(pl.getName()).sendMessage(plugin.wprefix + ChatColor.YELLOW + "Your priority is now " + plugin.config.getInt("votes.players." + pl.getName()));
        plugin.saveConfig();
        if (!pl.isOnline()) {
            cmdSender.sendMessage(plugin.wprefix + "Done!");
            return true;
        }
        pl.setDisplayName(pl.getName());
        if (plugin.config.isSet("votes.players." + pl.getName()) && plugin.config.getInt("votes.players." + pl.getName()) >= 20) {
            pl.setDisplayName(plugin.wprefix + ChatColor.DARK_AQUA + pl.getName() + ChatColor.WHITE);
        }
        if (plugin.config.isSet("votes.players." + pl.getName()) && plugin.config.getInt("votes.players." + pl.getName()) >= 250) {
            pl.setDisplayName(plugin.wprefix + ChatColor.DARK_RED + pl.getName() + ChatColor.WHITE);
        }
        if (plugin.config.getBoolean("priorities") == true) {
            if (plugin.config.isSet("votes.players." + pl.getName())) {
                pl.setDisplayName(plugin.wprefix + ChatColor.YELLOW + "[" + plugin.config.getInt("votes.players." + pl.getName()) + "]" + ChatColor.GRAY + pl.getDisplayName() + ChatColor.WHITE);
            } else pl.setDisplayName(plugin.wprefix + ChatColor.GRAY + "[0]" + pl.getDisplayName() + ChatColor.WHITE);
        }
        return true;
    }
}