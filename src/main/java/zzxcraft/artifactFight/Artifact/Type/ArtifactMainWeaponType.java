package zzxcraft.artifactFight.Artifact.Type;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.Artifact.MainWeapon.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class ArtifactMainWeaponType {
    Class<? extends ArtifactMainWeaponFather> prclass;
    ItemStack itemStack;
    Set<ArtifactMainWeaponType> children;
    Integer price;
    Integer id;
    public static final ArtifactMainWeaponType NETHERITE_SWORD = new ArtifactMainWeaponType(5,createItemStack(Material.NETHERITE_SWORD,1,"下界合金剑",List.of(Component.text("来自灼热的下界"),Component.text("Piece:1000")),Set.of()), netherite_sword.class,Set.of(),1000);
    public static final ArtifactMainWeaponType DIAMOND_SWORD = new ArtifactMainWeaponType(4,createItemStack(Material.DIAMOND_SWORD,1,"钻石剑",List.of(Component.text("由大地的宝物构成"),Component.text("Piece:750")),Set.of()), diamond_sword.class,Set.of(ArtifactMainWeaponType.NETHERITE_SWORD),750);
    public static final ArtifactMainWeaponType IRON_SWORD = new ArtifactMainWeaponType(3,createItemStack(Material.IRON_SWORD,1,"铁剑",List.of(Component.text("由坚硬的铁锻造而成"),Component.text("Piece:500")),Set.of()), iron_sword.class,Set.of(ArtifactMainWeaponType.DIAMOND_SWORD),500);
    public static final ArtifactMainWeaponType STONE_SWORD = new ArtifactMainWeaponType(2,createItemStack(Material.STONE_SWORD,1,"石剑",List.of(Component.text("由石头捶打而成"),Component.text("Piece:200")),Set.of()), stone_sword.class,Set.of(ArtifactMainWeaponType.IRON_SWORD),200);
    public static final ArtifactMainWeaponType WOODEN_SWORD = new ArtifactMainWeaponType(1,createItemStack(Material.WOODEN_SWORD,1,"木剑",List.of(Component.text("由木头削成"),Component.text("Piece:free")),Set.of()), wooden_sword.class,Set.of(ArtifactMainWeaponType.STONE_SWORD),0);
    public static final ArtifactMainWeaponType BUY_WEAPON= new ArtifactMainWeaponType(-1,ItemStack.of(Material.BARRIER), ArtifactMainWeaponFather.class,Set.of(ArtifactMainWeaponType.WOODEN_SWORD),0);
    private ArtifactMainWeaponType(Integer id,ItemStack itemStack,Class<? extends ArtifactMainWeaponFather> prclass,Set<ArtifactMainWeaponType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactMainWeaponFather createRunnable(Player player,Integer slot) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class, Integer.class).newInstance(player,slot);
    }
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, Set<Pair<Enchantment,Integer>> EnchSet){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        itemStack1.setItemMeta(itemMeta);
        for(Pair<Enchantment,Integer> pair: EnchSet){
            itemStack1.addEnchantment(pair.getLeft(),pair.getRight());
        }
        return itemStack1;
    }
    public Set<ArtifactMainWeaponType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static ArtifactMainWeaponType getWeapon(Integer id){
        return switch (id) {
            case 1 -> ArtifactMainWeaponType.WOODEN_SWORD;
            case 2 -> ArtifactMainWeaponType.STONE_SWORD;
            case 3 -> ArtifactMainWeaponType.IRON_SWORD;
            case 4 -> ArtifactMainWeaponType.DIAMOND_SWORD;
            case 5 -> ArtifactMainWeaponType.NETHERITE_SWORD;
            default -> null;
        };
    }
}
