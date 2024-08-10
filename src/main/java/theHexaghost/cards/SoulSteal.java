package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.LivingBombPower;

public class SoulSteal extends AbstractHexaCard {

    public final static String ID = makeID("LivingBomb");

    //living bomb

    public SoulSteal() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBurn = burn = 4;
        HexaMod.loadJokeCardImage(this, "SoulSteal.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        burn(m, burn);
        applyToEnemy(m, new LivingBombPower(m, magicNumber));

    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBurn(3);
        }
    }
}