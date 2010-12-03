package com.amxmobile.SixBookmarks.Runtime;

import net.rim.blackberry.api.browser.Browser;
import net.rim.blackberry.api.browser.BrowserSession;

import com.amxmobile.SixBookmarks.Database.Bookmark;
import com.amxmobile.SixBookmarks.Database.BookmarkCollection;
import com.amxmobile.SixBookmarks.Entities.EntityField;
import com.amxmobile.SixBookmarks.Entities.EntityType;

public class SixBookmarksRuntime 
{
	public static void Start()
	{
		// create the entity type...
		EntityType bookmark = new EntityType(Bookmark.class, BookmarkCollection.class, "Bookmarks");
		bookmark.AddField(Bookmark.BookmarkIdKey, Bookmark.BookmarkIdKey, EntityField.DATATYPE_INT32, -1).setIsKey(true);
		bookmark.AddField(Bookmark.OrdinalKey, Bookmark.OrdinalKey, EntityField.DATATYPE_INT32, -1);
		bookmark.AddField(Bookmark.NameKey, Bookmark.NameKey, EntityField.DATATYPE_STRING, 128);
		bookmark.AddField(Bookmark.UrlKey, Bookmark.UrlKey, EntityField.DATATYPE_STRING, 256);
		bookmark.AddField(Bookmark.LocalModifiedKey, Bookmark.LocalModifiedKey, EntityField.DATATYPE_INT32, -1).setIsOnServer(false);
		bookmark.AddField(Bookmark.LocalDeletedKey, Bookmark.LocalDeletedKey, EntityField.DATATYPE_INT32, -1).setIsOnServer(false);
		
		// register it...
		EntityType.RegisterEntityType(bookmark);
	}

	public static void OpenUrl(String url)
	{
		BrowserSession browser = Browser.getDefaultSession();
		browser.displayPage(url);
	}
}
