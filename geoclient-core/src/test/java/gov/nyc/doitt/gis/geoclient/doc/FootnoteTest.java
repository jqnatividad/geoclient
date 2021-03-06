package gov.nyc.doitt.gis.geoclient.doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FootnoteTest
{
	
	@Test
	public void testDefaultConstructor()
	{
		Footnote footnote = new Footnote();
		assertNull(footnote.getSymbol());
		assertEquals(0, footnote.getPosition());
	}

	@Test
	public void testConstructorWithArguments()
	{
		Footnote footnote = new Footnote("*",2);
		assertEquals("*",footnote.getSymbol());
		assertEquals(2, footnote.getPosition());
	}

	@Test
	public void testLinksTo()
	{
		Footnote footnote = new Footnote("*",1);
		Footnote footnoteSameSymbol = new Footnote("*",2);
		Footnote footnoteDifferentSymbol = new Footnote("$",1);
		assertTrue(footnote.linksTo(footnoteSameSymbol));
		assertTrue(footnoteSameSymbol.linksTo(footnote));
		assertFalse(footnote.linksTo(footnoteDifferentSymbol));
		assertFalse(footnoteDifferentSymbol.linksTo(footnote));
	}

	@Test
	public void testToHtml()
	{
		assertEquals("<sup>*</sup>", new Footnote("*",1).toHtml());
	}

}
