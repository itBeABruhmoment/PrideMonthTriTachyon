package PrideMonthTT;

import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BaseCampaignEventListener;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CommDirectoryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI.EntryType;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.impl.campaign.ids.FleetTypes;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PrideMonthTTListener extends BaseCampaignEventListener{
    private static final Logger log = Global.getLogger(PrideMonthTT.PrideMonthTTListener.class);
    static {
        log.setLevel(Level.ALL);
    }

    public PrideMonthTTListener(boolean permaRegister) {
        super(permaRegister);
    }

    @Override
    public void reportEconomyMonthEnd() 
    {
        log.debug(Global.getSector().getClock().getMonth());
        CampaignClockAPI clock = Global.getSector().getClock();
        if(clock.getMonth() == 6) { // pride month start
            FactionAPI tritachyon = Global.getSector().getFaction("tritachyon");
            tritachyon.setFactionLogoOverride("graphics/factions/pride_tritachyon_tritachyon.png");
            tritachyon.setFactionCrestOverride("graphics/factions/pride_tritachyon_crest_tritachyon.png");

            FactionAPI remnants = Global.getSector().getFaction("remnant");
            remnants.setFactionLogoOverride("graphics/factions/pride_tritachyon_remnants.png");
            remnants.setFactionCrestOverride("graphics/factions/pride_tritachyon_crest_remnants.png");
        } else if(clock.getMonth() == 7) { // pride month end
            FactionAPI tritachyon = Global.getSector().getFaction("tritachyon");
            tritachyon.setFactionLogoOverride("graphics/factions/tritachyon.png");
            tritachyon.setFactionCrestOverride("graphics/factions/crest_tritachyon.png");

            FactionAPI remnants = Global.getSector().getFaction("remnant");
            remnants.setFactionLogoOverride("graphics/factions/ai_remnant.png");
            remnants.setFactionCrestOverride("graphics/factions/crest_ai_remnant.png");
        }
    }
}
