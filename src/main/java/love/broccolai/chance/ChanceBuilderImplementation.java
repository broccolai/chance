package love.broccolai.chance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

final class ChanceBuilderImplementation<R, C> implements ChanceBuilder<R, C> {

    private LootTable<R> lootTable = null;
    private final Collection<Modifier<C>> modifiers = new ArrayList<>();

    @Override
    public ChanceBuilder<R, C> lootTable(final LootTable<R> lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public ChanceBuilder<R, C> modifiers(Collection<Modifier<C>> modifiers) {
        this.modifiers.addAll(modifiers);
        return this;
    }

    @Override
    public Chance<R, C> build() {
        Objects.requireNonNull(this.lootTable);
        return new ChanceImplementation<R, C>(this.lootTable, this.modifiers);
    }

}
