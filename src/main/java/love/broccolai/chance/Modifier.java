package love.broccolai.chance;

@FunctionalInterface
public interface Modifier<C> {

    Likelihood modify(C context, Likelihood result);

}
