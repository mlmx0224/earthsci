/*******************************************************************************
 * Copyright 2013 Geoscience Australia
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package au.gov.ga.earthsci.layer.intent;

import gov.nasa.worldwind.Factory;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;

import java.net.URL;

import org.w3c.dom.Document;

import au.gov.ga.earthsci.core.intent.AbstractXmlRetrieveIntentHandler;
import au.gov.ga.earthsci.intent.IIntentCallback;
import au.gov.ga.earthsci.intent.Intent;
import au.gov.ga.earthsci.worldwind.common.util.AVKeyMore;

/**
 * {@link IXmlLoader} implementation for layer and elevation model documents.
 * 
 * @author Michael de Hoog (michael.dehoog@ga.gov.au)
 */
public class LayerXmlIntentHandler extends AbstractXmlRetrieveIntentHandler
{
	@Override
	protected void handle(Document document, URL url, Intent intent, IIntentCallback callback)
	{
		AVList params = new AVListImpl();
		params.setValue(AVKeyMore.CONTEXT_URL, url);
		try
		{
			Factory factory = (Factory) WorldWind.createConfigurationComponent(AVKey.LAYER_FACTORY);
			Object result = factory.createFromConfigSource(document.getDocumentElement(), params);
			callback.completed(result, intent);
		}
		catch (Exception e)
		{
			callback.error(e, intent);
		}
	}
}
