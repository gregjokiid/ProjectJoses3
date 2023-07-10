package com.example.mcsjoses.model;

import com.example.mcsjoses.R;

import java.util.ArrayList;

public class JsonData {
    private static final int[] userId = {
            1,
            2,
            3,
            4,
            5
    };

    private static final int[] id = {
            1,
            2,
            3,
            4,
            5
    };

    private static final String[] title = {
            "Title 1",
            "Title 2",
            "Title 3",
            "Title 4",
            "Title 5"
    };

    private static final String[] body = {
            "Randy Fauzi F - Selasa, 15 November 2022 | 14:00 WIB",
            "Amalia Septiyani - Kamis, 10 November 2022 | 19:10 WIB",
            "Randy Fauzi F - Selasa, 25 Oktober 2022 | 11:55 WIB",
            "Randy Fauzi F - Senin, 24 Oktober 2022 | 00:45 WIB",
            "Randy Fauzi F - Rabu, 5 Oktober 2022 | 14:00 WIB"
    };

    public static ArrayList<Json> getListData() {
        ArrayList<Json> list = new ArrayList<>();
        for(int position = 0; position < userId.length; position++) {
            Json json = new Json();
            json.setUserId(userId[position]);
            json.setId(id[position]);
            json.setTitle(title[position]);
            json.setBody(body[position]);
            list.add(json);
        }
        return list;
    }

}
