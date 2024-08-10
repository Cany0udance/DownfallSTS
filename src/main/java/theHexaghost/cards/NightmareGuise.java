package theHexaghost.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.downfallMod;
import theHexaghost.HexaMod;
import theHexaghost.util.HexaPurpleTextInterface;

public class NightmareGuise extends AbstractHexaCard implements HexaPurpleTextInterface {

    public final static String ID = makeID("NightmareGuise");

    public NightmareGuise() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        isEthereal = true;
        cardsToPreview = new ShadowGuise();
        tags.add(HexaMod.AFTERLIFE);
        HexaMod.loadJokeCardImage(this, "NightmareGuise.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        superFlash(Color.PURPLE);
        AbstractCard q = new ShadowGuise(this);
        atb(new MakeTempCardInHandAction(q));
    }

    @Override
    public void afterlife() {
        blck();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    // to still show afterlife tooltip. because the format [purple]hexamod:afterlife[] doesnt get displayed correctly
    // we are only using [purple]afterlife[] here for easier text comprehension for new players, but doing this
    // means we dont have the keyword tooltip so we need to manually add it
    // but after I tried adding it in the constrcutor it turns out sometimes who knows why it wont be added
    // and this way seems to work
    @Override
    public void initializeDescription() {
        super.initializeDescription();
        String afterlife_name = downfallMod.keywords_and_proper_names.get("afterlife");
        this.keywords.add(afterlife_name);
    }
}