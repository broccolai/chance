package love.broccolai.chance;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

final class ChanceImplementation<R, C> implements Chance<R, C> {

    private final Map<LootTable<R>, Collection<Modifier<C>>> lootTables;

    ChanceImplementation(
            final Map<LootTable<R>, Collection<Modifier<C>>> lootTables
    ) {
        this.lootTables = lootTables;
    }

    @Override
    public Collection<? extends R> roll(final C context) {
        return this.lootTables.entrySet()
                .stream()
                .map(entry -> this.handleRun(context, entry))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Collection<? extends R> handleRun(
            final C context,
            final Map.Entry<LootTable<R>, Collection<Modifier<C>>> entry
    ) {
        return new ChanceRun<>(context, entry.getKey(), entry.getValue()).run();
    }

}
