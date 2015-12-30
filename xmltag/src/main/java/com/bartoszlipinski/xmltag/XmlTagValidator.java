package com.bartoszlipinski.xmltag;

/**
 * Created by Bartosz Lipinski
 * 28.12.2015
 */
public class XmlTagValidator {
    public static boolean hasButterKnife() {
        try {
            butterknife.ButterKnife.setDebug(true);
            butterknife.ButterKnife.setDebug(false);
            return true;
        } catch (NoClassDefFoundError error) {
            return false;
        }
    }
}
