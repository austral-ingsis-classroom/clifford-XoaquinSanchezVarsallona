package edu.austral.ingsis;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.command.SimpleFileSystem;
import java.util.ArrayList;
import java.util.List;

public class SystemRunner implements FileSystemRunner {
  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystem runner = new SimpleFileSystem();
    List<String> result = new ArrayList<>();
    for (String command : commands) {
      System.out.println(command);
      result.add(runner.execute(command).getResult());
    }
    return result;
  }
  // ['horace' directory created, 'emily' directory created, 'jetta' directory created, moved to
  // directory 'emily', 'elizabeth.txt' file created, 't-bone' directory created, 'elizabeth.txt'
  // file created, t-bone elizabeth.txt, cannot remove 't-bone', is a directory, 't-bone' removed,
  // elizabeth.txt, 'elizabeth.txt' removed, ]> but was: <
  // ['horace' directory created, 'emily' directory created, 'jetta' directory created, moved to
  // directory 'emily', 'elizabeth.txt' file created, 't-bone' directory created, 'elizabeth.txt'
  // file created, elizabeth t-bone, cannot remove 'emily', is a directory, cannot remove 'emily',
  // is a directory, elizabeth t-bone, 'elizabeth.txt' removed, t-bone]

}
