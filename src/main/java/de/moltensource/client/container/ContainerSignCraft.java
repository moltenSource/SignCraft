package de.moltensource.client.container;

import de.moltensource.tile.TileEntitySignPress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSignCraft extends Container {
    public static final int CRAFTING_SLOTS_COUNT = 9;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int SIGNPRESS_SLOTS_COUNT = CRAFTING_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int FIRST_CRAFTING_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int FIRST_OUTPUT_SLOT_INDEX = FIRST_CRAFTING_SLOT_INDEX + CRAFTING_SLOTS_COUNT;

    private static final int FIRST_CRAFTING_SLOT_NUMBER = 0;
    private static final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_CRAFTING_SLOT_NUMBER + CRAFTING_SLOTS_COUNT;

    private TileEntitySignPress entity;
    private int[] cachedFields;

    public ContainerSignCraft(InventoryPlayer invPlayer, TileEntitySignPress entity) {
        this.entity = entity;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 183;

        // add player hotbar to gui
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlotToContainer(new Slot(invPlayer, x, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 125;

        // add players inventory to gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
            }
        }

        final int CRAFTING_SLOTS_XPOS = 53;
        final int CRAFTING_SLOTS_YPOS = 96;

        // add crafting slots
        for (int x = 0; x < CRAFTING_SLOTS_COUNT; x++) {
            int slotNumber = x + FIRST_CRAFTING_SLOT_NUMBER;
            addSlotToContainer(new SlotCrafting(entity, slotNumber, CRAFTING_SLOTS_XPOS + SLOT_X_SPACING * x, CRAFTING_SLOTS_YPOS));
        }

        final int OUTPUT_SLOTS_XPOS = 134;
        final int OUTPUT_SLOTS_YPOS = 24;

        // add output slots
        for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
            int slotNumber = y + FIRST_OUTPUT_SLOT_NUMBER;
            addSlotToContainer(new SlotOutput(entity, slotNumber, OUTPUT_SLOTS_XPOS, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return entity.isUseableByPlayer(player);
    }

    // this defines shift-clicking action
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
        Slot sourceSlot = (Slot) inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return null;

        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // check if the slot clicked is a vanilla container slots
        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // merge clicked stack into the furnace slots
            if (!mergeItemStack(sourceStack, FIRST_CRAFTING_SLOT_INDEX, FIRST_CRAFTING_SLOT_INDEX + CRAFTING_SLOTS_COUNT, false)) {
                return null;
            } else {
                return null;
            }
        } else if (sourceSlotIndex == FIRST_OUTPUT_SLOT_INDEX) {
            // output slot was clicked
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return null;
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return null;
        }

        // if stack size == 0 set slot content to null
        if (sourceStack.stackSize == 0) {
            sourceSlot.putStack(null);
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onPickupFromSlot(player, sourceStack);
        return copyOfSourceStack;
    }

    // client sync
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        boolean allFieldsHaveChanged = false;
        boolean fieldHasChanged[] = new boolean[entity.getFieldCount()];

        if (cachedFields == null) {
            cachedFields = new int[entity.getFieldCount()];
            allFieldsHaveChanged = true;
        }

        for (int i = 0; i < cachedFields.length; ++i) {
            if (allFieldsHaveChanged || cachedFields[i] != entity.getField(i)) {
                cachedFields[i] = entity.getField(i);
                fieldHasChanged[i] = true;
            }
        }

        // update each listener if necessary
        for (IContainerListener listener : this.listeners) {
            for (int fieldID = 0; fieldID < entity.getFieldCount(); ++fieldID) {
                if (fieldHasChanged[fieldID]) {
                    listener.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
                }
            }
        }
    }

    // crafting slot
    public class SlotCrafting extends Slot {
        public SlotCrafting(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntitySignPress.isValidCraftingItem(stack);
        }
    }

    // output slot
    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntitySignPress.isValidOutputItem(stack);
        }
    }
}
