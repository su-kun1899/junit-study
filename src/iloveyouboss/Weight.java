package iloveyouboss;

/**
 * 重み付けを管理するenum
 *
 * Created by koji on 2016/07/27.
 */
public enum Weight {
  MustMatch(Integer.MAX_VALUE),
  VeryImportant(5000),
  Important(1000),
  WouldPrefer(100),
  DontCare(0);

  private int value;

  Weight(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
