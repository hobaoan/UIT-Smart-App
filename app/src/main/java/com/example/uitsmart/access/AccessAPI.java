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
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzIyMTE0MjMsImlhdCI6MTY3MjEyNTAyMywiYXV0aF90aW1lIjoxNjcyMTI1MDIzLCJqdGkiOiJmNmEzMTNjYy1kNWMwLTQ4OTctYjc1MS1hOWU3OWUyNDYxOTAiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiIxZDhlNzJkZS04NTg4LTRiYmUtOTdmNS05N2YxNTU4ZGUxZjYiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiIxZDhlNzJkZS04NTg4LTRiYmUtOTdmNS05N2YxNTU4ZGUxZjYiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.MN97EmfOv0UAy566mZclpv3lRc-MIfiyq6tfctoaC4Uko_SmelSzt_eIA5zxgd0mC8CeZmTTvqVQJ_S5m-ORRPglZOT6FMzVqdVEdPLMJF3ri-vqOqARFpyZSGEwxytbvQWlaoB7VmGiRfJXwX582XArt4UqQQnbhn0aIez7m1l1M9xRWAijzC0_529hp9OJArr9ceVqYf3Y93PklY8DJdG9QTOX7SIYYcEQieJDQpTq1E0YVgPb2zowZEReAtbNE-7bg6pgAMu6BgWqEL3Ydyz8qaTJMpTnqYSRvtGpAMUhFV9huSoVpnuKNYql3PFbRX81_QdjAoIbutdALkcl6Q";
        return token;
    }
}
