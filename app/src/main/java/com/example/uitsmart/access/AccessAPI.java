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
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzEyNjMxNjcsImlhdCI6MTY3MTE3Njc2OCwiYXV0aF90aW1lIjoxNjcxMTc2NzY3LCJqdGkiOiIxMmVhZGM2OC1jY2NhLTQ2MGYtOTNmMS03MTQwYTVhODEyMzQiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiJjM2U0M2FkYy1kN2YzLTQxZjktYWQ2MS1hM2UxYjU1YTg0MTIiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiJjM2U0M2FkYy1kN2YzLTQxZjktYWQ2MS1hM2UxYjU1YTg0MTIiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.VeRW7vZIasMtCNzYuj05cgnm8rwB0Ifbb12KZyoMukS5rUOpOCjU8V9NBivAHd64doG_qOK11zk1vX3gc4Rq5qehlY8VRXmy9hqaId1osZuQ4Y7yW1B42ssK2J39Tl1QsoYUDLIKfrGP5jDcCPWNGb_BmHb8AQ96CbrYzVpdNmfvDmO2Mh4HX2DJE4PQ6_NApKHYVcia7ifNgJiiXD2q6ljwK6dGHCzEP4TBGhyO_mHAFomulRS-fDxaGRyXgoGfORpP-9WMxqccbAYIN2L2alfJ-l1bpz7eR6diHdhYRm8ieAEGFrPrI1WUSyIbKaDOBLvIvC_Mf2s0GQdaHldxow";
        return token;
    }
}
