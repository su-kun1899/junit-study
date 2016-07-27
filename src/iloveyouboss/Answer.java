package iloveyouboss;

/**
 * 質問への回答を管理するクラス
 *
 * Created by koji on 2016/07/27.
 */
public class Answer {
  private int i;
  private Question question;

  public Answer(int i, Question question) {
    this.i = i;
    this.question = question;
  }

  public Answer(Question characteristic, String matchingValue) {
    this.question = characteristic;
    this.i = characteristic.indexOf(matchingValue);
  }

  public String getQuestionText() {
    return question.getText();
  }

  @Override
  public String toString() {
    return String.format("%s %s", question.getText(), question.getAnswerChoice(i));
  }

  public boolean match(int expected) {
    return question.match(expected, i);
  }

  public boolean match(Answer otherAnswer) {
    return question.match(i, otherAnswer.i);
  }

  public Question getCharacteristic() {
    return question;
  }
}
