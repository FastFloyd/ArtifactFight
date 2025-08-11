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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Helmet.*;
import zzxcraft.artifactFight.ArtifactFight;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArtifactChestPlateType {
    Class<? extends ArtifactChestPlateFather> prclass;
    ItemStack itemStack;
    Set<ArtifactChestPlateType> children;
    Integer price;
    Integer id;
    public static final ArtifactChestPlateType SUPER_SPEED_CHESTPLATE = new ArtifactChestPlateType(14,createItemStack(Material.LEATHER_CHESTPLATE,1,"神行",List.of(Component.text("极速 IV",TextColor.color(168,168,168)),Component.text("日行千里")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.1),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), super_speed_chestplate.class,Set.of(),2000);
    public static final ArtifactChestPlateType SPEED_CHESTPLATE_PLUS_PLUS = new ArtifactChestPlateType(13,createItemStack(Material.LEATHER_CHESTPLATE,1,"强化旅行胸甲",List.of(Component.text("极速 III",TextColor.color(168,168,168)),Component.text("更加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.075),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), speed_chestplate_plus_plus.class,Set.of(ArtifactChestPlateType.SUPER_SPEED_CHESTPLATE),1000);
    public static final ArtifactChestPlateType SPEED_CHESTPLATE_PLUS = new ArtifactChestPlateType(12,createItemStack(Material.LEATHER_CHESTPLATE,1,"加强旅行胸甲",List.of(Component.text("极速 II",TextColor.color(168,168,168)),Component.text("加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.05),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.5))), speed_chestplate_plus.class,Set.of(ArtifactChestPlateType.SPEED_CHESTPLATE_PLUS_PLUS),500);
    public static final ArtifactChestPlateType SPEED_CHESTPLATE = new ArtifactChestPlateType(11,createItemStack(Material.LEATHER_CHESTPLATE,1,"旅行胸甲",List.of(Component.text("极速 I",TextColor.color(168,168,168)),Component.text("很快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.025),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.0))), speed_chestplate.class,Set.of(ArtifactChestPlateType.SPEED_CHESTPLATE_PLUS),100);
    public static final ArtifactChestPlateType SUPER_MING_CHESTPLATE = new ArtifactChestPlateType(10,createItemStack(Material.IRON_CHESTPLATE,1,"明光",List.of(Component.text("明光 V",TextColor.color(168,168,168)),Component.text("无比坚硬")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0))), super_ming_chestplate.class,Set.of(),2000);
    public static final ArtifactChestPlateType MING_CHESTPLATE_PLUS = new ArtifactChestPlateType(9,createItemStack(Material.IRON_CHESTPLATE,1,"强化明光胸甲",List.of(Component.text("明光 III",TextColor.color(168,168,168)),Component.text("更好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), ming_chestplate_plus.class,Set.of(ArtifactChestPlateType.SUPER_MING_CHESTPLATE),1000);
    public static final ArtifactChestPlateType MING_CHESTPLATE = new ArtifactChestPlateType(8,createItemStack(Material.IRON_CHESTPLATE,1,"明光胸甲",List.of(Component.text("明光 I",TextColor.color(168,168,168)),Component.text("上好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), ming_chestplate.class,Set.of(ArtifactChestPlateType.MING_CHESTPLATE_PLUS),500);
    public static final ArtifactChestPlateType SUPER_DRAGON_CHESTPLATE = new ArtifactChestPlateType(7,createItemStack(Material.DIAMOND_CHESTPLATE,1,"神龙胸甲",List.of(Component.text("神龙 IV",TextColor.color(168,168,168)),Component.text("神龙之力")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), super_dragon_chestplate.class,Set.of(),2000);
    public static final ArtifactChestPlateType DRAGON_CHESTPLATE = new ArtifactChestPlateType(6,createItemStack(Material.DIAMOND_CHESTPLATE,1,"龙之胸甲",List.of(Component.text("神龙 III",TextColor.color(168,168,168)),Component.text("减弱魔法与箭矢的伤害")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), dragon_chestplate.class,Set.of(ArtifactChestPlateType.SUPER_DRAGON_CHESTPLATE),1000);
    public static final ArtifactChestPlateType SUPER_NETHERITE_CHESTPLATE = new ArtifactChestPlateType(5,createItemStack(Material.NETHERITE_CHESTPLATE,1,"不摧胸甲",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,5.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER,0.15))), super_netherite_chestplate.class,Set.of(),2000);
    public static final ArtifactChestPlateType NETHERITE_CHESTPLATE = new ArtifactChestPlateType(4,createItemStack(Material.NETHERITE_CHESTPLATE,1,"合金胸甲",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true,Set.of()), netherite_chestplate.class, Set.of(ArtifactChestPlateType.SUPER_NETHERITE_CHESTPLATE), 1000);
    public static final ArtifactChestPlateType DIAMOND_CHESTPLATE = new ArtifactChestPlateType(3,createItemStack(Material.DIAMOND_CHESTPLATE,1,"钻石胸甲",List.of(Component.text("无比坚硬的装甲")),false,Set.of()), diamond_chestplate.class,Set.of(ArtifactChestPlateType.NETHERITE_CHESTPLATE,ArtifactChestPlateType.DRAGON_CHESTPLATE),500);
    public static final ArtifactChestPlateType IRON_CHESTPLATE = new ArtifactChestPlateType(2,createItemStack(Material.IRON_CHESTPLATE,1,"铁胸甲",List.of(Component.text("百炼成钢")),false,Set.of()), iron_chestplate.class,Set.of(ArtifactChestPlateType.DIAMOND_CHESTPLATE,ArtifactChestPlateType.MING_CHESTPLATE),100);
    public static final ArtifactChestPlateType LEATHER_CHESTPLATE = new ArtifactChestPlateType(1,createItemStack(Material.LEATHER_CHESTPLATE,1,"皮革胸甲",List.of(Component.text("旅行者的最爱")),false,Set.of()), leather_chestplate.class,Set.of(ArtifactChestPlateType.IRON_CHESTPLATE,ArtifactChestPlateType.SPEED_CHESTPLATE),0);
    public static final ArtifactChestPlateType BUY_CHESTPLATE= new ArtifactChestPlateType(-1,ItemStack.of(Material.BARRIER), ArtifactChestPlateFather.class,Set.of(ArtifactChestPlateType.LEATHER_CHESTPLATE),0);
    private ArtifactChestPlateType(Integer id,ItemStack itemStack,Class<? extends ArtifactChestPlateFather> prclass,Set<ArtifactChestPlateType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactChestPlateFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
            itemMeta.addAttributeModifier(triple.getLeft(),new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),triple.getRight(),triple.getMiddle(), EquipmentSlotGroup.CHEST));
        }
        itemStack1.setItemMeta(itemMeta);
        return itemStack1;
    }
    public Set<ArtifactChestPlateType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getChestPlateSize(){
        return 14;
    }
    public static ArtifactChestPlateType getChestplate(Integer id){
        return switch (id) {
            case 1 -> ArtifactChestPlateType.LEATHER_CHESTPLATE;
            case 2 -> ArtifactChestPlateType.IRON_CHESTPLATE;
            case 3 -> ArtifactChestPlateType.DIAMOND_CHESTPLATE;
            case 4 -> ArtifactChestPlateType.NETHERITE_CHESTPLATE;
            case 5 -> ArtifactChestPlateType.SUPER_NETHERITE_CHESTPLATE;
            case 6 -> ArtifactChestPlateType.DRAGON_CHESTPLATE;
            case 7 -> ArtifactChestPlateType.SUPER_DRAGON_CHESTPLATE;
            case 8 -> ArtifactChestPlateType.MING_CHESTPLATE;
            case 9 -> ArtifactChestPlateType.MING_CHESTPLATE_PLUS;
            case 10 -> ArtifactChestPlateType.SUPER_MING_CHESTPLATE;
            case 11 -> ArtifactChestPlateType.SPEED_CHESTPLATE;
            case 12 -> ArtifactChestPlateType.SPEED_CHESTPLATE_PLUS;
            case 13 -> ArtifactChestPlateType.SPEED_CHESTPLATE_PLUS_PLUS;
            case 14 -> ArtifactChestPlateType.SUPER_SPEED_CHESTPLATE;
            default -> null;
        };
    }
}
