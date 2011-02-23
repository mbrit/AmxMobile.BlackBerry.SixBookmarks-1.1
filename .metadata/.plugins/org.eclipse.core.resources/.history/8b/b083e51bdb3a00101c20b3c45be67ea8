package com.amxmobile.SixBookmarks;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

import com.amxmobile.SixBookmarks.Database.Bookmark;
import com.amxmobile.SixBookmarks.Database.BookmarkCollection;
import com.amxmobile.SixBookmarks.Runtime.MessageBox;
import com.amxmobile.SixBookmarks.Runtime.SixBookmarksRuntime;

public class NavigatorForm extends MainScreen implements FieldChangeListener
{
	private BookmarkCollection _bookmarks = null;
	private Vector _buttons = new Vector();
	private ButtonField _configureButton;
	private ButtonField _logoffButton;
	private ButtonField _aboutButton;
	
	public NavigatorForm()
	{
		super();
		
		// title...
		setTitle(new LabelField("Six Bookmarks"));
		
		// buttons...
		for(int index = 0; index < 6; index++)
			AddButton(index);
		
		// configure button...
		_configureButton = new ButtonField("Configure", ButtonField.CONSUME_CLICK);
		_configureButton.setChangeListener(this);
		this.add(_configureButton);
		
		// about button...
		_aboutButton = new ButtonField("About", ButtonField.CONSUME_CLICK);
		_aboutButton.setChangeListener(this);
		this.add(_aboutButton);
		
		// about button...
		_logoffButton = new ButtonField("Logoff", ButtonField.CONSUME_CLICK);
		_logoffButton.setChangeListener(this);
		this.add(_logoffButton);
		
		// init...
		try
		{
			Initialize();
		}
		catch(Exception ex)
		{
			MessageBox.Show(ex);
		}
	}

	private void Initialize() throws Exception
	{
		_bookmarks = Bookmark.GetBookmarksForDisplay();
		for(int index = 0; index < _bookmarks.size(); index++)
			Initialize((Bookmark)_bookmarks.elementAt(index));
	}

	private void Initialize(Bookmark bookmark) throws Exception
	{
		ButtonField button = GetBookmarkButton(bookmark.getOrdinal());
		button.setLabel(bookmark.getName());
	}

	private ButtonField GetBookmarkButton(int ordinal)
	{
		return (ButtonField)_buttons.elementAt(ordinal);
	}

	private void AddButton(int index)
	{
		ButtonField button = new ButtonField("...", ButtonField.CONSUME_CLICK);
		button.setChangeListener(this);
		_buttons.addElement(button);
		this.add(button);
	}

	public void fieldChanged(Field field, int context)
	{
		try
		{
			if(field == _configureButton)
				HandleConfigure();
			else if(field == _aboutButton)
				HandleAbout();
			else if(field == _logoffButton)
				HandleLogoff();
			else
				HandleNavigationClick(field);
		}
		catch(Exception ex)
		{
			MessageBox.Show(ex);
		}	
	}

	private void HandleNavigationClick(Field field) throws Exception
	{
		int index = GetButtonIndex((ButtonField)field);
		
		// show...
		Bookmark bookmark = _bookmarks.GetByOrdinal(index);
		if(bookmark != null)
			SixBookmarksRuntime.OpenUrl(bookmark.getUrl());
		else
			HandleConfigure();
	}
	
	private int GetButtonIndex(ButtonField field) throws Exception
	{
		for(int index = 0; index < _buttons.size(); index++)
		{
			ButtonField check = (ButtonField)_buttons.elementAt(index);
			if(check == field)
				return index;
		}
		
		// nope...
		throw new Exception("The button was not found.");
	}

	private void HandleLogoff()
	{
		UiApplication.getUiApplication().pushScreen(new LogonForm());
	}

	private void HandleAbout()
	{
		SixBookmarksRuntime.OpenUrl("http://www.multimobiledevelopment.com/");
	}

	private void HandleConfigure()
	{
		ConfigureForm form = new ConfigureForm();
		UiApplication.getUiApplication().pushScreen(form);
	}
}
