package com.github.ihandy.yandex_translate_demo;

import java.util.HashMap;

/**
 * Created by Handy on 01/25/2018.
 */

public class LangsListResponse {


    HashMap<String, String> langs;

    public HashMap<String, String> getLangs() {
        return langs;
    }

    public void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }
}
