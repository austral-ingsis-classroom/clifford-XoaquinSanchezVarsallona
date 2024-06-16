package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Node;
import edu.austral.ingsis.clifford.Result;
import java.util.*;

public class Ls implements Command {

  @Override
  public boolean isNeeded(String command) {
    return command.equals("ls");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    List<String> allNodes = getAllNodes(actualNode);
    if (allNodes.isEmpty()) {
      return new Result("", actualNode);
    } else if (split.length != 1 && split[1].contains("--ord")) {
      allNodes = ord(split[1], allNodes);
    }
    return new Result(printList(allNodes).toString(), actualNode);
  }

  private StringBuilder printList(List<String> list) {
    StringBuilder builder = new StringBuilder();
    for (String s : list) {
      if (list.indexOf(s) != list.size() - 1) {
        builder.append(s).append(" ");
      } else {
        builder.append(s);
      }
    }
    return builder;
  }

  private List<String> getAllNodes(Directory actualNode) {
    List<Node> nodes = actualNode.getNodes();
    List<String> allNodes = new ArrayList<>();
    for (Node node : nodes) {
      if (node instanceof Directory) {
        allNodes.add(node.getName());
      } else allNodes.add(node.getFullName());
    }
    return allNodes;
  }

  private List<String> ord(String split, List<String> allNodes) {
    String order = split.split("=")[1];
    ArrayList<String> res = new ArrayList<>(allNodes);
    switch (order) {
      case "asc":
        Collections.sort(res);
        break;
      case "desc":
        res.sort(Collections.reverseOrder());
        break;
      default:
        System.out.println("Can't use --ord option with: " + order);
        return null;
    }
    return res;
  }
}
