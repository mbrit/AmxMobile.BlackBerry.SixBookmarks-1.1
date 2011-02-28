package com.amxmobile.SixBookmarks.Runtime;

import java.util.Enumeration;
import java.util.Hashtable;

import net.rim.device.api.i18n.MessageFormat;

public class XmlSerializer
{
	private StringBuffer _builder = new StringBuffer();
	private boolean _firstElement = true;
	private boolean _inElementHeader = false;
	private Hashtable _namespaces = new Hashtable();
	private String _defaultNamespace = null;

	public void startDocument(String string, boolean b)
	{
		// no-op this - we don't need a header...
	}

	public void setPrefix(String prefix, String nsUri)
	{
		if(prefix == null || prefix.length() == 0)
			_defaultNamespace = nsUri;
		else
			_namespaces.put(nsUri, prefix);
	}

	public void startTag(String nsUri, String name) throws Exception
	{
		// close?
		closeElement();
		
		// create...
		String qualifiedName = getQualifiedName(nsUri, name);
		_builder.append("<");
		_builder.append(qualifiedName);
		
		// set...
		_inElementHeader = true;
		
		// first...
		if(_firstElement)
		{
			// reset...
			_firstElement = false;
			
			// add...
			if(hasDefaultNamespace())
				namespaceAttribute(null, _defaultNamespace);
			
			// the other namespaces...
			Enumeration uris = _namespaces.keys();
			while(uris.hasMoreElements())
			{
				String uri = (String)uris.nextElement();
				if(uri == null)
					break;
				
				// append...
				String prefix = (String)_namespaces.get(uri);
				namespaceAttribute(prefix, uri);
			}
		}
	}

	private boolean hasDefaultNamespace()
	{
		if(_defaultNamespace != null && _defaultNamespace.length() > 0) 
			return true;
		else
			return false;
	}

	private void namespaceAttribute(String prefix, String uri)
	{
		if(prefix == null || prefix.length() == 0)
		{
			_builder.append(" xmlns=\"");
			_builder.append(uri);
			_builder.append("\"");
		}
		else
		{
			_builder.append(" xmlns:");
			_builder.append(prefix);
			_builder.append("=\"");
			_builder.append(uri);
			_builder.append("\"");
		}
	}

	private void closeElement()
	{
		// are we in an element header?
		if(_inElementHeader)
		{
			// close...
			_builder.append(">");
			
			// reset...
			_inElementHeader = false;
		}
	}

	public void attribute(String nsUri, String name, String value)
	{
		// space...
		_builder.append(" ");
		
		// do we have a namespace?
		if(nsUri != null && nsUri.length() > 0)
		{
			// not needed for this implementation...
		}
		
		// the rest...
		_builder.append(name);
		_builder.append("=\"");
		_builder.append(value);
		_builder.append("\"");
	}

	public void endTag(String nsUri, String name) throws Exception
	{
		// close the header...
		closeElement();
		
		// close the tag...
		String qualifiedName = getQualifiedName(nsUri, name);
		_builder.append("</");
		_builder.append(qualifiedName);
		_builder.append(">");
	}

	private String getQualifiedName(String nsUri, String name) throws Exception
	{
		// defualt... or no default?
		if(_defaultNamespace == null || _defaultNamespace.compareTo(nsUri) == 0)
			return name;
		else
		{
			// find...
			if(_namespaces.containsKey(nsUri))
			{
				String prefix = (String)_namespaces.get(nsUri);
				return prefix + ":" + name;
			}
			else
				throw new Exception(MessageFormat.format("Namespace {0} not found.", new String[] { nsUri }));
		}
	}

	public void endDocument()
	{
		// no-op...
	}

	public void text(String buf)
	{
		// close...
		closeElement();
		
		// append... this needs more science to escape out or handle invalid
		// characters...
		_builder.append(buf);
	}

	public String toString()
	{
		return _builder.toString();
	}
}
