package com.example.uitsmart.access;

public class AccessAPI {
    public static String urlMap, urlUserCurrent, urlGoogleWeather;
    public static String token;

    public AccessAPI(String url, String token, String urlUserCurrent, String urlGoogleWeather) {
        this.urlMap = url;
        this.token = token;
        this.urlUserCurrent = urlUserCurrent;
        this.urlGoogleWeather = urlGoogleWeather;
    }

    public static String getURLMap() {
        urlMap = "https://103.126.161.199/api/master/map";
        return urlMap;
    }
    public static String getUrlUserCurrent () {
        urlUserCurrent = "https://103.126.161.199/api/master/asset/user/current";
        return urlUserCurrent;
    }

    public static String getUrlGoogleWeather () {
        urlGoogleWeather = "https://api.openweathermap.org/data/2.5/weather?q=Saigon&units=metric&appid=ac212f5768bf3e2f84201adbd2bc7961";
        return urlGoogleWeather;
    }

    public static String getToken() {
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzI3NTI2MDcsImlhdCI6MTY3MjcyMzM3MSwiYXV0aF90aW1lIjoxNjcyNjY2MjA3LCJqdGkiOiI5ZmRjZTdmZi1hY2YwLTRmNmItOWI3MC0yYjE1NWVlNzJkN2MiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiJjYjk3OTM5ZS1kM2ZkLTRhY2YtOTFkMi0xYTlkNjM2ODcwN2IiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiJjYjk3OTM5ZS1kM2ZkLTRhY2YtOTFkMi0xYTlkNjM2ODcwN2IiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.aVJ1h3helkX1UhdHGyJLoHQWx2cd1m4hp4NJTbjaqTSVS2eKEoFHJqSIXLXsz7yXOeZuHMvWeiBvTK8peEAGI1ral98mgtqo-Fac0T_NpzHVeR0A4J1J9pQr3TXxVlc_KmxkbPuILewO2Z6J6yPYT-GDDtb4DAp2nrJMwRL17wyYsPIOSqYrqXonMC0WoR4c5FWeTACGFwTibhDPkd4eb3YITZtxXl3sSiIU6xCylVWTqsh7AaZh5TRdw5nts1ywriXPkdLj63sELI4uDOwHojyPW-VPAYcU2ng_VprCY5PH0jRTTe6yWxT23cvyxwfVjQdf04bM1dOzftLbvtxSjw";
        return token;
    }
}
