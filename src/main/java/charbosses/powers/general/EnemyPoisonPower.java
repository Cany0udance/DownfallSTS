package charbosses.powers.general;

import charbosses.actions.unique.EnemyPoisonDamageAction;
import charbosses.bosses.AbstractCharBoss;
import charbosses.relics.CBR_SneckoSkull;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

public class EnemyPoisonPower extends AbstractPower {
    public static final String POWER_ID = "Poison";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private AbstractCreature source;

    public EnemyPoisonPower(AbstractCreature owner, AbstractCreature source, int poisonAmt) {
        this.name = NAME;
        this.ID = "Poison";
        this.owner = owner;
        this.source = source;
        this.amount = poisonAmt;
        if( AbstractCharBoss.boss.hasRelic(CBR_SneckoSkull.ID) ){
            this.amount++;
        }
        if (this.amount >= 9999) {
            this.amount = 9999;
        }

        this.updateDescription();
        this.loadRegion("poison");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;  // without the positive check, your antidote will reduce 1 less, because it will apply a negative amount, which would get + 1
        if( (stackAmount >= 0) && AbstractCharBoss.boss.hasRelic(CBR_SneckoSkull.ID) ){
            this.amount ++;
        }
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flashWithoutSound();
                this.addToBot(new EnemyPoisonDamageAction(this.owner, this.source, this.amount, AttackEffect.POISON));
                //Poison reduction/removal handled in damage action
            }

        }
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Poison");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
