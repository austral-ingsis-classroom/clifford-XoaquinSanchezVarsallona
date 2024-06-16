package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.Result;

public class SimpleFileSystem implements FileSystem {
  Directory home;

  public SimpleFileSystem() {
    home = new Directory();
  }

  public SimpleFileSystem(Directory path) {
    home = path;
  }

  @Override
  public Result execute(String command) {
    Result res = home.execute(command);
    home = res.getDir();
    return res;
  }
}
