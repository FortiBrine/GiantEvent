package me.fortibrine.giantevent.commands;

import me.fortibrine.giantevent.GiantEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Random;

public class CommandStartEvent implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        GiantEvent plugin = GiantEvent.getMain();
        FileConfiguration config = plugin.getConfig();

        List<Entity> entities = plugin.getMobs();

        Bukkit.getWorld("world").setTime(18000);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        for (String key : config.getConfigurationSection("spawn").getKeys(false)) {
            World world = Bukkit.getWorld(config.getString("spawn." + key + ".world"));
            double x = config.getDouble("spawn." + key + ".x");
            double y = config.getDouble("spawn." + key + ".y");
            double z = config.getDouble("spawn." + key + ".z");

            Location location = new Location(world, x, y, z);

            if (new Random().nextInt(2) == 0) {
                Entity entity = world.spawnEntity(location, EntityType.GIANT);
                entity.setGlowing(true);
                LivingEntity lv = (LivingEntity) entity;
                lv.setAI(true);
//                lv.setHealth(200);

                entities.add(entity);
            } else {
                Entity entity = world.spawnEntity(location, EntityType.IRON_GOLEM);
                entity.setGlowing(true);
                LivingEntity lv = (LivingEntity) entity;
                lv.setAI(true);
//                lv.setHealth(200);

                entities.add(entity);
            }

        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(config.getString("messages.startevent"));
        }

        return false;
    }
}
