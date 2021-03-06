package com.jkush321.autowalls.commands;

import com.jkush321.autowalls.AutoWalls;
import com.jkush321.autowalls.WallDropper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    private AutoWalls plugin;

    public JoinCommand(AutoWalls plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {


        if (cmdSender instanceof Player)
        {
            Player p = (Player) cmdSender;
            boolean allowed = false;
            if (plugin.config.getInt("votes.players." + p.getName()) >= plugin.earlyJoinPriority && !plugin.gameInProgress) { allowed = true; }
            if (plugin.canJoin && !plugin.gameInProgress){ allowed = true; }
            if (plugin.playing.size()<plugin.teamSize*4 && plugin.config.getInt("votes.players." + p.getName()) >= plugin.lateJoinPriority && WallDropper.time > 0) { allowed = true; }
            if (!allowed)
            {
                cmdSender.sendMessage(plugin.wprefix + ChatColor.DARK_RED + "You can not join the game at this time!");
                return true;
            }
            if (args.length == 0) // Add to random team
            {
                if (plugin.redTeam.size()<plugin.teamSize)
                    plugin.joinTeam(p, "red");
                else if (plugin.blueTeam.size()<plugin.teamSize)
                    plugin.joinTeam(p, "blue");
                else if (plugin.greenTeam.size()<plugin.teamSize)
                    plugin.joinTeam(p, "green");
                else if (plugin.orangeTeam.size()<plugin.teamSize)
                    plugin.joinTeam(p, "orange");
                else p.sendMessage(plugin.wprefix + ChatColor.RED + "Every team is full!");
            }
            else if (args.length == 1) // Add to specified team
            {
                if (args[0].equalsIgnoreCase("red"))
                    plugin.joinTeam(p,"red");
                else if (args[0].equalsIgnoreCase("blue"))
                    plugin.joinTeam(p,"blue");
                else if (args[0].equalsIgnoreCase("green"))
                    plugin.joinTeam(p,"green");
                else if (args[0].equalsIgnoreCase("orange"))
                    plugin.joinTeam(p,"orange");
                else p.sendMessage(plugin.wprefix + ChatColor.DARK_RED + "The Team " + args[0] + " Is Invalid!");
            }
            else p.sendMessage(plugin.wprefix + ChatColor.RED + "Too Many Arguments. /join <red|blue|green|orange>");
        }
        else cmdSender.sendMessage("You can't join a team, console :P");
        return true;
    }
}