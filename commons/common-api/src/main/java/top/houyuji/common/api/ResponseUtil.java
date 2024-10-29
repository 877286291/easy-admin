package top.houyuji.common.api;

import top.houyuji.common.base.R;

public interface ResponseUtil {

    /**
     * 转换
     *
     * @param JSFResponse .
     * @param <T>         .
     * @return .
     */
    static <T> R<T> converter(JR<T> JSFResponse) {
        R<T> result = new R<T>();
        result.setSuccess(JSFResponse.isSuccess());
        result.setCode(JSFResponse.isSuccess() ? R.OK : R.INTERNAL_SERVER_ERROR);
        result.setMessage(JSFResponse.getMessage());
        result.setData(JSFResponse.getResult());
        return result;
    }
}
