package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder implements Command {
  private List<Command> commands =
      new ArrayList<>(
          Arrays.asList(new Ls(), new Pwd(), new Cd(), new Mkdir(), new Touch(), new Rm()));

  @Override
  public boolean isNeeded(String command) {
    // Funcion que te permite saber si existe ese commando o no.
    String[] split = command.split(" ");
    for (Command commandItem : commands) {
      if (commandItem.isNeeded(split[0])) return true;
    }
    System.out.print("There is no command with that name.");
    return false;
  }

  @Override
  public Result execute(String command, Directory actualNode) {
    String[] split = command.split(" ");
    for (Command commandItem : commands) {
      if (commandItem.isNeeded(split[0])) return commandItem.execute(command, actualNode);
    }
    return null;
  }
}
