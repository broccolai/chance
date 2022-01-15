package love.broccolai.chance;

import java.util.Collection;

public interface Chance<R, C> {

    static <R, C> ChanceBuilder<R, C> builder() {
        return new ChanceBuilderImplementation<>();
    }

    Collection<R> roll(C context);

}
