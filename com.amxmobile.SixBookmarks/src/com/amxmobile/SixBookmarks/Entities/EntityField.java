package com.amxmobile.SixBookmarks.Entities;

public class EntityField extends EntityItem 
{
	public final static int DATATYPE_STRING = 0;
	public final static int DATATYPE_INT32 = 1;
	
	private int _type;
	private int _size;
	private boolean _isKey;
	private int _ordinal;
	private boolean _isOnServer = true;
	
	public EntityField(String name, String nativeName, int type, int size,	int ordinal) 
	{
		super(name, nativeName);
		
		// set...
		_type = type;
		_size = size;
		_ordinal = ordinal;
	}

	public int getOrdinal()
	{
		return _ordinal;
	}
	
	public int getDataType()
	{
		return _type;	
	}
	
	public int getSize()
	{
		return _size;
	}
	
	public boolean getIsKey() 
	{
		return _isKey;
	}

	public void setIsKey(boolean value)
	{
		_isKey = value;
	}
	
	public boolean getIsOnServer()
	{
		return _isOnServer;
	}
	
	public void setIsOnServer(boolean value)
	{
		_isOnServer = value;
	}
}
