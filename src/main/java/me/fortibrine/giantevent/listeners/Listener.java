package me.fortibrine.giantevent.listeners;

import me.fortibrine.giantevent.GiantEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        GiantEvent plugin = GiantEvent.getMain();
        List<Entity> entities = plugin.getMobs();

        Entity entity = event.getEntity();

        if (!entities.contains(entity)) {
            return;
        }

        entities.remove(entity);

        Location location = entity.getLocation();

        World world = location.getWorld();

        world.getBlockAt(location).setType(Material.CHEST);

        Chest chest = (Chest) world.getBlockAt(location);

        Inventory inventory = chest.getInventory();

        inventory.addItem(new ItemStack(Material.DIAMOND, 22));

    }

}
