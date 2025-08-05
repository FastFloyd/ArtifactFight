package zzxcraft.artifactFight;

import org.bukkit.GameRule;
import org.bukkit.command.CommandMap;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactFather;
import zzxcraft.artifactFight.Command.ItemCommands;
import zzxcraft.artifactFight.Listener.ArtifactFightPlayerListener;
import zzxcraft.artifactFight.Listener.ArtifactItemListener;
import zzxcraft.artifactFight.Player.PlayerOnPlay;
import zzxcraft.artifactFight.Player.PlayerOnServer;
import zzxcraft.artifactFight.Player.PlayerOnWait;
import zzxcraft.artifactFight.Listener.ArtifactFightPlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


public final class ArtifactFight extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new ArtifactFightPlayerListener(),this);
        this.getServer().getPluginManager().registerEvents(new ArtifactItemListener(),this);
        this.getServer().getCommandMap().register("tent", new ItemCommands("tent"));
        (new PlayerOnWait()).start();
        (new PlayerOnPlay()).start();
        (new PlayerOnServer()).start();
        this.getServer().getWorld("world").setGameRule(GameRule.KEEP_INVENTORY,true);
        this.getServer().getWorld("world").setGameRule(GameRule.FALL_DAMAGE,false);
        this.getLogger().info("ArtifactFight succeeded in enabling!");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.getLogger().info("EnableTime : "+formatter.format(date));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("ArtifactFight disable successfully.");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.getLogger().info("DisableTime : "+formatter.format(date));
        PlayerArtifactMap.ArtifactMap.clear();
        PlayerArtifactMap.HelmetPlayerMap.clear();
        PlayerArtifactMap.ChestPlatePlayerMap.clear();
        PlayerArtifactMap.LeggingPlayerMap.clear();
        PlayerArtifactMap.BootPlayerMap.clear();
        PlayerArtifactMap.MainWeaponPlayerMap.clear();
        PlayerArtifactMap.DeputyWeaponPlayerMap.clear();
        PlayerArtifactMap.Prop1PlayerMap.clear();
        PlayerArtifactMap.Prop2PlayerMap.clear();
        PlayerArtifactMap.Prop3PlayerMap.clear();
    }
    public static JavaPlugin getMainClass(){
        return JavaPlugin.getPlugin(ArtifactFight.class);
    }
    public static String ComponentToString(Component component){
        String s = PlainTextComponentSerializer.plainText().serialize(component);
        return s.substring(1,s.length()-1);
    }
}
