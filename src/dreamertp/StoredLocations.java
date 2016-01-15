/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamertp;

import org.bukkit.Location;

/**
 *
 * @author Bussiness
 */
public class StoredLocations {
    
    Location loc1;
    Location loc2;

    public StoredLocations() {
        loc1 = null;
        loc2 = null;
    }
    
    
    
    void setLoc1(Location location) {
        loc1 = location;
    }
    
    void setLoc2(Location location) {
        loc2 = location;
    }
    
    Location getLoc1() {
        return loc1;
    }
    
    Location getLoc2() {
        return loc2;
    }
}
