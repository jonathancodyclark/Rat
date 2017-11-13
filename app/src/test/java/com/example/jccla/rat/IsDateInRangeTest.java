package com.example.jccla.rat;

import org.junit.Assert;
import org.junit.Test;
import com.example.jccla.rat.Model.Model;

/**
 * Created by mikereilly on 11/13/17.
 */

public class IsDateInRangeTest {
	Model model = Model.getInstance();

	@Test
    public void testYearTooEarly() {
   		String date = "9/24/2010 12:00:00 AM";
    	Assert.assertEquals("Test Year too early", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testYearTooLate() {
   		String date = "9/24/2018 12:00:00 AM";
    	Assert.assertEquals("Test Year too late", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testMonthTooEarly() {
   		String date = "1/24/2015 12:00:00 AM";
    	Assert.assertEquals("Test month too early", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testdayTooEarly() {
   		String date = "9/20/2015 12:00:00 AM";
    	Assert.assertEquals("Test day too early", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testMonthTooLate() {
   		String date = "12/24/2015 12:00:00 AM";
    	Assert.assertEquals("Test month too late", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testDayTooLate() {
   		String date = "10/30/2015 12:00:00 AM";
    	Assert.assertEquals("Test day too late", false, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }

    @Test
    public void testDayInRange() {
   		String date = "10/20/2015 12:00:00 AM";
    	Assert.assertEquals("Test day in range", true, model.isDateInRange(date, 9, 25, 2015, 10, 25, 2015));
    }
}
