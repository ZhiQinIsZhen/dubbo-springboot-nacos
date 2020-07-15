package com.liyz.dubbo.service.file.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/24 16:17
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileType {

    PORTRAIT(0, "/portrait/", "头像"),
    ID_CARD(1, "/idCard/", "身份证"),
    BANNER(2, "/banner/", "banner图片"),
    LOGO(3, "/logo/", "logo");

    @Getter
    private int code;
    @Getter
    private String subPath;
    @Getter
    private String desc;

    public static FileType getByCode(int code) {
        FileType[] enums = FileType.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getCode() == code) {
                return enums[i];
            }
        }
        return null;
    }
}
