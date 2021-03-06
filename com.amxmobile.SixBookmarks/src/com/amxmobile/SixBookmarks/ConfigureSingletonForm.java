package com.amxmobile.SixBookmarks;

import com.amxmobile.SixBookmarks.Database.*;
import com.amxmobile.SixBookmarks.Runtime.*;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;

public class ConfigureSingletonForm extends MainScreen implements FieldChangeListener
{
	private EditField _nameTextBox;
	private EditField _urlTextBox;
	private ButtonField _saveButton;
	private ButtonField _deleteButton;
	private ButtonField _backButton;
	private Bookmark _bookmark;

	public ConfigureSingletonForm(int ordinal)
	{
		super();
		
		// title...
		setTitle(new LabelField("Configure"));
		
		// set...
		// controls...
		this.add(new LabelField("Name"));
		_nameTextBox = new EditField();
		this.add(_nameTextBox);
		
		this.add(new LabelField("URL"));
		_urlTextBox = new EditField();
		this.add(_urlTextBox);
		
		// horiz...
		HorizontalFieldManager horiz = new HorizontalFieldManager();
		this.add(horiz);
		
		// buttons...
		_saveButton = new ButtonField("Save", ButtonField.CONSUME_CLICK);
		_saveButton.setChangeListener(this);
		horiz.add(_saveButton);

		// buttons...
		_deleteButton = new ButtonField("Delete", ButtonField.CONSUME_CLICK);
		_deleteButton.setChangeListener(this);
		horiz.add(_deleteButton);

		// buttons...
		_backButton = new ButtonField("Back", ButtonField.CONSUME_CLICK);
		_backButton.setChangeListener(this);
		horiz.add(_backButton);
		
		// init...
		try
		{
			Initialize(ordinal);
		}
		catch(Exception ex)
		{
			MessageBox.Show(ex);
		}
	}

	private void Initialize(int ordinal) throws Exception
	{
		_bookmark = Bookmark.GetByOrdinal(ordinal);
		if(_bookmark == null)
		{
			// set...
			_bookmark = new Bookmark();
			_bookmark.setOrdinal(ordinal);
		}
		else
		{
			_nameTextBox.setText(_bookmark.getName());
			_urlTextBox.setText(_bookmark.getUrl());
		}
	}

	public void fieldChanged(Field field, int context)
	{
		try
		{
			if(field == _saveButton)
				HandleSave();
			else if(field == _deleteButton)
				HandleDelete();
			else if(field == _backButton)
				HandleBack();
		}
		catch(Exception ex)
		{
			MessageBox.Show(ex);
		}
	}

	private void HandleDelete() throws Exception
	{
		// flag it...
		_bookmark.setLocalDeleted(true);
		_bookmark.SaveChanges();
		
		// sync and quit...
		HandleSync();
	}

	private void HandleBack()
	{
		ConfigureForm form = new ConfigureForm();
		UiApplication.getUiApplication().pushScreen(form);
	}

	private void HandleSave() throws Exception
	{
		// check...
		ErrorBucket errors = new ErrorBucket();
		String name = _nameTextBox.getText();
		if(name.length() == 0)
			errors.AddError("Name is required.");
		String url = _urlTextBox.getText();
		if(url.length() == 0)
			errors.AddError("URL is required.");
		
		// errors?
		if(!(errors.getHasErrors()))
		{
			// set...
			_bookmark.setName(name);
			_bookmark.setUrl(url);
			
			// flag that we've changed it...
			_bookmark.setLocalModified(true);
			_bookmark.setLocalDeleted(false);
			
			// save...
			_bookmark.SaveChanges();
			
			// sync and back...
			HandleSync();
		}
		else
			MessageBox.Show(errors.GetAllErrors());
	}

	private void HandleSync() throws Exception
	{
		Sync sync = new Sync();
		sync.DoSync();
		
		// go...
		HandleBack();
	}
}
