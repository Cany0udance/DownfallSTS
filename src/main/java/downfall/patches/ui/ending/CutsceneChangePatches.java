package downfall.patches.ui.ending;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import downfall.downfallMod;
import downfall.patches.EvilModeCharacterSelect;
import downfall.util.TextureLoader;
import theHexaghost.TheHexaghost;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SpirePatch(clz = Cutscene.class, method = SpirePatch.CONSTRUCTOR)
public class CutsceneChangePatches {
    //Should be compatible with basemod's change since Basemod should be loaded before this
    @SpirePostfixPatch
    public static void patch(Cutscene __instance, AbstractPlayer.PlayerClass chosenClass) {
        if (EvilModeCharacterSelect.evilMode) {

            Texture customBg = TextureLoader.getTexture("images/scenes/redBg.jpg");;
            if (customBg != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("bgImg");
                    f.setAccessible(true);

                    Texture oldBg = (Texture) f.get(__instance);
                    oldBg.dispose();
                    f.set(__instance, customBg);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            List<CutscenePanel> customPanels = new ArrayList<>();
            if(chosenClass ==  TheHexaghost.Enums.THE_SPIRIT){
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending1.jpg"), "VO_NEOW_1A"));
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending2.jpg")));
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending3.jpg")));
            }else {
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending1.png"), "VO_NEOW_1A"));
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending2.png")));
                customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending3.png")));
            }
            if (customPanels != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("panels");
                    f.setAccessible(true);

                    ArrayList<CutscenePanel> panels = (ArrayList<CutscenePanel>) f.get(__instance);
                    for (CutscenePanel panel : panels) {
                        panel.dispose();
                    }
                    panels.clear();
                    panels.addAll(customPanels);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
