package brain;

import genes.Connection;
import genes.Node;

import java.util.ArrayList;

public class Brain {

    public int inputNodes;
    public int outputNodes;
    public ArrayList<Node> nodes;
    public ArrayList<Connection> connections;

    public Brain(int inputNodes, int outputNodes) {
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
    }

}
