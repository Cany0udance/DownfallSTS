package charbosses.relics;

import charbosses.bosses.AbstractCharBoss;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class CBR_LizardTail extends AbstractCharbossRelic {
    public static final String ID = "LizardTail";

    public CBR_LizardTail() {
        super(new LizardTail());
        this.tier = RelicTier.BOSS;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void setCounter(final int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
            this.grayscale = true;
        }
    }

    @Override
    public void onTrigger() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractCharBoss.boss, this));
        int healAmt = AbstractCharBoss.boss.maxHealth / 2;
        if (healAmt < 1) {
            healAmt = 1;
        }
        AbstractCharBoss.boss.heal(healAmt, true);
        AbstractDungeon.effectsQueue.add(new HealEffect(this.hb.cX, this.hb.cY, healAmt));

        this.setCounter(-2);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_LizardTail();
    }
}
