package PrideMonthTT;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignEventListener;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager.GenericBarEventCreator;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PrideMonthTTModPlugin extends BaseModPlugin{
    private static final Logger log = Global.getLogger(PrideMonthTT.PrideMonthTTModPlugin.class);
    static {
        log.setLevel(Level.ALL);
    }

    @Override
    public void onApplicationLoad() throws IOException
    {
        log.debug("loading PrideMonthTT flags");
        SettingsAPI settings = Global.getSettings();
        settings.loadTexture("graphics/factions/pride_tritachyon_tritachyon.png");
        settings.loadTexture("graphics/factions/pride_tritachyon_crest_tritachyon.png");
        settings.loadTexture("graphics/factions/pride_tritachyon_remnants.png");
        settings.loadTexture("graphics/factions/pride_tritachyon_crest_remnants.png");
    }

    @Override
    public void onGameLoad(boolean newGame)
    {
        log.debug("adding PrideMonthTT listener");
        Global.getSector().addTransientListener(new PrideMonthTTListener(false));

        // in case it is already june on load
        CampaignClockAPI clock = Global.getSector().getClock();
        if(clock.getMonth() == 6) { // pride month start
            FactionAPI tritachyon = Global.getSector().getFaction("tritachyon");
            tritachyon.setFactionLogoOverride("graphics/factions/pride_tritachyon_tritachyon.png");
            tritachyon.setFactionCrestOverride("graphics/factions/pride_tritachyon_crest_tritachyon.png");

            FactionAPI remnants = Global.getSector().getFaction("remnant");
            remnants.setFactionLogoOverride("graphics/factions/pride_tritachyon_remnants.png");
            remnants.setFactionCrestOverride("graphics/factions/pride_tritachyon_crest_remnants.png");
        }
        /* 
        for(CampaignEventListener lis : Global.getSector().getAllListeners()) {
            Console.showMessage(lis.getClass());
        }
        */
    }

    @Override
    // hopefully this makes this mod removable
    public void beforeGameSave() 
    {
        FactionAPI tritachyon = Global.getSector().getFaction("tritachyon");
        tritachyon.setFactionLogoOverride("graphics/factions/tritachyon.png");
        tritachyon.setFactionCrestOverride("graphics/factions/crest_tritachyon.png");
        FactionAPI remnants = Global.getSector().getFaction("remnant");
        remnants.setFactionLogoOverride("graphics/factions/ai_remnant.png");
        remnants.setFactionCrestOverride("graphics/factions/crest_ai_remnant.png");
    }
}
