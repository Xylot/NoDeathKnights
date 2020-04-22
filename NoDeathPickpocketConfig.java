package net.runelite.client.plugins.nodeathknights;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("nodeathpickpocket")
public interface NoDeathKnightsConfig extends Config{
    @ConfigItem(
            keyName = "getHitpointsThreshold",
            name = "Health",
            description = "Health value to stop pickpockets",
            position = 1
    )
    default int getHitpointsThreshold() {
        return 30;
    }
}
