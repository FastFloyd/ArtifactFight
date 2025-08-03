package zzxcraft.artifactFight.Artifact.Type;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Boot.diamond_boot;
import zzxcraft.artifactFight.Artifact.Boot.iron_boot;
import zzxcraft.artifactFight.Artifact.Boot.leather_boot;
import zzxcraft.artifactFight.Artifact.Boot.netherite_boot;
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
    public static final ArtifactBootType NETHERITE_BOOT = new ArtifactBootType(4,createItemStack(Material.NETHERITE_BOOTS,1,"合金靴子",List.of(Component.text("合金铸造"),Component.text("Piece:$1000")),Set.of(Pair.of(Enchantment.FIRE_PROTECTION,4))), netherite_boot.class, Set.of(), 1000);
    public static final ArtifactBootType DIAMOND_BOOT = new ArtifactBootType(3,createItemStack(Material.DIAMOND_BOOTS,1,"钻石靴子",List.of(Component.text("无比坚硬的装甲"),Component.text("Piece:$500")),Set.of()), diamond_boot.class,Set.of(ArtifactBootType.NETHERITE_BOOT),500);
    public static final ArtifactBootType IRON_BOOT = new ArtifactBootType(2,createItemStack(Material.IRON_BOOTS,1,"铁靴子",List.of(Component.text("百炼成钢"),Component.text("Piece:$100")),Set.of()), iron_boot.class,Set.of(ArtifactBootType.DIAMOND_BOOT),100);
    public static final ArtifactBootType LEATHER_BOOT = new ArtifactBootType(1,createItemStack(Material.LEATHER_BOOTS,1,"皮革靴子", List.of(Component.text("旅行者的最爱"),Component.text("Piece:free")),Set.of()), leather_boot.class,Set.of(ArtifactBootType.IRON_BOOT),0);
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
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, Set<Pair<Enchantment,Integer>> EnchSet){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        itemStack1.setItemMeta(itemMeta);
        HashMap<Enchantment,Integer> hashMap=new HashMap<>();
        for(Pair<Enchantment,Integer> pair: EnchSet){
            hashMap.put(pair.getLeft(),pair.getRight());
        }
        itemStack1.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments(hashMap,true));
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
        return 4;
    }
    public static ArtifactBootType getBoot(Integer id){
        return switch (id) {
            case 1 -> ArtifactBootType.LEATHER_BOOT;
            case 2 -> ArtifactBootType.IRON_BOOT;
            case 3 -> ArtifactBootType.DIAMOND_BOOT;
            case 4 -> ArtifactBootType.NETHERITE_BOOT;
            default -> null;
        };
    }
}
