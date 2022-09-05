package top.wsure.auth.cache;

/*
    FileName:   Cache
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class Cache<T> {
    private T data;

    private long expired;

    public Cache(T data, long expired) {
        this.data = data;
        this.expired = expired;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }
}
