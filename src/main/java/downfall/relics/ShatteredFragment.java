package downfall.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardPowerTip;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.cards.BloodySacrifice;
import downfall.downfallMod;

public class ShatteredFragment extends CustomRelic {

    public static final String ID = downfallMod.makeID("ShatteredFragment");
    private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/WingShiv.png"));
    private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/WingShiv.png"));

    public ShatteredFragment() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);
        tips.add(new CardPowerTip( new BloodySacrifice() ) );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

//    @Override
//    public void atTurnStart() {
//        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new MakeTempCardInHandAction(new CrystalShiv(), 1, false));
//    }
    @Override
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 24
        this.addToBot(new MakeTempCardInHandAction(new BloodySacrifice()));// 25
    }

}
