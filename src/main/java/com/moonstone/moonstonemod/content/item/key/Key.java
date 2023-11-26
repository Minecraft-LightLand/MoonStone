package com.moonstone.moonstonemod.content.item.key;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Key {
	public static KeyMapping keyMapping_g =
			new KeyMapping("key.moonstone.g", GLFW.GLFW_KEY_G, "key.categories.movement");
	public static KeyMapping keyMapping_r =
			new KeyMapping("key.moonstone.r", GLFW.GLFW_KEY_R, "key.categories.movement");

}
