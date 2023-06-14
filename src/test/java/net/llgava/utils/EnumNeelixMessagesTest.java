package net.llgava.utils;

import net.llgava.utils.NeelixMessages;
import org.junit.Assert;
import org.junit.Test;

public class EnumNeelixMessagesTest {
  private static final NeelixMessages MESSAGE = NeelixMessages.INVALID_FILE_EXTENSION;

  @Test
  public void getByIndexTest() {
    NeelixMessages message = NeelixMessages.findByIndex(0);
    NeelixMessages nullValue = NeelixMessages.findByIndex(10000);

    Assert.assertEquals(message, MESSAGE);
    Assert.assertNull(nullValue);

  }

  @Test
  public void getMessageTest() {
    Assert.assertEquals(MESSAGE.getMessage(), "The file doesn't have a valid extension. Please, use '{0}' extension.");
  }
}
