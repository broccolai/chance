package love.broccolai.chance;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

final class ChanceRun<R, C> {

    private final C context;
    private final LootTable<R> lootTable;
    private final Collection<Modifier<C>> modifiers;

    ChanceRun(
            final C context,
            final LootTable<R> lootTable,
            final Collection<Modifier<C>> modifiers
    ) {
        this.context = context;
        this.lootTable = lootTable;
        this.modifiers = modifiers;
    }

    public Collection<? extends R> run() {
        return this.lootTable
                .map(this::applyModifiersWithContext)
                .filter(this::roll)
                .rewards()
                .keySet();
    }

    private Likelihood applyModifiersWithContext(final Likelihood likelihood) {
        Likelihood modifiedLikelihood = likelihood;

        for (Modifier<C> modifier : this.modifiers) {
            modifiedLikelihood = modifier.modify(this.context, modifiedLikelihood);
        }

        return modifiedLikelihood;
    }

    private boolean roll(final Likelihood likelihood) {
        double rolledChance = ThreadLocalRandom.current().nextDouble();
        return likelihood.value() > rolledChance;
    }

    private int simulateRolls(final Likelihood likelihood, int rolls) {
        // Simulate 'rolls' amount of times to obtain an object with a success chance of 'likelihood'.
        // If possible, will normalize the distribution and obtain values therefrom.

        double p = likelihood.value();
        double mean = rolls * p;
        double stddv = Math.sqrt(rolls * p * (1 - p));

        boolean normalized = false;
        double threeStddv;
        if (p != 0) {
            threeStddv = 9 * ((1 - p) / p);
        } else {
            threeStddv = 0;
        }
        if (rolls > threeStddv) {
            normalized = true;
        }

        int amount;
        if (normalized) {
            Random random = new Random();
            do {
                amount = (int) (random.nextGaussian() * stddv + mean + 0.5);
            } while (amount < 0 || amount > rolls);
        } else {
            // If it can not be normalized, do it the old fashioned way.

            amount = 0;
            for (int i = 0; i < rolls; i++) {
                if (Math.random() < p) {
                    amount++;
                }
            }
        }

        return amount;
    }

}
