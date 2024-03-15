package id.alex.dto.response;

import java.util.List;

public class ResponseDto {

    public boolean err;
    public String msg;
    public List<?> data;

    public ResponseDto() {};

    public ResponseDto(boolean err, String msg, List<?> data) {
        this.err = err;
        this.msg = msg;
        this.data = data;
    }
}
