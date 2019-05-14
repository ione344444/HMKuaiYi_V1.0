package com.xiaoxi.translate;

import java.util.ArrayList;
import java.util.List;

public class Language {
    // 各语言字符串
    public static final String LANGUAGE_AUTODET = "自动检测";
    public static final String LANGUAGE_CHINESE = "中文";
    public static final String LANGUAGE_ENGLISH = "英语";
    public static final String LANGUAGE_AP = "文言文";
    public static final String LANGUAGE_JAPANESE = "日语";
    public static final String LANGUAGE_KOREAN = "韩语";
    public static final String LANGUAGE_FRENCH = "法语";
    public static final String LANGUAGE_RUSSIAN = "俄语";
    public static final String LANGUAGE_GERMAN = "德语";
    public static final String LANGUAGE_CHINESETRADITIONAL = "繁体中文";

    // 各语言简写
    public static final String LANGUAGEABB_AUTODET = "auto";
    public static final String LANGUAGEABB_CHINESE = "zh";
    public static final String LANGUAGEABB_ENGLISH = "en";
    public static final String LANGUAGEABB_AP = "wyw";
    public static final String LANGUAGEABB_JAPANESE = "jp";
    public static final String LANGUAGEABB_KOREAN = "kor";
    public static final String LANGUAGEABB_FRENCH = "fra";
    public static final String LANGUAGEABB_RUSSIAN = "ru";
    public static final String LANGUAGEABB_GERMAN = "de";
    public static final String LANGUAGEABB_CHINESETRADITIONAL = "cht";

    public static List<String> getAllLanguageAbb(){
        List<String> languageAbbs = new ArrayList<>();

        languageAbbs.add(LANGUAGEABB_AUTODET);
        languageAbbs.add(LANGUAGEABB_CHINESE);
        languageAbbs.add(LANGUAGEABB_ENGLISH);
        languageAbbs.add(LANGUAGEABB_AP);
        languageAbbs.add(LANGUAGEABB_JAPANESE);
        languageAbbs.add(LANGUAGEABB_KOREAN);
        languageAbbs.add(LANGUAGEABB_FRENCH);
        languageAbbs.add(LANGUAGEABB_RUSSIAN);
        languageAbbs.add(LANGUAGEABB_GERMAN);
        languageAbbs.add(LANGUAGEABB_CHINESETRADITIONAL);

        return languageAbbs;
    }

    public static List<String> getAllLanguage(){
        List<String> languageAbbs = new ArrayList<>();

        languageAbbs.add(LANGUAGE_AUTODET);
        languageAbbs.add(LANGUAGE_CHINESE);
        languageAbbs.add(LANGUAGE_ENGLISH);
        languageAbbs.add(LANGUAGE_AP);
        languageAbbs.add(LANGUAGE_JAPANESE);
        languageAbbs.add(LANGUAGE_KOREAN);
        languageAbbs.add(LANGUAGE_FRENCH);
        languageAbbs.add(LANGUAGE_RUSSIAN);
        languageAbbs.add(LANGUAGE_GERMAN);
        languageAbbs.add(LANGUAGE_CHINESETRADITIONAL);

        return languageAbbs;
    }


    /**
     * 该方法用于获取语言的简写
     *
     * @param language 语言
     * @return 该语言的简写
     */
    public static String getLanguageAbb(String language){
        switch (language){
            case LANGUAGE_AUTODET:
                return LANGUAGEABB_AUTODET;

            case LANGUAGE_CHINESE:
                return LANGUAGEABB_CHINESE;

            case LANGUAGE_ENGLISH:
                return LANGUAGEABB_ENGLISH;

            case LANGUAGE_AP:
                return LANGUAGEABB_AP;

            case LANGUAGE_JAPANESE:
                return LANGUAGEABB_JAPANESE;

            case LANGUAGE_KOREAN:
                return LANGUAGEABB_KOREAN;

            case LANGUAGE_FRENCH:
                return LANGUAGEABB_FRENCH;

            case LANGUAGE_RUSSIAN:
                return LANGUAGEABB_RUSSIAN;

            case LANGUAGE_GERMAN:
                return LANGUAGEABB_GERMAN;

            case LANGUAGE_CHINESETRADITIONAL:
                return LANGUAGEABB_CHINESETRADITIONAL;

            default:
                return null;
        }
    }

    /**
     * 该方法用于通过简写获取语种
     *
     * @param languageAbb 语种简写
     * @return 简写代表的语种
     */
    public static String getLanguageUseAbb(String languageAbb){
        switch (languageAbb){
            case LANGUAGEABB_AUTODET:
                return LANGUAGE_AUTODET;

            case LANGUAGEABB_CHINESE:
                return LANGUAGE_CHINESE;

            case LANGUAGEABB_ENGLISH:
                return LANGUAGE_ENGLISH;

            case LANGUAGEABB_AP:
                return LANGUAGE_AP;

            case LANGUAGEABB_JAPANESE:
                return LANGUAGE_JAPANESE;

            case LANGUAGEABB_KOREAN:
                return LANGUAGE_KOREAN;

            case LANGUAGEABB_FRENCH:
                return LANGUAGE_FRENCH;

            case LANGUAGEABB_RUSSIAN:
                return LANGUAGE_RUSSIAN;

            case LANGUAGEABB_GERMAN:
                return LANGUAGE_GERMAN;

            case LANGUAGEABB_CHINESETRADITIONAL:
                return LANGUAGE_CHINESETRADITIONAL;

            default:
                return "自动检测";
        }
    }

}
