package automaton.cards;

import automaton.FunctionHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ForceShield extends AbstractBronzeCard {

    public final static String ID = makeID("ForceShield");

    //stupid intellij stuff skill, self, uncommon

    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 4;

    public ForceShield() {
        //This fix should only apply in cases where the card is generated mid-combat
        //Energy cost isn't green because card isn't updated, but couldn't
        //find easy way to update it as soon as it is initialized
        super(ID, 4 - FunctionHelper.functionsCompiledThisCombat, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public static void decrementShields() {
        for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
            if (q instanceof ForceShield) {
                q.updateCost(-1);
            }
        }
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (q instanceof ForceShield) {
                q.updateCost(-1);
            }
        }
        for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
            if (q instanceof ForceShield) {
                q.updateCost(-1);
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}