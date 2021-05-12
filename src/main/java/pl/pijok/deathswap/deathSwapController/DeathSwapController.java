package pl.pijok.deathswap.deathSwapController;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.pijok.deathswap.DeathSwap;
import pl.pijok.deathswap.essentials.ChatUtils;
import pl.pijok.deathswap.essentials.Debug;

import java.util.ArrayList;

public class DeathSwapController {

    private ArrayList<Player> players = new ArrayList<>();
    private boolean started = false;
    private int timerCooldown = 300;
    private boolean won = false;

    public void start(){
        if(players.size() != 2){
            Debug.log("&cMusi byc dokladnie 2 graczy w grze!");
            return;
        }

        ChatUtils.broadcast("&a&lRozpoczeto DeathSwap!");
        initTimer();
    }

    public void initTimer(){
        final int[] currentTime = new int[1];
        currentTime[0] = timerCooldown;

        DeathSwap.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(DeathSwap.getInstance(), new Runnable() {
            @Override
            public void run() {

                if(players.size() < 2){
                    if(!won){
                        ChatUtils.broadcast("&a&l" + players.get(0) + " wygrywa!");
                        won = true;
                    }
                    return;
                }

                if(currentTime[0] < 10){
                    ChatUtils.broadcast("&aZmiana nastapi za &e" + currentTime[0]);
                }
                if(currentTime[0] == 0){
                    ChatUtils.broadcast("&c&lZamiana!");
                    Location player1 = players.get(0).getLocation();
                    Location player2 = players.get(1).getLocation();

                    players.get(0).teleport(player2);
                    players.get(1).teleport(player1);
                    currentTime[0] = timerCooldown;
                    ChatUtils.broadcast("&cNastepna zamiana za 5 minut!");
                }

                currentTime[0]--;
            }
        }, 20L, 20L);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
    }

    public boolean isPlayer(Player player){
        return players.contains(player);
    }

    public boolean hasStarted(){
        return started;
    }

    public int getTimerCooldown() {
        return timerCooldown;
    }

    public void setTimerCooldown(int timerCooldown) {
        this.timerCooldown = timerCooldown;
    }
}
