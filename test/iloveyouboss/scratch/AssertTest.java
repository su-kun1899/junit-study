package iloveyouboss.scratch;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import sun.management.counter.AbstractCounter;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AssertTest {

  class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
  }

  class Account {
    int balance;
    String name;

    public Account(String name) {
      this.name = name;
    }

    void deposit(int dollars) {
      balance += dollars;
    }

    void withdraw(int dollars) {
      if ((balance < dollars)) {
        throw new InsufficientFundsException("balance only " + balance);
      }
      balance -= dollars;
    }

    public int getBalance() {
      return balance;
    }

    public String getName() {
      return name;
    }

    public boolean hasPositiveBalance() {
      return balance > 0;
    }
  }

  class Customer {
    List<Account> accounts = new ArrayList<>();

    void add(Account account) {
      accounts.add(account);
    }

    Iterator<Account> getAccounts() {
      return accounts.iterator();
    }
  }

  private Account account;

  @Before
  public void createAccount() {
    account = new Account("an account name");
  }

  @Test
  public void hasPositiveBalance() {
    account.deposit(50);
    assertTrue(account.hasPositiveBalance());
  }

  @Test
  public void depositIncreasesBalance() {
    int initialBalance = account.getBalance();
    account.deposit(100);
    assertTrue(account.getBalance() > initialBalance);
  }

  @Ignore
  @Test
  public void comparesArraysFailing() {
    assertThat(new String[]{"a", "b", "c"}, equalTo(new String[]{"a", "b"}));
  }

  @Test
  public void comparesArraysPassing() {
    assertThat(new String[]{"a", "b"}, equalTo(new String[]{"a", "b"}));
  }

  @Ignore
  @Test
  public void comparesCollectionsFailing() {
    assertThat(Arrays.asList(new String[]{"a"}), equalTo(Arrays.asList(new String[]{"a", "ab"})));
  }

  @Test
  public void comparesCollectionsPassing() {
    assertThat(Arrays.asList(new String[]{"a"}), equalTo(Arrays.asList(new String[]{"a"})));
  }

  @Test
  public void testWithWorthlessAssertionComment() {
    account.deposit(50);
    assertThat("account balance is 100", account.getBalance(), equalTo(50));
  }

  @Ignore
  @Test
  public void assertFailure() {
    assertTrue(account.getName().startsWith("xyz"));
  }

  @Ignore
  @Test
  public void matchesFailure() {
    assertThat(account.getName(), startsWith("xyz"));
  }

  @Test
  public void variousMatcherTests() {
    Account account = new Account("my big fat acct");
    assertThat(account.getName(), is(equalTo("my big fat acct")));

    assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));

    assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));

    assertThat(account.getName(), not(equalTo("plunderings")));

    assertThat(account.getName(), is(not(nullValue())));
    assertThat(account.getName(), is(notNullValue()));

    assertThat(account.getName(), isA(String.class));

    assertThat(account.getName(), is(notNullValue())); // not helpful
    assertThat(account.getName(), equalTo("my big fat acct"));
  }

  @Test
  public void sameInstanceAssert() {
    Account a = new Account("a");
    Account aPrime = new Account("a");

    assertThat(a, not(sameInstance(aPrime)));
  }

  @Test
  public void moreMatcherTests() {
    Account account = new Account(null);
    assertThat(account.getName(), is(nullValue()));
  }

  @Test
  public void items() {
    List<String> names = new ArrayList<>();
    names.add("Moe");
    names.add("Larry");
    names.add("Curly");

    assertThat(names, hasItem("Curly"));
    assertThat(names, hasItems("Curly", "Moe"));
    assertThat(names, hasItems(endsWith("y")));
    assertThat(names, hasItems(endsWith("y"), startsWith("C")));

    assertThat(names, not(everyItem(endsWith("y"))));
  }

  @Test
  @Ignore
  public void classicAssertions() {
    Account account = new Account("acct namex");
    assertEquals("acct name", account.getName());
  }

  @Test(expected = InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch() {
    account.withdraw(100);
  }

  @Test
  public void throwsWhenWithdrawingTooMuchTry() {
    try {
      account.withdraw(100);
      fail();
    } catch (InsufficientFundsException expected) {
      assertThat(expected.getMessage(), equalTo("balance only 0"));
    }
  }
}
