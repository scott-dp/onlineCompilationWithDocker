package stud.ntnu.codeCompiler.model;

public class Result {
  private String result;
  public Result(String result) {
    this.result = result;
  }
  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void appendToResult(String stringToAppend) {
    result += stringToAppend;
    result += "\n";
  }
}
