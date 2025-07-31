package zzxcraft.artifactFight.Inventory.ChooseInventory;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Type.*;
import zzxcraft.artifactFight.ArtifactFight;

public class ChooseItemInventory implements InventoryHolder {
    private final Inventory inventory;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final String[] SlotName={"helmet","chestplate","legging","boots","mainweapon","deputyweapon","firstprop","secondprop","thirdprop"};
    public ChooseItemInventory(Player player) {
        this.player=player;
        inventory = javaPlugin.getServer().createInventory(this,9);
        for(int i=0;i<=8;i++){
            itemdisplay(i);
        }
    }
    private void itemdisplay(int slot){
        PersistentDataContainer playerPersistentDataContainer = player.getPersistentDataContainer();
        Integer type=playerPersistentDataContainer.get(new NamespacedKey(javaPlugin,"chose_"+SlotName[slot]), PersistentDataType.INTEGER);
        if(type==null){
            inventory.setItem(slot, ItemStack.of(Material.BARRIER));
        }
        else{
            inventory.setItem(slot,switch (slot){
                case 0 -> ArtifactHelmetType.getHelmet(type).getItemStack();
                case 1 -> switch (type%10){
                    case 1 -> ArtifactChestPlateType.getChestplate(type/10).getItemStack();
                    case 2 -> ArtifactElytraType.getElytra(type/10).getItemStack();
                    default -> null;
                };
                case 2 -> ArtifactLeggingType.getLegging(type).getItemStack();
                case 3 -> ArtifactBootType.getBoot(type).getItemStack();
                case 4, 5 -> switch (type%10){
                    case 1 -> ArtifactMainWeaponType.getWeapon(type/10).getItemStack();
                    case 2 -> ArtifactBowType.getBow(type/10).getItemStack();
                    case 3 -> ArtifactShieldType.getShield(type/10).getItemStack();
                    default -> null;
                };
                case 6, 7, 8 -> ArtifactPropType.getProp(type).getItemStack();
                default -> null;
            });
        }
    }
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
