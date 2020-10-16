package genes;

public class Connection {

    public float weight;
    public boolean enabled;
    public Node nodeFrom;
    public Node nodeTo;

    public Connection(Node nodeFrom, Node nodeTo, float weight, boolean enabled) {
        this.nodeTo = nodeTo;
        this.nodeFrom = nodeFrom;
        this.weight = weight;
        this.enabled = enabled;
    }

    public Connection(Node nodeFrom, Node nodeTo) {
        this(nodeFrom, nodeTo, (float) Math.random() * 2 - 1, true);
    }

}
