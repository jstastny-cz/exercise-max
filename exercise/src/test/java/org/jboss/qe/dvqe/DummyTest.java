package org.jboss.qe.dvqe;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;



public class DummyTest {
  @Test
  public void test() {
	  assertNotEquals(15,"15","Testing 15 and \"15\"");
	  assertEquals((int) 15,(int) Integer.valueOf(15),"Testing 15 and Integer.valueOf(15)");
	  assertNotEquals(15,String.valueOf(15),"Testing 15 and String.valueOf(15)");
	  assertNotEquals(Integer.valueOf(15),"15","Testing Integer.valueOf(15) and \"15\"");
	  assertEquals(String.valueOf(15),"15","Testing String.valueOf(15) and \"15\"");
	  assertNotEquals(String.valueOf(15),Integer.valueOf(15),"Testing String.valueOf(15) and Integer.valueOf(15)");
	
  }
}
