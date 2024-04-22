package com.qppd.rizalelearning.Libs.Imagez;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageBase64 {

    private ByteArrayOutputStream bao;
    private int quality = 100;

    public ImageBase64(){
        bao = new ByteArrayOutputStream();
    }

    public String enCode(Bitmap bitmap){

        //Bitmap bm = Bitmap.createBitmap(bitmap);
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality, bao);
        if(bitmap != null && !bitmap.isRecycled()){
            bitmap.recycle();
        }
        byte[] b = bao.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public Bitmap deCode(String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage.substring(encodedImage.indexOf(",")  + 1),
                Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}