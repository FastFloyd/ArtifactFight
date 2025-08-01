package zzxcraft.artifactFight;

import org.bukkit.command.CommandMap;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactFather;
import zzxcraft.artifactFight.Command.ItemCommands;
import zzxcraft.artifactFight.Listener.ArtifactFightPlayerListener;
import zzxcraft.artifactFight.Listener.ArtifactItemListener;
import zzxcraft.artifactFight.Player.PlayerOnPlay;
import zzxcraft.artifactFight.Player.PlayerOnWait;
import zzxcraft.artifactFight.Listener.ArtifactFightPlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;


public final class ArtifactFight extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ArtifactFightPlayerListener(),this);
        getServer().getPluginManager().registerEvents(new ArtifactItemListener(),this);
        PlayerOnWait playerOnWait = new PlayerOnWait();
        playerOnWait.start();
        PlayerOnPlay playerOnPlay = new PlayerOnPlay();
        playerOnPlay.start();
        this.getServer().getCommandMap().register("tent", new ItemCommands("tent"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static JavaPlugin getMainClass(){
        return JavaPlugin.getPlugin(ArtifactFight.class);
    }
    public static String ComponentToString(Component component){
        String s = PlainTextComponentSerializer.plainText().serialize(component);
        return s.substring(1,s.length()-1);
    }
}
