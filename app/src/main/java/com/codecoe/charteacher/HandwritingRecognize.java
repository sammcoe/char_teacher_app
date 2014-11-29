package com.codecoe.charteacher;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sam on 11/23/14.
 */
public class HandwritingRecognize {
    private static ImageRecognitionPlugin imageRecognition;

    public static void main(String[] args) {
        // load trained neural network saved with Neuroph Studio (specify some existing neural network file here)
        NeuralNetwork nnet = NeuralNetwork.load("handwriting.nnet"); // load trained neural network saved with Neuroph Studio
        // get the image recognition plugin from neural network
        imageRecognition = (ImageRecognitionPlugin)nnet.getPlugin(ImageRecognitionPlugin.class); // get the image recognition plugin from neural network
        Draw myDraw = new Draw();
    }

    public String recognize(String fileLoc){
        File file = new File(fileLoc);
        HashMap<String, Double> output = new HashMap<String, Double>();
        try {
            // image recognition is done here (specify some existing image file)
            output = imageRecognition.recognizeImage(file);
            System.out.println(output.toString());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
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
