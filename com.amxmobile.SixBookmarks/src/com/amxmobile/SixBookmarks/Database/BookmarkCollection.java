package com.amxmobile.SixBookmarks.Database;

import java.util.Vector;

public class BookmarkCollection extends Vector
{
	public Bookmark GetByOrdinal(int ordinal) throws Exception
	{
		for(int index = 0; index < size(); index++)
		{
			Bookmark bookmark = (Bookmark)elementAt(index);
			if(bookmark.getOrdinal() == ordinal)
				return bookmark;
		}
		
		// return...
		return null;
	}

	public void SortByOrdinal() throws Exception
	{
		// mmm... no sort in j2me...
		Object[] byOrdinal = new Object[6];
		for(int index = 0; index < size(); index++)
		{
			Bookmark bookmark = (Bookmark)elementAt(index);
			byOrdinal[bookmark.getOrdinal()] = bookmark; 
		}
		
		// rebuild the array, removing the null values...
		removeAllElements();
		for(int index = 0; index < 6; index++)
		{
			if(byOrdinal[index] != null)
				addElement(byOrdinal[index]);
		}
	}
}
