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
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NzAzMTM3MjksImlhdCI6MTY3MDIyNzMyOSwiYXV0aF90aW1lIjoxNjcwMjI3MzI5LCJqdGkiOiJjMjBlMzgzYi0zYWQyLTQ1YWQtOGRhYS0wZDM0MWU4MThiYWIiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI2ZTkyNzg4YS1iMGQxLTRmYjQtYWQyYi04ZTVlN2E5MzBiNDEiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2ZTkyNzg4YS1iMGQxLTRmYjQtYWQyYi04ZTVlN2E5MzBiNDEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIn0.FRYuDI6e8wOBfY99mvDg-0seAauI64f47fTiOSuAQ2TBimJOZWOWskGThqSUerDPdUNyZT8GT6Ap6e8JnvmuaPzihtavpo2nGDi91_G_CztMgyRxmHfEIGDwZUffikU-z90jwnqshcu9Gs1kwHrwu1B8zYVVSKtIsLbzytCL4YgV_FpzopeUO3-0PhHn1oYRNEDrC5eR1KLBdz-nHb1cgEKMoz8Q31ZagdRUsU6DEu4039NWbzpU91xMmj09qCKqa7SxG5MCq7JZ6FXnbCW1f6Zno81FByYBD3WCPMjE5o-StNiXs2bMms545c6EHrM26ixT1J21Hu2m-jtHgNHrww";
        return token;
    }
}
