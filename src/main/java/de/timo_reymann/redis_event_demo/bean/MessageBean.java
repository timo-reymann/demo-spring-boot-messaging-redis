package de.timo_reymann.redis_event_demo.bean;

/**
 * @author Timo Reymann
 * @since 17.11.18
 */
public class MessageBean {
    private String content;

    public MessageBean(String content) {
        this.content = content;
    }

    public MessageBean() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "content='" + content + '\'' +
                '}';
    }
}
