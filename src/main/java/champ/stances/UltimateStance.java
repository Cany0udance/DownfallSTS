package champ.stances;

import champ.ChampChar;
import champ.ChampMod;
import com.megacrit.cardcrawl.stances.AbstractStance;
import expansioncontent.util.DownfallAchievementUnlocker;
import expansioncontent.util.DownfallAchievementVariables;

public class UltimateStance extends AbstractChampStance {

    public static final String STANCE_ID = "champ:UltimateStance";
    private static long sfxId = -1L;

    public UltimateStance() {
        this.ID = STANCE_ID;
        this.name = ChampChar.characterStrings.TEXT[7];
        this.updateDescription();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return ChampChar.characterStrings.TEXT[8] + ": " +
                ChampChar.characterStrings.TEXT[12] + //Gain #B
                BerserkerStance.amount() +
                ChampChar.characterStrings.TEXT[46] + //#y Vigor
                " NL " +
                ChampChar.characterStrings.TEXT[12] + //Gain #B
                DefensiveStance.amount() +
                ChampChar.characterStrings.TEXT[47] + //#y Counter
                " NL " +
                ChampChar.characterStrings.TEXT[62] + //"Charges Remaining:
                getRemainingChargeCount() +
                ChampChar.characterStrings.TEXT[55]; //"."
    }


    @Override
    public String getKeywordString() {
        return "champ:ultimate";
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        ChampMod.enteredBerserkerThisTurn = true;
        ChampMod.enteredDefensiveThisTurn = true;
        DownfallAchievementVariables.enteredUltimateStance++;
        if (DownfallAchievementVariables.enteredUltimateStance >= 3) {
            DownfallAchievementUnlocker.unlockAchievement("I_AM_THE_ONE");
        }
    }

    @Override
    public void updateDescription() {
        this.description = ChampChar.characterStrings.TEXT[21];
    }

    @Override
    public void technique() {
        ((BerserkerStance) AbstractStance.getStanceFromName(BerserkerStance.STANCE_ID)).technique();
        ((DefensiveStance) AbstractStance.getStanceFromName(DefensiveStance.STANCE_ID)).technique();
    }

    @Override
    public void finisher() {
        ((BerserkerStance) AbstractStance.getStanceFromName(BerserkerStance.STANCE_ID)).finisher();
        ((DefensiveStance) AbstractStance.getStanceFromName(DefensiveStance.STANCE_ID)).finisher();
    }
}
