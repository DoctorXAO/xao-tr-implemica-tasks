package Task2;

public class SourceDestination {
    private City source;
    private City destination;

    public void setSource(City source) {
        this.source = source;
    }

    public City getSource() {
        return source;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public City getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "SourceDestination{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
}
