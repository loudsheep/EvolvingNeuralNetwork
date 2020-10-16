package genes;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    public enum Type {                      // type of node
        INPUT,
        OUTPUT,
        HIDDEN
    }

    public enum ActivationFunction {        // activation function of node, can be evolved
        SIGMOIS,
        STEP,
        SIN,
        COS,
        TAN,
        SQR,
        SQRT
    }

    public float x;                             // for correct data feed forward order
    public float y;                             // not used util some visualization program
    public Type type;                           // type of node/neuron
    public float inputSum;                      // used to calculate brain output
    public float outputSum;                     // used to calculate brain output
    public float dopamine;                      // level of node growth/ evolution
    public ArrayList<Connection> connections;   // connections going out of this node

    public Node(Type type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;

        inputSum = 0;
        outputSum = 0;

        connections = new ArrayList<>();
    }

    public Node(Type type, float x) {
        this(type, x, 0.5f);
    }

    public void setConnections(ArrayList<Connection> connections) {
        for (Connection c : connections) {
            if (c.nodeFrom == this) {
                this.connections.add(c);
            }
        }
    }

    public void feedForward() {
        outputSum = activationFunction(inputSum);
        inputSum = 0;

        if (type != Type.OUTPUT) {
            for (Connection c : connections) {
                if (c.enabled) {
                    c.nodeTo.inputSum += outputSum * c.weight;
                }
            }
        }
    }

    public float activationFunction(float x) {
        return (float) (1 / (1 + Math.exp(-x)));
    }

    @Override
    public Node clone() {       // used to clone object, do not implement until class is finished
        return null;
    }

    @Override
    public int compareTo(Node o) {      // compares two Nodes (positions)
        if (x < o.x) return -1;
        if (x > o.x) return 1;

        return 0;
    }

}
