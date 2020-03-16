package tfar.blastprocessing.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.blastprocessing.ExampleMod;

@Mixin(AbstractFurnaceBlockEntity.class)
abstract class AbstractFurnaceBlockEntityMixin extends BlockEntity {

	@Shadow protected DefaultedList<ItemStack> inventory;

	@Shadow protected abstract boolean isBurning();

	public AbstractFurnaceBlockEntityMixin(BlockEntityType<?> type) {
		super(type);
	}

	@Inject(method = "tick",at = @At("RETURN"))
	private void boom(CallbackInfo ci){
		ItemStack stack = this.inventory.get(1);
		if ((stack.getItem().isIn(ExampleMod.GUNPOWDER) || stack.getItem().isIn(ExampleMod.GUNPOWDER_BLOCK)) && isBurning()) {
			float boom = stack.getItem().isIn(ExampleMod.GUNPOWDER) ? 3 : 9;
			boom *= Math.pow(stack.getCount(),1/3d);
			this.world.createExplosion(null, this.pos.getX(), this.pos.getY()+1, this.pos.getZ(), boom, Explosion.DestructionType.BREAK);
		}
	}
}
