package theHexaghost.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theHexaghost.GhostflameHelper;
import theHexaghost.HexaMod;
import theHexaghost.ghostflames.AbstractGhostflame;
import theHexaghost.ghostflames.MayhemGhostflame;
import downfall.util.TextureLoader;

import static theHexaghost.HexaMod.makeRelicOutlinePath;
import static theHexaghost.HexaMod.makeRelicPath;

public class SoulOfChaos extends CustomRelic {

    public static final String ID = HexaMod.makeID("SoulOfChaos");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SoulOfChaos.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SoulOfChaos.png"));

    public SoulOfChaos() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractGhostflame q = GhostflameHelper.hexaGhostFlames.get(3);
        AbstractGhostflame gf = new MayhemGhostflame(q.lx, q.ly);
        GhostflameHelper.hexaGhostFlames.set(3, gf);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
