package zzxcraft.artifactFight.Artifact.Type;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.ChestPlate.*;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactChestPlateType {
    Class<? extends ArtifactChestPlateFather> prclass;
    ItemStack itemStack;
    Set<ArtifactChestPlateType> children;
    Integer price;
    Integer id;
    public static final ArtifactChestPlateType SUPER_NETHERITE_CHESTPLATE = new ArtifactChestPlateType(5,createItemStack(Material.NETHERITE_CHESTPLATE,1,"不摧胸甲",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true), super_netherite_chestplate.class,Set.of(),2000);
    public static final ArtifactChestPlateType NETHERITE_CHESTPLATE = new ArtifactChestPlateType(4,createItemStack(Material.NETHERITE_CHESTPLATE,1,"合金胸甲",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true), netherite_chestplate.class, Set.of(ArtifactChestPlateType.SUPER_NETHERITE_CHESTPLATE), 1000);
    public static final ArtifactChestPlateType DIAMOND_CHESTPLATE = new ArtifactChestPlateType(3,createItemStack(Material.DIAMOND_CHESTPLATE,1,"钻石胸甲",List.of(Component.text("无比坚硬的装甲")),false), diamond_chestplate.class,Set.of(ArtifactChestPlateType.NETHERITE_CHESTPLATE),500);
    public static final ArtifactChestPlateType IRON_CHESTPLATE = new ArtifactChestPlateType(2,createItemStack(Material.IRON_CHESTPLATE,1,"铁胸甲",List.of(Component.text("百炼成钢")),false), iron_chestplate.class,Set.of(ArtifactChestPlateType.DIAMOND_CHESTPLATE),100);
    public static final ArtifactChestPlateType LEATHER_CHESTPLATE = new ArtifactChestPlateType(1,createItemStack(Material.LEATHER_CHESTPLATE,1,"皮革胸甲",List.of(Component.text("旅行者的最爱")),false), leather_chestplate.class,Set.of(ArtifactChestPlateType.IRON_CHESTPLATE),0);
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
        return 5;
    }
    public static ArtifactChestPlateType getChestplate(Integer id){
        return switch (id) {
            case 1 -> ArtifactChestPlateType.LEATHER_CHESTPLATE;
            case 2 -> ArtifactChestPlateType.IRON_CHESTPLATE;
            case 3 -> ArtifactChestPlateType.DIAMOND_CHESTPLATE;
            case 4 -> ArtifactChestPlateType.NETHERITE_CHESTPLATE;
            case 5 -> ArtifactChestPlateType.SUPER_NETHERITE_CHESTPLATE;
            default -> null;
        };
    }
}
