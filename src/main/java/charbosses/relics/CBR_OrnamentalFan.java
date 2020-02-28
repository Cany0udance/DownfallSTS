package charbosses.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.relics.OrnamentalFan;
import evilWithin.EvilWithinMod;

public class CBR_OrnamentalFan extends AbstractCharbossRelic {
    public static final String ID = "OrnamentalFan";


    public CBR_OrnamentalFan() {
        super(new OrnamentalFan());
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 4 + this.DESCRIPTIONS[1];
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++this.counter;
            if (this.counter % 3 == 0) {
                this.flash();
                this.counter = 0;
                this.addToBot(new RelicAboveCreatureAction(this.owner, this));
                this.addToBot(new GainBlockAction(this.owner, this.owner, 4));
            }
        }

    }


    @Override
    public AbstractRelic makeCopy() {
        return new CBR_OrnamentalFan();
    }
}
