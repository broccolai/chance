package love.broccolai.chance;

final class LikelihoodImplementation implements Likelihood {

    private final double value;

    LikelihoodImplementation(final double value) {
        this.value = value;
    }

    @Override
    public double value() {
        return this.value;
    }

    @Override
    public Likelihood multiplicative(final double multiplier) {
        double modified = this.value * multiplier;
        return new LikelihoodImplementation(modified);
    }

    @Override
    public Likelihood additive(final double addition) {
        double modified = this.value + addition;
        return new LikelihoodImplementation(modified);
    }

}
