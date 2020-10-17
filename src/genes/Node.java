package genes;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    public enum Type {                      // type of node
        INPUT,
        OUTPUT,
        HIDDEN
    }

    public enum ActivationFunction {        // activation function of node, can be evolved
        SIGMOID {
            public float function(float x) {
                return (float) (1 / (1 + Math.exp(-x)));
            }
        },
        STEP {
            public float function(float x) {
                return x <= 0 ? 0 : 1;
            }
        },
        SIN {
            public float function(float x) {
                return (float) Math.sin(x);
            }
        },
        COS {
            public float function(float x) {
                return (float) Math.cos(x);
            }
        },
        TAN {
            public float function(float x) {
                return (float) Math.tan(x);
            }
        },
        TANH {
            public float function(float x) {
                return (float) Math.tanh(x);
            }
        },
        SQR {
            public float function(float x) {
                return x * x;
            }
        },
        SQRT {
            public float function(float x) {
                return (float) Math.sqrt(x);
            }
        };

        public abstract float function(float x);
    }

    public float x;                             // for correct data feed forward order
    public float y;                             // not used util some visualization program
    public Type type;                           // type of node/neuron
    public ActivationFunction function;         // activation function of node
    public float inputSum;                      // used to calculate brain output
    public float outputSum;                     // used to calculate brain output
    public float dopamine;                      // level of node growth/evolution
    public ArrayList<Connection> connections;   // connections going out of this node

    public Node(Type type, ActivationFunction function, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;

        this.function = function;

        inputSum = 0;
        outputSum = 0;

        connections = new ArrayList<>();
    }

    public Node(Type type, float x) {
        this(type, ActivationFunction.SIGMOID, x, 0.5f);
    }

    public void setConnections(ArrayList<Connection> connections) {
        for (Connection c : connections) {
            if (c.nodeFrom == this) {
                this.connections.add(c);
            }
        }
    }

    public void feedForward() {
        outputSum = function.function(inputSum);
        inputSum = 0;

        for (Connection c : connections) {
            if (c.enabled) {
                c.nodeTo.inputSum += outputSum * c.weight;
            }
        }

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
