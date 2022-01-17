package love.broccolai.chance;

import java.util.Collection;

public interface ChanceBuilder<R, C> {

    ChanceBuilder<R, C> lootTable(LootTable<R> lootTable);

    ChanceBuilder<R, C> lootTable(LootTable<R> lootTable, Collection<Modifier<C>> additionalModifiers);

    ChanceBuilder<R, C> globalModifiers(Collection<Modifier<C>> modifiers);

    Chance<R, C> build();

}
