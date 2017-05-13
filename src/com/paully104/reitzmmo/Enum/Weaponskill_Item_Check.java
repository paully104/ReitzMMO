package com.paully104.reitzmmo.Enum;

/**
 * Created by Paul on 4/30/2017.
 */
public class Weaponskill_Item_Check {

    public enum Weaponskill_Check
    {
        WOOD_SWORD(true), GOLD_SWORD(true), STONE_SWORD(true), IRON_SWORD(true), DIAMOND_SWORD(true),
        WOOD_AXE(true), GOLD_AXE(true), STONE_AXE(true), IRON_AXE(true), DIAMOND_AXE(true),
        WOOD_SPADE(true), GOLD_SPADE(true), STONE_SPADE(true), IRON_SPADE(true), DIAMOND_SPADE(true),
        WOOD_HOE(true), GOLD_HOE(true), STONE_HOE(true), IRON_HOE(true), DIAMOND_HOE(true);
        private final boolean value;

        Weaponskill_Check(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;

        }
    }
}
