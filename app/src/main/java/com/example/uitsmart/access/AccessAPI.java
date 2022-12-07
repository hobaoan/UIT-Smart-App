package com.example.uitsmart.access;

public class AccessAPI {
    public static String urlMap, urlUserCurrent;
    public static String token;

    public AccessAPI(String url, String token, String urlUserCurrent) {
        this.urlMap = url;
        this.token = token;
        this.urlUserCurrent = urlUserCurrent;
    }

    public static String getURLMap() {
        urlMap = "https://103.126.161.199/api/master/map";
        return urlMap;
    }
    public static String getUrlUserCurrent () {
        urlUserCurrent = "https://103.126.161.199/api/master/asset/user/current";
        return urlUserCurrent;
    }
    public static String getToken() {
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzAzMTM3MjksImlhdCI6MTY3MDI4OTg1MywiYXV0aF90aW1lIjoxNjcwMjI3MzI5LCJqdGkiOiI0MzQ1ZjdiMS1kYzEyLTQzZjQtYjYzYS1mN2NjNDA3Y2NmMmEiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI2ZTkyNzg4YS1iMGQxLTRmYjQtYWQyYi04ZTVlN2E5MzBiNDEiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2ZTkyNzg4YS1iMGQxLTRmYjQtYWQyYi04ZTVlN2E5MzBiNDEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.ZH4f2F1Xc7YADm1MxcJTgKxjXEy0fiRnG1EdMC9n4D0KJQ3GgbDr7Bl8_WVr3loqt0pFlI-BINJ6a5rZLIFrB5S8nS-_JgKmXDY22tNFSQKDgi5-xO9sLIQGR1AbBOmviu87vfiQpTTIwmwblfVnTXZZNB_g3oJmUCnlnnx7DBgrZMKf2kxvqRDXbLI837xsWFs1HD8GhHBE-Xp-PXy49eqG2l6N1HeW9iG4_PWYRi88_ZrOS1KeWQ1q5Wn1st-ZuCCPbbu6sP7aXTEi8hVSA91pFiREZngqs074acgn9KuuBmhxhEc57V6ZTdq_Lfqgy87duOrrRaKc7cKOqNaoDw";
        return token;
    }
}
