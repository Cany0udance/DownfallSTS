package charbosses.relics;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Girya;
import evilWithin.EvilWithinMod;

import java.util.ArrayList;

public class CBR_Girya extends AbstractCharbossRelic {
    public static final String ID = "Girya";

    public CBR_Girya() {
        super(new Girya());
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + CardCrawlGame.languagePack.getRelicStrings(EvilWithinMod.makeID(ID)).DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = AbstractDungeon.actNum;
        if (this.counter != 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractCharBoss.boss, AbstractCharBoss.boss, new StrengthPower(AbstractCharBoss.boss, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractCharBoss.boss, this));
        }
    }

    @Override
    public void onEquip() {
        this.owner.damage(new DamageInfo(this.owner, MathUtils.floor(this.owner.maxHealth * 0.15F), DamageInfo.DamageType.HP_LOSS));

    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> groupToModify, int actIndex) {
        for (int i = actIndex; i < 3; i++) {
            this.owner.chosenArchetype.cardUpgradesPerAct[i] -= 1;
        }

    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_Girya();
    }
}
