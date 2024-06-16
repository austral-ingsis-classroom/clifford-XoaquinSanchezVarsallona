package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.Node;
import edu.austral.ingsis.clifford.Result;

public class Touch implements Command {
  @Override
  public boolean isNeeded(String command) {
    return command.equals("touch");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    if (actualNode.getNodeByFullName(split[1]) != null) {
      Node node = actualNode.getNodeByFullName(split[1]);
      actualNode.removeNode(node);
      actualNode.addNode(node);
      return new Result("'" + split[1] + "'" + " file created", actualNode);
    }
    String[] file = split[1].split("\\.");
    File newFile = new File(file[0], file[1], actualNode);
    actualNode.addNode(newFile);
    return new Result("'" + newFile.getFullName() + "'" + " file created", actualNode);
  }
}
