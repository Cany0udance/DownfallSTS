package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NightmareStrike extends AbstractHexaCard {

    public final static String ID = makeID("NightmareStrike");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 16;
    private static final int UPG_DAMAGE = 4;

    public NightmareStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        isEthereal = true;
        cardsToPreview = new ShadowStrike();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard q = new ShadowStrike();
        if (upgraded) q.upgrade();
        atb(new MakeTempCardInHandAction(q));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            rawDescription = UPGRADE_DESCRIPTION;
            AbstractCard q = new ShadowStrike();
            q.upgrade();
            cardsToPreview = q;
            initializeDescription();
        }
    }
}