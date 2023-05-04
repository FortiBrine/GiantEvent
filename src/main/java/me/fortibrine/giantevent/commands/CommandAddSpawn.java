package me.fortibrine.giantevent.commands;

import me.fortibrine.giantevent.GiantEvent;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandAddSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        GiantEvent plugin = GiantEvent.getMain();
        FileConfiguration config = GiantEvent.getMain().getConfig();

        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("messages.nonplayer"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(GiantEvent.getMain().getDescription().getPermissions().get(1))) {
            sender.sendMessage(config.getString("messages.nonpermission"));
            return true;
        }

        Location location = player.getLocation();

        String uuid = UUID.randomUUID().toString();

        config.set("spawn." + uuid + ".world", location.getWorld().getName());
        config.set("spawn." + uuid + ".x", location.getX());
        config.set("spawn." + uuid + ".y", location.getY());
        config.set("spawn." + uuid + ".z", location.getZ());

        player.sendMessage(config.getString("messages.location"));

        plugin.saveConfig();
        plugin.reloadConfig();

        return true;
    }

}
