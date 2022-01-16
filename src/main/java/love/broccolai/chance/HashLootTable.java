package love.broccolai.chance;

import java.util.Map;

final class HashLootTable<R> extends AbstractLootTable<R> {

    HashLootTable(final Map<R, Likelihood> rewards) {
        super(rewards);
    }

    @Override
    public Map<R, Likelihood> rewards() {
        return this.rewards;
    }

}
