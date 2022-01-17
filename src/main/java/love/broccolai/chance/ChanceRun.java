package love.broccolai.chance;

import java.util.Collection;
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

}
