package ru.dieselru.irealtor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoadDBATask extends AsyncTask<String, Void, String> {
    /** The system calls this to perform work in a worker thread and
      * delivers it the parameters given to AsyncTask.execute() 
      * Как использовать этот класс
      * public void onClick(View v) {
      * 	new SendSMSTask().execute("http://example.com/image.png");
      * }*/

	private String _cookie = "";
	
	//MainActivity ma = new MainActivity();
	
    protected String doInBackground(String... urls) {
        try {
        	// Получаем изображение капчи в отдельном потоке
        	return SendPost(urls[0], urls[1]);
			//return SendPost(urls[0], urls[1], urls[2], urls[3], urls[4] ,urls[5]);
		} catch (MalformedURLException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	// Отправляем SMS  
    // new SendSMSTask().execute("http://irk.ru/sms/?", GetToken(_cookie), txtPhoneNumber.getText(), txtSMSText.getText() + "\n" + strMyName, strCaptcha0, txtCaptcha1.getText());
	public String SendPost(String httpURL, String data) throws IOException  
	//public String SendPost(String httpURL, String csrfmiddlewaretoken, String number, String message, String captcha_0, String captcha_1) throws IOException 
	{
		//Log.v("POST", httpURL);
		//Log.v("POST", data);
		//System.out.println(httpURL);
		
		
		URL url = new URL(httpURL);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
		connection.setRequestProperty("Host", "www.realtor38.ru");
		connection.setRequestProperty("Referer","http://www.realtor38.ru/connect.php/");
		connection.setRequestMethod("POST");
                
		// If cookie exists, then send cookie
		//_cookie = MainActivity.getCoockie();
		if (_cookie != "") 
		{
			//Log.v("Cookie", _cookie);
			connection.setRequestProperty("Cookie", _cookie);
			connection.connect();
		}
                
		// If Post Data not empty, then send POST Data
		if (data != "") 
		{
			//Log.v("POST data", data);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(data);
			out.flush();
			out.close();
		}
                
		// Save Cookie
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String headerName = null;
		//_cookies.clear();
		if (_cookie == "") 
		{
			for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) 
			{
				if (headerName.equalsIgnoreCase("Set-Cookie")) 
				{    
					String cookie = connection.getHeaderField(i);
					_cookie += cookie.substring(0,cookie.indexOf(";")) + "; ";
				}
			}
			
			//MainActivity.setCoockie(_cookie);
		}
                
		// Get HTML from Server
		String getData = "";
		String decodedString;
		while ((decodedString = in.readLine()) != null) 
		{
			//Log.v("DATA", "read data");
			getData += decodedString + "\n";
		}
		in.close();
		
		return getData;
		
	}

	@Override
    protected void onProgressUpdate(Void... values) {
         super.onProgressUpdate(values);
    }

	// Обработка результата работы нового потока и взаимодействие с элементами основного потока
    protected void onPostExecute(String result) {
    	// Уничтожить окно диалого
    	//pdSMS.dismiss();
    	
		//String matchtoken = null;
		// работаем с регулярками
    	
		final Pattern pattern = Pattern.compile ("(succes[a-z]+)");
		Matcher matcher = pattern.matcher(result);

		new BackFLoader().execute(result);
		//MainActivity.setMessageStatus(matcher.find());
		//Log.d("POST", result);
    }
}  
