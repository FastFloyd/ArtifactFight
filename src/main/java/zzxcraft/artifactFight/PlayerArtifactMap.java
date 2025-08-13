package zzxcraft.artifactFight;

import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactFather;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactProjectileFather;

import java.util.HashMap;
import java.util.UUID;

public class PlayerArtifactMap {
    public static HashMap<UUID, HashMap<Integer,ArtifactFather>> ArtifactMap=new HashMap<>();
    public static HashMap<UUID, HashMap<Integer,ArtifactEffectFather>> EffectMap=new HashMap<>();
    public static HashMap<UUID, ArtifactProjectileFather> ProjectileMap=new HashMap<>();
}
