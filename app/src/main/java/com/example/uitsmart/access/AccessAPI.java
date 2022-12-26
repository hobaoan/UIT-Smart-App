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
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzIxMjgwNzYsImlhdCI6MTY3MjA0MTY3NywiYXV0aF90aW1lIjoxNjcyMDQxNjc2LCJqdGkiOiJlZDI4NDdmNy03M2Y1LTQxOTgtYWY2MS1iODM5NzY0ZTY4ZmYiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiIxMTNjN2MxYi1lNWFkLTRhZDYtOTVhMS05MTE4Y2RlNmUyYmEiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiIxMTNjN2MxYi1lNWFkLTRhZDYtOTVhMS05MTE4Y2RlNmUyYmEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.eqGMkFlswhvM2QljeTgEMnjx7eTXWHRvBKEQUrluvayElYkL9NNC8JpqF8dCXulrOn9Xc_v7HctglQku2lPMW4mhk8jqCX8_P_OMtd9yjqYwxAtV0u_MAiYSuXDoEmUXcjW7Kx1yYqlqWcvd2aNp1l-ANyBDN-bgF6CY6RELRUaZfBlSjHVdpb6JKi-RVpfoA3NEe-v25eP1ykc5QjLH3_dE481lgmWFisNaV5GO9y2mc2Aui3bM-kZGA6DF2g2FdFuANEyRyMWGu-VrhH-GW6HTImLnvKfGEGUNBnO0qv0brDOwBH6Nbh6XIlIhBTx_Sv8SH2gGPBPuuamFSof2hA";
        return token;
    }
}
