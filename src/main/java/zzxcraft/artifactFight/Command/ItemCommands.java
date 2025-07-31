package zzxcraft.artifactFight.Command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.ArtifactFight;

public class ItemCommands extends Command {
    public ItemCommands(@NotNull String name) {
        super(name);
        this.setDescription("set player tent");
        this.setUsage("/tent");
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(s.equals("tent")){
            if(commandSender instanceof Player player){
                if(!player.isOp()){
                    player.sendMessage(Component.text("你没有这样做的权限", TextColor.color(255,0,0)));
                }
                else{
                    Player player1= ArtifactFight.getMainClass().getServer().getPlayer(strings[1]);
                    if(player1==null){
                        commandSender.sendMessage("这个玩家不存在！");
                        return false;
                    }
                    PersistentDataContainer persistentDataContainer=player1.getPersistentDataContainer();
                    NamespacedKey namespacedKey=new NamespacedKey(ArtifactFight.getMainClass(),"tent");
                    switch(strings[0]){
                        case "add":{
                            persistentDataContainer.set(namespacedKey, PersistentDataType.INTEGER,persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)+Integer.parseInt(strings[2]));
                            break;
                        }
                        case "set":{
                            persistentDataContainer.set(namespacedKey,PersistentDataType.INTEGER,Integer.parseInt(strings[2]));
                            break;
                        }
                        case "remove":{
                            persistentDataContainer.set(namespacedKey, PersistentDataType.INTEGER,Math.max(persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)-Integer.parseInt(strings[2]),0));
                            break;
                        }
                        case "get":{
                            commandSender.sendMessage(persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER).toString());
                            break;
                        }
                        default:{
                            commandSender.sendMessage(strings[0]+"不是一个合法的指令");
                            break;
                        }
                    }
                }
            }
            else{
                Player player= ArtifactFight.getMainClass().getServer().getPlayer(strings[1]);
                if(player==null){
                    commandSender.sendMessage("这个玩家不存在！");
                    return false;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(ArtifactFight.getMainClass(),"tent");
                switch(strings[0]){
                    case "add":{
                        persistentDataContainer.set(namespacedKey, PersistentDataType.INTEGER,persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)+Integer.parseInt(strings[2]));
                    }
                    case "set":{
                        persistentDataContainer.set(namespacedKey,PersistentDataType.INTEGER,Integer.parseInt(strings[2]));
                    }
                    case "remove":{
                        persistentDataContainer.set(namespacedKey, PersistentDataType.INTEGER,Math.max(persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER)-Integer.parseInt(strings[2]),0));
                    }
                    case "get":{
                        commandSender.sendMessage(persistentDataContainer.get(namespacedKey,PersistentDataType.INTEGER).toString());
                    }
                }
            }
        }
        return false;
    }
}
