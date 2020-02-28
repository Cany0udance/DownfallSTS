package charbosses.relics;

import charbosses.actions.common.EnemyGainEnergyAction;
import charbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.Lantern;
import evilWithin.EvilWithinMod;

import java.util.ArrayList;

public class CBR_FrozenEgg extends AbstractCharbossRelic {
    public static final String ID = "FrozenEgg";
    int numCards = 0;

    public CBR_FrozenEgg() {
        super(new FrozenEgg2());
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + CardCrawlGame.languagePack.getRelicStrings(EvilWithinMod.makeID(ID)).DESCRIPTIONS[0] + this.numCards + CardCrawlGame.languagePack.getRelicStrings(EvilWithinMod.makeID(ID)).DESCRIPTIONS[1];
    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> groupToModify, int actIndex) {
        this.owner.chosenArchetype.upgradeAllPowers = true;
    }

    @Override
    public void onTrigger() {
        numCards ++;
        this.description = this.getUpdatedDescription();
        this.refreshDescription();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_FrozenEgg();
    }
}
