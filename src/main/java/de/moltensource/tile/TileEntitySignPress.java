package de.moltensource.tile;

import de.moltensource.client.container.ContainerSignCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Arrays;

public class TileEntitySignPress extends TileEntity implements IInventory {
    private ItemStack[] itemStacks = new ItemStack[ContainerSignCraft.SIGNPRESS_SLOTS_COUNT];

    static public boolean isValidCraftingItem(ItemStack itemStack) {
        return true;
    }

    static public boolean isValidOutputItem(ItemStack itemStack) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return itemStacks[index];
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);

        if (stack == null) return null;

        ItemStack stackRemoved;
        if (stack.stackSize <= count) {
            stackRemoved = stack;
            setInventorySlotContents(index, null);
        } else {
            stackRemoved = stack.splitStack(count);
            if (stack.stackSize == 0) {
                setInventorySlotContents(index, null);
            }
        }
        markDirty();

        return stackRemoved;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);

        if (stack != null) setInventorySlotContents(index, null);

        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        itemStacks[index] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (this.worldObj.getTileEntity(this.pos) != this) return false;

        return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "tile.SignCraft.signPressBlock.name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    // saves data
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {
        super.writeToNBT(parentNBTTagCompound);

        NBTTagList dataForAllSlots = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; i++) {
            if (this.itemStacks[i] != null) {
                NBTTagCompound dataForThisSlot = new NBTTagCompound();

                dataForThisSlot.setByte("Slot", (byte) i);
                this.itemStacks[i].writeToNBT(dataForThisSlot);
                dataForAllSlots.appendTag(dataForThisSlot);
            }
        }
        parentNBTTagCompound.setTag("Items", dataForAllSlots);

        return parentNBTTagCompound;
    }

    // loads data
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        final byte NBT_TYPE_COMPOUND = 10;
        NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(itemStacks, null);
        for (int i = 0; i < dataForAllSlots.tagCount(); i++) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = ItemStack.loadItemStackFromNBT(dataForOneSlot);
            }
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        final int METADATA = 0;
        NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();

        return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);

        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }
}
