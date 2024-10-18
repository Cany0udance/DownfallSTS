package guardian.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;


public class EvasiveProtocolPower extends AbstractGuardianPower implements DefensiveModeBooster {
    public static final String POWER_ID = "Guardian:EvasiveProtocolPower";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static String[] DESCRIPTIONS;

    public EvasiveProtocolPower(AbstractCreature owner, int amount) {

        this.ID = POWER_ID;
        this.owner = owner;
        this.setImage("EvasiveProtocolPower84.png", "EvasiveProtocolPower32.png");
        this.type = POWER_TYPE;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void onEnter() {
        flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, amount), amount));
    }

    @Override
    public void onLeave() {
//        flash();
//        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, DexterityPower.POWER_ID, amount));
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
