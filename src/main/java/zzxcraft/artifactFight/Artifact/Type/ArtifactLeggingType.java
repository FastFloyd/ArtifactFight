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
import zzxcraft.artifactFight.Artifact.ChestPlate.diamond_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.iron_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.leather_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.netherite_chestplate;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactLeggingFather;
import zzxcraft.artifactFight.Artifact.Legging.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactLeggingType {
    Class<? extends ArtifactLeggingFather> prclass;
    ItemStack itemStack;
    Set<ArtifactLeggingType> children;
    Integer price;
    Integer id;
    public static final ArtifactLeggingType SUPER_NETHERITE_LEGGING = new ArtifactLeggingType(5,createItemStack(Material.NETHERITE_LEGGINGS,1,"不摧护腿",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),Set.of(Pair.of(Enchantment.VANISHING_CURSE,1)),Set.of(ItemFlag.HIDE_ENCHANTS)), super_netherite_legging.class,Set.of(),2000);
    public static final ArtifactLeggingType NETHERITE_LEGGING = new ArtifactLeggingType(4,createItemStack(Material.NETHERITE_LEGGINGS,1,"合金护腿",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),Set.of(Pair.of(Enchantment.VANISHING_CURSE,1)),Set.of(ItemFlag.HIDE_ENCHANTS)), netherite_legging.class, Set.of(ArtifactLeggingType.SUPER_NETHERITE_LEGGING), 1000);
    public static final ArtifactLeggingType DIAMOND_LEGGING = new ArtifactLeggingType(3,createItemStack(Material.DIAMOND_LEGGINGS,1,"钻石护腿",List.of(Component.text("无比坚硬的装甲")),Set.of(),Set.of()), diamond_legging.class,Set.of(ArtifactLeggingType.NETHERITE_LEGGING),500);
    public static final ArtifactLeggingType IRON_LEGGING = new ArtifactLeggingType(2,createItemStack(Material.IRON_LEGGINGS,1,"铁护腿",List.of(Component.text("百炼成钢")),Set.of(),Set.of()), iron_legging.class,Set.of(ArtifactLeggingType.DIAMOND_LEGGING),100);
    public static final ArtifactLeggingType LEATHER_LRGGING = new ArtifactLeggingType(1,createItemStack(Material.LEATHER_LEGGINGS,1,"皮革护腿",List.of(Component.text("旅行者的最爱")),Set.of(),Set.of()), leather_legging.class,Set.of(ArtifactLeggingType.IRON_LEGGING),0);
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
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, Set<Pair<Enchantment,Integer>> EnchSet,Set<ItemFlag> flags){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        HashMap<Enchantment,Integer> hashMap=new HashMap<>();
        for(Pair<Enchantment,Integer> pair: EnchSet){
            hashMap.put(pair.getLeft(), pair.getRight());
        }
        itemStack1.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments(hashMap, true));
        for (ItemFlag itemFlag : flags) {
            itemMeta.addItemFlags(itemFlag);
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
        return 5;
    }
    public static ArtifactLeggingType getLegging(Integer id){
        return switch (id) {
            case 1 -> ArtifactLeggingType.LEATHER_LRGGING;
            case 2 -> ArtifactLeggingType.IRON_LEGGING;
            case 3 -> ArtifactLeggingType.DIAMOND_LEGGING;
            case 4 -> ArtifactLeggingType.NETHERITE_LEGGING;
            case 5 -> ArtifactLeggingType.SUPER_NETHERITE_LEGGING;
            default -> null;
        };
    }
}
