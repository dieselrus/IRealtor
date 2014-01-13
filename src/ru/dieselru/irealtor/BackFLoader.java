package ru.dieselru.irealtor;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class BackFLoader extends AsyncTask<String, Integer, File> {
	/**
	 * The system calls this to perform work in a worker thread and delivers it
	 * the parameters given to AsyncTask.execute() Љак использовать этот класс
	 * public void onClick(View v) { new
	 * SendSMSTask().execute("http://example.com/image.png"); }
	 */
	private Exception m_error = null;

	// MainActivity ma = new MainActivity();

	protected File doInBackground(String... _url) {
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

			// читаем со входа и пишем в выход,
			// с каждой итерацией публикуем прогресс
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
	// обновляем progressDialog
	protected void onProgressUpdate(Integer... values) {
		MainActivity.progressDialog.setProgress((int) ((values[0] / (float) values[1]) * 100));
	};

	// Ћбработка результата работы нового потока и взаимодействие с элементами
	// основного потока
	protected void onPreExecute(String result) {
		MainActivity.progressDialog.setMessage("Downloading ...");
		MainActivity.progressDialog.setCancelable(false);
		MainActivity.progressDialog.setMax(100);
		MainActivity.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		MainActivity.progressDialog.show();
	}
	
	@Override
	protected void onPostExecute(File file) {
		// отображаем сообщение, если возникла ошибка
		if (m_error != null) {
			m_error.printStackTrace();
			return;
		}
		// закрываем прогресс и удаляем временный файл
		MainActivity.progressDialog.hide();
		file.delete();
	}
}
