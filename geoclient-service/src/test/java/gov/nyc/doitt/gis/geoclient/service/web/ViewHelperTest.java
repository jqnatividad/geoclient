package gov.nyc.doitt.gis.geoclient.service.web;

import static org.junit.Assert.assertEquals;
import gov.nyc.doitt.gis.geoclient.doc.Description;
import gov.nyc.doitt.gis.geoclient.doc.FunctionDocumentation;
import gov.nyc.doitt.gis.geoclient.doc.GroupDocumentation;
import gov.nyc.doitt.gis.geoclient.doc.GroupMember;
import gov.nyc.doitt.gis.geoclient.doc.ItemDocumentation;
import gov.nyc.doitt.gis.geoclient.doc.ItemDocumentationSupport;

import org.junit.Before;
import org.junit.Test;

public class ViewHelperTest
{
	private ViewHelper vh;
	private ItemDocumentation doc;

	@Before
	public void setUp()
	{
		this.vh = new ViewHelper();
		this.doc = new ItemDocumentation("abc");
	}
	@Test
	public void testMemberText()
	{
		GroupMember member = new GroupMember();
		member.setId("gMember");
		member.setSizeIndicator(true);
		assertEquals("gMember", vh.memberText(null, member));
		member.setSizeIndicator(false);
		GroupDocumentation group = new GroupDocumentation();
		group.setMax(12);
		assertEquals("gMember1 to 12", vh.memberText(group, member));
	}
	
	@Test
	public void testSectionHeaderText()
	{
		FunctionDocumentation functionDocumentation = new FunctionDocumentation();
		assertEquals("10.2 - null (Function null)", vh.sectionHeaderText(10, 2,functionDocumentation));
		functionDocumentation.setId("abc");
		assertEquals("10.2 - null (Function abc)", vh.sectionHeaderText(10, 2,functionDocumentation));
		functionDocumentation.setDisplayName("xyz");
		assertEquals("10.2 - xyz (Function abc)", vh.sectionHeaderText(10, 2,functionDocumentation));
	}

	@Test
	public void testSectionAnchor()
	{
		assertEquals("section-10.2", vh.sectionAnchor(10, 2));
	}

	@Test
	public void testSectionNumber()
	{
		assertEquals("10.2", vh.sectionNumber(10, 2));
	}

	@Test
	public void testItemAnchor()
	{
		assertEquals("item-" + doc.getId(), vh.itemAnchor(doc));
	}

	@Test
	public void testHrefStringArgument()
	{
	  assertEquals("#null",vh.href((String)null));
		assertEquals("#xyz", vh.href("xyz"));
		assertEquals("http://xyz",vh.href("http://xyz"));
    assertEquals("hTtp://Xyz.com",vh.href("hTtp://Xyz.com"));
    assertEquals("https://xyz",vh.href("https://xyz"));
    assertEquals("HTTPS://xyz",vh.href("HTTPS://xyz"));
	}
	
	@Test
	public void testHrefItemDocumentationArgument()
	{
		assertEquals("#item-" + doc.getId(), vh.href(doc));
	}

	@Test
	public void testHrefItemDocumentationSupportArgument()
	{
		ItemDocumentationSupport ids = new ItemDocumentationSupport();
		ids.setItemDocumentation(doc);
		assertEquals("#item-" + doc.getId(), vh.href(ids));
	}

	@Test
	public void testSummarize()
	{
		assertEquals("", vh.summarize(doc, 4, "..."));
		doc.setDescription(new Description("123"));
		assertEquals("123", vh.summarize(doc, 4, "..."));
		doc.setDescription(new Description("1234"));
		assertEquals("1234...", vh.summarize(doc, 4, "..."));
		doc.setDescription(new Description("12345"));
		assertEquals("1234...", vh.summarize(doc, 4, "..."));
	}

}
