package downfall.patches;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.dynamicvariables.BlockVariable;
import basemod.helpers.dynamicvariables.DamageVariable;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CardModifierPatches;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariableCN;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.*;
import downfall.downfallMod;
import downfall.util.TextureLoader;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.Objects;
import java.util.regex.Pattern;

//TODO: I accidentally pushed one of my attempt code to change those evil run specific relic names(and image) for the related relics, it's not fully working
// (breaks when you load a game) but shouldn't cause any bugs I think, Mwalls

public class RelicOverrides {

//    @SpirePatch(
//            clz = SlaversCollar.class,
//            method = "setDescription"
//    )
//    public static class slaversCollarDesc {
//        @SpirePrefixPatch
//        public static SpireReturn<String> Prefix() {
//            if (EvilModeCharacterSelect.evilMode) {
//                return SpireReturn.Return(CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[0]);
//
//            }
//            return SpireReturn.Continue();
//        }
//
//    }


    @SpirePatch(
            clz = OldCoin.class,
            method = "getUpdatedDescription"
    )
    public static class oldCoinName {
        @SpirePrefixPatch
        public static void Prefix(OldCoin _instance) {
            if (EvilModeCharacterSelect.evilMode && _instance.name != CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[1]) {
                //ReflectionHacks.setPrivateStaticFinal(OldCoin.class, "name", CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[1]);
                _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/oldCoinEvil.png"));
                _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/oldCoinEvil.png"));
                _instance.flavorText = CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[2];
            }

        }

    }


    @SpirePatch(
            clz = MembershipCard.class,
            method = "getUpdatedDescription"
    )
    public static class membershipCardName {
        @SpirePrefixPatch
        public static void Prefix(MembershipCard _instance) {
            if (EvilModeCharacterSelect.evilMode && _instance.name != CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[3]) {
                //ReflectionHacks.setPrivateStaticFinal(MembershipCard.class, "name", CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[3]);
                _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/membershipCardEvil.png"));
                _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/membershipCardEvil.png"));
                _instance.flavorText = CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[4];

            }

        }
    }


    @SpirePatch(
            clz = Courier.class,
            method = "getUpdatedDescription"
    )
    public static class courierName {
        @SpirePrefixPatch
        public static void Prefix(Courier _instance) {
            if (EvilModeCharacterSelect.evilMode && _instance.name != CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[6]) {
                //ReflectionHacks.setPrivateStaticFinal(Courier.class, "name", CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[6]);
                _instance.imgUrl = null;
                _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/courierEvil.png"));
                _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/courierEvil.png"));
                _instance.flavorText = CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[5];

            }

        }
    }

    @SpirePatch(
            clz = PrismaticShard.class,
            method = "getUpdatedDescription"
    )
    public static class prismaticDesc {
        @SpirePrefixPatch
        public static void Postfix(PrismaticShard _instance) {
            if (EvilModeCharacterSelect.evilMode) {
                //ReflectionHacks.setPrivateStaticFinal(Courier.class, "name", CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[6]);
                _instance.description = CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[8];

            }

        }
    }

    @SpirePatch(
            clz = Ectoplasm.class,
            method = "getUpdatedDescription"
    )
    public static class EctoImage {
        @SpirePrefixPatch
        public static void Prefix(Ectoplasm _instance) {
            if (EvilModeCharacterSelect.evilMode) {
                _instance.imgUrl = null;
                _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/ectoplasmEvil.png"));
                _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/ectoplasmEvil.png"));
                _instance.flavorText = CardCrawlGame.languagePack.getRelicStrings("downfall:Hecktoplasm").FLAVOR;
            }

        }
    }

    @SpirePatch(
            clz = Ectoplasm.class,
            method = "setDescription"
    )
    public static class EctoDesc {
        @SpirePrefixPatch
        public static SpireReturn<String> Prefix() {

            if (EvilModeCharacterSelect.evilMode) {
                return SpireReturn.Return(CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[9]);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz=AbstractRelic.class,
            method= SpirePatch.CONSTRUCTOR
    )
    public static class EctoTitle {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractRelic __instance,String setId, String imgName, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
            if(Objects.equals(__instance.relicId, "Ectoplasm") && EvilModeCharacterSelect.evilMode) {
                ReflectionHacks.setPrivateFinal(__instance, AbstractRelic.class, "relicStrings", CardCrawlGame.languagePack.getRelicStrings("downfall:Hecktoplasm").NAME);

            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ImageMaster.class, "loadRelicImg");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
/*
    @SpirePatch(
            clz = Courier.class,
            method = "setDescription"
    )
    public static class courierCollarDesc {
        @SpirePrefixPatch
        public static SpireReturn<String> Prefix() {
            if (EvilModeCharacterSelect.evilMode) {
                return SpireReturn.Return(CardCrawlGame.languagePack.getRelicStrings("downfall:replacements").DESCRIPTIONS[7]);

            }
            return SpireReturn.Continue();
        }

    }
    */


}

