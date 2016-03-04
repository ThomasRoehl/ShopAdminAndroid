package com.example.thomasroehl.shopadminandroid.tesseract;

import android.provider.MediaStore;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas Roehl on 23.01.2016.
 */
public class Classificator {
    private String text;
    private List<String> resultList = new ArrayList<String>();
    private String shopName;
    private String sum;
    private List<String> shops = new ArrayList<String>();
    private final String TAG = "CLS";

    public void setText(String txt) {
        shops.addAll(Arrays.asList(new String[] { "REWE", "ALDI", "DM", "KAUFHOF", "ROSSMANN", "LILIUM", "SATURN", "IKEA", "PRIMARK" }));
        text = txt;
        filterText();
        text = text.toUpperCase();
    }

    public String getShopName(){
        return this.shopName;
    }

    public String getSum(){
        return this.sum;
    }

    public void classificate() {
        listItems();
        searchForShop();
        findSum();
    }

    private void listItems() {

        text = text.replace("\n", " ");
        text = text.replace("\r", " ");
        Log.v(TAG, "TESSERACT RESULTS: " + text);
        String newText = "" + text.charAt(0);
        for (int i = 1; i < text.length(); i++){
            Character c = text.charAt(i);
            if (c == ' ' && i < text.length()-2){
                if (text.charAt(i+1) != ' ' && text.charAt(i+2) == ' ' && !isDigit(text.charAt(i+1))){
//                    Log.v(TAG, "POS: " + i);
                    newText += text.charAt(i+1);
                    i++;
                }
                else{
//                    Log.v(TAG, "POS: " + i);
                    newText += c;
                }
            }
            else{
//                Log.v(TAG, "POS: " + i);
                newText += c;
            }
        }
//        Log.v(TAG, newText);
        text = newText;
        resultList.addAll(Arrays.asList(text.split(" ")));
        for (int i = 0; i < resultList.size(); i++) {
            String s = resultList.get(i).trim();
            resultList.remove(i);
            if (s.equals(""))
                continue;
            resultList.add(s);
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).isEmpty())
                resultList.remove(i);
        }
//        printList();
    }

    private void printList(){
        for (String s: resultList){
            Log.v(TAG, s);
        }
    }

    private void findSum() {
        try {
            List<Double> sums = new ArrayList<Double>();
            for (String s : resultList) {
                String r = s.replace(',', '.');
                try {
                    try {
                        @SuppressWarnings("unused")
                        Integer i = Integer.parseInt(r);
                    } catch (Exception e) {

                        Double d = Double.parseDouble(r);

                        if (d < 10000 && !r.endsWith("F")) {
                            Log.v(TAG+"_possibleVal", d.toString());
                            sums.add(d);
                        }
                    }
                } catch (Exception e) {

                }

            }
//            Collections.sort(sums);
//            double max = sums.get(0);
//            for (int i = 1; i < sums.size(); i++) {
//                if (max < sums.get(i)) {
//                    max = sums.get(i);
//                }
//            }
//            sums.remove(max);
            sum = Collections.max(sums).toString();
        }
        catch(Exception e){
            sum = "-1.0";
        }
    }

    private void searchForShop() {
//        List<String> sublist = resultList.subList(0, resultList.size());
        Collections.sort(resultList);
        HashMap<String, Integer> levenstein = new HashMap<String, Integer>();
        for (int i = 0; i < resultList.size(); i++) {
            for (int j = 0; j < shops.size(); j++) {
                Integer l = StringUtils.getLevenshteinDistance(resultList.get(i), shops.get(j));
                double l1 = resultList.get(i).length();
                double q = (l / l1);
                l = (int)(l * q);
                levenstein.put(resultList.get(i) + " - " + shops.get(j), l);
//                System.out.println(resultList.get(i) + "\t" + shops.get(j) + "\t["+ l1 + ", " + q + "]: " + l);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : levenstein.entrySet()) {

            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) < 0) {
                maxEntry = entry;

            }
            else{
                if (maxEntry.getValue() == entry.getValue()){
                    if (maxEntry.getKey().length() < entry.getKey().length()) maxEntry = entry;
                }
            }

        }
        if(maxEntry != null)
            shopName = maxEntry.getKey().split(" - ")[1].trim();
        else
            shopName = "Nothing";

    }

    private void filterText(){
        text.replace("?", "");
        text.replace("!", "");
        text.replace("_", "");
        text.replace(";", "");
        text.replace("/", "");
        text.replace("\\", "");
        text.replace("[", "");
        text.replace("]", "");
        text.replace("{", "");
        text.replace("}", "");
        text.replace("(", "");
        text.replace(")", "");
    }

    private boolean isDigit(Character c){
        switch (c){
            case '1': return true;
            case '2': return true;
            case '3': return true;
            case '4': return true;
            case '5': return true;
            case '6': return true;
            case '7': return true;
            case '8': return true;
            case '9': return true;
            case '0': return true;
            default: return false;
        }
    }

}
