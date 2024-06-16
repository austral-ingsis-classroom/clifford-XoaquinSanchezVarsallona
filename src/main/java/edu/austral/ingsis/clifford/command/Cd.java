package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Node;
import edu.austral.ingsis.clifford.Result;
import java.util.Arrays;

public class Cd implements Command {
  @Override
  public boolean isNeeded(String command) {
    return command.equals("cd");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    String[] directory = split[1].split("/");
    if (directory.length == 0) {
      actualNode = fromRoot(actualNode);
      return cd(directory, actualNode);
    }
    return switch (directory[0]) {
      case ".." -> {
        Directory prevousNode = actualNode.getPrevousNode();
        if (prevousNode == null) {
          yield new Result("moved to directory " + "'" + actualNode.getName() + "'", actualNode);
        }
        yield cd(Arrays.copyOfRange(directory, 1, directory.length), prevousNode);
      }
      case "." -> {
        actualNode = actualNode;
        yield cd(directory, actualNode);
      }
      default -> cd(directory, actualNode);
    };
  }

  private Result cd(String[] directory, Directory actualNode) {
    Directory path = actualNode;
    if (directory.length == 0) {
    } else {
      for (String dir : directory) {
        Node node = path.getNode(dir);
        if (node == null) {
          return new Result("'" + dir + "' directory does not exist", actualNode);
        } else if (node instanceof Directory) {
          path = (Directory) node;
        } else {
          System.out.print("Can't enter file: " + path.getName() + " using command cd ");
        }
      }
    }
    String message = "moved to directory " + "'" + path.getName() + "'";
    return new Result(message, path);
  }

  private Directory fromRoot(Directory actualNode) {
    if (actualNode.getPrevousNode() == null) return actualNode;
    else return fromRoot(actualNode.getPrevousNode());
  }
}
