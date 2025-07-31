package zzxcraft.artifactFight.Inventory.ChooseInventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactMainWeaponType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.Inventory.ChooseInventory.ChooseItemInventory;

import java.util.HashMap;
import java.util.Set;

public class ChooseMainWeaponInventory implements InventoryHolder {
    private final Set<Inventory> inventorys;
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    private final Player player;
    private final ChooseItemInventory superInventory;
    private HashMap<Integer, ArtifactMainWeaponType> mainWeaponTypeHashMap=new HashMap<>();
    public ChooseMainWeaponInventory(Player player,ChooseItemInventory chooseItemInventory){
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
    private void addMainWeaponType(int slot,ArtifactMainWeaponType artifactMainWeaponType){
        this.mainWeaponTypeHashMap.put(slot,artifactMainWeaponType);
        this.getInventory().setItem(slot,artifactMainWeaponType.getItemStack());
    }
    public ArtifactMainWeaponType getMainWeaponType(int slot){
        return this.mainWeaponTypeHashMap.get(slot);
    }
}
