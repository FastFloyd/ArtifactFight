package zzxcraft.artifactFight.Artifact.Type;

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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;
import zzxcraft.artifactFight.Artifact.Shield.shield;
import zzxcraft.artifactFight.Artifact.Shield.super_thorn_shield;
import zzxcraft.artifactFight.Artifact.Shield.thorn_shield;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class ArtifactShieldType {
    Class<? extends ArtifactShieldFather> prclass;
    ItemStack itemStack;
    Set<ArtifactShieldType> children;
    Integer price;
    Integer id;
    public static final ArtifactShieldType SUPER_THORN_SHIELD = new ArtifactShieldType(3,createItemStack(Material.SHIELD,1,"强化荆棘盾牌",List.of(Component.text("地表最强输出"),Component.text("在防御同时提供更高输出")),Set.of()), super_thorn_shield.class,Set.of(),750);
    public static final ArtifactShieldType THORN_SHIELD = new ArtifactShieldType(2,createItemStack(Material.SHIELD,1,"荆棘盾牌",List.of(Component.text("攻防兼备"),Component.text("在防御同时输出")),Set.of()), thorn_shield.class,Set.of(ArtifactShieldType.SUPER_THORN_SHIELD),250);
    public static final ArtifactShieldType SHIELD = new ArtifactShieldType(1,createItemStack(Material.SHIELD,1,"盾牌", List.of(Component.text("绝对防御"),Component.text("可以完美防御剑和大部分远程武器的伤害")),Set.of()), shield.class,Set.of(ArtifactShieldType.THORN_SHIELD),0);
    public static final ArtifactShieldType BUY_SHIELD= new ArtifactShieldType(-1,ItemStack.of(Material.BARRIER), ArtifactShieldFather.class,Set.of(ArtifactShieldType.SHIELD),0);
    private ArtifactShieldType(Integer id,ItemStack itemStack,Class<? extends ArtifactShieldFather> prclass,Set<ArtifactShieldType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactShieldFather createRunnable(Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class).newInstance(player);
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
    public Set<ArtifactShieldType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static ArtifactShieldType getShield(Integer id){
        return switch (id) {
            case 1 -> ArtifactShieldType.SHIELD;
            case 2 -> ArtifactShieldType.THORN_SHIELD;
            case 3 -> ArtifactShieldType.SUPER_THORN_SHIELD;
            default -> null;
        };
    }
}
