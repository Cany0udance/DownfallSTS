package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.GiftsFromTheDeadPower;

public class GiftsFromTheDead extends AbstractHexaCard {

    public final static String ID = makeID("GiftsFromBeyond");

    public GiftsFromTheDead() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = baseMagicNumber = 1;
        HexaMod.loadJokeCardImage(this, "GiftsFromTheDead.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GiftsFromTheDeadPower(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}