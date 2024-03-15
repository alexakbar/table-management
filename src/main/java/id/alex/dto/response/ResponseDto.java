package id.alex.dto.response;

import java.util.List;

public class ResponseDto {

    public boolean err;
    public String msg;
    public Object data;

    public ResponseDto() {};

    public ResponseDto(boolean err, String msg, List<?> data) {
        this.err = err;
        this.msg = msg;
        this.data = data;
    }

    public ResponseDto(boolean err, String msg, Object singleObject) {
        this.err = err;
        this.msg = msg;
        this.data = List.of(singleObject); // Create a list containing a single object
    }
}
