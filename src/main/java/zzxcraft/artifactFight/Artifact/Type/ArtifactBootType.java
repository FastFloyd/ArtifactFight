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
import zzxcraft.artifactFight.Artifact.Boot.*;
import zzxcraft.artifactFight.Artifact.ChestPlate.diamond_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.iron_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.leather_chestplate;
import zzxcraft.artifactFight.Artifact.ChestPlate.netherite_chestplate;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactChestPlateFather;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactBootType {
    Class<? extends ArtifactBootFather> prclass;
    ItemStack itemStack;
    Set<ArtifactBootType> children;
    Integer price;
    Integer id;
    public static final ArtifactBootType SUPER_NETHERITE_BOOT = new ArtifactBootType(5,createItemStack(Material.NETHERITE_BOOTS,1,"不摧靴子",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),Set.of(Pair.of(Enchantment.VANISHING_CURSE,1)),Set.of(ItemFlag.HIDE_ENCHANTS)), super_netherite_boot.class,Set.of(),2000);
    public static final ArtifactBootType NETHERITE_BOOT = new ArtifactBootType(4,createItemStack(Material.NETHERITE_BOOTS,1,"合金靴子",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),Set.of(Pair.of(Enchantment.VANISHING_CURSE,1)),Set.of(ItemFlag.HIDE_ENCHANTS)), netherite_boot.class, Set.of(ArtifactBootType.SUPER_NETHERITE_BOOT), 1000);
    public static final ArtifactBootType DIAMOND_BOOT = new ArtifactBootType(3,createItemStack(Material.DIAMOND_BOOTS,1,"钻石靴子",List.of(Component.text("无比坚硬的装甲")),Set.of(),Set.of()), diamond_boot.class,Set.of(ArtifactBootType.NETHERITE_BOOT),500);
    public static final ArtifactBootType IRON_BOOT = new ArtifactBootType(2,createItemStack(Material.IRON_BOOTS,1,"铁靴子",List.of(Component.text("百炼成钢")),Set.of(),Set.of()), iron_boot.class,Set.of(ArtifactBootType.DIAMOND_BOOT),100);
    public static final ArtifactBootType LEATHER_BOOT = new ArtifactBootType(1,createItemStack(Material.LEATHER_BOOTS,1,"皮革靴子", List.of(Component.text("旅行者的最爱")),Set.of(),Set.of()), leather_boot.class,Set.of(ArtifactBootType.IRON_BOOT),0);
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
        return 5;
    }
    public static ArtifactBootType getBoot(Integer id){
        return switch (id) {
            case 1 -> ArtifactBootType.LEATHER_BOOT;
            case 2 -> ArtifactBootType.IRON_BOOT;
            case 3 -> ArtifactBootType.DIAMOND_BOOT;
            case 4 -> ArtifactBootType.NETHERITE_BOOT;
            case 5 -> ArtifactBootType.SUPER_NETHERITE_BOOT;
            default -> null;
        };
    }
}
