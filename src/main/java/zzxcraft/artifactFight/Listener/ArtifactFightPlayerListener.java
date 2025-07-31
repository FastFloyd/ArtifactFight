package zzxcraft.artifactFight.Listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;
import zzxcraft.artifactFight.Artifact.Type.*;
import zzxcraft.artifactFight.ArtifactFight;
import zzxcraft.artifactFight.Inventory.ChooseInventory.*;
import zzxcraft.artifactFight.Inventory.BuyInventory.*;
import zzxcraft.artifactFight.Inventory.GetInventory.*;

public class ArtifactFightPlayerListener implements Listener {
    private final static JavaPlugin javaPlugin = ArtifactFight.getMainClass();
    private final FileConfiguration config =ArtifactFight.getMainClass().getConfig();
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent playerJoinEvent){
        Player player = playerJoinEvent.getPlayer();
        PersistentDataContainer persistentDataType=player.getPersistentDataContainer();
        if(persistentDataType.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)==null){
            persistentDataType.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,0);
        }
        player.teleport(new Location(javaPlugin.getServer().getWorld("world"),
                javaPlugin.getConfig().getDouble("spawnWait.x"),
                javaPlugin.getConfig().getDouble("spawnWait.y"),
                javaPlugin.getConfig().getDouble("spawnWait.z")
        ));
        for(String string : player.getScoreboardTags()){
            player.removeScoreboardTag(string);
        }
        player.addScoreboardTag("onWait");
    }

    @EventHandler
    public void PlayerDie(PlayerDeathEvent playerDeathEvent){
        Player player = playerDeathEvent.getPlayer();
        player.teleport(new Location(javaPlugin.getServer().getWorld("world"),
                javaPlugin.getConfig().getDouble("spawnWait.x"),
                javaPlugin.getConfig().getDouble("spawnWait.y"),
                javaPlugin.getConfig().getDouble("spawnWait.z")
        ));
        for(String string : player.getScoreboardTags()){
            player.removeScoreboardTag(string);
        }
        player.addScoreboardTag("onWait");
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent blockPlaceEvent){
        if(!blockPlaceEvent.getPlayer().isOp()){
            blockPlaceEvent.getPlayer().sendMessage(Component.text("你没有这样做的权限",TextColor.color(255,0,0)));
            blockPlaceEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerUse(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();
        if(playerInteractEvent.getItem()==null) return;
        ItemStack itemStack = playerInteractEvent.getItem();
        if(playerInteractEvent.getItem().getItemMeta().hasDisplayName()){
            String itemName=ArtifactFight.ComponentToString(itemStack.displayName());
            if(itemName.equals("加入游戏") && itemStack.getType().equals(Material.COMPASS)){
                player.sendMessage("§4GoGoGo出发喽");
                player.getInventory().clear();
                player.teleport(new Location(ArtifactFight.getMainClass().getServer().getWorld("world"),
                        config.getDouble("spawnFight.x"),
                        config.getDouble("spawnFight.y"),
                        config.getDouble("spawnFight.z")));
                for(String string : player.getScoreboardTags()){
                    player.removeScoreboardTag(string);
                }
                player.addScoreboardTag("onPlay");
            }
            else if(itemName.equals("选择装备") && itemStack.getType().equals(Material.CHEST)){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else if(itemName.equals("购买武器") && itemStack.getType().equals(Material.ANVIL)){
                player.openInventory(new BuyItemMainInventory(player).getInventory());
            }
        }
    }

    @EventHandler
    public void PlayerClick(InventoryClickEvent inventoryClickEvent){
        if(!inventoryClickEvent.getWhoClicked().isOp()){
            inventoryClickEvent.setCancelled(true);
        }
        Inventory inventory = inventoryClickEvent.getClickedInventory();
        if(inventory == null) return;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if(inventoryHolder == null) return;
        Player player= (Player) inventoryClickEvent.getWhoClicked();
        int slot=inventoryClickEvent.getSlot();
        if(inventoryHolder instanceof ChooseItemInventory){
            switch (slot){
                case 0: player.openInventory(new ChooseHelmetInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 1: player.openInventory(new ChooseChestPlateInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 2: player.openInventory(new ChooseLeggingInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 3: player.openInventory(new ChooseBootInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 4: player.openInventory(new ChooseMainWeaponInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 5: player.openInventory(new ChooseDeputyWeaponInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 6: player.openInventory(new ChooseFirstPropInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 7: player.openInventory(new ChooseSecondPropInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                case 8: player.openInventory(new ChooseThirdPropInventory(player, (ChooseItemInventory) inventoryHolder).getInventory());break;
                default:break;
            }
        }
        else if(inventoryHolder instanceof BuyItemMainInventory){
            switch (slot){
                case 0 : player.openInventory(new BuyHelmetInventory(player, ArtifactHelmetType.BUY_HELMET,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 1 : player.openInventory(new BuyChestPlateInventory(player, ArtifactChestPlateType.BUY_CHESTPLATE,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 2 : player.openInventory(new BuyLeggingInventory(player, ArtifactLeggingType.BUY_LEGGING,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 3 : player.openInventory(new BuyBootInventory(player, ArtifactBootType.BUY_BOOT,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 4 : player.openInventory(new BuyMainWeaponInventory(player, ArtifactMainWeaponType.BUY_WEAPON,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 5 : player.openInventory(new BuyBowInventory(player, ArtifactBowType.BUY_BOW,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 6 : player.openInventory(new BuyPropInventory(player, ArtifactPropType.BUY_PROP,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 7 : player.openInventory(new BuyElytraInventory(player, ArtifactElytraType.BUY_ELYTRA,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                case 8 : player.openInventory(new BuyShieldInventory(player, ArtifactShieldType.BUY_SHIELD,(BuyItemMainInventory) inventoryHolder).getInventory());break;
                default:break;
            }
        }
        else if((inventoryHolder instanceof ChooseHelmetInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseHelmetInventory) inventoryHolder).getHelmetType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_helmet"),PersistentDataType.INTEGER, integer);
            }
        }
        else if((inventoryHolder instanceof ChooseChestPlateInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Object o=((ChooseChestPlateInventory) inventoryHolder).getChestPlateType(slot);
                if(o instanceof ArtifactChestPlateType){
                    Integer integer=((ArtifactChestPlateType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_chestplate"),PersistentDataType.INTEGER,integer*10+1);
                }
                else{
                    Integer integer=((ArtifactElytraType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_elytra"),PersistentDataType.INTEGER,integer*10+2);
                }
            }
        }
        else if((inventoryHolder instanceof ChooseLeggingInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseLeggingInventory) inventoryHolder).getLeggingType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_legging"),PersistentDataType.INTEGER,integer);
            }
        }
        else if((inventoryHolder instanceof ChooseBootInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseBootInventory) inventoryHolder).getBootType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_boot"),PersistentDataType.INTEGER,integer);
            }
        }
        else if((inventoryHolder instanceof ChooseMainWeaponInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Object o=((ChooseMainWeaponInventory)inventoryHolder).getMainWeaponType(slot);
                if(o instanceof ArtifactMainWeaponType){
                    Integer integer=((ArtifactMainWeaponType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_mainweapon"),PersistentDataType.INTEGER,integer*10+1);
                }
                else if(o instanceof ArtifactBowType){
                    Integer integer=((ArtifactBowType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_mainweapon"),PersistentDataType.INTEGER,integer*10+2);
                }
                else if(o instanceof ArtifactShieldType){
                    Integer integer=((ArtifactShieldType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_mainweapon"),PersistentDataType.INTEGER,integer*10+3);
                }
            }
        }
        else if((inventoryHolder instanceof ChooseDeputyWeaponInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Object o=((ChooseDeputyWeaponInventory)inventoryHolder).getDeputyWeaponType(slot);
                if(o instanceof ArtifactMainWeaponType){
                    Integer integer=((ArtifactMainWeaponType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_deputyweapon"),PersistentDataType.INTEGER,integer*10+1);
                }
                else if(o instanceof ArtifactBowType){
                    Integer integer=((ArtifactBowType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_deputyweapon"),PersistentDataType.INTEGER,integer*10+2);
                }
                else if(o instanceof ArtifactShieldType){
                    Integer integer=((ArtifactShieldType) o).getId();
                    if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_deputyweapon"),PersistentDataType.INTEGER,integer*10+3);
                }
            }
        }
        else if((inventoryHolder instanceof ChooseFirstPropInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseFirstPropInventory) inventoryHolder).getPropType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_firstprop"),PersistentDataType.INTEGER,integer);
            }
        }
        else if((inventoryHolder instanceof ChooseSecondPropInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseSecondPropInventory) inventoryHolder).getPropType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_secondprop"),PersistentDataType.INTEGER,integer);
            }
        }
        else if((inventoryHolder instanceof ChooseThirdPropInventory)){
            if(slot==49){
                player.openInventory(new ChooseItemInventory(player).getInventory());
            }
            else{
                Integer integer=((ChooseThirdPropInventory) inventoryHolder).getPropType(slot).getId();
                if(integer!=null) player.getPersistentDataContainer().set(new NamespacedKey(javaPlugin,"chose_thirdprop"),PersistentDataType.INTEGER,integer);
            }
        }
        else if((inventoryHolder instanceof BuyHelmetInventory)){
            if(slot==49){
                player.openInventory(((BuyHelmetInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyHelmetInventory buyHelmetInventory=(BuyHelmetInventory) inventoryHolder;
                ArtifactHelmetType artifactHelmetType=buyHelmetInventory.getHelmetType(slot);
                if(artifactHelmetType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey= new NamespacedKey(javaPlugin,"bought_helmet"+ artifactHelmetType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey, PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetHelmetInventory(player,artifactHelmetType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyHelmetInventory(player,artifactHelmetType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyChestPlateInventory)){
            if(slot==49){
                player.openInventory(((BuyChestPlateInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyChestPlateInventory buyChestPlateInventory=(BuyChestPlateInventory) inventoryHolder;
                ArtifactChestPlateType artifactChestPlateType=buyChestPlateInventory.getChestPlateType(slot);
                if(artifactChestPlateType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_chestplate"+ artifactChestPlateType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetChestPlateInventory(player,artifactChestPlateType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyChestPlateInventory(player,artifactChestPlateType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyLeggingInventory)){
            if(slot==49){
                player.openInventory(((BuyLeggingInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyLeggingInventory buyLeggingInventory=(BuyLeggingInventory) inventoryHolder;
                ArtifactLeggingType artifactLeggingType=buyLeggingInventory.getLeggingType(slot);
                if(artifactLeggingType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_legging"+ artifactLeggingType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetLeggingInventory(player,artifactLeggingType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyLeggingInventory(player,artifactLeggingType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyBootInventory)){
            if(slot==49){
                player.openInventory(((BuyBootInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyBootInventory buyBootInventory=(BuyBootInventory) inventoryHolder;
                ArtifactBootType artifactBootType=buyBootInventory.getBootType(slot);
                if(artifactBootType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_boot"+ artifactBootType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetBootInventory(player,artifactBootType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyBootInventory(player,artifactBootType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyMainWeaponInventory)){
            if(slot==49){
                player.openInventory(((BuyMainWeaponInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyMainWeaponInventory buyMainWeaponInventory=(BuyMainWeaponInventory) inventoryHolder;
                ArtifactMainWeaponType artifactMainWeaponType=buyMainWeaponInventory.getMainWeaponType(slot);
                if(artifactMainWeaponType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_mainweapon"+ artifactMainWeaponType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetMainWeaponInventory(player,artifactMainWeaponType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyMainWeaponInventory(player,artifactMainWeaponType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyBowInventory)){
            if(slot==49){
                player.openInventory(((BuyBowInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyBowInventory buyBowInventory=(BuyBowInventory) inventoryHolder;
                ArtifactBowType artifactBowType=buyBowInventory.getBowType(slot);
                if(artifactBowType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_bow"+ artifactBowType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetBowInventory(player,artifactBowType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyBowInventory(player,artifactBowType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyShieldInventory)){
            if(slot==49){
                player.openInventory(((BuyShieldInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyShieldInventory buyShieldInventory=(BuyShieldInventory) inventoryHolder;
                ArtifactShieldType artifactShieldType=buyShieldInventory.getShieldType(slot);
                if(artifactShieldType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_shield"+ artifactShieldType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetShieldInventory(player,artifactShieldType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyShieldInventory(player,artifactShieldType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyPropInventory)){
            if(slot==49){
                player.openInventory(((BuyPropInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyPropInventory buyPropInventory=(BuyPropInventory) inventoryHolder;
                ArtifactPropType artifactPropType=buyPropInventory.getPropType(slot);
                if(artifactPropType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_prop"+ artifactPropType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetPropInventory(player,artifactPropType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyPropInventory(player,artifactPropType,inventoryHolder).getInventory());
                }
            }
        }
        else if((inventoryHolder instanceof BuyElytraInventory)){
            if(slot==49){
                player.openInventory(((BuyElytraInventory) inventoryHolder).getSuperInventory().getInventory());
            }
            else{
                BuyElytraInventory buyElytraInventory=(BuyElytraInventory) inventoryHolder;
                ArtifactElytraType artifactElytraType=buyElytraInventory.getElytraType(slot);
                if(artifactElytraType==null){
                    return;
                }
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                NamespacedKey namespacedKey=new NamespacedKey(javaPlugin,"bought_elytra"+ artifactElytraType.getId());
                Boolean c=persistentDataContainer.get(namespacedKey,PersistentDataType.BOOLEAN);
                if(c==null){
                    player.openInventory(new GetElytraInventory(player,artifactElytraType,inventoryHolder).getInventory());
                }
                else{
                    player.openInventory(new BuyElytraInventory(player,artifactElytraType,inventoryHolder).getInventory());
                }
            }
        }
        else if(inventoryHolder instanceof GetHelmetInventory){
            if(slot==0){
                player.openInventory(((GetHelmetInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetHelmetInventory) inventoryHolder).getArtifactHelmetType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetHelmetInventory) inventoryHolder).getArtifactHelmetType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_helmet"+((GetHelmetInventory) inventoryHolder).getArtifactHelmetType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetHelmetInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetChestPlateInventory){
            if(slot==0){
                player.openInventory(((GetChestPlateInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetChestPlateInventory) inventoryHolder).getArtifactChestPlateType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetChestPlateInventory) inventoryHolder).getArtifactChestPlateType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_chestplate"+((GetChestPlateInventory) inventoryHolder).getArtifactChestPlateType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetChestPlateInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetLeggingInventory){
            if(slot==0){
                player.openInventory(((GetLeggingInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetLeggingInventory) inventoryHolder).getArtifactLeggingType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetLeggingInventory) inventoryHolder).getArtifactLeggingType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_legging"+((GetLeggingInventory) inventoryHolder).getArtifactLeggingType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetLeggingInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetBootInventory){
            if(slot==0){
                player.openInventory(((GetBootInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetBootInventory) inventoryHolder).getArtifactBootType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetBootInventory) inventoryHolder).getArtifactBootType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_boot"+((GetBootInventory) inventoryHolder).getArtifactBootType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetBootInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetMainWeaponInventory){
            if(slot==0){
                player.openInventory(((GetMainWeaponInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetMainWeaponInventory) inventoryHolder).getArtifactMainWeaponType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetMainWeaponInventory) inventoryHolder).getArtifactMainWeaponType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_mainWeapon"+((GetMainWeaponInventory) inventoryHolder).getArtifactMainWeaponType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetMainWeaponInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetShieldInventory){
            if(slot==0){
                player.openInventory(((GetShieldInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetShieldInventory) inventoryHolder).getArtifactShieldType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetShieldInventory) inventoryHolder).getArtifactShieldType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_shield"+((GetShieldInventory) inventoryHolder).getArtifactShieldType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetShieldInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetBowInventory){
            if(slot==0){
                player.openInventory(((GetBowInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetBowInventory) inventoryHolder).getArtifactBowType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetBowInventory) inventoryHolder).getArtifactBowType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_bow"+((GetBowInventory) inventoryHolder).getArtifactBowType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetBowInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetPropInventory){
            if(slot==0){
                player.openInventory(((GetPropInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetPropInventory) inventoryHolder).getArtifactPropType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetPropInventory) inventoryHolder).getArtifactPropType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_prop"+((GetPropInventory) inventoryHolder).getArtifactPropType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetPropInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255,0,0)));
                }
            }
        }
        else if(inventoryHolder instanceof GetElytraInventory){
            if(slot==0){
                player.openInventory(((GetElytraInventory) inventoryHolder).getSuper_inventory().getInventory());
            }
            else if(slot==8){
                PersistentDataContainer persistentDataContainer=player.getPersistentDataContainer();
                if(persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER).intValue()>((GetElytraInventory) inventoryHolder).getArtifactElytraType().getPrice()){
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER,persistentDataContainer.get(new NamespacedKey(javaPlugin,"tent"),PersistentDataType.INTEGER)-((GetElytraInventory) inventoryHolder).getArtifactElytraType().getPrice());
                    persistentDataContainer.set(new NamespacedKey(javaPlugin,"bought_elytra"+((GetElytraInventory) inventoryHolder).getArtifactElytraType().getId()),PersistentDataType.BOOLEAN,true);
                    player.openInventory(((GetElytraInventory) inventoryHolder).getSuper_inventory().getInventory());
                }
                else{
                    player.sendMessage(Component.text("你没有足够的钱做这件事！", TextColor.color(255, 0, 0)));
                }
            }
        }
    }
}
