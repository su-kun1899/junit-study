package iloveyouboss;

/**
 * 条件を管理するクラス
 *
 * Created by koji on 2016/07/27.
 */
public class Criterion implements Scoreable {
  private Weight weight;
  private Answer answer;
  private int score;

  public Criterion(Weight weight, Answer answer) {
    this.weight = weight;
    this.answer = answer;
  }

  public Weight getWeight() {
    return weight;
  }

  public Answer getAnswer() {
    return answer;
  }

  @Override
  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
