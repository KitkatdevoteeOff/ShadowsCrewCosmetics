package net.sadowscrewcosmetics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.HttpTexture;
import net.minecraft.util.Identifier;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class CapeLoader implements ClientModInitializer {

    private static final Map<UUID, Identifier> PLAYER_CAPES = new HashMap<>();

    @Override
    public void onInitializeClient() {
        System.out.println("[ShadowsCrewCosmetics] Système de capes initialisé !");
    }

    public static Identifier getCape(AbstractClientPlayerEntity player) {
        if (PLAYER_CAPES.containsKey(player.getUuid())) {
            return PLAYER_CAPES.get(player.getUuid());
        }

        try {
            // 🔗 URL GitHub pour la cape
            String url = "https://raw.githubusercontent.com/KitkatdevoteeOff/ShadowsCrewCosmetics/main/capes/" 
                         + player.getName().getString() + ".png";

            Identifier id = new Identifier("sadowscrewcosmetics", player.getUuidAsString());
            MinecraftClient.getInstance().getTextureManager().loadTexture(
                    id,
                    new HttpTexture(new URL(url), null, null, true, null)
            );

            PLAYER_CAPES.put(player.getUuid(), id);
            return id;
        } catch (Exception e) {
            // Pas de cape trouvée ou erreur réseau
            return null;
        }
    }
}
