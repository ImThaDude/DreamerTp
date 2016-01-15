/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamertp;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
I need to work on storing and loading player locations from a file.
Also try making it more efficient
Implement if player dies, plaer will be teleported to the world he died spawn.

Changed to all but dreamworld
Changed to UUID instead of plaer hashmap
Make sure to change spawn of world first things first when you use this for the first time.
*/

public class DreamerTp extends JavaPlugin {
    
    World DreamWorld;
    World MainWorld;
    public HashMap<UUID, StoredLocations> Stored;

    public void onEnable() {
        
        Stored = new HashMap<>();
        
        DreamWorld = getServer().createWorld(new WorldCreator("DreamWorld"));
        MainWorld = getServer().createWorld(new WorldCreator("world"));
        getServer().getPluginManager().registerEvents(new SleepListeners(this), this);
        
    }
    
    public void onDisable() {
        
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("world")) {
            p.sendMessage(p.getWorld().getName() + " " + p.getWorld().getSpawnLocation().getBlockX() + " " + p.getWorld().getSpawnLocation().getBlockY());
        }
        return false;
    }
}
