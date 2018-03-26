package com.example.android.imagetotext;

import android.net.Uri;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;

public class FileSystem implements Serializable{

	private ArrayList<File> Files;
	private File dir;

	public FileSystem(String dir)
	{
		Files = new ArrayList<>();
		this.dir = new File(dir);
		if(!this.dir.exists())
		{this.dir.mkdir();}
		else {
			File[] allFiles = this.dir.listFiles();
			for (int i = 0; i < allFiles.length; i++) {
				Files.add(allFiles[i].getAbsoluteFile());
			}
		}

	}


	public String getLocation()
	{return dir.getAbsolutePath();}



	public String OpenDoc(File Document) {
		String doc = "";
		FileInputStream in =null;

		try{
			in = new FileInputStream(Uri.decode(Document.getAbsolutePath()));
			byte [] arr = new byte[in.available()];
			while(in.read(arr) != -1)
			{
				doc = new String(arr);
			}

		}
		catch(Exception E) {
			E.printStackTrace();
			return null;
		}
		finally {
			try{
				in.close();
				return doc;
			}
			catch(Exception E)
			{
				E.printStackTrace();
				return null;
			}
		}
	}

	public boolean saveFile(String msg, String fileName)
	{
		try{
			byte data[] = msg.getBytes();
			File fSave = new File(dir, fileName+".txt");
			FileWriter write = new FileWriter(fSave);
			write.append(msg);
			write.flush();
			write.close();
			addFile(fSave);
			return true;

		}
		catch (IOException E)
		{
			E.printStackTrace();
			return false;
		}
	}

	public ArrayList<File> getFiles() {
	return Files;
	}


	public boolean addFile(File FileName) {
		Files.add(FileName);
		return true;
	}


	public boolean RemoveFile(File FileName) {
		if(!FileName.exists())
		{return false;}
		else{FileName.delete();
		return true;
		}
	}


	public void getFile(File file) {

		throw new UnsupportedOperationException();
	}

}