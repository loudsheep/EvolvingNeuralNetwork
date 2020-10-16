package brain;

import genes.Connection;
import genes.Node;

import java.util.ArrayList;
import java.util.Comparator;

public class Brain {

    public int inputNodes;
    public int outputNodes;
    public ArrayList<Node> nodes;
    public ArrayList<Connection> connections;

    public Brain(int inputNodes, int outputNodes) {
        this(inputNodes, outputNodes, 0, 0);
    }

    public Brain(int inputNodes, int outputNodes, int hiddenNodes, int connection) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;

        nodes = new ArrayList<>();
        connections = new ArrayList<>();

        for (int i = 0; i < inputNodes; i++) {
            nodes.add(new Node(Node.Type.INPUT, 0.1f));
        }

        for (int i = 0; i < outputNodes; i++) {
            nodes.add(new Node(Node.Type.OUTPUT, 0.9f));
        }

//        Node from = nodes.get(0);
//        Node to = nodes.get(inputNodes);
//
//        connections.add(new Connection(from, to));
//
//        to = nodes.get(inputNodes + outputNodes - 1);
//        connections.add(new Connection(from, to));
    }

    public float[] query(float[] input) {
        if (input.length != inputNodes)
            throw new RuntimeException("Inputs don't match input nodes: size=" + inputNodes + ", provided=" + input.length);

        ArrayList<Node> inputs = new ArrayList<>();
        ArrayList<Node> hidden = new ArrayList<>();
        ArrayList<Node> output = new ArrayList<>();

        for (Node n : nodes) {
            if (n.type == Node.Type.INPUT) inputs.add(n);
            else if (n.type == Node.Type.OUTPUT) output.add(n);
            else if (n.type == Node.Type.HIDDEN) hidden.add(n);

            n.setConnections(connections);
        }

        hidden.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).outputSum = input[i];
            inputs.get(i).feedForward();
        }

        for (Node n : hidden) {
            n.feedForward();
        }

        float[] out = new float[output.size()];

        for (int i = 0; i < output.size(); i++) {
            output.get(i).feedForward();
            out[i] = output.get(i).outputSum;
        }


        return out;
    }

}
