/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.common.supscan.freeform;

import com.sml.sz.common.supscan.common.Common;
import com.sml.sz.common.supscan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author splat
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
