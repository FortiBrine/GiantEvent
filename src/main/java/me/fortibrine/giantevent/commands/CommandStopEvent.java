package me.fortibrine.giantevent.commands;

import me.fortibrine.giantevent.GiantEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandStopEvent implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        GiantEvent plugin = GiantEvent.getMain();
        FileConfiguration config = plugin.getConfig();

        List<Entity> entities = plugin.getMobs();

        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

        for (Entity en : entities) {
            LivingEntity lv = (LivingEntity) en;
            lv.setHealth(0);

            entities.remove(en);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(config.getString("messages.stopevent"));
        }

        return true;
    }
}
