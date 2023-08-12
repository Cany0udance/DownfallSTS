package collector.cards;

import collector.actions.DrawCardFromCollectionAction;
import collector.actions.GainReservesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.atb;

public class DragonsTrove extends AbstractCollectorCard {
    public final static String ID = makeID(DragonsTrove.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 2, 1

    public DragonsTrove() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++) {
            atb(new DrawCardFromCollectionAction());
        }
        atb(new GainReservesAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}