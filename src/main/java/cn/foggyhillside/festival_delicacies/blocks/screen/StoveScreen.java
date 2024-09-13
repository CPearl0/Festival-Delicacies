package cn.foggyhillside.festival_delicacies.blocks.screen;

import cn.foggyhillside.festival_delicacies.FestivalDelicacies;
import cn.foggyhillside.festival_delicacies.tag.ModTags;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cn.foggyhillside.festival_delicacies.blocks.StoveBlock.LIT;

public class StoveScreen extends AbstractContainerScreen<StoveMenu> {

    @Override
    protected void init() {
        super.init();
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(FestivalDelicacies.MODID, "textures/gui/pot_stove_gui.png");

    public StoveScreen(StoveMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i1, int i2) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(guiGraphics, x, y);
        renderLit(guiGraphics, x, y);
        renderNotLit(guiGraphics, x, y);
        renderPot(guiGraphics, x, y);
        renderContainerSlot(guiGraphics, x, y);
        renderDarkSlot(guiGraphics, x, y);
    }

    private void renderHeatIndicatorTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (this.isHovering(94, 52, 15, 18, (double)mouseX, (double)mouseY)) {
            List<Component> tooltip = new ArrayList();
            if(!menu.entity.getBlockState().getValue(LIT)){
                tooltip.add(Component.translatable(FestivalDelicacies.MODID + ".stove" + ".not_lit" ));
            }else if(!menu.entity.getLevel().getBlockState(menu.entity.getBlockPos().above()).is(ModTags.IS_POT)){
                tooltip.add(Component.translatable(FestivalDelicacies.MODID + ".stove" + ".lit" ));
            }else {
                tooltip.add(Component.translatable(FestivalDelicacies.MODID + ".stove" + ".pot" ));
            }
            guiGraphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
        }

    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 90, y + 35, 176, 0, menu.getScaledProgress(), 17);
        }
    }

    private void renderLit(GuiGraphics guiGraphics, int x, int y) {
        if (menu.entity.getBlockState().getValue(LIT) && !menu.entity.getLevel().getBlockState(menu.entity.getBlockPos().above()).is(ModTags.IS_POT)) {
            guiGraphics.blit(TEXTURE, x + 94, y + 52, 176, 17, 14, 18);
        }
    }
    private void renderNotLit(GuiGraphics guiGraphics, int x, int y) {
        if (!menu.entity.getBlockState().getValue(LIT)) {
            guiGraphics.blit(TEXTURE, x + 94, y + 52, 190, 17, 14, 18);
        }
    }

    private void renderPot(GuiGraphics guiGraphics, int x, int y) {
        if (menu.entity.getBlockState().getValue(LIT) && menu.entity.getLevel().getBlockState(menu.entity.getBlockPos().above()).is(ModTags.IS_POT)) {
            guiGraphics.blit(TEXTURE, x + 94, y + 54, 193, 35, 15, 15);
        }
    }

    private void renderContainerSlot(GuiGraphics guiGraphics, int x, int y) {
        if (menu.entity.itemStackHandler.getStackInSlot(9) != ItemStack.EMPTY) {
            guiGraphics.blit(TEXTURE, x + 93, y + 17, 30, 17, 17, 17);
        }
    }

    private void renderDarkSlot(GuiGraphics guiGraphics, int x, int y) {
        if (!menu.entity.getLevel().getBlockState(menu.entity.getBlockPos().above()).is(ModTags.IS_POT)) {
            guiGraphics.blit(TEXTURE, x + 65, y + 16, 176, 35, 17, 17);
            guiGraphics.blit(TEXTURE, x + 65, y + 34, 176, 35, 17, 17);
            guiGraphics.blit(TEXTURE, x + 29, y + 52, 176, 35, 17, 17);
            guiGraphics.blit(TEXTURE, x + 47, y + 52, 176, 35, 17, 17);
            guiGraphics.blit(TEXTURE, x + 65, y + 52, 176, 35, 17, 17);
        }
    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float f) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, f);
        renderTooltip(poseStack, mouseX, mouseY);
        this.renderHeatIndicatorTooltip(poseStack, mouseX, mouseY);
    }

}
