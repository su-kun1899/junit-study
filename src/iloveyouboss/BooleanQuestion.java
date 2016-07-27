package iloveyouboss;

/**
 * はい・いいえ形式の質問クラス
 *
 * Created by koji on 2016/07/27.
 */
public class BooleanQuestion extends Question {
  public BooleanQuestion(int id, String text) {
    super(text, new String[]{"No", "Yes"}, id);
  }

  @Override
  public boolean match(int expected, int actual) {
    return expected == actual;
  }
}
