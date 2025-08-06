package zzxcraft.artifactFight.Artifact.Type;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.ChestPlate.diamond_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.iron_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.leather_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.netherite_chestplate;
import zzxcraft.artifactFight.Artifact.Elytra.elytra;
import zzxcraft.artifactFight.Artifact.Elytra.speed_elytra;
import zzxcraft.artifactFight.Artifact.Elytra.super_speed_elytra;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactElytraFather;
import zzxcraft.artifactFight.ArtifactFight;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArtifactElytraType {
    Class<? extends ArtifactElytraFather> prclass;
    ItemStack itemStack;
    Set<ArtifactElytraType> children;
    Integer price;
    Integer id;
    public static final ArtifactElytraType SUPER_SPEED_ELYTRA = new ArtifactElytraType(3,createItemStack(Material.ELYTRA,1,"闪电",List.of(Component.text("快如闪电")),false,Set.of()), super_speed_elytra.class,Set.of(),500);
    public static final ArtifactElytraType SPEED_ELYTRA = new ArtifactElytraType(2,createItemStack(Material.ELYTRA,1,"疾风",List.of(Component.text("翱翔蓝天")),false,Set.of()), speed_elytra.class,Set.of(ArtifactElytraType.SUPER_SPEED_ELYTRA),200);
    public static final ArtifactElytraType ELYTRA = new ArtifactElytraType(1,createItemStack(Material.ELYTRA,1,"鞘翅", List.of(Component.text("天空即为极限")),false,Set.of()), elytra.class,Set.of(ArtifactElytraType.SPEED_ELYTRA),0);
    public static final ArtifactElytraType BUY_ELYTRA= new ArtifactElytraType(-1,ItemStack.of(Material.BARRIER), ArtifactElytraFather.class,Set.of(ArtifactElytraType.ELYTRA),0);
    private ArtifactElytraType(Integer id, ItemStack itemStack, Class<? extends ArtifactElytraFather> prclass, Set<ArtifactElytraType> children, Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactElytraFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
    public Set<ArtifactElytraType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getElytraSize(){
        return 3;
    }
    public static ArtifactElytraType getElytra(Integer id){
        return switch (id) {
            case 1 -> ArtifactElytraType.ELYTRA;
            case 2 -> ArtifactElytraType.SPEED_ELYTRA;
            case 3 -> ArtifactElytraType.SUPER_SPEED_ELYTRA;
            default -> null;
        };
    }
}
