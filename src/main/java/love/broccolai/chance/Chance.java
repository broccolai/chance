package love.broccolai.chance;

import java.util.Collection;

public interface Chance<R, C> {

    static <R, C> ChanceBuilder<R, C> builder() {
        return new ChanceBuilderImplementation<>();
    }

    Collection<? extends R> roll(C context);

}
