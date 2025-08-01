package zzxcraft.artifactFight;

import org.bukkit.entity.Player;
import zzxcraft.artifactFight.Artifact.Fathers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerArtifactMap {
    public static HashMap<UUID, ArtifactHelmetFather> HelmetPlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactFather> ChestPlatePlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactLeggingFather> LeggingPlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactBootFather> BootPlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactFather> MainWeaponPlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactFather> DeputyWeaponPlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactPropFather> Prop1PlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactPropFather> Prop2PlayerMap=new HashMap<>();
    public static HashMap<UUID, ArtifactPropFather> Prop3PlayerMap=new HashMap<>();
}
