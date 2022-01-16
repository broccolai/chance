package love.broccolai.chance;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractLootTable<R> implements LootTable<R> {

    protected final Map<R, Likelihood> rewards;

    AbstractLootTable(final Map<R, Likelihood> rewards) {
        this.rewards = rewards;
    }

    @Override
    public final LootTable<R> map(final Function<Likelihood, Likelihood> function) {
        Map<R, Likelihood> modifiedRewards = new HashMap<>(this.rewards);
        modifiedRewards.replaceAll((reward, likelihood) -> function.apply(likelihood));

        return new HashLootTable<>(modifiedRewards);
    }

    @Override
    public final LootTable<R> filter(final Predicate<Likelihood> predicate) {
        Predicate<Likelihood> negatedPredicate = predicate.negate();
        Map<R, Likelihood> modifiedRewards = new HashMap<>(this.rewards);

        modifiedRewards.values().removeIf(negatedPredicate);

        return new HashLootTable<>(modifiedRewards);
    }

}
