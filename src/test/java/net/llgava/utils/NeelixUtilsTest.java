package net.llgava.utils;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import net.llgava.Neelix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NeelixUtilsTest {

  @Before
  public void setUp() {
    ServerMock server = MockBukkit.mock();
    Neelix neelix = MockBukkit.load(Neelix.class);
  }

  @Test
  public void getServerVersionTest() {
    String version = NeelixUtils.getServerVersion();
    Assert.assertEquals(version, "1.16.2");
  }

  @Test
  public void isMinimumServerVersionTest() {
    Assert.assertTrue(NeelixUtils.isMinimumServerVersion("1.13.0"));
    Assert.assertTrue(NeelixUtils.isMinimumServerVersion("1.16.2"));
    Assert.assertFalse(NeelixUtils.isMinimumServerVersion("1.20.0"));
  }

  @Test
  public void parseMessageTest() {
    String rawMessage = "This is my {0} example using the parsed {1} in a string";
    String expectedMessage = "This is my first example using the parsed value in a string";
    String noOccurrencesMessage = "This is my first example using the parsed value in a string";

    Assert.assertEquals(NeelixUtils.parseMessage(rawMessage, "first", "value"), expectedMessage);
    Assert.assertEquals(NeelixUtils.parseMessage(rawMessage, "first"), rawMessage);
    Assert.assertEquals(NeelixUtils.parseMessage(rawMessage, "first", "value", "invalid"), rawMessage);
    Assert.assertEquals(NeelixUtils.parseMessage(noOccurrencesMessage, "first", "value", "invalid"), noOccurrencesMessage);
  }

  @After
  public void tearDown() { MockBukkit.unmock(); }
}
