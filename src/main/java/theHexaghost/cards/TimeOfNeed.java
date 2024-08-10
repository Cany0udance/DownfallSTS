package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.actions.MakeTempCardInHandActionReduceCost;
import theHexaghost.HexaMod;

import static automaton.AutomatonMod.makeBetaCardPath;

public class TimeOfNeed extends AbstractHexaCard {

    public final static String ID = makeID("TimeOfNeed");

    public TimeOfNeed() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        selfRetain = true;
        exhaust = true;
        HexaMod.loadJokeCardImage(this, "TimeOfNeed.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int roll = AbstractDungeon.cardRng.random(0, 99); // adding this because after it's moved to uncommon, I noticed it generates rare powers too frequently.
        AbstractCard q = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
        if(roll >= 80){
            while( q.rarity != CardRarity.RARE ){
                q = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
            }
        }else{
            while( q.rarity == CardRarity.RARE ){
                q = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
            }
        }

//        q.freeToPlayOnce = true;
        this.addToBot(new MakeTempCardInHandActionReduceCost(q));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}