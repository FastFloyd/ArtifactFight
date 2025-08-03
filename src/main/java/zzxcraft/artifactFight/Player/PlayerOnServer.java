package zzxcraft.artifactFight.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import zzxcraft.artifactFight.ArtifactFight;

public class PlayerOnServer extends BukkitRunnable {
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final static FileConfiguration config = ArtifactFight.getMainClass().getConfig();
    private final static ScoreboardManager scoreBoardManager = javaPlugin.getServer().getScoreboardManager();
    public PlayerOnServer(){

    }

    @Override
    public void run() {
        for(Player player : javaPlugin.getServer().getOnlinePlayers()){
            Do(player);
        }
    }
    public void start(){
        this.runTaskTimer(javaPlugin,0,1);
    }
    private void Do(Player player){
        Scoreboard scoreboard=scoreBoardManager.getNewScoreboard();
        Objective objective=scoreboard.registerNewObjective("title",Criteria.DUMMY,Component.text("IMC.RE",TextColor.color(255,215,0)));
        boolean fl=false;
        for(String s:player.getScoreboardTags()){
            if(s.equals("onPlay")){
                fl=true;
                break;
            }
        }
        objective.getScore("Status: "+(fl?"OnPlay":"OnWait")).setScore(1);
        objective.getScore("Tent: "+ player.getPersistentDataContainer().get(new NamespacedKey(javaPlugin, "tent"), PersistentDataType.INTEGER)).setScore(2);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }
}
