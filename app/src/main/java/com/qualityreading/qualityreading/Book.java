package com.qualityreading.qualityreading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class Book {
    private class DownloadThumbnailTask extends AsyncTask<String, Void, Bitmap> {
        ImageView view;

        public DownloadThumbnailTask(ImageView view) {
            this.view = view;
        }

        @Override
        protected Bitmap doInBackground(String...params){
            Bitmap thumbnail = null;
            try {
                InputStream inputStream = new URL(params[0]).openStream();
                thumbnail = BitmapFactory.decodeStream(inputStream);
            }
            catch (Exception e) {
                Log.d("DOWNLOAD_THUMBNAIL", e.getMessage());
            }
            return thumbnail;
        }

        @Override
        protected void onPostExecute(Bitmap thumbnail) {
            view.setImageBitmap(thumbnail);
        }
    }

    String title;
    String author;
    String publisher;
    String publishDate;
    String thumbnail;
    public Book (String title, String author, String publisher, String publishDate, String thumbnail) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.thumbnail = thumbnail;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getPublishDate(){
        return publishDate;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public void showThumbnail(ImageView view){
        if(!thumbnail.isEmpty()){
            new DownloadThumbnailTask(view).execute(thumbnail);
        }
    }
}
