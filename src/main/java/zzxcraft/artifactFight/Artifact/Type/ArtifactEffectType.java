package zzxcraft.artifactFight.Artifact.Type;

import org.bukkit.entity.Player;
import zzxcraft.artifactFight.Artifact.Effect.freeze;
import zzxcraft.artifactFight.Artifact.Effect.wither;
import zzxcraft.artifactFight.Artifact.Fathers.ArtifactEffectFather;

import java.lang.reflect.InvocationTargetException;

public class ArtifactEffectType {
    Class<? extends ArtifactEffectFather> prclass;
    Integer id;
    public static final ArtifactEffectType FREEZE = new ArtifactEffectType(2, freeze.class);
    public static final ArtifactEffectType WITHER = new ArtifactEffectType(1, wither.class);
    private ArtifactEffectType(Integer id,Class<? extends ArtifactEffectFather> prclass){
        this.prclass=prclass;
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }
    public ArtifactEffectFather createEffect(Player player,Integer duration,Integer amplifier,Player causePlayer) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(prclass==null) return null;
        return prclass.getConstructor(Player.class,Integer.class,Integer.class,Player.class).newInstance(player,duration,amplifier,causePlayer);
    }
    public static Integer getEffectSize(){
        return 2;
    }
    public static ArtifactEffectType getEffect(Integer id){
        return switch (id){
            case 1 -> ArtifactEffectType.WITHER;
            case 2 -> ArtifactEffectType.FREEZE;
            default -> null;
        };
    }
}
