package net.sadowscrewcosmetics;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class LayerCape extends RenderLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public LayerCape(PlayerRenderer playerRenderer) {
        super(playerRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayerEntity player, 
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, 
                       float netHeadYaw, float headPitch) {

        Identifier cape = CapeLoader.getCape(player);
        if (cape == null) return; // pas de cape

        getParentModel().copyPropertiesTo(getParentModel());
        MinecraftClient.getInstance().getTextureManager().bind(cape);

        // Simple rendu de cape derrière le joueur
        this.getParentModel().body.render(poseStack, buffer.getBuffer(RenderLayer.getEntityTranslucentCull(cape)), packedLight, OverlayTexture.NO_OVERLAY);
    }
}
