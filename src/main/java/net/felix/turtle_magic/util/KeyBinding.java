package net.felix.turtle_magic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_BINDING_TM = "key.category.turtle_magic.tm";
    public static final String KEY_TOGGLE_SPELLHUD = "key.turtle_magic.toggle_spellhud";

    public static final KeyMapping TOGGLE_SPELLHUD_KEY = new KeyMapping(KEY_TOGGLE_SPELLHUD, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_BINDING_TM);
}
