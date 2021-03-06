/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package love.broccolai.chance;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

class ChanceTest {

    Reward rewardOne = new Reward("one");
    Reward rewardTwo = new Reward("two");

    LootTable<Reward> lootTable = LootTable.ofMap(Map.of(
            this.rewardOne, Likelihood.of(1),
            this.rewardTwo, Likelihood.of(0)
    ));

    @Test
    void chanceBase() {
        Collection<? extends Reward> results = Chance.<Reward, Context>builder()
                .lootTable(this.lootTable)
                .build()
                .roll(new Context());

        assertThat(results).containsExactly(this.rewardOne);
    }

    @Test
    void chanceNone() {
        Modifier<Context> modifier = (context, result) -> result.multiplicative(0);

        Collection<? extends Reward> results = Chance.<Reward, Context>builder()
                .lootTable(this.lootTable)
                .globalModifiers(List.of(modifier))
                .build()
                .roll(new Context());

        assertThat(results).isEmpty();
    }

    @Test
    void chanceAll() {
        Modifier<Context> modifier = (context, result) -> result.additive(1);

        Collection<? extends Reward> results = Chance.<Reward, Context>builder()
                .lootTable(this.lootTable)
                .globalModifiers(List.of(modifier))
                .build()
                .roll(new Context());

        assertThat(results).containsExactlyElementsIn(this.lootTable.rewards().keySet());
    }
}
