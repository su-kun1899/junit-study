package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Profileのテストクラス
 *
 * Created by koji on 2016/07/27.
 */
public class ProfileTest {
  private Profile profile;
  private BooleanQuestion question;
  private Criteria criteria;

  @Before
  public void create() {
    profile = new Profile("Bull Hockey, Inc");
    question = new BooleanQuestion(1, "ボーナスは支給されますか？");
    criteria = new Criteria();
  }

  @Test
  public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
    // Arrange
    profile.add(new Answer(Bool.FALSE, question));
    criteria.add(new Criterion(Weight.MustMatch, new Answer(Bool.TRUE, question)));

    // Act
    boolean matches = profile.matches(criteria);

    // Assert
    assertFalse(matches);
  }

  @Test
  public void matchAnswersTrueForAnyDontCareCriteria() {
    // Arrange
    Answer profileAnswer = new Answer(Bool.FALSE, question);
    profile.add(profileAnswer);
    Criteria criteria = new Criteria();
    Answer criteriaAnswer = new Answer(Bool.TRUE, question);
    Criterion criterion = new Criterion(Weight.DontCare, criteriaAnswer);
    criteria.add(criterion);

    // Act
    boolean matches = profile.matches(criteria);

    // Assert
    assertTrue(matches);
  }
}