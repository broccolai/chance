package love.broccolai.chance;

public interface Likelihood {

    static Likelihood of(double value) {
        return new LikelihoodImplementation(value);
    }

    double value();

    Likelihood multiplicative(double multiplier);

    Likelihood additive(double addition);

}
