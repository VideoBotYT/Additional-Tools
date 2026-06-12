package net.videobot.additionaltools.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.videobot.additionaltools.block.custom.CrystalUpgraderBlock;
import net.videobot.additionaltools.item.ModItems;
import net.videobot.additionaltools.recipe.ModRecipes;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipe;
import net.videobot.additionaltools.recipe.crystal_upgrader.CrystalUpgraderRecipeInput;
import net.videobot.additionaltools.screen.custom.CrystalUpgraderMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrystalUpgraderBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int TEMPLATE_SLOT = 0;
    private static final int TOOL_SLOT = 1;
    private static final int INGREDIENT_SLOT = 2;
    private static final int OUT_SLOT = 3;

    protected final ContainerData data;
    public int progress = 0;
    public int max_progress = 0;

    public CrystalUpgraderBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CRYSTAL_UPGRADER_BLOCK_ENTITY.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index){
                    case 0 -> CrystalUpgraderBlockEntity.this.progress;
                    case 1 -> CrystalUpgraderBlockEntity.this.max_progress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index){
                    case 0: CrystalUpgraderBlockEntity.this.progress = value;
                    case 1: CrystalUpgraderBlockEntity.this.max_progress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.additionaltools.crystal_upgrader");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CrystalUpgraderMenu(containerId, playerInventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++)
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        boolean flag = hasRecipe();
        boolean flag1 = false;
        if (flag) {
            increaseProgress();

            if (hasCraftingFinished()) {
                craft();
                resetProgress();
            }
        } else {
            resetProgress();
            flag1 = true;
        }

        if (flag != blockState.getValue(CrystalUpgraderBlock.ACTIVE)){
            flag1 = true;
            blockState = blockState.setValue(CrystalUpgraderBlock.ACTIVE, hasRecipe());
            level.setBlock(blockPos, blockState, 3);
        }
        if (flag1)
            setChanged(level, blockPos, blockState);
    }

    private void resetProgress() {
        progress = 0;
        max_progress = 80;
    }

    private void craft() {
        Optional<RecipeHolder<CrystalUpgraderRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemStackHandler.extractItem(TEMPLATE_SLOT, recipe.get().value().template().getItems()[0].getCount(), false);
        itemStackHandler.extractItem(TOOL_SLOT, recipe.get().value().tool().getItems()[0].getCount(), false);
        itemStackHandler.extractItem(INGREDIENT_SLOT, recipe.get().value().ingredient().getItems()[0].getCount(), false);

        itemStackHandler.setStackInSlot(OUT_SLOT, new ItemStack(output.getItem(),
                itemStackHandler.getStackInSlot(OUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= max_progress;
    }

    private void increaseProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CrystalUpgraderRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty())
            return false;

        max_progress = recipe.get().value().craftingTime();

        ItemStack output = recipe.get().value().output();

        return canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<CrystalUpgraderRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.CRYSTAL_UPGRADER_RECIPE_TYPE.get(), new CrystalUpgraderRecipeInput(
                        itemStackHandler.getStackInSlot(TEMPLATE_SLOT),
                        itemStackHandler.getStackInSlot(TOOL_SLOT),
                        itemStackHandler.getStackInSlot(INGREDIENT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemStackHandler.getStackInSlot(OUT_SLOT).isEmpty()
                || itemStackHandler.getStackInSlot(OUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertIntoOutputSlot(int count) {
        int maxCount = itemStackHandler.getStackInSlot(OUT_SLOT).isEmpty() ? 64 : itemStackHandler.getStackInSlot(OUT_SLOT).getMaxStackSize();
        int currentCount = itemStackHandler.getStackInSlot(OUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemStackHandler.serializeNBT(registries));
        tag.putInt("crystal_upgrader.progress", progress);
        tag.putInt("crystal_upgrader.max_progress", max_progress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        itemStackHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("crystal_upgrader.progress");
        max_progress = tag.getInt("crystal_upgrader.max_progress");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
