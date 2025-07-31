package zzxcraft.artifactFight;

import org.bukkit.entity.Player;
import zzxcraft.artifactFight.Artifact.Fathers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerArtifactMap {
    public static HashMap<UUID, ArtifactHelmetFather> HelmetPlayerMap;
    public static HashMap<UUID, ArtifactChestPlateFather> ChestPlatePlayerMap;
    public static HashMap<UUID, ArtifactLeggingFather> LeggingPlayerMap;
    public static HashMap<UUID, ArtifactBootFather> BootPlayerMap;
    public static HashMap<UUID, ArtifactMainWeaponFather> WeaponPlayerMap;
    public static HashMap<UUID,ArtifactBowFather> BowPlayerMap;
    public static HashMap<UUID,ArtifactShieldFather> ShieldPlayerMap;
    public static HashMap<UUID,ArtifactPropFather> Prop1PlayerMap;
    public static HashMap<UUID,ArtifactPropFather> Prop2PlayerMap;
    public static HashMap<UUID,ArtifactPropFather> Prop3PlayerMap;
    public static HashMap<UUID,ArtifactElytraFather> ElytraPlayerMap;
}
