package love.broccolai.chance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class ChanceBuilderImplementation<R, C> implements ChanceBuilder<R, C> {

    private final Collection<Modifier<C>> globalModifiers = new ArrayList<>();
    private final Map<LootTable<R>, Collection<Modifier<C>>> lootTables = new HashMap<>();

    @Override
    public ChanceBuilder<R, C> lootTable(final LootTable<R> lootTable) {
        return this.lootTable(lootTable, Collections.emptyList());
    }

    @Override
    public ChanceBuilder<R, C> lootTable(
            final LootTable<R> lootTable,
            final Collection<Modifier<C>> additionalModifiers
    ) {
        this.lootTables.put(lootTable, additionalModifiers);
        return this;
    }

    @Override
    public ChanceBuilder<R, C> globalModifiers(final Collection<Modifier<C>> modifiers) {
        this.globalModifiers.addAll(modifiers);
        return this;
    }

    @Override
    public Chance<R, C> build() {
        this.lootTables.replaceAll((table, specificModifiers) -> this.merge(this.globalModifiers, specificModifiers));
        return new ChanceImplementation<R, C>(this.lootTables);
    }

    private <T> Collection<T> merge(final Collection<T> listOne, final Collection<T> listTwo) {
        Collection<T> output = new ArrayList<>(listOne.size() + listTwo.size());
        output.addAll(listOne);
        output.addAll(listTwo);

        return output;
    }

}
