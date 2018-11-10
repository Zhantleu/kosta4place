package kost4place.aa.kz.kosta4place.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnection extends AsyncTask<Void, Void, String> {

    private static final String IP = "192.168.1.245";
    private static final int PORT = 8889;

    private StringBuilder stringBuilder = new StringBuilder();
    private ImageView textResponse;
    private Context context;

    public SocketConnection(ImageView textResponse, Context context) {
        this.textResponse = textResponse;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        try (Socket socket = new Socket(IP, PORT)) {
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println("1");
                out.flush();

                int c;
                while ((c = in.read()) != -1) {
                    System.out.print((char) c);
                    stringBuilder.append((char) c);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        byte[] decoded = Base64.decode(result, Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        Glide.with(context)
                .load(stream.toByteArray())
                .asBitmap()
                .into(textResponse);

    }


    private String saveToInternalStorage(Bitmap bitmapImage, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

}