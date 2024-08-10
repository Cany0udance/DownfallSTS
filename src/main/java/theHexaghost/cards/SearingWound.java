package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnPower;

public class SearingWound extends AbstractHexaCard {

    public final static String ID = makeID("SearingWound");

    public SearingWound() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.exhaust = true;
        HexaMod.loadJokeCardImage(this, "SearingWound.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!q.isDying && q.hasPower(BurnPower.POWER_ID)) {
                atb(new LoseHPAction(q, p, q.getPower(BurnPower.POWER_ID).amount, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }
}