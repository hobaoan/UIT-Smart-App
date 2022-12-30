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
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzI0NjI4MTcsImlhdCI6MTY3MjM3NjQxNywiYXV0aF90aW1lIjoxNjcyMzc2NDE3LCJqdGkiOiIzYTBlZGQzZS1kNDEzLTRmZTUtODllNS0wMmRiYjk3OWI4NTciLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI2MGQwZmQzZS00NDg5LTQyNmEtOGQxOS0wNmZkYjZiMDM0OGIiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2MGQwZmQzZS00NDg5LTQyNmEtOGQxOS0wNmZkYjZiMDM0OGIiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.IiTWQn2lV7dhXUyTsIR6DXpogOQHSSD0XiSnkhiThIqSInLSHgWEgLofZhIsOLCb8a5QGAcW88-VvfPfadw3xIHep5-BFB-dXLWeGZ0ey6VnbR3UEwvDhblr-mQI2eJVvL0bq26-UM_br9lk92by1qgDzbR0nerGv6-r7tW1u7jMkGzWfFWjk7mHS5-DBaQ7Ac4uzX4-ZHLun_MgYwlKmE0ruAUHVR2uN64n2DdglpvNV9tEMoqlwAf2iG2LqxFAlzguMGXc6UncmaoppfrpeDi1g9S2HGGbHW4cqr3xHWjbyuJkuqa8mgGoMm79dgcapn7vtPuGYhcIRNO2Yn4grg";
        return token;
    }
}
