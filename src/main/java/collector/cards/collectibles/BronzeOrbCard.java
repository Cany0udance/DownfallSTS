package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.atb;

public class BronzeOrbCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BronzeOrbCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 2, 5, 2, , 

    public BronzeOrbCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.NONE); //TODO: Beam
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.drawPile.getTopCard().freeToPlayOnce = true;
                //Note: this technically lets you figure out what your top card is by checking draw pile before/after
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}