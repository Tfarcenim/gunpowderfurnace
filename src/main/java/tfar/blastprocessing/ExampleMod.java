package tfar.blastprocessing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {

	public static final Tag<Item> GUNPOWDER = new ItemTags.CachingTag(new Identifier("blastprocessing","gunpowder"));
	public static final Tag<Item> GUNPOWDER_BLOCK = new ItemTags.CachingTag(new Identifier("blastprocessing","gunpowder_block"));


	@Override
	public void onInitialize() {
		FuelRegistry.INSTANCE.add(GUNPOWDER,1000);
		FuelRegistry.INSTANCE.add(GUNPOWDER_BLOCK,10000);
	}
}
