package org.ipav.warp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        this.getCommand("warp").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Menu(), this);
        getLogger().info("Initialized WARP Plugin!");
    }

    @Override
    public void onDisable(){getLogger().info("Disabled WARP Plugin!");}

}
