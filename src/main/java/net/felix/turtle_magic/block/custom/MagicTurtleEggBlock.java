package net.felix.turtle_magic.block.custom;

import net.felix.turtle_magic.block.TMBlocks;
import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.custom.MagicTurtle;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MagicTurtleEggBlock extends TurtleEggBlock {
    public static final int MAX_HATCH_LEVEL = 2;
    public static final int MIN_EGGS = 1;
    public static final int MAX_EGGS = 4;
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;

    public MagicTurtleEggBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            this.destroyEgg(level, state, pos, entity, 100);
        }

        super.stepOn(level, pos, state, entity);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f1) {
        if (!(entity instanceof Zombie)) {
            this.destroyEgg(level, state, pos, entity, 3);
        }

        super.fallOn(level, state, pos, entity, f1);
    }

    private void destroyEgg(Level level, BlockState state, BlockPos pos, Entity entity, int i1) {
        if (this.canDestroyEgg(level, entity)) {
            if (!level.isClientSide && level.random.nextInt(i1) == 0 && state.is(TMBlocks.MAGIC_TURTLE_EGG.get())) {
                this.decreaseEggs(level, pos, state);
            }

        }
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        level.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        int i = state.getValue(EGGS);
        if (i <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            level.levelEvent(2001, pos, Block.getId(state));
        }

    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (this.shouldUpdateHatchLevel(level)) {
            int i = state.getValue(HATCH);
            if (i < 2) {
                level.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + source.nextFloat() * 0.2F);
                level.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                level.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + source.nextFloat() * 0.2F);
                level.removeBlock(pos, false);

                for(int j = 0; j < state.getValue(EGGS); ++j) {
                    level.levelEvent(2001, pos, Block.getId(state));
                    MagicTurtle magicTurtle = TMEntityTypes.MAGIC_TURTLE.get().create(level);
                    magicTurtle.setAge(-24000);
                    magicTurtle.setHomePos(pos);
                    magicTurtle.moveTo((double)pos.getX() + 0.3D + (double)j * 0.2D, (double)pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
                    level.addFreshEntity(magicTurtle);
                }
            }
        }

    }

    public static boolean onSand(BlockGetter blockGetter, BlockPos pos) {
        return isSand(blockGetter, pos.below());
    }

    public static boolean isSand(BlockGetter blockGetter, BlockPos pos) {
        return blockGetter.getBlockState(pos).is(BlockTags.SAND);
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean b1) {
        if (!level.isClientSide) {
            level.levelEvent(2005, pos, 0);
        }

    }

    private boolean shouldUpdateHatchLevel(Level level) {
        float f = level.getTimeOfDay(1.0F);
        if ((double)f < 0.69D && (double)f > 0.65D) {
            return true;
        } else {
            return level.random.nextInt(500) == 0;
        }
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, blockEntity, stack);
        this.decreaseEggs(level, pos, state);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext placeContext) {
        return !placeContext.isSecondaryUseActive() && placeContext.getItemInHand().is(this.asItem()) && state.getValue(EGGS) < 4 ? true : super.canBeReplaced(state, placeContext);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockState blockstate = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(4, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(placeContext);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return state.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HATCH, EGGS);
    }

    private boolean canDestroyEgg(Level level, Entity entity) {
        if (!(entity instanceof Turtle) && !(entity instanceof Bat)) {
            if (!(entity instanceof LivingEntity)) {
                return false;
            } else {
                return entity instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level, entity);
            }
        } else {
            return false;
        }
    }
}
