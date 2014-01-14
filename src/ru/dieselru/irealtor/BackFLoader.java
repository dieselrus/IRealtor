package ru.dieselru.irealtor;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;

import android.app.ProgressDialog;
import android.content.Context;
//import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class BackFLoader extends AsyncTask<String, Integer, File> {
	/**
	 * The system calls this to perform work in a worker thread and delivers it
	 * the parameters given to AsyncTask.execute() ��� ������������ ���� �����
	 * public void onClick(View v) { new
	 * SendSMSTask().execute("http://example.com/image.png"); }
	 */
	private Exception m_error = null;

	// ��������� ������
	public ProgressDialog dialog;
	// �������� ������������� ������
	Context ctx;
	  
	// MainActivity ma = new MainActivity();

	protected File doInBackground(String... _url) {
		
		//Log.d("FILE URL", _url[0]);
		
		URL url;
		HttpURLConnection urlConnection;
		InputStream inputStream;
		int totalSize;
		int downloadedSize;
		byte[] buffer;
		int bufferLength;

		File file = null;
		FileOutputStream fos = null;

		try {
			url = new URL(_url[0]);
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();

			file = File.createTempFile("Mustachify", "download");
			fos = new FileOutputStream(file);
			inputStream = urlConnection.getInputStream();

			totalSize = urlConnection.getContentLength();
			downloadedSize = 0;

			buffer = new byte[1024];
			bufferLength = 0;

			// ������ �� ����� � ����� � �����,
			// � ������ ��������� ��������� ��������
			while ((bufferLength = inputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
				publishProgress(downloadedSize, totalSize);
			}

			fos.close();
			inputStream.close();

			return file;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			m_error = e;
		} catch (IOException e) {
			e.printStackTrace();
			m_error = e;
		}

		return null;

	}

	@Override
	// ��������� progressDialog
	protected void onProgressUpdate(Integer... values) {
		//MainActivity.progressDialog.setProgress((int) ((values[0] / (float) values[1]) * 100));
		MainActivity.setProgres(values);
	};

	// ��������� ���������� ������ ������ ������ � �������������� � ����������
	@Override
	protected void onPreExecute() {
		MainActivity.openProgres();
		
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(File file) {
		// ���������� ���������, ���� �������� ������
		
		if (m_error != null) {
			m_error.printStackTrace();
			//Log.d("FILE URL ERROR", m_error.getMessage());
			return;
		}
		// ��������� �������� � ������� ��������� ����
		MainActivity.closeProgres();
		//Log.d("FILE URL", "NO ERROR");
		
		try {
			copy(file, Environment.getDataDirectory());
		} catch (IOException e) {
			e.printStackTrace();
			//MainActivity.setToast("gggggg", MainActivity.this);
		}
		file.delete();
		//String packageName = context.getPackageName();
		//DB_PATH = String.format("//data//data//%s//databases//", packageName);
		
	}
	
	public static void copy(File source, File dest) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        try {
            FileChannel destChannel = new FileOutputStream(dest).getChannel();
            try {
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            } finally {
                destChannel.close();
            }
        } finally {
            sourceChannel.close();
        }
    }
}
