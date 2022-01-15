package love.broccolai.chance;

final class LikelihoodImplementation implements Likelihood {

    private final double value;

    public LikelihoodImplementation(final double value) {
        this.value = value;
    }

    @Override
    public double value() {
        return this.value;
    }

    @Override
    public Likelihood multiplicative(double multiplier) {
        double modified = this.value * multiplier;
        return new LikelihoodImplementation(modified);
    }

    @Override
    public Likelihood additive(double addition) {
        double modified = this.value + addition;
        return new LikelihoodImplementation(modified);
    }

}
