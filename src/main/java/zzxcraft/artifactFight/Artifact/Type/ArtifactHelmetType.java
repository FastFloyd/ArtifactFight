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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Helmet.diamond_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.iron_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.leather_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.netherite_helmet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactHelmetType {
    Class<? extends ArtifactHelmetFather> prclass;
    ItemStack itemStack;
    Set<ArtifactHelmetType> children;
    Integer price;
    Integer id;
    public static ArtifactHelmetType NETHERITE_HELMET=new ArtifactHelmetType(4,createItemStack(Material.NETHERITE_HELMET,1,"下界合金头盔",List.of(Component.text("合金铸造")),Set.of(Pair.of(Enchantment.FIRE_PROTECTION,4))), netherite_helmet.class,Set.of(),1000);
    public static final ArtifactHelmetType DIAMOND_HELMET= new ArtifactHelmetType(3,createItemStack(Material.DIAMOND_HELMET,1,"钻石头盔",List.of(Component.text("无比坚硬的装甲")),Set.of()), diamond_helmet.class,Set.of(ArtifactHelmetType.NETHERITE_HELMET),500);
    public static final ArtifactHelmetType IRON_HELMET = new ArtifactHelmetType(2,createItemStack(Material.IRON_HELMET,1,"铁头盔",List.of(Component.text("百炼成钢")),Set.of()), iron_helmet.class,Set.of(ArtifactHelmetType.DIAMOND_HELMET),100);
    public static final ArtifactHelmetType LEATHER_HELMET = new ArtifactHelmetType(1,createItemStack(Material.LEATHER_HELMET,1,"皮革头盔",List.of(Component.text("旅行者的最爱")),Set.of()), leather_helmet.class,Set.of(ArtifactHelmetType.IRON_HELMET),0);
    public static final ArtifactHelmetType BUY_HELMET= new ArtifactHelmetType(-1,ItemStack.of(Material.BARRIER),ArtifactHelmetFather.class,Set.of(ArtifactHelmetType.LEATHER_HELMET),0);
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
        return 4;
    }
    public static ArtifactHelmetType getHelmet(Integer id){
        return switch (id) {
            case 1 -> ArtifactHelmetType.LEATHER_HELMET;
            case 2 -> ArtifactHelmetType.IRON_HELMET;
            case 3 -> ArtifactHelmetType.DIAMOND_HELMET;
            case 4 -> ArtifactHelmetType.NETHERITE_HELMET;
            default -> null;
        };
    }
}
