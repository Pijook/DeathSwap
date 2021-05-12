package pl.pijok.deathswap;

import org.bukkit.plugin.java.JavaPlugin;
import pl.pijok.deathswap.commands.DeathSwapCommand;
import pl.pijok.deathswap.deathSwapController.DeathSwapController;
import pl.pijok.deathswap.listeners.DeathListener;

public class DeathSwap extends JavaPlugin {

    private static DeathSwap instance;
    private static DeathSwapController deathSwapController;

    @Override
    public void onEnable() {
        instance = this;
        deathSwapController = new DeathSwapController();

        getCommand("deathswap").setExecutor(new DeathSwapCommand());

        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static DeathSwap getInstance() {
        return instance;
    }

    public static DeathSwapController getDeathSwapController() {
        return deathSwapController;
    }
}
