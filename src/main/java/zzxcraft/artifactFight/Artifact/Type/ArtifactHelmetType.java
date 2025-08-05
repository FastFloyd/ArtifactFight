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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactHelmetFather;
import zzxcraft.artifactFight.Artifact.Helmet.*;

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
    public static final ArtifactHelmetType SUPER_NETHERITE_HELMET = new ArtifactHelmetType(5,createItemStack(Material.NETHERITE_HELMET,1,"不摧头盔",List.of(Component.text("不摧 IV", TextColor.color(168,168,168)),Component.text("坚不可摧")),true), super_netherite_helmet.class,Set.of(),2000);
    public static final ArtifactHelmetType NETHERITE_HELMET =new ArtifactHelmetType(4,createItemStack(Material.NETHERITE_HELMET,1,"合金头盔",List.of(Component.text("抗火 IV", TextColor.color(168,168,168)),Component.text("合金铸造")),true), netherite_helmet.class,Set.of(ArtifactHelmetType.SUPER_NETHERITE_HELMET),1000);
    public static final ArtifactHelmetType DIAMOND_HELMET = new ArtifactHelmetType(3,createItemStack(Material.DIAMOND_HELMET,1,"钻石头盔",List.of(Component.text("无比坚硬的装甲")),false), diamond_helmet.class,Set.of(ArtifactHelmetType.NETHERITE_HELMET),500);
    public static final ArtifactHelmetType IRON_HELMET = new ArtifactHelmetType(2,createItemStack(Material.IRON_HELMET,1,"铁头盔",List.of(Component.text("百炼成钢")),false), iron_helmet.class,Set.of(ArtifactHelmetType.DIAMOND_HELMET),100);
    public static final ArtifactHelmetType LEATHER_HELMET = new ArtifactHelmetType(1,createItemStack(Material.LEATHER_HELMET,1,"皮革头盔",List.of(Component.text("旅行者的最爱")),false), leather_helmet.class,Set.of(ArtifactHelmetType.IRON_HELMET),0);
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
        return 5;
    }
    public static ArtifactHelmetType getHelmet(Integer id){
        return switch (id) {
            case 1 -> ArtifactHelmetType.LEATHER_HELMET;
            case 2 -> ArtifactHelmetType.IRON_HELMET;
            case 3 -> ArtifactHelmetType.DIAMOND_HELMET;
            case 4 -> ArtifactHelmetType.NETHERITE_HELMET;
            case 5 -> ArtifactHelmetType.SUPER_NETHERITE_HELMET;
            default -> null;
        };
    }
}
