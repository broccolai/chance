package love.broccolai.chance;

import java.util.Map;

final class HashLootTable<R> extends AbstractLootTable<R> {

    private final Map<R, Likelihood> rewards;

    HashLootTable(final Map<R, Likelihood> rewards) {
        this.rewards = rewards;
    }

    @Override
    public Map<R, Likelihood> rewards() {
        return this.rewards;
    }

}
