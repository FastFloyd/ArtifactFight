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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Helmet.*;
import zzxcraft.artifactFight.ArtifactFight;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArtifactHelmetType {
    Class<? extends ArtifactHelmetFather> prclass;
    ItemStack itemStack;
    Set<ArtifactHelmetType> children;
    Integer price;
    Integer id;
    public static final ArtifactHelmetType SUPER_SPEED_HELMET = new ArtifactHelmetType(14,createItemStack(Material.LEATHER_HELMET,1,"神行",List.of(Component.text("极速 IV",TextColor.color(168,168,168)),Component.text("日行千里")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.1),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), super_speed_helmet.class,Set.of(),2000);
    public static final ArtifactHelmetType SPEED_HELMET_PLUS_PLUS = new ArtifactHelmetType(13,createItemStack(Material.LEATHER_HELMET,1,"强化旅行头盔",List.of(Component.text("极速 III",TextColor.color(168,168,168)),Component.text("更加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.075),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), speed_helmet_plus_plus.class,Set.of(ArtifactHelmetType.SUPER_SPEED_HELMET),1000);
    public static final ArtifactHelmetType SPEED_HELMET_PLUS = new ArtifactHelmetType(12,createItemStack(Material.LEATHER_HELMET,1,"加强旅行头盔",List.of(Component.text("极速 II",TextColor.color(168,168,168)),Component.text("加快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.05),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.5))), speed_helmet_plus.class,Set.of(ArtifactHelmetType.SPEED_HELMET_PLUS_PLUS),500);
    public static final ArtifactHelmetType SPEED_HELMET = new ArtifactHelmetType(11,createItemStack(Material.LEATHER_HELMET,1,"旅行头盔",List.of(Component.text("极速 I",TextColor.color(168,168,168)),Component.text("很快的速度")),true,Set.of(Triple.of(Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_NUMBER,0.025),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,1.0))), speed_helmet.class,Set.of(ArtifactHelmetType.SPEED_HELMET_PLUS),100);
    public static final ArtifactHelmetType SUPER_MING_HELMET = new ArtifactHelmetType(10,createItemStack(Material.IRON_HELMET,1,"明光",List.of(Component.text("明光 V",TextColor.color(168,168,168)),Component.text("无比坚硬")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0))), super_ming_helmet.class,Set.of(),2000);
    public static final ArtifactHelmetType MING_HELMET_PLUS = new ArtifactHelmetType(9,createItemStack(Material.IRON_HELMET,1,"强化明光头盔",List.of(Component.text("明光 III",TextColor.color(168,168,168)),Component.text("更好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.5))), ming_helmet_plus.class,Set.of(ArtifactHelmetType.SUPER_MING_HELMET),1000);
    public static final ArtifactHelmetType MING_HELMET = new ArtifactHelmetType(8,createItemStack(Material.IRON_HELMET,1,"明光头盔",List.of(Component.text("明光 I",TextColor.color(168,168,168)),Component.text("上好的铁铸成")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,2.0))), ming_helmet.class,Set.of(ArtifactHelmetType.MING_HELMET_PLUS),500);
    public static final ArtifactHelmetType SUPER_DRAGON_HELMET = new ArtifactHelmetType(7,createItemStack(Material.DIAMOND_HELMET,1,"神龙头盔",List.of(Component.text("神龙 IV",TextColor.color(168,168,168)),Component.text("神龙之力")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,2.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), super_dragon_helmet.class,Set.of(),2000);
    public static final ArtifactHelmetType DRAGON_HELMET = new ArtifactHelmetType(6,createItemStack(Material.DIAMOND_HELMET,1,"龙之头盔",List.of(Component.text("神龙 III",TextColor.color(168,168,168)),Component.text("减弱魔法与箭矢的伤害")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,1.5),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,2.0))), dragon_helmet.class,Set.of(ArtifactHelmetType.SUPER_DRAGON_HELMET),1000);
    public static final ArtifactHelmetType SUPER_NETHERITE_HELMET = new ArtifactHelmetType(5,createItemStack(Material.NETHERITE_HELMET,1,"不摧头盔",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true,Set.of(Triple.of(Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER,5.0),Triple.of(Attribute.ARMOR, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER,3.0),Triple.of(Attribute.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER,0.15))), super_netherite_helmet.class,Set.of(),2000);
    public static final ArtifactHelmetType NETHERITE_HELMET =new ArtifactHelmetType(4,createItemStack(Material.NETHERITE_HELMET,1,"合金头盔",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true,Set.of()), netherite_helmet.class,Set.of(ArtifactHelmetType.SUPER_NETHERITE_HELMET),1000);
    public static final ArtifactHelmetType DIAMOND_HELMET = new ArtifactHelmetType(3,createItemStack(Material.DIAMOND_HELMET,1,"钻石头盔",List.of(Component.text("无比坚硬的装甲")),false,Set.of()), diamond_helmet.class,Set.of(ArtifactHelmetType.NETHERITE_HELMET,ArtifactHelmetType.DRAGON_HELMET),500);
    public static final ArtifactHelmetType IRON_HELMET = new ArtifactHelmetType(2,createItemStack(Material.IRON_HELMET,1,"铁头盔",List.of(Component.text("百炼成钢")),false,Set.of()), iron_helmet.class,Set.of(ArtifactHelmetType.DIAMOND_HELMET,ArtifactHelmetType.MING_HELMET),100);
    public static final ArtifactHelmetType LEATHER_HELMET = new ArtifactHelmetType(1,createItemStack(Material.LEATHER_HELMET,1,"皮革头盔",List.of(Component.text("旅行者的最爱")),false,Set.of()), leather_helmet.class,Set.of(ArtifactHelmetType.IRON_HELMET,ArtifactHelmetType.SPEED_HELMET),0);
    public static final ArtifactHelmetType BUY_HELMET = new ArtifactHelmetType(-1,ItemStack.of(Material.BARRIER),ArtifactHelmetFather.class,Set.of(ArtifactHelmetType.LEATHER_HELMET),0);
    private ArtifactHelmetType(Integer id,ItemStack itemStack,Class<? extends ArtifactHelmetFather> prclass,Set<ArtifactHelmetType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;

    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactHelmetFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
            itemMeta.addAttributeModifier(triple.getLeft(),new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),triple.getRight(),triple.getMiddle(), EquipmentSlotGroup.HEAD));
        }
        itemStack1.setItemMeta(itemMeta);
        return itemStack1;
    }
    public Set<ArtifactHelmetType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getHelmetSize(){
        return 14;
    }
    public static ArtifactHelmetType getHelmet(Integer id){
        return switch (id) {
            case 1 -> ArtifactHelmetType.LEATHER_HELMET;
            case 2 -> ArtifactHelmetType.IRON_HELMET;
            case 3 -> ArtifactHelmetType.DIAMOND_HELMET;
            case 4 -> ArtifactHelmetType.NETHERITE_HELMET;
            case 5 -> ArtifactHelmetType.SUPER_NETHERITE_HELMET;
            case 6 -> ArtifactHelmetType.DRAGON_HELMET;
            case 7 -> ArtifactHelmetType.SUPER_DRAGON_HELMET;
            case 8 -> ArtifactHelmetType.MING_HELMET;
            case 9 -> ArtifactHelmetType.MING_HELMET_PLUS;
            case 10 -> ArtifactHelmetType.SUPER_MING_HELMET;
            case 11 -> ArtifactHelmetType.SPEED_HELMET;
            case 12 -> ArtifactHelmetType.SPEED_HELMET_PLUS;
            case 13 -> ArtifactHelmetType.SPEED_HELMET_PLUS_PLUS;
            case 14 -> ArtifactHelmetType.SUPER_SPEED_HELMET;
            default -> null;
        };
    }
}
