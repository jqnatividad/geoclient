/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nyc.doitt.gis.geoclient.service.search.request;

import gov.nyc.doitt.gis.geoclient.service.search.InputValue;


public abstract class CountyRequest extends Request
{
	private InputValue boroughInputValue;
	private InputValue zipInputValue;
	
	public CountyRequest()
	{
		super();
	}
	
	public CountyRequest(int level, InputValue borough, InputValue zip)
	{
		super(level);
		this.boroughInputValue = borough;
		this.zipInputValue = zip;
	}

	public String getBorough()
	{
		return stringValueOrNull(boroughInputValue);
	}

	public String getZip()
	{
		return stringValueOrNull(zipInputValue);
	}

	public InputValue getBoroughInputValue()
	{
		return boroughInputValue;
	}

	public void setBoroughInputValue(InputValue boroughInputValue)
	{
		this.boroughInputValue = boroughInputValue;
	}

	public InputValue getZipInputValue()
	{
		return zipInputValue;
	}

	public void setZipInputValue(InputValue zipInputValue)
	{
		this.zipInputValue = zipInputValue;
	}

	@Override
	public boolean containsAssignedValue()
	{
		return isAssigned(boroughInputValue) || isAssigned(zipInputValue);
	}
	
}
