package main.modelPackage;

public class DirectMessageModel {
    private UserModel sender;
    private String text;
    private String mediaUrl;
    private String mediaType;

    public DirectMessageModel(UserModel sender, String text, String mediaUrl, String mediaType) {
        setSender(sender);
        setText(text);
        setMediaUrl(mediaUrl);
        setMediaType(mediaType);
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
            this.text = text;
        }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
