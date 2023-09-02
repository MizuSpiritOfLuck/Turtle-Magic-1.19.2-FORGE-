package net.felix.turtle_magic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_BINDING_RM = "key.category.regna_magia.rm";
    public static final String KEY_USE_FIREBALL = "key.regna_magia.use_fireball";
    public static final String KEY_USE_LIGHTNING_BOLT = "key.regna_magia.use_lightning_bolt";

    public static final KeyMapping USE_FIREBALL_KEY = new KeyMapping(KEY_USE_FIREBALL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_0, KEY_BINDING_RM);

    public static final KeyMapping USE_LIGHTNING_BOLT_KEY = new KeyMapping(KEY_USE_LIGHTNING_BOLT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_1, KEY_BINDING_RM);
}
