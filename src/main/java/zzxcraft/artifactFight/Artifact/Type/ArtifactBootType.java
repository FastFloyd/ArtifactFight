package zzxcraft.artifactFight.Artifact.Type;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.tokens.TagTuple;
import zzxcraft.artifactFight.Artifact.Boot.*;
import zzxcraft.artifactFight.Artifact.ChestPlate.*;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Helmet.dragon_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.super_netherite_helmet;
import zzxcraft.artifactFight.ArtifactFight;
import org.bukkit.attribute.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArtifactBootType {
    Class<? extends ArtifactBootFather> prclass;
    ItemStack itemStack;
    Set<ArtifactBootType> children;
    Integer price;
    Integer id;
    public static final ArtifactBootType SUPER_SPEED_BOOT = new ArtifactBootType(14,createItemStack(Material.LEATHER_BOOTS,1,"神行",List.of(Component.text("极速 IV",TextColor.color(168,168,168)),Component.text("日行千里")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.1),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), super_speed_boot.class,Set.of(),2000);
    public static final ArtifactBootType SPEED_BOOT_PLUS_PLUS = new ArtifactBootType(13,createItemStack(Material.LEATHER_BOOTS,1,"强化旅行靴子",List.of(Component.text("极速 III",TextColor.color(168,168,168)),Component.text("更加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.075),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), speed_boot_plus_plus.class,Set.of(ArtifactBootType.SUPER_SPEED_BOOT),1000);
    public static final ArtifactBootType SPEED_BOOT_PLUS = new ArtifactBootType(12,createItemStack(Material.LEATHER_BOOTS,1,"加强旅行靴子",List.of(Component.text("极速 II",TextColor.color(168,168,168)),Component.text("加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.05),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.5))), speed_boot_plus.class,Set.of(ArtifactBootType.SPEED_BOOT_PLUS_PLUS),500);
    public static final ArtifactBootType SPEED_BOOT = new ArtifactBootType(11,createItemStack(Material.LEATHER_BOOTS,1,"旅行靴子",List.of(Component.text("极速 I",TextColor.color(168,168,168)),Component.text("很快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.025),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.0))), speed_boot.class,Set.of(ArtifactBootType.SPEED_BOOT_PLUS),100);
    public static final ArtifactBootType SUPER_MING_BOOT = new ArtifactBootType(10,createItemStack(Material.IRON_BOOTS,1,"明光",List.of(Component.text("明光 V",TextColor.color(168,168,168)),Component.text("无比坚硬")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0))), super_ming_boot.class,Set.of(),2000);
    public static final ArtifactBootType MING_BOOT_PLUS = new ArtifactBootType(9,createItemStack(Material.IRON_BOOTS,1,"强化明光靴子",List.of(Component.text("明光 III",TextColor.color(168,168,168)),Component.text("更好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), ming_boot_plus.class,Set.of(ArtifactBootType.SUPER_MING_BOOT),1000);
    public static final ArtifactBootType MING_BOOT = new ArtifactBootType(8,createItemStack(Material.IRON_BOOTS,1,"明光靴子",List.of(Component.text("明光 I",TextColor.color(168,168,168)),Component.text("上好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), ming_boot.class,Set.of(ArtifactBootType.MING_BOOT_PLUS),500);
    public static final ArtifactBootType SUPER_DRAGON_BOOT = new ArtifactBootType(7,createItemStack(Material.DIAMOND_BOOTS,1,"神龙靴子",List.of(Component.text("神龙 IV",TextColor.color(168,168,168)),Component.text("神龙之力")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), super_dragon_boot.class,Set.of(),2000);
    public static final ArtifactBootType DRAGON_BOOT = new ArtifactBootType(6,createItemStack(Material.DIAMOND_BOOTS,1,"龙之靴子",List.of(Component.text("神龙 III",TextColor.color(168,168,168)),Component.text("减弱魔法与箭矢的伤害")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), dragon_boot.class,Set.of(ArtifactBootType.SUPER_DRAGON_BOOT),1000);
    public static final ArtifactBootType SUPER_NETHERITE_BOOT = new ArtifactBootType(5,createItemStack(Material.NETHERITE_BOOTS,1,"不摧靴子",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,5.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER,0.15))), super_netherite_boot.class,Set.of(),2000);
    public static final ArtifactBootType NETHERITE_BOOT = new ArtifactBootType(4,createItemStack(Material.NETHERITE_BOOTS,1,"合金靴子",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true,Set.of()), netherite_boot.class, Set.of(ArtifactBootType.SUPER_NETHERITE_BOOT), 1000);
    public static final ArtifactBootType DIAMOND_BOOT = new ArtifactBootType(3,createItemStack(Material.DIAMOND_BOOTS,1,"钻石靴子",List.of(Component.text("无比坚硬的装甲")),false,Set.of()), diamond_boot.class,Set.of(ArtifactBootType.NETHERITE_BOOT,ArtifactBootType.DRAGON_BOOT),500);
    public static final ArtifactBootType IRON_BOOT = new ArtifactBootType(2,createItemStack(Material.IRON_BOOTS,1,"铁靴子",List.of(Component.text("百炼成钢")),false,Set.of()), iron_boot.class,Set.of(ArtifactBootType.DIAMOND_BOOT,ArtifactBootType.MING_BOOT),100);
    public static final ArtifactBootType LEATHER_BOOT = new ArtifactBootType(1,createItemStack(Material.LEATHER_BOOTS,1,"皮革靴子", List.of(Component.text("旅行者的最爱")),false,Set.of()), leather_boot.class,Set.of(ArtifactBootType.IRON_BOOT,ArtifactBootType.SPEED_BOOT),0);
    public static final ArtifactBootType BUY_BOOT = new ArtifactBootType(-1,ItemStack.of(Material.BARRIER), ArtifactBootFather.class,Set.of(ArtifactBootType.LEATHER_BOOT),0);
    private ArtifactBootType(Integer id,ItemStack itemStack,Class<? extends ArtifactBootFather> prclass,Set<ArtifactBootType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactBootFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
            itemMeta.addAttributeModifier(triple.getLeft(),new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),triple.getRight(),triple.getMiddle(), EquipmentSlotGroup.FEET));
        }
        itemStack1.setItemMeta(itemMeta);
        return itemStack1;
    }
    public Set<ArtifactBootType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getBootSize(){
        return 14;
    }
    public static ArtifactBootType getBoot(Integer id){
        return switch (id) {
            case 1 -> ArtifactBootType.LEATHER_BOOT;
            case 2 -> ArtifactBootType.IRON_BOOT;
            case 3 -> ArtifactBootType.DIAMOND_BOOT;
            case 4 -> ArtifactBootType.NETHERITE_BOOT;
            case 5 -> ArtifactBootType.SUPER_NETHERITE_BOOT;
            case 6 -> ArtifactBootType.DRAGON_BOOT;
            case 7 -> ArtifactBootType.SUPER_DRAGON_BOOT;
            case 8 -> ArtifactBootType.MING_BOOT;
            case 9 -> ArtifactBootType.MING_BOOT_PLUS;
            case 10 -> ArtifactBootType.SUPER_MING_BOOT;
            case 11 -> ArtifactBootType.SPEED_BOOT;
            case 12 -> ArtifactBootType.SPEED_BOOT_PLUS;
            case 13 -> ArtifactBootType.SPEED_BOOT_PLUS_PLUS;
            case 14 -> ArtifactBootType.SUPER_SPEED_BOOT;
            default -> null;
        };
    }
}
