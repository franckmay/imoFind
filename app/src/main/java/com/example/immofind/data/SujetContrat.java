package com.example.immofind.data;

import android.provider.BaseColumns;

public class SujetContrat {

    public  static final String TABLE_NAME = "sujet";

    public static final class SujetEntry implements BaseColumns {

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SUJET_NAME = "nom";
        public static final String COLUMN_SUJET_LOYER = "loyer";
        public static final String COLUMN_SUJET_VILLE = "ville";
        public static final String COLUMN_SUJET_DESCRIPTION = "description";
        public static final String COLUMN_SUJET_IMAGE = "image";


    }
}
