package collector.cards;

import collector.powers.LoseHpNextTurnPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.applyToEnemyTop;
import static collector.util.Wiz.atb;

public class SapStrength extends AbstractCollectorCard {
    public final static String ID = makeID(SapStrength.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 24, 8, , , , 

    public SapStrength() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageCallbackAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, (dealt) -> {
            if (dealt > 0) {
                applyToEnemyTop(m, new LoseHpNextTurnPower(m, dealt * 2));
            }
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
