package zzxcraft.artifactFight.Artifact.MainWeapon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactEffectType;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class wither_sword_plus extends ArtifactMainWeaponFather {
    public wither_sword_plus(Player player, Integer slot) {
        super(player, slot);
        ItemStack itemStack=ItemStack.of(Material.STONE_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("加强凋零之剑"));
        itemMeta.lore(List.of(Component.text("凋零 II", TextColor.color(168,168,168))));
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        this.setItemStack(itemStack);
    }

    @Override
    public void OnFight(EntityDamageByEntityEvent event) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HashMap<Integer, ArtifactEffectFather> hashMap= PlayerArtifactMap.EffectMap.get(event.getEntity().getUniqueId());
        if(hashMap==null){
            hashMap=new HashMap<>();
        }
        ArtifactEffectFather effectFather=hashMap.get(ArtifactEffectType.WITHER.getId());
        if(effectFather==null){
            effectFather=ArtifactEffectType.WITHER.createEffect((Player) event.getEntity(),100,1,this.getPlayer());
        }
        else{
            if(effectFather.getAmplifier()<1){
                effectFather.setAmplifier(1);
                effectFather.setDuration(100);
                effectFather.setCausePlayer(this.getPlayer());
            }
            else if(effectFather.getAmplifier()==1){
                effectFather.setDuration(Math.max(effectFather.getDuration(),100));
                effectFather.setCausePlayer(this.getPlayer());
            }
        }
        hashMap.remove(ArtifactEffectType.WITHER.getId());
        hashMap.put(ArtifactEffectType.WITHER.getId(), effectFather);
        PlayerArtifactMap.EffectMap.remove(event.getEntity().getUniqueId());
        PlayerArtifactMap.EffectMap.put(event.getEntity().getUniqueId(),hashMap);
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
