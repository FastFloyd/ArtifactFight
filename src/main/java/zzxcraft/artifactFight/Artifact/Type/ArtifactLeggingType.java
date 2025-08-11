package zzxcraft.artifactFight.Artifact.Type;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.ChestPlate.*;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;
import zzxcraft.artifactFight.Artifact.Helmet.dragon_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.super_netherite_helmet;
import zzxcraft.artifactFight.Artifact.Legging.*;
import zzxcraft.artifactFight.ArtifactFight;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArtifactLeggingType {
    Class<? extends ArtifactLeggingFather> prclass;
    ItemStack itemStack;
    Set<ArtifactLeggingType> children;
    Integer price;
    Integer id;
    public static final ArtifactLeggingType SUPER_SPEED_LEGGING = new ArtifactLeggingType(14,createItemStack(Material.LEATHER_LEGGINGS,1,"神行",List.of(Component.text("极速 IV",TextColor.color(168,168,168)),Component.text("日行千里")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.1),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), super_speed_legging.class,Set.of(),2000);
    public static final ArtifactLeggingType SPEED_LEGGING_PLUS_PLUS = new ArtifactLeggingType(13,createItemStack(Material.LEATHER_LEGGINGS,1,"强化旅行护腿",List.of(Component.text("极速 III",TextColor.color(168,168,168)),Component.text("更加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.075),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), speed_legging_plus_plus.class,Set.of(ArtifactLeggingType.SUPER_SPEED_LEGGING),1000);
    public static final ArtifactLeggingType SPEED_LEGGING_PLUS = new ArtifactLeggingType(12,createItemStack(Material.LEATHER_LEGGINGS,1,"加强旅行护腿",List.of(Component.text("极速 II",TextColor.color(168,168,168)),Component.text("加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.05),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.5))), speed_legging_plus.class,Set.of(ArtifactLeggingType.SPEED_LEGGING_PLUS_PLUS),500);
    public static final ArtifactLeggingType SPEED_LEGGING = new ArtifactLeggingType(11,createItemStack(Material.LEATHER_LEGGINGS,1,"旅行护腿",List.of(Component.text("极速 I",TextColor.color(168,168,168)),Component.text("很快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.025),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.0))), speed_legging.class,Set.of(ArtifactLeggingType.SPEED_LEGGING_PLUS),100);
    public static final ArtifactLeggingType SUPER_MING_LEGGING = new ArtifactLeggingType(10,createItemStack(Material.IRON_LEGGINGS,1,"明光",List.of(Component.text("明光 V",TextColor.color(168,168,168)),Component.text("无比坚硬")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0))), super_ming_legging.class,Set.of(),2000);
    public static final ArtifactLeggingType MING_LEGGING_PLUS = new ArtifactLeggingType(9,createItemStack(Material.IRON_LEGGINGS,1,"强化明光护腿",List.of(Component.text("明光 III",TextColor.color(168,168,168)),Component.text("更好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), ming_legging_plus.class,Set.of(ArtifactLeggingType.SUPER_MING_LEGGING),1000);
    public static final ArtifactLeggingType MING_LEGGING = new ArtifactLeggingType(8,createItemStack(Material.IRON_LEGGINGS,1,"明光护腿",List.of(Component.text("明光 I",TextColor.color(168,168,168)),Component.text("上好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), ming_legging.class,Set.of(ArtifactLeggingType.MING_LEGGING_PLUS),500);
    public static final ArtifactLeggingType SUPER_DRAGON_LEGGING = new ArtifactLeggingType(7,createItemStack(Material.DIAMOND_LEGGINGS,1,"神龙护腿",List.of(Component.text("神龙 IV",TextColor.color(168,168,168)),Component.text("神龙之力")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), super_dragon_legging.class,Set.of(),2000);
    public static final ArtifactLeggingType DRAGON_LEGGING = new ArtifactLeggingType(6,createItemStack(Material.DIAMOND_LEGGINGS,1,"龙之护腿",List.of(Component.text("神龙 III",TextColor.color(168,168,168)),Component.text("减弱魔法与箭矢的伤害")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), dragon_legging.class,Set.of(ArtifactLeggingType.SUPER_DRAGON_LEGGING),1000);
    public static final ArtifactLeggingType SUPER_NETHERITE_LEGGING = new ArtifactLeggingType(5,createItemStack(Material.NETHERITE_LEGGINGS,1,"不摧护腿",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,5.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER,0.15))), super_netherite_legging.class,Set.of(),2000);
    public static final ArtifactLeggingType NETHERITE_LEGGING = new ArtifactLeggingType(4,createItemStack(Material.NETHERITE_LEGGINGS,1,"合金护腿",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true,Set.of()), netherite_legging.class, Set.of(ArtifactLeggingType.SUPER_NETHERITE_LEGGING), 1000);
    public static final ArtifactLeggingType DIAMOND_LEGGING = new ArtifactLeggingType(3,createItemStack(Material.DIAMOND_LEGGINGS,1,"钻石护腿",List.of(Component.text("无比坚硬的装甲")),false,Set.of()), diamond_legging.class,Set.of(ArtifactLeggingType.NETHERITE_LEGGING,ArtifactLeggingType.DRAGON_LEGGING),500);
    public static final ArtifactLeggingType IRON_LEGGING = new ArtifactLeggingType(2,createItemStack(Material.IRON_LEGGINGS,1,"铁护腿",List.of(Component.text("百炼成钢")),false,Set.of()), iron_legging.class,Set.of(ArtifactLeggingType.DIAMOND_LEGGING,ArtifactLeggingType.MING_LEGGING),100);
    public static final ArtifactLeggingType LEATHER_LRGGING = new ArtifactLeggingType(1,createItemStack(Material.LEATHER_LEGGINGS,1,"皮革护腿",List.of(Component.text("旅行者的最爱")),false,Set.of()), leather_legging.class,Set.of(ArtifactLeggingType.IRON_LEGGING,ArtifactLeggingType.SPEED_LEGGING),0);
    public static final ArtifactLeggingType BUY_LEGGING= new ArtifactLeggingType(-1,ItemStack.of(Material.BARRIER), ArtifactLeggingFather.class,Set.of(ArtifactLeggingType.LEATHER_LRGGING),0);
    private ArtifactLeggingType(Integer id,ItemStack itemStack,Class<? extends ArtifactLeggingFather> prclass,Set<ArtifactLeggingType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactLeggingFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class).newInstance(player);
    }
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, boolean glow, Set<Triple<Attribute, AttributeModifier.Operation,Double>> attributeSet){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        if(glow){
            itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        for(Triple<Attribute, AttributeModifier.Operation,Double> triple:attributeSet){
            itemMeta.addAttributeModifier(triple.getLeft(),new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),triple.getRight(),triple.getMiddle(), EquipmentSlotGroup.LEGS));
        }
        itemStack1.setItemMeta(itemMeta);
        return itemStack1;
    }
    public Set<ArtifactLeggingType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getLeggingSize(){
        return 14;
    }
    public static ArtifactLeggingType getLegging(Integer id){
        return switch (id) {
            case 1 -> ArtifactLeggingType.LEATHER_LRGGING;
            case 2 -> ArtifactLeggingType.IRON_LEGGING;
            case 3 -> ArtifactLeggingType.DIAMOND_LEGGING;
            case 4 -> ArtifactLeggingType.NETHERITE_LEGGING;
            case 5 -> ArtifactLeggingType.SUPER_NETHERITE_LEGGING;
            case 6 -> ArtifactLeggingType.DRAGON_LEGGING;
            case 7 -> ArtifactLeggingType.SUPER_DRAGON_LEGGING;
            case 8 -> ArtifactLeggingType.MING_LEGGING;
            case 9 -> ArtifactLeggingType.MING_LEGGING_PLUS;
            case 10 -> ArtifactLeggingType.SUPER_MING_LEGGING;
            case 11 -> ArtifactLeggingType.SPEED_LEGGING;
            case 12 -> ArtifactLeggingType.SPEED_LEGGING_PLUS;
            case 13 -> ArtifactLeggingType.SPEED_LEGGING_PLUS_PLUS;
            case 14 -> ArtifactLeggingType.SUPER_SPEED_LEGGING;
            default -> null;
        };
    }
}
