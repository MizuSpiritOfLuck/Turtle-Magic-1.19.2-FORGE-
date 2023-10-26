package net.felix.turtle_magic.client;

import net.felix.turtle_magic.TurtleMagic;
import com.mojang.blaze3d.systems.RenderSystem;
import net.felix.turtle_magic.item.TMItems;
import net.felix.turtle_magic.item.custom.*;
import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
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

           if (!gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV1.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV2.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV3.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) &&
               !gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get())) {

               RenderSystem.setShaderTexture(0, EMPTY);
               GuiComponent.blit(poseStack, x - 211, y - 50,
                       0, 0, 48, 48, 48, 48);
           }

        if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV1.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV2.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV3.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) ||
            gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get())) {

            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV1.get()) && TurtleStaffLv1.lv1mode == 0 && TurtleStaffLv1.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV2.get()) && TurtleStaffLv2.lv2mode == 0 && TurtleStaffLv2.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV3.get()) && TurtleStaffLv3.lv3mode == 0 && TurtleStaffLv3.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) && TurtleStaffLv4.lv4mode == 0 && TurtleStaffLv4.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) && TurtleStaffLv5.lv5mode == 0 && TurtleStaffLv5.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 0 && TurtleStaffLv6.mode0 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 0 && TurtleStaffLv3.mode0) {

                RenderSystem.setShaderTexture(0, TESTUDO);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV2.get()) && TurtleStaffLv2.lv2mode == 1 && TurtleStaffLv2.mode1 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV3.get()) && TurtleStaffLv3.lv3mode == 1 && TurtleStaffLv3.mode1 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) && TurtleStaffLv4.lv4mode == 1 && TurtleStaffLv4.mode1 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) && TurtleStaffLv5.lv5mode == 1 && TurtleStaffLv5.mode1 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 1 && TurtleStaffLv6.mode1 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 1 && TurtleStaffLv7.mode1) {

                RenderSystem.setShaderTexture(0, COVER_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV3.get()) && TurtleStaffLv3.lv3mode == 2 && TurtleStaffLv3.mode2 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) && TurtleStaffLv4.lv4mode == 2 && TurtleStaffLv4.mode2 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) && TurtleStaffLv5.lv5mode == 2 && TurtleStaffLv5.mode2 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 2 && TurtleStaffLv6.mode2 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 2 && TurtleStaffLv3.mode2) {

                RenderSystem.setShaderTexture(0, EMPTY /*SNAPPER_FANG*/);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV4.get()) && TurtleStaffLv4.lv4mode == 3 && TurtleStaffLv4.mode3 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) && TurtleStaffLv5.lv5mode == 3 && TurtleStaffLv5.mode3 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 3 && TurtleStaffLv6.mode3 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 3 && TurtleStaffLv3.mode3) {

                RenderSystem.setShaderTexture(0, TWIRLING_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV5.get()) && TurtleStaffLv5.lv5mode == 4 && TurtleStaffLv5.mode4 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 4 && TurtleStaffLv6.mode4 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 4 && TurtleStaffLv3.mode4) {

                RenderSystem.setShaderTexture(0, TURTLES_BINDING);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV6.get()) && TurtleStaffLv6.lv6mode == 5 && TurtleStaffLv6.mode5 ||
                gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 5 && TurtleStaffLv3.mode5) {

                RenderSystem.setShaderTexture(0, TURTLES_CURSE);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
            if (gui.getMinecraft().player.getMainHandItem().is(TMItems.TURTLE_STAFF_LV7.get()) && TurtleStaffLv7.lv7mode == 6 && TurtleStaffLv3.mode6) {

                RenderSystem.setShaderTexture(0, DESCENDING_SHELL);
                GuiComponent.blit(poseStack, x - 211, y - 50,
                        0, 0, 48, 48, 48, 48);
            }
        }
       }
    });
}
