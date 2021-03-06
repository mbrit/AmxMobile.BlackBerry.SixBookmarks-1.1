package com.amxmobile.SixBookmarks.Database;

import java.util.*;

import com.amxmobile.SixBookmarks.Entities.*;

public class Bookmark extends Entity 
{
	public final static String BookmarkIdKey = "BookmarkId";
	public final static String OrdinalKey = "Ordinal";
	public final static String NameKey = "Name";
	public final static String UrlKey = "Url";
	public final static String LocalModifiedKey = "LocalModified";
	public final static String LocalDeletedKey = "LocalDeleted";

	public Bookmark() throws Exception
	{
	}
	
	public Bookmark(Hashtable values) throws Exception
	{	
		super(values);
	}
	
	public int getBookmarkId() throws Exception
	{
		return GetInt32Value(BookmarkIdKey);
	}
	
	public void setBookmarkId(int value) throws Exception
	{
		SetValue(BookmarkIdKey, new Integer(value), Entity.SETREASON_USER);
	}
	
	public int getOrdinal() throws Exception
	{
		return GetInt32Value(OrdinalKey);
	}
	
	public void setOrdinal(int value) throws Exception
	{
		SetValue(OrdinalKey, new Integer(value), Entity.SETREASON_USER);
	}

	public String getName() throws Exception
	{
		return GetStringValue(NameKey);
	}
	
	public void setName(String value) throws Exception
	{
		SetValue(NameKey, value, Entity.SETREASON_USER);
	}
	
	public String getUrl() throws Exception
	{
		return GetStringValue(UrlKey);
	}
	
	public void setUrl(String value) throws Exception
	{
		SetValue(UrlKey, value, Entity.SETREASON_USER);
	}

	public boolean getLocalModified() throws Exception
	{
		return GetBooleanValue(LocalModifiedKey);
	}
	
	public void setLocalModified(boolean value) throws Exception
	{
		SetValue(LocalModifiedKey, new Boolean(value), Entity.SETREASON_USER);
	}

	public boolean getLocalDeleted() throws Exception
	{
		return GetBooleanValue(LocalDeletedKey);
	}
	
	public void setLocalDeleted(boolean b) throws Exception
	{
		SetValue(LocalDeletedKey, new Boolean(b), Entity.SETREASON_USER);
	}
	
	public static BookmarkCollection GetBookmarksForDisplay() throws Exception
	{
		// get those that are flagged as modified and deleted...
		SqlFilter filter = new SqlFilter(Bookmark.class);
		filter.AddConstraint("LocalDeleted", 0);
		
		// return...
		return (BookmarkCollection)filter.ExecuteEntityCollection(); 
	}
	
	public static Bookmark GetByOrdinal(int ordinal) throws Exception
	{
		SqlFilter filter = new SqlFilter(Bookmark.class);
		filter.AddConstraint("Ordinal", ordinal);
		filter.AddConstraint("LocalDeleted", 0);
		
		// return...
		return (Bookmark)filter.ExecuteEntity();
	}
	
	public static BookmarkCollection GetBookmarksForServerUpdate() throws Exception
	{
		// get those that are flagged as modified and deleted...
		SqlFilter filter = new SqlFilter(Bookmark.class);
		filter.AddConstraint("LocalModified", 1);
		filter.AddConstraint("LocalDeleted", 0);
		
		// return...
		return (BookmarkCollection)filter.ExecuteEntityCollection(); 
	}

	public static BookmarkCollection GetBookmarksForServerDelete() throws Exception
	{
		// get those that are flagged as modified and deleted...
		SqlFilter filter = new SqlFilter(Bookmark.class);
		filter.AddConstraint("LocalDeleted", 1);
		
		// return...
		return (BookmarkCollection)filter.ExecuteEntityCollection(); 
	}
	
	public String toString()
	{
		try
		{
			return getName();
		}
		catch(Exception ex)
		{
			return "???";
		}
	}
}
