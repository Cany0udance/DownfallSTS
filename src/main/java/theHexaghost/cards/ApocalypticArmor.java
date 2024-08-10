package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.HexaMod;
import theHexaghost.powers.ApocalypticArmorPower;

public class ApocalypticArmor extends AbstractHexaCard {

    public final static String ID = makeID("Doomsday");

    //doomsday dooms day

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = -1;

    public ApocalypticArmor() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(HexaMod.GHOSTWHEELCARD);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        HexaMod.loadJokeCardImage(this, "ApocalypticArmor.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ApocalypticArmorPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}