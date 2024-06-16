package edu.austral.ingsis.clifford;

public class Result {
  private String result;
  private Directory dir;

  public Result(String result, Directory dir) {
    this.result = result;
    this.dir = dir;
  }

  public String getResult() {
    return result;
  }

  public Directory getDir() {
    return dir;
  }
}
