package com.codecoe.charteacher;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.contrib.imgrec.image.ImageAndroid;
import org.neuroph.core.NeuralNetwork;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.io.File;
import java.util.Map;

import java.io.InputStream;

import android.content.Context;

public class HandwritingRecognize {
    private Bitmap bitmap;
    private ImageAndroid image;

    private static NeuralNetwork nnet;
    private  ImageRecognitionPlugin imageRecognition;

    private static Context context;

    public HandwritingRecognize(Context cont){
        context = cont;
        //loadData();
    }

    private void loadData() {
    }

    public String recognize(File file){
        // open neural network
        InputStream is = context.getResources().openRawResource(R.raw.digitrec);
        // load neural network
        nnet = NeuralNetwork.load(is);
        imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);
        String filePath = file.getAbsolutePath();
        // get image
        image = new ImageAndroid(file);
        HashMap<String, Double> output = imageRecognition.recognizeImage(image);

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
        System.out.println(answer);
        return answer;
    }
}
