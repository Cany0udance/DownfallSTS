package theHexaghost.potions;


import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theHexaghost.HexaMod;
import theHexaghost.powers.FlameAffectAllEnemiesPower;


public class InfernoChargePotion extends CustomPotion {
    public static final String POTION_ID = "hexamod:InfernoChargePotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public InfernoChargePotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPHERE, PotionColor.FAIRY);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = HexaMod.placeholderColor;
    }

    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player != null && potency > 1) {
            this.description = potionStrings.DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[2];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(HexaMod.makeID("ignite"))), BaseMod.getKeywordDescription(HexaMod.makeID("ignite"))));
    }

    public void use(AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FlameAffectAllEnemiesPower(this.potency), this.potency));
    }


    public CustomPotion makeCopy() {
        return new InfernoChargePotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}


