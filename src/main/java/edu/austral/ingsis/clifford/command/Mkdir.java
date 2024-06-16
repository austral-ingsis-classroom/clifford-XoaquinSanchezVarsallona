package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Result;

public class Mkdir implements Command {
  public boolean isNeeded(String command) {
    return command.equals("mkdir");
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    String directory = split[1];
    actualNode.addNode(new Directory(directory, actualNode));
    return new Result("'" + directory + "'" + " directory created", actualNode);
  }
}
