package com.carlgo11.report;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Plugin extends JavaPlugin {

    /**
     * Send an error message to the console.
     *
     * @param message Message to be displayed.
     */
    public static void sendError(String message) {
        Logger logger = Bukkit.getLogger();
        logger.log(Level.WARNING, message);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
