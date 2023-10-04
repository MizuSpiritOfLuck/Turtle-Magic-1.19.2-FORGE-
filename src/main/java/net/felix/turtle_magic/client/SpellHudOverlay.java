package net.felix.turtle_magic.client;

import net.felix.turtle_magic.TurtleMagic;
import com.mojang.blaze3d.systems.RenderSystem;
import net.felix.turtle_magic.item.TMItems;
import net.felix.turtle_magic.item.custom.TempStaff;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SpellHudOverlay {
    public static boolean showSpellHud = true;

    private static final ResourceLocation BORDER = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/border.png");
    private static final ResourceLocation EMPTY = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/empty.png");
    private static final ResourceLocation TESTUDO = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/testudo.png");
    private static final ResourceLocation COVER_SHELL = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/cover_shell.png");
    private static final ResourceLocation SNAPPER_FANG = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/snapper_fang.png");
    private static final ResourceLocation TWIRLING_SHELL = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/twirling_shell.png");
    private static final ResourceLocation DESCENDING_SHELL = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/descending_shell.png");
    private static final ResourceLocation TURTLES_CURSE = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/turtles_curse.png");
    private static final ResourceLocation TURTLES_BINDING = new ResourceLocation(TurtleMagic.MOD_ID,
            "textures/spell_icons/turtles_binding.png");


    public static final IGuiOverlay SPELL_ICONS = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
       int x = screenWidth / 2;
       int y = screenHeight;

       if(showSpellHud) {
           RenderSystem.setShader(GameRenderer::getPositionTexShader);
           RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

           RenderSystem.setShaderTexture(0, BORDER);
           GuiComponent.blit(poseStack, x - 211, y - 50,
                   0, 0, 48, 48, 48, 48);

           if (!gui.getMinecraft().player.getMainHandItem().is(TMItems.TEMP_STAFF.get())) {
               RenderSystem.setShaderTexture(0, EMPTY);
               GuiComponent.blit(poseStack, x - 211, y - 50,
                       0, 0, 48, 48, 48, 48);
           }


        if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TEMP_STAFF.get())) {
            if (TempStaff.mode == 0) {
                RenderSystem.setShaderTexture(0, TESTUDO);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 1) {
                RenderSystem.setShaderTexture(0, COVER_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 2) {
                RenderSystem.setShaderTexture(0, EMPTY /*SNAPPER_FANG*/);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 3) {
                RenderSystem.setShaderTexture(0, TWIRLING_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 4) {
                RenderSystem.setShaderTexture(0, DESCENDING_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 5) {
                RenderSystem.setShaderTexture(0, TURTLES_CURSE);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (TempStaff.mode == 6) {
                RenderSystem.setShaderTexture(0, TURTLES_BINDING);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
        }
       }
    });
}
