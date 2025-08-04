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
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactBootFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactShieldFather;
import zzxcraft.artifactFight.Artifact.Shield.shield;
import zzxcraft.artifactFight.Artifact.Shield.super_thorn_shield;
import zzxcraft.artifactFight.Artifact.Shield.thorn_shield;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArtifactShieldType {
    Class<? extends ArtifactShieldFather> prclass;
    ItemStack itemStack;
    Set<ArtifactShieldType> children;
    Integer price;
    Integer id;
    public static final ArtifactShieldType SUPER_THORN_SHIELD = new ArtifactShieldType(3,createItemStack(Material.SHIELD,1,"强化荆棘盾牌",List.of(Component.text("在防御同时提供更高输出")),Set.of()), super_thorn_shield.class,Set.of(),750);
    public static final ArtifactShieldType THORN_SHIELD = new ArtifactShieldType(2,createItemStack(Material.SHIELD,1,"荆棘盾牌",List.of(Component.text("在防御同时输出")),Set.of()), thorn_shield.class,Set.of(ArtifactShieldType.SUPER_THORN_SHIELD),250);
    public static final ArtifactShieldType SHIELD = new ArtifactShieldType(1,createItemStack(Material.SHIELD,1,"盾牌", List.of(Component.text("可以完美防御剑和大部分远程武器的伤害")),Set.of()), shield.class,Set.of(ArtifactShieldType.THORN_SHIELD),0);
    public static final ArtifactShieldType BUY_SHIELD= new ArtifactShieldType(-1,ItemStack.of(Material.BARRIER), ArtifactShieldFather.class,Set.of(ArtifactShieldType.SHIELD),0);
    private ArtifactShieldType(Integer id,ItemStack itemStack,Class<? extends ArtifactShieldFather> prclass,Set<ArtifactShieldType> children,Integer price){
        this.id=id;
        this.itemStack=itemStack;
        this.prclass=prclass;
        this.children=children;
        this.price=price;
    }
    public ItemStack getItemStack() {return this.itemStack;}
    public ArtifactShieldFather createRunnable(Player player,Integer slot) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class, Integer.class).newInstance(player,slot);
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
    public Set<ArtifactShieldType> getChildren(){
        return this.children;
    }
    public Integer getPrice(){
        return this.price;
    }
    public Integer getId(){
        return this.id;
    }
    public static Integer getShieldSize(){
        return 3;
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
