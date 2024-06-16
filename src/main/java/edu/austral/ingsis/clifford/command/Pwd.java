package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Result;

public class Pwd implements Command {
  @Override
  public boolean isNeeded(String command) {
    return command.equals("pwd");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    if (isNeeded(command)) {
      StringBuilder builder = new StringBuilder();
      Directory node = actualNode;
      getAbsolutePath(builder, node);
      return new Result(builder.toString(), node);
    } else {
      return new Result("There are no options for command pwd.", actualNode);
    }
  }

  private void getAbsolutePath(StringBuilder builder, Directory node) {
    if (node.getName() == "/") {
    } else {
      builder.insert(0, "/" + node.getName());
      getAbsolutePath(builder, node.getPrevousNode());
    }
  }
}
