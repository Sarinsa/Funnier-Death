package com.sarinsa.funnier_death.common.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FDClientConfig {

    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT = clientPair.getLeft();
        CLIENT_SPEC = clientPair.getRight();
    }

    public static final class Client {

        private final ForgeConfigSpec.BooleanValue deathFlipDegreesEnabled;
        private final ForgeConfigSpec.DoubleValue deathFlipDegrees;
        private final ForgeConfigSpec.BooleanValue randomizeDegrees;
        private final ForgeConfigSpec.DoubleValue minRandomDegrees;
        private final ForgeConfigSpec.DoubleValue maxRandomDegrees;

        private final ForgeConfigSpec.BooleanValue scaleEnabled;
        private final ForgeConfigSpec.DoubleValue scale;

        private Client(ForgeConfigSpec.Builder configBuilder) {
            configBuilder.push("global").comment("These settings apply to all mobs, unless they have their own config section (not yet implemented).");

            this.deathFlipDegreesEnabled = configBuilder.comment("Toggles death flip degrees.")
                    .define("death_flip_degrees_enabled", true);

            this.deathFlipDegrees = configBuilder.comment("If death flip degrees is enabled, this value will override how many degrees entities rotate when they die.")
                    .defineInRange("death_flip_degrees", 360.0D, -10000.0D, 10000.0D);

            this.randomizeDegrees = configBuilder.comment("If enabled, mobs will rotate a random amount of degrees when dying. The minimum and maximum random number of degrees can also be configured.")
                    .define("randomize_degrees", false);

            this.maxRandomDegrees = configBuilder.comment("If Randomized Degrees is enabled, this value dictates the largest possible random number for flip degrees.")
                    .comment("NOTE: This value MUST be larger than the configured minimum random degrees value.")
                    .defineInRange("max_random_degrees", 270.0D, 1, 10000.0D);

            this.minRandomDegrees = configBuilder.comment("If Randomized Degrees is enabled, this value dictates the smallest possible random number for flip degrees.")
                    .comment("NOTE: This value MUST be smaller than the configured maximum random degrees value.")
                    .defineInRange("min_random_degrees", 20.0D, 0, 10000.0D);

            this.scaleEnabled = configBuilder.comment("(WIP) Toggles mob scaling; altering the mob's model scale.")
                    .define("scale_enabled", false);

            this.scale = configBuilder.comment("(WIP) Shrinks or inflates the mob. Values greater than 1 scales the mob up while values less than 1 scales them down (values must be greater than zero).")
                            .defineInRange("scale", 1.0D, 0.00001D, 1000.0D);

            configBuilder.pop();
        }

        public boolean deathFlipDegreesEnabled() {
            return this.deathFlipDegreesEnabled.get();
        }

        public float getDeathFlipDegrees() {
            return (float) this.deathFlipDegrees.get().doubleValue();
        }

        public boolean randomizeDegreesEnabled() {
            return this.randomizeDegrees.get();
        }

        public float getMaxRandomDegrees() {
            return (float) this.maxRandomDegrees.get().doubleValue();
        }

        public float getMinRandomDegrees() {
            return (float) this.minRandomDegrees.get().doubleValue();
        }

        public boolean scaleEnabled() {
            return this.scaleEnabled.get();
        }

        public float getScale() {
            return (float) this.scale.get().doubleValue();
        }
    }
}
