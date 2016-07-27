package iloveyouboss;

import org.junit.Test;

/**
 * Profileのテストクラス
 *
 * Created by koji on 2016/07/27.
 */
public class ProfileTest {
  @Test
  public void test() {
    Profile profile = new Profile("Bull Hockey, Inc");
    Question question = new BooleanQuestion(1, "ボーナスは支給されますか？");
    Criteria criteria = new Criteria();
    Answer criteriaAnswer = new Answer(Bool.TRUE, question);
    Criterion criterion = new Criterion(Weight.MustMatch, criteriaAnswer);
    criteria.add(criterion);
  }
}