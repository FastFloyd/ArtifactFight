package zzxcraft.artifactFight.Command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.ArtifactFight;

public class ItemCommands extends Command {
    JavaPlugin javaplugin=ArtifactFight.getMainClass();
    public ItemCommands(@NotNull String name) {
        super(name);
        this.setDescription("set player tent");
        this.setUsage("/tent");
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(s.equals("tent")){
            if(commandSender instanceof Player player && !player.isOp()){
                player.sendMessage(Component.text("你没有这样做的权限！",TextColor.color(255,0,0)));
                return false;
            }
            if(strings.length<2){
                commandSender.sendMessage(Component.text("不完整的指令参数！",TextColor.color(255,0,0)));
                return false;
            }
            Player player=javaplugin.getServer().getPlayer(strings[1]);
            if(player==null){
                commandSender.sendMessage(Component.text("不存在的玩家名",TextColor.color(255,0,0)));
                return false;
            }
            PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
            NamespacedKey namespacedKey=new NamespacedKey(javaplugin,"tent");
            switch (strings[0]){
                case "add":{
                    if(strings.length<3){
                        commandSender.sendMessage(Component.text("不完整的指令参数！",TextColor.color(255,0,0)));
                        return false;
                    }
                    persistentDataContainer.set(namespacedKey,PersistentDataType.INTEGER,persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)+Integer.parseInt(strings[2]));
                }
                case "remove":{
                    if(strings.length<3){
                        commandSender.sendMessage(Component.text("不完整的指令参数！",TextColor.color(255,0,0)));
                        return false;
                    }
                    persistentDataContainer.set(namespacedKey,PersistentDataType.INTEGER,persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)-Integer.parseInt(strings[2]));
                }
                case "set":{
                    if(strings.length<3){
                        commandSender.sendMessage(Component.text("不完整的指令参数！",TextColor.color(255,0,0)));
                        return false;
                    }
                    persistentDataContainer.set(namespacedKey,PersistentDataType.INTEGER,Integer.parseInt(strings[2]));
                }
                case "get":{
                    commandSender.sendMessage(String.valueOf(persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)));
                }
                default:{
                    commandSender.sendMessage(Component.text("错误的指令参数",TextColor.color(255,0,0)));
                }
            }
        }
        return false;
    }
}
