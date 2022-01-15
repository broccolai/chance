package love.broccolai.chance;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

final class HashLootTable<R> implements LootTable<R> {

    private final Map<R, Likelihood> rewards;

    public HashLootTable(final Map<R, Likelihood> rewards) {
        this.rewards = rewards;
    }

    @Override
    public Map<R, Likelihood> rewards() {
        return this.rewards;
    }

    @Override
    public LootTable<R> map(Function<Likelihood, Likelihood> function) {
        Map<R, Likelihood> modifiedRewards = new HashMap<>(this.rewards);
        modifiedRewards.replaceAll((reward, likelihood) -> function.apply(likelihood));

        return new HashLootTable<>(modifiedRewards);
    }

    @Override
    public LootTable<R> filter(Predicate<Likelihood> predicate) {
        Predicate<Likelihood> negatedPredicate = predicate.negate();
        Map<R, Likelihood> modifiedRewards = new HashMap<>(this.rewards);

        modifiedRewards.values().removeIf(negatedPredicate);

        return new HashLootTable<>(modifiedRewards);
    }

}
