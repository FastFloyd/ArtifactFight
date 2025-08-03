package zzxcraft.artifactFight.Artifact.Type;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Bow.bow;
import zzxcraft.artifactFight.Artifact.Bow.power_bow;
import zzxcraft.artifactFight.Artifact.Bow.power_bow_plus;
import zzxcraft.artifactFight.Artifact.Bow.super_power_bow;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBowFather;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class ArtifactBowType {
    Class<? extends ArtifactBowFather> prclass;
    ItemStack itemStack;
    Set<ArtifactBowType> children;
    Integer price;
    Integer id;
    public static final ArtifactBowType SUPER_POWER_BOW = new ArtifactBowType(4,createItemStack(Material.BOW,1,"力量V弓",List.of(Component.text("平明寻白羽，没在石棱中"),Component.text("Piece:$1000")),Set.of(Pair.of(Enchantment.POWER,5))), super_power_bow.class,Set.of(),1000);
    public static final ArtifactBowType POWER_BOW_PLUS = new ArtifactBowType(3,createItemStack(Material.BOW,1,"力量III弓",List.of(Component.text("力大无穷"),Component.text("Piece:$500")),Set.of(Pair.of(Enchantment.POWER,3))), power_bow_plus.class,Set.of(ArtifactBowType.SUPER_POWER_BOW),500);
    public static final ArtifactBowType POWER_BOW = new ArtifactBowType(2,createItemStack(Material.BOW,1,"力量I弓",List.of(Component.text("更强的力量"),Component.text("Piece:$100")),Set.of(Pair.of(Enchantment.POWER,1))), power_bow.class,Set.of(ArtifactBowType.POWER_BOW_PLUS),100);
    public static final ArtifactBowType BOW = new ArtifactBowType(1,createItemStack(Material.BOW,1,"普通弓箭", List.of(Component.text("简单的远程武器"),Component.text("Piece:free")),Set.of()), bow.class,Set.of(ArtifactBowType.POWER_BOW),0);
    public static final ArtifactBowType BUY_BOW= new ArtifactBowType(-1,ItemStack.of(Material.BARRIER), ArtifactBowFather.class,Set.of(ArtifactBowType.BOW),0);
    private ArtifactBowType(Integer id,ItemStack itemStack, Class<? extends ArtifactBowFather> prclass, Set<ArtifactBowType> children, Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactBowFather createRunnable(Player player,Integer slot) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class, Integer.class).newInstance(player,slot);
    }
    private static ItemStack createItemStack(Material material, Integer count, String name, List<Component> lore, Set<Pair<Enchantment,Integer>> EnchSet){
        ItemStack itemStack1=ItemStack.of(material,count);
        ItemMeta itemMeta=itemStack1.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.lore(lore);
        itemStack1.setItemMeta(itemMeta);
        for(Pair<Enchantment,Integer> pair: EnchSet){
            itemStack1.addEnchantment(pair.getLeft(),pair.getRight());
        }
        return itemStack1;
    }
    public Set<ArtifactBowType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static ArtifactBowType getBow(Integer id){
        return switch (id) {
            case 1 -> ArtifactBowType.BOW;
            case 2 -> ArtifactBowType.POWER_BOW;
            case 3 -> ArtifactBowType.POWER_BOW_PLUS;
            case 4 -> ArtifactBowType.SUPER_POWER_BOW;
            default -> null;
        };
    }
}
