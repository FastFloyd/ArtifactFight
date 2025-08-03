package zzxcraft.artifactFight.Inventory.ChooseInventory;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactBowType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactMainWeaponType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactShieldType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.Inventory.ChooseInventory.ChooseItemInventory;

import java.util.HashMap;
import java.util.Set;

public class ChooseMainWeaponInventory implements InventoryHolder {
    private final Inventory inventory;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer, ArtifactMainWeaponType> mainWeaponTypeHashMap=new HashMap<>();
    private HashMap<Integer, ArtifactShieldType> shieldTypeHashMap=new HashMap<>();
    private HashMap<Integer, ArtifactBowType> bowTypeHashMap=new HashMap<>();
    public ChooseMainWeaponInventory(Player player,ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventory=javaPlugin.getServer().createInventory(this,54);
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
        PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
        int I=0;
        for(int i=1;i<=ArtifactMainWeaponType.getMainWeaponSize();i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_mainWeapon"+i),PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addMainWeaponType(I,ArtifactMainWeaponType.getWeapon(i));
            I++;
        }
        for(int i=1;i<=ArtifactShieldType.getShieldSize();i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_shield"+i),PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addMainWeaponType(I,ArtifactShieldType.getShield(i));
            I++;
        }
        for(int i=1;i<=ArtifactBowType.getBowSize();i++){
            Boolean c=persistentDataContainer.get(new NamespacedKey(javaPlugin,"bought_bow"+i),PersistentDataType.BOOLEAN);
            if(c==null) continue;
            addMainWeaponType(I,ArtifactBowType.getBow(i));
            I++;
        }
    }
    public ChooseItemInventory getSuperInventory(){return this.superInventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
    private static ItemStack NameItemStack(ItemStack itemStack){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("返回"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void addMainWeaponType(int slot,ArtifactMainWeaponType artifactMainWeaponType){
        this.mainWeaponTypeHashMap.put(slot,artifactMainWeaponType);
        this.getInventory().setItem(slot,artifactMainWeaponType.getItemStack());
    }
    private void addMainWeaponType(int slot,ArtifactShieldType artifactShieldType){
        this.shieldTypeHashMap.put(slot,artifactShieldType);
        this.getInventory().setItem(slot,artifactShieldType.getItemStack());
    }
    private void addMainWeaponType(int slot, ArtifactBowType artifactBowType){
        this.bowTypeHashMap.put(slot,artifactBowType);
        this.getInventory().setItem(slot,artifactBowType.getItemStack());
    }
    public Object getMainWeaponType(int slot){
        if(slot<this.mainWeaponTypeHashMap.size()){
            return this.mainWeaponTypeHashMap.get(slot);
        }
        else if(slot<this.mainWeaponTypeHashMap.size()+this.shieldTypeHashMap.size()){
            return this.shieldTypeHashMap.get(slot);
        }
        else  {
            return this.bowTypeHashMap.get(slot);
        }
    }
}
