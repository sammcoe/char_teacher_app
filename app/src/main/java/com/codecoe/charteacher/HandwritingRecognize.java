package com.codecoe.charteacher;
import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.contrib.imgrec.image.Image;
import org.neuroph.contrib.imgrec.image.ImageFactory;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.contrib.imgrec.*;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class HandwritingRecognize {
    private Bitmap bitmap;
    private Image image;

    private static NeuralNetwork nnet;
    private static ImageRecognitionPlugin imageRecognition;

    private static Context context;

    public HandwritingRecognize(Context cont){
        context = cont;
    }

    public static void main(String[] args) {
        loadData();
    }

    private static void loadData() {
        // load neural network in separate thread with stack size = 32000
        new Thread(null, loadDataRunnable, "dataLoader", 32000).start();
    }

    private static Runnable loadDataRunnable = new Runnable() {
        public void run() {
            // open neural network
            InputStream is = context.getResources().openRawResource(R.raw.handwriting);
            // load neural network
            nnet = NeuralNetwork.load(is);
            imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);
        }
    };

    public String recognize(File file){
        String filePath = file.getAbsolutePath();
        // get image
        image = ImageFactory.getImage(filePath);
        System.out.println(image.getHeight());
        HashMap<String, Double> output = imageRecognition.recognizeImage(image);
        System.out.println(output.toString());

        return(getAnswer(output));
    }

    private String getAnswer(HashMap<String, Double> output) {
        double highest = 0;
        String answer = "";
        for (Map.Entry<String, Double> entry : output.entrySet()) {
            if (entry.getValue() > highest) {
                highest = entry.getValue();
                answer = entry.getKey();
            }
        }

        return answer;
    }
}
