package models;

public class PostData {

    public String title;
    public String slug;
    public String markdown;
    public String text;

    public PostData setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostData setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public PostData setMarkdown(String markdown) {
        this.markdown = markdown;
        return this;
    }

    public PostData setText(String text) {
        this.text = text;
        return this;
    }

}
