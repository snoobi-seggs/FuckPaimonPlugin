package snoobi.bignose;

import emu.grasscutter.plugin.Plugin;
import snoobi.bignose.commands.RevealSurroundings;

public final class RevealSurroundingsSnoo extends Plugin {
    /* Turn the plugin into a singleton. */
    private static RevealSurroundingsSnoo instance;

    public static RevealSurroundingsSnoo getInstance() {
        return instance;
    }
    
    @Override public void onLoad() {
        // Set the plugin instance.
        instance = this;
    }

    @Override public void onEnable() {
        // Register commands.
        this.getHandle().registerCommand(new RevealSurroundings());

        // Log a plugin status message.
        this.getLogger().info("The Reveal Surroundings plugin has been enabled.");
    }

    @Override public void onDisable() {
        // Log a plugin status message.
        this.getLogger().info("The Reveal Surroundings plugin died lol");
    }

  
}
