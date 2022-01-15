package love.broccolai.chance;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface LootTable<R> {

    static <R> LootTable<R> ofMap(Map<R, Likelihood> map) {
        return new HashLootTable<>(map);
    }

    Map<R, Likelihood> rewards();

    LootTable<R> map(Function<Likelihood, Likelihood> function);

    LootTable<R> filter(Predicate<Likelihood> predicate);

}
