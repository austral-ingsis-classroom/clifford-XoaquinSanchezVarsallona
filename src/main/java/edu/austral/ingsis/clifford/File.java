package edu.austral.ingsis.clifford;

public class File implements Node {
  Directory prevous;
  String name;
  String dot;

  public File(String name, String dot, Directory prevous) {
    this.name = name;
    this.dot = dot;
    this.prevous = prevous;
  }

  public Directory getPrevous() {
    return prevous;
  }

  @Override
  public Result execute(String command) {
    return null;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getFullName() {
    return name + "." + dot;
  }
}
