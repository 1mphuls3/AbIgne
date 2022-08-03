package com.Imphuls3.abigne.common.entity.renderer;

import com.Imphuls3.abigne.common.entity.SoulEntity;
import com.Imphuls3.abigne.common.entity.SoulModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.Imphuls3.abigne.AbIgne.prefix;

public class SoulEntityRenderer<T extends Entity> extends GeoEntityRenderer<SoulEntity> {

    public SoulEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SoulModel());
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEntity pEntity) {
        return prefix("textures/entity/soul/soul.png");
    }

    @Override
    public void render(SoulEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        /*Level level = Minecraft.getInstance().level;
        *//*PostProcessing effect2 = PostProcessing.HALFTONE;

        PoseStack finalStack2 = RenderUtils.copyPoseStack(stack);

        effect2.postEntity(buffer -> {
            super.render(entity, entityYaw, partialTicks, finalStack2, buffer, packedLightIn);
        });*//*

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(CIRCLE);

        stack.pushPose();
        stack.translate(entity.position().x, entity.position().y, entity.position().z);
        stack.mulPose(Vector3f.YP.rotationDegrees(entityYaw + 90));

        PostProcessing effect = PostProcessing.BLOOM_UNREAL;
        PoseStack finalStack = RenderUtils.copyPoseStack(stack);
        Matrix4f matrix = finalStack.last().pose();

        effect.postEntity(buffer -> {
            VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderType.ADD);
            RenderHelper.renderSprite(vertexConsumer, matrix, finalStack, sprite, 0.5F, 0.5F, 0.5F, 0, 0.8F, LightTexture.FULL_BRIGHT);
        });
        stack.popPose();*/
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }

    @Override
    public RenderType getRenderType(SoulEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
