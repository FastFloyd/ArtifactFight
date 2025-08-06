package zzxcraft.artifactFight.Artifact.Type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
    public static final ArtifactMainWeaponType SUPER_WITHER_SWORD = new ArtifactMainWeaponType(16,createItemStack(Material.STONE_SWORD,1,"凋灭",List.of(Component.text("凋零 V",TextColor.color(168,168,168)),Component.text("凋零本源")),true), super_wither_sword.class,Set.of(),1500);
    public static final ArtifactMainWeaponType WITHER_SWORD_PLUS_PLUS = new ArtifactMainWeaponType(15,createItemStack(Material.STONE_SWORD,1,"强化凋零之剑",List.of(Component.text("凋零 III",TextColor.color(168,168,168)),Component.text("更强的凋零之力")),true), wither_sword_plus_plus.class,Set.of(ArtifactMainWeaponType.SUPER_WITHER_SWORD),1000);
    public static final ArtifactMainWeaponType WITHER_SWORD_PLUS = new ArtifactMainWeaponType(14,createItemStack(Material.STONE_SWORD,1,"加强凋零之剑",List.of(Component.text("凋零 II",TextColor.color(168,168,168)),Component.text("加强的凋零之力")),true), wither_sword_plus.class,Set.of(ArtifactMainWeaponType.WITHER_SWORD_PLUS_PLUS),750);
    public static final ArtifactMainWeaponType WITHER_SWORD = new ArtifactMainWeaponType(13,createItemStack(Material.STONE_SWORD,1,"凋零之剑",List.of(Component.text("凋零 I",TextColor.color(168,168,168)),Component.text("携带凋零之力")),true), wither_sword.class,Set.of(ArtifactMainWeaponType.WITHER_SWORD_PLUS),500);
    public static final ArtifactMainWeaponType SUPER_NULL_SWORD = new ArtifactMainWeaponType(12,createItemStack(Material.IRON_SWORD,1,"终焉",List.of(Component.text("湮灭 V",TextColor.color(168,168,168)),Component.text("虚无铸成剑身")),true), super_null_sword.class,Set.of(),1500);
    public static final ArtifactMainWeaponType NULL_SWORD_PLUS = new ArtifactMainWeaponType(11,createItemStack(Material.IRON_SWORD,1,"强化湮灭之剑",List.of(Component.text("湮灭 III",TextColor.color(168,168,168)),Component.text("传说null曾经使用过这把剑")),true), null_sword_plus.class,Set.of(ArtifactMainWeaponType.SUPER_NULL_SWORD),1000);
    public static final ArtifactMainWeaponType NULL_SWORD = new ArtifactMainWeaponType(10,createItemStack(Material.IRON_SWORD,1,"湮灭之剑",List.of(Component.text("湮灭 I",TextColor.color(168,168,168)),Component.text("高血量克星")),true), null_sword.class,Set.of(ArtifactMainWeaponType.NULL_SWORD_PLUS),750);
    public static final ArtifactMainWeaponType SUPER_DRAGON_SWORD = new ArtifactMainWeaponType(9,createItemStack(Material.DIAMOND_SWORD,1,"龙怒",List.of(Component.text("破甲 V", TextColor.color(168,168,168)),Component.text("无视防御，真实伤害")),true),super_dragon_sword.class,Set.of(),1500);
    public static final ArtifactMainWeaponType DRAGON_SWORD = new ArtifactMainWeaponType(8,createItemStack(Material.DIAMOND_SWORD,1,"神龙剑",List.of(Component.text("破甲 III", TextColor.color(168,168,168)),Component.text("携带神龙的力量")),true), dragon_sword.class,Set.of(ArtifactMainWeaponType.SUPER_DRAGON_SWORD),1000);
    public static final ArtifactMainWeaponType SUPER_FIRE_SWORD = new ArtifactMainWeaponType(7,createItemStack(Material.NETHERITE_SWORD,1,"炽火",List.of(Component.text("焚烧 V", TextColor.color(168,168,168)),Component.text("携带下界的烈火而来")),true), super_fire_sword.class,Set.of(),1500);
    public static final ArtifactMainWeaponType SUPER_SHARP_SWORD = new ArtifactMainWeaponType(6,createItemStack(Material.NETHERITE_SWORD,1,"神锋",List.of(Component.text("锋利 V", TextColor.color(168,168,168)),Component.text("世上最坚硬的剑")),true), super_sharp_sword.class,Set.of(),1500);
    public static final ArtifactMainWeaponType NETHERITE_SWORD = new ArtifactMainWeaponType(5,createItemStack(Material.NETHERITE_SWORD,1,"下界合金剑",List.of(Component.text("来自灼热的下界")),false), netherite_sword.class,Set.of(ArtifactMainWeaponType.SUPER_FIRE_SWORD,ArtifactMainWeaponType.SUPER_SHARP_SWORD),1000);
    public static final ArtifactMainWeaponType DIAMOND_SWORD = new ArtifactMainWeaponType(4,createItemStack(Material.DIAMOND_SWORD,1,"钻石剑",List.of(Component.text("由大地的宝物构成")),false), diamond_sword.class,Set.of(ArtifactMainWeaponType.NETHERITE_SWORD,ArtifactMainWeaponType.DRAGON_SWORD),750);
    public static final ArtifactMainWeaponType IRON_SWORD = new ArtifactMainWeaponType(3,createItemStack(Material.IRON_SWORD,1,"铁剑",List.of(Component.text("由坚硬的铁锻造而成")),false), iron_sword.class,Set.of(ArtifactMainWeaponType.DIAMOND_SWORD,ArtifactMainWeaponType.NULL_SWORD),500);
    public static final ArtifactMainWeaponType STONE_SWORD = new ArtifactMainWeaponType(2,createItemStack(Material.STONE_SWORD,1,"石剑",List.of(Component.text("由石头捶打而成")),false), stone_sword.class,Set.of(ArtifactMainWeaponType.IRON_SWORD,ArtifactMainWeaponType.WITHER_SWORD),200);
    public static final ArtifactMainWeaponType WOODEN_SWORD = new ArtifactMainWeaponType(1,createItemStack(Material.WOODEN_SWORD,1,"木剑",List.of(Component.text("由木头削成")),false), wooden_sword.class,Set.of(ArtifactMainWeaponType.STONE_SWORD),0);
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
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, boolean glow){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        if(glow){
            itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack1.setItemMeta(itemMeta);
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
    public static Integer getMainWeaponSize(){
        return 16;
    }
    public static ArtifactMainWeaponType getWeapon(Integer id){
        return switch (id) {
            case 1 -> ArtifactMainWeaponType.WOODEN_SWORD;
            case 2 -> ArtifactMainWeaponType.STONE_SWORD;
            case 3 -> ArtifactMainWeaponType.IRON_SWORD;
            case 4 -> ArtifactMainWeaponType.DIAMOND_SWORD;
            case 5 -> ArtifactMainWeaponType.NETHERITE_SWORD;
            case 6 -> ArtifactMainWeaponType.SUPER_SHARP_SWORD;
            case 7 -> ArtifactMainWeaponType.SUPER_FIRE_SWORD;
            case 8 -> ArtifactMainWeaponType.DRAGON_SWORD;
            case 9 -> ArtifactMainWeaponType.SUPER_DRAGON_SWORD;
            case 10 -> ArtifactMainWeaponType.NULL_SWORD;
            case 11 -> ArtifactMainWeaponType.NULL_SWORD_PLUS;
            case 12 -> ArtifactMainWeaponType.SUPER_NULL_SWORD;
            case 13 -> ArtifactMainWeaponType.WITHER_SWORD;
            case 14 -> ArtifactMainWeaponType.WITHER_SWORD_PLUS;
            case 15 -> ArtifactMainWeaponType.WITHER_SWORD_PLUS_PLUS;
            case 16 -> ArtifactMainWeaponType.SUPER_WITHER_SWORD;
            default -> null;
        };
    }
}
