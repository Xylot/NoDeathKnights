package net.runelite.client.plugins.nodeathknights;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
        name = "No Death Knights",
        description = "Never die when pickpocketing Ardougne Knights"
)

public class NoDeathKnights extends Plugin {

    @Provides
    NoDeathKnightsConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(NoDeathKnightsConfig.class);
    }

    @Inject
    private NoDeathKnightsConfig config;

    @Inject
    private Client client;

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {

        String menuOption = event.getMenuOption();
        String menuTarget = event.getMenuTarget();

        if (menuOption.equals("Pickpocket") && menuTarget.contains("Knight of Ardougne") && checkLowHitpoints()) {
            event.consume();
        }

    }

    private boolean checkLowHitpoints()
    {
        if (config.getHitpointsThreshold() == 0)
        {
            return false;
        }
        return client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.getHitpointsThreshold();
    }

}
