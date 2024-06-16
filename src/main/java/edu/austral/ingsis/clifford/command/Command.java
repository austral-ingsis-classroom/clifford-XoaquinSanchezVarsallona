package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Result;

public interface Command {
  boolean isNeeded(String command);

  Result execute(String command, Directory actualNode);
}
