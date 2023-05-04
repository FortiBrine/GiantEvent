package me.fortibrine.giantevent;

import me.fortibrine.giantevent.commands.CommandAddSpawn;
import me.fortibrine.giantevent.commands.CommandStartEvent;
import me.fortibrine.giantevent.commands.CommandStopEvent;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GiantEvent extends JavaPlugin {

    private List<Entity> mobs = new ArrayList<>();
    private static GiantEvent instance;

    public static GiantEvent getMain() {
        return instance;
    }

    public List<Entity> getMobs() {
        return this.mobs;
    }

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(this.getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            this.saveDefaultConfig();
        }

        this.getCommand("addspawn").setExecutor(new CommandAddSpawn());
        this.getCommand("startevent").setExecutor(new CommandStartEvent());
        this.getCommand("stopevent").setExecutor(new CommandStopEvent());

    }

}
