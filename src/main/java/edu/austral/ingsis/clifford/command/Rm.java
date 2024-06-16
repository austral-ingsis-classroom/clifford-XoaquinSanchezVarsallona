package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.Node;
import edu.austral.ingsis.clifford.Result;
import java.util.List;

public class Rm implements Command {
  @Override
  public boolean isNeeded(String command) {
    return command.equals("rm");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    if (split.length == 2) {
      return rm(split[1], actualNode);
    } else if (split.length == 3 && split[1].contains("--recursive")) {
      Node dir = actualNode.getNode(split[2]);
      if (dir instanceof Directory) {
        recursive((Directory) dir);
      } else rm(dir.getFullName(), actualNode);
      return new Result("'" + dir.getName() + "' removed", actualNode);
    } else return rm(actualNode.getFullName(), actualNode);
  }

  private Result rm(String split, Directory actualNode) {
    Node node = actualNode.getNodeByFullName(split);
    if (actualNode.getFullName().equals(split)) {
      node = actualNode.getNodes().get(0);
    }
    if (node instanceof Directory) {
      return new Result("cannot remove '" + node.getName() + "', is a directory", actualNode);
    } else {
      assert node instanceof File;
      actualNode.removeNode(node);
      return new Result("'" + (node).getFullName() + "' removed", actualNode);
    }
  }

  private void recursive(Directory node) {
    while (!node.getNodes().isEmpty()) {
      List<Node> nodes = node.getNodes();
      if (nodes.get(0) instanceof Directory) {
        recursive((Directory) nodes.get(0));
      } else if (nodes.get(0) instanceof File) {
        nodes.remove(0);
      }
    }
    node.getPrevousNode().removeNode(node);
  }
}
