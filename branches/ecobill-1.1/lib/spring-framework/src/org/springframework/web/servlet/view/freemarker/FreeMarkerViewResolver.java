/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.view.freemarker;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

/**
 * Convenience subclass of UrlBasedViewResolver that supports FreeMarkerView
 * (i.e. FreeMarker templates) and custom subclasses of it.
 *
 * <p>The view class for all views generated by this resolver can be specified
 * via setViewClass. See UrlBasedViewResolver's javadocs for details.
 *
 * <p>Note: When chaining ViewResolvers, a FreeMarkerViewResolver always needs
 * to be last, as it will attempt to resolve any view name, no matter whether
 * the underlying resource actually exists.
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see #setViewClass
 * @see #setPrefix
 * @see #setSuffix
 * @see #setRequestContextAttribute
 * @see #setExposeSpringMacroHelpers
 * @see FreeMarkerView
 */
public class FreeMarkerViewResolver extends AbstractTemplateViewResolver {

	/**
	 * Sets default viewClass to FreeMarkerView.
	 * @see #setViewClass
	 */
	public FreeMarkerViewResolver() {
		setViewClass(FreeMarkerView.class);
	}

	/**
	 * Requires FreeMarkerView.
	 * @see FreeMarkerView
	 */
	protected Class requiredViewClass() {
		return FreeMarkerView.class;
	}

}
