package pl.pijok.deathswap.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.pijok.deathswap.DeathSwap;
import pl.pijok.deathswap.deathSwapController.DeathSwapController;
import pl.pijok.deathswap.essentials.ChatUtils;

public class DeathListener implements Listener {

    private final DeathSwapController deathSwapController = DeathSwap.getDeathSwapController();

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        Player player = event.getEntity();

        if(deathSwapController.isPlayer(player)){
            ChatUtils.broadcast("&c&l" + player.getName() + " umiera!");
            deathSwapController.removePlayer(player);
            return;
        }

    }
}
