package charbosses.bosses.Merchant;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.colorless.*;
import charbosses.powers.bossmechanicpowers.MerchantStrengthPower;
import charbosses.relics.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ArchetypeAct3MerchantBoss extends ArchetypeBaseMerchant {

    public ArchetypeAct3MerchantBoss() {
        super("ME_ARCHETYPE_MERCHANT", "Merchant");
    }

    public void initialize() {


        /////   RELICS   /////

        addRelic(new CBR_NeowsBlessing());
      //  addRelic(new CBR_MercuryHourglass());
       // addRelic(new CBR_Torii());
        //addRelic(new CBR_SelfFormingClay());
        //addRelic(new CBR_IncenseBurner());
        //addRelic(new CBR_Calipers());
       // addRelic(new CBR_Girya(3));
       // addRelic(new CBR_Vajra());
      //  addRelic(new CBR_SmoothStone());
        addRelic(new CBR_FossilizedHelix());
        //addRelic(new CBR_BagOfPreparation());
        addRelic(new CBR_ClockworkSouvenir());
        addRelic(new CBR_TungstenRod());
        addRelic(new CBR_IceCream());
        //addRelic(new CBR_FusionHammer());


    }


    @Override
    public void addedPreBattle() {
        super.addedPreBattle();
        AbstractCreature p = AbstractCharBoss.boss;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MerchantStrengthPower(p)));
    }


    @Override
    public ArrayList<AbstractCard> getThisTurnCards() {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        boolean extraUpgrades = AbstractDungeon.ascensionLevel >= 4;
        if (!looped) {
            switch (turn) {
                case 0:

                    addToList(cardsList, new EnPanicButton());
                    addToList(cardsList, new EnDramaticEntrance());
                    addToList(cardsList, new EnTheBomb(), extraUpgrades);
                    turn++;
                    break;
                case 1:
                    addToList(cardsList, new EnApotheosis(), true);
                    addToList(cardsList, new EnPanacea(), true);
                    addToList(cardsList, new EnGoodInstincts(), true);
                    turn++;
                    break;
                case 2:
                    //Bomb goes Boom.
                    addToList(cardsList, new EnPanacea(), true);
                    addToList(cardsList, new EnPanicButton(), true);
                    addToList(cardsList, new EnBlind(), true);
                    turn++;
                    break;
                case 3:
                    addToList(cardsList, new EnTrip(), true);
                    addToList(cardsList, new EnTheBomb(), true);
                    addToList(cardsList, new EnSwiftStrike(), true);
                    turn++;
                    break;
                case 4:
                    addToList(cardsList, new EnGoodInstincts(), true);
                    addToList(cardsList, new EnSwiftStrike(), true);
                    addToList(cardsList, new EnHandOfGreed(), true);
                    turn=0;
                    looped = true;
                    break;
            }

        } else {
            switch (turn) {
                case 0:
                    //Bomb goes Boom.
                    addToList(cardsList, new EnTheBomb(), true);
                    addToList(cardsList, new EnTrip(), true);
                    addToList(cardsList, new EnGoodInstincts(), true);
                    turn++;
                    break;
                case 1:
                    addToList(cardsList, new EnHandOfGreed(), true);
                    addToList(cardsList, new EnTheBomb(), true);
                    addToList(cardsList, new EnBlind(), true);
                    turn++;
                    break;
                case 2:
                    //Bomb goes Boom.
                    addToList(cardsList, new EnGoodInstincts(), true);
                    addToList(cardsList, new EnSwiftStrike(), true);
                    addToList(cardsList, new EnSwiftStrike(), true);
                    turn = 0;
                    break;
            }

        }
        return cardsList;
    }

    @Override
    public void initializeBonusRelic() {
        addRelic(new CBR_SelfFormingClay());
    }
}