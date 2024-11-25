package umc.spring.study.apiPayload.exception.handler;

import umc.spring.study.apiPayload.code.BaseErrorCode;
import umc.spring.study.apiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode errorCode) {super(errorCode);}
}
