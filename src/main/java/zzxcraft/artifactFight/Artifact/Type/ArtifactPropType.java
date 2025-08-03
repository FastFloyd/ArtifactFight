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
import org.checkerframework.framework.qual.DefaultQualifier;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactPropFather;
import zzxcraft.artifactFight.Artifact.Helmet.diamond_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.iron_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.leather_helmet;
import zzxcraft.artifactFight.Artifact.Helmet.netherite_helmet;
import zzxcraft.artifactFight.Artifact.Prop.egg;
import zzxcraft.artifactFight.Artifact.Prop.firework_rocket;
import zzxcraft.artifactFight.Artifact.Prop.snowball;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactPropType {
    Class<? extends ArtifactPropFather> prclass;
    ItemStack itemStack;
    Set<ArtifactPropType> children;
    Integer price;
    Integer id;
    public static final ArtifactPropType EGG = new ArtifactPropType(3,createItemStack(Material.EGG,16,"鸡蛋",List.of(Component.text("进阶的投掷物"),Component.text("Piece:50")),Set.of()), egg.class,Set.of(),50);
    public static final ArtifactPropType FIREWORK_ROCKET = new ArtifactPropType(2,createItemStack(Material.FIREWORK_ROCKET,16,"烟花火箭",List.of(Component.text("飞行员的必备物，驽箭手的最爱"),Component.text("Piece:100")),Set.of()), firework_rocket.class,Set.of(),100);
    public static final ArtifactPropType SNOWBALL = new ArtifactPropType(1,createItemStack(Material.SNOWBALL,16,"雪球", List.of(Component.text("简单的投掷物"),Component.text("Piece:free")),Set.of()), snowball.class,Set.of(ArtifactPropType.EGG),0);
    public static final ArtifactPropType BUY_PROP= new ArtifactPropType(-1,ItemStack.of(Material.BARRIER), ArtifactPropFather.class,Set.of(ArtifactPropType.SNOWBALL,ArtifactPropType.FIREWORK_ROCKET),0);
    private ArtifactPropType(Integer id,ItemStack itemStack,Class<? extends ArtifactPropFather> prclass,Set<ArtifactPropType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;

    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactPropFather createRunnable(Player player,Integer slot) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class,Integer.class).newInstance(player,slot);
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
    public Set<ArtifactPropType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getPropSize(){
        return 3;
    }
    public static ArtifactPropType getProp(Integer id){
        return switch (id) {
            case 1 -> ArtifactPropType.SNOWBALL;
            case 2 -> ArtifactPropType.FIREWORK_ROCKET;
            case 3 -> ArtifactPropType.EGG;
            default -> null;
        };
    }
}
