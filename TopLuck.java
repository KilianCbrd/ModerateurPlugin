package me.minecraftserver.moderationplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TopLuck implements CommandExecutor, Listener {

    public static ArrayList<Player> playerList = new ArrayList<>();
    public static HashMap<Player, Double> MineraisBreaked = new HashMap<>();
    public static HashMap<Player, Double> StoneBreaked = new HashMap<>();
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Inventory topluck = Bukkit.createInventory(null, 27, "§6§lTopLuck");
        double TotalValue = ;
        double value = TopLuck.MineraisBreaked.get(player);
        double valueS = TopLuck.StoneBreaked.get(player);

        for(Player x: playerList){
            topluck.setItem(x, it(x.getPlayer(), BlockBreakEvent.));
        }

        return false;
    }

    public ItemStack it(String custom, String lore){

        ItemStack i = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta iM = i.getItemMeta();
        iM.setDisplayName(custom);
        iM.setLore(Arrays.asList(lore));
        i.setItemMeta(iM);
        return i;
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e){
        Player player = e.getPlayer();
        Material block = e.getBlock().getType();
        Material[] minerais = {Material.DIAMOND_ORE, Material.ANCIENT_DEBRIS};
        Double value = TopLuck.MineraisBreaked.get(player);
        Double valueS = TopLuck.StoneBreaked.get(player);

        for(Material x: minerais){
            if(block.equals(x)){
                player.sendMessage(block.toString());
                TopLuck.MineraisBreaked.putIfAbsent(player, 1.0);
                TopLuck.MineraisBreaked.replace(player, value, value++);
            }else{
                player.sendMessage(".");
                TopLuck.StoneBreaked.putIfAbsent(player, 1.0);
                TopLuck.StoneBreaked.replace(player, valueS, valueS++);
            }
        }
    }
}
