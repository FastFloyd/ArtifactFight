package zzxcraft.artifactFight.Artifact.MainWeapon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactMainWeaponFather;
import zzxcraft.artifactFight.Artifact.Type.ArtifactEffectType;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.PlayerArtifactMap;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class freeze_sword extends ArtifactMainWeaponFather {
    public freeze_sword(Player player, Integer slot) {
        super(player, slot);
        ItemStack itemStack=ItemStack.of(Material.GOLDEN_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("寒冰之剑"));
        itemMeta.lore(List.of(Component.text("冰冻 I", TextColor.color(168,168,168))));
        itemMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        itemMeta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),-0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
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
        ArtifactEffectFather effectFather=hashMap.get(ArtifactEffectType.FREEZE.getId());
        if(effectFather==null){
            effectFather=ArtifactEffectType.FREEZE.createEffect((Player) event.getEntity(),30,1,this.getPlayer());
        }
        else{
            if(effectFather.getAmplifier()<1){
                effectFather.setAmplifier(1);
                effectFather.setDuration(30);
                effectFather.setCausePlayer(this.getPlayer());
            }
            else if(effectFather.getAmplifier()==1){
                effectFather.setDuration(Math.max(effectFather.getDuration(),30));
                effectFather.setCausePlayer(this.getPlayer());
            }
        }
        hashMap.remove(ArtifactEffectType.FREEZE.getId());
        hashMap.put(ArtifactEffectType.FREEZE.getId(), effectFather);
        PlayerArtifactMap.EffectMap.remove(event.getEntity().getUniqueId());
        PlayerArtifactMap.EffectMap.put(event.getEntity().getUniqueId(),hashMap);
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
    }
}
