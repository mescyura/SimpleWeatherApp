package com.weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    static String key = "c10eb1f19f50b44f0dc13c1636f5439c";

    static String getWeatherFrom(String city) {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key);
            URLConnection urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                content.append(line);
        } catch (Exception e) {
            System.out.println("I/O error");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("I/O error");
                }
            }
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String yourCity = "Ternopil";
        String content = getWeatherFrom(yourCity);
        System.out.println(content);
        if (!content.isEmpty()) {
            JSONObject jsonObject = new JSONObject(content);
            System.out.printf("Temp in %s are %s C%n", yourCity, jsonObject.getJSONObject("main").getDouble("temp") - 273.15);

        }
    }
}
