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

public class super_freeze_sword extends ArtifactMainWeaponFather {
    private int attack;
    private int fight;
    public super_freeze_sword(Player player, Integer slot) {
        super(player, slot);
        this.attack=0;
        this.fight=0;
        ItemStack itemStack=ItemStack.of(Material.GOLDEN_SWORD);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.displayName(Component.text("极冰"));
        itemMeta.lore(List.of(Component.text("冰冻 V", TextColor.color(168,168,168))));
        itemMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        itemMeta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(ArtifactFight.getMainClass(), UUID.randomUUID().toString()),0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
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
            effectFather=ArtifactEffectType.FREEZE.createEffect((Player) event.getEntity(),200,Math.min(10,this.attack/3),this.getPlayer());
        }
        else{
            if(effectFather.getAmplifier()<Math.min(10,this.attack/3)){
                effectFather.setAmplifier(Math.min(10,this.attack/3));
                effectFather.setDuration(200);
                effectFather.setCausePlayer(this.getPlayer());
            }
            else if(effectFather.getAmplifier()==Math.min(10,this.attack/3)){
                effectFather.setDuration(Math.max(effectFather.getDuration(),200));
                effectFather.setCausePlayer(this.getPlayer());
            }
        }
        hashMap.remove(ArtifactEffectType.FREEZE.getId());
        hashMap.put(ArtifactEffectType.FREEZE.getId(), effectFather);
        PlayerArtifactMap.EffectMap.remove(event.getEntity().getUniqueId());
        PlayerArtifactMap.EffectMap.put(event.getEntity().getUniqueId(),hashMap);
        this.fight=40;
        this.attack++;
    }

    @Override
    public void run() {
        if(!Objects.equals(this.getPlayer().getInventory().getItem(this.getSlot()), this.getItemStack())) this.getPlayer().getInventory().setItem(this.getSlot(),this.getItemStack());
        if(this.fight>0){
            this.fight--;
        }
        else{
            this.attack=0;
        }
    }
}
