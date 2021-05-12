package pl.pijok.deathswap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pijok.deathswap.DeathSwap;
import pl.pijok.deathswap.deathSwapController.DeathSwapController;
import pl.pijok.deathswap.essentials.ChatUtils;
import pl.pijok.deathswap.essentials.Debug;

public class DeathSwapCommand implements CommandExecutor {

    private final DeathSwapController deathSwapController = DeathSwap.getDeathSwapController();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            Debug.log("&cKomenda tylko dla graczy!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("start")){
                if(!deathSwapController.hasStarted()){
                    deathSwapController.start();
                    return true;
                }
                else{
                    ChatUtils.sendMessage(player, "&cGra juz wystartowala!");
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("join")){
                if(!deathSwapController.isPlayer(player)){
                    deathSwapController.addPlayer(player);
                    ChatUtils.sendMessage(player, "&aDolaczono do gry!");
                    return true;
                }
                else{
                    ChatUtils.sendMessage(player, "&cJuz jestes w grze!");
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("leave")){
                if(deathSwapController.isPlayer(player)) {
                    deathSwapController.removePlayer(player);
                    ChatUtils.sendMessage(player, "&cOpuszczono gre ;c");
                    return true;
                }
                else{
                    ChatUtils.sendMessage(player, "&cNie ma cie w grze!");
                    return true;
                }
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("timer")){
                int time = Integer.parseInt(args[1]);
                deathSwapController.setTimerCooldown(time);
                ChatUtils.sendMessage(player, "&cUstawiono czas na " + time);
                return true;
            }
        }


        ChatUtils.sendMessage(player, "&7/" + label + " start");
        ChatUtils.sendMessage(player, "&7/" + label + " join");
        ChatUtils.sendMessage(player, "&7/" + label + " leave");
        ChatUtils.sendMessage(player, "&7/" + label + "timer <time>");
        return true;
    }
}
