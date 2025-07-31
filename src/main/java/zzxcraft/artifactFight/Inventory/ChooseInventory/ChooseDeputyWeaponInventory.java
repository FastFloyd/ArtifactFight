package zzxcraft.artifactFight.Inventory.ChooseInventory;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactBowType;
import zzxcraft.artifactFight.Artifact.Type.ArtifactShieldType;
import zzxcraft.artifactFight.ArtifactFight;

import java.util.HashMap;
import java.util.Set;

public class ChooseDeputyWeaponInventory implements InventoryHolder {
    private final Set<Inventory> inventorys;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer, ArtifactShieldType> shieldTypeHashMap=new HashMap<>();
    private HashMap<Integer, ArtifactBowType> bowTypeHashMap=new HashMap<>();
    public ChooseDeputyWeaponInventory(Player player,ChooseItemInventory chooseItemInventory){
        this.superInventory=chooseItemInventory;
        this.player=player;
        this.inventorys = Set.of(javaPlugin.getServer().createInventory(this,54));
        this.getInventory().setItem(49,NameItemStack(ItemStack.of(Material.BLACK_WOOL)));
    }
    public ChooseItemInventory getSuperInventory(){return this.superInventory;}
    @Override
    public @NotNull Inventory getInventory() {
        return (Inventory) inventorys.toArray()[0];
    }
    public Inventory getInventory(int slot){
        return (Inventory) inventorys.toArray()[slot];
    }
    private static ItemStack NameItemStack(ItemStack itemStack){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("返回"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void addDeputyWeaponType(int slot,ArtifactShieldType artifactShieldType){
        this.shieldTypeHashMap.put(slot,artifactShieldType);
        this.getInventory().setItem(slot,artifactShieldType.getItemStack());
    }
    private void addDeputyWeaponType(int slot,ArtifactBowType artifactBowType){
        this.bowTypeHashMap.put(slot,artifactBowType);
        this.getInventory().setItem(slot,artifactBowType.getItemStack());
    }
    public Pair<Integer,Object> getDeputyWeaponType(int slot){
        if(slot<this.shieldTypeHashMap.size()){
            return Pair.of(1,this.shieldTypeHashMap.get(slot));
        }
        else{
            return Pair.of(2,this.bowTypeHashMap.get(slot));
        }
    }
}
