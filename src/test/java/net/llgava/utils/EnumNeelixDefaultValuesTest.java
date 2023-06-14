package net.llgava.utils;

import net.llgava.utils.NeelixDefaultValues;
import org.junit.Assert;
import org.junit.Test;

public class EnumNeelixDefaultValuesTest {
  private static final NeelixDefaultValues DEFAULT_VALUE_STRING = NeelixDefaultValues.NEXT_STACK_TITLE;
  private static final NeelixDefaultValues DEFAULT_VALUE_INT = NeelixDefaultValues.NEXT_STACK_SLOT;

  @Test
  public void getByIndexTest() {
    NeelixDefaultValues stringValue = NeelixDefaultValues.findByIndex(0);
    NeelixDefaultValues intStringedValue = NeelixDefaultValues.findByIndex(2);
    NeelixDefaultValues nullValue = NeelixDefaultValues.findByIndex(10000);

    Assert.assertEquals(stringValue, DEFAULT_VALUE_STRING);
    Assert.assertEquals(intStringedValue, DEFAULT_VALUE_INT);
    Assert.assertNull(nullValue);
  }

  @Test
  public void getValueTest() {
    Assert.assertEquals(DEFAULT_VALUE_STRING.getString(), "Next page");
    Assert.assertEquals(DEFAULT_VALUE_INT.getInt(), 8);
  }
}
