package com.github.Imphuls3.abigne.common.entity;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.core.registry.RenderTypeRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import static org.lwjgl.opengl.GL11C.GL_ONE;
import static org.lwjgl.opengl.GL11C.GL_ZERO;

public class WispModel extends EntityModel<WispEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AbIgne.MODID, "wisp"), "main");
    private final ModelPart skull;
    private final ModelPart flames;

    public WispModel(ModelPart root) {
        this.skull = root.getChild("skull");
        this.flames = root.getChild("flames");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skull = partdefinition.addOrReplaceChild("skull", CubeListBuilder.create()
                .texOffs(0, 39).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition flames = partdefinition.addOrReplaceChild("flames", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-5.0F, -19.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(WispEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int light = LightTexture.FULL_BRIGHT;
        stack.pushPose();
        flames.render(stack, vertexConsumer, light, packedOverlay, red, green, blue, alpha);
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        skull.render(stack, vertexConsumer, light, packedOverlay, red, green, blue, alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        stack.popPose();
    }
}
