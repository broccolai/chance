package love.broccolai.chance;

import java.util.Collection;


final class ChanceImplementation<R, C> implements Chance<R, C> {

    private final LootTable<R> lootTable;
    private final Collection<Modifier<C>> modifiers;

    public ChanceImplementation(LootTable<R> lootTable, Collection<Modifier<C>> modifiers) {
        this.lootTable = lootTable;
        this.modifiers = modifiers;
    }

    @Override
    public Collection<R> roll(C context) {
        return new ChanceRun<>(context, lootTable, modifiers)
                .run();
    }

}
