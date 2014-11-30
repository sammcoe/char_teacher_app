package com.codecoe.charteacher;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.contrib.imgrec.image.Image;
import org.neuroph.contrib.imgrec.image.ImageFactory;
import org.neuroph.core.NeuralNetwork;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.io.File;
import java.util.Map;

import java.io.InputStream;

import android.content.Context;

public class HandwritingRecognize {
    private Bitmap bitmap;
    private Image image;

    private static NeuralNetwork nnet;
    private  ImageRecognitionPlugin imageRecognition;

    private static Context context;

    public HandwritingRecognize(Context cont){
        context = cont;
        loadData();
    }

    private void loadData() {
        // open neural network
        InputStream is = context.getResources().openRawResource(R.raw.digitrec);
        // load neural network
        nnet = NeuralNetwork.load(is);
        imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);
    }

    public String recognize(File file){
        System.out.println(file.getAbsolutePath());
        String filePath = file.getAbsolutePath();
        // get image
        image = ImageFactory.getImage(filePath);
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
