package genes;

public class Connection {

    public float weight;
    public boolean enabled;
    public Node nodeFrom;
    public Node nodeTo;

    public Connection(Node nodeTo, Node nodeFrom, float weight, boolean enabled) {
        this.nodeTo = nodeTo;
        this.nodeFrom = nodeFrom;
        this.weight = weight;
        this.enabled = enabled;
    }

    public Connection(Node nodeTo, Node nodeFrom) {
        this(nodeTo, nodeFrom, 1f, true);
    }

}
