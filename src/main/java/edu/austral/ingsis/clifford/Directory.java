package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.CommandBuilder;
import java.util.ArrayList;
import java.util.List;

public class Directory implements Node {
  private String name;
  private Directory prevousNode;
  private List<Node> nodes;

  public Directory() {
    name = "/";
    nodes = new ArrayList<>();
  }

  public Directory(String name, Directory prevousNode) {
    this.name = name;
    this.prevousNode = prevousNode;
    this.nodes = new ArrayList<>();
  }

  private final CommandBuilder builder = new CommandBuilder();

  public void addNode(Node node) {
    this.nodes.add(node);
  }

  public void removeNode(Node node) {
    this.nodes.remove(node);
  }

  public Directory getPrevousNode() {
    return prevousNode;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public Node getNode(String name) {
    for (Node node : nodes) {
      if (node.getName().equals(name)) return node;
    }
    return null;
  }

  @Override
  public Result execute(String command) {
    if (builder.isNeeded(command)) return builder.execute(command, this);
    return null;
  }

  @Override
  public String getName() {
    return name;
  }

  public Node getNodeByFullName(String fullName) {
    for (Node node : nodes) {
      if (node.getFullName().equals(fullName)) return node;
    }
    return null;
  }

  @Override
  public String getFullName() {
    return name + "-dir";
  }
}
