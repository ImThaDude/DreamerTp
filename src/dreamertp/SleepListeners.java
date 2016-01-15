/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamertp;

import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

/*
This is the sleep listener. It will assign and store the values.
*/

public class SleepListeners implements Listener {
    
    DreamerTp plugin;
    
    public SleepListeners(DreamerTp value) {
        plugin = value;
    }
    
    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        
        Player p = event.getPlayer();
        Location l = event.getBed().getLocation();
        
        p.sendMessage("Telepoting!");
        
        if (l.getWorld().getName() != plugin.DreamWorld.getName()) {
            p.sendMessage("Telepoting ro dream!");
            storeLocation(p, l);
            teleport(p, l);
        }
        
        else if (l.getWorld().getName() == plugin.DreamWorld.getName()) {
            p.sendMessage("Telepoting ro main!");
            storeLocation(p, l);
            teleport(p, l);
        }
        
        p.sendMessage("Tested!");
        
        event.setCancelled(true);
    }
    
    Location storeLocation(Player p, Location l) {
        UUID id = p.getUniqueId();
        World w = l.getWorld();
        if (!plugin.Stored.containsKey(id)) {
            plugin.Stored.put(id, new StoredLocations());
        }
        if (w.getName() != plugin.DreamWorld.getName()) {
            plugin.Stored.get(id).setLoc1(l);
            p.setBedSpawnLocation(plugin.Stored.get(id).getLoc2());
        }
        else if (w.getName() == plugin.DreamWorld.getName()) {
            plugin.Stored.get(id).setLoc2(l);
            p.setBedSpawnLocation(plugin.Stored.get(id).getLoc1());
        }
        return l;
    }
    
    Location teleport(Player p, Location l) {
        UUID id = p.getUniqueId();
        World w = l.getWorld();
        if (w.getName() != plugin.DreamWorld.getName()) {
            if (p.getBedSpawnLocation() == null) {
                p.sendMessage("You have not set yet. " + w.getName());
                p.teleport(plugin.DreamWorld.getSpawnLocation());
                //p.sendMessage(plugin.DreamWorld.getSpawnLocation().getBlockX() + " " + plugin.DreamWorld.getSpawnLocation().getBlockY());
                return plugin.DreamWorld.getSpawnLocation();
            }
            else {
                p.teleport(plugin.Stored.get(id).loc2);
                p.sendMessage(plugin.Stored.get(id).loc2.getWorld().getName());
                return plugin.Stored.get(id).loc2;
            }
        }
        else if (w.getName() == plugin.DreamWorld.getName()) {
            if (p.getBedSpawnLocation() == null) {
                p.sendMessage("You have not set yet. " + w.getName());
                p.teleport(plugin.MainWorld.getSpawnLocation());
                return plugin.MainWorld.getSpawnLocation();
            }
            else {
                p.teleport(plugin.Stored.get(id).loc1);
                p.sendMessage(plugin.Stored.get(id).loc1.getWorld().getName());
                return plugin.Stored.get(id).loc1;
            }
        }
        return null;
    }
}
